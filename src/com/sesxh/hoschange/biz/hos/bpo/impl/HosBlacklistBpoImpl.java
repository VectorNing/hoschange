package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosBlacklistBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBlacklistDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosBlacklistBpoImpl")
public class HosBlacklistBpoImpl extends SesframeBaseBpo implements HosBlacklistBpo {

	@Autowired
	private HosBlacklistDao hosBlacklistDao;

	@Autowired
	private HosRecordDao hosRecordDao;

	@Override
	public int insertHosBlacklist(HosBlacklist hosBlacklist) throws BaseException {
		return hosBlacklistDao.insertHosBlacklist(hosBlacklist);
	}

	@Override
	public HosBlacklist selectHosBlacklistByUserIdAndType(Integer userId, String type) throws BaseException {
		return hosBlacklistDao.selectHosBlacklistByUserIdAndType(userId, type);
	}

	@Override
	public int updateHosBlacklist(HosBlacklist hosBlacklist) throws BaseException {
		Integer id = hosBlacklist.getId();
		Integer userId = hosBlacklist.getUserId();
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		List<HosRecord> hosRecords = hosRecordDao.selectHosRecordByUserIdAndIsCallBack(userId, "0");

		for (HosRecord hosRecord : hosRecords) {
			hosRecordDao.updateHosRecordIsCallbackById(hosRecord.getId(), "1");
		}
		hosBlacklist.setOutTime(new Date());
		hosBlacklist.setEnabled(BizConst.ENABLED.DISABLE);// 更新成无效的
		return hosBlacklistDao.updateHosBlacklist(hosBlacklist);
	}

	@Override
	public DataSet selcetHosBlacklistSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String userName = pm.getStrParam("userName");
		if (userName != null) {
			pm.updateParam("userName", "%" + userName + "%");
		}
		DataSet ds = DataSet.newDS(hosBlacklistDao.selectHosBlacklistCount(pm),
				hosBlacklistDao.selectHosBlacklistSet(pm));
		return ds;
	}

	@Override
	public int displayScreenHosBacklist(String[] ids, String display) throws BaseException {
		HosBlacklist hosBlacklist = new HosBlacklist();
		for (String id : ids) {
			Integer idd = Integer.parseInt(id);
			hosBlacklist.setId(idd);
			hosBlacklist.setDisplay(display);
			ZAssert.bigThanZero(idd, "请选择数据", "参数类型错误");
			hosBlacklistDao.displayScreenHosBacklist(hosBlacklist);
		}

		return 0;
	}
}
