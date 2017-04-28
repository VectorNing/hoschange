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
/**
 * 调用硬件后。硬件返回调用成功信息
* @Title:DeliveryReturn 
* @Description: 
* @author Ning
* @date 2017年4月19日
 */
@Component
public class DeliveryReturn extends BaseClientPacketHandler {
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
		hosWarbelSelfService.deliveryReturn(jsonObject);
		return null;
	}

	@Override
	protected JSON assembleJson(Object returnObject) throws BaseException {
		JSONObject json = ClientJsonUtils.ObjcktToJsonObject(returnObject);
		return json;
	}

	@Override
	protected void register() throws BaseException {
		this.clientHandlerFactory.register("DeliveryReturn", this.getClass());

	}

}
