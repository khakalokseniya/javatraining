package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Client;

public interface ClientDao {
	Client getById(Long id);

	void insert(Client client);

	void update(Client client);

}
