package com.shenque.controller;


import com.shenque.entity.SysUser;
import com.shenque.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author Promise
 * @createTime 2018年12月27日 下午11:13:22
 * @description
 *  @Api：用在请求的类上，说明该类的作用
        tags="说明该类的作用"
        value="该参数没什么意义，所以不需要配置"
 */
@RestController
@Api(tags="测试接口")
@Slf4j   //日志注解，简化我们编写会自动编写成：private static final Logger log = LoggerFactory.getLogger(TestController.class);
public class TestController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 新添加的事务方法！
     * 如果想启动事务，首先要在springboot的启动类上加上注解：@EnableTransactionManagement
     * 然后在service层需要事务的方法(也可以是类)上加上注解：@Transactional 默认配置即可，遇到异常会自动回滚
     * 有几个注意地方：第一 @Transactional 需要在@service层; 第二 在业务层不要捕获异常，如果捕获异常则不会回滚
     * 我们正确的做法是业务层不捕获异常，在controller层我们统一处理异常即可！
     *
     * 详细信息看网址：https://blog.csdn.net/nextyu/article/details/78669997
     * 注意事项看：https://www.jianshu.com/p/380a9d980ca5
     * 这里我们测试一下插入数据
     */
    @ApiOperation(value = "事务请求")
    @GetMapping("/affairAddUser")
    public String affairAddUser() {
        try {
            sysUserService.affairAddUser();
        } catch (Exception e) {
            e.printStackTrace();
            return "自我抛异常";
        }
        return "插入成功";
    }

    /**
     *  用来测试springboot事务是否可以控制hbase数据库，事实证明根本不可能，因为springboot的事务是针对
     *  mybatis的，hbase这类数据库，根本不支持  hbase本身只支持行级事务，只能我们自己程序控制了
     */
    @ApiOperation(value = "请求hbase，暂不使用")
    @GetMapping("/affairAddHbase")
    public String affairAddHbase() {
        try {
            sysUserService.affairAddHbase();
        } catch (Exception e) {
            e.printStackTrace();
            return "hbase自我抛异常";
        }
        return "hbase插入成功";
    }

    /**
     * 查询1
     * 请求路径： http://localhost:8080/user
     */
    @ApiOperation(value = "固定查询ID=1")
    @GetMapping("/user")
    public SysUser getUser() {
        return sysUserService.findById(1L);
    }

    /**
     * 查询2  如果我们需要获取Url=localhost:8080/user2/id中的id值，实现代码如下：
     * method= RequestMethod.GET 是指定是GET请求访问
     * @PathVaribale 获取url中的数据
     * 请求路径： http://localhost:8080/user2/1
     */
    @ApiOperation(value = "根据ID查询")
    @GetMapping(value="/user2/{id}")
    public SysUser getUser2(@PathVariable("id") Long id) {
        log.info("打印");
        return sysUserService.findById(id);
    }

    /**
     * 查询3
     * 同样，如果我们需要在url有多个参数需要获取，则如下代码所示来做就可以了。
     * 请求路径： http://localhost:8080/user3/1/aa
     */
    @ApiOperation(value = "测试多个参数")
    @GetMapping(value="/user3/{id}/{name}")
    public String getUser3(@PathVariable("id") Long id, @PathVariable("name") String name) {
        return "id:"+id+" name:"+name;
    }

    /**
     * 查询4
     * 以上，通过@PathVariable注解来获取URL中的参数时的前提条件是我们知道url的格式时怎么样的。
       只有知道url的格式，我们才能在指定的方法上通过相同的格式获取相应位置的参数值。
       一般情况下，url的格式为：localhost:8080/user4?id=98,这种情况下该如何来获取其id值呢,这就需要借助于@RequestParam来完成
       请求路径： http://localhost:8080/user4?id=1    如果id没有值，服务器还没有判断就会报错！
     */
    @ApiOperation(value = "根据ID查询")
    @GetMapping(value="/user4")
    public SysUser getUser4(@RequestParam("id") Long id){
        return sysUserService.findById(id);
    }

    /**
     * 查询5
     * 如果在url中有多个参数，即类似于localhost:8080/hello?id=98&&name=wojiushimogui这样的url，
     * 同样可以这样来处理。具体代码如下：
     * 请求路径： http://localhost:8080/user5?id=1&name=aaa
     */
    @ApiOperation(value = "测试多个参数")
    @GetMapping(value="/user5")
    public String getUser5(@RequestParam("id") Long id, @RequestParam("name") String name){
        return "id:"+id+ " name:"+name;
    }

    /**
     *  查询6
     *  @GetMapping 组合注解
        @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
        该注解将HTTP Get 映射到 特定的处理方法上。
        即可以使用@GetMapping(value = “/hello”)来代替@RequestMapping(value=”/hello”,method= RequestMethod.GET)。
        即可以让我们精简代码。
        请求路径： http://localhost:8080/user6?id=1        如果不传id值默认按1来查询
     */
    @ApiOperation(value = "根据ID查询")
    @GetMapping(value = "/user6")
    //required=false 表示url中可以不传入id参数，此时就使用默认参数1
    public SysUser getUser6(@RequestParam(value="id",required = false,defaultValue = "1") Long id){
        return sysUserService.findById(id);
    }

    /**
     *  查询7
     *  多参数查询，即类似于localhost:8080/hello?id=98&&name=wojiushimogui这样的url，
        请求路径： http://localhost:8080/user7?id=1&name=aaa
     */
    @ApiOperation(value = "测试多个参数")
    @GetMapping(value = "/user7")
    //required=false 表示url中可以不传入id，name参数，此时就使用默认参数1,bbb
    public String getUser7(@RequestParam(value="id",required = false,defaultValue = "1") Long id,
                           @RequestParam(value="name",required = false,defaultValue = "bbb") String name) {
        return "id:"+id+ " name:"+name;
    }

    /**
     * 增加用户
     * 特意试了下肖姐的写法，没有问题
     * @RequestBody注解 是将json串注入到类中, 常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
     * 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
     * @ApiOperation："用在请求的方法上，说明方法的作用"
        value="说明方法的作用"
        notes="方法的备注说明"
     * @ApiImplicitParam：用在 @ApiImplicitParams 注解中，指定一个请求参数的配置信息
        name：参数名
        value：参数的汉字说明、解释
        required：参数是否必须传
        dataType：参数类型，默认String，其它值dataType="Integer"
        defaultValue：参数的默认值
     *
     * 请求路径：http://localhost:8080/addUser   后面跟的是json格式参数  使用Postman软件发送请求
     * 参数：
     *  {
            "usercode":"lijian",
            "username":"lijian",
            "password":"123456",
            "salt":"aaa",
            "locked":"1",
            "id":"2"
        }
     * 表字段在本地mysql dm 库下 表名：sys_user
     */
    @ApiOperation(value="新增用户", notes="新增用户")
    @ApiImplicitParam(name = "user", value = "新增用户参数",
            required = true, dataType = "SysUser")
    @PostMapping(value = "/addUser")
    public int addUser(@RequestBody SysUser user) {
        return sysUserService.addUser(user);
    }

    /**
     *  修改操作
     *  请求路径：http://localhost:8080/update   后面跟的是json格式参数  使用Postman软件发送请求
     * 参数：
     *  {
             "usercode":"lijian2",
             "username":"lijian2",
             "password":"1234562",
             "salt":"aaa2",
             "locked":"2",
              "id":"2"
         }
     */
    @ApiOperation(value="修改用户", notes="修改用户")
    @ApiImplicitParam(name = "user", value = "修改用户参数, 更改所有",
            required = true, dataType = "SysUser")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody SysUser user) {
        return sysUserService.update(user);
    }


}
