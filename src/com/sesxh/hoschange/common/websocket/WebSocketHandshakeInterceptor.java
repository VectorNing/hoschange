package com.sesxh.hoschange.common.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 
 * @Title:WebSocketHandshakeInterceptor
 * @Description:WebSocket连接拦截器
 * @author Ning
 * @date 2017年3月17日
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception arg3) {

	}
	/**
	 * @date 2017年4月19日
	 * @author Ning
	 * WebSocket连接握手之前拦截
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			// 获取连接参数
			String queryStrings = ((ServletServerHttpRequest) request).getServletRequest().getQueryString();
			
			if (queryStrings != null) {
				//获取不同参数数组
				String[] queryStringsList = queryStrings.split("&");
				//遍历解析参数
				for (int i = 0; i < queryStringsList.length; i++) {
					//分割单个参数，获得参数的key和value
					String[] singleQueryString = queryStringsList[i].split("=");
					String qureyStringKey = singleQueryString[0];
					String queryStringValue = singleQueryString[1];
					//把参数装载进WebSocketSession中
					attributes.put(qureyStringKey, queryStringValue);
				}
			}
		}
		return true;
	}

}
