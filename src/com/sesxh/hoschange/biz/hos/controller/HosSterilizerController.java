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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.service.HosShoesService;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerContainerService;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerService;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/hosSterilizer")
public class HosSterilizerController extends ZBaseController {

	@Autowired
	private HosSterilizerService hosSterilizerService;
	@Autowired
	private HosSterilizerContainerService hosSterilizerContainerService;
	@Autowired
	private HosShoesService hosShoesServiceImpl;

	private static final String prefix = "hos/";

	@RequestMapping(value = "/queryHosSterilizerSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosSterilizerSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosSterilizerService.selectHosSterilizerSet(params);
		return lists;
	}

	@RequestMapping(value = {
			"/enterHosSterilizerAll" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosSterilizerAll() {
		return prefix + "pageSterilizer";
	}

	@RequestMapping(value = "/selectSteByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectSteByNumber(String number, Integer id) throws BaseException {
		Long count = hosSterilizerService.selectSteByNumber(number, id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	@RequestMapping(value = {
			"/enterAddHosSterilizer" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddHosSterilizer() {
		return prefix + "respSterilizerAdd";
	}

	@RequestMapping(value = "/addHosSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		hosSterilizerService.insertHosSterilizer(hosSterilizer);
		return ajaxDoneSuccess("数据添加成功！");
	}

	@RequestMapping(value = {
			"/enterEditHosSterilizer" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterEditHosSterilizer(Integer id, Model model) throws BaseException {
		HosSterilizer hosSterilizer = hosSterilizerService.findHosShoesSterilizerById(id);
		model.addAttribute("hosSterilizer", hosSterilizer);
		return prefix + "respSterilizerEdit";
	}

	@RequestMapping(value = "/updateHosSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		hosSterilizerService.updateHosSterilizer(hosSterilizer);
		return ajaxDoneSuccess("数据修改成功！");
	}

	@RequestMapping(value = "/selectShoesFromSterilizerByNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectShoesFromSterilizerByNumber(String number) throws BaseException {
		Long count = hosSterilizerService.selectShoesFromSterilizerByNumber(number);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	@RequestMapping(value = "/deleteHosSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg deleteHosSterilizer(Integer id) throws BaseException {
		hosSterilizerService.deleteHosSterilizerById(id);
		return ajaxDoneSuccess("数据删除成功！");
	}

	@RequestMapping(value = { "/enterAllotShoes" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAllotShoes(String steNumber, Integer rows, Integer columns, Model model) throws BaseException {
		List<Map<String, Object>> rooms = Lists.newArrayList();
		List<HosSterilizerContainer> containerList = hosSterilizerContainerService
				.selectSterilizerContainerBySteNumber(steNumber);
		Map<String, Object> map = Maps.newHashMap();
		map.put("steNumber", steNumber);
		map.put("rows", rows);
		map.put("containerList", containerList);
		rooms.add(map);
		model.addAttribute("rooms", rooms);
		return prefix + "modeOne/respSterilizerShoesAllot";
	}

	@RequestMapping(value = "/enterSteAllotShoes", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSteAllotShoes(Model model, String shoesSize, String length) throws BaseException {
		model.addAttribute("shoeSize", shoesSize);
		model.addAttribute("length", length);
		return prefix + "modeTwo/respSterilizerTwoAllotShoes";
	}

	@RequestMapping(value = "/allotShoesToSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotShoesToSterilizer(String[] ids, Integer sid) throws BaseException {
		System.err.println(ids + ";" + sid);

		return ajaxDoneSuccess("数据删除成功！");
	}

	@RequestMapping(value = "/queryShoesAllowCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg queryShoesAllowCount(String shoesSize) throws BaseException {
		BizMsg bm = new BizMsg();
		bm.setStatusCode(BizConst.SC.SC_200);
		return bm;
	}

	@RequestMapping(value = "/allotShoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotShoes(Integer[] lockerNumbers, String shoesSize, int allotCount) throws BaseException {
		hosSterilizerContainerService.allotShoesToContainer(lockerNumbers, shoesSize, allotCount);
		return ajaxDoneSuccess("数据分配成功！");
	}

	/*@RequestMapping(value = "/allotShoesTwo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotShoesTwo(Integer[] lockerNumbers, Integer[] ids) throws BaseException {
		return ajaxDoneSuccess("数据分配成功！");
	}*/

	@RequestMapping(value = "/emptyContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyContainer(Integer[] ids) throws BaseException {
		hosSterilizerContainerService.emptyContainer(ids);
		return ajaxDoneSuccess("数据清空成功！");
	}

	@RequestMapping(value = "/emptyAllContainer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg emptyAllContainer(String number) throws BaseException {
		hosSterilizerContainerService.emptyAllContainer(number);
		return ajaxDoneSuccess("数据清空成功！");
	}

	@RequestMapping(value = "/enterAssignSteToTheater", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public Object enterAssignSteToTheater(Model model, String theaterNumber) throws BaseException {
		model.addAttribute("theaterNumber", theaterNumber);
		return prefix + "/respTheaterSteAssign";
	}

	@RequestMapping(value = "/loadSterilizerListByTheNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object loadSterilizerListByTheNumber(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet ds = hosSterilizerService.loadSterilizerListByTheNumber(params);
		return ds;
	}

	@RequestMapping(value = "/assignTheaterToSte", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object assignTheaterToSte(String number, Integer[] ids) throws BaseException {
		hosSterilizerService.assignTheaterToSte(number, ids);
		return ajaxDoneSuccess("分配消毒柜成功！");
	}

	@RequestMapping(value = "/removeSteFromTheater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object removeSteFromTheater(String number, Integer[] ids) throws BaseException {
		hosSterilizerService.removeSteFromTheater(number, ids);
		return ajaxDoneSuccess("移除消毒柜成功！");
	}

	// ############################# 11-30 - 最新
	// #######################################//

	/**
	 * 管理员跳转设定消毒鞋尺码 modal1
	 * 
	 * @param steNumber
	 * @param rows
	 * @param columns
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterSteAllotShoesM1" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSteAllotShoesM1(String steNumber, Integer rows, Integer columns, Model model)
			throws BaseException {
		model.addAttribute("steNumber", steNumber);
		model.addAttribute("rows", rows);
		model.addAttribute("columns", columns);
		return prefix + "modeOne/respSterilizerSettingShoesSize";
	}

	/**
	 * 动态加载 消毒鞋柜的小柜信息  根据type返回页面
	 * 
	 * @param steNumber
	 * @param rows
	 * @param type
	 * @param columns
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {
			"/enterAllotShoesList" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAllotShoesList(String steNumber, Integer rows, Integer columns, Model model,String type)
			throws BaseException {
		List<Map<String, Object>> rooms = Lists.newArrayList();
		List<HosSterilizerContainer> containerList = hosSterilizerContainerService
				.selectSterilizerContainerBySteNumber(steNumber);
		List<HosShoes> shoesList = hosShoesServiceImpl.loadShoeSizeBySteNumber( steNumber);
		Map<String, Object> map = Maps.newHashMap();
		map.put("steNumber", steNumber);
		map.put("rows", rows);
		map.put("columns", columns);
		map.put("containerList", containerList);
		rooms.add(map);
		model.addAttribute("rooms", rooms);
		model.addAttribute("shoesList", shoesList);
		if(type.equals("lock")){//锁箱
			return prefix + "modeOne/respSterilizerListsLock";
		}else if(type.equals("binding")){//绑定
			return prefix + "modeOne/respSterilizerListsBinding";
		}else{//设定尺码
			return prefix + "modeOne/respSterilizerLists";
		}
	}

	/**
	 * 管理员分配 消毒鞋尺码
	 * 
	 * @param lockerNumbers
	 * @param shoesSize
	 * @param allotCount
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/allotSizeShoesToSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg allotSizeShoesToSterilizer(Integer[] ids, String shoesSize) throws BaseException {
		hosSterilizerContainerService.allotSizeShoesToSterilizer(ids, shoesSize);
		return ajaxDoneSuccess("尺码设定成功！");
	}

	// --------------------衣物管理员查看鞋柜小柜的信息-------------------------------

	@RequestMapping(value = { "/enterSteLoadShoes" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSteLoadShoes(String steNumber, Integer rows, Integer columns, Model model) throws BaseException {
		model.addAttribute("steNumber", steNumber);
		model.addAttribute("rows", rows);
		model.addAttribute("columns", columns);
		return prefix + "modeOne/respSterilizerShoesLoad";
	}

	@RequestMapping(value = {
			"/enterSteLoadShoesList" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSteLoadShoesList(String steNumber, Integer rows, Integer columns, Model model)
			throws BaseException {
		List<Map<String, Object>> rooms = Lists.newArrayList();
		List<HosSterilizerContainer> containerList = hosSterilizerContainerService
				.selectSterilizerContainerBySteNumber(steNumber);
		Map<String, Object> map = Maps.newHashMap();
		map.put("steNumber", steNumber);
		map.put("rows", rows);
		map.put("columns", columns);
		map.put("containerList", containerList);
		rooms.add(map);
		model.addAttribute("rooms", rooms);
		return prefix + "modeOne/respSterilizerShoesLoadLists";
	}

	// ########################### 衣物管理员 加载 所属手术区的消毒鞋列表
	// ###############################//

	@RequestMapping(value = "/selectHosSterilizerMonitored", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectHosSterilizerMonitored(@RequestParam Map<String, Object> params,
			HttpServletRequest request) throws BaseException {
		SessionUser user = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		params.put("userId", user.getUserId());
		DataSet lists = hosSterilizerService.selectHosSterilizerMonitored(params);
		return lists;
	}
	
	 /**
	  * 给鞋柜小柜绑定医护人员页面跳转
	  * @author xujialong
	  * @param steNumber
	  * @param rows
	  * @param columns
	  * @param model
	  * @return
	  * @throws BaseException
	  */
	@RequestMapping(value = {"/enterSetShoesUserHosSterilizer" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterSetShoesUserHosSterilizer(String steNumber, Integer rows, Integer columns, Model model) throws BaseException {
		model.addAttribute("steNumber", steNumber);
		model.addAttribute("rows", rows);
		model.addAttribute("columns", columns);
		return prefix + "respSterilizerSetShoesUser";
	}
	/**
	 * 打开或者锁定小柜页面跳转
	 * @author xujialong
	 * @param steNumber
	 * @param rows
	 * @param columns
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {"/enterOpenOrLockHosSterilizer" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterOpenOrLockHosSterilizer(String steNumber, Integer rows, Integer columns, Model model) throws BaseException {
		model.addAttribute("steNumber", steNumber);
		model.addAttribute("rows", rows);
		model.addAttribute("columns", columns);
		return prefix + "respSterilizerOpenOrLock";
	}
	/**
	 * 锁定鞋柜小柜
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/lockSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg lockSterilizer (Integer[] ids) throws BaseException{
		hosSterilizerContainerService.lockSterilizerContainer(ids);
		return ajaxDoneSuccess("锁定成功");
	}
	/**
	 * 取消锁定鞋柜小柜
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/stopLockSterilizer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg stopLockSterilizer (Integer[] ids) throws BaseException{
		hosSterilizerContainerService.stopLockSterilizer(ids);
		return ajaxDoneSuccess("取消锁定成功");
	}
	/**
	 * 给鞋柜小柜绑定医护人员
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/bindingSterilizerAndUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg bindingSterilizerAndUser (Integer id , Integer userId) throws BaseException{
		hosSterilizerContainerService.bindingSterilizerAndUser(id, userId);
		return ajaxDoneSuccess("绑定成功");
	}
	/**
	 * 取消鞋柜小柜与医护人员的绑定
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/stopBindingSterilizerAndUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg stopBindingSterilizerAndUser (Integer id) throws BaseException{
		hosSterilizerContainerService.stopBindingSterilizerAndUser(id);
		return ajaxDoneSuccess("取消绑定成功");
	}
	/**
	 * 跳转设置添加消毒鞋规则界面
	 * @author xujialong
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {"/enterAddShoeRule" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterAddShoeRule() throws BaseException{
		return prefix + "pageAddShoeRule";
	}
	/**
	 * 加载添加消毒鞋规则
	 * @author xujialong
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectAddShoeRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataSet selectAddShoeRule(@RequestParam Map<String, Object> params)throws BaseException{
		DataSet lists = hosSterilizerContainerService.selectAddShoeRule(params);
		return lists;
	}
	/**
	 * 设置按鞋码添加鞋子时跳转设定鞋码页面
	 * @author xujialong
	 * @param id
	 * @param model
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = {"/addShoeRuleSetSize" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String addShoeRuleSetSize(String code, Model model) throws BaseException{
		model.addAttribute("code",code);
		return prefix + "respAddShoeRuleSetSize";
	}
	/**
	 * 设定添加鞋子规则
	 * @author xujialong
	 * @param id
	 * @param size
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/addShoeRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg addShoeRule(String code,Integer shoesSize,HttpServletRequest request, HttpServletResponse response) throws BaseException{
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		hosSterilizerContainerService.setUserAddShoeRule(code,sessionUser.getUserId(),shoesSize);
		return ajaxDoneSuccess("设置成功");
	}
	/**
	 * 打开鞋柜记录日志
	 * @author xujialong
	 * @param deviceNumber
	 * @param numbers
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/openContainerShoesLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg openContainerShoesLog(String deviceNumber,String[] numbers,HttpServletRequest request, HttpServletResponse response) throws BaseException{
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(BizConst.SESSIONUSER);
		hosSterilizerContainerService.openContainerShoesLog(deviceNumber, numbers, sessionUser);
		return ajaxDoneSuccess("柜门已打开");
	}
	
}
