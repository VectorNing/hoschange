package com.sesxh.hoschange.biz.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.AuthRoleBpo;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.biz.sys.service.AuthRoleService;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class AuthRoleServiceImpl extends SesframeBaseService implements AuthRoleService {

	@Autowired
	private AuthRoleBpo authRoleBpo;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthRole loadAuthRoleById(Integer roleId) throws BaseException {
		return authRoleBpo.loadAuthRoleById(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet queryAuthRoleSet(Map<String, Object> params) throws BaseException {
		return authRoleBpo.queryAuthRoleSet(params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createAuthRole(AuthRole sysRole) throws BaseException {
		return authRoleBpo.createAuthRole(sysRole);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateAuthRole(AuthRole sysRole) throws BaseException {
		return authRoleBpo.updateAuthRole(sysRole);
	}
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public int assignRoleToPerm(Integer id, List<Integer> PermIds) throws BaseException{
		return authRoleBpo.assignRoleToPerm(id, PermIds);
	}
	
	public int removePermFromRole(Integer id, List<Integer> PermIds) throws BaseException{
		return authRoleBpo.removeRoleToPerm(id, PermIds);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet loadAuthRoleListByUserId(Map<String,Object> params) throws BaseException{
		return authRoleBpo.loadAuthRoleListByUserId(params);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteAuthRole(Integer roleId) throws BaseException {
		return authRoleBpo.deleteAuthRole(roleId);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int disableBatchAuthRole(Integer[] ids) throws BaseException{
		return authRoleBpo.disableBatchAuthRole(ids);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int enableBatchAuthRole(Integer[] ids) throws BaseException{
		return authRoleBpo.enableBatchAuthRole(ids);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<ClassConvertDict> selectAuthRoleList() throws BaseException{
		return authRoleBpo.selectAuthRoleList();
	}
}
