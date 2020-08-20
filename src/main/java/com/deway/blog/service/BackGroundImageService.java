package com.deway.blog.service;

import com.deway.blog.entity.BackGroundImage;

public interface BackGroundImageService {

    boolean create(BackGroundImage image);

    String getFilePath(long id);

}
