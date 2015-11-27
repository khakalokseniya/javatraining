package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.dao.mapper.OrderMapper;
import com.epam.training.kk.dataaccess.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	public static long ID_GEN;

	private static Map<Long, Order> TABLE_ORDER = new HashMap<Long, Order>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Order getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Order\" where id = ?", new Object[] { 1 },
				new OrderMapper());
	}

	@Override
	public void insert(Order order) {
		jdbcTemplate.update("INSERT INTO \"Order\" (client, car, address, time"
				+ ") VALUES (?,?,?,?)", order.getClient(), order.getCar(),
				order.getAddress(), order.getTime());
	}

	@Override
	public void update(Order order) {
		Order existingOrder = TABLE_ORDER.get(order.getId());
		existingOrder.setClient(order.getClient());
		existingOrder.setCar(order.getCar());
		existingOrder.setAddress(order.getAddress());
		existingOrder.setDistance(order.getDistance());
		existingOrder.setPrice(order.getPrice());
		existingOrder.setCompleted(order.isCompleted());
	}

	@Override
	public void delete(Long id) {
			jdbcTemplate.update("delete from \"Order\" where id = ?", id);
	}

}
