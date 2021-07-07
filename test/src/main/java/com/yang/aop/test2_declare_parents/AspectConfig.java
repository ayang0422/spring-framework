package com.yang.aop.test2_declare_parents;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月05日
 */
@Aspect
@Component
public class AspectConfig {

	@DeclareParents(value = "com.yang.aop.test2_declare_parents.Person+", defaultImpl = Lsp.class)
	public Man man;
}
