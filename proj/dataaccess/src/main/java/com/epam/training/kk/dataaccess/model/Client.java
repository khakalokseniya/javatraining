package com.epam.training.kk.dataaccess.model;

public class Client {
	private Long id;
	private String fullName;
	private String phoneNumber;
	private String address;
	private int discont;
	
	public Client(String fullName, String phoneNumber, String address){
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
	 * @param id the id to set
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
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
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
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the discont
	 */
	public int getDiscont() {
		return discont;
	}

	/**
	 * @param discont the discont to set
	 */
	public void setDiscont(int discont) {
		this.discont = discont;
	}
	
}
