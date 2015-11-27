package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Car;


public interface CarDao {
	Car getById(Long id);

	void insert(Car car);

	void update(Car car);
	
	void delete(Long id);
	
	
}

