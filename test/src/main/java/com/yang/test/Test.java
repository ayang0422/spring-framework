package com.yang.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangpeng`
 * @version 1.0.0
 * @date 2021年03月24日
 */
public class Test {

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
//		A bean = context.getBean(A.class);
//		System.out.println("a的B属性是:" + bean.getB());


		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		A bean = applicationContext.getBean(A.class);
		System.out.println("a的B属性是:" + bean.getB());
	}
}
