package com.deway.blog.service.impl;

import com.deway.blog.entity.BackGroundImage;
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

    /**
     * 数据库数据有问题则可能会抛出@NullPointerException
     *
     * @param id id
     * @return path
     */
    @Override
    public String getFilePath(long id) {
        var image = imageMapper.findById(id);
        return image.getFileRecord().getPath();
    }
}
