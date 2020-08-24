package com.deway.blog.controller.user;

import com.deway.blog.config.AuthTokenConfig;
import com.deway.blog.entity.auth.User;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import java.util.HashMap;

/**
 *  应不应该设置状态码？还是应该由容器管理？
 *
 * @author Deway
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final Jedis redis;

    private final AuthTokenConfig config;

    /**
     *
     * @todo 考虑多线程情况下同一个用户同时登录
     * @todo 中文字符处理
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody User user) {
        var token = "";
        //允许重复登录
        //@todo 然而多个地方会共享这个token，每个地方都会共享过期时间，还会共享退出
        if (redis.exists(user.getUserId()) && config.isMultiSignOn()) {
            token = redis.hget(user.getUserId(),JwtConstant.TOKEN);
            redis.expire(user.getUserId(), config.getSessionExpire());
        }
        //不允许重复登录或者未登录现登录
        else if(userService.login(user)) {
            var hm = new HashMap<String, String>(1);
            hm.put(JwtConstant.UID, user.getUserId());
            var salt = RandomSalt.randomSalt();
            token = JwtTokenUtil.encrypt(hm, salt);

            hm.clear();
            hm.put(JwtConstant.TOKEN, token);
            hm.put(JwtConstant.SALT, salt);
            redis.hset(user.getUserId(), hm);
            redis.expire(user.getUserId(), config.getSessionExpire());
        }
        else {
            return R.response(HttpStatus.UNAUTHORIZED, "unknown username or invalid password");
        }
        var resp = new HashMap<String, String>(1);
        resp.put("token", token);
        return R.response(HttpStatus.OK, resp);
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
                return R.response(HttpStatus.OK, "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.response(HttpStatus.INTERNAL_SERVER_ERROR, "unknown server error!");
    }

}

