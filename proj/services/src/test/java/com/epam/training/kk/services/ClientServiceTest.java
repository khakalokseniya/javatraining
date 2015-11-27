package com.epam.training.kk.services;

import java.text.ParseException;

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

	@Test
	public void creationClientTest() throws ParseException {
		Client client = new Client("Ivano Ivan", "+375291234567", "Grodno");
		client.setDiscont(10);
		service.insertOrUpdate(client);
	}
}
