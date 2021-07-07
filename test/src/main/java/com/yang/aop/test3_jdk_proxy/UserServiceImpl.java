package com.yang.aop.test3_jdk_proxy;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月07日
 */
public class UserServiceImpl implements UserService {
	@Override
	public void add() {
		System.out.println("---------------add---------");
	}
}
