package com.phonestore.entity;

public class User {

	private int id;
	private String userName;
	private String password;
	private int vip;
	private int admin;
	private String phoneNum;
	
	
	public User() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public User(String userName, String password, int vip, int admin, String phoneNum) {
		super();
		this.userName = userName;
		this.password = password;
		this.vip = vip;
		this.admin = admin;
		this.phoneNum = phoneNum;
	}
	public User(int id, String userName, String password, int vip, int admin, String phoneNum) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.vip = vip;
		this.admin = admin;
		this.phoneNum = phoneNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", vip=" + vip + ", admin="
				+ admin + ", phoneNum=" + phoneNum + "]";
	}
	
	
}
