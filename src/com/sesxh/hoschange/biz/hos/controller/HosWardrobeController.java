package com.sesxh.hoschange.biz.hos.controller;

import java.util.List;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;
import com.sesxh.hoschange.biz.hos.service.HosOperationService;
import com.sesxh.hoschange.biz.hos.service.HosRoomService;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeContainerService;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeService;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosWardrobe")
public class HosWardrobeController extends ZBaseController {

	@Autowired
	private HosWardrobeService hosWardrobeService;
	@Autowired
	private HosOperationService hosOperationService;
	@Autowired
	private HosWardrobeContainerService hosWardrobeContainerService;
	@Autowired
	private HosRoomService hosRoomService;
	
	private static final String prefix = "hos/";
	
	
	/**
	 * 查询衣柜信息列表数据
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/queryHosWardrobeSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosWardrobeSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosWardrobeService.selectHosWardrobeSet(params);
		return lists;
	}

	/**
	 * 跳转衣柜信息列表页面
	 * @return
	 */
	@RequestMapping(value = {"/enterHosWardrobeAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosWardrobeAll() {
		return prefix + "pageWardrobe";
	}

	/**
	 * 跳转衣柜添加页面
	 * @return
	 */
	@RequestMapping(value = {"/enterAddHosWardrobe" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddHosWardrobe() {
		return prefix + "respWardrobeAdd";
	}
	
	/**
	 * 衣柜信息保存
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/addHosWardrobe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		hosWardrobeService.insertHosWardrobe(hosWardrobe);
		return ajaxDoneSuccess("数据添加成功！");
	}
	/**
	 * 跳转衣柜更新页面
	 * @param modal
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {"/enterEditHosWardrobe" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditHosWardrobe(Model modal,Integer id) throws BaseException {
		HosWardrobe hosWardrobe = hosWardrobeService.selectHosWardrobeById(id);
		HosRoom hosRoom = hosRoomService.selectHosRoomByRoomId(hosWardrobe.getRoomId());
		modal.addAttribute("hosWardrobe", hosWardrobe);
		modal.addAttribute("hosRoom", hosRoom);
		return prefix + "respWardrobeEdit";
	}
	/**
	 * 更新衣柜信息保存
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/updateHosWardrobe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		hosWardrobeService.updateHosWardrobe(hosWardrobe);
		return ajaxDoneSuccess("数据修改成功！");
	}
	/**
	 * 衣柜信息删除
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/deleteHosWardrobe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteHosWardrobe(Integer id,String number) throws BaseException {
		hosWardrobeService.deleteHosWardrobeByIdAndNumber(id, number);
		return ajaxDoneSuccess("数据删除成功！");
	}
	

	/**
	 * 
	 * @param number
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectWarByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectWarByNumber(String number,Integer id) throws BaseException {
		Long count = hosWardrobeService.selectWarByNumber(number, id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}
	
	/**
	 * 
	 * @param modal
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {"/enterDefendContainer" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterDefendContainer(Model modal,Integer id) throws BaseException {
		HosWardrobe hosWardrobe = hosWardrobeService.selectHosWardrobeById(id);
		List<HosWardrobeContainer> con = hosWardrobeContainerService.selectHosWardrobeContainerByWarNumber(hosWardrobe.getNumber());
		modal.addAttribute("hosWardrobe", hosWardrobe);
		modal.addAttribute("con", con);
		return prefix + "respWardrobeContaineAdd";
	}
	

	@RequestMapping(value = "/addContaineForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addContaineForm(@RequestParam Map<String,Object> params) throws BaseException {
		hosWardrobeService.insertContainer(params);
		return ajaxDoneSuccess("数据添加成功！");
	}
	
	
	
	@RequestMapping(value = "/selectOperationFromWardBynumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg selectOperationFromWardBynumber(String number) throws BaseException {
		Long count = hosWardrobeService.selectOperationFromWardBynumber( number);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}
	
	
	/**
	 * 跳转设定尺码页面（管理员）
	 * @param warNumber
	 * @param modal
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterSettingOperationSizePage", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSettingOperationSizePage(String warNumber,Model modal) throws BaseException {
		//hosWardrobeService.deleteHosWardrobeById(id);
		List<Map<String,Object>> rooms = Lists.newArrayList();
		
		List<HosWardrobeContainer> HosWardrobeContainerList = hosWardrobeContainerService.selectHosWardrobeContainerByWarNumber(warNumber);
		
		Map<String,Object> map = Maps.newHashMap();
		
		map.put("warNumber", warNumber);
		map.put("HosWardrobeContainerList", HosWardrobeContainerList);
		rooms.add(map);
		modal.addAttribute("rooms", rooms);
		return prefix + "modeOne/respWardrobeSettingOperationSize";
	}
	
	/**
	 * 保存设定尺码（管理员）
	 * @param ids
	 * @param opeSize
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/settingOperationSize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg settingOperationSize(Integer[] ids,String opeSize) throws BaseException {
		hosWardrobeContainerService.settingOperationSizeForContainer(ids, opeSize);
		return ajaxDoneSuccess("尺码设定成功！");
	}
	
	//=================(结束)==================2016/11/30确认需求最终版================================================//
	
	
	
	
	
	@RequestMapping(value = "/enterAllotOperationPage", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAllotOperationPage(String warNumber,Model modal) throws BaseException {
		//hosWardrobeService.deleteHosWardrobeById(id);
		List<Map<String,Object>> rooms = Lists.newArrayList();
		
		List<HosWardrobeContainer> HosWardrobeContainerList = hosWardrobeContainerService.selectHosWardrobeContainerByWarNumber(warNumber);
		
		Map<String,Object> map = Maps.newHashMap();
		
		map.put("warNumber", warNumber);
		map.put("HosWardrobeContainerList", HosWardrobeContainerList);
		rooms.add(map);
		modal.addAttribute("rooms", rooms);
		return prefix + "modeOne/respWardrobeOperationAllot";
	}
	
	/**
	 * 衣物管理员查看  手术衣柜使用信息
	 * @param warNumber
	 * @param modal
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterLoadOperationPage", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterLoadOperationPage(String warNumber,Model modal) throws BaseException {
		modal.addAttribute("warNumber",warNumber);
		return prefix + "modeOne/respWardrobeOperationLoad";
	}
	
	@RequestMapping(value = "/enterLoadOperationPageList", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterLoadOperationPageList(String warNumber,Model modal) throws BaseException {
		//hosWardrobeService.deleteHosWardrobeById(id);
		List<Map<String,Object>> rooms = Lists.newArrayList();
		
		List<HosWardrobeContainer> HosWardrobeContainerList = hosWardrobeContainerService.selectHosWardrobeContainerByWarNumber(warNumber);
		
		Map<String,Object> map = Maps.newHashMap();
		
		map.put("warNumber", warNumber);
		map.put("HosWardrobeContainerList", HosWardrobeContainerList);
		rooms.add(map);
		modal.addAttribute("rooms", rooms);
		return prefix + "modeOne/respWardrobeOperationLoadList";
	}
	
	//===========(开始)======================/2016/12/1=====================================================//
	/**
	 * 跳转设定尺码页面(管理员)
	 * @param warNumber
	 * @param modal
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterAllotOperationPageM1", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAllotOperationPageM1(String warNumber,Model modal) throws BaseException {
		modal.addAttribute("warNumber", warNumber);
		return prefix + "modeOne/respWardrobeSettingOperationSize";
	}
	
	/**
	 * 跳转设定托盘存放尺码(管理员)
	 * @param warNumber
	 * @param modal
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enterOperationPageList", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterOperationPageList(String warNumber,Model model) throws BaseException {
		List<Map<String,Object>> rooms = Lists.newArrayList();
		List<HosWardrobeContainer> HosWardrobeContainerList = hosWardrobeContainerService.selectHosWardrobeContainerByWarNumber(warNumber);
		List<Map<String, Object>> sizeAndCountList = hosOperationService.queryHosOperationList(warNumber);
		Map<String,Object> map = Maps.newHashMap();
		map.put("warNumber", warNumber);
		map.put("rows", 1);
		map.put("columns", HosWardrobeContainerList.size());
		map.put("HosWardrobeContainerList", HosWardrobeContainerList);
		rooms.add(map);
		model.addAttribute("rooms", rooms);
		model.addAttribute("sizeAndCountList", sizeAndCountList);
		return prefix + "modeOne/respWardrobeLists";
	}
	
	
	@RequestMapping(value = "/selectHosOperation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectHosOperation() throws BaseException {
		List<ClassConvertDict> list = hosOperationService.selectHosOperation();
		return list;
	}
	
	/**
	 * 设定托盘存放尺码(管理员)
	 * @param warNumber
	 * @param modal
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/allotOperation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotOperation(Integer[] ids,String clothSize) throws BaseException {
		hosWardrobeContainerService.settingOperationSizeForContainer(ids, clothSize);
		return ajaxDoneSuccess("尺码设定成功！");
	}
	/**
	 * 清空小托盘(管理员)
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyContainer(Integer[] ids) throws BaseException {
		hosWardrobeContainerService.emptyContainer(ids);
		return ajaxDoneSuccess("托盘清空成功！");
	}
	/**
	 * 清空全部小托盘(管理员)
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/emptyAllContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyAllContainer(String number) throws BaseException {
		hosWardrobeContainerService.emptyAllContainer(number);
		return ajaxDoneSuccess("数据清空成功！");
	}
	
	//=============结束====================/2016/12/1=====================================================//
	
	@RequestMapping(value="/enterAssignWarToTheater",method=RequestMethod.GET,produces=MediaType.TEXT_PLAIN_VALUE)
	public Object enterAssignWarToTheater(Model model,String theaterNumber) throws BaseException {
		model.addAttribute("theaterNumber", theaterNumber);
		return prefix+"/respTheaterWarAssign";
	}
	
	@RequestMapping(value="/loadWardrobeListByTheNumber",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadWardrobeListByTheNumber(@RequestParam Map<String,Object> params ) throws BaseException {
		 DataSet ds =  hosWardrobeService.loadWardrobeListByTheNumber(params);
		 return ds;
	}
	
	@RequestMapping(value="/assignTheaterToWar",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object assignTheaterToWar(String number, Integer [] ids) throws BaseException {
	    hosWardrobeService.assignTheaterToWar(number, ids);
		return ajaxDoneSuccess("分配手术衣柜成功！");
	}
	
	@RequestMapping(value="/removeWarFromTheater",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object removeWarFromTheater(String number,Integer [] ids) throws BaseException {
		hosWardrobeService.removeWarFromTheater(number, ids);
		return ajaxDoneSuccess("移除手术衣柜成功！");
	}
	
	
//################################# 衣物管理员加载管辖范围 衣柜 ##############################################//
	/**
	 * 衣柜监控
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectHosWardrobeMonitored", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectHosWardrobeMonitored(@RequestParam Map<String, Object> params,HttpServletRequest request) throws BaseException {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER) ;
		params.put("userId", user.getUserId());
		DataSet lists = hosWardrobeService.selectHosWardrobeMonitored(params);
		return lists;
	}
	
}
