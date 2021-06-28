package com.yang.aop.test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月28日
 */
public class TestAopMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop/test1.xml");
		TestAOP bean = context.getBean(TestAOP.class);
		bean.test();
	}
}
