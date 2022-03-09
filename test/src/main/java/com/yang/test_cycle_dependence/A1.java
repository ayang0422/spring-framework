package com.yang.test_cycle_dependence;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
public class A1 {

	private B1 b1;

	public B1 getB1() {
		return b1;
	}

	public void setB1(B1 b1) {
		this.b1 = b1;
	}
}
