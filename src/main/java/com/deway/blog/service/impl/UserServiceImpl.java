package com.deway.blog.service.impl;

import com.deway.blog.config.datasource.TransactionManager;
import com.deway.blog.entiry.auth.AccessToken;
import com.deway.blog.entiry.auth.User;
import com.deway.blog.mapper.UserMapper;
import com.deway.blog.service.UserService;
import com.deway.blog.tool.Pbkdf2Util;
import com.deway.blog.tool.RandomSalt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Deway
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final Jedis redis;

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManager.USER_TRANSACTION)
    public boolean create(User user) throws Exception{
        try {
            var salt = RandomSalt.randomSalt();
            user.setSalt(salt);
            var pwd  = Pbkdf2Util.encrypt(user.getPassword(), salt.getBytes());
            user.setPassword(pwd);
        } catch (InvalidKeySpecException e) {
            var ex = new Exception();
            ex.addSuppressed(e);
            ex.initCause(e);
            throw ex;
        }
        return userMapper.create(user);
    }

    @Override
    public boolean exist(String userId) {
        return userMapper.isExistedById(userId);
    }

    @Override
    public boolean login(User user) {
        var users = userMapper.find(user);
        if(users.size() != 0) {
            var u = users.get(0);
            try {
                var encrypt = Pbkdf2Util.encrypt(user.getPassword(), u.getSalt().getBytes());
                return  encrypt.equals(u.getPassword());
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void xx(AccessToken accessToken) {

    }


}
