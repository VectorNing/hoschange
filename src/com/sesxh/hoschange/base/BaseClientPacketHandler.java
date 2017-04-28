package com.sesxh.hoschange.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.common.util.ClientJsonUtils;
import com.sesxh.hoschange.global.factories.WebsocketHandlerFactory;

/**
 * @Description: 客户端请求报文处理父类
 */
public abstract class BaseClientPacketHandler {
	@Autowired
	protected WebsocketHandlerFactory clientHandlerFactory;

	public final String handle(JSONObject JsonObject) throws BaseException {
		JSON returnJsonObject, paraJsonObject;
		Object paraModel, returnObject;
		String devicetype;

		paraJsonObject = JsonObject.getJSONObject("Parameter");

		devicetype = ClientJsonUtils.jsonToJsonObject(paraJsonObject).getString("DeviceType");
		
		paraModel = this.parseJsonObject(paraJsonObject);
		returnObject = this.bizProcess(paraModel);
		returnJsonObject = this.assembleJson(returnObject);

		return ClientJsonUtils.genReturnJsonPacket(returnJsonObject,devicetype);
	}

	protected abstract Object parseJsonObject(JSON paraJsonObject) throws BaseException;

	protected abstract Object bizProcess(Object paraModel) throws BaseException;

	protected abstract JSON assembleJson(Object returnObject) throws BaseException;

	/**
	 * @Description:注册方法
	 * @throws BaseException
	 * @return void
	 * @author lijiajia
	 * @date 2016年8月15日 下午2:50:37
	 */
	@PostConstruct
	protected abstract void register() throws BaseException;

}
