package com.yang.test_event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月24日
 */
public class TestListener implements ApplicationListener {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof TestEvent){
			TestEvent testEvent = (TestEvent)event;
			System.out.print("i listened---->");
			testEvent.print();
		}
	}
}
