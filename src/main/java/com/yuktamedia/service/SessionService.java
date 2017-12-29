package com.yuktamedia.service;

import com.yuktamedia.model.Session;

import java.util.List;

public interface SessionService {
    List<Session> findAll();

    Session findByUserId(Integer id);

    void deleteById(String sessionId);

    void save(Session session);
}
