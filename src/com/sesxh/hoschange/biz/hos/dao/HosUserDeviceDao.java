package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosUserDevice;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosUserDeviceDao {
	
	/**
	 * 用户设备
	 * @param hosUserDevice
	 * @return
	 * @throws BaseException
	 */
	public int insertHosUserDevice(HosUserDevice hosUserDevice) throws BaseException;

	public int deleteHosUserDeviceById(Integer id) throws BaseException;

	public int updateHosUserDevice(HosUserDevice hosUserDevice) throws BaseException;

	public List<Map<String,Object>> selectAllHosUserDevice(ParamMap paramMap) throws BaseException;

	public Long HosUserDeviceCount(ParamMap paramMap) throws BaseException;
}
