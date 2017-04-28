package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosBlacklistRuleBpo;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.biz.hos.service.HosBlacklistRuleService;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class HosBlacklistRuleServiceImpl extends SesframeBaseService implements HosBlacklistRuleService {

	@Autowired
	private HosBlacklistRuleBpo hosBlacklistRuleBpo;

	@Override
	@Transactional
	public DataSet selectHosBlacklistRuleSetM1(Map<String, Object> params) throws BaseException {
		return hosBlacklistRuleBpo.selectHosBlacklistRuleSetM1(params);
	}

	@Override
	@Transactional
	public DataSet selectHosBlacklistRuleSetM2(Map<String, Object> params) throws BaseException {
		return hosBlacklistRuleBpo.selectHosBlacklistRuleSetM2(params);
	}

	@Override
	@Transactional
	public int insertHosBlacklistRule(HosBlacklistRule hosBlacklistRule) throws BaseException {
		return hosBlacklistRuleBpo.insertHosBlacklistRule(hosBlacklistRule);
	}

	@Override
	@Transactional
	public int updateHosBlacklistRuleM1(HosBlacklistRule hosBlacklistRule) throws BaseException {
		return hosBlacklistRuleBpo.updateHosBlacklistRuleM1(hosBlacklistRule);
	}

	@Override
	@Transactional
	public int updateHosBlacklistRuleM2(HosBlacklistRule hosBlacklistRule) throws BaseException {
		return hosBlacklistRuleBpo.updateHosBlacklistRuleM2(hosBlacklistRule);
	}

	@Override
	@Transactional
	public HosBlacklistRule selectHosBlacklistRuleById(Integer id) throws BaseException {
		return hosBlacklistRuleBpo.selectHosBlacklistRuleById(id);
	}

	@Override
	@Transactional
	public String selectHosBlacklistRuleMode() throws BaseException {
		return hosBlacklistRuleBpo.selectEnabledHosBlacklistRule();
	}

	@Override
	@Transactional
	public int updateBlacklistRuleMode(HosBlacklistRule blacklistRule) throws BaseException {
		return hosBlacklistRuleBpo.updateBlacklistRuleMode(blacklistRule);
	}

	@Override
	@Transactional
	public List<Map<String, Object>> queryBlackListResult() throws BaseException {
		return hosBlacklistRuleBpo.queryBlackListResult();
	}
}
