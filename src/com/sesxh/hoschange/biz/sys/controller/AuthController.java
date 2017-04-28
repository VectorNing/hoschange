package com.sesxh.hoschange.biz.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.service.AuthMenuService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.util.MD5Util;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.common.util.ZWebUtil;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/auth")
public class AuthController extends ZBaseController {

	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private AuthMenuService authMenuService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, Model model, String loginname,
			String password, String mode) throws BaseException {
		AuthUser authUser = null;
		if ("1".equals(mode)) {// 用户名密码登陆
			String salt = authUserService.findSaltForLoginName(loginname);
			password = MD5Util.encrypt(password, salt);
			authUser = authUserService.findByLoginNameAndPas(loginname, password);
			if (authUser == null) {
				return ajaxBizError("用户名或密码错误");
			}
			if (authUser.getEnabled().equals(BizConst.ENABLED.DISABLE)) {
				return ajaxBizError("用户已禁用，请联系相关人员");
			}
		} else if ("2".equals(mode)) {// 刷卡登陆
			authUser = authUserService.findByLoginName(loginname);
			if (authUser == null) {
				authUser = authUserService.findByLoginName2(loginname);
			}
			if (authUser == null) {
				return ajaxBizError("登陆信息错误，请联系管理员");
			}
		} else {
			return ajaxBizError("登陆模式错误");
		}
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		SessionUser sessionUser = authUserService.loadUserInfor(authUser.getId());
		sessionUser.setSessionId(sessionId);
		session.setAttribute(BizConst.SESSIONUSER, sessionUser);
		session.setAttribute("authUser", authUser);
		initUserSession(request);
		return ajaxDoneSuccess("登陆成功");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws BaseException, IOException, ServletException {
		HttpSession session = request.getSession();
		session.invalidate();
		ZWebUtil.redirect(request, response, "/login.jsp");
	}

	@RequestMapping(value = "/core", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public void core(Model model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException, IOException, ServletException {
		ZWebUtil.redirect(request, response, "/index.jsp");
	}

	@RequestMapping(value = "/logout")
	public Object logout(HttpServletRequest request) {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		if (user == null) {
			return "redirect:/login.jsp";
		}
		request.getSession().removeAttribute(BizConst.SESSIONUSER);
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterUserPassWord", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public Object enterUserPassWord(HttpServletRequest request, Model model) throws BaseException {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		if (user == null) {
		}
		model.addAttribute("user", user);
		return "auth/respUserPassWord";
	}

	/**
	 * 查询原密码
	 * 
	 * @param password
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectUserPassWord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectUserPassWord(HttpServletRequest request, String loginname, String password)
			throws BaseException {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		// if(user==null){
		// return "redirect:/login.jsp";
		// }
		loginname = user.getLoginName();// 登录名
		ZAssert.hasText(loginname, "没有获取到用户信息或登录超时！");
		String salt = authUserService.findSaltForLoginName(loginname);
		password = MD5Util.encrypt(password, salt);
		AuthUser authUser = authUserService.findByLoginNameAndPas(loginname, password);
		BizMsg biz = BizMsg.newMSG(BizConst.SC.SC_200);
		if (authUser == null) {
			biz.setMessage("原密码输入有误");
			biz.setStrData("1");
		} else {
			biz.setMessage("");
			biz.setStrData("-1");
		}
		return biz;
	}

	@RequestMapping(value = "/changePasswordSalt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object changePasswordSalt(AuthUser user) throws BaseException {
		authUserService.changePasswordSalt(user);
		return ajaxDoneSuccess("修改成功");
	}

	/**
	 * 
	 * @Title: initUserSession
	 * @author Ning
	 * @Description:
	 * @data:2017年1月6日
	 */
	private void initUserSession(HttpServletRequest request) throws BaseException {
		List<AuthMenu> menuList = new ArrayList<AuthMenu>();
		JSONArray jsonArray = new JSONArray();
		String menuJson = null;
		AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
		String isSuperAdmin = authUser.getSfcjgly();
		// 超级管理员加载所有菜单
		if (BizConst.SFCJGLY.TURE.equals(isSuperAdmin)) {
			menuList = authMenuService.loadAllAuthMenuList();
			for (AuthMenu authMenu : menuList) {
				jsonArray.add(authMenu);
			}
			menuJson = jsonArray.toString();
			request.getSession().setAttribute("menuJson", menuJson);
		} else {// 其它人员根据角色权限加载相应的菜单
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
			Integer userId = sessionUser.getUserId();
			menuList = authMenuService.selectAuthMenuListByUserId(userId);
			if (menuList == null || menuList.size() == 0) {// 没有角色权限的禁止登录系统
				throw new BusinessException("您没有权限登录系统");
			}
			for (AuthMenu authMenu : menuList) {
				jsonArray.add(authMenu);
			}
			menuJson = jsonArray.toString();
			request.getSession().setAttribute("menuJson", menuJson);
		}
	}
}
