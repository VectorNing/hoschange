package com.sesxh.hoschange.biz.hos.entity;

public class HosPower {
	private String id;// 权限id
	private String userid;// 人员id
	private String begintime;// 权限有效开始时间
	private String endtime;// 权限有效结束时间
	private String operschedulid;// 手术排班Id
	private String synway;// 同步方式，0 自动；1 手动

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
