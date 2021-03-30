package com.yang.test_02_lookup;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年03月29日
 */
public abstract class AbstractGetBeanTest {

	public void showMe(){
		getBean().showMe();
	}

	public abstract User getBean();
}
