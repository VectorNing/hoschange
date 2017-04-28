package com.sesxh.hoschange.localize.bpo;


import com.sesxh.base.BaseException;
import com.sesxh.common.exception.AppException;

/**
 * 动态组件工厂接口
 */
//@Component
public interface ComponentDynamicFactory {

	/**
	 * 根据请求的组件标识和用户的机构id，查询该组件是否存在本地化文件，<br>
	 * 如果存在，返回本地化组件标识，反之，返回默认的组件标识
	 * 
	 * @param compId
	 *            请求的组件标识，String类型
	 * @param userObjId
	 *            使用方ID
	 * @return String类型
	 * @throws AppException
	 */
	public String getLocalComponentIdDefault(String compId, String userObjId)
			throws BaseException;

}
