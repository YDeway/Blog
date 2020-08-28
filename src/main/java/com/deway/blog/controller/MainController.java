package com.deway.blog.controller;

import com.deway.blog.tool.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author Deway
 */
@RestController
@RequestMapping("/")
@AllArgsConstructor
@Api(value = "main controller", tags = {"返回一些信息"})
public class MainController {

    @GetMapping
    public R<Properties> info(HttpServletRequest request) {
        request.getSession();
        return R.response(HttpStatus.OK, System.getProperties());
    }
}
