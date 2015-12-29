package com.epam.training.kk.services.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ClientDao clientDao;

	@Override
	public Order get(Long id) {
		return orderDao.getById(id);
	}

	@Override
	public Long insert(Order order) {
		Long id = orderDao.insert(order);
		LOGGER.info("new order created. id: {}", id);
		return id;
	}

	@Override
	public void update(Long id, String clientPhone, Long carId, Address address, String time, double distance, int price, boolean isCompleted) {
		orderDao.update(id, clientPhone, carId, address, time, distance, price, isCompleted);
		LOGGER.info("order {} updated", id);
	}

	@Override
	public List<Order> getAll() {
		return orderDao.getAll();
	}

	@Override
	public List<Order> sort(long first, long count) {
		return orderDao.sort(first, count);
	}

	public Integer getCount() {
		return orderDao.getCount();

	}

	@Override
	public void delete(Long id) {
		orderDao.delete(id);
		LOGGER.info("order deleted. id: {}", id);
		return;
	}

	@Override
	public void updateDriverInfo(double distance, int price, boolean isCompleted, Long id) {
		orderDao.updateDriverInfo(distance, price, isCompleted, id);
		LOGGER.info("orders driver information updated, id: {}", id);

	}
	
	@Override
	public void setDepartureTime(Order order){
        String departureTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setTime(departureTime);
	}
	
	@Override 
	public void findAndDeleteCar(Long id){
		orderDao.findAndDeleteCar(id);
	}

}