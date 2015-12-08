package com.epam.training.kk.services;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.HistoryOfOrders;
import com.epam.training.kk.dataaccess.model.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-db-context.xml")
public class HistoryOfOrdersServiceTest {

	@Autowired
	private HistoryOfOrdersService historyService;
	private OrderService service;

	private Order order;

	@Before
	public void init() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		order = new Order(1, 112, "Grodno", time);
	}

	@Test
	public void addAndGetOrderToHistoryTest() {
		Long id = service.insert(order);
		Long hid = historyService.insert(service.get(id));
		Order o = historyService.get(hid);
		assertNotNull(o);
	}
}