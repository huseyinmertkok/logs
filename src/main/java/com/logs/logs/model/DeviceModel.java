package com.logs.logs.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName="deviceIndex")
@Setting(settingPath = "static/es-setting.json")
public class DeviceModel {
    private String id;
    private String gemNumber;
    private String serialNumber;
}
