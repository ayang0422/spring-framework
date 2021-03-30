package com.yang.test_05_properties;

import com.yang.test_04_constructor.HelloBean;
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
				"propertiesTest.xml");
		TestBean test = (TestBean) context.getBean("testBean");
		System.out.println(test.getProperties1());
		System.out.println(test.getProperties2());
		test.getProperties3().forEach(System.out::println);
		test.getProperties4().forEach((k, v) -> {
			System.out.println("key:" + k + ",value:" + v);
		});
	}
}
