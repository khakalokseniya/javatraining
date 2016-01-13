package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Order;

public final class OrderMapper implements RowMapper<Order> {
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String clientPhone = rs.getString("client_phone");
		Long carId = rs.getLong("car_id");
		String street = rs.getString("street");
		String house = rs.getString("house");
		String corps = rs.getString("corps");
		String apartment = rs.getString("apartment");
		Address address = new Address(street, house, corps, apartment);
		String time = rs.getString("time");
		double distance = rs.getDouble("distance");
		double price = rs.getDouble("price");
		boolean isCompleted = rs.getBoolean("is_completed");
		String date = rs.getString("date");
		Order order = new Order(clientPhone, carId, address, time, date);
		order.setId(id);
		order.setDistance(distance);
		order.setPrice(price);
		order.setCompleted(isCompleted);
		return order;
	}
}
