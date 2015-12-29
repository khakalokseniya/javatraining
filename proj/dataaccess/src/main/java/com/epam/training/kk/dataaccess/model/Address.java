package com.epam.training.kk.dataaccess.model;

import java.io.Serializable;

public class Address implements Serializable{
	private String street;
	private String house;
	private String corps;
	private String apartment;
	
	public Address(){}

	public Address(String street, String  house, String corps, String apartment) {
		this.street = street;
		this.house = house;
		this.corps = corps;
		this.apartment = apartment;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (street + " " + house + "-" + corps + "-" + apartment);
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}

	/**
	 * @param house
	 *            the house to set
	 */
	public void setHouse(String house) {
		this.house = house;
	}

	/**
	 * @return the corps
	 */
	public String  getCorps() {
		return corps;
	}

	/**
	 * @param corps
	 *            the corps to set
	 */
	public void setCorps(String corps) {
		this.corps = corps;
	}

	/**
	 * @return the apartment
	 */
	public String getApartment() {
		return apartment;
	}

	/**
	 * @param apartment
	 *            the apartment to set
	 */
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

}
