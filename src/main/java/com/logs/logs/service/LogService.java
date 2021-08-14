package com.logs.logs.service;

import com.logs.logs.model.LogModel;
import com.logs.logs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<LogModel> findAll(){
        List<LogModel> list = new ArrayList<>();
        Iterable<LogModel> findAll = logRepository.findAll();
        findAll.forEach(p -> list.add(p));
        return list;
    }

}
