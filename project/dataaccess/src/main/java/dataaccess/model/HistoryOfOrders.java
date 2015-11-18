package dataaccess.model;

import java.sql.Time;

public class HistoryOfOrders extends Order{

	public HistoryOfOrders(int id, Car car, Client client,
			String clientPhoneNumber, String address, Time time, int distance,
			int price, boolean isCompleted) {
		super(id, car, client, clientPhoneNumber, address, time, distance, price,
				isCompleted);
		
	}
	
	

}
