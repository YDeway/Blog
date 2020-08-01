package com.deway.blog.service.impl;

import com.deway.blog.entiry.Blog;
import com.deway.blog.mapper.BlogMapper;
import com.deway.blog.service.BlogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;

    @Override
    public List<Blog> list() {
        System.out.println("shit");
        return blogMapper.list();
    }
}
