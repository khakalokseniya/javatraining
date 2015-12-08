package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Car;

public interface CarService {
	
	Car get(Long id);
	
	Long insert(Car car);
	 
	Long update(String registrationNumber, String brand, String model, String color, int year,
			String callsign, Long driverId, boolean activity, Long id);
	
	void delete(Long id);

}
