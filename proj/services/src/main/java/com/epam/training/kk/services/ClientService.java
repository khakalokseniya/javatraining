package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Client;

public interface ClientService {
	void insertOrUpdate(Client client);

	Client get(Long id);
}
