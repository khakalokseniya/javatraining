package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Car;

public interface CarService {
	
	Car get(Long id);
	
	 void location(Car car);
	 
	 void insertOrUpdate(Car car);
	 
	 void radius(Car car);

}
