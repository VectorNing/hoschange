package com.sesxh.hoschange.global.factories;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sesxh.common.exception.AppException;
import com.sesxh.hoschange.base.BaseClientPacketHandler;

/**
 * @Description:websocket注册工厂
 * @author lijiajia
 * @date 2017年3月17日 上午10:53:45
 */
@Component("com.sesxh.base.global.factories.WebsocketHandlerFactory")
public class WebsocketHandlerFactory {
	private final HashMap<String, Class<?>> mapping = new HashMap<String, Class<?>>();
	@Autowired
	private ApplicationContext applicationContext;

	public BaseClientPacketHandler getClientHandler(String method) throws AppException {
		if (!mapping.containsKey(method)) {
			throw new AppException("未查找到方法[" + method + "]对应的Handler类！");
		}
		return (BaseClientPacketHandler) applicationContext.getBean(mapping.get(method));
	}

	public void register(String method, Class<?> pclass) throws AppException {
		if ((method == null) || "".equals(method)) {
			throw new AppException("传入的参数[method]为空！");
		}
		if (mapping.containsKey(method)) {
			throw new AppException("方法[" + method + "]已经注册，无法再次注册！");
		}
		if (pclass == null) {
			throw new AppException("传入的参数[pclass]为空！");
		}
		mapping.put(method, pclass);
	}
}
