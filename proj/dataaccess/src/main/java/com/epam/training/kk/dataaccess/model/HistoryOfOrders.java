package com.epam.training.kk.dataaccess.model;

import java.sql.Time;

public class HistoryOfOrders extends Order{

	public HistoryOfOrders(Client client, Car car, String address, Time time) {
		super(client, car,  address, time);
		super.getDistance();
		super.getPrice();
		super.isCompleted();
	}
	
	

}
