package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.User;

public class UserMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String login = rs.getString("login");
		String password = rs.getString("password");
		User user = new User(login, password);
		user.setId(id);
		return user;
	}
}

