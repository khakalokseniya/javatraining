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
		Client client = new Client(null, null);
		try{
			client =  jdbcTemplate.queryForObject(
				"select * from \"Client\" where id = ?", new Object[] { id },
				new ClientMapper());
		}catch(DataAccessException e){
			client = null;
		}
		return client;
	}

	@Override
	public Long insert(final Client client) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO \"Client\" (phone_number, address,"
								+ "discont) VALUES (?,?,?)", new String[] { "id" });
				ps.setString(1, client.getPhoneNumber());
				ps.setString(2, client.getAddress());
				ps.setInt(3, client.getDiscont());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(String phoneNumber, String address, int discont, Long id) {
		String sqlUpdate = "UPDATE \"Client\" set phone_number=?, address=?,"
				+ "discont=? where id=?";
		jdbcTemplate.update(sqlUpdate, phoneNumber, address, discont,
				id);
		return;
	}
	
	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Client\" where id = ?", id);
		return;
	}
	
	@Override
	public List<Client> getAll() {
		return jdbcTemplate.query(String.format(
				"select * from \"Client\" order by  id"), new ClientMapper());
	}

}
