package com.yang.test_09_import_annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 *
 * 测试@import注解和ImportSelector
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
@ComponentScan
@Import({User.class,Person.class,MyImportSelector.class})
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
}
