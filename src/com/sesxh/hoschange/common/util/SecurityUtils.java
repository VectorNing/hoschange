package com.sesxh.hoschange.common.util;

import javax.servlet.http.HttpSession;

import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.threadLocal.RequestThreadLocal;
import com.sesxh.hoschange.global.BizConst;

/**
 * 
 * @author zhaohutai
 *
 */
public class SecurityUtils {

	public static SessionUser getSessionUser() {
		return RequestThreadLocal.getSessionUser();
	}

	public static Integer getUserId() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getUserId();
	}

	public static String getLoginname() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getLoginName();
	}

	public static String getUserName() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getUserName();
	}

	public static String getUserEnabled() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getEnabled();
	}

	public static String getOrgCode() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getOrgCode();
	}

	public static String getUserOrgId() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getOrgId();
	}

	public static String getOrgName() {
		SessionUser user = RequestThreadLocal.getSessionUser();
		if (user == null) {
			return null;
		}
		return user.getOrgName();
	}

	public static HttpSession getHtttpSession() {
		HttpSession session = RequestThreadLocal.getSession();
		return session;
	}

	public static SessionUser getUserFromSession() {
		HttpSession session = getHtttpSession();
		if (session == null) {
			return null;
		}
		SessionUser user = (SessionUser) session.getAttribute(BizConst.SESSIONUSER);
		return user;
	}

}
