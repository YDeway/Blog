package com.deway.blog.service.impl;

import com.deway.blog.entity.FileRecord;
import com.deway.blog.mapper.FileRecordMapper;
import com.deway.blog.service.FileRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileRecordServiceImpl implements FileRecordService {

    private final FileRecordMapper fileMapper;

    @Override
    public Boolean create(FileRecord file) {
        return fileMapper.create(file);
    }

    /**
     *  可能会有空指针异常
     *
     * @param id 文件存储的ID
     * @return 文件在服务器的地址
     */
    @Override
    public String getFilePath(Long id) {
        var record = fileMapper.findById(id);
        return record == null? "" : record.getPath();
    }
}
