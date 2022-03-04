package com.yang.jdbc;

import java.util.List;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年10月08日
 */
public interface UserService {

	int insert(User user);

	List<User> getUsers();

}
