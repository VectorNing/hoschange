package com.sesxh.hoschange.biz.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.biz.sys.service.AuthRoleService;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;

@Controller
@RequestMapping("/authRole")
public class AuthRoleController extends ZBaseController {

	@Autowired
	private AuthRoleService authRoleService;
	
	private String prefix="auth";
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String enterAuthRole() throws BaseException{
		return prefix+"/pageRole";
	}
	
	@RequestMapping(value="/queryAuthRoleSet",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryAuthRoleSet(@RequestParam Map<String,Object> params ) throws BaseException {
		 DataSet lists =  authRoleService.queryAuthRoleSet(params);
		 return lists;
	}
	
	@RequestMapping(value="/enterAddRole",method=RequestMethod.GET)
	public String enterAddRole() throws BaseException{
		return prefix+"/respRoleAdd";
	}
	
	@RequestMapping(value="/addAuthRole",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addAuthRole(AuthRole sysRole) throws BaseException {
		authRoleService.createAuthRole(sysRole);
		return ajaxDoneSuccess("数据添加成功！");
	}
	
	@RequestMapping(value="enterEditRole",method=RequestMethod.GET)
	public String enterEditRole(Model modal,Integer id) throws BaseException{
		AuthRole authRole = authRoleService.loadAuthRoleById(id);
		modal.addAttribute("authRole", authRole);
		return prefix+"/respRoleEdit";
	}
	
	@RequestMapping(value="/updateAuthRole",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateAuthRole(AuthRole sysRole) throws BaseException {
		authRoleService.updateAuthRole(sysRole);
		return ajaxDoneSuccess("数据修改成功！");
	}
	@RequestMapping(value="permAssign",method=RequestMethod.GET)
	public String enterAssignPermToRole(Model model,String id) throws BaseException{
		model.addAttribute("roleId", id);
		return prefix+"/respRolePermAssign";
	}
	
	@RequestMapping(value="/assignPermToRole",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg assignPermToRole(Integer id, Integer[] permIds) throws BaseException {
		authRoleService.assignRoleToPerm(id, Arrays.asList(permIds));
		return ajaxDoneSuccess("添加权限成功！");
	}
	
	@RequestMapping(value="/removePermFromRole",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg removePermFromRole(Integer id ,Integer[] permIds) throws BaseException {
		authRoleService.removePermFromRole(id, Arrays.asList(permIds));
		return ajaxDoneSuccess("移除权限成功！");
	}
//--------------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/deleteRoleById",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteRoleById(Integer id) throws BaseException {
		authRoleService.deleteAuthRole(id);
		return ajaxDoneSuccess("数据删除成功！");
	}
	
	@RequestMapping(value="/loadAuthRoleListByUserId",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadAuthRoleListByUserId(@RequestParam Map<String,Object> params ) throws BaseException {
		 DataSet lists =  authRoleService.loadAuthRoleListByUserId(params);
		 return lists;
	}
	
	//----------------------------------11-25-------------------------
	
	/**
	 * 批量注销角色信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/disableBatchAuthRole",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object disableBatchAuthRole(Integer[] ids) throws BaseException{
		authRoleService.disableBatchAuthRole(ids);
		return ajaxDoneSuccess("角色注销成功！");
	}
	
	/**
	 * 批量启用角色信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/enableBatchAuthRole",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object enableBatchAuthRole(Integer[] ids) throws BaseException{
		authRoleService.enableBatchAuthRole(ids);
		return ajaxDoneSuccess("角色启用成功！");
	}
	
	@RequestMapping(value = "/selectAuthRoleList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectAuthRoleList() throws BaseException {
		List<ClassConvertDict> list = authRoleService.selectAuthRoleList();
		return list;
	}
}
