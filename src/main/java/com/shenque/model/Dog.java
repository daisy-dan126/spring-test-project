package com.shenque.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//自动生成get,set方法
@Data
//将此类注入到spring容器中
@Component
//有参构造方法
@AllArgsConstructor
//无参构造方法
@NoArgsConstructor
public class Dog {

    private String name;
    private int age;


}
