package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.hos.dao.HosPowerDao;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingDao;
import com.sesxh.hoschange.biz.hos.entity.HosPower;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosPowerBpoImpl")
public class HosPowerBpoImpl extends SesframeBaseBpo implements HosPowerBpo {
	@Autowired
	private HosPowerDao hosPowerDao;
	@Autowired
	private HosSchedulingDao HosSchedulingDao;
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public int deleteInvilidPower(String time) throws BaseException {
		return hosPowerDao.deleteInvilidPower(time);

	}

	@Override
	public int insertPower(HosPower hosPower) throws BaseException {
		return hosPowerDao.insertPower(hosPower);

	}

	@Override
	public int updatePower(HosPower hosPower) throws BaseException {
		return hosPowerDao.updatePower(hosPower);

	}

	@Override
	public int selectPowerCount(String userid, String operschedulid) throws BaseException {
		return hosPowerDao.selectPowerCount(userid, operschedulid);
	}

	@Override
	public List<String> selectPowerJobNumber(String operschedulid) throws BaseException {
		List<String> listJobNumber = new ArrayList<String>();
		List<Map<String, Object>> listMap = hosPowerDao.selectPowerUserid(operschedulid);
		for (int i = 0; i < listMap.size(); i++) {
			listJobNumber.add((String) listMap.get(i).get("userid"));
		}
		return listJobNumber;
	}

	@Override
	public int deleteInvilidPower(String userid, String operschedulid) throws BaseException {

		return hosPowerDao.deleteInvilidPower(userid, operschedulid);
	}

	@Override
	public DataSet queryHosPowerSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String username = pm.getStrParam("username");
		String begintime = pm.getStrParam("begintime");
		String endtime = pm.getStrParam("endtime");
		String roomname = pm.getStrParam("roomname");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String nowtime = df.format(new Date());
		pm.updateParam("nowtime", nowtime);
		if (username != null) {
			pm.updateParam("username", "%" + username + "%");
		}
		if (begintime != null) {
			begintime = begintime.replace("-", "").replace(":", "").replace(" ", "");
			pm.updateParam("begintime", begintime);
		}
		if (endtime != null) {
			endtime = endtime.replace("-", "").replace(":", "").replace(" ", "");
			pm.updateParam("endtime", endtime);
		}
		if (roomname != null) {
			pm.updateParam("roomname", "%" + roomname + "%");
		}

		DataSet ds = DataSet.newDS(hosPowerDao.HosPowerCount(pm), hosPowerDao.queryHosPowerAll(pm));
		return ds;

	}

	@Override
	public String addFollower(String userId, String schedulid) throws BaseException {
		HosPower hosPower = new HosPower();
		if (StringUtils.isEmpty(userId)) {
			throw new BusinessException("请选择相关人员");
		}
		if (StringUtils.isEmpty(schedulid)) {
			throw new BusinessException("请选择手术");
		}
		int count=hosPowerDao.selectPowerCount(userId, schedulid);
		if(count>0){
			return "此人员已在本次手术中";
		}
		Integer preTime = Integer
				.parseInt(sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.POWERVALIDTIME).getConfig());
		HosScheduling hosScheduling = HosSchedulingDao.selectHosSchedulingById(schedulid);
		String endtime = hosScheduling.getOperationtime();
		endtime=endtime.replace("-", "").replace(" ", "").replace(":", "");
		String begintime = addDate(endtime, -preTime);
		hosPower.setUserid(userId);
		hosPower.setOperschedulid(schedulid);
		hosPower.setBegintime(begintime);
		hosPower.setEndtime(endtime);
		hosPower.setSynway(BizConst.SYNWAY.HAND);
			 hosPowerDao.insertPower(hosPower);
		return "添加成功";
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
	public int selectIfHavePower(String userid) throws BaseException {
		return hosPowerDao.selectIfHavePower(userid);
	}

	@Override
	public String addTemporaries(String userId, String endtime, String begintime) throws BaseException {
		HosPower hosPower = new HosPower();
		if (StringUtils.isEmpty(userId)) {
			return "请选择相关人员";
		}
		if (StringUtils.isEmpty(begintime)) {
			return "请选择权限开始时间";
		}
		if (StringUtils.isEmpty(endtime)) {
			return "请选择权限结束时间";
		}
		endtime=endtime.replace("-", "").replace(" ", "").replace(":", "");
		begintime=begintime.replace("-", "").replace(" ", "").replace(":", "");
		hosPower.setUserid(userId);
		hosPower.setBegintime(begintime);
		hosPower.setEndtime(endtime);
		hosPower.setSynway(BizConst.SYNWAY.HAND);
			 hosPowerDao.insertPower(hosPower);
		return "添加成功";
	}

}
