package com.epam.training.kk.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.dataaccess.model.Car.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-db-context.xml")
public class CarServiceTest {

	@Autowired
	private CarService service;

	private Car car;

	@Before
	public void init() {
		car = new Car("regNumber", "brand", "model", Type.MINIVAN, "black", 2011, "callsign", 157l);
		car.setActivity(true);
	}

	@Test
	public void creationCarTest() {
		Long id = service.insert(car);
		assertNotNull(service.get(id));
	}

	@Test
	public void updateCarTest() {
		Long id = service.insert(car);
		service.update("Izmenen", "brand", "model", Type.MINIVAN, "black", 2011, "callsign", 157l, true, id);
		assertEquals("Izmenen", service.get(id).getRegistrationNumber());
	}

	@Test
	public void deletingCarTest() {
		Long id = service.insert(car);
		Car d = service.get(id);
		assertNotNull(d);
		service.delete(id);
		assertNull(service.get(id));
	}

	@Test
	public void getCarTest() {
		Long id = service.insert(car);
		assertNotNull(service.get(id));
	}
	
	@Test
	public void getAllTest(){
		List <Car> cars = service.getAll();
		for(int i = 0; i < cars.size(); i++){
			assertNotNull(cars.get(i));
		}
	}
}
