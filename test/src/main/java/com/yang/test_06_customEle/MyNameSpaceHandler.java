package com.yang.test_06_customEle;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年04月02日
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
	}
}
