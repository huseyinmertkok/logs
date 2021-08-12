package com.logs.logs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

@Document(indexName="logIndex")
@Setting(settingPath = "static/es-setting.json")
public class LogModel {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Object)
    private DeviceModel device;
    @Field(type = FieldType.Text)
    private String name;
    //private LogLevelEnum level;
    @Field(type = FieldType.Text)
    private String request;
    @Field(type = FieldType.Text)
    private String response;
    @Field(type = FieldType.Text)
    private String message;
    @Field(type = FieldType.Text)
    private Date requestDateTime;
    @Field(type = FieldType.Text)
    private Date responseDateTime;
    @Field(type = FieldType.Text)
    private Date createdAt;
}
