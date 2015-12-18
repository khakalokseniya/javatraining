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

import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.dao.mapper.OrderMapper;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Order getById(Long id) {
		Order order = new Order(0l, 0l, null, null);
		try {
			return jdbcTemplate.queryForObject(
					"select * from \"Order\" where id = ?",
					new Object[] { id }, new OrderMapper());
		} catch (DataAccessException e) {
			order = null;
		}
		return order;
	}

	@Override
	public Order getFromHistory(Long id) {
		Order order = new Order(0l, 0l, null, null);
		try {
			return jdbcTemplate.queryForObject(
					"select * from \"HistoryOfOrders\" where id = ?",
					new Object[] { id }, new OrderMapper());
		} catch (DataAccessException e) {
			order = null;
		}
		return order;
	}

	@Override
	public Long insert(final Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement(
								"INSERT INTO \"Order\" (client_id, car_id, address, time, distance, price, is_completed"
										+ ") VALUES (?,?,?,?,?,?,?)",
								new String[] { "id" });
				ps.setLong(1, order.getClientId());
				ps.setLong(2, order.getCarId());
				ps.setString(3, order.getAddress());
				ps.setString(4, order.getTime());
				ps.setDouble(5, order.getDistance());
				ps.setInt(6, order.getPrice());
				ps.setBoolean(7, order.isCompleted());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public Long addToHistory(final Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement(
								"INSERT INTO \"HistoryOfOrders\" (client_id, car_id, address, time, distance, price, is_completed"
										+ ") VALUES (?,?,?,?,?,?,?)",
								new String[] { "id" });
				ps.setLong(1, order.getClientId());
				ps.setLong(2, order.getCarId());
				ps.setString(3, order.getAddress());
				ps.setString(4, order.getTime());
				ps.setDouble(5, order.getDistance());
				ps.setInt(6, order.getPrice());
				ps.setBoolean(7, order.isCompleted());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(Long id, Long clientId, Long carId, String address,
			String time, double distance, int price, boolean isCompleted) {
		String sqlUpdate = "UPDATE \"Order\" set client_id=?, car_id=?, address=?, time=?,"
				+ "distance=?, price=?, is_completed=? where id=?";
		jdbcTemplate.update(sqlUpdate, clientId, carId, address, time,
				distance, price, isCompleted, id);
		return;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Order\" where id = ?", id);
	}
	
	@Override
	public List<Order> getAll(long first, long count) {
		return jdbcTemplate.query(String.format(
				"select * from \"Order\" order by  id limit %s offset %s ", count,
				first), new OrderMapper());
	}
	
	@Override
	public List<Order> getAllFromHistory(long first, long count) {
		return jdbcTemplate.query(String.format(
				"select * from \"HistoryOfOrders\" order by  id limit %s offset %s ", count,
				first), new OrderMapper());
	}
	
	
	@Override
	public Integer getCount() {
		return jdbcTemplate.queryForObject("select count(1) from \"Order\" ",
				Integer.class);
	}

}
