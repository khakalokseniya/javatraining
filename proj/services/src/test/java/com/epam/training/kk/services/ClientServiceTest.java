package com.epam.training.kk.services;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training.kk.dataaccess.model.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-db-context.xml")
public class ClientServiceTest {

	@Autowired
	private ClientService service;

	private Client client;

	@Before
	public void init() {
		client = new Client("+123456", "Minsk");
		client.setDiscont(5);
	}
	@Ignore
	@Test
	public void creationClientTest() {
		Long id = service.insert(client);
		Client cl = service.get(id);
		assertNotNull(cl);
	}
	@Ignore
	@Test
	public void updateClientTest() {
		Long id = service.insert(client);
		service.update("Izmenen", "Grodno", 10, id);
		assertEquals("Izmenen", service.get(id).getPhoneNumber());
	}
@Ignore
	@Test
	public void deletingClientTest() {
		Long id = service.insert(client);
		assertNotNull(service.get(id));
		service.delete(id);
		assertNull(service.get(id)); 
	}
@Ignore
	@Test
	public void getClientTest() throws ParseException {
		Long id = service.insert(client);
		Client d = service.get(id);
		assertNotNull(d);
	}

	@Test 
	public void insetOrUpdateTest(){
		Long id = service.insertOrUpdate(client);
		Client d = service.get(id);
		assertTrue(d.getAddress() == client.getAddress());
		assertTrue(d.getId() == client.getId());
	}
}
