package com.epam.training.kk.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
	
	boolean authenticate(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
