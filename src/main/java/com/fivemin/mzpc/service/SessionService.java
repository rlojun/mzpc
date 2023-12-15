package com.fivemin.mzpc.service;

import com.fivemin.mzpc.data.entity.Session;
import com.fivemin.mzpc.data.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void saveStringSession(String code, String name, String stringValue){
        Session newSession = new Session();
        newSession.setCode(code);
        newSession.setName(name);
        newSession.setStringValue(stringValue);
        sessionRepository.save(newSession);
    }

    public void saveIntSession(String code, String name, int intValue){
        Session newSession = new Session();
        newSession.setCode(code);
        newSession.setName(name);
        newSession.setIntValue(intValue);
        sessionRepository.save(newSession);
    }

    public void saveLocalTimeSession(String code, String name, LocalTime localTimeValue){
        Session newSession = new Session();
        newSession.setCode(code);
        newSession.setName(name);
        newSession.setLocalTimeValue(localTimeValue);
        sessionRepository.save(newSession);
    }

    public Session findByCodeAndName(String code, String name){
        return sessionRepository.findByCodeAndName(code, name);
    }

    @Transactional
    public void deleteByCode(String code){
        sessionRepository.deleteByCode(code);
    }
}
