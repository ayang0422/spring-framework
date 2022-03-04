package com.yang.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年10月08日
 */
@Service
public class UserServiceImpl implements UserService{

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(User user) {
		int update = jdbcTemplate.update("insert into `user`(`name`,`age`,`sex`) values (?,?,?)",
				user.getName(), user.getAge(), user.getSex());
		return update;
	}

	@Override
	public List<User> getUsers() {
		List<User> list = jdbcTemplate.query("select * from `user`", new UserRowMapper());
		return list;
	}
}
