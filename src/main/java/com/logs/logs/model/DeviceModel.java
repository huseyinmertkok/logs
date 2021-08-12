package com.logs.logs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName="deviceIndex")
@Setting(settingPath = "static/es-setting.json")
public class DeviceModel {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String gemNumber;
    @Field(type = FieldType.Text)
    private String serialNumber;
}
