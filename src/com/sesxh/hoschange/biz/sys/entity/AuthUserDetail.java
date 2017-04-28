package com.sesxh.hoschange.biz.sys.entity;

import java.io.Serializable;

public class AuthUserDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String userName;// 姓名
	private String userId;
	private String sfzh;
	private String sex;// 性别
	private String telephone;
	private String num;
	private Integer shoesSize;// 鞋尺寸
	private Integer clothesSize;// 衣服尺寸
	private String shoesType;// 鞋尺寸String
	private String clothesType;// 衣服尺寸String
	private String note;
	private String enabled;
	private String personnelType;

	private String loginName;
	private String loginName2;
	private String password;
	private String roleId;
	private String type;
	private String department;// 科室
	private String position;
	private String jobnumber;// 工号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(Integer shoesSize) {
		this.shoesSize = shoesSize;
	}

	public Integer getClothesSize() {
		return clothesSize;
	}

	public void setClothesSize(Integer clothesSize) {
		this.clothesSize = clothesSize;
	}

	public String getShoesType() {
		return shoesType;
	}

	public void setShoesType(String shoesType) {
		this.shoesType = shoesType;
	}

	public String getClothesType() {
		return clothesType;
	}

	public void setClothesType(String clothesType) {
		this.clothesType = clothesType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginName2() {
		return loginName2;
	}

	public void setLoginName2(String loginName2) {
		this.loginName2 = loginName2;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

}
