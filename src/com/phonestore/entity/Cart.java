package com.phonestore.entity;

public class Cart {

	private int id;
	private String phoneId;
	private int num;
	private int userId;
	private int checked;
	
	
	public Cart() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	public Cart(int id, String phoneId, int num, int userId, int checked) {
		super();
		this.id = id;
		this.phoneId = phoneId;
		this.num = num;
		this.userId = userId;
		this.checked = checked;
	}

	public Cart(String phoneId, int num, int userId, int checked) {
		super();
		this.phoneId = phoneId;
		this.num = num;
		this.userId = userId;
		this.checked = checked;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
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
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}


	@Override
	public String toString() {
		return "Cart [id=" + id + ", phoneId=" + phoneId + ", num=" + num + ", userId=" + userId + ", checked="
				+ checked + "]";
	}


	
}
