package com.sesxh.hoschange.common.data;

/**
 * 查询类型
 * @author zhaohuatai
 */
public  enum QueryType {
	S_O_C,//分页 + count
	S_O_NC,//分页 + no count
	S_NO_C,//不分页  + count
	S_NO_NC,//不分页  + no count
}