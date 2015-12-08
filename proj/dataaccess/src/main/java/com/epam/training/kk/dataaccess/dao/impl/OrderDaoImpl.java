package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.dao.mapper.OrderMapper;
import com.epam.training.kk.dataaccess.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Order getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Order\" where id = ?", new Object[] { id },
				new OrderMapper());
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
				ps.setInt(1, order.getClientId());
				ps.setInt(2, order.getCarId());
				ps.setString(3, order.getAddress());
				ps.setString(4, order.getTime());
				ps.setFloat(5, order.getDistance());
				ps.setInt(6, order.getPrice());
				ps.setBoolean(7, order.isCompleted());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(Long id, int clientId, int carId, String address, String time,
			float distance, int price, boolean isCompleted) {
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

}
