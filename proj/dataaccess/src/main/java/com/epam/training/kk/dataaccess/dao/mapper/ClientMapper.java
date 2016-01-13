package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.epam.training.kk.dataaccess.model.Client;

public final class ClientMapper implements RowMapper<Client> {
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String phoneNumber = rs.getString("phone_number");
		int discont = rs.getInt("discont");
		double distance = rs.getDouble("distance");
		int numberOfOrders = rs.getInt("number_of_orders");
		Client client = new Client(phoneNumber);
		client.setId(id);
		client.setDiscont(discont);
		client.setDistance(distance);
		client.setNumberOfOrders(numberOfOrders);
		return client;
	}
}
