package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Truck;

public interface TruckDao {
	Truck getById(Long id);

	void insert(Truck truck);

	void update(Truck truck);

	void delete(Long id);

}
