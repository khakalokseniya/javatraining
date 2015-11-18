package dataaccess.model;

public class PassengerCar extends Car{

	public PassengerCar(String brand, String registrationNumber) {
		super(brand, registrationNumber);
		super.setActivity(true);
		super.setBrand(brand);
		super.setCallSign(callSign);
		super.setColor(color);;
		super.setDriver(driver);
		super.setId(id);
		super.setRegistrationNumber(registrationNumber);
		super.setYear(year);
		super.getRegistrationNumber();
		super.getYear();
		super.getBrand();
		super.getCallSign();
		super.getColor();
		super.getDriver();
		super.getId();
	}
	

}
