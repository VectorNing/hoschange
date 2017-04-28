package com.sesxh.hoschange.common.websocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.springframework.web.socket.WebSocketSession;
/**
 * 
* @Title:WebSocketChanne 
* @Description: 
* @author Ning
* @date 2017年3月17日
 */
public class WebSocketChanne {
	/**
	 * 设备管道连接容器
	 */
	public final static Map<String, WebSocketSession> deviceMap = Collections.synchronizedMap(new HashMap<>());
	/**
	 * ip储存容器
	 */
	public final static Map<String, String> ipMap = Collections.synchronizedMap(new HashMap<>());
	/**
	 * 页面连接管道容器
	 */
	public final static Map<String, WebSocketSession> pageMap = Collections.synchronizedMap(new HashMap<>());
}
