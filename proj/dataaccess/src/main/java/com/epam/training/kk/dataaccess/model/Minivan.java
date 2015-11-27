package com.epam.training.kk.dataaccess.model;

public class Minivan extends Car{
	public Minivan (String brand, String model, String registrationNumber) {
		super(brand, model, registrationNumber);
		super.setActivity(true);
		super.setCallsign(getCallsign());
		super.setColor(getColor());;
		super.setDriver(getDriver());
		super.setId(getId());
		super.setYear(getYear());
		super.getRegistrationNumber();
		super.getYear();
		super.getBrand();
		super.getCallsign();
		super.getColor();
		super.getDriver();
		super.getId();
	}

}
