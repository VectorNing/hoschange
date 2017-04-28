package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.common.util.BeanUtils;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.SysConfigBpo;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.common.util.PageModel;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.SysConfigBpoImp")
public class SysConfigBpoImp extends SesframeBaseBpo implements SysConfigBpo {
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public Map<String, Object> enterSysConfig(Map<String, Object> params) throws BaseException {
		Map<String, Object> map = new HashMap<>();
		PageModel page = BeanUtils.getBeanFromMap(params, PageModel.class);
		List<Map<String, Object>> maps = sysConfigDao.querySysConfig(page);
		Integer total = sysConfigDao.querySysConfigCount();
		map.put("rows", maps);
		map.put("total", total);
		return map;
	}

	@Override
	public SysConfig querySysConfigById(Integer id) throws BaseException {
		return sysConfigDao.querySysConfigById(id);
	}

	@Override
	public SysConfig querySysConfigByConfigName(String configName) throws BaseException {
		return sysConfigDao.querySysConfigByConfigName(configName);
	}

	@Override
	public Integer updateSysConfig(SysConfig config) throws BaseException {
		return sysConfigDao.updateSysConfig(config);
	}

}
