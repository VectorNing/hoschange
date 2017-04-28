package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosBlacklistRuleService {

	/**
	 * 加载名单规则列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectHosBlacklistRuleSetM1(Map<String, Object> params) throws BaseException;

	public DataSet selectHosBlacklistRuleSetM2(Map<String, Object> params) throws BaseException;

	/**
	 * 添加黑灰名单规则
	 * 
	 * @param hosBlacklistRule
	 * @return
	 * @throws BaseException
	 */
	public int insertHosBlacklistRule(HosBlacklistRule hosBlacklistRule) throws BaseException;

	/**
	 * 更新黑灰名单规则
	 * 
	 * @param hosBlacklistRule
	 * @return
	 * @throws BaseException
	 */
	public int updateHosBlacklistRuleM1(HosBlacklistRule hosBlacklistRule) throws BaseException;

	public int updateHosBlacklistRuleM2(HosBlacklistRule hosBlacklistRule) throws BaseException;

	/**
	 * 根据id 查询规则信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosBlacklistRule selectHosBlacklistRuleById(Integer id) throws BaseException;

	/**
	 * 查询黑名单模式
	 * 
	 * @Title: selectHosBlacklistRuleMode
	 * @author Ning
	 * @Description:
	 * @data:2017年1月9日
	 */
	public String selectHosBlacklistRuleMode() throws BaseException;

	/**
	 * 修改黑名单模式
	 * 
	 * @Title: updateBlacklistRuleMode
	 * @author Ning
	 * @Description:
	 * @data:2017年1月9日
	 */
	public int updateBlacklistRuleMode(HosBlacklistRule blacklistRule) throws BaseException;

	/**
	 * 加载黑名单列表
	 * 
	 * @Title: selectHosBlacklistRuleMode
	 * @author xn
	 * @Description:
	 * @data:2017年4月17日10:47:37
	 */
	public List<Map<String, Object>> queryBlackListResult() throws BaseException;
}
