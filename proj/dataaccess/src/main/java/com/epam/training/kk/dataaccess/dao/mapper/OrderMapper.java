package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;

public final class OrderMapper implements RowMapper<Order> {
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		int clientId = rs.getInt("client_id");
		int carId = rs.getInt("car_id");
		String address = rs.getString("address");
		String time = rs.getString("time");
		double distance = rs.getFloat("distance");
		int price = rs.getInt("price");
		boolean isCompleted = rs.getBoolean("is_completed");
		Order order = new Order(clientId, carId, address, time);
		order.setId(id);
		order.setDistance(distance);
		order.setPrice(price);
		order.setCompleted(isCompleted);
		return order;
	}
}
