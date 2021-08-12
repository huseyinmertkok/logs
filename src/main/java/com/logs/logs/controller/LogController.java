package com.logs.logs.controller;

import com.logs.logs.model.LogModel;
import com.logs.logs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
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

    @GetMapping("/{id}")
    public LogModel findById(@PathVariable final String id){
        return logService.findById(id);
    }
}
