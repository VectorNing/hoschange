package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSchedulingBpo;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingDao;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingPersonDao;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.hos.entity.HosSchedulingPerson;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosSchedulingBpoImpl")
public class HosSchedulingBpoImpl extends SesframeBaseBpo implements HosSchedulingBpo {

	@Autowired
	private HosSchedulingDao hosSchedulingDao;
	@Autowired
	private HosSchedulingPersonDao hosSchedulingPersonDao;

	@Override
	public int insertHosOperation(HosScheduling hosScheduling) throws BaseException {
		return hosSchedulingDao.insertHosScheduling(hosScheduling);
	}

	@Override
	public int deleteHosSchedulingById(Integer id) throws BaseException {
		return hosSchedulingDao.deleteHosSchedulingById(id);
	}

	@Override
	public int deleteHosSchedulingByOperationNumber(String operationNumber) throws BaseException {

		return hosSchedulingDao.deleteHosSchedulingByOperationNumber(operationNumber);
	}

	@Override
	public int updateHosScheduling(HosScheduling hosScheduling) throws BaseException {
		// TODO Auto-generated method stub
		return hosSchedulingDao.updateHosScheduling(hosScheduling);
	}

	@Override
	public DataSet queryHosSchedulingSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String name = pm.getStrParam("name");
		String type = pm.getStrParam("type");
		pm.updateParam("type", type);
		String operationNumber = pm.getStrParam("operationNumber");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String nowtime = df.format(new Date());
		pm.updateParam("nowtime", nowtime);

		if (name != null) {
			pm.updateParam("name", "%" + name + "%");
		}
		if (operationNumber != null) {
			pm.updateParam("operationNumber", "%" + operationNumber + "%");
		}
		DataSet ds = DataSet.newDS(hosSchedulingDao.HosHosSchedulingCount(pm),
				hosSchedulingDao.selectAllHosScheduling(pm));
		return ds;
	}

	@Override
	public List<String> selectSchedulByOperationTime() throws BaseException {
		List<String> listOperschedulid = new ArrayList<String>();
		List<Map<String, Object>> listMap = hosSchedulingDao.selectSchedulByOperationTime();
		for (int i = 0; i < listMap.size(); i++) {
			listOperschedulid.add((String) listMap.get(i).get("operationNumber"));
		}
		return listOperschedulid;
	}

	@Override
	public String selectIdByOperationNumber(String operationNumber) throws BaseException {
		List<Map<String, Object>> listMap = hosSchedulingDao.selectIdByOperationNumber(operationNumber);
		return listMap.get(0).get("id") + "";
	}

	@Override
	public List<HosScheduling> queryHosSchedulingByTime(String begintime, String endtime) throws BaseException {
		if (StringUtils.isEmpty(begintime)) {
			throw new BusinessException("请输入起始时间");
		}
		if (StringUtils.isEmpty(endtime)) {
			throw new BusinessException("请输入结束时间");
		}
		begintime = begintime.replace("-", "").replace(":", "").replace(" ", "");
		endtime = endtime.replace("-", "").replaceAll(":", "").replace(" ", "");
		List<HosScheduling> list = hosSchedulingDao.queryHosSchedulingByTime(begintime, endtime);
		return list;
	}

	@Override
	public Object addUserToSchedul(String scheduleId, String[] ids) throws BaseException {
		HosSchedulingPerson hosSchedulingPerson = new HosSchedulingPerson();
		for (String id : ids) {
			List<Map<String, Object>> list = hosSchedulingDao.selectUserNameByUserId(id, scheduleId);
			if (list.size() > 0) {
				String userName = (String) list.get(0).get("userName");
				throw new BusinessException(userName + "已在本次手术中，请勿重复添加");
			} else {
				hosSchedulingPerson.setOperschedulid(scheduleId);
				hosSchedulingPerson.setSynway(BizConst.SYNWAY.HAND);
				hosSchedulingPerson.setUserid(Integer.parseInt(id));
				hosSchedulingPersonDao.insertHosSchedulingPerson(hosSchedulingPerson);
			}
		}
		return null;
	}

	@Override
	public DataSet showUserFromSchedul(String scheduleId) throws BaseException {

		DataSet ds = DataSet.newDS(hosSchedulingDao.showUserFromSchCount(scheduleId),
				hosSchedulingDao.showUserFromSch(scheduleId));
		return ds;
	}
}
