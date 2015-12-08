package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Truck;

public interface TruckService {
	
	Truck get(Long id);
	 
	void insertOrUpdate(Truck car);
	
	void delete(Long id);

}
