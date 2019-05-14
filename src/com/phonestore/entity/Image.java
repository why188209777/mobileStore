package com.phonestore.entity;

/**
 * @author why
 *
 */
public class Image {
	private int id;
	private String imageid;
	private String phonename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public String getPhonename() {
		return phonename;
	}
	public void setPhonename(String phonename) {
		this.phonename = phonename;
	}
	@Override
	public String toString() {
		return "image [id=" + id + ", imageid=" + imageid + ", phonename=" + phonename + "]";
	}
	public Image(int id, String imageid, String phonename) {
		super();
		this.id = id;
		this.imageid = imageid;
		this.phonename = phonename;
	}
	public Image(String imageid, String phonename) {
		super();
		this.imageid = imageid;
		this.phonename = phonename;
	}
	
	public Image() {
		super();
	}
}
