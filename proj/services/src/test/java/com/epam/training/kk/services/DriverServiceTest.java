package com.epam.training.kk.services;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.Driver;
import com.epam.training.kk.services.DriverService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-db-context.xml")
public class DriverServiceTest {

	@Autowired
	private DriverService service;

	private Driver driver;

	@Before
	public void init() throws ParseException {
		driver = new Driver("create", "+375291234567", "Grodno");
		driver.setCertificate("QW-11");
		String dateStr = "2015-11-11";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date) df.parse(dateStr);
		driver.setStartingDate(date);
	}

	@Test
	public void creationDriverTest() throws ParseException {
		Long id = service.insert(driver);
		Driver f = service.get(id);
		assertNotNull(f);
	}

	@Test
	public void updateDriverTest() throws ParseException {
		Long id = service.insert(driver);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = "2015-10-12";
		java.sql.Date startingDate = new java.sql.Date(sdf.parse(date2)
				.getTime());
		service.update("Izmenen", "+375291234655", "Grodno", startingDate,
				"LLL", id);
		assertEquals("Izmenen", service.get(id).getFullName());
	}

	@Test
	public void deletingDriverTest() {
		Long id = service.insert(driver);
		Driver d = service.get(id);
		assertNotNull(d);
		service.delete(id);
		assertNull(service.get(id)); // доделать!!!!!
	}

	@Test
	public void getDriverTest() throws ParseException {
		Long id = service.insert(driver);
		Driver d = service.get(id);
		assertNotNull(d);
	}
}
