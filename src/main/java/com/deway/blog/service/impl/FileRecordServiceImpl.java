package com.deway.blog.service.impl;

import com.deway.blog.entiry.FileRecord;
import com.deway.blog.mapper.FileRecordMapper;
import com.deway.blog.service.FileRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileRecordServiceImpl implements FileRecordService {

    private final FileRecordMapper fileMapper;

    @Override
    public Boolean insert(FileRecord file) {
        return fileMapper.insert(file);
    }
}
