package com.sesxh.hoschange.biz.hos.entity;
/**
 * 
* <p>Title:HosClothesPressContainer </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author Ning
* @date 2017年1月19日
 */
public class HosClothesPressContainer {
	private Integer id;
	private Integer userId;
	private String lockerNumber;
	private int ClothSize;
	private String state;
	private String clothesPressId;
	private String doorNumber;
	
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
	public String getLockerNumber() {
		return lockerNumber;
	}
	public void setLockerNumber(String lockerNumber) {
		this.lockerNumber = lockerNumber;
	}
	public int getClothSize() {
		return ClothSize;
	}
	public void setClothSize(int clothSize) {
		ClothSize = clothSize;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getClothesPressId() {
		return clothesPressId;
	}
	public void setClothesPressId(String clothPressId) {
		this.clothesPressId = clothPressId;
	}
	
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	@Override
	public String toString() {
		return "HosClothesPressContainer [id=" + id + ", userId=" + userId + ", lockerNumber=" + lockerNumber
				+ ", ClothSize=" + ClothSize + ", state=" + state + ", clothesPressId=" + clothesPressId
				+ ", doorNumber=" + doorNumber + "]";
	}

	
	
	
	
	
}
