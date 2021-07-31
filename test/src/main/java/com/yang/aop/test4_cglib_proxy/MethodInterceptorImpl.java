package com.yang.aop.test4_cglib_proxy;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月27日
 */
public class MethodInterceptorImpl implements MethodInterceptor {


	int index = 0;


	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println(++index);
		System.err.println("before " + method.getName());
		Object invoke = method.invoke(o, objects);
		System.err.println("after " + method.getName());
		return invoke;
	}
}
