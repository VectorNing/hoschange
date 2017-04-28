package com.sesxh.hoschange.biz.hos.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.biz.hos.service.HosBlacklistRuleService;
import com.sesxh.hoschange.biz.hos.service.HosBlacklistService;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.biz.sys.service.SysConfigService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosBlack")
public class HosBlackController extends ZBaseController {

	@Autowired
	private HosBlacklistRuleService hosBlacklistRuleService;
	@Autowired
	private HosBlacklistService hosBlacklistService;
	@Autowired
	private SysConfigService sysConfigService;

	private static final String prefix = "hos/";

	/**
	 * 跳转黑名单模式
	 * 
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterBlackListRule" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterBlackListRule(Model model) throws BaseException {
		String mode = hosBlacklistRuleService.selectHosBlacklistRuleMode();
		SysConfig sysConfig = sysConfigService.querySysConfigByConfigName(BizConst.SYSCONFIG.ISORNOROSTER);
		model.addAttribute("mode", mode);
		model.addAttribute("sysConfig", sysConfig);
		return prefix + "modeOne/pageBlacklistRuleOne";
	}

	@RequestMapping(value = "/selectBlackListRuleSetM1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectBlackListRuleSetM1(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosBlacklistRuleService.selectHosBlacklistRuleSetM1(params);
		return lists;
	}

	@RequestMapping(value = "/selectBlackListRuleSetM2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectBlackListRuleSetM2(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosBlacklistRuleService.selectHosBlacklistRuleSetM2(params);
		return lists;
	}

	@RequestMapping(value = {
			"/enterEditBlacklistRuleM1" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditBlacklistRuleM1(Integer id, String mode, Model model) throws BaseException {
		HosBlacklistRule blacklistRule = hosBlacklistRuleService.selectHosBlacklistRuleById(id);
		model.addAttribute("blacklistRule", blacklistRule);
		if (mode.equals(BizConst.BLACKLIST_MODEL.ONE)) {
			return prefix + "modeOne/respBlacklistRuleOneEdit";
		} else {
			return prefix + "modeTwo/respBlacklistRuleTwoEdit";
		}
	}

	@RequestMapping(value = "/updateBlacklistRuleM1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateBlacklistRuleM1(HosBlacklistRule hosBlacklistRule) throws BaseException {
		hosBlacklistRuleService.updateHosBlacklistRuleM1(hosBlacklistRule);
		return ajaxDoneSuccess("数据修改成功！");
	}

	@RequestMapping(value = {
			"/enterEditBlacklistRuleM2" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditBlacklistRuleM2(Integer id, Model model) throws BaseException {
		HosBlacklistRule blacklistRule = hosBlacklistRuleService.selectHosBlacklistRuleById(id);
		model.addAttribute("blacklistRule", blacklistRule);
		return prefix + "modeTwo/respBlacklistRuleTwoEdit";
	}

	@RequestMapping(value = "/updateBlacklistRuleM2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateBlacklistRuleM2(HosBlacklistRule hosBlacklistRule) throws BaseException {
		hosBlacklistRuleService.updateHosBlacklistRuleM2(hosBlacklistRule);
		return ajaxDoneSuccess("数据修改成功！");
	}

	// #################################### 黑灰名单管理
	// #######################################//

	/**
	 * 跳转黑名单列表
	 * 
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = { "/enterBlacklist" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterBlacklist(Model model) throws BaseException {
		return prefix + "pageBlacklist";
	}

	/**
	 * 跳转灰名单列表
	 * 
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = { "/enterGreylist" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterGreylist(Model model) throws BaseException {
		return prefix + "pageGreylist";
	}

	@RequestMapping(value = "/selectBlacklistSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectBlacklistSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosBlacklistService.selcetHosBlacklistSet(params);
		return lists;
	}

	/**
	 * @author Ning
	 * @param hosBlacklist
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/updateHosBacklist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosBacklist(HosBlacklist hosBlacklist, HttpServletRequest httpServletRequest)
			throws BaseException {
		SessionUser sessionUser = (SessionUser) httpServletRequest.getSession().getAttribute(BizConst.SESSIONUSER);
		hosBlacklist.setOperationUserId(sessionUser.getUserId());
		hosBlacklistService.updateHosBlacklist(hosBlacklist);
		return ajaxDoneSuccess("数据修改成功！");
	}

	@RequestMapping(value = {
			"/enterBlacklistRuleChangeMode" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterBlacklistRuleChangeMode(Model model) throws BaseException {
		String mode = hosBlacklistRuleService.selectHosBlacklistRuleMode();
		model.addAttribute("mode", mode);
		return prefix + "respBlacklistRuleChangeMode";
	}

	/**
	 * 修改黑名单模式
	 * 
	 * @Title: updateBlacklistRuleMode
	 * @author Ning
	 * @Description:
	 * @data:2017年1月9日
	 */
	@RequestMapping(value = "/updateBlacklistRuleMode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateBlacklistRuleMode(HosBlacklistRule hosBlacklistRule) throws BaseException {
		hosBlacklistRuleService.updateBlacklistRuleMode(hosBlacklistRule);
		return ajaxDoneSuccess("数据修改成功！");
	}

	/**
	 * @author xn
	 * @param hosBlacklist
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/displayScreenHosBacklist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg displayScreenHosBacklist(String[] ids, String display) throws BaseException {
		hosBlacklistService.displayScreenHosBacklist(ids, display);
		return ajaxDoneSuccess("数据修改成功！");
	}
}
