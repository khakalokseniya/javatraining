package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.CarDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.dao.mapper.OrderMapper;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.dataaccess.model.Car.Type;

@Repository
public class CarDaoImpl implements CarDao {

	@Autowired
	private JdbcTemplate jdbcTemplate; //

	@Override
	public Car getById(Long id) {
		Car car = new Car(null, null, null, null, null, 0, null, id);
		try {
			return jdbcTemplate.queryForObject("select * from \"Car\" where id = ?", new Object[] { id }, new CarMapper());
		} catch (DataAccessException e) {
			car = null;
		}
		return car;
	}

	@Override
	public Long insert(final Car car) throws NullPointerException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Car\" (registration_number, brand, model, type,"
						+ "color, year, callsign, driver_id, activity, distance) VALUES (?,?,?,?,?,?,?,?,?,?)", new String[] { "id" });
				ps.setString(1, car.getRegistrationNumber());
				ps.setString(2, car.getBrand());
				ps.setString(3, car.getModel());
				ps.setString(4, car.getType().toString());
				ps.setString(5, car.getColor());
				ps.setInt(6, car.getYear());
				ps.setString(7, car.getCallsign());
				ps.setLong(8, car.getDriverId());
				ps.setBoolean(9, car.getActivity());
				ps.setDouble(10, car.getDistance());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(String registrationNumber, String brand, String model, Type type, String color, int year, String callsign, Long driverId, Boolean activity, Long id) {
		String stype = type.name();
		String sqlUpdate = "UPDATE \"Car\" set registration_number=?, brand=?, model=?, type=?, color=?," + "year=?, callsign=?, driver_id=?, activity=? where id=?";
		jdbcTemplate.update(sqlUpdate, registrationNumber, brand, model, stype, color, year, callsign, driverId, activity, id);
		return;

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Car\" where id = ?", id);
	}

	@Override
	public List<Car> getAll() {
		return jdbcTemplate.query(String.format("select * from \"Car\" order by  id"), new CarMapper());
	}

	@Override
	public List<Car> getActiveCars() {
		return jdbcTemplate.query(String.format("select * from \"Car\" where activity='true' order by  id "), new CarMapper());
	}

	@Override
	public Integer getCount() {
		return jdbcTemplate.queryForObject("select count(1) from \"Car\" ", Integer.class);
	}

	@Override
	public List<Car> sort(long first, long count, boolean direction, String column) {
		String cd = direction ? "ASC" : "DESC";
		return jdbcTemplate.query(String.format("select * from \"Car\" " + "LEFT JOIN \"Driver\" ON (\"Car\".driver_id=\"Driver\".id) order by  %s %s  limit %s offset %s", column, cd, count, first),
				new CarMapper());
	}

	@Override
	public Long updateDistance(double distance, Long id) {
		String sqlUpdate = "UPDATE \"Car\" set distance=? where id=?";
		jdbcTemplate.update(sqlUpdate, distance, id);
		return id;
	}
}
