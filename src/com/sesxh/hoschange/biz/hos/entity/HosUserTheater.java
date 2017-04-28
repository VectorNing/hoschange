package com.sesxh.hoschange.biz.hos.entity;

/**
 * 用户更衣室
 * 
 * @author cyf
 * @date 2016年12月13日 上午10:50:19
 * @title HosUserTheater.java
 *
 */
public class HosUserTheater {

	private Integer id;
	private Integer userId;// 用户id
	private String theaterId;// 更衣室编号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

}
