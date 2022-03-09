package com.yang.test_infer_construct;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
@ComponentScan
public class AConstruct {

	private String name;

	public AConstruct() {
	}

	public AConstruct(String name) {
		this.name = name;
	}
}
