package com.epam.training.kk.services;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-db-context.xml")
public class OrderServiceTest {

	@Autowired
	private OrderService service;

	private Order order;

	@Before
	public void init() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		order = new Order(10l, 10l, "Grodno", time);
		order.setCompleted(true);
		order.setDistance(10);
		order.setPrice(15000);
	}

	@Test
	public void creationOrderTest() {
		Long id = service.insert(order);
		Order o = service.get(id);
		assertNotNull(o);
	}

	@Test
	public void creationHistoryRowTest() {
		Long id = service.addToHistoryAndUpdateClient(order);
		Order o = service.getFromHistory(id);
		assertNotNull(o);
	}

	@Test
	public void updateOrderTest() {
		Long id = service.insert(order);
		service.update(id, 1l, 11l, "Minsk", null, 0, 0, false);
		assertEquals("Minsk", service.get(id).getAddress());
	}

	@Test
	public void getOrderTest() {
		Long id = service.insert(order);
		assertNotNull(service.get(id));
	}

	@Test
	public void getHistoryOrderTest() {
		Long id = service.addToHistoryAndUpdateClient(order);
		assertNotNull(service.getFromHistory(id));
	}
}
