package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.UserDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.dao.mapper.UserMapper;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.User;
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean authenticate(String login, String password) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) from \"User\" where login=? and password=?", Integer.class, new String[]{login, password});
		return count>0;
	}

	@Override
	public User getById(Long id) {
		User user = new User(null, null);
		try {
			return jdbcTemplate.queryForObject("select * from \"User\" where id = ?", new Object[] { id }, new UserMapper());
		} catch (DataAccessException e) {
			user = null;
		}
		return user;
	}

	@Override
	public Long insert(final User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO \"User\" (login, password) VALUES (?,?)", new String[] { "id" });
				ps.setString(1, user.getLogin());
				ps.setString(2, user.getPassword());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"User\" where id = ?", id);
		
	}
	
	

}
