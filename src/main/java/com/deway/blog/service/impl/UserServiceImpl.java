package com.deway.blog.service.impl;

import com.deway.blog.config.datasource.TransactionManager;
import com.deway.blog.entiry.auth.User;
import com.deway.blog.mapper.UserMapper;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.Pbkdf2Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Deway
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Throwable.class, transactionManager = TransactionManager.USER_TRANSACTION)
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        try {
            var salt = Pbkdf2Util.randomSalt();
            user.setSalt(salt);
            var pwd  = Pbkdf2Util.encrypt(user.getPassword(), salt.getBytes());
            user.setPassword(pwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
        return userMapper.create(user) > 0;
    }

    @Override
    public boolean exist(String userId) {
        return userMapper.isExistedById(userId);
    }

    @Override
    public boolean login(User user) {
        var pwd = user.getPassword();
        user.setPassword(null);
        var users = userMapper.find(user);
        if(users.size() != 0) {
            var u = users.get(0);
            try {
                user.setPassword(Pbkdf2Util.encrypt(pwd, u.getSalt().getBytes()));
                return userMapper.find(user).size() > 0;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
