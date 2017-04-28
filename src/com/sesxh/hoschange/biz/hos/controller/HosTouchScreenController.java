package com.sesxh.hoschange.biz.hos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.service.HosBlacklistRuleService;
import com.sesxh.hoschange.biz.hos.service.HosClothesPressService;
import com.sesxh.hoschange.biz.hos.service.HosOperationService;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.biz.hos.service.HosRecoveryGoodsService;
import com.sesxh.hoschange.biz.hos.service.HosRecycleService;
import com.sesxh.hoschange.biz.hos.service.HosShoesService;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerContainerService;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerService;
import com.sesxh.hoschange.biz.hos.service.HosTheaterService;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeContainerService;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.biz.sys.service.SysConfigService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.websocket.WebSocketChanne;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/touch")
public class HosTouchScreenController extends ZBaseController {

	@Autowired
	private HosSterilizerService hosSterilizerService;
	@Autowired
	private HosTheaterService hosTheaterService;
	@Autowired
	private HosWardrobeService hosWardrobeService;
	@Autowired
	private HosSterilizerContainerService hosSterilizerContainerService;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private HosShoesService hosShoesService;
	@Autowired
	private HosRecordService hosRecordService;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private HosOperationService hosOperationService;
	@Autowired
	private HosWardrobeContainerService hosWardrobeContainerService;
	@Autowired
	private HosRecycleService hosRecycleService;
	@Autowired
	private HosClothesPressService hosClothesPressService;
	@Autowired
	private HosRecoveryGoodsService hosRecoveryGoodsService;
	@Autowired
	private HosBlacklistRuleService hosBlacklistRuleService;

	private String prefix = "touch/";

	private static Map<String, List<String>> sterilzerMap = new HashMap<>();

	// ########################################## 终端 12-01
	// ##################################################//

