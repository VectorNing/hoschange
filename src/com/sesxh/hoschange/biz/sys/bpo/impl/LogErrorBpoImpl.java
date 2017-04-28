package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.ILogErrorBpo;
import com.sesxh.hoschange.biz.sys.dao.ILogErrorDao;
import com.sesxh.hoschange.biz.sys.entity.LogError;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAssert;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.LogErrorBpoImpl")
public class LogErrorBpoImpl extends SesframeBaseBpo implements ILogErrorBpo {

	@Autowired
	private ILogErrorDao iLogErrorDao;

	public LogError selectLogError(Integer id) throws BaseException {
		if (id == null) {
			return null;
		}
		return iLogErrorDao.selectLogError(id);
	}

	public void createLogError(LogError logError) throws BaseException {
		iLogErrorDao.insertLogError(logError);
	}

	public void deletLogError(Long id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数错误");
		iLogErrorDao.deletLogError(id);
	}

	public DataSet loadLogErrSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		DataSet ds = DataSet.newDS(iLogErrorDao.selectLogErrCount(pm), iLogErrorDao.selectLogErrLists(pm));
		return ds;
	}
}
