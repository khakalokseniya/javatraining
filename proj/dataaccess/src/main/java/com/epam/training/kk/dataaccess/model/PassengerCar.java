package com.epam.training.kk.dataaccess.model;

public class PassengerCar extends Car{

	public PassengerCar(String brand, String model, String registrationNumber) {
		super(brand, model, registrationNumber);
		super.setActivity(true);
		super.setCallsign(getCallsign());
		super.setColor(getColor());;
		super.setDriverId(getDriverId());
		super.setId(getId());
		super.setYear(getYear());
		super.getRegistrationNumber();
		super.getYear();
		super.getBrand();
		super.getCallsign();
		super.getColor();
		super.getDriverId();
		super.getId();
	}
	

}
