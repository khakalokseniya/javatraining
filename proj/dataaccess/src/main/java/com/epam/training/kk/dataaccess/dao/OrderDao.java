package com.epam.training.kk.dataaccess.dao;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Order;

public interface OrderDao {
	Order getById(Long id);

	Long insert(Order order);

	void delete(Long id);

	void update(Long id, Long clientId, Long carId, String address, String time,
			double distance, int price, boolean isCompleted);

	Long addToHistory(Order order);

	Order getFromHistory(Long id);

	List<Order> getAllFromHistory(long first, long count);

	List<Order> getAll(long first, long count);

	Integer getCount();

}
