package com.yang.test_04_constructor;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月30日
 */
public class HelloBean {

	private String name;

	private Sex sex;

	public HelloBean(String name, Sex sex) {
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
