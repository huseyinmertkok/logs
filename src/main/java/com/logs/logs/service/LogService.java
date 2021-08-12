package com.logs.logs.service;

import com.logs.logs.model.LogModel;
import com.logs.logs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public void save(final LogModel logModel){
        logRepository.save(logModel);
    }

    public LogModel findById(final String id){
        return logRepository.findById(id).orElse(null);
    }
}
