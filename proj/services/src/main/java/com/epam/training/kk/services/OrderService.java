package com.epam.training.kk.services;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;

public interface OrderService {

	Order get(Long id);

	Long insert(Order order);

	void delete(Long id);

	List<Order> getAll();
	
	Integer getCount();

	void updateDriverInfo(double distance, double price, boolean isCompleted, Long id);

	void update(Long id, String clientPhone, Long carId, Address address, String time, double distance, double price, boolean isCompleted);

	List<Order> sort(long first, long count, boolean direction, String column);

	void setDepartureTime(Order order);
	
	void setDate(Order order);

	void findAndDeleteCar(Long id);

	Long updateClient(Order order, boolean useDiscont);

	List<Order> getCompletedOrders(long first, long count, boolean direction, String column);

	List<Order> getCurrentOrders(long first, long count, boolean direction, String column);
	
	List<Order> getClietsOrders(Client client);

}
