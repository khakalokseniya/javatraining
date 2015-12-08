package com.epam.training.kk.dataaccess.dao;


import com.epam.training.kk.dataaccess.model.Order;

public interface OrderDao {
	Order getById(Long id);

	Long insert(Order order);


	void delete(Long id);


	void update(Long id, int clientId, int carId, String address, String time,
			float distance, int price, boolean isCompleted);

}
