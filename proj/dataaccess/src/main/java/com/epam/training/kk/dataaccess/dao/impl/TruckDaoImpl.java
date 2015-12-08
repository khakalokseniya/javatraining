package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.TruckDao;
import com.epam.training.kk.dataaccess.dao.mapper.TruckMapper;
import com.epam.training.kk.dataaccess.model.Truck;

@Repository
public class TruckDaoImpl implements TruckDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Truck getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Trucks\" where id = ?", new Object[] { 1 },
				new TruckMapper());
	}

	@Override
	public Long insert(final Truck truck) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement(
								"INSERT INTO \"Trucks\" (brand, model, registration_number"
										+ "color, year, callsign, driver_id, activity VALUES (?,?,?,?,?,?,?,?)",
								new String[] { "id" });
				ps.setString(1, truck.getBrand());
				ps.setString(2, truck.getModel());
				ps.setString(3, truck.getRegistrationNumber());
				ps.setString(4, truck.getColor());
				ps.setInt(5, truck.getYear());
				ps.setString(6, truck.getCallsign());
				ps.setLong(7, truck.getDriverId());
				ps.setBoolean(8, truck.getActivity());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(Truck truck, String brand, String model,
			String registrationNumber, String color, Date year,
			String callsign, Long driverId, Boolean activity) {
		String sqlUpdate = "UPDATE \"Car\" set brand=?, model=?, registrationNumber=?, color=?,"
				+ "year=?, callsign=?, driverId=?, activity=? where id=?";
		jdbcTemplate.update(sqlUpdate, brand, model, registrationNumber, color,
				year, callsign, driverId, activity, truck.getId());
		return;

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Truck\" where id = ?", id);
	}

}