package com.sesxh.hoschange.biz.hos.entity;

import java.util.Date;

/**
 * @title : HosRecoveryGoods.java
 * @author 作者 E-mail: wwb
 * @date 创建时间：2016年10月21日 下午2:56:38
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class HosRecoveryGoods {

	private Integer id;
	private Integer userId;// 用户id
	private String theNumber;// 手术室编号
	private String type;
	private Integer size;// 尺码
	private String state;// 是否签退 0:未签退 1:已签退
	private Date recoveryTime;// 回收时间
	private Integer recordId;
	private String deviceNumber;
	private String goodsNumber;// 物品编号(模式2)

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

	public String getTheNumber() {
		return theNumber;
	}

	public void setTheNumber(String theNumber) {
		this.theNumber = theNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getRecoveryTime() {
		return recoveryTime;
	}

	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@Override
	public String toString() {
		return "HosRecoveryGoods [id=" + id + ", userId=" + userId + ", theNumber=" + theNumber + ", type=" + type
				+ ", size=" + size + ", state=" + state + ", recoveryTime=" + recoveryTime + ", recordId=" + recordId
				+ ", deviceNumber=" + deviceNumber + ", goodsNumber=" + goodsNumber + "]";
	}
	

}
