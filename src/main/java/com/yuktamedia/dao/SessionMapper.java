package com.yuktamedia.dao;

import com.yuktamedia.model.Session;

import java.util.List;

public interface SessionMapper {
    List<Session> findAll();

    Session findByUserId(Integer id);

    void deleteById(String sessionId);

    void save(Session session);
}
