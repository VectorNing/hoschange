package com.sesxh.hoschange.biz.sys.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;

@Controller
@RequestMapping("/authMenu")
public class AuthMenuController extends ZBaseController {
	@RequestMapping(value = "/loadMenu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String loadMenu(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		String menuJson = (String) request.getSession().getAttribute("menuJson");
		return menuJson;
	}
}
