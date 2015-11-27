package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.PassengerCar;


public interface PassengerCarDao {
	PassengerCar getById(Long id);

	void insert(PassengerCar passengerCar);

	void update(PassengerCar passengerCar);
	
	void delete(Long id);

}
