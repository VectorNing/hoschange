package com.sesxh.hoschange.biz.hos.controller.device;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSterilizerContainerBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerContainerService;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.websocket.WebSocketChanne;
import com.sesxh.hoschange.global.BizConst;

@Component
public class HosSterilizerSelfService {
	@Autowired
	private HosSterilizerService hosSterilizerService;
	@Autowired
	private HosSterilizerContainerService hosSterilizerContainerService;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private HosRecordService hosRecordService;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private HosPowerBpo hosPowerBpo;
	@Autowired
	private HosSterilizerContainerBpo hosSterilizerContainerBpo;

	public Message sterilizer(JSONObject jsonObject) throws BaseException {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		AuthUser authUser = authUserService.findByLoginName2(message.getCardNumber());
		String deviceType = BizConst.DEVICE_TYPE.STERILIZER;
		message.setDeviceType(deviceType);

		if (authUser == null) {
			message.setMessageType("02");
			message.setErrorMessage("系统没有您的信息，请联系管理员");
			sendMessageToPage("WarningMessage", message);
			return null;
		}
		// 查询权限

		/*
		 * int count = hosPowerBpo.selectIfHavePower(authUser.getId() + ""); if
		 * (count == 0) { message.setMessageType("07");
		 * message.setErrorMessage("您还没有申请权限");
		 * sendMessageToPage("WarningMessage", message); return null; }
		 */

		String type = authPermissionService.loadPermCodeListByUserId(authUser.getId());
		if ("0".equals(type)) {// 医护人员，判断是否签到
			return singInOrSingOut(authUser, message);
		} else {// 管理员，根据设置打开空柜
			message = hosSterilizerContainerService.OpenLockerByAdmin(authUser, message);
			sendMessageToPage("WarningMessage", message);
			sendMessageToDevice("OpenDoor", message);
		}
		return null;
	}

	private Message singInOrSingOut(AuthUser authUser, Message message) throws BaseException {
		String singType = sign(authUser, message.getDeviceID(), message.getDeviceType());
		if (BizConst.SIGN.OUT.equals(singType) || singType == null) {// 已签退，新的流程开始，判断上次是否进入黑名单
			return judgeBlackList(authUser, message);
		} else {// 已经签到，打开对应柜子
			int count = hosSterilizerContainerBpo.selectRecordByUseridAndSteNumber(authUser.getId() + "",
					message.getDeviceID());
			if (count > 0) {
				message = hosSterilizerContainerService.OpenLockerUnderSingIn(authUser, message);
				sendMessageToPage("WarningMessage", message);
				sendMessageToDevice("OpenDoor", message);
				return null;
			} else {
				return judgeBlackList(authUser, message);
			}
		}
	}

	/**
	 * 判断是否在黑名单
	 * 
	 * @Title: judgeBlackList
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:Message
	 * @throws:
	 */
	private Message judgeBlackList(AuthUser authUser, Message message) throws BaseException {
		String grey = hosRecordService.selectHosRecordCountByUserId(authUser.getId());
		if (BizConst.ISORNO_LIST.BLACK_TRUE.equals(grey)) {// 不在黑名单
			message.setMessageType("06");
			message.setErrorMessage("您已进入黑名单，请联系管理员");
			sendMessageToPage("WarningMessage", message);
			return null;
		} else {// 不在黑名单,判断是否一对一
			return judgeBinding(authUser, message);
		}
	}

	/**
	 * 判断是否绑定
	 * 
	 * @Title: judgeBinding
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:Message
	 * @throws:
	 */
	private Message judgeBinding(AuthUser authUser, Message message) throws BaseException {
		HosSterilizerContainer hosSterilizerContainer = hosSterilizerContainerService
				.selectBindingSterilizerAndUser(authUser.getId(), message.getDeviceID());
		if (hosSterilizerContainer != null) {// 绑定关系，固定分配
			message = hosSterilizerService.bindingReceiveShoeByUser(authUser, message);
			sendMessageToPage("WarningMessage", message);
			sendMessageToDevice("OpenDoor", message);
			return null;
		} else {// 随机分配
			message = hosSterilizerService.randomReceiveShoeByUser(authUser, message);
			sendMessageToPage("WarningMessage", message);
			sendMessageToDevice("OpenDoor", message);
			return null;
		}
	}

	private void sendMessageToPage(String methodName, Message message) {
		Map<String, Object> data = new HashMap<>();
		data.put("Method", methodName);
		data.put("Parameter", message);
		WebSocketSession session = WebSocketChanne.pageMap.get(message.getDeviceID());
		if (session != null) {
			try {
				session.sendMessage(new TextMessage(JSON.toJSONString(data)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMessageToDevice(String methodName, Message message) {
		Map<String, Object> data = new HashMap<>();
		List<String> opreation = message.getOperation();
		if (opreation != null) {
			data.put("Method", methodName);
			data.put("Parameter", message);
			WebSocketSession session = WebSocketChanne.deviceMap.get(message.getDeviceID());
			if (session != null) {
				try {
					session.sendMessage(new TextMessage(JSON.toJSONString(data)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断签到签退
	 * 
	 * @Title: sign
	 * @author Ning
	 * @data:2017年2月21日
	 * @return:String
	 * @throws:
	 */
	private String sign(AuthUser authUser, String deviceNumber, String deviceType) throws BaseException {
		HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(authUser.getId(), deviceNumber, deviceType);
		if (hosRecord == null || BizConst.SIGN.OUT.equals(hosRecord.getSign())) {
			return BizConst.SIGN.OUT;// 签退
		} else {
			return BizConst.SIGN.IN;// 签到
		}
	}

}
