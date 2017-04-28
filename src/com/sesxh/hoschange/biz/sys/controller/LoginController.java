package com.sesxh.hoschange.biz.sys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.util.ZWebUtil;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/")
public class LoginController extends ZBaseController {

	@RequestMapping(value = "/core", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public void core(Model model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException, IOException, ServletException {

		ZWebUtil.redirect(request, response, "/index.jsp");
	}

	@RequestMapping(value = { "/", "/logout" })
	public Object logout(HttpServletRequest request) {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		if (user == null) {
			return "redirect:/login.jsp";
		}
		request.getSession().removeAttribute(BizConst.SESSIONUSER);
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}

}
