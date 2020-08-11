package com.deway.blog.controller.user;

import com.deway.blog.entiry.auth.User;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/login")
    public R<String> login(@RequestBody User user, HttpServletResponse resp) {
        if(userService.login(user)) {
            resp.setStatus(200);
            return R.response(200, "success", "");
        } else {
            resp.setStatus(404);
            return R.response(404, "failed", "");
        }
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody User user) {
        if(userService.exist(user.getUserId())) {
            return R.response(400, "failed", "user's id is repeated");
        }
        try {
            if(userService.register(user)) {
                return R.response(200, "success", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.response(500, "failed", "unknown server error!");
    }
}