package com.yang.aware;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年06月09日
 */
@Component
public class MyDisposableBean implements DisposableBean {
	@Override
	public void destroy() throws Exception {
		System.out.println("I Distorted");
	}
}
