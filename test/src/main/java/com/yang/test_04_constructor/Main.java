package com.yang.test_04_constructor;

import com.yang.test_03_replaced.TestChangeMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月29日
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"constructorTest.xml");
		HelloBean test = (HelloBean)context.getBean("hello");
		System.out.println(test.getSex().getSex());
		System.out.println(test.getName());
	}
}
