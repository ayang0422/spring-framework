package com.yang.test_cycle_dependence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangpeng`
 * @version 1.0.0
 * @date 2021年03月24日
 */
@ComponentScan
public class Test {

	public static void main(String[] args) {
//		long t1 = System.currentTimeMillis();
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Test.class);
//		long t2 = System.currentTimeMillis();
//		System.out.println("init IoC container with " + (t2 - t1) + "ms");
		A bean = applicationContext.getBean(A.class);
		System.out.println("a的B属性是:" + bean.getB());
	}

	public static void  main2(){
//		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource(""));
//		xmlBeanFactory.
	}
}
