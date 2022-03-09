package com.yang.test_event;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangpeng`
 * @version 1.0.0
 * @date 2021年03月24日
 */
public class Test {

	public static void main(String[] args) {
//		long t1 = System.currentTimeMillis();
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		long t2 = System.currentTimeMillis();
//		System.out.println("init IoC container with " + (t2 - t1) + "ms");

		main2();
	}

	public static void main2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test_event.xml");
		TestEvent testEvent = new TestEvent("hello", "this is msg");
		context.publishEvent(testEvent);
		context.close();
	}

}
