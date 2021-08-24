package com.logs.logs.controller;

import com.logs.logs.model.LogModel;
import com.logs.logs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import search.SearchRequestDTO;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService){
        this.logService = logService;
    }

    @PostMapping
    public void save(@RequestBody final LogModel logModel){
        logService.save(logModel);
    }

    @GetMapping
    public List<LogModel> findAll(){
        return logService.findAll();
    }

    @PostMapping("/search")
    public List<LogModel> search(@RequestBody final SearchRequestDTO dto) throws IOException {
        return logService.search(dto);
    }
}
