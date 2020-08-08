package com.deway.blog.controller.user;

import com.deway.blog.entiry.auth.User;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionManager;
import org.springframework.web.bind.annotation.*;

/**
 * @author Deway
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public R<String> login(@RequestBody User user) {
        if(userService.login(user)) {
            return R.response(200, "success", "");
        } else {
            return R.response(404, "failed", "");
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        if(userService.exist(user.getUserId())) {
            return R.response(400, "failed", "user's id is repeated");
        }
        if(userService.register(user)) {
            return R.response(200, "success", "success");
        } else {
            return R.response(500, "failed", "unknown server error!");
        }
    }
}