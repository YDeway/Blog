package com.deway.blog.service;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.FileRecord;

/**
 * @author Deway
 */
public interface FileRecordService {

    Boolean create(FileRecord file);


}
