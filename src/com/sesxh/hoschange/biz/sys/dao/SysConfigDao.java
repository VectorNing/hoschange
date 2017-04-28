package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.common.util.PageModel;

public interface SysConfigDao {

	/**
	 * 查询配置信息
	 * 
	 * @Title: querySysConfigAll
	 * @author Ning
	 * @Description:
	 * @data:2017年1月12日
	 */
	public List<Map<String, Object>> querySysConfig(PageModel page) throws BaseException;

	/**
	 * 查询配置信息条数
	 * 
	 * @Title: querySysConfigCount
	 * @author Ning
	 * @Description:
	 * @data:2017年1月12日
	 */
	public int querySysConfigCount() throws BaseException;

	/**
	 * 根据id查询配置信息
	 * 
	 * @Title: querySysConfigById
	 * @author Ning
	 * @Description:
	 * @data:2017年1月13日
	 */
	public SysConfig querySysConfigById(Integer id) throws BaseException;

	/**
	 * 根据配置名字查询配置
	 * 
	 * @Title: querySysConfigByConfigName
	 * @author Ning
	 * @Description:
	 * @data:2017年1月13日
	 */
	public SysConfig querySysConfigByConfigName(String configName) throws BaseException;

	/**
	 * 修改配置信息
	 * 
	 * @Title: updateSysConfig
	 * @author Ning
	 * @Description:
	 * @data:2017年1月13日
	 */
	public Integer updateSysConfig(SysConfig config) throws BaseException;

}
