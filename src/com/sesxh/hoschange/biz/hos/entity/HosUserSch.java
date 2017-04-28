package com.sesxh.hoschange.biz.hos.entity;

/**
 * 用户手术排班对应表
 * 
 * @author wwb
 *
 */
public class HosUserSch {
	private Integer id;
	private Integer schId;// 排班id
	private String thNumber;// 手术室编号
	private Integer userId;// 用户id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchId() {
		return schId;
	}

	public void setSchId(Integer schId) {
		this.schId = schId;
	}

	public String getThNumber() {
		return thNumber;
	}

	public void setThNumber(String thNumber) {
		this.thNumber = thNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
