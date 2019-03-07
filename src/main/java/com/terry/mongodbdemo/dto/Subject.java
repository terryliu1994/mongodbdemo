package com.terry.mongodbdemo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //启用setter方法返回对象
public class Subject {

    String name;

    Integer score;
}
