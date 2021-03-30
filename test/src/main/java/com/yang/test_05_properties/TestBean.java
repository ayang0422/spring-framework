package com.yang.test_05_properties;

import java.util.List;
import java.util.Map;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月30日
 */
public class TestBean {

	private String properties1;
	private Integer properties2;
	private List<String> properties3;
	private Map<String,String> properties4;

	public String getProperties1() {
		return properties1;
	}

	public void setProperties1(String properties1) {
		this.properties1 = properties1;
	}

	public Integer getProperties2() {
		return properties2;
	}

	public void setProperties2(Integer properties2) {
		this.properties2 = properties2;
	}

	public List<String> getProperties3() {
		return properties3;
	}

	public void setProperties3(List<String> properties3) {
		this.properties3 = properties3;
	}

	public Map<String, String> getProperties4() {
		return properties4;
	}

	public void setProperties4(Map<String, String> properties4) {
		this.properties4 = properties4;
	}
}
