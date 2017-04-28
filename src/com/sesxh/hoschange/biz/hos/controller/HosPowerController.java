package com.sesxh.hoschange.biz.hos.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.service.HosPowerService;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;

@Controller
@RequestMapping("/hosPower")
public class HosPowerController extends ZBaseController {
	@Autowired
	private HosPowerService hosPowerService;

	private static final String prefix = "hos/";

	@RequestMapping(value = {"/enterHosPowerAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosPowerAll(HttpServletRequest request, HttpServletResponse response, Model model)
			throws BaseException {
		return prefix + "pagePower";
	}
	
	@RequestMapping(value = "/queryHosPowerSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosPowerSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosPowerService.queryHosPowerSet(params);
		return lists;
	}
	
	@RequestMapping(value = "/addFollower", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addFollower(String userId,String schedulid) throws BaseException {
		String msg=hosPowerService.addFollower(userId, schedulid);
		return ajaxDoneSuccess(msg);
	}
	
	@RequestMapping(value = "/addTemporaries", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addTemporaries(String userId,String endtime,String begintime) throws BaseException {
		String msg=hosPowerService.addTemporaries(userId, endtime, begintime);
		return ajaxDoneSuccess(msg);
	}
}
