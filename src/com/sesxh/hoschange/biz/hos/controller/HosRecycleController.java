package com.sesxh.hoschange.biz.hos.controller;

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

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.service.HosRecycleService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosRecycle")
public class HosRecycleController extends ZBaseController {
	private static final String prefix = "hos/";
	@Autowired
	private HosRecycleService hosRecycleService;

	/**
	 * 进入回收桶管理
	 * @author Ning
	 * @return
	 * @throws BaseException 
	 */
	@RequestMapping(value = {
			"/enterHosRecycleAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosRecycleAll(HttpServletRequest request, HttpServletResponse response,Model model) throws BaseException {
		return prefix + "pageRecycle";
	}

	/**
	 * 进入增加回收桶的界面
	 * 
	 * @author Ning
	 * @return
	 */
	@RequestMapping(value = {
	"/enterAddHosHosRecycle"} , method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddHosHosRecycle() {
		return prefix + "respRecycleAdd";
	}

	/**
	 * 增加回收桶设备
	 * 
	 * @author Ning
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/addHosRecycle" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object addHosRecycle(HosRecycle hosRecycle) throws BaseException {
		hosRecycleService.insertRecycle(hosRecycle);
		return ajaxDoneSuccess("数据添加成功！");
	}

	/**
	 * 根据编号查询回收桶
	 * 
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectRecycleByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectRecycleByNumber(String number,Integer id) throws BaseException {
		HosRecycle hosRecycle =  hosRecycleService.selectRecycleByNumber(number,id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		if(hosRecycle!=null){
			biz.setStrData(String.valueOf("1"));
		}
		return biz;
	}

	/**
	 * 分页查询所有回收桶信息
	 * 
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/queryHosRecycleSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosRecycleSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosRecycleService.queryHosRecycleSet(params);
		return lists;
	}

	/**
	 * 进入修改回收桶页面
	 * 
	 * @author Ning
	 * @param id
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterEditHosRecycle", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditHosRecycle(Integer id, Model model) throws BaseException {
		HosRecycle hosRecycle = hosRecycleService.queryHosrecycleById(id);
		model.addAttribute("hosRecycle", hosRecycle);
		return prefix + "respRecycleEdit";
	}

	/**
	 * 修改回收桶信息
	 * 
	 * @author Ning
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/updateHosRecycle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object updateHosRecycle(HosRecycle hosRecycle) throws BaseException {
		hosRecycleService.updateHosRecycle(hosRecycle);
		return ajaxDoneSuccess("数据修改成功");
	}

	/**
	 * 根据回收桶编号查询回收数量
	 * 
	 * @author Ning
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectRecycleFromRecycleByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectRecycleFromRecycleByNumber(String number) throws BaseException {
		Long count = hosRecycleService.selectRecycleFromRecycleByNumber(number);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	/**
	 * 根据回收桶id删除回收桶
	 * 
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/deleteHosRecycle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteHosRecycle(Integer id) throws BaseException {
		hosRecycleService.deleteHosRecycle(id);
		return ajaxDoneSuccess("数据删除成功！");
	}

	/**
	 * 清空回收桶
	 * 
	 * @author Ning
	 * @param recycleNum
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyRecycle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyRecycle(String recycleNum) throws BaseException {
		hosRecycleService.emptyRecycle(recycleNum);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setMessage("数据清空成功");
		return biz;
	}
	/**
	 * 查询衣物管理员管辖范围内的回收桶信息
	 * @author Ning
	 * @param params
	 * @param request
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/queryHosRecycleMonitored", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosRecycleMonitored(@RequestParam Map<String, Object> params,HttpServletRequest request) throws BaseException {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		params.put("userId", user.getUserId());
		DataSet lists = hosRecycleService.queryHosRecycleMonitored(params);
		return lists;
	}
}
