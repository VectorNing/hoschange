package com.sesxh.hoschange.biz.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.AuthMenuBpo;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;
import com.sesxh.hoschange.biz.sys.service.AuthMenuService;

@Service
public class AuthMenuServiceImpl extends SesframeBaseService implements AuthMenuService {

	@Autowired
	private AuthMenuBpo authMenuBpo;

	@Override
	@Transactional
	public List<AuthMenu> loadAllAuthMenuList() throws BaseException {
		return authMenuBpo.loadAllAuthMenuList();
	}

	@Override
	@Transactional
	public List<AuthMenu> selectAuthMenuListByUserId(Integer userId) throws BaseException {
		return authMenuBpo.selectAuthMenuListByUserId(userId);
	}

	
	

}
