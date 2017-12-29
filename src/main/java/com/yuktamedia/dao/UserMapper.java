package com.yuktamedia.dao;

import com.yuktamedia.model.User;

public interface UserMapper {
    User findByEmail(String email);

	void save(User user);

	void updatePassword(String password, String email);

	void updateDisplayName(String displayName, String email);

	void updateStatus(String status, String email);
}
