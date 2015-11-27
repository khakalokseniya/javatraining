package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.DriverDao;
import com.epam.training.kk.dataaccess.dao.mapper.DriverMapper;
import com.epam.training.kk.dataaccess.model.Driver;

@Repository
public class DriverDaoImpl implements DriverDao {

	public static long ID_GEN;

	private static Map<Long, Driver> TABLE_DRIVER = new HashMap<Long, Driver>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Driver getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Driver\" where id = ?", new Object[] { 1 },
				new DriverMapper());
	}

	@Override
	public void insert(Driver driver) {
		jdbcTemplate.update(
				"INSERT INTO \"Driver\" (full_name, phone_number, address, starting_date,"
						+ "certificate) VALUES (?,?,?,?,?)",
				driver.getFullName(), driver.getPhoneNumber(),
				driver.getAddress(), driver.getStartingDate(),
				driver.getCertificate());
	}

	@Override
	public void update(Driver driver) {
		Driver existingDriver = TABLE_DRIVER.get(driver.getId());
		existingDriver.setFullName(driver.getFullName());
		existingDriver.setAddress(driver.getAddress());
		existingDriver.setPhoneNumber(driver.getPhoneNumber());
		existingDriver.setCertificate(driver.getCertificate());
		existingDriver.setStartingDate(driver.getStartingDate());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Driver\" where id = ?", id);
	}

}
