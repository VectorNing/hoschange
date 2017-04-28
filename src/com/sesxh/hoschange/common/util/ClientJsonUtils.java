package com.sesxh.hoschange.common.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.sesxh.common.util.StringUtils;

/**
 * 客户端Json数据解析与组装工具类
 * 
 * @author lijiajia
 */
public class ClientJsonUtils {

	/**
	 * 将字符串解析为json
	 * 
	 * @param jsonstr
	 * @return
	 */

	public static JSONObject getjsonObj(String jsonstr) {
		JSONObject json = new JSONObject();

		json = JSONObject.parseObject(jsonstr);

		return json;
	};

	/**
	 * 将字符串解析为JSONObject
	 * 
	 * @param jsonstr
	 * @return
	 */

	public static JSON getjson(String jsonstr) {
		JSON json = JSON.parseObject(jsonstr);

		return json;
	};

	/**
	 * 将字符串解析为json
	 *
	 * @param jsonstr
	 * @return
	 */

	public static JSONArray getjsonArr(String jsonstr) {
		JSONArray json = new JSONArray();

		json = JSONArray.parseArray(jsonstr);

		return json;
	};

	/**
	 * 获取请求方法名
	 * 
	 * @Description:
	 * @param json
	 * @return
	 * @return String
	 * @author lijiajia
	 * @date 2016年8月11日 下午3:33:34
	 */
	public static String getMethodName(JSONObject json) {
		String methodName = json.getString("Method");
		return methodName;
	};

	/**
	 * @Description:组装返回报文
	 * @param returnbodyjson
	 *            返回报文体
	 * @param errmessage
	 *            异常信息，没有时为空
	 * @return
	 * @return String
	 * @author lijiajia
	 * @date 2017年3月17日 上午11:13:56
	 */
	public static String genReturnxgJsonPacket(JSON returnbodyjson, String errmessage) {
		if (StringUtils.isEmpty(errmessage)) {
			errmessage = "null";
		}

		JSONObject returnjson = new JSONObject();
		returnjson.put("ErrorMessage", errmessage);
		returnjson.put("Parameter", returnbodyjson);

		return returnjson.toString();
	}

	public static String genReturnygJsonPacket(JSON returnbodyjson, String method) {
		if (StringUtils.isEmpty(method)) {
			method = "null";
		}

		JSONObject returnjson = new JSONObject();
		returnjson.put("method", method);
		returnjson.put("Parameter", returnbodyjson);

		return returnjson.toString();
	}

	/**
	 * @Description:组装无异常返回报文
	 * @param returnbodyjson
	 *            返回报文体
	 * @param errmessage
	 *            异常信息，没有时为空
	 * @return
	 * @return String
	 * @author lijiajia
	 * @date 2017年3月17日 上午11:13:56
	 */
	public static String genReturnJsonPacket(JSON returnbodyjson, String devicetype) {
		String returnstr = null;
		if (returnbodyjson == null) {
			return null;
		}
		returnstr = returnbodyjson.toJSONString();
		return returnstr;
	}

	/**
	 * @Description:将Json 类封装
	 * @param obj
	 * @return
	 * @return JSONObject
	 * @author lijiajia
	 * @date 2016年8月13日 下午2:42:48
	 */

	public static JSONObject ObjcktToJsonObject(Object obj) {
		ValueFilter filter = new ValueFilter() {
			@Override
			public Object process(Object obj, String s, Object v) {
				if (v == null)
					return "";
				return v;
			}
		};
		String string = JSON.toJSONString(obj, filter);
		return JSONObject.parseObject(string);

	}

	/**
	 * 
	 * @Description:将List<T> 封装为JSONArray
	 * 
	 * @param list
	 * @return
	 * @return JSONArray
	 * @author lijiajia
	 * @date 2016年8月15日 下午3:08:15
	 */
	public static <T> JSONArray listObjcktToJsonObject(List<T> list) {
		ValueFilter filter = new ValueFilter() {
			@Override
			public Object process(Object obj, String s, Object v) {
				if (v == null)
					return "";
				return v;
			}
		};
		String jsonText = JSON.toJSONString(list, filter);
		JSONArray jsonarr = JSONArray.parseArray(jsonText);
		return jsonarr;
	}

	/**
	 * 
	 * @Description: json 转map
	 * @param json
	 * @return
	 * @return Map
	 * @author wangzehao
	 * @date 2016年8月15日 下午4:20:56
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonObjectToMapObjckt(JSONObject json) {
		String jsonStr = json.toString();
		Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
		return map;
	}

	/**
	 * 
	 * @Description:json 转JSONObject
	 * @param json
	 * @return
	 * @return JSONObject
	 * @author wangzehao
	 * @date 2016年8月17日 下午12:29:32
	 */
	public static JSONObject jsonToJsonObject(JSON json) {
		return (JSONObject) json;
	}

	/**
	 * @Description:json 转JSONArray
	 * @param json
	 * @return
	 * @return JSONArray
	 * @author wangzehao
	 * @date 2016年8月17日 下午12:42:11
	 */
	public static JSONArray jsonToJsonArray(JSON json) {
		return (JSONArray) json;
	}

}
