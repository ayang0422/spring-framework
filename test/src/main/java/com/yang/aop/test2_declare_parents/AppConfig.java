package com.yang.aop.test2_declare_parents;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年07月05日
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {

}
