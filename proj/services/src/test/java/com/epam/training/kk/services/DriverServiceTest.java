package com.epam.training.kk.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.Driver;
import com.epam.training.kk.services.DriverService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-db-context.xml")
public class DriverServiceTest {

	@Autowired
	private DriverService service;

	@Test
	public void creationDriverTest() throws ParseException {
		Driver driver = new Driver("Ivanov Ivan", "+375291234567", "Grodno");
		driver.setCertificate("QW-11");
		String dateStr = "11.11.2015";
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date date = (Date) df.parse(dateStr);
		driver.setStartingDate(date);
		service.insertOrUpdate(driver);
	}

	@Test
	public void deletingDriverTest() throws ParseException {
		Driver driver = new Driver("Sidorov Sidor", "+375291234567", "Grodno");
		service.delete(driver.getId());
	}

	@Test
	public void getDriver() {
		service.get(1l);
	}

}
