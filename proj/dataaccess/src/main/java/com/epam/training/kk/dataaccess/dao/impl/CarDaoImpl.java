package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.CarDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.model.Car;

@Repository
public class CarDaoImpl implements CarDao {

	public static long ID_GEN;

	private static Map<Long, Car> TABLE_CAR = new HashMap<Long, Car>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Car getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Car\" where id = ?", new Object[] { 1 },
				new CarMapper());
	}

	@Override
	public void insert(Car car) {
		jdbcTemplate
				.update("INSERT INTO \"Car\" (brand, model, registration_number"
						+ "color, year, callsign, driver, activity VALUES (?,?,?,?,?,?,?,?)",
						car.getBrand(), car.getModel(),
						car.getRegistrationNumber(), car.getColor(),
						car.getYear(), car.getCallsign(), car.getDriver(),
						car.getActivity());
	}

	@Override
	public void update(Car car) {
		Car existingCar = TABLE_CAR.get(car.getId());
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
		jdbcTemplate.update("delete from \"Car\" where id = ?", id);
	}
}
