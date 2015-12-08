package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Driver;

public interface DriverService {

	Long insert(Driver driver);

	Driver get(Long id);

	void delete(Long id);

	Long update(String fullName, String phoneNumber, String address,
			java.util.Date d, String certificate, Long id);

}
