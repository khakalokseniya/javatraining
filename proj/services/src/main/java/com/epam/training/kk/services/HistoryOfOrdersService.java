package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Order;

public interface HistoryOfOrdersService {

	Order get(Long id);
	
	Long insert(Order order);

}
