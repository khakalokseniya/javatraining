package com.epam.training.kk.services;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

	
	@Test
	public void creationClientTest() {
		client = new Client("1");
		client.setDiscont(500);
		Long id = service.insert(client);
		Client cl = service.get(id);
		assertNotNull(cl);
	}
	
	@Test
	public void updateClientTest() {
		client = new Client("2");
		client.setDiscont(500);
		Long id = service.insert(client);
		service.update("Izm", 10, id);
		assertEquals("Izm", service.get(id).getPhoneNumber());
	}
	
	@Test
	public void deletingClientTest() {
		client = new Client("3");
		client.setDiscont(500);
		Long id = service.insert(client);
		assertNotNull(service.get(id));
		service.delete(id);
		assertNull(service.get(id)); 
	}
	
	@Test
	public void getClientTest() throws ParseException {
		client = new Client("4");
		client.setDiscont(500);
		Long id = service.insert(client);
		Client d = service.get(id);
		assertNotNull(d);
	}

	@Test 
	public void insetOrUpdateTest(){
		client = new Client("5");
		client.setDiscont(500);
		Long id = service.insertOrUpdate(client);
		assertNotNull(service.get(id));
		List clients = new ArrayList();
		clients.addAll(service.getAll());
		int count = 0;
		for(Iterator<Client> i = clients.iterator(); i.hasNext();){
			if(i.next().getPhoneNumber().equals(client.getPhoneNumber())){
				count = count+1;
			}
		}
		assertTrue(count==1);
	}
}
