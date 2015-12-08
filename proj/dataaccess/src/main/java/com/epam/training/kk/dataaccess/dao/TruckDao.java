package com.epam.training.kk.dataaccess.dao;

import java.sql.Date;

import com.epam.training.kk.dataaccess.model.Truck;

public interface TruckDao{
	Truck getById(Long id);

	Long insert(final Truck truck);

	void delete(Long id);

	void update(Truck truck, String brand, String model,
			String registrationNumber, String color, Date year,
			String callsign, Long driverId, Boolean activity);

}
