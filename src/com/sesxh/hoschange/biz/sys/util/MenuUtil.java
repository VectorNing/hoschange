package com.sesxh.hoschange.biz.sys.util;

import java.util.List;
import com.google.common.collect.Lists;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.biz.sys.view.MenuView;


public class MenuUtil {

	public static AuthMenu findRootMenu(List<AuthMenu> allMenus){
		if(allMenus!=null&&allMenus.size()>0){
			for(AuthMenu menu:allMenus){
				if(menu.getParentId()==null){
					return menu ;
				}
			}
		}
		return null;
	}
	
	public static List<AuthMenu>  findChildMeus(AuthMenu target,List<AuthMenu> allMenus){
		List<AuthMenu> childMenus=Lists.newArrayList();
		if(target==null||allMenus==null||allMenus.size()==0){
			return childMenus;
		}
		for(AuthMenu menu:allMenus){
			if(menu.getParentId()==target.getId()){
				childMenus.add(menu);
			}
		}
		return childMenus;
	}
	
	public static AuthPermission findPermission(AuthMenu target,List<AuthPermission> permList){
		if(target==null||permList==null||permList.size()==0){
			return null;
		}
		for(AuthPermission perm:permList){
			if(target.getPermId()==perm.getId()){
				return perm;
			}
		}
		return null;
	}
	

	private static boolean issss=false;
	private static void resetIssss(){
		issss=false;
	}
	private static void isCanBeAddToTree(AuthMenu authMenu,List<AuthPermission> permList,List<AuthMenu> allMenus){
		if(findPermission(authMenu, permList)!=null){
			issss=true;
		}
		List<AuthMenu> childMenus= findChildMeus( authMenu, allMenus);
		if(childMenus!=null&&!childMenus.isEmpty()){
			for (AuthMenu childAuthMenu : childMenus){
				isCanBeAddToTree( childAuthMenu,permList, allMenus);
			}
		}
	}
	public static void traverseMenuTree(AuthMenu rootMenu,MenuView rootViewNode,List<AuthPermission> permList,List<AuthMenu> allMenus) {
		rootViewNode.setId(rootMenu.getId());
		rootViewNode.setTitle(rootMenu.getName());
		AuthPermission permssion=findPermission(rootMenu,permList);
		if(permssion!=null){
			//rootViewNode.setUrl(permssion.getUrl());
		}
		List<AuthMenu> childMenus=findChildMeus(rootMenu, allMenus);
		if (childMenus == null || childMenus.isEmpty()) {
			return;
		}
		for (AuthMenu childAuthMenu : childMenus) {
			resetIssss();
			isCanBeAddToTree( rootMenu, permList, allMenus);
			if(issss){
				MenuView menuNode=new MenuView();
				rootViewNode.addChild(menuNode);
				menuNode.setParentId(rootViewNode.getId());
				traverseMenuTree(childAuthMenu,menuNode,permList,allMenus);
			}
			
		}
	}
	
	
	
}
