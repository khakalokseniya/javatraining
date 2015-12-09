package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Order;

public interface OrderService {

	Order get(Long id);

	Order getFromHistory(Long id);

	Long insert(Order order);

	Long addToHistory(Order order);

	void update(Long id, int clientId, int carId, String address, String time,
			double distance, int price, boolean isCompleted);
}
