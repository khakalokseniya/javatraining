package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.dao.mapper.ClientMapper;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Client getById(Long id) {
		Client client = new Client(null);
		try {
			client = jdbcTemplate.queryForObject("select * from \"Client\" where id = ?", new Object[] { id }, new ClientMapper());
		} catch (DataAccessException e) {
			client = null;
		}
		return client;
	}

	@Override
	public Long insert(final Client client) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Client\" (phone_number," + "discont) VALUES (?,?)", new String[] { "id" });
				ps.setString(1, client.getPhoneNumber());
				ps.setInt(2, client.getDiscont());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(String phoneNumber, int discont, Long id) {
		String sqlUpdate = "UPDATE \"Client\" set phone_number=?, discont=? where id=?";
		jdbcTemplate.update(sqlUpdate, phoneNumber, discont, id);
		return;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Client\" where id = ?", id);
		return;
	}

	@Override
	public List<Client> getAll() {
		return jdbcTemplate.query(String.format("select * from \"Client\" order by  id"), new ClientMapper());
	}

	@Override
	public Long findByPhone(final String phoneNumber) {
		Long id;
		Client client;
		try{
			client = jdbcTemplate.queryForObject("select * from \"Client\" where phone_number = ?", new Object[] { phoneNumber }, new ClientMapper());
			id = client.getId();
		}catch (DataAccessException e){
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Client\" (phone_number) VALUES(?)", new String[] { "id" });
					ps.setString(1, phoneNumber);
					return ps;
				}
			}, keyHolder);
			id = keyHolder.getKey().longValue();
		} 
		return id;
	}

	@Override 
	public List<Client> sort(long first, long count){
		return jdbcTemplate.query(String.format("select * from \"Client\" "+
				 "order by id  limit %s offset %s", count, first), new ClientMapper());
	}
	
	@Override
	public Integer getCount() {
		return jdbcTemplate.queryForObject("select count(1) from \"Client\" ",
				Integer.class);
	}
}
