package com.sesxh.hoschange.biz.hos.controller.device;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.clienthandler.wardorbe.WardorbeLock;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.biz.hos.service.HosTheaterService;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.websocket.WebSocketChanne;
import com.sesxh.hoschange.global.BizConst;

@Component
public class HosWarbelSelfService {
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private HosRecordService hosRecordService;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private HosTheaterService hosTheaterService;
	@Autowired
	private HosPowerBpo hosPowerBpo;
	@Autowired
	private HosWardrobeService hosWardrobeService;

	public Message WardorbeReadCard(JSONObject jsonObject) throws BaseException {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		AuthUser authUser = authUserService.findByLoginName2(message.getCardNumber());
		String deviceType = BizConst.DEVICE_TYPE.WARDRODE;
		message.setDeviceType(deviceType);

		if (authUser == null) {
			message.setErrorMessage("系统没有您的信息，请联系管理员");
			sendMessageToPage("WarningMessage", message);
			return null;
		}
		// 判断权限
		/*
		 * int count = hosPowerBpo.selectIfHavePower(authUser.getId() + ""); if
		 * (count == 0) { message.setErrorMessage("您还没有申请权限");
		 * sendMessageToPage("WarningMessage", message); return null; }
		 */
		// 判断设备是否停用
		HosWardrobe ward = hosWardrobeService.selectHosWardrobeEnabledByNumber(message.getDeviceID());
		String enable = ward.getEnabled();
		// 判断人员类别
		String usertype = authPermissionService.loadPermCodeListByUserId(authUser.getId());

		if ("0".equals(enable)) {// 暂停服务
			if ("1".equals(usertype)) {// 管理员刷卡跳转页面
				sendMessageToPage("ChangePage", message);
			}
			if ("0".equals(usertype)) {// 医护人员刷卡提示信息
				message.setErrorMessage("暂停服务");
				sendMessageToPage("WarningMessage", message);
			}
		}
		if ("1".equals(enable)) {// 服务中
			if ("1".equals(usertype)) {// 管理员刷卡跳转页面
				sendMessageToPage("ChangePage", message);
			}
			if ("0".equals(usertype)) {// 医护人员刷卡发衣
				judgeBlackList(authUser, message);
			}
		}
		return null;
	}

	private void judgeBlackList(AuthUser authUser, Message message) throws BaseException {
		String grey = hosRecordService.selectHosRecordCountByUserId(authUser.getId());
		if (BizConst.ISORNO_LIST.BLACK_TRUE.equals(grey)) {// 在黑名单
			message.setErrorMessage("您已进入黑名单，请联系管理员");
			sendMessageToPage("WarningMessage", message);
			return;
		} else {// 不在黑名单,发衣
			randomReceiveOpeByUserIdWarNumber(authUser, message);
		}
	}

	/**
	 * 发衣
	 * 
	 * @Title: randomReceiveOpeByUserIdWarNumber
	 * @author Ning
	 * @data:2017年4月18日
	 * @return:void
	 * @throws:
	 */
	private void randomReceiveOpeByUserIdWarNumber(AuthUser authUser, Message message) throws BaseException {
		Map<String, Object> map = hosTheaterService.randomReceiveOpeByUserIdWarNumber(authUser.getId(),
				message.getDeviceID(), null);
		String warning = (String) map.get("ErrorMessage");
		if (warning != null) {
			message.setErrorMessage(warning);
			sendMessageToPage("WarningMessage", message);
		} else {
			// 托盘号
			String trayNumber = (String) map.get("trayNumber");
			// 发衣柜编号
			String warNumber = (String) map.get("warNumber");
			// 衣服尺码
			String opeSize = (String) map.get("opeSize");
			// 存衣柜锁号
			String lockerNumber = (String) map.get("lockerNumber");
			// 存衣柜编号
			String clothesPress = (String) map.get("clothesPress");

			if (WardorbeLock.wardorbeLock.get(warNumber) == null) {// 当前设备空闲，发衣
				sendMessageToDevice(warNumber, trayNumber);
				message.setErrorMessage("刷卡成功，正在发衣，请稍后...");
				sendMessageToPage("WarningMessage", message);

				String msg = opeSize + "码手术衣发衣成功,祝您手术成功";
				WardorbeLock.wardorbeLock.put(warNumber, msg);
			} else {// 设备正忙，禁止刷卡
				message.setErrorMessage("当前设备正忙，请稍后...");
				sendMessageToPage("WarningMessage", message);
			}

		}
	}

	/**
	 * 
	 * @Title: sendMessageToPage
	 * @author Ning
	 * @data:2017年4月19日
	 * @return:void
	 * @throws:
	 */
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

	/**
	 * 
	 * @Title: sendMessageToDevice
	 * @author Ning
	 * @data:2017年4月19日
	 * @return:void
	 * @throws:
	 */
	private void sendMessageToDevice(String warNumber, String trayNumber) {
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("Type", trayNumber);
		data.put("Method", "Delivery");
		data.put("Parameter", parameter);
		WebSocketSession session = WebSocketChanne.deviceMap.get(warNumber);
		if (session != null) {
			try {
				session.sendMessage(new TextMessage(JSON.toJSONString(data)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 设备返回调用成功信息后，向页面推送提示信息，并把设备解锁，进入工作状态
	 * 
	 * @Title: deliveryReturn
	 * @author Ning
	 * @data:2017年4月19日
	 * @return:void
	 * @throws:
	 */
	public void deliveryReturn(JSONObject jsonObject) {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		String warNumber = WebSocketChanne.ipMap.get(message.getIp());
		message.setDeviceID(warNumber);
		String msg = WardorbeLock.wardorbeLock.get(warNumber);
		if (msg != null) {
			String erroMessage = message.getMessage();
			if ("null".equals(erroMessage)) {// 调用成功
				message.setErrorMessage(msg);
				sendMessageToPage("WarningMessage", message);
				WardorbeLock.wardorbeLock.remove(warNumber);
			} else {
				message.setErrorMessage(erroMessage);
				sendMessageToPage("WarningMessage", message);
				WardorbeLock.wardorbeLock.remove(warNumber);
			}

		}
	}
	/**
	 * 监测发衣柜衣服数量预警
	* @Title: wardorbeWarning
	* @author Ning 
	* @data:2017年4月20日
	* @return:void
	* @throws:
	 */
	public void wardorbeWarning(JSONObject jsonObject) {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		String warNumber = WebSocketChanne.ipMap.get(message.getIp());
		message.setDeviceID(warNumber);
		String erroMessage = message.getMessage();
		message.setErrorMessage(erroMessage);
		sendMessageToPage("WarningMessage", message);
	}

}
