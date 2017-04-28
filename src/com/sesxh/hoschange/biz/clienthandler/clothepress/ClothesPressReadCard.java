package com.sesxh.hoschange.biz.clienthandler.clothepress;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.BaseClientPacketHandler;
import com.sesxh.hoschange.biz.hos.controller.device.HosClothePressSelfService;
import com.sesxh.hoschange.common.util.ClientJsonUtils;

@Component
public class ClothesPressReadCard extends BaseClientPacketHandler {
	@Autowired
	private HosClothePressSelfService hosClothePressSelfService;

	@Override
	protected Object parseJsonObject(JSON paraJson) throws BaseException {
		JSONObject json = JSON.parseObject(paraJson.toJSONString());
		Map<String, Object> loginpara = ClientJsonUtils.jsonObjectToMapObjckt(json);
		return loginpara;
	}

	@Override
	protected Object bizProcess(Object paraJsonObject) throws BaseException {
		Object object = new Object();
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paraJsonObject);
		object = hosClothePressSelfService.ClothesPress(jsonObject);
		return object;
	}

	@Override
	protected JSON assembleJson(Object returnObject) throws BaseException {
		JSONObject json = ClientJsonUtils.ObjcktToJsonObject(returnObject);
		return json;
	}

	@Override
	protected void register() throws BaseException {
		this.clientHandlerFactory.register("ClothesPressReadCard", this.getClass());
	}
}
