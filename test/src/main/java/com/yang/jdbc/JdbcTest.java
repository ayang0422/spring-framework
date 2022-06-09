package com.yang.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年10月08日
 */
public class JdbcTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"jdbc/spring.xml");
		Object dataSource = context.getBean("dataSource");

		UserService bean = context.getBean(UserService.class);
		User user = new User(1,"zs",12,"man");
		int insert = bean.insert(user);
		System.out.println("insert rs : " + insert);

		List<User> users = bean.getUsers();
		for (User u : users) {
			System.out.println(u);
		}
	}
}
