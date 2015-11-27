package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Order;

public interface OrderDao {
	Order getById(Long id);

	void insert(Order order);

	void update(Order order);

	void delete(Long id);

}
