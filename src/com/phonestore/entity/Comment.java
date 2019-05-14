package com.phonestore.entity;

public class Comment {

	private int id;
	private String content;
	private int userid;
	private String phoneid;
	private String ip;
	private String equipment;
	private String contentTime;
	
	public Comment(){}
	
	
	
	
	public Comment(String content, int userid, String phoneid, String ip, String equipment, String contentTime) {
		super();
		this.content = content;
		this.userid = userid;
		this.phoneid = phoneid;
		this.ip = ip;
		this.equipment = equipment;
		this.contentTime = contentTime;
	}




	public Comment(int id, String content, int userid, String phoneid, String ip, String equipment,
			String contentTime) {
		super();
		this.id = id;
		this.content = content;
		this.userid = userid;
		this.phoneid = phoneid;
		this.ip = ip;
		this.equipment = equipment;
		this.contentTime = contentTime;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getContentTime() {
		return contentTime;
	}
	public void setContentTime(String contentTime) {
		this.contentTime = contentTime;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", userid=" + userid + ", phoneid=" + phoneid + ", ip="
				+ ip + ", equipment=" + equipment + ", contentTime=" + contentTime + "]";
	}
	
	
}
