package com.wer.elasticsearch.model;

import lombok.Data;

@Data
public class UserDocument {

    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String city;
}
