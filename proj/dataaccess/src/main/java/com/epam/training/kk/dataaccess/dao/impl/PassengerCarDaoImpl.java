package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.PassengerCarDao;
import com.epam.training.kk.dataaccess.dao.mapper.PassengerCarMapper;
import com.epam.training.kk.dataaccess.model.PassengerCar;
@Repository
public class PassengerCarDaoImpl implements PassengerCarDao {
	public static long ID_GEN;

	private static Map<Long, PassengerCar> TABLE_PASSENGER_CARS = new HashMap<Long, PassengerCar>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PassengerCar getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Passenger_Cars\" where id = ?",
				new Object[] { 1 }, new PassengerCarMapper());
	}

	@Override
	public void insert(PassengerCar car) {
		jdbcTemplate
				.update("INSERT INTO \"Passenger_Cars\" (brand, model, registration_number"
						+ "color, year, callsign, driver, activity VALUES (?,?,?,?,?,?,?,?)",
						car.getBrand(), car.getModel(),
						car.getRegistrationNumber(), car.getColor(),
						car.getYear(), car.getCallsign(), car.getDriver(),
						car.getActivity());
	}

	@Override
	public void update(PassengerCar car) {
		PassengerCar existingCar = TABLE_PASSENGER_CARS.get(car.getId());
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
		jdbcTemplate.update("delete from \"Passenger_Cars\" where id = ?", id);
	}
}
