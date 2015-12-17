package com.epam.training.kk.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientDao clientDao;

	@Override
	public Client get(Long id) {
		return clientDao.getById(id);
	}

	@Override
	public Long insertOrUpdate(Client client) {
		Long id = null;
		if (client.getId() == null) {
			id = clientDao.insert(client);
			LOGGER.info("new client created. id: {}", id);
		}else{
			System.out.println("Клиент уже существует");
		}
		return id;
	}

	@Override
	public Long update(String phoneNumber, String address,
			int discont, Long id) {
		clientDao.update(phoneNumber, address, discont, id);
		LOGGER.info("client {} updated", id);
		return id;
	}

	@Override
	public void delete(Long id) {
		clientDao.delete(id);
		LOGGER.info("client deleted. id: {}", id);
		return;
	}
}