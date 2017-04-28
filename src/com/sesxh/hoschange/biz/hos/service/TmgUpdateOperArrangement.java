package com.sesxh.hoschange.biz.hos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosSchedulingBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSchedulingPersonBpo;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.sys.bpo.AuthUserBpo;
import com.sesxh.hoschange.biz.sys.bpo.HisDataFromOracleBpo;
import com.sesxh.hoschange.biz.tmg.core.SesTaskSlice;
import com.sesxh.hoschange.global.BizConst.ENABLED;

@Service("com.sesxh.hoschange.biz.hos.service.TmgUpdateOperArrangement")
public class TmgUpdateOperArrangement extends SesframeBaseService implements SesTaskSlice {
	@Autowired
	HosSchedulingBpo hosSchedulingBpo;
	@Autowired
	HosPowerService hosPowerService;
	@Autowired
	AuthUserBpo authUserBpo;
	@Autowired
	HosSchedulingPersonBpo hosSchedulingPersonBpo;
	@Autowired
	HisDataFromOracleBpo hisDataFromOracleBpo;

	@Override
	public boolean execute(String paras) throws BaseException {
		// json格式
		// String jsonMessage =
		// "[{'thNumber':'1','operationTime':'20170429152030','operationName':'SSS4'"
		// +
		// ",'patientId':'111','employees':['a','b','c','f','k'],'operationNumber':'99999999'}]";
		// JSONArray jsonArray = JSONArray.parseArray(jsonMessage);
		List<Map<String, Object>> listMap = hisDataFromOracleBpo.getOperationSchedule();// 手术排班信息(视图形式)

		List<String> operschedulidListAdd = new ArrayList<String>();
		List<String> operschedulidListUpdate = new ArrayList<String>();
		List<String> operschedulidListSub = new ArrayList<String>();
		List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listMap.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			HosScheduling hosScheduling = new HosScheduling();
			String thNumber = (String) listMap.get(i).get("THNUMBER");
			String operationName = (String) listMap.get(i).get("OPERATIONNAME");
			String operationTime = (String) listMap.get(i).get("OPERATIONTIME");
			String patientId = (String) listMap.get(i).get("PATIENTID");
			String operationNumber = (String) listMap.get(i).get("OPERATIONNUMBER");
			List<String> employees = hisDataFromOracleBpo.getOperationSchedulePerson(operationNumber);
			// String jsonStr = jsonArray.get(i).toString();
			// Map<String, Object> jsonToMap = JSON.parseObject(jsonStr,
			// Map.class);
			// String thNumber = (String) jsonToMap.get("thNumber");// 手术室编号
			// String operationName = (String) jsonToMap.get("operationName");//
			// 手术室名称
			// String operationTime = (String) jsonToMap.get("operationTime");//
			// 手术时间
			// String patientId = (String) jsonToMap.get("patientId");// 患者Id
			// String operationNumber = (String)
			// jsonToMap.get("operationNumber");// 手术申请编号
			// List<String> employees = (List<String>)
			// jsonToMap.get("employees");
			List<String> id = new ArrayList<String>();
			for (int j = 0; j < employees.size(); j++) {
				Integer userid = authUserBpo.selectIdByJobNumber(employees.get(j));
				id.add(userid + "");
			}
			hosScheduling.setEnabled(ENABLED.ENABLE);
			hosScheduling.setName(operationName);
			hosScheduling.setOperationtime(operationTime);
			hosScheduling.setThNumber(thNumber);
			hosScheduling.setPatientId(patientId);
			hosScheduling.setOperationNumber(operationNumber);
			hosScheduling.setEmployees(id);
			map.put("operationNumber", hosScheduling);
			all.add(map);
			operschedulidListAdd.add(operationNumber);
			operschedulidListUpdate.add(operationNumber);
			operschedulidListSub.add(operationNumber);
		}
		List<String> operationNumberListData = hosSchedulingBpo.selectSchedulByOperationTime();
		operschedulidListAdd.removeAll(operationNumberListData);
		// 添加
		for (int i = 0; i < operschedulidListAdd.size(); i++) {
			for (int j = 0; j < all.size(); j++) {
				HosScheduling hos = new HosScheduling();
				hos = (HosScheduling) all.get(j).get("operationNumber");
				if (operschedulidListAdd.get(i).equals(hos.getOperationNumber())) {
					hosSchedulingBpo.insertHosOperation(hos);
					hosSchedulingPersonBpo.insertHosSchedulingPerson(hos);
					hosPowerService.insertPower(hos);
				}
			}
		}
		// 更改
		operschedulidListUpdate.retainAll(operationNumberListData);
		for (int i = 0; i < operschedulidListUpdate.size(); i++) {
			for (int j = 0; j < all.size(); j++) {
				HosScheduling hos = new HosScheduling();
				hos = (HosScheduling) all.get(j).get("operationNumber");
				if (operschedulidListUpdate.get(i).equals(hos.getOperationNumber())) {
					hosSchedulingBpo.updateHosScheduling(hos);
					hosSchedulingPersonBpo.updateHosSchedulingPerson(hos);
					hosPowerService.updatePower(hos);
				}
			}
		}
		// 删除
		operationNumberListData.removeAll(operschedulidListSub);
		for (int i = 0; i < operationNumberListData.size(); i++) {
			String id = hosSchedulingBpo.selectIdByOperationNumber(operationNumberListData.get(i));
			hosSchedulingBpo.deleteHosSchedulingByOperationNumber(operationNumberListData.get(i));
			hosSchedulingPersonBpo.deleteHosSchedulingPersonByOperschedulid(id);
			hosPowerService.deleteInvilidPower("", id);
		}

		return true;
	}

}
