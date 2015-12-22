package com.epam.training.kk.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.UserDao;
import com.epam.training.kk.services.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean authenticate(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String encodePassword = md5(password);
		return userDao.authenticate(login, encodePassword);
	}
	
	
	 private String md5(String md5) {
	        try {
	            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	            byte[] array = md.digest(md5.getBytes());
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < array.length; ++i) {
	                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	            }
	            return sb.toString();
	        } catch (java.security.NoSuchAlgorithmException e) {
	        }
	        return null;
	    }
}
