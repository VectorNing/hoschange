package com.sesxh.hoschange.global;

import com.sesxh.frame.base.BaseGlobalNames;

/**
 * 框架全局变量类
 */
public class HoschangeGlobalNames extends BaseGlobalNames {
	/**
	 * 缓存登录用户对象的参数名称
	 */
	public static final String logonUser = "logon_user";
	public static final String logonUserName = "logon_user_name";	
	
	private final String appVersion = "3.00.00";// 应用系统版本号
	private final String frameVersion = "3.00.00";// 系统框架版本版本号
	

	public final String pageNumber = "pageNumber"; // 当前页
	public final String splitPage = "USER";
	public final String navbar = "navbar";

	public String getAppVersion() {
		return appVersion;
	}

	public String getFrameVersion() {
		return frameVersion;
	}
}
