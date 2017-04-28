package com.sesxh.hoschange.biz.hos.entity;

/**
 * 
 * <p>
 * Title:HosClothesPress
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ning
 * @date 2017年1月19日
 */
public class HosClothesPress {
	private String id;
	private String number;
	private String state;
	private String theaterNumber;
	private String description;
	private Integer roomId;
	private String oldTheaterNumber;
	private String yesOrNoLock;
	private String yesOrNoBinding;
	private String userName;
	private Integer startDoor;
	private Integer endDoor;
	private Integer total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTheaterNumber() {
		return theaterNumber;
	}

	public void setTheaterNumber(String theaterNumber) {
		this.theaterNumber = theaterNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getOldTheaterNumber() {
		return oldTheaterNumber;
	}

	public void setOldTheaterNumber(String oldTheaterNumber) {
		this.oldTheaterNumber = oldTheaterNumber;
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

	public Integer getStartDoor() {
		return startDoor;
	}

	public void setStartDoor(Integer startDoor) {
		this.startDoor = startDoor;
	}

	public Integer getEndDoor() {
		return endDoor;
	}

	public void setEndDoor(Integer endDoor) {
		this.endDoor = endDoor;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "HosClothesPress [id=" + id + ", number=" + number + ", total=" + total + ", endDoor=" + endDoor
				+ ", state=" + state + ", theaterNumber=" + theaterNumber + ", description=" + description + ", roomId="
				+ roomId + ", oldTheaterNumber=" + oldTheaterNumber + ", yesOrNoLock=" + yesOrNoLock
				+ ", yesOrNoBinding=" + yesOrNoBinding + ", userName=" + userName + ", startDoor=" + startDoor + "]";
	}

}
