package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.AuthRoleBpo;
import com.sesxh.hoschange.biz.sys.dao.AuthRoleDao;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.AuthRoleBpoImpl")
public class AuthRoleBpoImpl extends SesframeBaseBpo implements AuthRoleBpo {

	@Autowired
	private AuthRoleDao authRoleDao;

	@Override
	public AuthRole loadAuthRoleById(Integer roleId) throws BaseException {
		ZAssert.bigThanZero(roleId, "请选择数据", "参数类型错误");
		return authRoleDao.selectAuthRoleById(roleId);
	}

	@Override
	public DataSet queryAuthRoleSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String name = pm.getStrParam("name");
		if (name != null) {
			pm.updateParam("name", "%" + name + "%");
		}
		DataSet ds = DataSet.newDS(authRoleDao.selectAuthRoleCount(pm), authRoleDao.selectAuthRoleList(pm));
		return ds;
	}

	private void validateRole(AuthRole sysRole) throws BusinessException {
		ZAssert.hasText(sysRole.getName(), "名称不能为空");
		ZAssert.lenLessTan(sysRole.getName(), 50, "名称长度不能超过50");
	}

	@Override
	public int createAuthRole(AuthRole sysRole) throws BaseException {

		validateRole(sysRole);
		sysRole.setEnabled(BizConst.ENABLED.ENABLE);
		return authRoleDao.insertAuthRole(sysRole);
	}

	@Override
	public int updateAuthRole(AuthRole sysRole) throws BaseException {
		validateRole(sysRole);
		ZAssert.hasText(sysRole.getEnabled(), "状态不能为空");

		return authRoleDao.updateAuthRole(sysRole);
	}

	@Override
	public int assignRoleToPerm(Integer id, List<Integer> PermIds) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		int flag = 1;
		if (PermIds == null || PermIds.isEmpty()) {
			ZAlert.Error("请选择数据");
		}
		for (Integer PermId : PermIds) {
			flag = authRoleDao.removePermFromRole(id, PermId);
		}

		for (Integer PermId : PermIds) {
			flag = authRoleDao.assignPermToRole(id, PermId);
		}
		return flag;
	}

	public int removeRoleToPerm(Integer id, List<Integer> PermIds) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		int flag = 1;
		if (PermIds == null || PermIds.isEmpty()) {
			ZAlert.Error("请选择数据");
		}
		for (Integer PermId : PermIds) {
			flag = authRoleDao.removePermFromRole(id, PermId);
		}
		return flag;
	}

	public DataSet loadAuthRoleListByUserId(Map<String, Object> params) throws BaseException {
		DataSet ds = null;
		if (params == null) {
			return DataSet.emptyDS();
		}
		ParamMap pm = ParamMap.filterParam(params);
		String isInRole = pm.getStrParam("isInRole");
		if ("true".equals(isInRole)) {
			ds = DataSet.newDS(authRoleDao.selectAuthRoleCountByUserId(pm), authRoleDao.selectAuthRoleListByUserId(pm));
		} else {
			ds = DataSet.newDS(authRoleDao.selectNotAuthRoleCountByUserId(pm),
					authRoleDao.selectNotAuthRoleListByUserId(pm));
		}
		return ds;
	}

	@Override
	public int deleteAuthRole(Integer roleId) throws BaseException {
		ZAssert.bigThanZero(roleId, "请选择数据", "数据格式错误");
		return authRoleDao.deleteAuthRole(roleId);
	}

	// -------------------------------------------------------------------------

	public int disableBatchAuthRole(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		for (Integer id : ids) {
			// falg = authRoleDao.setAuthRoleStatus(id,
			// BizConst.ENABLED.DISABLE);
			falg = authRoleDao.deleteAuthRole(id);
		}
		return falg;
	}

	public int enableBatchAuthRole(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		for (Integer id : ids) {
			falg = authRoleDao.setAuthRoleStatus(id, BizConst.ENABLED.ENABLE);
		}
		return falg;
	}

	public List<ClassConvertDict> selectAuthRoleList() throws BaseException {
		return authRoleDao.selectAuthRoleList();
	}
}
