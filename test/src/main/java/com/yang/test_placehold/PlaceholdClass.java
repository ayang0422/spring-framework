package com.yang.test_placehold;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月23日
 */
public class PlaceholdClass implements BeanPostProcessor {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("============");
		return null;
	}
}
