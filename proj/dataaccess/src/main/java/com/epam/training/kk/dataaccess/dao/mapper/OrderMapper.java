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
		Long id = rs.getLong("Order_id");
		Client client = (Client) rs.getObject("client");
		Car car = (Car) rs.getObject("car");
		String address = rs.getString("address");
		int distance = rs.getInt("distance");
		int price = rs.getInt("price");
		boolean isCompleted = rs.getBoolean("is_completed");
		Time timeOfDeparture = rs.getTime("time_of_departure");
		Order order = new Order(client, car, address, timeOfDeparture);
		order.setId(id);
		order.setDistance(distance);
		order.setPrice(price);
		order.setCompleted(isCompleted);
		return order;
	}
}
