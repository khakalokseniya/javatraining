package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.Car;

public final class CarMapper implements RowMapper<Car> {
	
	@Override
	public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String registrationNumber = rs.getString("registration_number");
		String brand = rs.getString("brand");
		String model = rs.getString("model");
		String color = rs.getString("color");
		int year = rs.getInt("year");
		String callsign = rs.getString("callsign");
		long driverId = rs.getLong("driver_id");
		boolean activity = rs.getBoolean("activity");
		Car car = new Car(registrationNumber, brand, model);
		car.setColor(color);
		car.setYear(year);
		car.setCallsign(callsign);
		car.setDriverId(driverId);
		car.setActivity(activity);
		car.setId(id);
		return car;
	}
}

