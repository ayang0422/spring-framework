package com.yang.aop.test1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月28日
 */
@Aspect
public class AspectJTest {


	@Pointcut("execution(* com.yang.aop.test1.TestAOP.*(..))")
	public void test() {

	}

	@Before("test()")
	public void before() {
		System.out.println("aspect test before....");
	}


	@After("test()")
	public void after() {
		System.out.println("aspect test after....");
	}

	@Around("test()")
	public Object around(ProceedingJoinPoint point) {
		System.out.println("around before");
		Object proceed = null;
		try {
			proceed = point.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println("around after");
		return proceed;
	}

}
