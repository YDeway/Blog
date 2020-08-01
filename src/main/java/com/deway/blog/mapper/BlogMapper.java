package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.Blog;

import java.util.List;

@TargetDataSource(dataSource = DynamicDataSource.BLOG_DATASOURCE)
public interface BlogMapper {

    List<Blog> list();

}
