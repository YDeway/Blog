package com.deway.blog.controller;

import com.deway.blog.entity.Blog;
import com.deway.blog.service.BlogService;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Deway
 */
@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public R<List<Blog>> listBlog() {
        return  R.response(HttpStatus.OK, blogService.list());
    }

}
