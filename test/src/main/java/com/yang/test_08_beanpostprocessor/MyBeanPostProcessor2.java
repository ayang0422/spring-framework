package com.yang.test_08_beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月23日
 */
public class MyBeanPostProcessor2 implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof A) {
			System.out.println("before2");
			System.out.println("beanName:" + beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof A)
			System.out.println("after2");
		return bean;
	}


}
