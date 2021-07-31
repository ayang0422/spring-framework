package com.yang.aop.test3_jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月07日
 */
public class MyInvocationHandler implements InvocationHandler {

	private Object targetObject;

	public MyInvocationHandler(Object targetObject) {
		super();
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-----------before-----------");

		Object invoke = null;
		try {
			invoke = method.invoke(proxy, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-----------after-----------");
		return invoke;
	}

	public Object getProxy(){
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				targetObject.getClass().getInterfaces(),this);
	}
}
