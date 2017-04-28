package com.sesxh.hoschange.biz.hos.entity;

import java.util.Date;

/**
 * 刷卡记录
 * 
 * @author cyf
 * @date 2016年11月30日 下午4:07:33
 * @title HosRecord.java
 *
 */
public class HosRecord {

	private Integer id;

	/**
	 * 
	 */
	private Integer userId;

	/**
	 * 0 签到，1签退
	 */
	private String sign;

	/**
	 * 签到时间
	 */
	private Date signInTime;

	/**
	 * 设备类别 1衣柜 2鞋柜
	 */
	private String deviceType;

	/**
	 * 设备编号
	 */
	private String deviceNumber;

	/**
	 * 是否回收
	 */
	private String isCallback;

	/**
	 * 回收时间
	 */
	private Date callbackTime;

	private String theaterNumber;

	private String lqcs;

	@Override
	public String toString() {
		return "HosRecord [id=" + id + ", userId=" + userId + ", sign=" + sign + ", signInTime=" + signInTime
				+ ", deviceType=" + deviceType + ", deviceNumber=" + deviceNumber + ", isCallback=" + isCallback
				+ ", callbackTime=" + callbackTime + ", theaterNumber=" + theaterNumber + ", lqcs=" + lqcs + "]";
	}

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getIsCallback() {
		return isCallback;
	}

	public void setIsCallback(String isCallback) {
		this.isCallback = isCallback;
	}

	public Date getCallbackTime() {
		return callbackTime;
	}

	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}

	public String getTheaterNumber() {
		return theaterNumber;
	}

	public void setTheaterNumber(String theaterNumber) {
		this.theaterNumber = theaterNumber;
	}

	public String getLqcs() {
		return lqcs;
	}

	public void setLqcs(String lqcs) {
		this.lqcs = lqcs;
	}

}
