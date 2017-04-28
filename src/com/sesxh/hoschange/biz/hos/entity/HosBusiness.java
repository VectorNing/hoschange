package com.sesxh.hoschange.biz.hos.entity;

import java.util.Date;

/**
 * 业务操作日志模块
 * @author root
 *
 */
public class HosBusiness {

	private Integer id;//
	private Integer userId;//用户id
	private String deviceType;//设备类别
	private Date operateTime;//操作时间
	private String deviceNumber;//设备编号
	private String conNumber;//柜子编号
	private Integer size;//尺码
	private String operationType;//操作类型
	private String description;//说明

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

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getConNumber() {
		return conNumber;
	}

	public void setConNumber(String conNumber) {
		this.conNumber = conNumber;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
