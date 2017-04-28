package com.sesxh.hoschange.biz.hos.entity;

/**
 * 手术衣
 * 
 * @author wwb
 *
 */
public class HosOperation {

	private Integer id;
	private String number;//序号（手术衣编号）
	private String clothSize;//手术衣尺码
	private String state;//使用状态 1:使用中 0：未使用
	private Integer count;//总数量
	private String theNumber;//手术室编号
	
	

	private String updateCount;//业务过度字段
	private Integer oldCount;//业务过度字段
	private String notAllocatedCount;//业务过度字段
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getClothSize() {
		return clothSize;
	}

	public void setClothSize(String clothSize) {
		this.clothSize = clothSize;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getOldCount() {
		return oldCount;
	}

	public void setOldCount(Integer oldCount) {
		this.oldCount = oldCount;
	}

	public String getNotAllocatedCount() {
		return notAllocatedCount;
	}

	public void setNotAllocatedCount(String notAllocatedCount) {
		this.notAllocatedCount = notAllocatedCount;
	}


	public String getTheNumber() {
		return theNumber;
	}

	public void setTheNumber(String theNumber) {
		this.theNumber = theNumber;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(String updateCount) {
		this.updateCount = updateCount;
	}

}
