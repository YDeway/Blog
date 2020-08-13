package com.deway.blog.controller.user;

import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.entiry.auth.User;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import java.util.HashMap;

/**
 *  应不应该设置状态码？还是应该由容器管理？
 *
 * @author Deway
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private Jedis redis;

    @Value("${spring.session-expire}")
    private int sessionExpire;

    /**
     * 现在的做法则是可在多个地点同时登录
     *
     * @todo 考虑多线程情况下同一个用户同时登录，有点小问题,虽然概率很小，redis服务是多线程共享的，第一个if里。
     *        引入redis增加了系统的复杂度，实际上这个项目根本没有必要使用redis
     * @todo 中文字符处理
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody User user) {
        var token = "";
        if(redis.exists(user.getUserId())) {
            token = redis.hget(user.getUserId(), AccessToken.Constant.TOKEN);
        }
        else {
            if(userService.login(user)) {
                var salt = RandomSalt.randomSalt();
                var m = new HashMap<String, String>(2);
                m.put(AccessToken.Constant.UID, user.getUserId());
                token = JwtUtil.encrypt(m, salt, sessionExpire / 2);
                m.clear();
                m.put(AccessToken.Constant.SALT, salt);
                m.put(AccessToken.Constant.TOKEN, token);
                redis.hset(user.getUserId(), m);
                redis.expire(user.getUserId(), sessionExpire / 2);
            }
            else {
                return R.response(HttpStatus.UNAUTHORIZED, "unknown username or invalid password");
            }
        }

        return R.response(HttpStatus.SUCCESS, token);
    }

    /**
     * 需要对重复点击做处理
     * @todo 用户多次点击注册，多线程容器中会出现问题，但这个问题可能会被数据唯一主键给抵消掉，可颁发一次性使用的短时间令牌
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody User user) {
        if(userService.exist(user.getUserId())) {
            return R.response(HttpStatus.CONFLICT,  "user's id is repeated");
        }
        try {
            if(userService.create(user)) {
                return R.response(HttpStatus.SUCCESS, "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.response(HttpStatus.INTERNAL_SERVER_ERROR, "unknown server error!");
    }


    @Autowired
    private void init(UserService userService, Jedis redis) {
        this.redis = redis;
        this.userService = userService;
    }

}

