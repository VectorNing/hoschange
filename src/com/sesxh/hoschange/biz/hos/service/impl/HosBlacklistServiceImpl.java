package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosBlacklistBpo;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.biz.hos.service.HosBlacklistService;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class HosBlacklistServiceImpl extends SesframeBaseService implements HosBlacklistService {

	@Autowired
	private HosBlacklistBpo hosBlacklistBpo;

	@Override
	@Transactional
	public int insertHosBlacklist(HosBlacklist hosBlacklist) throws BaseException {
		return hosBlacklistBpo.insertHosBlacklist(hosBlacklist);
	}

	@Override
	@Transactional
	public HosBlacklist selectHosBlacklistByUserIdAndType(Integer userId, String type) throws BaseException {
		return hosBlacklistBpo.selectHosBlacklistByUserIdAndType(userId, type);
	}

	@Override
	@Transactional
	public int updateHosBlacklist(HosBlacklist hosBlacklist) throws BaseException {
		return hosBlacklistBpo.updateHosBlacklist(hosBlacklist);
	}

	@Override
	@Transactional
	public DataSet selcetHosBlacklistSet(Map<String, Object> params) throws BaseException {
		return hosBlacklistBpo.selcetHosBlacklistSet(params);
	}

	@Override
	@Transactional
	public int displayScreenHosBacklist(String[] ids, String display) throws BaseException {
		return hosBlacklistBpo.displayScreenHosBacklist(ids, display);
	}
}
