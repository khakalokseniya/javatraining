package com.epam.training.kk.services;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Car.Type;

public interface CarService {

	Car get(Long id);

	Long insert(Car car);

	Long update(String registrationNumber, String brand, String model, Type type, String color, int year, String callsign, Long driverId, boolean activity, Long id);

	void delete(Long id); 


	Integer getCount();

	List<Car> getAll();
	
	List<Car> getActiveCars();
	
	public List<Car> sort(long first, long count, boolean direction, String column);
	
	Long updateDistance(double distance, Long id);

}
