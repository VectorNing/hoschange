package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosBlacklistRuleBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBlacklistRuleDao;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosBlacklistRuleBpoImpl")
public class HosBlacklistRuleBpoImpl extends SesframeBaseBpo implements HosBlacklistRuleBpo {

	@Autowired
	private HosBlacklistRuleDao hosBlacklistRuleDao;

	@Override
	public DataSet selectHosBlacklistRuleSetM1(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		pm.addParam("rosterModel", BizConst.BLACKLIST_MODEL.ONE);
		DataSet ds = DataSet.newDS(hosBlacklistRuleDao.selectHosBlacklistRuleCount(pm),
				hosBlacklistRuleDao.selectHosBlacklistRuleSet(pm));
		return ds;
	}

	@Override
	public DataSet selectHosBlacklistRuleSetM2(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		pm.addParam("rosterModel", BizConst.BLACKLIST_MODEL.TWO);
		DataSet ds = DataSet.newDS(hosBlacklistRuleDao.selectHosBlacklistRuleCount(pm),
				hosBlacklistRuleDao.selectHosBlacklistRuleSet(pm));
		return ds;
	}

	@Override
	public int insertHosBlacklistRule(HosBlacklistRule hosBlacklistRule) throws BaseException {
		return 0;
	}

	@Override
	public int updateHosBlacklistRuleM1(HosBlacklistRule hosBlacklistRule) throws BaseException {
		hosBlacklistRule.setRosterModel(BizConst.BLACKLIST_MODEL.ONE);
		hosBlacklistRuleDao.updateHosBlacklistRuleLatelyNumM1(hosBlacklistRule);
		return hosBlacklistRuleDao.updateHosBlacklistRuleM1(hosBlacklistRule);
	}

	@Override
	public int updateHosBlacklistRuleM2(HosBlacklistRule hosBlacklistRule) throws BaseException {
		hosBlacklistRule.setRosterModel(BizConst.BLACKLIST_MODEL.TWO);
		hosBlacklistRuleDao.updateHosBlacklistRuleRosterTimeM2(hosBlacklistRule);
		return hosBlacklistRuleDao.updateHosBlacklistRuleM2(hosBlacklistRule);
	}

	@Override
	public HosBlacklistRule selectHosBlacklistRuleById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosBlacklistRuleDao.selectHosBlacklistRuleById(id);
	}

	@Override
	public String selectEnabledHosBlacklistRule() throws BaseException {
		List<HosBlacklistRule> hosBlacklistRules = hosBlacklistRuleDao.selectEnabledHosBlacklistRule();
		String mode = null;
		for (HosBlacklistRule blacklistRule : hosBlacklistRules) {
			mode = blacklistRule.getRosterModel();
		}
		return mode;
	}

	@Override
	public int updateBlacklistRuleMode(HosBlacklistRule blacklistRule) throws BaseException {
		int on = hosBlacklistRuleDao.updateBlacklistRuleModeOn(blacklistRule);
		int off = hosBlacklistRuleDao.updateBlacklistRuleModeOff(blacklistRule);
		return (on + off);
	}

	@Override
	public List<Map<String, Object>> queryBlackListResult() throws BaseException {
		List<Map<String, Object>> queryBlackListResult = hosBlacklistRuleDao.queryBlackListResult();
		return queryBlackListResult;
	}
}
