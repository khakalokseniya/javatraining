package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Truck;

public final class TruckMapper implements RowMapper<Truck> {
	
	@Override
	public Truck mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("Car_id");
		String registrationNumber = rs.getString("registration_number");
		String brand = rs.getString("brand");
		String model = rs.getString("model");
		String color = rs.getString("color");
		int year = rs.getInt("year");
		String callsign = rs.getString("callsign");
		boolean activity = rs.getBoolean("activity");
		long driverId = rs.getLong("driver_id");
		Car car = new Truck(brand, model, registrationNumber);
		car.setActivity(activity);
		car.setCallsign(callsign);
		car.setColor(color);
		car.setDriverId(driverId);
		car.setId(id);
		car.setYear(year);
		return (Truck) car;
	}
}
