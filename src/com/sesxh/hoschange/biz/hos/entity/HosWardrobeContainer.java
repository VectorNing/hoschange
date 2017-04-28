package com.sesxh.hoschange.biz.hos.entity;

/**
 * 衣柜详情
 * 
 * @author root
 *
 */
public class HosWardrobeContainer {

	private Integer id;
	private String warNumber;// 衣柜编号
	private String trayNumber;// 托盘编码
	private Integer trayTotal;// 托盘容量
	private Integer opeSize;// 衣服尺码
	
	private String opeSizeName;//业务过度字段
	
	private Integer alloutCount;// 已分配数量

	private String count;// 业务过度字段

	public Integer getId() {
		return id;
	}

	public String getOpeSizeName() {
		return opeSizeName;
	}

	public void setOpeSizeName(String opeSizeName) {
		this.opeSizeName = opeSizeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWarNumber() {
		return warNumber;
	}

	public void setWarNumber(String warNumber) {
		this.warNumber = warNumber;
	}

	public String getTrayNumber() {
		return trayNumber;
	}

	public void setTrayNumber(String trayNumber) {
		this.trayNumber = trayNumber;
	}

	public Integer getOpeSize() {
		return opeSize;
	}

	public void setOpeSize(Integer opeSize) {
		this.opeSize = opeSize;
	}

	public Integer getTrayTotal() {
		return trayTotal;
	}

	public void setTrayTotal(Integer trayTotal) {
		this.trayTotal = trayTotal;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Integer getAlloutCount() {
		return alloutCount;
	}

	public void setAlloutCount(Integer alloutCount) {
		this.alloutCount = alloutCount;
	}

}
