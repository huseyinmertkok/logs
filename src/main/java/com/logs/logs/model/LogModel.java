package com.logs.logs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

@Document(indexName="log_index")
@Setting(settingPath = "static/es-settings.json")
public class LogModel {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    private DeviceModel device;
    private String name;
    private LogLevelEnum level;
    private String request;
    private String response;
    private String message;
    private Date requestDateTime;
    private Date responseDateTime;
    private Date createdAt;
}
