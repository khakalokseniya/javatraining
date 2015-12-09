package com.epam.training.kk.dataaccess.model;

public class Car {
	private Long id;
	private String registrationNumber;
	private String brand;
	private String model;
	private Type type;
	private String color;
	private int year;
	private String callsign;
	private Long driverId;
	boolean activity;
	public enum Type {PASSENGER_CAR, MINIVAN, TRUCK};
	
	public Car(String registrationNumber, String brand, String model, Type type, String color,
			int year, String callsign, Long driverId){
		this.brand = brand;
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.type= type;
		this.color = color;
		this.year = year;
		this.callsign = callsign;
		this.driverId = driverId;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the callSign
	 */
	public String getCallsign() {
		return callsign;
	}

	/**
	 * @param callsign the callSign to set
	 */
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	

	/**
	 * @return the activity
	 */
	public boolean getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
