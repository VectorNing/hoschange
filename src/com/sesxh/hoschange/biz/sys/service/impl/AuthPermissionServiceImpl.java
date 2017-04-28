package com.sesxh.hoschange.biz.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.AuthPermissionBpo;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.common.data.DataSet;

@Service(value="com.sesxh.hoschange.biz.sys.service.impl.AuthPermissionServiceImpl")
public class AuthPermissionServiceImpl extends SesframeBaseService implements AuthPermissionService {

	@Autowired
	private AuthPermissionBpo authPermissionBpo;

	
	@Cacheable(value="authTmpResultCache",key="'AuthPermissionServiceImpl_queryPermCodeListByUserId'+#userId")
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public String loadPermCodeListByUserId(Integer userId) throws BaseException {
		return authPermissionBpo.loadPermCodeListByUserId(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet loadAuthPermSet(Map<String, Object> params) throws BaseException {
		return authPermissionBpo.loadAuthPermSet(params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthPermission loadAuthPermById(Integer id) throws BaseException {
		return authPermissionBpo.loadAuthPermById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createAuthPerm(AuthPermission authPerm) throws BaseException {
		return authPermissionBpo.createAuthPerm(authPerm);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateAuthPerm(AuthPermission authPerm) throws BaseException {
		return authPermissionBpo.updateAuthPerm(authPerm);
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet loadAuthPermListByRoleId(Map<String,Object> params) throws BaseException{
		return authPermissionBpo.loadAuthPermListByRoleId(params);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int disableBatchAuthPermStatus(Integer[] ids) throws BaseException{
		return authPermissionBpo.disableBatchAuthPermStatus(ids);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int enableBatchAuthPermStatus(Integer[] ids) throws BaseException{
		return authPermissionBpo.enableBatchAuthPermStatus(ids);
	}
}
