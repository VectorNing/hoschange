package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.AuthPermissionBpo;
import com.sesxh.hoschange.biz.sys.dao.AuthPermissionDao;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.AuthPermissionBpoImpl")
public class AuthPermissionBpoImpl extends SesframeBaseBpo implements AuthPermissionBpo {

	@Autowired
	private AuthPermissionDao authPermissionDao;

	public String loadPermCodeListByUserId(Integer userId) throws BaseException {
		String type = "0";
		List<Map<String, Object>> lists = authPermissionDao.selectPermCodeListByUserId(userId);
		for (Map<String, Object> a : lists) {
			if (a.get("code").equals(BizConst.PERMISSION_CODE.BACKADMIN)
					|| a.get("code").equals(BizConst.PERMISSION_CODE.CLOTHINGADMIN)) {
				type = "1";
				break;
			}
		}
		return type;
	}

	public DataSet loadAuthPermSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String name = pm.getStrParam("name");
		if (name != null) {
			pm.updateParam("name", "%" + name + "%");
		}
		String permCode = pm.getStrParam("permCode");
		if (permCode != null) {
			pm.updateParam("permCode", "%" + permCode + "%");
		}
		DataSet ds = DataSet.newDS(authPermissionDao.selectPermCount(pm), authPermissionDao.selectPermList(pm));
		return ds;
	}

	public AuthPermission loadAuthPermById(Integer id) throws BaseException {
		if (id == null) {
			return null;
		}
		return authPermissionDao.selectAuthPermById(id);
	}

	private void validatePerm(AuthPermission authPerm) throws BusinessException {
		ZAssert.hasText(authPerm.getPermCode(), "编码不能为空");
		ZAssert.hasText(authPerm.getName(), "名称不能为空");

		ZAssert.lenLessTan(authPerm.getPermCode(), 100, "编码长度不能超过100");
		ZAssert.lenLessTan(authPerm.getName(), 100, "名称长度不能超过50");
	}

	public int createAuthPerm(AuthPermission authPerm) throws BaseException {
		validatePerm(authPerm);
		authPerm.setEnabled(BizConst.ENABLED.ENABLE);
		AuthPermission perm = authPermissionDao.selectAuthPermByCode(authPerm.getPermCode());
		ZAssert.isNull(perm, "该编码已经存在");
		return authPermissionDao.insertAuthPerm(authPerm);
	}

	public int updateAuthPerm(AuthPermission authPerm) throws BaseException {
		validatePerm(authPerm);
		AuthPermission perm = authPermissionDao.selectAuthPermByCode(authPerm.getPermCode());
		if (perm != null && !perm.getId().equals(perm.getId())) {
			ZAlert.Error("该编码已经存在");
		}
		return authPermissionDao.updateAuthPerm(authPerm);
	}

	public DataSet loadAuthPermListByRoleId(Map<String, Object> params) throws BaseException {
		DataSet ds = null;
		if (params == null || "".equals(params)) {
			return DataSet.emptyDS();
		}

		ParamMap pm = ParamMap.filterParam(params);
		String isInRole = pm.getStrParam("isInRole");
		String name = pm.getStrParam("name");
		if (name != null) {
			pm.updateParam("name", "%" + name + "%");
		}
		if ("true".equals(isInRole)) {
			ds = DataSet.newDS(authPermissionDao.selectAuthPermCountByRoleId(pm),
					authPermissionDao.selectAuthPermListByRoleId(pm));
		} else {
			ds = DataSet.newDS(authPermissionDao.selectPermCountNotInPermRole(pm),
					authPermissionDao.selectPermListNotInPermRole(pm));
		}
		return ds;
	}

	public int disableBatchAuthPermStatus(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		for (Integer id : ids) {
			falg = authPermissionDao.setAuthPermStatus(id, BizConst.ENABLED.DISABLE);
		}
		return falg;
	}

	public int updateStateByPermCode(String permCode, String enabled) throws BaseException {
		return authPermissionDao.updateStateByPermCode(permCode, enabled);
	}

	public int enableBatchAuthPermStatus(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		for (Integer id : ids) {
			falg = authPermissionDao.setAuthPermStatus(id, BizConst.ENABLED.ENABLE);
		}
		return falg;
	}
}
