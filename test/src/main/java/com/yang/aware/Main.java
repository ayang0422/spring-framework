package com.yang.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年06月08日
 */
@ComponentScan
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		MyBeanNameAware bean = context.getBean(MyBeanNameAware.class);
		System.out.println(bean);

		context.close();
	}
}
