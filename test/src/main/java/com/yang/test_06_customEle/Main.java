package com.yang.test_06_customEle;

import com.yang.test_05_properties.TestBean;
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
				"testCustomTag.xml");
		User test = (User) context.getBean("testBean");
		System.out.println(test.getName());
		System.out.println(test.getEmail());
	}
}
