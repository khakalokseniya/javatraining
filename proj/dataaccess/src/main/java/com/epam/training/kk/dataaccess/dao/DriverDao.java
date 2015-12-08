package com.epam.training.kk.dataaccess.dao;


import com.epam.training.kk.dataaccess.model.Driver;

public interface DriverDao {
	Driver getById(Long id);

	Long insert(Driver driver);

	void delete(Long id);


	void update(String fullName, String phoneNumber, String address,
			java.util.Date startingDate, String certificate, Long id);
	
}
