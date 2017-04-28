package com.sesxh.hoschange.biz.hos.controller;

import java.util.HashMap;
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

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
import com.sesxh.hoschange.biz.hos.service.HosClothesPressService;
import com.sesxh.hoschange.biz.hos.service.HosRoomService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosClothesPress")
public class HosClothesPressController extends ZBaseController {
	@Autowired
	private HosClothesPressService hosClothesService;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private HosRoomService hosRoomService;
	private static final String prefix = "hos/";

	/**
	 * 跳转衣柜管理页面
	 * 
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = { "/enterClothesPress" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterClothesPress(Model model) throws BaseException {
		return prefix + "pageClothesPress";
	}

	/**
	 * 查询所有衣柜信息
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/queryClothesPress" }, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String queryClothesPress(@RequestParam Map<String, Object> params) throws BaseException {
		Map<String, Object> map = hosClothesService.queryClothesPressAll(params);
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 添加衣柜页面跳转
	 * 
	 * @Title: enterAddHosClothesPress
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:String
	 * @throws:
	 */
	@RequestMapping(value = {
			"/enterAddHosClothesPress" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddHosClothesPress(Model model) throws BaseException {
		return prefix + "respClothesPressAdd";
	}

	@RequestMapping(value = {
			"/addClothesPress" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object addClothesPress(Model model, HosClothesPress hosClothesPress) throws BaseException {
		hosClothesService.addClothesPress(hosClothesPress);
		return ajaxDoneSuccess("数据添加成功！");
	}

	/**
	 * 修改衣柜页面跳转
	 * 
	 * @author xujialong
	 * @data 2017年1月19日
	 * @param model
	 * @return String
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterEditHosClothesPress" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditHosClothesPress(String id, Model model) throws BaseException {
		HosClothesPress hosClothesPress = hosClothesService.queryClothesPressById(id);
		HosRoom hosRoom = hosRoomService.selectHosRoomByRoomId(hosClothesPress.getRoomId());
		model.addAttribute("hosClothesPress", hosClothesPress);
		model.addAttribute("hosRoom", hosRoom);
		return prefix + "respClothesPressEdit";
	}

	/**
	 * 修改衣柜信息
	 * 
	 * @author xujialong
	 * @param model
	 * @param hosClothesPress
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"updateClothesPress" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object updateClothesPress(Model model, HosClothesPress hosClothesPress) throws BaseException {
		hosClothesService.updateHosClothesPress(hosClothesPress);
		return ajaxDoneSuccess("数据修改成功!");
	}

	/**
	 * 根据衣柜id查询衣柜使用情况
	 * 
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"selectClothesFromClothesPressById" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectClothesFromClothesPressById(String id) throws BaseException {
		Long count = hosClothesService.selectClothesFromClothesPressById(id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	/**
	 * 根据衣柜id删除衣柜
	 * 
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"deleteClothesPressById" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object deleteClothesPressById(String id) throws BaseException {
		hosClothesService.deleteClothesPressById(id);
		return ajaxDoneSuccess("注销成功！");
	}

	/**
	 * 根据用户id和衣柜编号查询是否具有打开小柜的权限
	 * 
	 * @author xujialong
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"selectClothesPressByUserIdAndNumber" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectClothesPressByUserIdAndNumber(String ClothesPressNum, HttpServletRequest httpServletRequest)
			throws BaseException {
		SessionUser sessionUser = (SessionUser) httpServletRequest.getSession().getAttribute(BizConst.SESSIONUSER);
		Map<String, Object> map = hosClothesService.selectClothesPressByUserIdAndNumber(sessionUser.getUserId(),
				ClothesPressNum);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setData(map);
		return biz;
	}

	/**
	 * 查询编号是否重复
	 * 
	 * @author xujialong
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"selectClothesPressByNumber" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectClothesPressByNumber(String id, String number) throws BaseException {
		Long count = hosClothesService.selectClothesPressByNumber(id, number);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	/**
	 * 给储物柜小柜绑定医护人员页面跳转
	 * 
	 * @author xujialong
	 * @param steNumber
	 * @param rows
	 * @param columns
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterSetClothesPressAndUser" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSetClothesPressAndUser(String steNumber, Integer total, Model model) throws BaseException {
		List<Map<String, Object>> rooms = Lists.newArrayList();
		List<Map<String, Object>> hosClothesPress = hosClothesService.selectClothesPressBySteNumber(steNumber);
		Map<String, Object> map = Maps.newHashMap();
		double columns = 5.0;
		map.put("steNumber", steNumber);
		int rows = (int) Math.ceil(total / columns);
		map.put("rows", rows);
		map.put("columns", (int) columns);
		map.put("total", total);
		map.put("hosClothesPress", hosClothesPress);
		rooms.add(map);
		model.addAttribute("rooms", rooms);
		return prefix + "modeOne/respClothesPressListsBinding";
	}

	/**
	 * 打开或者锁定储物柜小柜页面跳转
	 * 
	 * @author xujialong
	 * @param steNumber
	 * @param rows
	 * @param columns
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterOpenOrLockClothesPress" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterOpenOrLockClothesPress(String steNumber, Integer total, Model model) throws BaseException {
		List<Map<String, Object>> rooms = Lists.newArrayList();
		List<Map<String, Object>> hosClothesPress = hosClothesService.selectClothesPressBySteNumber(steNumber);
		Map<String, Object> map = Maps.newHashMap();
		double columns = 5.0;
		map.put("steNumber", steNumber);
		int rows = (int) Math.ceil(total / columns);
		map.put("rows", rows);
		map.put("columns", (int) columns);
		map.put("total", total);
		map.put("hosClothesPress", hosClothesPress);
		rooms.add(map);
		model.addAttribute("rooms", rooms);
		return prefix + "modeOne/respClothesPressListsOpenOrLock";
	}

	/**
	 * 锁定储物柜小柜
	 * 
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/lockClothesPress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg lockClothesPress(Integer[] ids) throws BaseException {
		hosClothesService.updateLockClothesPress(ids);
		return ajaxDoneSuccess("锁定成功");
	}

	/**
	 * 取消锁定储物柜小柜
	 * 
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/stoplockClothesPress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg stoplockClothesPress(Integer[] ids) throws BaseException {
		hosClothesService.updateStoplockClothesPress(ids);
		return ajaxDoneSuccess("取消锁定成功");
	}

	/**
	 * 给储物柜小柜绑定医护人员
	 * 
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/bindingClothesPressAndUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg bindingClothesPressAndUser(Integer id, Integer userId) throws BaseException {
		hosClothesService.updateBindingClothesPressAndUser(id, userId);
		return ajaxDoneSuccess("绑定成功");
	}

	/**
	 * 取消储物柜柜小柜与医护人员的绑定
	 * 
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/stopBindingClothesPressAndUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg stopBindingClothesPressAndUser(Integer id) throws BaseException {
		hosClothesService.updateStopBindingClothesPressAndUser(id);
		return ajaxDoneSuccess("取消绑定成功");
	}

	/**
	 * 打开储物柜记录日志
	 * 
	 * @author xujialong
	 * @param deviceNumber
	 * @param numbers
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/openContainerClothesPressLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg openContainerClothesPressLog(String deviceNumber, String[] numbers, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		hosClothesService.openContainerClothesPressLog(deviceNumber, numbers, sessionUser);
		return ajaxDoneSuccess("柜门已打开");
	}
	// =========================================硬件交互===============================================

	@RequestMapping(value = {
			"burshCard" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object burshCard(String cardNumber, String machineNumber) throws BaseException {
		Map<String, Object> map = new HashMap<>();
		AuthUser authUser = authUserService.findByLoginName2(cardNumber);
		if (authUser == null) {
			return ajaxBizError("没有找到相关信息，刷卡失败，请联系相关人员");
		}
		String type = authPermissionService.loadPermCodeListByUserId(authUser.getId());
		if ("1".equals(type)) {
			return "all";
		} else {
			map = hosClothesService.selectClothesPressByUserIdAndNumber(authUser.getId(), machineNumber);
		}
		String mapStr = JSON.toJSONString(map);
		return mapStr;
	}
}
