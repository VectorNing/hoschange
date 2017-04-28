package com.sesxh.hoschange.biz.hos.service;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosUserSch;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosUserSchService {
	public int insertHosUserSch(HosUserSch hosUserSch) throws BaseException;

	public int deleteHosUserSchById(Integer id) throws BaseException;

	public int updateHosUserSch(HosUserSch hosUserSch) throws BaseException;

	public DataSet selectHosUserSchSet(Map<String,Object> params)throws BaseException ;
}
