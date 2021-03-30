package com.yang.test_04_constructor;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月30日
 */
public class Sex {

	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Sex(String sex) {
		this.sex = sex;
	}
}
