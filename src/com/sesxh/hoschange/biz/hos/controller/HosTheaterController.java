package com.sesxh.hoschange.biz.hos.controller;

import java.util.Arrays;
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
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.hos.service.HosTheaterService;
import com.sesxh.hoschange.biz.sys.service.SysConfigService;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosTheater")
public class HosTheaterController extends ZBaseController {

	@Autowired
	private HosTheaterService hosTheaterService;
	@Autowired
	private SysConfigService sysConfigService;

	private static final String prefix = "hos/";

	@RequestMapping(value = "/queryHosTheaterSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosTheaterSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosTheaterService.selectHosTheaterSet(params);
		return lists;
	}

	@RequestMapping(value = {
			"/enterHosTheaterAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosTheaterAll() {
		return prefix + "pageTheater";
	}

	@RequestMapping(value = "/selectTheByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectTheByNumber(String number, Integer id) throws BaseException {
		Long count = hosTheaterService.selectTheByNumber(number, id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	@RequestMapping(value = {
			"/enterAddHosTheater" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddHosTheater() {
		return prefix + "respTheaterAdd";
	}

	@RequestMapping(value = "/addHosTheater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addHosTheater(HosTheater hosTheater) throws BaseException {
		hosTheaterService.insertHosTheater(hosTheater);
		return ajaxDoneSuccess("数据添加成功！");
	}

	@RequestMapping(value = {
			"/enterEditHosTheater" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditHosTheater(Model model, Integer id) throws BaseException {
		HosTheater hosTheater = hosTheaterService.selectTheaterById(id);
		model.addAttribute("hosTheater", hosTheater);
		return prefix + "respTheaterEdit";
	}

	@RequestMapping(value = "/updateHosTheater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosTheater(HosTheater hosTheater) throws BaseException {
		hosTheaterService.updateHosTheater(hosTheater);
		return ajaxDoneSuccess("数据修改成功！");
	}

	@RequestMapping(value = "/deleteHosTheater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteHosTheater(Integer id, String number) throws BaseException {
		hosTheaterService.deleteHosTheaterById(id, number);
		return ajaxDoneSuccess("数据删除成功！");
	}

	@RequestMapping(value = "/selectDeviceByUserId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg selectDeviceByUserId(Integer id, String number, String size) throws BaseException {
		Map<String, Object> map = hosTheaterService.receiveDeviceShoByUserIdM1(id, number, size);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);
		return biz;
	}

	@RequestMapping(value = "/loadTheByNumberForDict", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadTheByNumberForDict() throws BaseException {
		List<ClassConvertDict> list = hosTheaterService.loadTheNumberConvertDict();
		return list;
	}

	// ############################ 分配更衣室
	// 开始#######################################//

	@RequestMapping(value = "enterAssignUserToTheater", method = RequestMethod.GET)
	public String enterAssignUserToTheater(Model model, Integer id) throws BaseException {
		model.addAttribute("theaterId", id);
		return prefix + "/respTheaterUserAssign";
	}

	@RequestMapping(value = "/assignUserToTheater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg assignUserToTheater(Integer theaterId, Integer[] userIds) throws BaseException {
		hosTheaterService.assignUserToTheater(theaterId, Arrays.asList(userIds));
		return ajaxDoneSuccess("添加用户成功！");
	}

	@RequestMapping(value = "/removeTheaterFromUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg removeTheaterFromUser(Integer theaterId, Integer[] userIds) throws BaseException {
		hosTheaterService.removeTheaterFromUser(theaterId, Arrays.asList(userIds));
		return ajaxDoneSuccess("移除用户成功！");

	}

	/**
	 * 加载用户更衣室的数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/loadTheaterUserByTheaterId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadTheaterUserByTheaterId(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosTheaterService.loadTheaterUserByTheaterId(params);
		return lists;
	}

	// ############################ 分配更衣室 结束
	// #######################################//
	/**
	 * 更衣室设备监控
	 * 
	 * @Title: enterHosDeviceMonitor
	 * @author Ning
	 * @throws BaseException
	 * @Description:
	 * @data:2017年1月14日
	 */
	@RequestMapping(value = {
			"/enterHosTheaterDeviceMonitor" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosTheaterDeviceMonitor(Model model) throws BaseException {
		String config = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();
		model.addAttribute("isOrNoSterilizer", config);
		return prefix + "pageTheaterDeviceMonitor";
	}
}
