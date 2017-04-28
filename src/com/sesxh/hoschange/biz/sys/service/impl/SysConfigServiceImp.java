package com.sesxh.hoschange.biz.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.AuthPermissionBpo;
import com.sesxh.hoschange.biz.sys.bpo.SysConfigBpo;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.biz.sys.service.SysConfigService;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Service
public class SysConfigServiceImp extends SesframeBaseService implements SysConfigService {
	@Autowired
	private SysConfigBpo sysConfigBpo;
	@Autowired
	private AuthPermissionBpo authPermissionBpo;

	@Override
	@Transactional
	public Map<String, Object> querySysConfig(Map<String, Object> params) throws BaseException {
		return sysConfigBpo.enterSysConfig(params);
	}

	@Override
	@Transactional
	public SysConfig querySysConfigById(Integer id) throws BaseException {
		return sysConfigBpo.querySysConfigById(id);
	}

	@Override
	@Transactional
	public SysConfig querySysConfigByConfigName(String configName) throws BaseException {
		return sysConfigBpo.querySysConfigByConfigName(configName);
	}

	@Override
	@Transactional
	public Integer updateSysConfig(SysConfig config) throws BaseException {
		ZAssert.hasText(config.getConfigName(), "权限码为空！");
		if (config.getConfigName().equals(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX)) {// 修改鞋柜配置同时修改权限菜单
			authPermissionBpo.updateStateByPermCode(BizConst.PERMISSION_CODE.SHOES, config.getConfig());
			authPermissionBpo.updateStateByPermCode(BizConst.PERMISSION_CODE.STERILIZER, config.getConfig());
		} else if (config.getConfigName().equals(BizConst.SYSCONFIG.ISORNOROSTER)) {// 修改黑名单配置同时修改权限菜单
			authPermissionBpo.updateStateByPermCode(BizConst.PERMISSION_CODE.GREYLIST, config.getConfig());
			authPermissionBpo.updateStateByPermCode(BizConst.PERMISSION_CODE.BLACKLIST, config.getConfig());
		} else {
		}
		return sysConfigBpo.updateSysConfig(config);
	}

}
