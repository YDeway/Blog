package com.deway.blog.service;

import com.deway.blog.entity.FileRecord;

/**
 * @author Deway
 */
public interface FileRecordService {

    Boolean create(FileRecord file);

    String getFilePath(Long id);

}
