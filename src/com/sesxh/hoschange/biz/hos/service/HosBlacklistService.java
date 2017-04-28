package com.sesxh.hoschange.biz.hos.service;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosBlacklistService {

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
	 * 加载数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selcetHosBlacklistSet(Map<String, Object> params) throws BaseException;

	/**
	 * 设置显示在大屏幕
	 * 
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public int displayScreenHosBacklist(String[] ids, String display) throws BaseException;
}
