package com.terry.mongodbdemo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "user_collection")
@Data
@Accessors(chain = true)
public class User {

    @Id
    @Field("_id")
    private String userId;

    private String userName;

    private Integer age;

    private String[] relationships;

    private List<Subject> subjects;

} 