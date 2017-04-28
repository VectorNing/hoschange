package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.ParamMap;

public interface AuthRoleDao {
	/**
	 * 根据id查询角色信息
	 * @param roleId
	 * @return
	 * @throws BaseException
	 * 
	 */
	public AuthRole selectAuthRoleById(Integer roleId) throws BaseException;
	


	/**
	 * 加载角色信息列表分页
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAuthRoleList(ParamMap paramMap) throws BaseException;
	
	/**
	 * 统计角色信息
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectAuthRoleCount(ParamMap paramMap) throws BaseException;
	
	/**
	 * 添加角色信息
	 * @param sysRole
	 * @return
	 * @throws BaseException
	 */
	public int insertAuthRole(AuthRole sysRole) throws BaseException;

	/**
	 * 修改角色信息
	 * @param sysRole
	 * @return
	 * @throws BaseException
	 */
	public int updateAuthRole(AuthRole sysRole) throws BaseException;

	/**
	 * 修改角色状态
	 * @param roleId
	 * @return
	 * @throws BaseException
	 */
	public int setAuthRoleStatus(Integer roleId,String enabled) throws BaseException ;
	
	
	public int deleteAuthRole(Integer roleId) throws BaseException;
	
	/**
	 * 分配角色权限
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int assignPermToRole(Integer id, Integer permId) throws BaseException;
	
	/**
	 * 移除角色权限
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int removePermFromRole(Integer id, Integer permId) throws BaseException;
	
	/**
	 * 加载属于用户角色数据列表
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAuthRoleListByUserId(ParamMap paramMap) throws BaseException;
	
	/**
	 * 统计属于用户角色数据列表
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectAuthRoleCountByUserId(ParamMap paramMap) throws BaseException;
	
	/**
	 *  加载不属于用户角色数据列表
	 *  
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectNotAuthRoleListByUserId(ParamMap paramMap) throws BaseException;
	
	/**
	 * 统计不属于用户角色数据列表
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectNotAuthRoleCountByUserId(ParamMap paramMap) throws BaseException;
	
	
	/**
	 * 加载角色信息
	 *    用于添加用户 的角色下拉框(数据字典)
	 * @return
	 * @throws BaseException
	 */
	public List<ClassConvertDict> selectAuthRoleList() throws BaseException;
}
