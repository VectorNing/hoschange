package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.LogError;
import com.sesxh.hoschange.common.data.ParamMap;

public interface ILogErrorDao {

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
	public void insertLogError(LogError logError) throws BaseException;

	/**
	 * 删除错误日志信息
	 * 
	 * @param id
	 * @throws BaseException
	 */
	public void deletLogError(Long id) throws BaseException;

	/**
	 * 统计错误日志信息
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectLogErrCount(ParamMap paramMap) throws BaseException;

	/**
	 * 分页加载错误日志列表
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectLogErrLists(ParamMap paramMap) throws BaseException;
}
