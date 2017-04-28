package com.sesxh.hoschange.common.websocket;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.common.exception.AppException;
import com.sesxh.hoschange.base.BaseClientPacketHandler;
import com.sesxh.hoschange.common.util.ClientJsonUtils;
import com.sesxh.hoschange.global.factories.WebsocketHandlerFactory;
/**
 * 
* @Title:MyWebSocketHandler 
* @Description: 
* @author Ning
* @date 2017年3月17日
 */
public class MyWebSocketHandler implements WebSocketHandler {
	// 静态变量，用来记录当前在线连接数
	private static int onlineCount = 0;
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private WebsocketHandlerFactory websocketHandlerFactory;

	// 创建连接后处理
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String deviceID = (String) session.getAttributes().get("deviceID");
		String ip = (String) session.getRemoteAddress().getAddress().toString();
		addOnlineCount(); // 在线数加1

		if (deviceID != null) {// 设备加入连接
			WebSocketChanne.deviceMap.put(deviceID, session);
			WebSocketChanne.ipMap.put(ip, deviceID);
			System.out.println("有新设备连接" + deviceID + "加入！当前在线设备为" + getOnlineCount());
		} else {// 页面加入连接
			String pageID=WebSocketChanne.ipMap.get(ip);
			if (pageID != null) {
				WebSocketChanne.pageMap.put(pageID, session);
				System.out.println("有新页面连接" + pageID + "加入！当前在线设备为" + getOnlineCount());
			}
		}

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String str = (String) message.getPayload();
		JSONObject msg = ClientJsonUtils.getjsonObj(str);
		msg.getJSONObject("Parameter").put("Ip", session.getRemoteAddress().getAddress().toString());
		String method, returnmsg;
		BaseClientPacketHandler clienthandler;

		method = msg.getString("Method");

		try {
			clienthandler = websocketHandlerFactory.getClientHandler(method);
			returnmsg = clienthandler.handle(msg);
		} catch (AppException e) {
			returnmsg = ClientJsonUtils.genReturnJsonPacket(new JSONObject(), e.getMessage());
		} catch (BaseException e) {
			returnmsg = ClientJsonUtils.genReturnJsonPacket(new JSONObject(), e.getMessage());
		}
		try {
			if (returnmsg != null) {
				session.sendMessage(new TextMessage(returnmsg));
			}
		} catch (IOException e) {
			logger.error(e);
		}

	}

	// 抛出异常时处理
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		String name = (String) session.getAttributes().get("name");
		System.out.println("有一连接" + name + "异常！");

	}

	// 连接关闭时处理
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		subOnlineCount(); // 在线数减1
		String deviceID = (String) session.getAttributes().get("deviceID");
		String ip = session.getRemoteAddress().getAddress().toString();
		if (deviceID != null) {
			System.out.println("有一设备连接" + deviceID + "关闭！当前在线设备为" + getOnlineCount());
			removeSessionFromDeviceMap(deviceID);
			removeDeviceIdFromIpMap(ip);
		} else {
			for (String key : WebSocketChanne.pageMap.keySet()) {
				if (session.equals(WebSocketChanne.pageMap.get(key))) {
					System.out.println("有一页面连接" + key + "关闭！当前在线设备为" + getOnlineCount());
					removePageFromPageMap(key);
				}
			}
		}

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		MyWebSocketHandler.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		MyWebSocketHandler.onlineCount--;
	}

	/**
	 * 
	 * @Title: removeSessionFromMap
	 * @author Ning
	 * @data:2017年3月17日
	 * @return:void
	 * @throws:
	 */
	private void removeSessionFromDeviceMap(String deviceID) {
		WebSocketSession socketSession = WebSocketChanne.deviceMap.get(deviceID);
		if (socketSession != null) {
			WebSocketChanne.deviceMap.remove(deviceID);
		}
	}

	/**
	 * 
	 * @Title: removeDeviceIdFromIpMap
	 * @author Ning
	 * @data:2017年4月18日
	 * @return:void
	 * @throws:
	 */
	private void removeDeviceIdFromIpMap(String ip) {
		String deviceID = WebSocketChanne.ipMap.get(ip);
		if (deviceID != null) {
			WebSocketChanne.ipMap.remove(ip);
		}
	}

	/**
	 * 
	 * @Title: removePageFromPageMap
	 * @author Ning
	 * @data:2017年4月18日
	 * @return:void
	 * @throws:
	 */
	private void removePageFromPageMap(String deviceID) {
		WebSocketSession socketSession = WebSocketChanne.pageMap.get(deviceID);
		if (socketSession != null) {
			WebSocketChanne.pageMap.remove(deviceID);
		}
	}
}
