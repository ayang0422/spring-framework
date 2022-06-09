package com.yang.aop.test1;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月10日
 */
public class SysTest {
	public static void main(String[] args) {
		String property = System.getProperty("org.graalvm.nativeimage.imagecode");
		System.out.println(property);
	}

}
