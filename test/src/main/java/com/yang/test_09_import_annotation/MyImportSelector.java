package com.yang.test_09_import_annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月05日
 */
public class MyImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.yang.test_cycle_dependence.A","com.yang.test_cycle_dependence.B"};
	}
}
