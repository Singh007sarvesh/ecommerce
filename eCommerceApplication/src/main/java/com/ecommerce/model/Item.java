package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
    @GeneratedValue
    @Column(name = "itemid")
	private Integer itemid;
	@Column(name = "itemname")
	private String itemname;
	@Column(name = "itempicture")
	private String itempicture;
	@Column(name = "itemdesc")
	private String itemdesc;
	@Column(name = "itemprice")
	private float itemprice;
	@Column(name = "itemcategory")
	private String itemcategory;
	@Column(name = "itemstatus")
	private String itemstatus;
	@Column(name = "userid")
	private Integer userid;
	
	public Item() {
		
	}
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItempicture() {
		return itempicture;
	}
	public void setItempicture(String itempicture) {
		this.itempicture = itempicture;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	public float getItemprice() {
		return itemprice;
	}
	public void setItemprice(float itemprice) {
		this.itemprice = itemprice;
	}
	public String getItemcategory() {
		return itemcategory;
	}
	public void setItemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}
	public String getItemstatus() {
		return itemstatus;
	}
	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	
}
