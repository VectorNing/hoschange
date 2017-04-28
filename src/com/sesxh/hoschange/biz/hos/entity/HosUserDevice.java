package com.sesxh.hoschange.biz.hos.entity;

/**
 * 用户设备对应表
 * 
 * @author wwb
 *
 */
public class HosUserDevice {
	private Integer id;
	private Integer userId;// 用户id
	private String type;// 设备类型 0消毒鞋柜 1衣柜
	private String deviceNumber;// 设备编号(衣柜，消毒柜编号)
	private String enabled;// 是否有效

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
