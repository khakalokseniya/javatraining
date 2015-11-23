package dataaccess.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import dataaccess.model.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class DriverMapper  implements RowMapper<Driver> {
	@Override

	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("Driver_id");
		String fullName = rs.getString("full_name");
		String phoneNumber = rs.getString("phone_number");
		String address = rs.getString("address");
		Date startingDate = rs.getDate("starting_date");
		String certificate = rs.getString("cetrificate");
		Driver driver = new Driver(fullName, phoneNumber, address);
		driver.setId(id);
		driver.setStartingDate(startingDate);
		driver.setCertificate(certificate);
		return driver;
	}
}
