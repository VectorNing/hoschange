package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosSchedulingBpo;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.hos.service.HosSchedulingService;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class HosSchedulingServiceImpl extends SesframeBaseService implements HosSchedulingService{

	@Autowired
	private HosSchedulingBpo hosSchedulingBpo;
	
	@Override
	@Transactional
	public int insertHosScheduling(HosScheduling hosScheduling) throws BaseException {
		return hosSchedulingBpo.insertHosOperation(hosScheduling);
	}

	@Override
	@Transactional
	public int deleteHosSchedulingById(Integer id) throws BaseException {
		return hosSchedulingBpo.deleteHosSchedulingById(id);
	}

	@Override
	@Transactional
	public int updateHosScheduling(HosScheduling hosScheduling) throws BaseException {
		return hosSchedulingBpo.updateHosScheduling(hosScheduling);
	}

	@Override
	@Transactional
	public DataSet selectHosSchedulingSet(Map<String, Object> params) throws BaseException {
		return hosSchedulingBpo.queryHosSchedulingSet(params);
	}

	@Override
	@Transactional
	public List<HosScheduling> queryHosSchedulingByTime(String begintime, String endtime) throws BaseException {
		return hosSchedulingBpo.queryHosSchedulingByTime(begintime, endtime);
	}

	@Override
	@Transactional
	public Object addUserToSchedul(String scheduleId, String[] ids) throws BaseException {
		return hosSchedulingBpo.addUserToSchedul(scheduleId, ids);
	}

	@Override
	@Transactional
	public DataSet showUserFromSchedul(String scheduleId) throws BaseException {
		return hosSchedulingBpo.showUserFromSchedul(scheduleId);
	}

}
