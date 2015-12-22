package com.epam.training.kk.dataaccess.dao;

public interface UserDao {
	
	boolean authenticate(String login, String password);

}
