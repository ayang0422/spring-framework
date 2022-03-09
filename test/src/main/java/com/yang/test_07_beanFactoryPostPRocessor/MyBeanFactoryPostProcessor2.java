package com.yang.test_07_beanFactoryPostPRocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月24日
 */
public class MyBeanFactoryPostProcessor2 implements BeanFactoryPostProcessor, Ordered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("process MyBeanFactoryPostProcessor2");
	}


	@Override
	public int getOrder() {
		return 1;
	}
}
