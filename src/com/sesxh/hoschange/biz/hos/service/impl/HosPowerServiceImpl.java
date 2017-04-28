package com.sesxh.hoschange.biz.hos.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSchedulingBpo;
import com.sesxh.hoschange.biz.hos.entity.HosPower;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.hos.service.HosPowerService;
import com.sesxh.hoschange.biz.sys.bpo.SysConfigBpo;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst.SYNWAY;
import com.sesxh.hoschange.global.BizConst.SYSCONFIG;

@Service("com.sesxh.hoschange.biz.hos.service.impl.HosPowerServiceImpl")
public class HosPowerServiceImpl extends SesframeBaseService implements HosPowerService {
	@Autowired
	HosPowerBpo hosPowerBpo;
	@Autowired
	HosSchedulingBpo hosSchedulingBpo;
	@Autowired
	SysConfigBpo sysConfigBpo;

	@Override
	public void insertPower(HosScheduling hos) throws BaseException {
		HosPower hosPower = new HosPower();
		hosPower.setSynway(SYNWAY.AUTOMATIC);
		SysConfig sysConfig = sysConfigBpo.querySysConfigByConfigName(SYSCONFIG.POWERVALIDTIME);
		Integer powerValidTime = Integer.valueOf(sysConfig.getConfig());
		String time = addDate(hos.getOperationtime(), -powerValidTime);// 手术时间往前提前一小时
		hosPower.setBegintime(time);
		hosPower.setEndtime(hos.getOperationtime());
		String id = hosSchedulingBpo.selectIdByOperationNumber(hos.getOperationNumber());
		hosPower.setOperschedulid(id);
		for (int k = 0; k < hos.getEmployees().size(); k++) {
			hosPower.setUserid(hos.getEmployees().get(k));
			hosPowerBpo.insertPower(hosPower);
		}
	}

	@Override
	public int updatePower(HosScheduling hos) throws BaseException {
		HosPower hosPower = new HosPower();
		hosPower.setSynway(SYNWAY.AUTOMATIC);
		SysConfig sysConfig = sysConfigBpo.querySysConfigByConfigName(SYSCONFIG.POWERVALIDTIME);
		Integer powerValidTime = Integer.valueOf(sysConfig.getConfig());
		String time = addDate(hos.getOperationtime(), -powerValidTime);// 手术时间往前提前一小时
		hosPower.setBegintime(time);
		hosPower.setEndtime(hos.getOperationtime());
		String id = hosSchedulingBpo.selectIdByOperationNumber(hos.getOperationNumber());
		hosPower.setOperschedulid(id + "");
		for (int k = 0; k < hos.getEmployees().size(); k++) {
			hosPower.setUserid(hos.getEmployees().get(k));
			int count = hosPowerBpo.selectPowerCount(hos.getEmployees().get(k), hosPower.getOperschedulid());
			if (count == 0) {
				hosPowerBpo.insertPower(hosPower);
			} else if (count == 1) {
				hosPowerBpo.updatePower(hosPower);
			}
			List<String> listUserid = hosPowerBpo.selectPowerJobNumber(hosPower.getOperschedulid());
			listUserid.removeAll(hos.getEmployees());
			for (int l = 0; l < listUserid.size(); l++) {
				hosPowerBpo.deleteInvilidPower(listUserid.get(l), hosPower.getOperschedulid());
			}
		}
		return 0;
	}

	@Override
	public int deleteInvilidPower(String jobNumber, String operschedulid) throws BaseException {
		return hosPowerBpo.deleteInvilidPower(jobNumber, operschedulid);

	}

	private String addDate(String day, int x) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
		date = cal.getTime();
		cal = null;
		return format.format(date);
	}

	@Override
	public DataSet queryHosPowerSet(Map<String, Object> params) throws BaseException {
		return hosPowerBpo.queryHosPowerSet(params);
	}

	@Override
	public String addFollower(String userId, String schedulid) throws BaseException {
		return hosPowerBpo.addFollower(userId, schedulid);
	}

	@Override
	public String addTemporaries(String userId, String endtime, String begintime) throws BaseException {
		return hosPowerBpo.addTemporaries(userId, endtime, begintime);
	}
}
