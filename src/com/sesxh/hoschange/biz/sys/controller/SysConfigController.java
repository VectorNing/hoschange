package com.sesxh.hoschange.biz.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.biz.sys.service.SysConfigService;
import com.sesxh.hoschange.common.data.BizMsg;

/**
 * 
 * <p>
 * Title:SysConfigController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ning
 * @date 2017年1月12日
 */
@Controller
@RequestMapping("/sys")
public class SysConfigController extends ZBaseController {
	@Autowired
	private SysConfigService sysConfigService;

	private static final String  prefix = "sys/";
	/**
	 * 进入系统配置界面
	* @Title: enterSysConfig
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	@RequestMapping(value = "/enterSysConfig", method = RequestMethod.GET)
	public String enterSysConfig(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		return prefix + "pageSysConfig";
	}
	/**
	 * 查询配置信息
	* @Title: querySysConfig
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	@RequestMapping(value = "/querySysConfig", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String querySysConfig(@RequestParam Map<String, Object> params) throws BaseException {

		Map<String, Object> map = sysConfigService.querySysConfig(params);
		String json = JSON.toJSONString(map);
		return json;
	}
	/**
	 * 进入修改配置界面
	* @Title: enterEditSysConfig
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	@RequestMapping(value = "/enterEditSysConfig", method = RequestMethod.GET)
	public String enterEditSysConfig(Integer id, Model model) throws BaseException {
		SysConfig sysConfig = sysConfigService.querySysConfigById(id);
		model.addAttribute("sysConfig", sysConfig);
		return prefix + "respSysConfigEdit";
	}
	/**
	 * 修改系统配置
	* @Title: updateSysConfig
	* @author Ning 
	* @Description: 
	* @data:2017年1月13日
	 */
	@RequestMapping(value = "/updateSysConfig", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateSysConfig(SysConfig config) throws BaseException {
		String message="数据修改失败";
		int flag=sysConfigService.updateSysConfig(config);
		if(flag>0){
			message="数据修改成功";
		}
		return ajaxDoneSuccess(message);
	}
}
