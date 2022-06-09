package com.yang.test_10_bean_annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月15日
 */
@ComponentScan
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		BeanAnnotation bean = context.getBean(BeanAnnotation.class);
		System.out.println(bean);
	}

	@Bean
	public BeanAnnotation beanAnnotationGet(){
		return new BeanAnnotation();
	}
}
