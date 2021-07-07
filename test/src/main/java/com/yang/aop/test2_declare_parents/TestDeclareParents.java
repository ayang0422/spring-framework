package com.yang.aop.test2_declare_parents;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月05日
 */
public class TestDeclareParents {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Person person = (Person)context.getBean("woman");
		System.out.println(person);
		person.likePerson();
		Man lsp = (Man) person;
		lsp.say();
	}
}
