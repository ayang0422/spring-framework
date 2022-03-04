package com.yang.test_beanpostprocessor;

import javax.annotation.PostConstruct;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月24日
 */
public class A {

	public A(){
		System.out.println("init a");
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PostConstruct
	public void doInit(){
		System.out.println("a init method ----------");
	}
}
