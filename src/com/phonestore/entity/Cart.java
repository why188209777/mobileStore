package com.phonestore.entity;

public class Cart {

	private int id;
	private int phoneId;
	private int num;
	private int userId;
	
	
	public Cart() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Cart(int id, int phoneId, int num, int userId) {
		super();
		this.id = id;
		this.phoneId = phoneId;
		this.num = num;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", phoneId=" + phoneId + ", num=" + num + ", userId=" + userId + "]";
	}
	
	
}
