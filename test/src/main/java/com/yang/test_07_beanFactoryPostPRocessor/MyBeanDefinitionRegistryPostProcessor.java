package com.yang.test_07_beanFactoryPostPRocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("----MyBeanDefinitionRegistryPostProcesso----postProcessBeanFactory----");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("----MyBeanDefinitionRegistryPostProcesso----postProcessBeanDefinitionRegistry----");
	}
}
