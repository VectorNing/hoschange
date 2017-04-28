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
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.websocket.WebSocketChanne;
import com.sesxh.hoschange.global.BizConst;

@Component
public class FkqService {
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private HosPowerBpo hosPowerBpo;

	public Message FkqReadCard(JSONObject jsonObject) throws BaseException {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		// AuthUser authUser = authUserService.findBySfzh(message.getSfzh());
		String deviceType = BizConst.DEVICE_TYPE.FKQ;
		message.setDeviceType(deviceType);

		// if (authUser == null) {
		// message.setErrorMessage("系统没有您的信息，请联系管理员");
		// sendMessageToPage("WarningMessage", message);
		// return null;
		// }

		// int count = hosPowerBpo.selectIfHavePower(authUser.getId() + "");
		// if (count == 0) {
		// message.setErrorMessage("您还没有申请权限");
		// sendMessageToPage("WarningMessage", message);
		// return null;
		// }

		sendCardYse(message.getDeviceID());
		return null;
	}

	/**
	 * 发衣
	 * 
	 * @Title: sendCardYse
	 * @author wzg
	 * @data:2017年4月21日
	 * @return:void
	 * @throws:
	 */
	private void sendCardYse(String deciceId) throws BaseException {
		sendMessageToDevice(deciceId);

	}

	/**
	 * 
	 * @Title: sendMessageToPage
	 * @author wzg
	 * @data:2017年4月21日
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
				System.out.println(JSON.toJSONString(data));
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
	private void sendMessageToDevice(String warNumber) {
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("Type", "1");
		data.put("Method", "SendCardYseOrNo");
		data.put("Parameter", parameter);
		WebSocketSession session = WebSocketChanne.deviceMap.get(warNumber);
		if (session != null) {
			try {
				System.out.println(JSON.toJSONString(data));
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
	 * 
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
