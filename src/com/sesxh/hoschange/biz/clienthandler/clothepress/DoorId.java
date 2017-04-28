package com.sesxh.hoschange.biz.clienthandler.clothepress;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.BaseClientPacketHandler;
import com.sesxh.hoschange.biz.hos.service.HosClothesPressService;
import com.sesxh.hoschange.common.util.ClientJsonUtils;
@Component
public class DoorId extends BaseClientPacketHandler {
	@Autowired
	private HosClothesPressService hosClothesPressService;
	@Override
	protected Object parseJsonObject(JSON paraJsonObject) throws BaseException {
		JSONObject json = JSON.parseObject(paraJsonObject.toJSONString());
		Map<String, Object> loginpara = ClientJsonUtils.jsonObjectToMapObjckt(json);
		return loginpara;
	}

	@Override
	protected Object bizProcess(Object paraModel) throws BaseException {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paraModel);
		String deviceNumber=jsonObject.getString("DeviceID");
		return hosClothesPressService.getDoorIdByDeviceId(deviceNumber);
	}

	@Override
	protected JSON assembleJson(Object returnObject) throws BaseException {
		JSONObject json = ClientJsonUtils.ObjcktToJsonObject(returnObject);
		return json;
	}

	@Override
	protected void register() throws BaseException {
		this.clientHandlerFactory.register("DoorId", this.getClass());
	}

}
