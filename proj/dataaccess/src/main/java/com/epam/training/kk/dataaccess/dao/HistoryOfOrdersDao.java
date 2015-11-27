package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.HistoryOfOrders;

public interface HistoryOfOrdersDao {
	HistoryOfOrders getById(Long id);
	
	void insert(HistoryOfOrders historyOfOrders);

	void update(HistoryOfOrders historyOfOrders);
	
	void delete(Long id);
}
