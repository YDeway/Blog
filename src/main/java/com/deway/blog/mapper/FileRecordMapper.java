package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entity.FileRecord;
import org.apache.ibatis.annotations.Param;

@TargetDataSource(dataSource = DynamicDataSource.BLOG_DATASOURCE)
public interface FileRecordMapper {

    boolean create(FileRecord file);

    FileRecord findById(@Param("id")long id);


}