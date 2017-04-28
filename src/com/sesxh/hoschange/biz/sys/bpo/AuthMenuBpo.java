package com.sesxh.hoschange.biz.sys.bpo;

import java.util.List;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;

public interface AuthMenuBpo {

	/**
	 * 加载所有的菜单
	* @Title: loadAllAuthMenuList
	* @Author:Ning 
	* @Description: 
	* @data:2017年1月5日
	 */
	public List<AuthMenu> loadAllAuthMenuList() throws BaseException;
	/**
	 * 通过用户id查找角色，根据角色权限加载菜单
	* @Title: selectAuthMenuListByUserId
	* @Description: 
	* @data:2017年1月5日
	* @author Ning
	 */
	public List<AuthMenu> selectAuthMenuListByUserId(Integer userId) throws BaseException;
}
