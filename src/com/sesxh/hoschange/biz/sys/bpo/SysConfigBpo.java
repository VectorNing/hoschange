package com.sesxh.hoschange.biz.sys.bpo;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;

public interface SysConfigBpo {
	/**
	 * 进入配置页面
	* @Title: querySysConfigAll
	* @author Ning 
	* @Description: 
	* @data:2017年1月12日
	 */
	public Map<String, Object> enterSysConfig(Map<String, Object> params) throws BaseException;
	/**
	 * 根据id查询配置信息
	* @Title: querySysConfigById
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	public SysConfig querySysConfigById(Integer id) throws BaseException;
	/**
	 * 根据configName查询配置信息
	* @Title: querySysConfigByConfigName
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	public SysConfig querySysConfigByConfigName(String configName) throws BaseException;
	/**
	 * 修改配置信息
	* @Title: updateSysConfig
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	public Integer updateSysConfig(SysConfig config) throws BaseException;
}
