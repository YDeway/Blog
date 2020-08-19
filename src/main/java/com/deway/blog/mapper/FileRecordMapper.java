package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.FileRecord;

@TargetDataSource(dataSource = DynamicDataSource.BLOG_DATASOURCE)
public interface FileRecordMapper {

    boolean create(FileRecord file);

}