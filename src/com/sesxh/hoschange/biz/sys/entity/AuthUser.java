package com.sesxh.hoschange.biz.sys.entity;

import java.io.Serializable;

public class AuthUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String loginName;
	private String loginName2;
	private String password;
	private String sfzh;
	private String salt;
	private String defaultRole;
	private String enabled;
	private String accountExpired;
	private String accountLocked;
	private String credentialsExpired;
	private String description;
	private String lastLoginTime;
	private String jobnumber;
	private String sfcjgly;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(String accountExpired) {
		this.accountExpired = accountExpired;
	}

	public String getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}

	public String getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(String credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSfcjgly() {
		return sfcjgly;
	}

	public void setSfcjgly(String sfcjgly) {
		this.sfcjgly = sfcjgly;
	}

	public String getLoginName2() {
		return loginName2;
	}

	public void setLoginName2(String loginName2) {
		this.loginName2 = loginName2;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

}
