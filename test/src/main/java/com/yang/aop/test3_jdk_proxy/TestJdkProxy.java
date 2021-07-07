package com.yang.aop.test3_jdk_proxy;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月07日
 */
public class TestJdkProxy {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		MyInvocationHandler handler = new MyInvocationHandler(userService);
		UserService proxy = (UserService) handler.getProxy();
		proxy.add();
	}
}
