package com.sesxh.hoschange.common.threadLocal;

import javax.servlet.http.HttpSession;

import com.sesxh.hoschange.common.auth.session.SessionUser;

public class RequestThreadLocal {

	private static final ThreadLocal<SessionUser> contextHolder = new ThreadLocal<SessionUser>();
	private static final ThreadLocal<HttpSession> contextSessonHolder = new ThreadLocal<HttpSession>();

	public static void setSessionUser(SessionUser sessionUser) {
		contextHolder.set(sessionUser);
	}

	public static SessionUser getSessionUser() {
		return contextHolder.get();
	}

	public static void clearCurrentReqestInfo() {
		contextHolder.remove();
	}

	public static void setSession(HttpSession session) {
		contextSessonHolder.set(session);
	}

	public static HttpSession getSession() {
		return contextSessonHolder.get();
	}

	public static void clearCurrentSeesionReqestInfo() {
		contextSessonHolder.remove();
	}
}
