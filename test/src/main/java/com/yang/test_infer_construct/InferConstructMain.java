package com.yang.test_infer_construct;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
@ComponentScan
public class InferConstructMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InferConstructMain.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}

	}
}
