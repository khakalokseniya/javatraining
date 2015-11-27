package com.epam.training.kk.dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.epam.training.kk.dataaccess.model.Client;

public final class ClientMapper implements RowMapper<Client> {
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("Client_id");
		String fullName = rs.getString("full_name");
		String phoneNumber = rs.getString("phone_number");
		String address = rs.getString("address");
		int discont = rs.getInt("discont");
		Client client = new Client(fullName, phoneNumber, address);
		client.setId(id);
		client.setFullName(fullName);
		client.setPhoneNumber(phoneNumber);
		client.setAddress(address);
		client.setDiscont(discont);
		return client;
	}
}
