package com.epam.training.kk.dataaccess.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order implements Serializable{
	private Long id;
	private String clientPhone;
	private Long carId;
	private Address address;
	private String time;
	private double distance;
	private int price;
	boolean isCompleted = true;
	
	public Order(){}
	
	public Order(String clientPhone, Long carId, Address address, String time) {
		this.clientPhone = clientPhone;
		this.carId = carId;
		this.address = address;
		this.time = time;
	}
	
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the carId
	 */
	public Long getCarId() {
		return carId;
	}

	/**
	 * @param carId
	 *            the carId to set
	 */
	public void setCarId(Long carId) {
		this.carId = carId;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted
	 *            the isCompleted to set
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;

	}
	
	/**
	 * @return the clientPhone
	 */
	public String getClientPhone() {
		return clientPhone;
	}

	/**
	 * @param clientPhone the clientPhone to set
	 */
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
}
