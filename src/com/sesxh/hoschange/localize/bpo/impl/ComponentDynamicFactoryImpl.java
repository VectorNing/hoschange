package com.sesxh.hoschange.localize.bpo.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.localize.bpo.ComponentDynamicFactory;
import com.sesxh.hoschange.localize.dao.CompDynaCfgDAO;
import com.sesxh.hoschange.localize.entity.CompDynaCfg;

/**
 * 动态组件工厂类
 */
@Component
@Scope("singleton")
public class ComponentDynamicFactoryImpl implements ComponentDynamicFactory {

	@Autowired
	private CompDynaCfgDAO compDynaCfgDAO;

	private final HashMap<String, HashMap<String, String>> compDynaCfgMap = new HashMap<String, HashMap<String, String>>();

	/**
	 * 获取并初始化本地化动态组件信息
	 */
	@PostConstruct
	public void init() throws BaseException {
		String userObjId;
		HashMap<String, String> compMap;
		List<CompDynaCfg> cfgList;

		cfgList = compDynaCfgDAO.getAllCompDynaCfg();
		for (CompDynaCfg cfg : cfgList) {
			userObjId = cfg.getUserobjid();

			if (compDynaCfgMap.containsKey(userObjId)) {
				compMap = compDynaCfgMap.get(userObjId);
			} else {
				compMap = new HashMap<String, String>();
			}

			compMap.put(cfg.getCompid(), cfg.getLocalid());
			compDynaCfgMap.put(userObjId, compMap);
		}

	}

	/**
	 * 根据请求的组件标识和用户的机构id，查询该组件是否存在本地化文件，<br>
	 * 如果存在，返回本地化组件标识，反之，返回默认的组件标识
	 * 
	 * @param compId
	 *            请求的组件标识，String类型
	 * @param userObjId
	 *            使用方ID
	 * @return String类型
	 * @throws FrameException
	 */
	@Override
	public String getLocalComponentIdDefault(String compId, String userObjId)
			throws FrameException {
		if ((compId == null) || "".equals(compId)) {
			throw new FrameException("传入的组件标识为空！");
		}
		if (!compDynaCfgMap.containsKey(userObjId)) {
			return compId;
		}
		if (!compDynaCfgMap.get(userObjId).containsKey(userObjId)) {
			return compId;
		}
		return compDynaCfgMap.get(userObjId).get(userObjId);
	}

}
