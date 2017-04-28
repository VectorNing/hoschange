package com.sesxh.hoschange.biz.sys.bpo;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.LogError;
import com.sesxh.hoschange.common.data.DataSet;

public interface ILogErrorBpo {

	/**
	 * 根据编号加载错误日志信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public LogError selectLogError(Integer id) throws BaseException;

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
	public void deletLogError(Long id) throws BaseException;

	/**
	 * 加载错误日志数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadLogErrSet(Map<String, Object> params) throws BaseException;
}
