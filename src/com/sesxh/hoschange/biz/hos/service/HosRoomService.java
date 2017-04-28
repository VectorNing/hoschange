package com.sesxh.hoschange.biz.hos.service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;

public interface HosRoomService {
	/**
	 * 增加手术区
	* @Title: addHosArea
	* @author Ning 
	* @data:2017年2月15日
	* @return:int
	* @throws:
	 */
	public int addHosRoom(HosRoom hosRoom) throws BaseException;
	/**
	 * 
	* @Title: selectHosRoomByRoomId
	* @author Ning 
	* @data:2017年2月16日
	* @return:HosRoom
	* @throws:
	 */
	public HosRoom selectHosRoomByRoomId(Integer roomId) throws BaseException;
}
