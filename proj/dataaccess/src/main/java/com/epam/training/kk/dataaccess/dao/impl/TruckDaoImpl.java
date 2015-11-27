package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.TruckDao;
import com.epam.training.kk.dataaccess.dao.mapper.TruckMapper;
import com.epam.training.kk.dataaccess.model.Truck;

@Repository
public class TruckDaoImpl implements TruckDao {
	public static long ID_GEN;

	private static Map<Long, Truck> TABLE_TRUCKS = new HashMap<Long, Truck>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Truck getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Trucks\" where id = ?", new Object[] { 1 },
				new TruckMapper());
	}

	@Override
	public void insert(Truck car) {
		jdbcTemplate
				.update("INSERT INTO \"Trucks\" (brand, model, registration_number"
						+ "color, year, callsign, driver, activity VALUES (?,?,?,?,?,?,?,?)",
						car.getBrand(), car.getModel(),
						car.getRegistrationNumber(), car.getColor(),
						car.getYear(), car.getCallsign(), car.getDriver(),
						car.getActivity());
	}

	@Override
	public void update(Truck car) {
		Truck existingCar = TABLE_TRUCKS.get(car.getId());
		existingCar.setBrand(car.getBrand());
		existingCar.setModel(car.getModel());
		existingCar.setRegistrationNumber(car.getRegistrationNumber());
		existingCar.setColor(car.getColor());
		existingCar.setYear(car.getYear());
		existingCar.setCallsign(car.getCallsign());
		existingCar.setDriver(car.getDriver());
		existingCar.setActivity(car.getActivity());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Trucks\" where id = ?", id);
	}
}
