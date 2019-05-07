package com.phonestore.entity;

import java.util.Date;

public class Order {

	private int id;
	private String orderId;
	private int userId;
	private Date createTime;
	private double total;
	private String address;
	private String phoneNum;
	private int status;
	
	
	public Order() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	
	public Order(String orderId, int userId, Date createTime, double total, String address, String phoneNum,
			int status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.createTime = createTime;
		this.total = total;
		this.address = address;
		this.phoneNum = phoneNum;
		this.status = status;
	}



	public Order(int id, String orderId, int userId, Date createTime, double total, String address, String phoneNum,
			int status) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.createTime = createTime;
		this.total = total;
		this.address = address;
		this.phoneNum = phoneNum;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderId=" + orderId + ", userId=" + userId + ", createTime=" + createTime
				+ ", total=" + total + ", address=" + address + ", phoneNum=" + phoneNum + ", status=" + status + "]";
	}
	
	
}
