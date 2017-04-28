package com.sesxh.hoschange.biz.hos.dao;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;

public interface HosBusinessDao {

	/**
	 * 添加业务 操作日志
	 * @param bus
	 * @return
	 * @throws BaseException
	 */
	public int insertDeviceLog(HosBusiness bus) throws BaseException ;
	
	/**
	 * 查询规定时间段内是否重复刷卡
	 * @param brushCardTime 不能重复刷卡规定时间段
	 * @param userId 用户id
	 * @param deviceType 设备类型
	 * @param deviceNumber 设备编码
	 * @return
	 * @throws BaseException
	 */
	public Long selectHosRecordForBrushCardTimes(String brushCardTime,Integer userId,String deviceType,String deviceNumber) throws BaseException;
	
	/**
	 * 查询最后一条日志信息根据用户id和设备类型
	 * @author xujialong
	 * @param userId 用户id
	 * @param deviceType 设备类型
	 * @return
	 * @throws BaseException
	 */
	public HosBusiness selectDeviceLogByUseridAndType(Integer userId,String deviceType) throws BaseException;
	
	/**
	 * 通过日志id修改日志信息为领取异常
	 * @author xujialong
	 * @return
	 * @throws BaseException
	 */
	public int updateDeviceLogById(HosBusiness bus) throws BaseException;
}
