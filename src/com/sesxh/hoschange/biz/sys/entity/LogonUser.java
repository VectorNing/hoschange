package com.sesxh.hoschange.biz.sys.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 登录用户信息类
 */
public class LogonUser implements Serializable {

	private static final long serialVersionUID = -2083071545436364551L;

	private String userId;
	private String userNo;
	private String userName;
	private String isValid;// 用户是否有效
	private String password;
	private String email;
	private String ip;
	private String logonTime;
	private String orgnId;
	private String orgnNo;
	private String orgnName;
	private String sfcjgly;// 是否超级管理员
	private HashMap<String, Object> userMap;// 用户自定义的属性
	private Long id;// 用户当前登录的id，用户记录登录日志

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogonTime() {
		return logonTime;
	}

	public void setLogonTime(String logonTime) {
		this.logonTime = logonTime;
	}

	public String getOrgnId() {
		return orgnId;
	}

	public void setOrgnId(String orgnId) {
		this.orgnId = orgnId;
	}

	public String getOrgnNo() {
		return orgnNo;
	}

	public void setOrgnNo(String orgnNo) {
		this.orgnNo = orgnNo;
	}

	public String getOrgnName() {
		return orgnName;
	}

	public void setOrgnName(String orgnName) {
		this.orgnName = orgnName;
	}

	public HashMap<String, Object> getUserMap() {
		return userMap;
	}

	public void setUserMap(HashMap<String, Object> userMap) {
		this.userMap = userMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSfcjgly() {
		return sfcjgly;
	}

	public void setSfcjgly(String sfcjgly) {
		this.sfcjgly = sfcjgly;
	}

}