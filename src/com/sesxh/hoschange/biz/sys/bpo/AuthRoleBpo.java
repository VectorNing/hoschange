package com.sesxh.hoschange.biz.sys.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;

public interface AuthRoleBpo {

	/**
	 * 根据id查询角色信息
	 * @param roleId
	 * @return
	 * @throws BaseException
	 */
	public AuthRole loadAuthRoleById(Integer roleId) throws BaseException;


	/**
	 * 加载角色列表
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryAuthRoleSet(Map<String, Object> params) throws BaseException;

	/**
	 * 添加角色信息
	 * @param sysRole
	 * @return
	 * @throws BaseException
	 */
	public int createAuthRole(AuthRole sysRole) throws BaseException;
	
	/**
	 * 修改角色信息
	 * @param sysRole
	 * @return
	 * @throws BaseException
	 */
	public int updateAuthRole(AuthRole sysRole) throws BaseException;
	
	public int deleteAuthRole(Integer roleId) throws BaseException;

	/**
	 * 分配角色权限
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int assignRoleToPerm(Integer id, List<Integer> PermIds) throws BaseException;
	
	/**
	 * 移除角色权限
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int removeRoleToPerm(Integer id, List<Integer> PermIds) throws BaseException;
	
	/**
	 * 加载属于用户角色数据列表
	 * @param id
	 * @param type
	 * @return
	 */
	public DataSet loadAuthRoleListByUserId(Map<String,Object> params) throws BaseException;
	
	/**
	 * 批量注销角色11-25
	 * 
	 * @param roleId
	 * @return
	 * @throws BaseException
	 */
	public int disableBatchAuthRole(Integer[] ids) throws BaseException;
	
	/**
	 * 批量启用角色11-25
	 * 
	 * @param roleId
	 * @return
	 * @throws BaseException
	 */
	public int enableBatchAuthRole(Integer[] ids) throws BaseException;
	
	/**
	 * 加载角色信息
	 *    用于添加用户 的角色下拉框
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<ClassConvertDict> selectAuthRoleList() throws BaseException;
}
