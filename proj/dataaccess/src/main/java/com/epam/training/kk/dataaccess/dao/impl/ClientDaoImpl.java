package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.dao.mapper.ClientMapper;
import com.epam.training.kk.dataaccess.model.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	public static long ID_GEN;

	private static Map<Long, Client> TABLE_CLIENT = new HashMap<Long, Client>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Client getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Client\" where id = ?", new Object[] { 1 },
				new ClientMapper());
	}

	@Override
	public void insert(Client client) {
		jdbcTemplate.update(
				"INSERT INTO \"Client\" (full_name, phone_number, address, discont,"
						+ "VALUES (?,?,?,?)", client.getFullName(),
				client.getPhoneNumber(), client.getAddress(),
				client.getDiscont());
	}

	@Override
	public void update(Client client) {
		Client existingClient = TABLE_CLIENT.get(client.getId());
		existingClient.setFullName(client.getFullName());
		existingClient.setAddress(client.getAddress());
		existingClient.setPhoneNumber(client.getPhoneNumber());
		existingClient.setDiscont(client.getDiscont());
	}
}
