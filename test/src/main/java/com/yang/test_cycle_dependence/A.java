package com.yang.test_cycle_dependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月02日
 */
@Component
public class A {

	@Autowired
	private B b;

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@PostConstruct
	public void init(){
		System.out.println("aaaaa");
	}
}
