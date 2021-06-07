package com.yang.test_06_customEle;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年04月02日
 */
public class UserBeanDefinitionParser extends AbstractBeanDefinitionParser {

	public Class getBeanClass() {
		return User.class;
	}

	@Override
	protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(getBeanClass());
		String name = element.getAttribute("name");
		String email = element.getAttribute("email");
		if (StringUtils.hasText(name)) {
			builder.addPropertyValue("name", name);
		}
		if (StringUtils.hasText(email)) {
			builder.addPropertyValue("email", email);
		}
		return builder.getBeanDefinition();
	}
}
