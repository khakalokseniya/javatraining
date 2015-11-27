package com.epam.training.kk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.DriverDao;
import com.epam.training.kk.dataaccess.model.Driver;
import com.epam.training.kk.services.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverDao driverDao;

	@Override
	public Driver get(Long id) {
		return driverDao.getById(id);
	}

	@Override
	public void insertOrUpdate(Driver driver) {
		if (driver.getId() == null) {
			driverDao.insert(driver);
		} else {
			driverDao.update(driver);
		}
	}

	@Override
	public void delete(Long id) {
		driverDao.delete(id);
	}
}
