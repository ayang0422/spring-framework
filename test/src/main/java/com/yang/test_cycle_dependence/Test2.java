package com.yang.test_cycle_dependence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
public class Test2 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test_cycle_dependence.xml");
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}

	}
}
