package com.yang.test_08_beanpostprocessor;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangpeng`
 * @version 1.0.0
 * @date 2021年03月24日
 */
public class TestBeanPostProcessor {

	public static void main(String[] args) {
//		long t1 = System.currentTimeMillis();
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		long t2 = System.currentTimeMillis();
//		System.out.println("init IoC container with " + (t2 - t1) + "ms");

		main2();
	}

	public static void main2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanpostprocessor.xml");
		MyBeanPostProcessor2 bean = context.getBean(MyBeanPostProcessor2.class);
		System.out.println(context);
		System.out.println(bean);
	}

}
