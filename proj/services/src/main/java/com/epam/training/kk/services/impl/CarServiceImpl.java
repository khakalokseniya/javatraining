package com.epam.training.kk.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.kk.dataaccess.dao.CarDao;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Car.Type;
import com.epam.training.kk.services.CarService;

@Service
public class CarServiceImpl implements CarService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DriverServiceImpl.class);

	@Autowired
	private CarDao carDao;

	@Override
	public Car get(Long id) {
		return carDao.getById(id);
	}

	@Override
	public Long insert(Car car) {
		Long id = null;
		if (car.getId() == null) {
			id = carDao.insert(car);
			LOGGER.info("new car created. id: {}", id);
		}
		return id;
	}

	@Override
	public void delete(Long id) {
		carDao.delete(id);
	}

	@Override
	public Long update(String registrationNumber, String brand, String model,
			Type type, String color, int year, String callsign, Long driverId,
			boolean activity, Long id) {
		carDao.update(registrationNumber, brand, model, type, color, year,
				callsign, driverId, activity, id);
		LOGGER.info("driver {} updated", id);
		return id;
	}

	@Override
	public List<Car> getAll() {
		return carDao.getAll();
	}
	
	public Integer getCount() {
		return carDao.getCount();

	}

	@Override
	public List<Car> getActiveCars() {
		return carDao.getActiveCars();
	}
	
	@Override
	public List<Car> sort(long first, long count, boolean direction, String column){
		return carDao.sort(first, count, direction, column);
	}

	@Override
	public Long updateDistance(double distance, Long id) {
		return carDao.updateDistance(distance, id);
	}
	
	
	
	
}
