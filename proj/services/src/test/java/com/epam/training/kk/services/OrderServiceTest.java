package com.epam.training.kk.services;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Driver;
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
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(cal.getInstance());
		Address address = new Address("Boldina", "5", "2", "11");
		order = new Order("12321", 132l, address, time, date);
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
	public void updateOrderTest() {
		Long id = service.insert(order);
		Address address = new Address("Minsk", "5", "2", "11");
		service.update(id, "12322", 132l, address, null, 0, 0, false);
		assertEquals("Minsk", service.get(id).getAddress().getStreet());
	}

	@Test
	public void getOrderTest() {
		Long id = service.insert(order);
		assertNotNull(service.get(id));
	}

	
	@Test
	public void getAllTest(){
		List <Order> orders= service.getAll();
		for(int i = 0; i < orders.size(); i++){
			assertNotNull(orders.get(i));
		}
	}
	
	
	@Test
	public void deletingOrderTest() {
		Long id = service.insert(order);
		Order d = service.get(id);
		assertNotNull(d);
		service.delete(id);
		assertNull(service.get(id));
	}
}

