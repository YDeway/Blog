package com.deway.blog.service.impl;

import com.deway.blog.config.datasource.TransactionManager;
import com.deway.blog.entity.Blog;
import com.deway.blog.mapper.BlogMapper;
import com.deway.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Deway
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Throwable.class, transactionManager = TransactionManager.BLOG_TRANSACTION)
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;

    @Override
    public List<Blog> list() {
        return blogMapper.find(null);
    }
}
