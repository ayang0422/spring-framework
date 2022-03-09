package com.yang.test_cycle_dependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月02日
 */
@Component
public class B {

	@Autowired
	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
