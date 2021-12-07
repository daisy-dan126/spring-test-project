package com.shenque;

import com.shenque.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestProjectApplicationTests {

	//注入注解，没有Autowired注解，无法完成注入
	@Autowired
	Person person;

	@Test
	public void contextLoads() {
		System.out.println(person);
	}

}
