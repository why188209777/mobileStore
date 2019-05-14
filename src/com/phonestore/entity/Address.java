package com.phonestore.entity;

public class Address {

	private int id;
	private String name;
	private String city;
	private String detail;
	private String postalcode;
	private String phoneNum;
	private int userId;
	
	
	public Address() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	
	public Address(int id, String name, String city, String detail, String postalcode, String phoneNum, int userId) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.detail = detail;
		this.postalcode = postalcode;
		this.phoneNum = phoneNum;
		this.userId = userId;
	}

	

	public Address(String name, String city, String detail, String postalcode, String phoneNum, int userId) {
		super();
		this.name = name;
		this.city = city;
		this.detail = detail;
		this.postalcode = postalcode;
		this.phoneNum = phoneNum;
		this.userId = userId;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", city=" + city + ", detail=" + detail + ", postalcode="
				+ postalcode + ", phoneNum=" + phoneNum + ", userId=" + userId + "]";
	}

	
	
	
	
}
