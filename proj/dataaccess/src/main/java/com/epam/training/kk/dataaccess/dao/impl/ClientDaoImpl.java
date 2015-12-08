package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Client getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Client\" where id = ?", new Object[] { id },
				new ClientMapper());
	}

	@Override
	public Long insert(final Client client) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO \"Client\" (full_name, phone_number, address,"
								+ "discont) VALUES (?,?,?,?)", new String[] { "id" });
				ps.setString(1, client.getFullName());
				ps.setString(2, client.getPhoneNumber());
				ps.setString(3, client.getAddress());
				ps.setInt(4, client.getDiscont());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(String fullName, String phoneNumber, String address,
			int discont, Long id) {
		String sqlUpdate = "UPDATE \"Client\" set full_name=?, phone_number=?, address=?,"
				+ "discont=? where id=?";
		jdbcTemplate.update(sqlUpdate, fullName, phoneNumber, address, discont,
				id);
		return;
	}
	
	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Client\" where id = ?", id);
		return;
	}

}
