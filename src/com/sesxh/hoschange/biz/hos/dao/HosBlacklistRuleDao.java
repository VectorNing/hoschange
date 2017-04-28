package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.common.data.ParamMap;

/**
 * 进入名单规则设置
 * 
 * @author cyf
 * @date 2016年11月30日 下午6:29:31
 * @title HosBlacklistRuleDao.java
 *
 */
public interface HosBlacklistRuleDao {

	/**
	 * 根据模式 名单类别查询 规则
	 * 
	 * @param type
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	public HosBlacklistRule selectHosBlacklistRuleByModelAndType(String type, String model) throws BaseException;

	/**
	 * 加载名单规则列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectHosBlacklistRuleSet(ParamMap paramMap) throws BaseException;

	/**
	 * 统计
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	Long selectHosBlacklistRuleCount(ParamMap paramMap) throws BaseException;

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
	public List<HosBlacklistRule> selectEnabledHosBlacklistRule() throws BaseException;

	/**
	 * 修改黑名单模式，开启
	 * 
	 * @Title: updateBlacklistRuleMode
	 * @author Ning
	 * @Description:
	 * @data:2017年1月9日
	 */
	public int updateBlacklistRuleModeOn(HosBlacklistRule blacklistRule) throws BaseException;

	/**
	 * 修改黑名单模式，关闭
	 * 
	 * @Title: updateBlacklistRuleMode
	 * @author Ning
	 * @Description:
	 * @data:2017年1月9日
	 */
	public int updateBlacklistRuleModeOff(HosBlacklistRule blacklistRule) throws BaseException;

	/**
	 * 修改黑名单最近次数，模式一
	 * 
	 * @Title: updateHosBlacklistRuleLatelyNum
	 * @author Ning
	 * @Description:
	 * @data:2017年1月10日
	 */
	public int updateHosBlacklistRuleLatelyNumM1(HosBlacklistRule hosBlacklistRule) throws BaseException;

	/**
	 * 修改黑名单管理时间段（模式二）
	 * 
	 * @Title: updateHosBlacklistRuleRosterTimeM2
	 * @author Ning
	 * @Description:
	 * @data:2017年1月10日
	 */
	public int updateHosBlacklistRuleRosterTimeM2(HosBlacklistRule hosBlacklistRule) throws BaseException;

	/**
	 * 根据黑灰名单模式查询名单规则
	 * 
	 * @Title: selectHosBlacklistRuleByRosterMode
	 * @author Ning
	 * @Description:
	 * @data:2017年1月10日
	 */
	public List<HosBlacklistRule> selectHosBlacklistRuleByRosterMode(String rosterMode) throws BaseException;

	/**
	 * 加载黑名单列表
	 * 
	 * @Title: selectHosBlacklistRuleMode
	 * @author xn
	 * @Description:
	 * @data:2017年4月17日10:55:45
	 */
	public List<Map<String, Object>> queryBlackListResult() throws BaseException;
}
