package com.sesxh.hoschange.biz.sys.service;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.common.data.DataSet;

public interface AuthPermissionService {
	
	/**
	 * 根据用户id 查询 功能 code
	 * 
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public String loadPermCodeListByUserId(Integer userId) throws BaseException;

	/**
	 * 加载数据列表
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadAuthPermSet(Map<String, Object> params) throws BaseException;

	/**
	 * 根据编号查询权限
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthPermission loadAuthPermById(Integer id) throws BaseException;
	/**
	 * 添加新权限信息
	 * @param authPerm
	 * @return
	 * @throws BaseException
	 */
	public int createAuthPerm(AuthPermission authPerm) throws BaseException;

	/**
	 * 修改权限信息
	 * @param authPerm
	 * @return
	 * @throws BaseException
	 */
	public int updateAuthPerm(AuthPermission authPerm) throws BaseException;

	
	/**
	 * 加载属于用户角色数据列表
	 * @param id
	 * @param type
	 * @return
	 */
	public DataSet loadAuthPermListByRoleId(Map<String,Object> params) throws BaseException;
	
	//-----------------------------------11-25----------------------------------
	
	
	/**
	 * 批量注销权限 11-25
	 * 
	 * @param roleId
	 * @return
	 * @throws BaseException
	 */
	public int disableBatchAuthPermStatus(Integer[] ids) throws BaseException;
	
	/**
	 * 批量启用权限 11-25
	 * 
	 * @param roleId
	 * @return
	 * @throws BaseException
	 */
	public int enableBatchAuthPermStatus(Integer[] ids) throws BaseException;
	
}
