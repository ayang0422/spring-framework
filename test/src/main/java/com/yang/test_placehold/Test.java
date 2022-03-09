package com.yang.test_placehold;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月23日
 */
public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"placehold.xml");
		System.out.println(context.getBean(PlaceholderTest.class).getName());
	}
}
