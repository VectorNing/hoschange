package com.sesxh.hoschange.biz.sys.entity;

public class AuthMenu implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1000439271525843226L;
	/**
	 * 
	 */
	private Integer id;
	private String name;
	private String code;
	private Integer parentId;
	private Integer ordercol;
	private String url;
	private Integer permId;
	
	
	public Integer getPermId() {
		return permId;
	}
	public void setPermId(Integer permId) {
		this.permId = permId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getOrdercol() {
		return ordercol;
	}
	public void setOrdercol(Integer ordercol) {
		this.ordercol = ordercol;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

	
}
