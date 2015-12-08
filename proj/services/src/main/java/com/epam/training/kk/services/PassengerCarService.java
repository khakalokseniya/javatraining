package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.PassengerCar;


public interface PassengerCarService {
	
	PassengerCar get(Long id);
	 
	void insertOrUpdate(PassengerCar car);
	
	void delete(Long id);

}
