package com.shenque.controller;

import com.shenque.service.TaskAsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="异步")
@RestController
@Slf4j //日志注解，简化我们编写会自动编写成：private static final Logger log = LoggerFactory.getLogger(TestController.class);
public class TaskAsyncController {

    @Autowired
    private TaskAsyncService taskService;

    /**
     *  1.springboot异步调用原理 其实就是创建一个默认的线程池，只要调用异步方法就在线程池中抽取线程单独执行异步方法，
     *      执行结束后放回线程池即可！ 我们也可以自己定义线程池！
     *
     *  2.下面的/taskAsync 接口执行taskService类的三个方法，而这三个方法都是异步方法，所以响应速度极快
     *
     *  3.测试路径： http://localhost:8080/taskAsync
     *
     *  4.如果想要记录异步执行的错误日志(像kafka 异步写入调用回调函数，看pdf36页)，可以在doTaskOne doTaskTwo doTaskThree
     *      异步方法中加上try catch 判断，如果异步执行异常直接写入日志即可！效果和kafka回调一样，springboot的回调
     *      的目的是为了判断异步线程时候执行成功，获取异步反回的返回值, 例如1+2+100  两个线程分别执行1+。。50， 50+。。100
     *      然后主线程判断回调异步是否执行成功，再相加，这就需要回调了！
     *      具体查看网址： https://juejin.im/post/5d42ef2ef265da03cd0a5f23  或者message_serve的异步消息写法！
     *
     *  5.注意：异步方法(taskExecute)不能与被调用的异步方法(doTaskOne doTaskTwo doTaskThree)在同一个类中,
     *      所以我新建了TaskAsyncService类 编写异步方法！
     */
    @ApiOperation(value = "异步查询")
    @GetMapping("/taskAsync")
    public String taskExecute() {
        try {
            taskService.doTaskOne();
            taskService.doTaskTwo();
            taskService.doTaskThree();
        } catch (Exception e) {
            log.error("error executing task for {}",e.getMessage());
        }
        return "ok";
    }
}