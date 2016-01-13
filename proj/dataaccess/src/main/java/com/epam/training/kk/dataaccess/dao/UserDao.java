package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.User;

public interface UserDao {

	boolean authenticate(String login, String password);

	User getById(Long id);

	Long insert(User user);

	void delete(Long id);

}
