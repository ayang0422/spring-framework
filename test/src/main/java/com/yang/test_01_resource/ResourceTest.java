package com.yang.test_01_resource;


import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月24日
 */
public class ResourceTest {

	public static void main(String[] args) throws IOException {
		ClassPathResource resource = new ClassPathResource("testBeanFactory.xml");
		InputStream inputStream = resource.getInputStream();
		byte[] bytes = new byte[1024];
		int read = 0;
		while (read != -1){
			read = inputStream.read(bytes);
			System.out.println(new String(bytes));
		}
	}
}
