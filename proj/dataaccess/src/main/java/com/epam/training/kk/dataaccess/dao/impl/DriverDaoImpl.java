package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.DriverDao;
import com.epam.training.kk.dataaccess.dao.mapper.DriverMapper;
import com.epam.training.kk.dataaccess.model.Driver;

@Repository
public class DriverDaoImpl implements DriverDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Driver getById(Long id) {
		Driver driver = new Driver(null, null, null);
		try{
		String sql = "SELECT * FROM \"Driver\" WHERE id = ?";
		 
		driver = jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new DriverMapper());
		}catch(DataAccessException e){
			driver = null;
		}
		return driver;
	}

	@Override
	public Long insert(final Driver driver) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO \"Driver\" (full_name, phone_number, address, starting_date,"
								+ "certificate) VALUES (?,?,?,?,?)",
						new String[] { "id" });
				ps.setString(1, driver.getFullName());
				ps.setString(2, driver.getPhoneNumber());
				ps.setString(3, driver.getAddress());
				ps.setDate(4, new Date(driver.getStartingDate().getTime()));
				ps.setString(5, driver.getCertificate());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(String fullName, String phoneNumber, String address, java.util.Date startingDate, 
			String certificate, Long id) {
		String sqlUpdate = "UPDATE \"Driver\" set full_name=?, phone_number=?, address=?, starting_date=?,"
								+ "certificate=? where id=?";
        jdbcTemplate.update(sqlUpdate, fullName, phoneNumber, address, startingDate, certificate,
        		id);
        return;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Driver\" where id = ?", id);
		return;
	}

}
