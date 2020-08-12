package com.deway.blog.controller.user;

import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.entiry.auth.User;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.HttpCode;
import com.deway.blog.tool.JwtUtil;
import com.deway.blog.tool.R;
import com.deway.blog.tool.RandomSalt;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final Jedis redis;

    /**
     * 现在的做法则是可在多个地点同时登录
     * 考虑多线程情况下同一个用户同时登录，有点小问题的虽然概率很小，redis服务是多线程共享的
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody User user, @Value("${spring.session-expire}")int expire) {
        var token = "";
        if(redis.exists(user.getUserId())) {
            token = redis.hget(user.getUserId(), AccessToken.Constant.TOKEN);
        }
        else {
            if(userService.login(user)) {
                var salt = RandomSalt.randomSalt();
                var m = new HashMap<String, String>(2);
                m.put(AccessToken.Constant.UID, user.getUserId());
                token = JwtUtil.encrypt(m, salt, expire * 1000);
                m.clear();
                m.put(AccessToken.Constant.SALT, salt);
                m.put(AccessToken.Constant.TOKEN, token);
                redis.hset(user.getUserId(), m);
                redis.expire(user.getUserId(), expire);
            }
            else {
                return R.response(HttpCode.UNAUTHORIZED, "unknown username or invalid password");
            }
        }

        return R.response(HttpCode.SUCCESS, token);
    }

    /**
     * 需要对重复点击做处理
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody User user) {
        if(userService.exist(user.getUserId())) {
            return R.response(HttpCode.CONFLICT,  "user's id is repeated");
        }
        try {
            if(userService.create(user)) {
                return R.response(HttpCode.SUCCESS, "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.response(HttpCode.INTERNAL_SERVER_ERROR, "unknown server error!");
    }


}