	/**
	 * 加载衣柜 读存储 编号页面
	 * 
	 * @param model
	 * @param warNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/touchWardorbeGoIndex", method = RequestMethod.GET)
	public Object touchWardorbeGoIndex(Model model, String warNumber) throws BaseException {
		model.addAttribute("warNumber", warNumber);
		return prefix + "touchWardorbeGoIndex";
	}

	/**
	 * 加载打开衣柜界面
	 * 
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = { "/touchClothesPress" }, method = RequestMethod.GET)
	public String touchClothesPress() throws BaseException {
		return prefix + "touchClothesPress";
	}

	/**
	 * 终端 进入手术衣柜管理页面
	 * 
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterTouchScreenWardorbe", method = RequestMethod.GET)
	public Object enterTouchScreenWardorbe(Model model, HttpServletRequest request) throws BaseException {
		String webSocketServer = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.WEBSOCKETSERVER)
				.getConfig();
		model.addAttribute("webSocketServer", webSocketServer);
		return prefix + "touchScreenWardorbeIndex";
	}

	/**
	 * 终端 进入消毒柜管理页面
	 * 
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterTouchScreenSterilizer", method = RequestMethod.GET)
	public Object enterTouchScreenSterilizer(Model model) throws BaseException {
		String webSocketServer = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.WEBSOCKETSERVER)
				.getConfig();
		model.addAttribute("webSocketServer", webSocketServer);
		return prefix + "touchScreenStreilizerIndex";
	}

	/**
	 * 回收桶终端
	 * 
	 * @author Ning
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterTouchScreenRecycle", method = RequestMethod.GET)
	public Object enterTouchScreenRecycle() throws BaseException {
		return prefix + "touchScreenRecycle";
	}

	/**
	 * 终端加载 根据手术衣柜编号 跳转分配页面
	 * 
	 * @param model
	 * @param number
	 * @param request
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/allotWardrobe", method = RequestMethod.GET)
	public Object allotWardrobeByWarNum(Model model, String warNumber, HttpServletRequest request)
			throws BaseException {
		List<Map<String, Object>> lists = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();
		List<HosWardrobe> hoss = hosWardrobeService.loadWardrobeByWarNumber(warNumber);
		List<Map<String, Object>> sizeAndCountList = hosOperationService.queryHosOperationList(warNumber);

		map.put("number", warNumber);
		map.put("hoss", hoss);
		lists.add(map);

		model.addAttribute("lists", lists);
		model.addAttribute("sizeAndCountList", sizeAndCountList);

		/*
		 * SessionUser user = (SessionUser)
		 * request.getSession().getAttribute(BizConst.SESSIONUSER);//
		 * SecurityUtils.getSessionUser(); if (user == null) {
		 * model.addAttribute("warNumber", warNumber); return prefix +
		 * "touchWardorbeGoIndex"; } AuthUserDetail userDetail =
		 * hosTheaterService.loadUserDetailById(user.getUserId());
		 * model.addAttribute("user", userDetail);
		 */
		return prefix + "respWardorbeAllot";
	}

	/**
	 * 终端 跳转 分配消毒鞋页面信息
	 * 
	 * @param model
	 * @param number
	 * @param request
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/allotSterilizer", method = RequestMethod.GET)
	public Object allotSterilizer(Model model, String number, HttpServletRequest request) throws BaseException {
		List<Map<String, Object>> lists = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();
		List<HosSterilizer> hoss = hosSterilizerService.loadSterilizerByThNumber(number);
		List<HosShoes> hosShoesList = hosShoesService.selectHosShoesByTheaterNumber(number);
		map.put("number", number);
		map.put("hoss", hoss);

		lists.add(map);
		model.addAttribute("lists", lists);
		model.addAttribute("hosShoesList", hosShoesList);
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);// SecurityUtils.getSessionUser();
		if (user == null) {
			// return prefix+"touchScreenStreilizerIndex";
		}
		AuthUserDetail userDetail = hosTheaterService.loadUserDetailById(user.getUserId());
		model.addAttribute("user", userDetail);
		return prefix + "respSterilizerAllot";
	}

	/**
	 * 回收桶刷卡登录，判断机器需要执行的操作
	 * 
	 * @author xujialong
	 * @param request
	 * @param response
	 * @param deviceNumber
	 * @param loginname
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/slotCardByRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object slotCardByRecord(HttpServletRequest request, HttpServletResponse response, String deviceNumber,
			String loginname) throws BaseException {
		AuthUser authUser = authUserService.findByLoginName2(loginname);
		if (authUser == null) {
			return ajaxBizError("没有找到相关信息，刷卡失败，请联系相关人员");
		}
		String userType = authPermissionService.loadPermCodeListByUserId(authUser.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		if (userType.equals("1")) {
			map.put("type", userType);
		} else {
			HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(authUser.getId(), deviceNumber,
					BizConst.DEVICE_TYPE.RECYCLE);
			HosRecoveryGoods hosRecoveryGoods = hosRecoveryGoodsService
					.selectHosRecoveryGoodsByIdAndType(hosRecord.getId(), BizConst.DEVICE_TYPE.WARDRODE);
			if (hosRecord != null && hosRecord.getIsCallback().equals("0") && hosRecoveryGoods != null) {
				map.put("type", userType);
			} else {
				map.put("type", "2");
			}
		}
		// type 1 管理员 0 回收 2 不执行
		map.put("loginname", loginname);
		String jsonMap = JSON.toJSONString(map);
		return jsonMap;
	}

	/**
	 * 回收桶执行完成后修改数据库信息
	 * 
	 * @author xujialong
	 * @param request
	 * @param response
	 * @param type
	 * @param loginname
	 * @param deviceNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/recordMachine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object recordMachine(HttpServletRequest request, HttpServletResponse response, String type, String loginname,
			String deviceNumber) throws BaseException {
		AuthUser authUser = authUserService.findByLoginName2(loginname);
		if (type.equals("1")) {// 管理员清空回收桶
			hosRecycleService.emptyRecycle(deviceNumber);
		} else if (type.equals("0")) {// 医护人员回收衣物
			String yesOrNoShoe = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX)
					.getConfig();// 是否有鞋柜
			if (yesOrNoShoe.equals("1")) {// 回收
				hosRecycleService.updateHosRecycleAndHosRecordAndHosRecovery(deviceNumber, loginname,
						BizConst.ISCALLBACK.YES);
			} else {// 签退并回收
				HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(authUser.getId(), deviceNumber,
						BizConst.DEVICE_TYPE.RECYCLE);
				hosRecycleService.updateHosRecycleAndHosRecordAndHosRecovery(hosRecord.getId(), deviceNumber, loginname,
						BizConst.ISCALLBACK.YES);
			}
		}
		return "ok";
	}

	/**
	 * 刷卡登录 区分医生或者管理员
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param loginname
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, Model model, String loginname)
			throws BaseException {
		AuthUser authUser = authUserService.findByLoginName2(loginname);
		if (authUser == null) {
			return ajaxBizError("没有找到相关信息，刷卡失败，请联系相关人员");
		}
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		SessionUser sessionUser = authUserService.loadUserInfor(authUser.getId());
		sessionUser.setSessionId(sessionId);
		session.setAttribute(BizConst.SESSIONUSER, sessionUser);
		String type = authPermissionService.loadPermCodeListByUserId(authUser.getId());
		BizMsg bm = new BizMsg();
		bm.setStatusCode(BizConst.SC.SC_200);
		bm.setStrData(type);
		return bm;
	}

	/**
	 * 判断用户是否在黑名单(不操作) 灰名单(提示) 是否在规定时间内重复刷卡
	 * 
	 * @param request
	 * @param response
	 * @param deviceNumber
	 *            衣柜编号 、鞋柜更衣室编号
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectUserAdoptBlacklistAndTimes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectUserAdoptBlacklistAndTimes(HttpServletRequest request, HttpServletResponse response,
			String deviceNumber, String deviceType) throws BaseException {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		BizMsg bm = new BizMsg();
		bm.setStatusCode(BizConst.SC.SC_200);
		Integer userId = sessionUser.getUserId();

		String yesOrNoShoe = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX)
				.getConfig();// 是否有鞋柜
		String isOrNoRoster = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.ISORNOROSTER).getConfig();// 有无黑名单

		HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(sessionUser.getUserId(), deviceNumber,
				deviceType);
		if (hosRecord == null) {
			bm.setSign(BizConst.SIGN.OUT);// 0 签到，1签退
		} else {
			bm.setSign(hosRecord.getSign());// 0 签到，1签退
			bm.setRecordId(hosRecord.getId());// 刷卡id 用于签退
		}

		if (yesOrNoShoe.equals(BizConst.YESORNO.NO)) {// 没有鞋柜 在衣柜签到或签退
			bm.setIsOrNoShoe(BizConst.DEVICE_TYPE.WARDRODE);// 在衣柜进行签到签退
		} else {// 有鞋柜 在衣柜执行领取
			bm.setIsOrNoShoe(BizConst.DEVICE_TYPE.STERILIZER);// 在鞋柜进行签到签退
		}

		if (isOrNoRoster.equals(BizConst.YESORNO_BLACK.YES) && bm.getSign().equals(BizConst.SIGN.OUT)) {// 没有配置相当于没有名单
			String grey = hosRecordService.selectHosRecordCountByUserId(userId);
			if (grey == null) {
				return ajaxBaseError("没有定义黑名单灰名单规则");
			}
			if (!grey.equals(BizConst.ISORNO_LIST.ALL_FALSE)) {
				if (grey.equals(BizConst.ISORNO_LIST.BLACK_TRUE)) {// 在黑名单 提示返回
																	// 不操作
					// bm.setStatusCode(BizConst.SC.SC_500);
					bm.setStrData("" + BizConst.SC.SC_500);
					bm.setMessage("您已在黑名单，不能进行操作，请联系相关管理员！");
					return bm;
				} else if (grey.equals(BizConst.ISORNO_LIST.GREY_TRUE)) {// 在灰名单
																			// 给出警告提示
					bm.setStrData("" + BizConst.SC.SC_502);
					bm.setMessage("您已在灰名单，请保持良好的回收操作，避免进入黑名单！");
				}
			}
		}

		return bm;
	}

	/**
	 * 回收判断签到签退
	 * 
	 * @param request
	 * @param response
	 * @param deviceNumber
	 *            更衣室编号
	 * @param deviceType
	 *            设备类别
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/callbackSeriesTimes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object callbackSeriesTimes(HttpServletRequest request, HttpServletResponse response, String recycleNumber,
			String deviceType) throws BaseException {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		BizMsg bm = new BizMsg();
		bm.setStatusCode(BizConst.SC.SC_200);
		Integer userId = sessionUser.getUserId();

		String yesOrNoShoe = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX)
				.getConfig();// 是否有鞋柜
		HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(userId, recycleNumber, deviceType);
		// HosRecord hosRecord =
		// hosRecordService.selectHosRecordLastByTheNumber(userId,
		// theaterNumber,deviceType);
		if (hosRecord == null) {
			bm.setSign("1");// 0 签到，1签退
		} else {
			bm.setSign(hosRecord.getSign());// 0 签到，1签退
			bm.setRecordId(hosRecord.getId());// 刷卡id 用于签退
		}
		if (yesOrNoShoe.equals(BizConst.YESORNO.NO)) {// 没有鞋柜 在衣柜签到或签退
			bm.setIsOrNoShoe(BizConst.DEVICE_TYPE.WARDRODE);// 在衣柜进行签到签退
		} else {// 有鞋柜 在衣柜执行领取回收
			bm.setIsOrNoShoe(BizConst.DEVICE_TYPE.STERILIZER);// 在鞋柜进行签到签退
		}

		return bm;
	}

	/**
	 * 查询手术衣柜 是否暂停服务
	 * 
	 * @param warNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectWardrobeEnabled", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectWardrobeEnabled(String warNumber) throws BaseException {
		HosWardrobe ward = hosWardrobeService.selectHosWardrobeEnabledByNumber(warNumber);
		if (ward == null) {
			ajaxFatalError("没有获取到衣柜信息");
		}
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(ward.getEnabled());
		return biz;
	}

	// -------------------------------- 领取 查看
	// --------------------------------------//
	/**
	 * 根据 衣柜编号 随机领取手术衣 如无鞋柜 则签到
	 * 
	 * @param warNumber
	 *            手术衣发放设备编号
	 * @param cardNum
	 *            用户卡号
	 * @param mark
	 *            标志是领取或者重新领取
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/randomReceiveOpeByUserIdWarNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg randomReceiveOpeByUserIdWarNumber(HttpServletRequest request, String warNumber, String cardNum,
			Integer mark) throws BaseException {
		AuthUser user = authUserService.findByLoginName2(cardNum);
		if (user == null) {
			ZAlert.Error("系统中查询不到用户信息，请联系管理员！！！");
		}
		Map<String, Object> map = hosTheaterService.randomReceiveOpeByUserIdWarNumber(user.getId(), warNumber, mark);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);

		request.getSession().invalidate();// 退出登录
		return biz;
	}

	/**
	 * 根据手术室编号 随机领取手术鞋
	 * 
	 * @param cardNum
	 *            用户卡号信息
	 * @param theNumber
	 *            手术室编号
	 * @param mark
	 *            标志是领取或者重新领取
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/randomReceiveShoeByUserIdTheNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg randomReceiveShoeByUserIdTheNumber(HttpServletRequest request, String theNumber, String cardNum,
			Integer mark) throws BaseException {
		AuthUser user = authUserService.findByLoginName2(cardNum);
		if (user == null) {
			ZAlert.Error("系统中查询不到用户信息，请联系管理员！！！");
		}
		Map<String, Object> map = hosTheaterService.randomReceiveShoeByUserIdTheNumber(user.getId(), theNumber, mark);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);

		request.getSession().invalidate();// 退出登录
		return biz;
	}

	/**
	 * 用户领取自己物品
	 * 
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/findSteConByUserIdAndThNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg findSteConNumberByUserIdAndThNumber(Integer id, String number, String cardNum) throws BaseException {
		AuthUser user = authUserService.findByLoginName2(cardNum);
		Map<String, Object> map = hosSterilizerContainerService.findSteConNumberByUserIdAndThNumber(user.getId(),
				number);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);
		return biz;
	}

	/**
	 * 用户查询自己的鞋子所在地
	 * 
	 * @param number
	 * @param cardNum
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectShoesByUserIdAndThNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg selectShoesByUserIdAndThNumber(String number, String cardNum) throws BaseException {
		Map<String, Object> map = hosSterilizerContainerService.selectShoesByUserIdAndThNumber(cardNum, number);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);
		return biz;
	}

	/**
	 * 根据用户卡号和手术衣发放设备编号查询衣柜信息
	 * 
	 * @author xujialong
	 * @param number
	 * @param cardNum
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectOpeByCardNumAndNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg selectOpeByCardNumAndNumber(String number, String cardNum) throws BaseException {
		Map<String, Object> map = hosClothesPressService.selectOpeByCardNumAndNumber(number, cardNum);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);
		return biz;
	}

	/**
	 * 手术衣柜 回收手术衣
	 * 
	 * @param cardNum
	 *            用户卡号
	 * @param state
	 *            回收状态
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/recoverySign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object recoverySign(String cardNum, String state, String recycleNum) throws BaseException {
		hosRecycleService.updateHosRecycleAndHosRecordAndHosRecovery(recycleNum, cardNum, state);
		return ajaxDoneSuccess("回收成功！");
	}

	/**
	 * 手术衣 回收并签退
	 * 
	 * @param recordId
	 *            刷卡id
	 * @param cardNum
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/signOutRecovery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object signOutRecovery(Integer recordId, String cardNum, String state, String recycleNum)
			throws BaseException {
		hosRecycleService.updateHosRecycleAndHosRecordAndHosRecovery(recordId, recycleNum, cardNum, state);
		return ajaxDoneSuccess("回收成功");
	}
	// --------------------------------领取 查看
	// end--------------------------------------//

	/**
	 * 跳转管理员 衣柜操作页面
	 * 
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterWardorbeAdminIndex", method = RequestMethod.GET)
	public String enterWardorbeAdminIndex(Model model, String warNumber, String carNum) throws BaseException {
		model.addAttribute("warNumber", warNumber);
		model.addAttribute("carNum", carNum);
		return prefix + "touchScreenWardorbeAdminIndex";
	}

	/**
	 * 跳转管理员 鞋柜操作页面
	 * 
	 * @param model
	 * @param thNumber
	 *            手术室编号
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterStreilizerAdminIndex", method = RequestMethod.GET)
	public String enterStreilizerAdminIndex(Model model, String thNumber, String carNum) throws BaseException {
		model.addAttribute("thNumber", thNumber);
		model.addAttribute("carNum", carNum);
		return prefix + "touchScreenStreilizerAdminIndex";
	}

	/**
	 * 暂停或者 恢复衣柜
	 * 
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/updateHosWardrobeEnabledByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosWardrobeEnabledByNumber(HosWardrobe hosWardrobe) throws BaseException {
		hosWardrobeService.updateHosWardrobeEnabledByNumber(hosWardrobe);
		return ajaxDoneSuccess("修改成功！");
	}

	// ---------------------------------衣物管理员分配
	// ---------------------------------//
	/**
	 * 衣物管理员 分配手术衣
	 * 
	 * @param jsonArray
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/allotOperationToContainerForZD", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotOperationToContainerForZD(String ids) throws BaseException {
		hosWardrobeContainerService.allotOperationToContainerForZD(ids);
		return ajaxDoneSuccess("手术衣分配成功！");
	}

	/**
	 * 清空衣柜所选小托盘(衣物管理员) 保留尺码
	 * 
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyContainer(String[] ids) throws BaseException {
		hosWardrobeContainerService.emptyContainerCloth(ids);
		return ajaxDoneSuccess("托盘清空成功！");
	}

	/**
	 * 清空全部小托盘(衣物管理员) 保留尺码
	 * 
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyAllContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyAllContainer(String number) throws BaseException {
		hosWardrobeContainerService.emptyAllContainerCloth(number);
		return ajaxDoneSuccess("数据清空成功！");
	}

	/**
	 * 衣物管理员 分配消毒鞋
	 * 
	 * @param ids
	 * @param jsonArray
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/allotShoesToSterilizerContainerForZD", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotShoesToSterilizerContainerForZD(Integer[] ids, String jsonArray) throws BaseException {
		hosSterilizerContainerService.allotShoesToContainerForZD(ids, jsonArray);
		return ajaxDoneSuccess("消毒鞋分配成功！");
	}

	/**
	 * 清空消毒柜 所选小柜 已分配鞋子 保留尺码
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyContainerShoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyContainerShoes(Integer[] ids) throws BaseException {
		hosSterilizerContainerService.emptyContainerShoes(ids);
		return ajaxDoneSuccess("数据清空成功！");
	}

	/**
	 * 清空消毒柜 所选小柜 存放用户鞋子 保留尺码
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyContainerShoes2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyContainerShoes2(Integer[] ids) throws BaseException {
		hosSterilizerContainerService.emptyContainerShoes2(ids);
		return ajaxDoneSuccess("数据清空成功！");
	}
	// ---------------------------------衣物管理员分配
	// end---------------------------------//

	// ---------------------------- 查询管理员是否有该更衣室的权限 -------------------------//

	@RequestMapping(value = "/selectUserByPermission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectUserByPermission(String theNumber, String cardNum) throws BaseException {
		boolean falg = hosTheaterService.selectTheaterUserByNumberAndCardNum(theNumber, cardNum);
		if (falg) {// 有权限
			return ajaxDoneSuccess("");
		} else {// 无权限
			return ajaxBaseError("您没有该更衣室的设备操作权限");
		}

	}

	// ----------------------------- 查询管理员是否有该更衣室的权限end---------------------//

	/**
	 * 加载领取消毒鞋页面信息
	 * 
	 * @param model
	 * @param number
	 * @param request
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/loadSterilizerByThNumber", method = RequestMethod.GET)
	public Object loadSterilizerByThNumber(Model model, String number, HttpServletRequest request)
			throws BaseException {
		List<Map<String, Object>> lists = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();
		List<HosSterilizer> hoss = hosSterilizerService.loadSterilizerByThNumber(number);
		map.put("number", number);
		map.put("hoss", hoss);
		lists.add(map);
		model.addAttribute("lists", lists);

		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);// SecurityUtils.getSessionUser();
		AuthUserDetail userDetail = hosTheaterService.loadUserDetailById(user.getUserId());
		model.addAttribute("user", userDetail);
		return prefix + "respSterilizer";
	}

	/**
	 * 根据 衣柜编号 随机领取手术衣
	 * 
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/receiveDeviceOpeByUserId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg receiveDeviceOpeByUserId(Integer id, String number) throws BaseException {
		Map<String, Object> map = hosTheaterService.receiveDeviceOpeByUserIdM1(id, number);
		BizMsg biz = new BizMsg();
		biz.setData(map);
		biz.setStatusCode(BizConst.SC.SC_200);
		return biz;
	}

	// ----------------------------------------------------------------------------------------

	@RequestMapping(value = "/emptyTouchAllSteContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyTouchAllSteContainer(String number) throws BaseException {
		hosTheaterService.emptyTouchAllSteContainer(number);
		return ajaxDoneSuccess("数据清空成功！");
	}

	@RequestMapping(value = "/emptyTouchAllWarContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyTouchAllWarContainer(String number) throws BaseException {
		hosTheaterService.emptyTouchAllWarContainer(number);
		return ajaxDoneSuccess("数据清空成功！");
	}

	@RequestMapping(value = "/recoverySignByUserId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg recoverySign(Integer userId) throws BaseException {
		// hosRecoveryGoodsService.updateHosRecoveryGoodsStateByUserId(userId);
		return ajaxDoneSuccess("签退成功！");
	}

	@RequestMapping(value = "/enterTouchScreenRecovery", method = RequestMethod.GET)
	public Object enterTouchScreenRecovery() throws BaseException {
		return prefix + "touchScreenRecoveryGoodsIndex";
	}

	// =====================================2016/12/1===================================================//

	@RequestMapping(value = "/allotShoesOne", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotShoesOne(Integer[] ids) throws BaseException {
		List<HosSterilizerContainer> hosSterilizerContainers = new ArrayList<>();// 所有的小柜集合
		Set<String> machineNumber = new HashSet<>();// 设备集合

		for (Integer id : ids) {
			HosSterilizerContainer hosSterilizerContainer = hosSterilizerContainerService
					.selectSterilizerContainerById(id);
			hosSterilizerContainers.add(hosSterilizerContainer);
			machineNumber.add(hosSterilizerContainer.getSteNumber());
		}

		Iterator<String> it = machineNumber.iterator();
		while (it.hasNext()) {
			List<String> containers = new ArrayList<>();// 小柜根据设备分类集合
			String steNumber = it.next();
			for (HosSterilizerContainer hosSterilizerContainer : hosSterilizerContainers) {
				if (hosSterilizerContainer.getSteNumber().equals(steNumber)) {
					containers.add(hosSterilizerContainer.getLockerNumber());
				}
				sterilzerMap.put(steNumber, containers);
			}
		}

		/*
		 * String str = JSON.toJSONString(map); System.out.println(str);
		 */

		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		return biz;
	}

	@RequestMapping(value = "/openDoor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String openDoor(String machineNumber) throws BaseException {
		if (sterilzerMap.size() > 0) {
			List<String> lockerNumbers = sterilzerMap.get(machineNumber);
			if (lockerNumbers != null) {
				String str = JSON.toJSONString(lockerNumbers);
				sterilzerMap.remove(machineNumber);
				return str;
			}
		}
		return "null";
	}

	/**
	 * 加载黑名单列表
	 * 
	 * @param model
	 * @return
	 * @author xn
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/queryBlackListResult" }, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public String queryBlackListResult(Model model) throws BaseException {
		List<Map<String, Object>> datastore = hosBlacklistRuleService.queryBlackListResult();
		if (datastore.size() == 0) {
			datastore = null;
		}
		model.addAttribute("datastore", datastore);
		return prefix + "pageBlacklistMar_result";
	}

	/**
	 * 管理员添加衣服，调用硬件装货
	 * 
	 * @Title: loding
	 * @author Ning
	 * @data:2017年4月19日
	 * @return:String
	 * @throws:
	 */
	@RequestMapping(value = { "/loding" }, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String loding(String warNumber) throws BaseException {
		WebSocketSession session = WebSocketChanne.deviceMap.get(warNumber);
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		data.put("Method", "Loading");
		if (session != null) {
			for (int i = 0; i <= 1; i++) {
				parameter.put("Type", i);
				data.put("Parameter", parameter);
				try {
					session.sendMessage(new TextMessage(JSON.toJSONString(data)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	@RequestMapping(value = { "/lodingSuccess" }, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String lodingSuccess(String warNumber) throws BaseException {
		WebSocketSession session = WebSocketChanne.deviceMap.get(warNumber);
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		data.put("Method", "LoadingSuccess");
		data.put("Parameter", parameter);
		try {
			session.sendMessage(new TextMessage(JSON.toJSONString(data)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
