package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Client;

public interface ClientDao {

	String insert(Client client);

	void update(String phoneNumber, String address,
			int discont, Long id);

	void delete(Long id);

	Client getByPhone(String phoneNumber);

}
