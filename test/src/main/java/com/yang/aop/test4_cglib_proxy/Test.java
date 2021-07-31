package com.yang.aop.test4_cglib_proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月27日
 */
public class Test {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(EnhancerDemo.class);
		enhancer.setCallback(new MethodInterceptorImpl());
		EnhancerDemo demo = (EnhancerDemo) enhancer.create();
		demo.test();
		System.out.println("over");
	}
}
