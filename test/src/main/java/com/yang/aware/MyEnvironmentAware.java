package com.yang.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年06月08日
 */
@Component
public class MyEnvironmentAware implements EnvironmentAware {

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("I got environment : " + environment);
	}
}
