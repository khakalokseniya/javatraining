package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.CarDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.model.Car;

@Repository
public class CarDaoImpl implements CarDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Car getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Car\" where id = ?", new Object[] { id },
				new CarMapper());
	}

	@Override
	public Long insert(final Car car) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
								"INSERT INTO \"Car\" (registration_number, brand, model,"
										+ "color, year, callsign, driver_id, activity) VALUES (?,?,?,?,?,?,?,?)",
								new String[] { "id" });
				ps.setString(1,  car.getRegistrationNumber());
				ps.setString(2, car.getBrand());
				ps.setString(3, car.getModel());
				ps.setString(4, car.getColor());
				ps.setInt(5, car.getYear());
				ps.setString(6, car.getCallsign());
				ps.setLong(7, car.getDriverId());
				ps.setBoolean(8, car.getActivity());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
		public void update(String registrationNumber, String brand, String model,  String color, int year,
				String callsign, Long driverId, Boolean activity, Long id) {
			String sqlUpdate = "UPDATE \"Car\" set registration_number=?, brand=?, model=?, color=?,"
									+ "year=?, callsign=?, driver_id=?, activity=? where id=?";
	        jdbcTemplate.update(sqlUpdate, registrationNumber, brand, model, color, 
	        		year,callsign, driverId, activity, id);
	        return;
		
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Car\" where id = ?", id);
	}
}
