package com.epam.training.kk.services;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Order;

public interface OrderService {

	Order get(Long id);

	Long insert(Order order);

	void delete(Long id);

	List<Order> getAll();

	Integer getCount();

	void updateDriverInfo(double distance, int price, boolean isCompleted, Long id);

	void update(Long id, String clientPhone, Long carId, Address address, String time, double distance, int price, boolean isCompleted);

	List<Order> sort(long first, long count);

	void setDepartureTime(Order order);

	void findAndDeleteCar(Long id);

}
