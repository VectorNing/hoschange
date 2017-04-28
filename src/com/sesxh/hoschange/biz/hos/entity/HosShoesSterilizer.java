package com.sesxh.hoschange.biz.hos.entity;

/**
 * 消毒柜与消毒鞋中间表
 * 
 * @author wwb
 *
 */
public class HosShoesSterilizer {
	private Integer id;
	private String steNumber;// 鞋柜编号
	private String lockerNumber;// 小柜编号
	private Integer shoSize;// 手术鞋尺码
	private Integer count;// 剩余数量
	private Integer shoesId;// 手术鞋主键

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSteNumber() {
		return steNumber;
	}

	public void setSteNumber(String steNumber) {
		this.steNumber = steNumber;
	}

	public String getLockerNumber() {
		return lockerNumber;
	}

	public void setLockerNumber(String lockerNumber) {
		this.lockerNumber = lockerNumber;
	}

	public Integer getShoSize() {
		return shoSize;
	}

	public void setShoSize(Integer shoSize) {
		this.shoSize = shoSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getShoesId() {
		return shoesId;
	}

	public void setShoesId(Integer shoesId) {
		this.shoesId = shoesId;
	}

}
