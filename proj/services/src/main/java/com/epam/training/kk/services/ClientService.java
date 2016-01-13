package com.epam.training.kk.services;

import java.util.List;

import com.epam.training.kk.dataaccess.model.Client;

public interface ClientService {
	Long insert(Client client);

	Client get(Long id);

	void delete(Long id);

    Long insertOrUpdate(Client client);
   
    List<Client> getAll();
    
    Long findByPhone(String phoneNumber);

	Long update(String phoneNumber, int discont, Long id);

	Integer getCount();
	
	Long updateDiscont(int discont, double distance, int numberOfOrders, Long id);

	List<Client> sort(long first, long count, boolean direction, String column);
	
	List<Client> search(String phoneNumber);
	
	
	
	
}