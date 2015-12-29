package com.epam.training.kk.dataaccess.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Driver implements Serializable{
	private Long id;
	private String fullName;
	private String phoneNumber;
	private String address;
	private Date startingDate;
	private String certificate;

	public Driver(){}
	public Driver(String fullName, String phoneNumber, String address) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullname
	 *            the fullName to set
	 */
	public void setFullName(String fullname) {
		this.fullName = fullname;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the startingDate
	 */
	public Date getStartingDate() {
		return startingDate;
	}

	/**
	 * @param startingDate2
	 *            the startingDate to set
	 * @throws ParseException
	 */
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
		;
	}

	/**
	 * @return the certificate
	 */
	public String getCertificate() {
		return certificate;
	}

	/**
	 * @param certificate
	 *            the certificate to set
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}
