package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.ClientDao;
import com.epam.training.kk.dataaccess.dao.mapper.ClientMapper;
import com.epam.training.kk.dataaccess.model.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Client getByPhone(String phoneNumber) {
		Client client = new Client(null, null);
		try {
			client = jdbcTemplate.queryForObject(
					"select * from \"Client\" where phone_number = ?",
					new Object[] { phoneNumber }, new ClientMapper());
		} catch (DataAccessException e) {
			client = null;
		}
		return client;
	}

	@Override
	public String insert(final Client client) {
		String sql = "INSERT INTO \"Client\" (phone_number, address,"
				+ "discont) VALUES (?,?,?)";

		PreparedStatement ps = null;
		try {
			jdbcTemplate.update(sql, ps);
			ps.setString(1, client.getPhoneNumber());
			ps.setString(2, client.getAddress());
			ps.setInt(3, client.getDiscont());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return phoneNumber;
	}

	@Override
	public void update(String phoneNumber, String address, int discont, Long id) {
		String sqlUpdate = "UPDATE \"Client\" set phone_number=?, address=?,"
				+ "discont=? where id=?";
		jdbcTemplate.update(sqlUpdate, phoneNumber, address, discont, id);
		return;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Client\" where id = ?", id);
		return;
	}

}
