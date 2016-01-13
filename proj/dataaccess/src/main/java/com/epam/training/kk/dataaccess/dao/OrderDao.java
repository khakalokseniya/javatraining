package com.epam.training.kk.dataaccess.dao;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;

public interface OrderDao {
	Order getById(Long id);

	Long insert(Order order);

	void delete(Long id);

	Integer getCount();

	void update(Long id, String clientPhone, Long carId, Address address, String time, double distance, double price, boolean isCompleted);

	void updateDriverInfo(double distance, double price, boolean isCompleted, Long id);

	List<Order> getAll();
	
	List<Order> getClietsOrders(Client client);
	
	List<Order> getCompletedOrders(long first, long count, boolean direction, String column);

	List<Order> sort(long first, long count, boolean direction, String column);

	void findAndDeleteCar(Long id);

	List<Order> getCurrentOrders(long first, long count, boolean direction, String column);

}