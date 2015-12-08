package com.epam.training.kk.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.kk.dataaccess.dao.HistoryOfOrdersDao;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.HistoryOfOrdersService;

public class HistoryOfOrdersServiceImpl implements HistoryOfOrdersService{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HistoryOfOrdersServiceImpl.class);

	@Autowired
	private HistoryOfOrdersDao historyDao;
	
	@Override
	public Order get(Long id) {
		return historyDao.getById(id);
	}

	@Override
	public Long insert(Order order) {
		Long id =  historyDao.insert(order);
			LOGGER.info("new order added, id: {}", id);
		return id;
	}
			
			

}
