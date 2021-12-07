package com.shenque;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //此注解是开启事务！！
@EnableScheduling//此注解是开启定时任务！
@MapperScan("com.shenque.dao")
@SpringBootApplication
@EnableAsync //异步调用的注解@EnableAsync 加上才可以使用！ 主要是为了扫描范围包下的所有 @Async 注解。
public class SpringTestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestProjectApplication.class, args);
	}

}
