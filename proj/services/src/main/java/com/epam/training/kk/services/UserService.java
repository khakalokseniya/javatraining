package com.epam.training.kk.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.epam.training.kk.dataaccess.model.User;

public interface UserService {
	
	User getById(Long id);

	Long insert(User user);

	void delete(Long id);
	
	boolean authenticate(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
