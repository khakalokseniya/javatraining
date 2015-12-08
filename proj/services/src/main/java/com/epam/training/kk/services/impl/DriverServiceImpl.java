package com.epam.training.kk.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.DriverDao;
import com.epam.training.kk.dataaccess.model.Driver;
import com.epam.training.kk.services.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DriverServiceImpl.class);

	@Autowired
	private DriverDao driverDao;

	@Override
	public Driver get(Long id) {
		return driverDao.getById(id);
	}

	@Override
	public Long insert(Driver driver) {
		Long id = null;
		if (driver.getId() == null) {
			id = driverDao.insert(driver);
			LOGGER.info("new driver created. id: {}", id);
		}
		return id;
	}

	@Override
	public Long update(String fullName, String phoneNumber, String address,
			java.util.Date startingDate, String certificate, Long id) {
		driverDao.update(fullName, phoneNumber, address, startingDate,
				certificate, id);
		LOGGER.info("driver {} updated", id);
		return id;
	}

	@Override
	public void delete(Long id) {
		driverDao.delete(id);
		LOGGER.info("driver deleted. id: {}", id);
		return;
	}
}
