package com.epam.training.kk.dataaccess.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.UserDao;
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean authenticate(String login, String password) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) from \"User\" where login=? and password=?", Integer.class, new String[]{login, password});
		return count>0;
	}
	
	

}
