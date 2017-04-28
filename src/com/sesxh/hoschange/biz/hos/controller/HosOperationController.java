package com.sesxh.hoschange.biz.hos.controller;

import java.util.List;
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
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.hos.service.HosOperationService;
import com.sesxh.hoschange.biz.hos.service.HosTheaterService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosOperation")
public class HosOperationController extends ZBaseController {

	@Autowired
	private HosOperationService hosOperationService;
	@Autowired
	private HosTheaterService hosTheaterService;

	private static final String prefix = "hos/";

	@RequestMapping(value = "/queryHosOperationSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosOperationSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosOperationService.selectHosOperationSet(params);
		return lists;
	}

	/**
	 * 根据尺码加载 分配，使用 回收
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectHosOperationSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectHosOperationSet(@RequestParam Map<String, Object> params) throws BaseException {
		List<Map<String, Object>> list = hosOperationService.selectHosOperationLists(params);
		Long total = Long.valueOf(list.size());
		DataSet ds = DataSet.newDS(total, list);
		return ds;
	}

	@RequestMapping(value = { "/enterOperationAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterOperationAll(HttpServletRequest request, HttpServletResponse response, Model model)
			throws BaseException {
		// 根据用户信息查询管辖的更衣室
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		List<HosTheater> listRoom = hosTheaterService.selectTheaterByUserId(sessionUser.getUserId());
		model.addAttribute("listRoom", listRoom);
		return prefix + "modeOne/pageOperationOne";
	}

	@RequestMapping(value = {
			"/enterAddHosOperation" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddHosOperation(Model model) {
		return prefix + "modeOne/respOperationOneAdd";
	}

	@RequestMapping(value = "/addHosOperation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addHosOperation(HosOperation hosOperation) throws BaseException {
		hosOperationService.insertHosOperationM1(hosOperation);
		return ajaxDoneSuccess("数据添加成功！");
	}

	/**
	 * （模式一跳转页面）修改手术服
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterEditHosOperationNum" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditHosOperationNum(Integer id, Integer clothSize, Integer yfp,Integer sumCount, Model model)
			throws BaseException {
		model.addAttribute("clothSize", clothSize);
		model.addAttribute("yfp", yfp);
		model.addAttribute("id", id);
		model.addAttribute("sumCount", sumCount);
		return prefix + "modeOne/respOperationOneEdit";
	}

	/**
	 * （模式一）修改手术服
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/updateHosOperationNum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosOperationNum(HosOperation hosOperation) throws BaseException {
		// HosOperation hosOp =
		// hosOperationService.selectHosOperationById(hosOperation.getId());
		hosOperationService.updateHosOperationM1(hosOperation);
		return ajaxDoneSuccess("数据修改成功！");
	}

	@RequestMapping(value = "/deleteHosOperation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteHosOperation(Integer id) throws BaseException {
		hosOperationService.deleteHosOperationById(id);
		return ajaxDoneSuccess("数据注销成功！");
	}

	/**
	 * 通过尺码删除手术服
	 * 
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/deleteHosOperationByClothSize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteHosOperationByClothSize(Integer id, String clothSize) throws BaseException {
		// hosWardrobeContainerService.updateContainerByClothSize(clothSize);
		hosOperationService.deleteHosOperationById(id);
		return ajaxDoneSuccess("数据注销成功！");
	}

	// ####################### 衣物管理员根据所在更衣室 加载手术衣 #########################//

}
