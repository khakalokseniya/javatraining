package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Client;

public interface ClientService {
	Long insert(Client client);

	Client get(Long id);

	void delete(Long id);

	Long update(String fullName, String phoneNumber, String address,
			int discont, Long id);
}