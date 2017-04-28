package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSchedulingPersonBpo;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingDao;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingPersonDao;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.hos.entity.HosSchedulingPerson;
import com.sesxh.hoschange.global.BizConst.SYNWAY;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosSchedulingPersonBpoImpl")
public class HosSchedulingPersonBpoImpl extends SesframeBaseBpo implements HosSchedulingPersonBpo {
	@Autowired
	HosSchedulingPersonDao hosSchedulingPersonDao;
	@Autowired
	HosSchedulingDao hosSchedulingDao;

	@Override
	public void insertHosSchedulingPerson(HosScheduling hos) throws BaseException {

		List<Map<String, Object>> listMap = hosSchedulingDao.selectIdByOperationNumber(hos.getOperationNumber());
		for (int i = 0; i < hos.getEmployees().size(); i++) {
			HosSchedulingPerson hsp = new HosSchedulingPerson();
			hsp.setOperschedulid(listMap.get(0).get("id") + "");
			hsp.setSynway(SYNWAY.AUTOMATIC);
			int userid = Integer.valueOf(hos.getEmployees().get(i));
			hsp.setUserid(userid);
			hosSchedulingPersonDao.insertHosSchedulingPerson(hsp);
		}
	}

	@Override
	public int deleteHosSchedulingPersonByOperschedulid(String operschedulid) throws BaseException {
		return hosSchedulingPersonDao.deleteHosSchedulingPersonByOperschedulid(operschedulid);
	}

	@Override
	public int deleteHosSchedulingPersonByOperschedulid(String operschedulid, Integer userid) throws BaseException {
		return hosSchedulingPersonDao.deleteHosSchedulingPersonByOperschedulid(operschedulid, userid);
	}

	@Override
	public List<HosSchedulingPerson> selectHosSchedulingPersonByOperschedulid(String operschedulid)
			throws BaseException {
		return hosSchedulingPersonDao.selectHosSchedulingPersonByOperschedulid(operschedulid);
	}

	@Override
	public int updateHosSchedulingPerson(HosScheduling hos) throws BaseException {
		List<Map<String, Object>> HosScheduling = hosSchedulingDao.selectIdByOperationNumber(hos.getOperationNumber());
		HosSchedulingPerson hsp = new HosSchedulingPerson();
		hsp.setSynway(SYNWAY.AUTOMATIC);
		hsp.setOperschedulid(HosScheduling.get(0).get("id") + "");
		for (int k = 0; k < hos.getEmployees().size(); k++) {
			int id = Integer.valueOf(hos.getEmployees().get(k));
			hsp.setUserid(id);
			int count = hosSchedulingPersonDao.selectHosSchedulingPersonCount(id + "", hsp.getOperschedulid());
			if (count == 0) {
				hosSchedulingPersonDao.insertHosSchedulingPerson(hsp);
			} else if (count == 1) {
			}
			List<HosSchedulingPerson> list = hosSchedulingPersonDao
					.selectHosSchedulingPersonByOperschedulid(hsp.getOperschedulid());
			List<String> listUserid = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				listUserid.add(list.get(i).getUserid() + "");
			}
			listUserid.removeAll(hos.getEmployees());
			for (int l = 0; l < listUserid.size(); l++) {
				int userid = Integer.valueOf(listUserid.get(l));
				hosSchedulingPersonDao.deleteHosSchedulingPersonByOperschedulid(hsp.getOperschedulid(), userid);
			}
		}
		return 0;
	}

}
