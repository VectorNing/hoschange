package com.sesxh.hoschange.biz.hos.entity;

public class HosRoom {
	private Integer id;
	private Integer name;
	private String state;
	private String description;
	private String theaterNumber;
	private String sex;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getName() {
		return name;
	}
	public void setName(Integer name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTheaterNumber() {
		return theaterNumber;
	}
	public void setTheaterNumber(String theaterNumber) {
		this.theaterNumber = theaterNumber;
	}
	
	public String getSex() {
		return sex;
	}
	@Override
	public String toString() {
		return "HosRoom [id=" + id + ", name=" + name + ", state=" + state + ", description=" + description
				+ ", theaterNumber=" + theaterNumber + ", sex=" + sex + "]";
	}
	
	
	
	
}
