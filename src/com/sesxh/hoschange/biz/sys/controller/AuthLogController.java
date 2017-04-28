package com.sesxh.hoschange.biz.sys.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.common.data.DataSet;

@Controller
@RequestMapping("/authLog")
public class AuthLogController extends ZBaseController{

	@Autowired
	private AuthPermissionService authPermissionService;

	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String authpermission() throws BaseException {
		return "";
	}
	
	@RequestMapping(value="/loadLogErrorSet",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataSet loadLogErrorSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet ds = authPermissionService.loadAuthPermSet(params);
		return ds;
	}
	
	@RequestMapping(value="/loadLogOperationSet",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataSet loadLogOperationSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet ds = authPermissionService.loadAuthPermSet(params);
		return ds;
	}
}
