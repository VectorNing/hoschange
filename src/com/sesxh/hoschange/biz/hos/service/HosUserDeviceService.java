package com.sesxh.hoschange.biz.hos.service;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosUserDevice;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosUserDeviceService {
	public int insertHosUserDevice(HosUserDevice hosUserDevice) throws BaseException;

	public int deleteHosUserDeviceById(Integer id) throws BaseException;

	public int updateHosUserDevice(HosUserDevice hosUserDevice) throws BaseException;

	public DataSet selectHosUserDeviceSet(Map<String,Object> params)throws BaseException ;
}
