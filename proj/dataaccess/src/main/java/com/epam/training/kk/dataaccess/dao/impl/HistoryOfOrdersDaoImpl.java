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

import com.epam.training.kk.dataaccess.dao.HistoryOfOrdersDao;
import com.epam.training.kk.dataaccess.dao.mapper.OrderMapper;
import com.epam.training.kk.dataaccess.model.Order;

@Repository
public class HistoryOfOrdersDaoImpl implements HistoryOfOrdersDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Order getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"HistoryOfOrders\" where id = ?", new Object[] { id },
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
								"INSERT INTO \"HistoryOfOrders\" (client_id, car_id, address, time, distance, price, isCompleted"
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


}
