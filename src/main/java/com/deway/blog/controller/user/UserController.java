package com.deway.blog.controller.user;

import com.deway.blog.entiry.User;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private  UserService userService;

    @PostMapping("/login")
    public R login() {
        return R.response(200, "done", "success");
    }

    @PostMapping("/register")

    public R register(@RequestBody User user) {
        return R.response(200, "status", userService.register(user));
    }
}
