package com.yang.aop.test2_declare_parents;

import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月05日
 */
@Component
public class Woman implements Person{

	@Override
	public void likePerson() {
		System.out.println("i like beauitiful girl");
	}
}
