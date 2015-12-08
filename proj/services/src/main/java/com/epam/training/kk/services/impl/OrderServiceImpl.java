package com.epam.training.kk.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.HistoryOfOrdersDao;
import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.model.HistoryOfOrders;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;
	private HistoryOfOrdersDao history;

	@Override
	public Order get(Long id) {
		return orderDao.getById(id);
	}

	@Override
	public Long insert(Order order) {
		Long id =  orderDao.insert(order);
			LOGGER.info("new order created. id: {}", id);
		return id;
	}

	@Override
	public void update(Long id, int clientId, int carId, String address, String time,
			float distance, int price, boolean isCompleted) {
		orderDao.update(id, clientId, carId, address, time, distance, price, isCompleted);
		LOGGER.info("order {} updated", id);
	}

	@Override
	public Long putToHistory(Long id) {
		if(orderDao.getById(id).isCompleted() == true){
			id = history.insert((HistoryOfOrders) orderDao.getById(id));
		}
		return id;
	}
	

}
