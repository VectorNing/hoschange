package com.sesxh.hoschange.biz.hos.entity;

import java.util.List;
import java.util.Map;

/**
 * 消毒鞋柜
 * 
 * @author wwb
 *
 */
public class HosSterilizer {
	private Integer id;
	private String state;// 使用状态
	private String theaterNumber;// 手术室编号(外键)
	private String number;// 编号
	private String description;// 描述
	private Integer rows;// 行数
	private Integer columns;// 列数
	private Integer total;// 小柜总数
	private Integer startDoor;// 起始门号
	private Integer endDoor;// 终止门号

	private String oldNumber;// 原编号 用于业务

	private List<Map<String, Object>> sizes;// 消毒柜下的尺码，用于业务
	private List<HosSterilizerContainer> containerList;// 消毒柜下小柜信息，

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

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public String getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}

	public List<Map<String, Object>> getSizes() {
		return sizes;
	}

	public void setSizes(List<Map<String, Object>> sizes) {
		this.sizes = sizes;
	}

	public List<HosSterilizerContainer> getContainerList() {
		return containerList;
	}

	public void setContainerList(List<HosSterilizerContainer> containerList) {
		this.containerList = containerList;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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

	@Override
	public String toString() {
		return "HosSterilizer [id=" + id + ", state=" + state + ", theaterNumber=" + theaterNumber + ", number="
				+ number + ", description=" + description + ", rows=" + rows + ", columns=" + columns + ", total="
				+ total + ", startDoor=" + startDoor + ", endDoor=" + endDoor + ", oldNumber=" + oldNumber + ", sizes="
				+ sizes + ", containerList=" + containerList + "]";
	}
}
