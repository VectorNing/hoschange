package com.sesxh.hoschange.biz.hos.entity;

import java.util.Date;

/**
 * 黑名单灰名单
 * 
 * @author cyf
 * @date 2016年11月30日 下午4:07:16
 * @title HosBlacklist.java
 *
 */
public class HosBlacklist {

	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 创建时间
	 */
	private Date creatorTime;
	/**
	 * 类别(灰/黑名单) 0灰 1黑
	 */
	private String type;

	/**
	 * 是否有效(1 有效，0 无效)
	 */
	private String enabled;

	private Date outTime;

	private Integer operationUserId;

	/**
	 * 是否显示(1 显示，0 不显示)
	 */
	private String display;

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

	public Date getCreatorTime() {
		return creatorTime;
	}

	public void setCreatorTime(Date creatorTime) {
		this.creatorTime = creatorTime;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Integer getOperationUserId() {
		return operationUserId;
	}

	public void setOperationUserId(Integer operationUserId) {
		this.operationUserId = operationUserId;
	}

	@Override
	public String toString() {
		return "HosBlacklist [id=" + id + ", userId=" + userId + ", creatorTime=" + creatorTime + ", type=" + type
				+ ", enabled=" + enabled + ",display=" + display + ", outTime=" + outTime + ", operationUserId="
				+ operationUserId + "]";
	}

}
