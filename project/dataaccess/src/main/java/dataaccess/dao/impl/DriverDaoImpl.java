package dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dataaccess.dao.DriverDao;
import dataaccess.dao.mapper.DriverMapper;
import dataaccess.model.Driver;

@Repository
public class DriverDaoImpl implements DriverDao{
	
	public static long ID_GEN;
	
	private static Map<Long, Driver> TABLE_DRIVER = new HashMap<Long, Driver>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Driver getById(Long id) {
		return jdbcTemplate.queryForObject("select * from \"Driver\" where id = ?",
				new Object[] { 1 }, new DriverMapper());
	}

	public void insert(Driver driver) {
		// TODO Auto-generated method stub
		
	}

	public void update(Driver driver) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Driver driver) {
		// TODO Auto-generated method stub
		
	}

	public void select(Driver driver) {
		// TODO Auto-generated method stub
		
	}
	

}
