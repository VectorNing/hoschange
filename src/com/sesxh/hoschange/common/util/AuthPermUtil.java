package com.sesxh.hoschange.common.util;
/*package com.sdsesxh.base.auth.util;

import java.util.List;
import java.util.Set;
import com.google.common.collect.Sets;
import com.sdses.base.BaseException;
import com.sdsesxh.base.util.AppCtxUtil;
import com.sdsesxh.biz.sys.service.impl.AuthPermissionServiceImpl;
import com.sdsesxh.biz.sys.service.impl.AuthRoleServiceImpl;
*//**
 * 
 * @author zhaohutai
 *
 */
/*public class AuthPermUtil {
	
	public static boolean isHavePerm(String permCode,Integer userId) throws BaseException{
		if(permCode!=null){
			Set<String> permSet=loadPermCodesByUserId( userId);
			return permSet.contains(permCode);
		}
		return true;
	}
	
	public static boolean isHavePermSet(Set<String> permCodes,Integer userId) throws BaseException{
		if(permCodes==null||permCodes.size()==0){
			return true;
		}
		
		Set<String> permSet=loadPermCodesByUserId( userId);
		return permSet.containsAll(permSet);
	}
	
	public static boolean isHaveRole(String roleCode,Integer userId) throws BaseException{
		if(roleCode==null||roleCode.isEmpty()){
			return true;
		}
		Set<String> roleSet=loadRoleCodesByUserId(userId);
		return roleSet.containsAll(roleSet);
	}
	
	
	public static boolean isHaveRoleSet(Set<String> roleCodes,Integer userId) throws BaseException{
		if(roleCodes==null||roleCodes.size()==0){
			return true;
		}
		Set<String> roleSet=loadRoleCodesByUserId(userId);
		return roleSet.containsAll(roleSet);
	}
	
	
	public static Set<String> loadPermCodesByUserId(Integer userId) throws BaseException{
		AuthPermissionServiceImpl authPermissionService=AppCtxUtil.getBean(AuthPermissionServiceImpl.class);
		List<String> permCodeList=authPermissionService.loadPermCodeListByUserId(userId);
		Set<String> setList =  Sets.newHashSet(permCodeList);
		return setList;
	}
	
	public static Set<String> loadRoleCodesByUserId(Integer userId) throws BaseException{
		AuthRoleServiceImpl authRoleServiceImpl=AppCtxUtil.getBean(AuthRoleServiceImpl.class);
		List<String> permCodeList=authRoleServiceImpl.loadRoleCodeListByUserId(userId);
		Set<String> setList =  Sets.newHashSet(permCodeList);
		return setList;
	}

}
*/