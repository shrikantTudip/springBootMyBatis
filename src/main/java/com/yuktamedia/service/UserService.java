package com.yuktamedia.service;

import com.yuktamedia.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public void changePassword(User user);
    public void changeDisplayName(User user);
}
