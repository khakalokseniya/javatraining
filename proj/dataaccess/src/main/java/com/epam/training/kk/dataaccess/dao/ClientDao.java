package com.epam.training.kk.dataaccess.dao;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;

public interface ClientDao {
	Client getById(Long id);

	Long insert(Client client);

	void delete(Long id);
	
	List<Client> getAll();
	
	Long findByPhone(String phoneNumber);

	void update(String phoneNumber, int discont, Long id);

	List<Client> sort(long first, long count, boolean direction, String column, String search);
	
	List<Client> search(String phoneNumber);

	Integer getCount();
	
	Long updateDiscontDistanceNumber(int discont, double distance, int numberOfOrders, Long clientId);

	
	
	
}
