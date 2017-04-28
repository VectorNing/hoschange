package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.common.data.ParamMap;

/**
 * 灰/黑名单
 * 
 * @author cyf
 * @date 2016年11月30日 下午6:29:14
 * @title HosBlacklistDao.java
 *
 */
public interface HosBlacklistDao {

	/**
	 * 添加名单信息
	 * 
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public int insertHosBlacklist(HosBlacklist hosBlacklist) throws BaseException;

	/**
	 * 根据用户名id 查询 该用户是否在黑名单
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            黑名单
	 * @return
	 * @throws BaseException
	 */
	public HosBlacklist selectHosBlacklistByUserIdAndType(Integer userId, String type) throws BaseException;

	/**
	 * 修改名单信息 修改状态
	 * 
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public int updateHosBlacklist(HosBlacklist hosBlacklist) throws BaseException;

	/**
	 * 加载黑灰名单列表
	 * 
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectHosBlacklistSet(ParamMap pm) throws BaseException;

	/**
	 * 统计
	 * 
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public Long selectHosBlacklistCount(ParamMap pm) throws BaseException;

	/**
	 * 设置显示在大屏幕
	 * 
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public int displayScreenHosBacklist(HosBlacklist hosBlacklist) throws BaseException;
}
