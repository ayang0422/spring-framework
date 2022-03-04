package com.yang.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年10月08日
 */
public class UserRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User(rs.getInt("id"),rs.getString("name"),
				rs.getInt("age"),rs.getString("sex"));
		return user;
	}
}
