//package com.yang.test_cycle_dependence;
//
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.stereotype.Component;
//
///**
// * @author yangpeng
// * @version 1.0.0
// * @date 2021年06月22日
// */
//@Component
//public class C implements FactoryBean {
//	@Override
//	public Object getObject() throws Exception {
//		return new C();
//	}
//
//	@Override
//	public Class<?> getObjectType() {
//		return C.class;
//	}
//
//	@Override
//	public boolean isSingleton() {
//		return FactoryBean.super.isSingleton();
//	}
//}
