package com.yang.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年06月08日
 */
@Component
public class MyBeanNameAware implements BeanNameAware {
	@Override
	public void setBeanName(String name) {
		System.out.println("MyBeanNameAware's bean name is:" + name);
	}
}
