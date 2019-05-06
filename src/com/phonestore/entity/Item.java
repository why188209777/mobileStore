package com.phonestore.entity;

public class Item {

	private int id;
	private String itemId;
	private String name;
	private String image;
	private double price;
	private int number;
	private int orderId;
	
	
	public Item() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Item(int id, String itemId, String name, String image, double price, int number, int orderId) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.name = name;
		this.image = image;
		this.price = price;
		this.number = number;
		this.orderId = orderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemId=" + itemId + ", name=" + name + ", image=" + image + ", price=" + price
				+ ", number=" + number + ", orderId=" + orderId + "]";
	}
	
	
}
