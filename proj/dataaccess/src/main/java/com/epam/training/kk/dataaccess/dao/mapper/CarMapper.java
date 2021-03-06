package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Car.Type;

public final class CarMapper implements RowMapper<Car> {
	
	@Override
	public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String registrationNumber = rs.getString("registration_number");
		String brand = rs.getString("brand");
		String model = rs.getString("model");
		Type type =  Type.valueOf(rs.getString("type"));
		String color = rs.getString("color");
		int year = rs.getInt("year");
		String callsign = rs.getString("callsign");
		long driverId = rs.getLong("driver_id");
		boolean activity = rs.getBoolean("activity");
		double distance = rs.getDouble("distance");
		Car car = new Car(registrationNumber, brand, model, type, color, year, callsign, driverId);
		car.setId(id);
		car.setActivity(activity);
		car.setDistance(distance);
		return car;
	}
}

