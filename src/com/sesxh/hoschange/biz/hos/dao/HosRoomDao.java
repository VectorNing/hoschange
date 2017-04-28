package com.sesxh.hoschange.biz.hos.dao;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;

public interface HosRoomDao {
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
	 * 删除更衣室
	* @Title: deleteHosRoomByNumber
	* @author Ning 
	* @data:2017年2月16日
	* @return:int
	* @throws:
	 */
	public int deleteHosRoomByTheaterNumber(String number) throws BaseException;
	/**
	 * 修改更衣室
	* @Title: updateHosRoomByTheaterNumber
	* @author Ning 
	* @data:2017年2月16日
	* @return:int
	* @throws:
	 */
	public int updateHosRoomByTheaterNumber(String oldNumber,String newNumber) throws BaseException;
	/**
	 * 
	* @Title: selectHosRoomByTheaterAndRoomId
	* @author Ning 
	* @data:2017年2月16日
	* @return:HosRoom
	* @throws:
	 */
	public HosRoom selectHosRoomByTheaterAndRoomId(String theaterNumber,Integer roomId) throws BaseException;
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
