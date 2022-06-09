package com.yang.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年06月08日
 */
@Component
public class MyBeanClassLoaderAware implements BeanClassLoaderAware {
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("MyBeanClassLoaderAware's class loader is : " + classLoader);
	}
}
