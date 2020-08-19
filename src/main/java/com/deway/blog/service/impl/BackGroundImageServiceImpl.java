package com.deway.blog.service.impl;

import com.deway.blog.entiry.BackGroundImage;
import com.deway.blog.mapper.BackGroundImageMapper;
import com.deway.blog.service.BackGroundImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BackGroundImageServiceImpl implements BackGroundImageService {

    private final BackGroundImageMapper imageMapper;

    @Override
    public boolean create(BackGroundImage image) {
        return imageMapper.create(image);
    }

    @Override
    public String getFilePath(long id) {
        return imageMapper.findById(id);
    }
}
