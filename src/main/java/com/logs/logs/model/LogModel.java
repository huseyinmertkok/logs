package com.logs.logs.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

@Data
@Document(indexName="log_index")
@Setting(settingPath = "static/es-settings.json")
public class LogModel {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Object)
    private DeviceModel device;

    @Field(type = FieldType.Text)
    private String name;

    private LogLevelEnum level;

    @Field(type = FieldType.Text)
    private String request;

    @Field(type = FieldType.Text)
    private String response;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date requestDateTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date responseDateTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date createdAt;
}
