package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Client;

public interface ClientDao {
	Client getById(Long id);

	Long insert(Client client);

	void update(String phoneNumber, String address,
			int discont, Long id);

	void delete(Long id);

}
