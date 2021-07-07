package com.yang.test.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月03日
 */
public class TestAnnotationUtil {

	public static void main(String[] args) throws ClassNotFoundException {
		testFindAnnotation();
	}

	public static void testFindAnnotation() throws ClassNotFoundException {
		Aspect annotation = AnnotationUtils.findAnnotation(Class.forName("com.yang.aop.test1.AspectJTest"), Aspect.class);
		assert annotation != null;
		System.out.println(annotation);


	}

}
