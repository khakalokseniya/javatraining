package com.epam.training.kk.dataaccess.dao;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Order;

public interface OrderDao {
	Order getById(Long id);

	Long insert(Order order);

	void delete(Long id);

	Integer getCount();

	void update(Long id, String clientPhone, Long carId, Address address, String time, double distance, int price, boolean isCompleted);

	void updateDriverInfo(double distance, int price, boolean isCompleted, Long id);

	List<Order> getAll();

	List<Order> sort(long first, long count);

	void findAndDeleteCar(Long id);
}
