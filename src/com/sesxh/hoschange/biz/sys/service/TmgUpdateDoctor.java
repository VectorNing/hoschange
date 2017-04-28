package com.sesxh.hoschange.biz.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.AuthUserBpo;
import com.sesxh.hoschange.biz.sys.bpo.HisDataFromOracleBpo;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.tmg.core.SesTaskSlice;

@Service("com.sesxh.hoschange.biz.sys.service.TmgUpdateDoctor")
public class TmgUpdateDoctor extends SesframeBaseService implements SesTaskSlice {
	@Autowired
	private AuthUserBpo authUserBpo;
	@Autowired
	private HisDataFromOracleBpo hisDataFromOracleBpo;

	@Override
	public boolean execute(String paras) throws BaseException {
		List<Map<String, Object>> listMap = hisDataFromOracleBpo.getAllPersonData();
		List<String> listAdd = new ArrayList<String>();// 传过来的信息多于表中的信息,添加。
		List<String> listUpdate = new ArrayList<String>();//
		// 传过来的信息和表中的信息相对于，更新。
		List<String> listSub = new ArrayList<String>();//
		// 传过来的信息小于表中信息的，把状态置为不可用。
		List<Map<String, Object>> listjobnumber = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listMap.size(); i++) {
			AuthUserDetail authUserDetail = new AuthUserDetail();
			Map<String, Object> map = new HashMap<String, Object>();
			String jobnumber = (String) listMap.get(i).get("JOBNUMBER");// 工号
			String name = (String) listMap.get(i).get("NAME");// 名字
			String department = (String) listMap.get(i).get("DEPARTMENT");// 部门
			String position = (String) listMap.get(i).get("POSITION");// 职位
			authUserDetail.setJobnumber(jobnumber);
			authUserDetail.setUserName(name);
			authUserDetail.setDepartment(department);
			authUserDetail.setPosition(position);
			map.put("jobnumber", authUserDetail);
			listjobnumber.add(map);
			listAdd.add(jobnumber);
			listUpdate.add(jobnumber);
			listSub.add(jobnumber);
		}

		List<String> listJobNumber = authUserBpo.selectAuthUserJobNumber();
		listAdd.removeAll(listJobNumber);
		// 添加
		for (int i = 0; i < listAdd.size(); i++) {
			for (int j = 0; j < listjobnumber.size(); j++) {
				AuthUserDetail ad = new AuthUserDetail();
				ad = (AuthUserDetail) listjobnumber.get(j).get("jobnumber");
				if (listAdd.get(i).equals(ad.getJobnumber())) {
					AuthUser au = new AuthUser();
					au.setJobnumber(ad.getJobnumber());
					authUserBpo.insertAuthUser(au);
					Integer id = authUserBpo.selectIdByJobNumber(ad.getJobnumber());
					ad.setUserId(id + "");
					authUserBpo.insertAuthUserDetail(ad);
				}
			}
		}
		// 更新表中的人员信息
		listUpdate.retainAll(listJobNumber);

		for (int i = 0; i < listUpdate.size(); i++) {
			for (int j = 0; j < listjobnumber.size(); j++) {
				AuthUserDetail ad = new AuthUserDetail();
				ad = (AuthUserDetail) listjobnumber.get(j).get("jobnumber");
				if (listAdd.get(i).equals(ad.getJobnumber())) {
					Integer id = authUserBpo.selectIdByJobNumber(ad.getJobnumber());
					ad.setUserId(id + "");
					authUserBpo.updateUserDedailAllById(ad);
				}
			}
		}
		// 将表中的多余人员信息置为不可用
		listJobNumber.removeAll(listSub);
		for (int i = 0; i < listJobNumber.size(); i++) {
			authUserBpo.updateAuthUserDisableByJobNumber(listJobNumber.get(i));
		}
		return true;

	}

}
