package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Driver;

public interface DriverDao {
	Driver getById(Long id);

	void insert(Driver driver);

	void update(Driver driver);
	
	void delete(Long id);
	
}
