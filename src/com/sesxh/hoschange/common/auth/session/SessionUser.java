package com.sesxh.hoschange.common.auth.session;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @author zhaohutai
 *
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String userNum;
	private String loginName;
	private String loginName2;

	public String getLoginName2() {
		return loginName2;
	}

	public void setLoginName2(String loginName2) {
		this.loginName2 = loginName2;
	}

	private String userName;

	private String sessionId;

	private String loginLogId;// 登录时对应的id，退出时要更新这条记录

	private String logonTime;

	private String enabled;// 是否可用

	private String ip;// 用户IP

	private String orgId;
	private String orgCode;
	private String orgName;

	private boolean isSuperAdmin;

	private String superAdminRoleCode;//

	private Map<String, Object> userSessionInfoMap = Maps.newHashMap();// 用户自定义的属性

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(String loginLogId) {
		this.loginLogId = loginLogId;
	}

	public String getLogonTime() {
		return logonTime;
	}

	public void setLogonTime(String logonTime) {
		this.logonTime = logonTime;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Map<String, Object> getUserSessionInfoMap() {
		return userSessionInfoMap;
	}

	public void setUserSessionInfoMap(Map<String, Object> userSessionInfoMap) {
		this.userSessionInfoMap = userSessionInfoMap;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public String getSuperAdminRoleCode() {
		return superAdminRoleCode;
	}

	public void setSuperAdminRoleCode(String superAdminRoleCode) {
		this.superAdminRoleCode = superAdminRoleCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SessionUser [userId=" + userId + ", userNum=" + userNum + ", loginName=" + loginName + ", loginName2="
				+ loginName2 + ", userName=" + userName + ", sessionId=" + sessionId + ", loginLogId=" + loginLogId
				+ ", logonTime=" + logonTime + ", enabled=" + enabled + ", ip=" + ip + ", orgId=" + orgId + ", orgCode="
				+ orgCode + ", orgName=" + orgName + ", isSuperAdmin=" + isSuperAdmin + ", superAdminRoleCode="
				+ superAdminRoleCode + ", userSessionInfoMap=" + userSessionInfoMap + "]";
	}

}
