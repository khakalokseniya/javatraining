package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Driver;

public interface DriverService {

	void insertOrUpdate(Driver driver);

	Driver get(Long id);


	void delete(Long id);

}
