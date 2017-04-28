package com.sesxh.hoschange.common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class tess {
	public static void main(String[] args) {
		List<String> list =new ArrayList<>();
		list.add("001");
		list.add("002");
		list.add("003");
		Message message= new Message();
		message.setErrorMessage("null");
		message.setMessage("管理员打开空柜");
		message.setDeviceID("11223344");
		message.setOperation(list);
		Map<String, Object> map =new HashMap<>();
		map.put("ErrorMessage", message.getErrorMessage());
		map.put("Parameter", message);
		System.out.println(JSON.toJSONString(map));
	}
}
