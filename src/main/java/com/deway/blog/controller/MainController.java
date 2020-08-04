package com.deway.blog.controller;

import com.deway.blog.tool.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Deway
 */
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/main")
    public R info(HttpServletRequest request) {
        request.getSession();
        return R.response(200, "SUCCESS", System.getProperties());
    }


}
