package com.yang.test_beanFactoryPostPRocessor;


import com.yang.test_beanpostprocessor.MyBeanPostProcessor2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 这个是测试 beanFactoryPostProcessor
 * 通过测试可以看出BeanFactoryPostProcessor是执行在bean初始化之前的，
 * 且BeanFactoryPostProcessor设置的order值越小,越先执行
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月24日
 */
public class TestBeanFactoryPostProcessor {

	public static void main(String[] args) {
//		long t1 = System.currentTimeMillis();
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		long t2 = System.currentTimeMillis();
//		System.out.println("init IoC container with " + (t2 - t1) + "ms");

		main2();
	}

	public static void main2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanfatorypostprocessor.xml");
		A bean = context.getBean(A.class);
		System.out.println(context);
		System.out.println(bean);
	}

}
