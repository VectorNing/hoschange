package com.sesxh.hoschange.biz.hos.entity;

/**
 * 手术鞋
 * 
 * @author wwb
 *
 */
public class HosShoes {

	private Integer id;
	private Integer count;// 总数量
	private String state;// 使用状态 0 未使用 1 使用中
	private Integer shoeSize;// 鞋尺码
	private String number;// 手术鞋编号

	private Integer oldCount;// 原总数量
	
	private String shoeSizeName;
	
	private String theaterNumber;
	
	
	
	
	public String getTheaterNumber() {
		return theaterNumber;
	}

	public void setTheaterNumber(String theaterNumber) {
		this.theaterNumber = theaterNumber;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(Integer shoeSize) {
		this.shoeSize = shoeSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOldCount() {
		return oldCount;
	}

	public void setOldCount(Integer oldCount) {
		this.oldCount = oldCount;
	}

	public String getShoeSizeName() {
		return shoeSizeName;
	}

	public void setShoeSizeName(String shoeSizeName) {
		this.shoeSizeName = shoeSizeName;
	}

}
