package com.shenque.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//测试定时任务的类, 记住！执行定时任务的类要放到springboot容器中！
@Component
public class TestScheduled {

    /**
     * 设置定时任务，每一分钟执行一次
     * cron从左到右分为:秒，分钟，小时，日，月，周   按照从右往左去分析  *代表任意
     * 例如下面的"0 00 02 ? * *" 从右往左就是，每个月周一到周日的凌晨02点00分0秒 说白了就是2点整执行一次  ?是解决周日期和日日期冲突的标识
     */
    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(fixedDelay=10000)
    public void execute() {
        System.out.println("正在执行定时任务......" + System.currentTimeMillis());
    }

}
