package com.sesxh.hoschange.biz.hos.entity;

import java.util.List;

/**
 * 手术服衣柜
 * 
 * @author wwb
 *
 */
public class HosWardrobe {
	private Integer id;
	private String state;// 使用状态
	private Integer traySum;// 托盘数量
	private String model;// x型号
	private Integer total;// 总容量
	private String theaterNumber;// 手术室id
	private String number;// 编号
	private String description;// 描述
	private String enabled;// 是否暂停服务(1 不暂停 0 暂停) 目前用于暂停服务锁屏
	private Integer roomId;

	private String oldNumber;// 原编号
	private String oldTheaterNumber;// 原更衣室
	private List<HosWardrobeContainer> containerList;// 衣柜下小柜，

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTheaterNumber() {
		return theaterNumber;
	}

	public void setTheaterNumber(String theaterNumber) {
		this.theaterNumber = theaterNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTraySum() {
		return traySum;
	}

	public void setTraySum(Integer traySum) {
		this.traySum = traySum;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}

	public List<HosWardrobeContainer> getContainerList() {
		return containerList;
	}

	public void setContainerList(List<HosWardrobeContainer> containerList) {
		this.containerList = containerList;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
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

	@Override
	public String toString() {
		return "HosWardrobe [id=" + id + ", state=" + state + ", traySum=" + traySum + ", model=" + model + ", total="
				+ total + ", theaterNumber=" + theaterNumber + ", number=" + number + ", description=" + description
				+ ", enabled=" + enabled + ", roomId=" + roomId + ", oldNumber=" + oldNumber + ", oldTheaterNumber="
				+ oldTheaterNumber + ", containerList=" + containerList + "]";
	}

}
