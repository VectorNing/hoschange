package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.HisDataFromOracleBpo;
import com.sesxh.hoschange.biz.sys.dao.HisDataFromOracleDao;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.HisDataFromOracleBpoImpl")
public class HisDataFromOracleBpoImpl extends SesframeBaseBpo implements HisDataFromOracleBpo {
	@Autowired
	HisDataFromOracleDao hisDataFromOracleDao;

	@Override
	public List<Map<String, Object>> getAllPersonData() throws BaseException {

		return hisDataFromOracleDao.getAllPersonData();
	}

	@Override
	public List<Map<String, Object>> getOperationSchedule() throws BaseException {

		return hisDataFromOracleDao.getOperationSchedule();
	}

	@Override
	public List<String> getOperationSchedulePerson(String operationNumber) throws BaseException {

		List<Map<String, Object>> listMap = hisDataFromOracleDao.getOperationSchedulePerson(operationNumber);
		List<String> listId = new ArrayList<>();
		for (int i = 0; i < listMap.size(); i++) {
			String id = (String) listMap.get(i).get("EMPLOYESS");
			listId.add(id);
		}
		return listId;
	}

}
