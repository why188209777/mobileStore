package com.phonestore.entity;

public class Phone {

	private int id;
	private String phoneId;
	private String name;
	private String brand;
	private double price;
	private int num;
	private String image;
	private String size;
	private String color;
	private String ram;
	private String rom;
	private String netType;
	private String camera;
	private String cpu;
	private String operatingSystem;
	private String description;
	
	public Phone() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	



	public Phone(String phoneId, String name, String brand, double price, int num, String image, String size,
			String color, String ram, String rom, String netType, String camera, String cpu, String operatingSystem,
			String description) {
		super();
		this.phoneId = phoneId;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.num = num;
		this.image = image;
		this.size = size;
		this.color = color;
		this.ram = ram;
		this.rom = rom;
		this.netType = netType;
		this.camera = camera;
		this.cpu = cpu;
		this.operatingSystem = operatingSystem;
		this.description = description;
	}





	public Phone(int id, String phoneId, String name, String brand, double price, int num, String image, String size,
			String color, String ram, String rom, String netType, String camera, String cpu, String operatingSystem,
			String description) {
		super();
		this.id = id;
		this.phoneId = phoneId;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.num = num;
		this.image = image;
		this.size = size;
		this.color = color;
		this.ram = ram;
		this.rom = rom;
		this.netType = netType;
		this.camera = camera;
		this.cpu = cpu;
		this.operatingSystem = operatingSystem;
		this.description = description;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getRom() {
		return rom;
	}
	public void setRom(String rom) {
		this.rom = rom;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}





	@Override
	public String toString() {
		return "Phone [id=" + id + ", phoneId=" + phoneId + ", name=" + name + ", brand=" + brand + ", price=" + price
				+ ", num=" + num + ", image=" + image + ", size=" + size + ", color=" + color + ", ram=" + ram
				+ ", rom=" + rom + ", netType=" + netType + ", camera=" + camera + ", cpu=" + cpu + ", operatingSystem="
				+ operatingSystem + ", description=" + description + "]";
	}

	
}
