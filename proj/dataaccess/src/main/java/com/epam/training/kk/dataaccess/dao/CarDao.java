package com.epam.training.kk.dataaccess.dao;


import com.epam.training.kk.dataaccess.model.Car;

public interface CarDao {
	Car getById(Long id);

	Long insert(Car car);

	
	void delete(Long id);


	void update(String registrationNumber, String brand, String model,
			String color, int year, String callsign, Long driverId,
			Boolean activity, Long id);
	
	
}

