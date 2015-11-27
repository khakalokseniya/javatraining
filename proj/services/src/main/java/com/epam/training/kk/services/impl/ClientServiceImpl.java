package com.epam.training.kk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Override
	public Client get(Long id) {
		return clientDao.getById(id);
	}

	@Override
	public void insertOrUpdate(Client client) {
		if (client.getId() == null) {
			clientDao.insert(client);
		} else {
			clientDao.update(client);
		}
	}
}
