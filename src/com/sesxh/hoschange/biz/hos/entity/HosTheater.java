package com.sesxh.hoschange.biz.hos.entity;

/**
 * 手术室基本信息
 * 
 * @author wwb
 *
 */
public class HosTheater {
	private Integer id;
	private String number;// 手术室编号
	private String name;// 名称
	private String state;// 使用状态 0 未使用，1 已使用
	private String description;// 描述
	private String address;// 位置

	private String oldNumber;// 原手术室编号 用于业务

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}

}
