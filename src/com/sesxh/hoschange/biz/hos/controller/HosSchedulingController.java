package com.sesxh.hoschange.biz.hos.controller;

import java.util.List;
import java.util.Map;

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
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.hos.service.HosSchedulingService;
import com.sesxh.hoschange.common.data.DataSet;

@Controller
@RequestMapping("/hosScheduling")
public class HosSchedulingController extends ZBaseController {

	@Autowired
	private HosSchedulingService hosSchedulingService;

	private static final String prefix = "hos/";

	@RequestMapping(value = "/queryHosSchedulingSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosSchedulingSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosSchedulingService.selectHosSchedulingSet(params);
		return lists;
	}

	@RequestMapping(value = {
			"/enterHosSchedulingAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosSchedulingAll() {
		return prefix + "pageScheduling";
	}

	@RequestMapping(value = "/queryHosSchedulingByTime", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosSchedulingByTime(String begintime, String endtime) throws BaseException {
		 List<HosScheduling> list= hosSchedulingService.queryHosSchedulingByTime(begintime, endtime);
		 return list;
	}

	@RequestMapping(value = {
			"/enterAddUserToSchedul" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddUserToSchedul(String id, Model model) {
		model.addAttribute("id", id);
		return prefix + "respSchedulingAdd";
	}

	@RequestMapping(value = "/addUserToSchedul", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object addUserToSchedul(String scheduleId, String[] ids) throws BaseException {
		hosSchedulingService.addUserToSchedul(scheduleId, ids);
		return ajaxDoneSuccess("添加成功");
	}

	@RequestMapping(value = {
			"/enterShowUserFromSchedul" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterShowUserFromSchedul(String id, Model model) {
		model.addAttribute("id", id);
		return prefix + "respShowUserFromSchedul";
	}

	@RequestMapping(value = "/showUserFromSchedul", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object showUserFromSchedul(String scheduleId) throws BaseException {
		DataSet lists = hosSchedulingService.showUserFromSchedul(scheduleId);
		return lists;
	}

	/*
	 * @RequestMapping(value = { "/enterAddHosScheduling"}, method =
	 * RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE) public String
	 * enterAddHosScheduling() { return prefix+"respSchedulingAdd"; }
	 * 
	 * @RequestMapping(value="/addHosScheduling",method=RequestMethod.POST,
	 * produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 * 
	 * @ResponseBody public BizMsg addHosScheduling(HosScheduling hosScheduling)
	 * throws BaseException{
	 * hosSchedulingService.insertHosScheduling(hosScheduling); return
	 * ajaxDoneSuccess("数据添加成功！"); }
	 * 
	 * @RequestMapping(value = { "/enterEditHosScheduling"}, method =
	 * RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE) public String
	 * enterEditHosScheduling() { return prefix+"respSchedulingEdit"; }
	 * 
	 * @RequestMapping(value="/updateHosScheduling",method=RequestMethod.POST,
	 * produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 * 
	 * @ResponseBody public BizMsg updateHosScheduling(HosScheduling
	 * hosScheduling) throws BaseException {
	 * hosSchedulingService.updateHosScheduling(hosScheduling); return
	 * ajaxDoneSuccess("数据修改成功！"); }
	 * 
	 * @RequestMapping(value="/deleteHosScheduling",method=RequestMethod.GET,
	 * produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 * 
	 * @ResponseBody public BizMsg deleteHosScheduling(Integer id) throws
	 * BaseException { hosSchedulingService.deleteHosSchedulingById(id); return
	 * ajaxDoneSuccess("数据删除成功！"); }
	 */
}
