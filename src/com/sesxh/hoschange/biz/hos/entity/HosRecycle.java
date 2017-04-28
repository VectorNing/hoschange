package com.sesxh.hoschange.biz.hos.entity;
/**
 * 回收桶信息
 * @author Ning
 *
 */
public class HosRecycle {
	private Integer id;
	private String number;
	private String theNumber;
	private String state;
	private Integer recycle;
	private String description;
	private String type;
	
	private String oldNumber;
	
	public String getOldNumber() {
		return oldNumber;
	}
	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTheNumber() {
		return theNumber;
	}
	public void setTheNumber(String theNumber) {
		this.theNumber = theNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getRecycle() {
		return recycle;
	}
	public void setRecycle(Integer recycle) {
		this.recycle = recycle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "HosRecycle [id=" + id + ", number=" + number + ", theNumber=" + theNumber + ", state=" + state
				+ ", recycle=" + recycle + ", description=" + description + ", type=" + type + ", oldNumber="
				+ oldNumber + "]";
	}
	
	
}
