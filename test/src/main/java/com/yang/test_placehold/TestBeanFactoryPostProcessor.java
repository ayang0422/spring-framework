package com.yang.test_placehold;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月23日
 */
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("###########");
		System.out.println(beanFactory.toString());
		System.out.println("###########");
		System.out.println("\n");
	}
}
