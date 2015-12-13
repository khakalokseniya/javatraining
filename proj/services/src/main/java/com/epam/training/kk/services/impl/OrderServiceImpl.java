package com.epam.training.kk.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ClientDao clientDao;

	@Override
	public Order get(Long id) {
		return orderDao.getById(id);
	}

	@Override
	public Order getFromHistory(Long id) {
		return orderDao.getFromHistory(id);
	}

	@Override
	public Long insert(Order order) {
		Long id = orderDao.insert(order);
		LOGGER.info("new order created. id: {}", id);
		return id;
	}

	@Override
	public Long  addToHistoryAndUpdateClient(Order order) {
		Long id = orderDao.addToHistory(order);
		LOGGER.info("new order added to the history, id: {}", id);
		int currentDiscont = order.getPrice() * 10 / 100;
		int discont = clientDao.getById(order.getClientId()).getDiscont() + currentDiscont;
		clientDao.update(clientDao.getById(order.getClientId()).getPhoneNumber(),order.getAddress(), 
				discont, order.getClientId());
		return id;
	}

	@Override
	public void update(Long id, Long clientId, Long carId, String address,
			String time, double distance, int price, boolean isCompleted) {
		orderDao.update(id, clientId, carId, address, time, distance, price,
				isCompleted);
		LOGGER.info("order {} updated", id);
	}


}