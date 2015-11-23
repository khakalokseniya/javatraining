package dataaccess.dao;

import dataaccess.model.Driver;

public interface DriverDao {
	Driver getById(Long id);

	void insert(Driver driver);

	void update(Driver driver);
	
	void delete(Driver driver);
	
	void select(Driver driver);
}
