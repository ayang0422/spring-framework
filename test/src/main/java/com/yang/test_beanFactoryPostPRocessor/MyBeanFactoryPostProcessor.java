package com.yang.test_beanFactoryPostPRocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月24日
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
		System.out.println("bean definition count is : " + beanDefinitionCount);
		System.out.println("process MyBeanFactoryPostProcessor");
	}


	@Override
	public int getOrder() {
		return 2;
	}
}
