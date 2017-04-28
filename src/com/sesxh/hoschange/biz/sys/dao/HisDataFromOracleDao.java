package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;

public interface HisDataFromOracleDao {

	/**
	 * 获取人员信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> getAllPersonData() throws BaseException;

	/**
	 * 获取排班信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> getOperationSchedule() throws BaseException;

	/**
	 * 根据手术申请编号获取手术排班的人员
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> getOperationSchedulePerson(String operationNumber) throws BaseException;
}
