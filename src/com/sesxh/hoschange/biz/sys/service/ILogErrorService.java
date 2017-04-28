package com.sesxh.hoschange.biz.sys.service;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.LogError;
import com.sesxh.hoschange.common.data.DataSet;

public interface ILogErrorService {

	/**
	 * 根据编号加载错误日志信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public LogError loadLogError(Integer id) throws BaseException;

	/**
	 * 添加错误日志信息
	 * 
	 * @param logError
	 * @throws BaseException
	 */
	public void createLogError(LogError logError) throws BaseException;

	/**
	 * 删除错误日志信息
	 * 
	 * @param id
	 * @throws BaseException
	 */
	public void deletLogError(Integer id) throws BaseException;

	/**
	 * 加载错误日志数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadLogErrorList(Map<String, Object> params) throws BaseException;

}