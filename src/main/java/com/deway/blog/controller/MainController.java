package com.deway.blog.controller;

import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author Deway
 */
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

    @GetMapping
    public R<Properties> info(HttpServletRequest request) {
        request.getSession();
        return R.response(HttpStatus.OK, System.getProperties());
    }


}
