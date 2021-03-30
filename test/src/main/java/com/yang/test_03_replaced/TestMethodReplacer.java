package com.yang.test_03_replaced;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月29日
 */
public class TestMethodReplacer implements MethodReplacer {
	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("i replaced old method");
		return null;
	}
}
