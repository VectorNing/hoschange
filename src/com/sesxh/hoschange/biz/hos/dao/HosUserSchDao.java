package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosUserSch;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosUserSchDao {
	public int insertHosUserSch(HosUserSch hosUserSch) throws BaseException;

	public int deleteHosUserSchById(Integer id) throws BaseException;

	public int updateHosUserSch(HosUserSch hosUserSch) throws BaseException;

	public List<Map<String,Object>> selectAllHosUserSch(ParamMap paramMap) throws BaseException;

	public Long HosUserSchCount(ParamMap paramMap) throws BaseException;
}
