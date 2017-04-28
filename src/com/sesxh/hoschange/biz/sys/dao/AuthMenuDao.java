package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;

public interface AuthMenuDao {

	/**
	 * 加载所有的菜单
	* @Title: selectAllAuthMenuList
	* @Author:Ning 
	* @Description: 
	* @data:2017年1月5日
	 */
	public List<AuthMenu> selectAllAuthMenuList() throws BaseException;
	/**
	 * 通过userId查询菜单
	* @Title: selectAuthMenuListByPermId
	* @Author:Ning 
	* @Description: 
	* @data:2017年1月6日
	 */
	public List<AuthMenu> selectAuthMenuListByUserId(Integer userId) throws BaseException;
	/**
	 * 通过二级菜单加载一级菜单
	* @Title: selectAuthMenuListByFatherId
	* @Author:Ning 
	* @Description: 
	* @data:2017年1月6日
	 */
	public AuthMenu selectAuthMenuListByParentId(Integer ParentId) throws BaseException;
	
}
