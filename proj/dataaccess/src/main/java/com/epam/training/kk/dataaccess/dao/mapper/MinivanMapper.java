package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.Minivan;

public final class MinivanMapper implements RowMapper<Minivan> {

	@Override
	public Minivan mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("Car_id");
		String registrationNumber = rs.getString("registration_number");
		String brand = rs.getString("brand");
		String model = rs.getString("model");
		String color = rs.getString("color");
		int year = rs.getInt("year");
		String callsign = rs.getString("callsign");
		boolean activity = rs.getBoolean("activity");
		long driverId = rs.getLong("driver_id");
		Minivan minivan = new Minivan(brand, model, registrationNumber);
		minivan.setActivity(activity);
		minivan.setCallsign(callsign);
		minivan.setColor(color);
		minivan.setDriverId(driverId);
		minivan.setId(id);
		minivan.setYear(year);
		return minivan;
	}
}
