package com.yang.test_event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2022年03月22日
 */
public class MyContextStartedListener implements ApplicationListener {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		if (event instanceof ApplicationEvent){
			System.out.println("MyContextStartedListener--->" + event);
//		}
	}
}
