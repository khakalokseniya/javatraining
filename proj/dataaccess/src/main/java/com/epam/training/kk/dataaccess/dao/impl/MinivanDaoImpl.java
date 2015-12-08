package com.epam.training.kk.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.MinivanDao;
import com.epam.training.kk.dataaccess.dao.mapper.MinivanMapper;
import com.epam.training.kk.dataaccess.model.Minivan;

@Repository
public class MinivanDaoImpl implements MinivanDao {

	private static Map<Long, Minivan> TABLE_MINIVANS = new HashMap<Long, Minivan>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Minivan getById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from \"Minivans\" where id = ?", new Object[] { 1 },
				new MinivanMapper());
	}

	@Override
	public void insert(Minivan minivan) {
		jdbcTemplate
				.update("INSERT INTO \"Minivans\" (brand, model, registration_number"
						+ "color, year, callsign, driver, activity VALUES (?,?,?,?,?,?,?,?)",
						minivan.getBrand(), minivan.getModel(),
						minivan.getRegistrationNumber(), minivan.getColor(),
						minivan.getYear(), minivan.getCallsign(),
						minivan.getDriverId(), minivan.getActivity());
	}

	@Override
	public void update(Minivan minivan) {
		Minivan existingMinivan = TABLE_MINIVANS.get(minivan.getId());
		existingMinivan.setBrand(minivan.getBrand());
		existingMinivan.setModel(minivan.getModel());
		existingMinivan.setRegistrationNumber(minivan.getRegistrationNumber());
		existingMinivan.setColor(minivan.getColor());
		existingMinivan.setYear(minivan.getYear());
		existingMinivan.setCallsign(minivan.getCallsign());
		existingMinivan.setDriverId(minivan.getDriverId());
		existingMinivan.setActivity(minivan.getActivity());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Minivans\" where id = ?", id);
	}
}
