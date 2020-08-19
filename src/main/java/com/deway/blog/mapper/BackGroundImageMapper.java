package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.BackGroundImage;
import org.apache.ibatis.annotations.Param;

@TargetDataSource(dataSource = DynamicDataSource.BLOG_DATASOURCE)
public interface BackGroundImageMapper {

    boolean create(BackGroundImage record);

    String findById(@Param("id") long id);
}