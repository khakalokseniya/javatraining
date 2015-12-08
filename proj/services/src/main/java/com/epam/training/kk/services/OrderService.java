package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Order;

public interface OrderService {
	
	Order get(Long id);
	
	Long insert(Order order);
	
	
	Long putToHistory(Long id);

	void update(Long id, int clientId, int carId, String address, String time,
			float distance, int price, boolean isCompleted);
	
	

}
