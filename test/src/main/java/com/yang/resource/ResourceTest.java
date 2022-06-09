package com.yang.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年06月07日
 */
public class ResourceTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("test.xml");
		try (InputStream is = resource.getInputStream()) {
			System.out.println(resource.isOpen());
			int size = 8;
			int cur = 0;
			byte[] buffer = new byte[size];
			while ((cur = is.read(buffer)) != -1) {
				System.out.println(new String(buffer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
