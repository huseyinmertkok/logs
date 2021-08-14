package com.logs.logs.model;

import java.util.HashMap;
import java.util.Map;
public enum LogLevelEnum {
    DEBUG(1),
    INFO(2),
    ERROR(4),
    FATAL(5);
    private int id;
    private LogLevelEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    };
    public static LogLevelEnum getById(int id) {
        return logLevelsByIdMap.get(id);
    }
    private static final Map<Integer,LogLevelEnum> logLevelsByIdMap;
    static {
        logLevelsByIdMap = new HashMap<Integer,LogLevelEnum>();
        for (LogLevelEnum v : LogLevelEnum.values()) {
            logLevelsByIdMap.put(v.getId(), v);
        }
    }
}
