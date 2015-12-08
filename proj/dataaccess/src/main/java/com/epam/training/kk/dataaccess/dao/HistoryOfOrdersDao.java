package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Order;

public interface HistoryOfOrdersDao {
	Order getById(Long id);
	
	Long insert(Order order);

	
}
