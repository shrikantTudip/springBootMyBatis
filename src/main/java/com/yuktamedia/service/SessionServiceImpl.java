package com.yuktamedia.service;

import com.yuktamedia.dao.SessionMapper;
import com.yuktamedia.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionMapper sessionMapper;

    @Override
    public List<Session> findAll() {
        return sessionMapper.findAll();
    }

    @Override
    public Session findByUserId(Integer id) {
        return sessionMapper.findByUserId(id);
    }

    @Override
    public void deleteById(String sessionId) {
        sessionMapper.deleteById(sessionId);
    }

    @Override
    public void save(Session session) {
        sessionMapper.save(session);
    }
}
