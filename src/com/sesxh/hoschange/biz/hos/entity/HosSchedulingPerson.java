package com.sesxh.hoschange.biz.hos.entity;

public class HosSchedulingPerson {
	private Integer id; // 权限id
	private Integer userid;// 人员Id
	private String operschedulid;// 手术排班Id
	private String synway;// 同步方式 0 自动；1手动

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getOperschedulid() {
		return operschedulid;
	}

	public void setOperschedulid(String operschedulid) {
		this.operschedulid = operschedulid;
	}

	public String getSynway() {
		return synway;
	}

	public void setSynway(String synway) {
		this.synway = synway;
	}

}
