package com.sesxh.hoschange.biz.hos.entity;

public class HosSterilizerContainer {

	private Integer id;
	private String steNumber;// 消毒柜编号
	private String lockerNumber;// 小柜编号
	private String doorNumber;// 小柜门编号
	private Integer shoesSize;// 尺码
	private String state;// 是否存放
	private Integer userId;
	private String yesOrNoLock;
	private String yesOrNoBinding;
	private Integer oldShoesSize;// 原尺码,用于业务
	private String shoesName;
	private String shoNumber;// 手术鞋编号 模式2
	private String userName;//

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(Integer shoesSize) {
		this.shoesSize = shoesSize;
	}

	public Integer getOldShoesSize() {
		return oldShoesSize;
	}

	public void setOldShoesSize(Integer oldShoesSize) {
		this.oldShoesSize = oldShoesSize;
	}

	public String getShoesName() {
		return shoesName;
	}

	public void setShoesName(String shoesName) {
		this.shoesName = shoesName;
	}

	public String getShoNumber() {
		return shoNumber;
	}

	public void setShoNumber(String shoNumber) {
		this.shoNumber = shoNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getYesOrNoLock() {
		return yesOrNoLock;
	}

	public void setYesOrNoLock(String yesOrNoLock) {
		this.yesOrNoLock = yesOrNoLock;
	}

	public String getYesOrNoBinding() {
		return yesOrNoBinding;
	}

	public void setYesOrNoBinding(String yesOrNoBinding) {
		this.yesOrNoBinding = yesOrNoBinding;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	@Override
	public String toString() {
		return "HosSterilizerContainer [id=" + id + ", steNumber=" + steNumber + ", lockerNumber=" + lockerNumber
				+ ", doorNumber=" + doorNumber + ", shoesSize=" + shoesSize + ", state=" + state + ", userId=" + userId
				+ ", yesOrNoLock=" + yesOrNoLock + ", yesOrNoBinding=" + yesOrNoBinding + ", oldShoesSize="
				+ oldShoesSize + ", shoesName=" + shoesName + ", shoNumber=" + shoNumber + ", userName=" + userName
				+ "]";
	}

}
