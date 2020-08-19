package com.deway.blog.service;

import com.deway.blog.entiry.BackGroundImage;

public interface BackGroundImageService {

    boolean create(BackGroundImage image);

    String getFilePath(long id);

}
