package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.AuthMenuBpo;
import com.sesxh.hoschange.biz.sys.dao.AuthMenuDao;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.AuthMenuBpoImpl")
public class AuthMenuBpoImpl extends SesframeBaseBpo implements AuthMenuBpo {

	@Autowired
	private AuthMenuDao authMenuDao;
	@Override
	public List<AuthMenu> loadAllAuthMenuList() throws BaseException {
		return authMenuDao.selectAllAuthMenuList();
	}

	@Override
	public List<AuthMenu> selectAuthMenuListByUserId(Integer userId) throws BaseException {
		List<AuthMenu> fatherMenuList = new ArrayList<>();
		List<AuthMenu> childMenuList = new ArrayList<>();
		List<AuthMenu> menuList = new ArrayList<>();
		Set<Integer> parentIdSet = new HashSet<>();
		//根据用户id查询出该用户角色拥有的权限，根据权限加载菜单，并为菜单排序
		childMenuList = authMenuDao.selectAuthMenuListByUserId(userId);
		//获取子菜单的父ID
		for (AuthMenu childMenu : childMenuList) {
			parentIdSet.add(childMenu.getParentId());
		}
		// 根据父菜单id加载父菜单
		Iterator<Integer> it = parentIdSet.iterator();
		while (it.hasNext()) {
			Integer parentId = it.next();
			AuthMenu fatherMenu = authMenuDao.selectAuthMenuListByParentId(parentId);
			fatherMenuList.add(fatherMenu);
		}
		// 将父菜单和子菜单装载进菜单中
		for (AuthMenu authMenu : fatherMenuList) {
			menuList.add(authMenu);
		}
		for (AuthMenu authMenu : childMenuList) {
			menuList.add(authMenu);
		}

		return menuList;
	}

}
