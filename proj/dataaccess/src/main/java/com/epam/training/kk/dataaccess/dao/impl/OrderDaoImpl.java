package com.epam.training.kk.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.kk.dataaccess.dao.OrderDao;
import com.epam.training.kk.dataaccess.dao.mapper.CarMapper;
import com.epam.training.kk.dataaccess.dao.mapper.ClientMapper;
import com.epam.training.kk.dataaccess.dao.mapper.OrderMapper;
import com.epam.training.kk.dataaccess.model.Address;
import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Order getById(Long id) {//add join address
		Order order = new Order(null, 0l, null, null, null);
		try {
			return jdbcTemplate.queryForObject("select * from \"Order\""
					+"LEFT JOIN \"Address\" ON (\"Order\".address=\"Address\".id)" , new Object[] { id }, new OrderMapper());
		} catch (DataAccessException e) {
			order = null;
		}
		return order;
	}

	@Override
	public Long insert(final Order order) {
		final KeyHolder keyHolder1 = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps1 = connection.prepareStatement("INSERT INTO \"Address\" (street, house, corps, apartment) VALUES (?,?,?,?)",
						new String[] { "id" });
				ps1.setString(1, order.getAddress().getStreet());
				ps1.setString(2, order.getAddress().getHouse());
				ps1.setString(3, order.getAddress().getCorps());
				ps1.setString(4, order.getAddress().getApartment());
				return ps1;
			}
		}, keyHolder1);
		KeyHolder keyHolder2 = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps2 = connection.prepareStatement("INSERT INTO \"Order\" (client_phone, car_id, address, time, date, is_completed) VALUES (?,?,?,?,?,?)",
						new String[] { "id" });
				ps2.setString(1, order.getClientPhone());
				ps2.setLong(2, order.getCarId());
				ps2.setObject(3, keyHolder1.getKey().longValue());
				ps2.setString(4, order.getTime());
				ps2.setString(5, order.getDate());
				ps2.setBoolean(6, order.isCompleted());
				
				return ps2;
			}
		}, keyHolder2);
		return keyHolder2.getKey().longValue();
		// TODO transaction address
	}
	
	


	@Override
	public void update(Long id, String clientPhone, Long carId, Address address, String time, double distance, double price, boolean isCompleted) {
		String sqlUpdate = "UPDATE \"Order\" set client_phone=?, car_id=?, address=?, time=?," + "distance=?, price=?, is_completed=? where id=?";
		jdbcTemplate.update(sqlUpdate, clientPhone, carId, address, time, distance, price, isCompleted, id);
		return;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from \"Order\" where id = ?", id);
	}

	@Override
	public List<Order> getAll() {
		return jdbcTemplate.query(String.format("select * from \"Order\" "+
	" LEFT JOIN \"Address\" ON (\"Order\".address=\"Address\".id) order by  \"Order\".id"), new OrderMapper());
	}
	
	@Override
	public List<Order> getCompletedOrders(long first, long count, boolean direction, String column) {
		String cd = direction ? "ASC" : "DESC";
		return jdbcTemplate.query(String.format("select * from \"Order\""+
	"LEFT JOIN \"Address\" ON (\"Order\".address=\"Address\".id)  where is_completed='true' order by  %s %s limit %s offset %s", column, cd, count, first), new OrderMapper());
	}
	
	@Override
	public List<Order> getCurrentOrders(long first, long count, boolean direction, String column) {
		String cd = direction ? "ASC" : "DESC";
		return jdbcTemplate.query(String.format("select * from \"Order\""+
				"LEFT JOIN \"Address\" ON (\"Order\".address=\"Address\".id)  where is_completed='false' order by  %s %s limit %s offset %s", column, cd, count, first), new OrderMapper());
	}
	
	
	@Override 
	public List<Order> sort(long first, long count, boolean direction, String column){
		String cd = direction ? "ASC" : "DESC";
		return jdbcTemplate.query(String.format("select * from \"Order\""+
				 "LEFT JOIN \"Address\" ON (\"Order\".address=\"Address\".id) order by  %s %s limit %s offset %s", column, cd, count, first), new OrderMapper());
	}


	@Override
	public Integer getCount() {
		return jdbcTemplate.queryForObject("select count(1) from \"Order\" ", Integer.class);
	}

	@Override
	public void updateDriverInfo(double distance, double price, boolean isCompleted, Long id) {
		String sqlUpdate = "UPDATE \"Order\" set distance=?, price=?, is_completed=? where id=?";
		jdbcTemplate.update(sqlUpdate, distance, price, isCompleted, id);
		return;
	}

	@Override
	public void findAndDeleteCar(Long id) {
			jdbcTemplate.update("delete from \"Order\" where car_id = ?", id);
	}

	@Override
	public List<Order> getClietsOrders(Client client) {
		String phone = client.getPhoneNumber();
		return jdbcTemplate.query(String.format("select * from \"Order\""+
				"LEFT JOIN \"Address\" ON (\"Order\".address=\"Address\".id)  where client_phone=? order by  \"Order\".date DESC", phone), new OrderMapper());
	}

	

}
