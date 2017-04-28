package com.sesxh.hoschange.biz.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;

@Controller
@RequestMapping("/authPermission")
public class AuthPermissionController extends ZBaseController {

	@Autowired
	private AuthPermissionService authPermissionService;

	private String prefix="auth";
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String authpermission() throws BaseException {
		return "auth/pagePermission";
	}
	
	@RequestMapping(value="/queryAuthPermSet",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataSet queryAuthPermSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet ds = authPermissionService.loadAuthPermSet(params);
		return ds;
	}
	@RequestMapping(value="/loadAuthPermById",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadAuthPermById(Integer id) throws BaseException {
		AuthPermission authPerm = authPermissionService.loadAuthPermById(id);
		return authPerm;
	}

	@RequestMapping(value="/enterAuthPermAdd",method=RequestMethod.GET)
	public String enterAuthPermAdd(ModelMap ModelMap, HttpServletRequest request,HttpServletResponse response)throws BaseException{
		return prefix+"/respPermissionAdd";
	}
	
	@RequestMapping(value="/addAuthPerm",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addAuthPerm(AuthPermission authPerm) throws BaseException {
		authPermissionService.createAuthPerm(authPerm);
		return ajaxDoneSuccess("数据添加成功！");
	}

	@RequestMapping(value="/enterAuthPermEdit",method=RequestMethod.GET)
	public String enterAuthPermEdit(Model model,Integer id, HttpServletRequest request,HttpServletResponse response)throws BaseException{
		AuthPermission authPerm = authPermissionService.loadAuthPermById(id);
		model.addAttribute("authPerm", authPerm);
		return prefix+"/respPermissionEdit";
	}
	
	@RequestMapping(value="/updateAuthPerm",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateAuthPerm(AuthPermission authPerm) throws BaseException {
		authPermissionService.updateAuthPerm(authPerm);
		return ajaxDoneSuccess("数据更新成功！");
	}
	
	@RequestMapping(value="/loadAuthPermListByRoleId",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadAuthPermListByRoleId(@RequestParam Map<String,Object> params ) throws BaseException {
		 DataSet lists =  authPermissionService.loadAuthPermListByRoleId(params);
		 return lists;
	}
	
	//-----------------------11-25----------------------------
	
	/**
	 * 批量注销权限 11-25
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/disableBatchAuthPerm",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object disableBatchAuthPerm(Integer[] ids) throws BaseException{
		authPermissionService.disableBatchAuthPermStatus(ids);
		return ajaxDoneSuccess("操作成功！");
	}

	/**
	 * 批量启用权限 11-25
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/enableBatchAuthPerm",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object enableBatchAuthPerm(Integer[] ids) throws BaseException{
		authPermissionService.enableBatchAuthPermStatus(ids);
		return ajaxDoneSuccess("操作成功！");
	}
	
}
