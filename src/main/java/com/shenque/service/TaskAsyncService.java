package com.shenque.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskAsyncService {

    /**
     *  1.注意！ @Async所修饰的函数不要定义为static类型，这样异步调用不会生效
     *
     *  2.如果我们使用自定义线程池来创建异步，那么我们的异步注解就要这样：
     *      @Async("taskExecutor")  参数为线程池 @Bean("taskExecutor")中的参数id
     *
     *  3. 关于自定义线程池写法看网址 ： https://juejin.im/post/5b27b8366fb9a00e46675879
     */
    @Async
    public void doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
