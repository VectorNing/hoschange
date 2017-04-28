package com.sesxh.hoschange.biz.hos.service;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosPowerService {

	/**
	 * 添加权限
	 * 
	 * @param hosPower
	 * @throws BaseException
	 */
	public void insertPower(HosScheduling hosScheduling) throws BaseException;

	/**
	 * 修改权限
	 * 
	 * @param hosPower
	 * @return
	 * @throws BaseException
	 */
	public int updatePower(HosScheduling hosScheduling) throws BaseException;

	/**
	 * 
	 * @param userid
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int deleteInvilidPower(String userid, String operschedulid) throws BaseException;

	/**
	 * 
	 * @Title: queryHosPowerSet
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:DataSet
	 * @throws:
	 */
	public DataSet queryHosPowerSet(Map<String, Object> params) throws BaseException;

	/**
	 * 为跟台人员添加权限
	 * 
	 * @Title: addFollower
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:int
	 * @throws:
	 */
	public String addFollower(String userId, String schedulid) throws BaseException;
	
	public String addTemporaries(String userId,String endtime,String begintime) throws BaseException;
}
