package com.shenque.controller;


import com.shenque.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="hello world")
@RestController
@Slf4j
public class HelloController {

    //通过此注解进行注入！
    @Autowired
    Person person;

    @ApiOperation(value = "测试函数")
    @GetMapping("/hello")
    public String hello() {
        return person.toString();
    }



}
