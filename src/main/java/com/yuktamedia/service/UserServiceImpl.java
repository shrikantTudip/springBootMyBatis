package com.yuktamedia.service;

import com.yuktamedia.model.User;
import com.yuktamedia.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userMapper.save(user);
    }

    @Override
    public void changePassword(User user) {
        String password = user.getPassword();
        String email = user.getEmail();
        userMapper.updatePassword(password, email);
    }

    @Override
    public void changeDisplayName(User user) {
        String status = user.getPassword();
        String email = user.getEmail();
        userMapper.updateStatus(status, email);
    }

}
