package com.sesxh.hoschange.biz.clienthandler.wardorbe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 发衣柜线程锁，控制发衣柜工作
* @Title:WardorbeLock 
* @Description: 
* @author Ning
* @date 2017年4月19日
 */
public class WardorbeLock {
	public final static Map<String, String> wardorbeLock = Collections.synchronizedMap(new HashMap<>());
}
