package com.deway.blog.mapper;

import com.deway.blog.entiry.auth.AccessToken;

public interface AccessTokenMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);

    AccessToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
}