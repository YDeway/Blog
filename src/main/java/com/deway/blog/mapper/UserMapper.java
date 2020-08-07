package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.auth.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author Deway
 */
@TargetDataSource(dataSource = DynamicDataSource.USER_DATASOURCE)
public interface UserMapper {

    int create(User user);

    Boolean isExistedById(@Param("userId") String userId);

    List<User> find(User user);
}
