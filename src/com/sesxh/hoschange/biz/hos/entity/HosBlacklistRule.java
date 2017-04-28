package com.sesxh.hoschange.biz.hos.entity;

public class HosBlacklistRule {

	private Integer id;

	/**
	 * 类别(0灰/ 1黑名单)
	 */
	private String rosterType;

	/**
	 * 模式1 次数限制 模式2 时间段+次数
	 */
	private String rosterModel;

	/**
	 * 次数，频率
	 */
	private Integer sums;

	/**
	 * 时间段
	 */
	private String rosterTime;

	private String description;
	/**
	 * 模式开启
	 */
	private String enable;
	/**
	 * 查询条数
	 */
	private Integer latelyNum;
	
	private String oldRosterModel;
	
	
	
	public String getOldRosterModel() {
		return oldRosterModel;
	}

	public void setOldRosterModel(String oldRosterModel) {
		this.oldRosterModel = oldRosterModel;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Integer getLatelyNum() {
		return latelyNum;
	}

	public void setLatelyNum(Integer latelyNum) {
		this.latelyNum = latelyNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRosterType() {
		return rosterType;
	}

	public void setRosterType(String rosterType) {
		this.rosterType = rosterType;
	}

	public String getRosterModel() {
		return rosterModel;
	}

	public void setRosterModel(String rosterModel) {
		this.rosterModel = rosterModel;
	}

	public String getRosterTime() {
		return rosterTime;
	}

	public void setRosterTime(String rosterTime) {
		this.rosterTime = rosterTime;
	}

	public Integer getSums() {
		return sums;
	}

	public void setSums(Integer sums) {
		this.sums = sums;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
