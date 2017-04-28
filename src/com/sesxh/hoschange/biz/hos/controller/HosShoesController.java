package com.sesxh.hoschange.biz.hos.controller;

import java.util.List;
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
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.hos.service.HosShoesService;
import com.sesxh.hoschange.biz.hos.service.HosTheaterService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosShoes")
public class HosShoesController extends ZBaseController {

	@Autowired
	private HosShoesService hosShoesService;
	@Autowired
	private HosTheaterService hosTheaterService;
	
	private static final String prefix = "hos/";

	/**
	 * 根据尺码加载 不同的分配，使用中，回收的消毒鞋信息
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectHosShoesList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectHosShoesList(@RequestParam Map<String, Object> params) throws BaseException {
		List<Map<String, Object>> list = hosShoesService.selectHosShoesList(params);
		Long total = Long.valueOf(list.size());
		DataSet ds = DataSet.newDS(total, list);
		return ds;
	}

	@RequestMapping(value = { "/enterHosShoesAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosShoesAll(HttpServletRequest request, HttpServletResponse response, Model model)
			throws BaseException {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		List<HosTheater> hosTheaters = hosTheaterService.selectTheaterByUserId(sessionUser.getUserId());
		model.addAttribute("roomLists", hosTheaters);
		return prefix + "modeOne/pageShoesOne";
	}

	@RequestMapping(value = { "/enterAddhosShoes" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddhosShoes(HttpServletRequest request, HttpServletResponse response, Model model)
			throws BaseException {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		List<HosTheater> hosTheaters = hosTheaterService.selectTheaterByUserId(sessionUser.getUserId());
		model.addAttribute("roomLists", hosTheaters);
		return prefix + "modeOne/respShoesOneAdd";
	}

	@RequestMapping(value = "/addhosShoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addhosShoes(HosShoes hosShoes) throws BaseException {
		hosShoesService.insertHosShoesM1(hosShoes);
		return ajaxDoneSuccess("数据添加成功！");
	}
	@RequestMapping(value = { "/enterEdithosShoes" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEdithosShoes(Integer id, Integer yfp, Model model) throws BaseException {
		HosShoes hosShoes = hosShoesService.findHosShoesById(id);
		model.addAttribute("hosShoes", hosShoes);
		model.addAttribute("yfp", yfp);
		return prefix + "modeOne/respShoesOneEdit";
	}

	@RequestMapping(value = "/updatehosShoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updatehosShoes(HosShoes hosShoes) throws BaseException {
		hosShoesService.updateHosShoesM1(hosShoes);
		return ajaxDoneSuccess("数据修改成功！");
	}

	@RequestMapping(value = "/deletehosShoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deletehosShoes(Integer id) throws BaseException {
		hosShoesService.deleteHosShoesById(id);
		return ajaxDoneSuccess("数据删除成功！");
	}

	// ####################### 衣物管理员根据所在更衣室 加载消毒鞋 #########################//

}
