package com.yang.test_02_lookup;

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
				"lookupTest.xml");
		AbstractGetBeanTest test = (AbstractGetBeanTest)context.getBean("getBeanTest");
		test.showMe();
	}

}
