package com.deway.blog.service.impl;

import com.deway.blog.config.datasource.TransactionManager;
import com.deway.blog.entity.Blog;
import com.deway.blog.mapper.BlogMapper;
import com.deway.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
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
    public List<Blog> list(int pageNum, int pageSize, Blog blog) {
        //@todo 这里是否同步处理？应该要吧
        PageHelper.<Blog>offsetPage(pageNum, pageSize);
        return blogMapper.find(blog);
    }
}
