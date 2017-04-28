package com.sesxh.hoschange.biz.clienthandler.wardorbe;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.BaseClientPacketHandler;
import com.sesxh.hoschange.biz.hos.controller.device.HosWarbelSelfService;
import com.sesxh.hoschange.common.util.ClientJsonUtils;
@Component
public class WardorbeWarning extends BaseClientPacketHandler {
	@Autowired
	private HosWarbelSelfService hosWarbelSelfService;
	@Override
	protected Object parseJsonObject(JSON paraJsonObject) throws BaseException {
		JSONObject json = JSON.parseObject(paraJsonObject.toJSONString());
		Map<String, Object> loginpara = ClientJsonUtils.jsonObjectToMapObjckt(json);
		return loginpara;
	}

	@Override
	protected Object bizProcess(Object paraModel) throws BaseException {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paraModel);
		hosWarbelSelfService.wardorbeWarning(jsonObject);
		return null;
	}

	@Override
	protected JSON assembleJson(Object returnObject) throws BaseException {
		JSONObject json = ClientJsonUtils.ObjcktToJsonObject(returnObject);
		return json;
	}

	@Override
	protected void register() throws BaseException {
		this.clientHandlerFactory.register("WardorbeWarning", this.getClass());

	}

}
