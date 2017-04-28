package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.common.data.ParamMap;

public interface AuthPermissionDao {

	/**
	 * 根据用户id 查询 功能 code
	 * 
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectPermCodeListByUserId(Integer userId) throws BaseException;

	/**
	 * 统计权限信息
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectPermCount(ParamMap paramMap) throws BaseException;

	/**
	 * 分页加载权限数据列表
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectPermList(ParamMap paramMap) throws BaseException;

	/**
	 * 根据ID查询权限
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthPermission selectAuthPermById(Integer id) throws BaseException;

	/**
	 * 根据编号查询权限
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthPermission selectAuthPermByCode(String code) throws BaseException;
	
	/**
	 * 添加新权限信息
	 * 
	 * @param authPerm
	 * @return
	 * @throws BaseException
	 */
	public int insertAuthPerm(AuthPermission authPerm) throws BaseException;

	/**
	 * 修改权限信息
	 * 
	 * @param authPerm
	 * @return
	 * @throws BaseException
	 */
	public int updateAuthPerm(AuthPermission authPerm) throws BaseException;


	/**
	 * 修改权限状态是否启用
	 * 
	 * @param id
	 * @param enabled
	 * @return
	 * @throws BaseException
	 */
	public int setAuthPermStatus(Integer id, String enabled) throws BaseException;
	
	/**
	 * 加载属于角色的权限数据列表
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAuthPermListByRoleId(ParamMap paramMap) throws BaseException;
	/**
	 * 通过permcode启用/禁用权限
	 * @author xujialong
	 * @return
	 * @throws BaseException
	 */
	public int updateStateByPermCode (String permCode ,String enabled) throws BaseException;
	/**
	 * 统计属于角色的权限数据列表
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectAuthPermCountByRoleId(ParamMap paramMap) throws BaseException;
	
	/**
	 *  加载不属于角色的权限数据列表
	 *  
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectPermListNotInPermRole(ParamMap paramMap) throws BaseException;
	
	/**
	 * 统计不属于角色的权限数据列表
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectPermCountNotInPermRole(ParamMap paramMap) throws BaseException;
	
	public List<Map<String,Object>> selectPermListByUser(ParamMap paramMap) throws BaseException;
	/**
	 * 根据userid查询权限
	* @Title: selectPermListByUserId
	* @Author:Ning 
	* @Description: 
	* @data:2017年1月6日
	 */
	public List<AuthPermission> selectPermListByUserId(Integer userId) throws BaseException;
}
