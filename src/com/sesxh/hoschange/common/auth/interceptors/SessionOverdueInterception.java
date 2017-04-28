package com.sesxh.hoschange.common.auth.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.util.InterceptorUtil;
import com.sesxh.hoschange.common.util.SecurityUtils;
import com.sesxh.hoschange.common.util.ZWebUtil;

public class SessionOverdueInterception extends HandlerInterceptorAdapter {
	private String[] ignorUrls;

	public String[] getIgnorUrls() {
		return ignorUrls;
	}

	public void setIgnorUrls(String[] ignorUrls) {
		this.ignorUrls = ignorUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (InterceptorUtil.isInignorUrls(request, ignorUrls)) {
			return true;
		}
		SessionUser user = SecurityUtils.getSessionUser();
		if (user == null) {
			if (ZWebUtil.isAjax(request)) {
				BizMsg msg = new BizMsg();
				msg.setStatusCode(401);
				msg.setMessage("当前用户没有登陆系统，请登录");
				msg.setForwardUrl("");
				ZWebUtil.respJSON(response, msg);
			} else {
				ZWebUtil.redirect(request, response, "pages/loginError.html");
			}
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}