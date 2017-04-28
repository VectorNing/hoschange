package com.sesxh.hoschange.common.auth.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.threadLocal.RequestThreadLocal;
import com.sesxh.hoschange.common.util.InterceptorUtil;
import com.sesxh.hoschange.global.BizConst;

/**
 * 
 * @author zhaohutai
 *
 */
public class RequestThreadLocalInterceptor extends HandlerInterceptorAdapter {
	private String[] ignorUrls;//

	public String[] getIgnorUrls() {
		return ignorUrls;
	}

	public void setIgnorUrls(String[] ignorUrls) {
		this.ignorUrls = ignorUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		try {
			if (InterceptorUtil.isInignorUrls(request, ignorUrls)) {
				super.afterCompletion(request, response, handler, ex);
				return;
			}
			RequestThreadLocal.clearCurrentReqestInfo();
			RequestThreadLocal.clearCurrentSeesionReqestInfo();
		} catch (Exception e) {
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (InterceptorUtil.isInignorUrls(request, ignorUrls)) {
			return true;
		}
		HttpSession sesson = request.getSession();
		SessionUser user = (SessionUser) sesson.getAttribute(BizConst.SESSIONUSER);
		if (user == null) {
			return true;
		}
		RequestThreadLocal.setSessionUser(user);
		RequestThreadLocal.setSession(sesson);
		return true;

	}
}
