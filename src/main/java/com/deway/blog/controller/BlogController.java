package com.deway.blog.controller;

import com.deway.blog.entity.Blog;
import com.deway.blog.service.BlogService;
import com.deway.blog.tool.R;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Deway
 */
@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/list/{pageNum}/{pageSize}")
    public R<List<Blog>> listBlog(@PathVariable("pageNum")int pageNum,
                                  @PathVariable("pageSize")int pageSize, Blog blog) {
        return  R.response(HttpStatus.OK, blogService.list(pageNum, pageSize, blog));
    }

}
