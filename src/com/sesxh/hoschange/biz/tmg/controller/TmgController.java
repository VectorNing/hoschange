package com.sesxh.hoschange.biz.tmg.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.frame.base.BaseController;
import com.sesxh.hoschange.biz.tmg.core.SplitPage;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;
import com.sesxh.hoschange.biz.tmg.service.TmgService;

@Controller
@RequestMapping("/tmg")
public class TmgController extends BaseController {
	@Autowired
	private TmgService tmgService;

	/**
	 * 查询所有任务
	 * 
	 * @param map
	 * @param request
	 * @param taskinfo
	 * @return String
	 * @throws BaseException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/querytask")
	public String queryTask(ModelMap map, HttpServletRequest request, TaskScheduler taskinfo) throws BaseException {

		SplitPage sp;
		String rwnrlx, content;
		HttpSession session = request.getSession();
		// tmgService = super.getLocalService(TmgService.class, request);

		ServletContext servletContext = request.getSession().getServletContext();
		HashMap<String, HashMap<String, String>> codeList = (HashMap<String, HashMap<String, String>>) servletContext
				.getAttribute("code");
		HashMap<String, String> code = codeList.get("RWNRLX");
		if (request.getParameter("pageNumber") == "0" || "0".equals(request.getParameter("pageNumber"))) {
			String userCount = tmgService.getTaskCount(taskinfo);
			int countInt = Integer.parseInt(userCount);
			sp = new SplitPage(countInt);
			sp.setQueryClass(taskinfo);
			session.setAttribute("USER", sp);
		} else {
			sp = (SplitPage) session.getAttribute("USER");
			sp.setCurrentPage(request.getParameter("pageNumber"));
		}
		List<TaskScheduler> tasklist = tmgService.queryTaskList(taskinfo, sp.getPagesize(), sp.getStartRow());

		for (int i = 0; i < tasklist.size(); i++) {
			rwnrlx = tasklist.get(i).getRwnrlx();
			if ((!("".equals(rwnrlx))) && (rwnrlx != null)) {
				content = code.get(rwnrlx);
				if (("".equals(content)) || content == null)
					tasklist.get(i).setRwnrlx("");
				tasklist.get(i).setRwnrlx(content);
			}
		}
		map.put("tasklist", tasklist);
		String goUrl = request.getContextPath() + "/tmg/querytask.do";
		String bar = sp.getNavBar(goUrl);
		request.setAttribute("navbar", bar);
		return "tmg/querytaskresult";
	}

	/**
	 * 新增一个任务
	 * 
	 * @param map
	 * @param request
	 * @param taskinfo
	 * @return String
	 * @throws BaseException
	 */
	@RequestMapping("/addtask")
	@ResponseBody
	public String addTask(ModelMap map, HttpServletRequest request, TaskScheduler taskinfo) throws BaseException {
		tmgService.addTask(taskinfo);
		return "success";
	}

	/**
	 * 进入修改一个任务界面
	 * 
	 * @param map
	 * @param request
	 * @return String
	 * @throws BaseException
	 */
	@RequestMapping("/enterupdatetaskpage")
	public String enterUpdateTaskPage(ModelMap map, HttpServletRequest request) throws BaseException {
		String tsid;
		TaskScheduler taskinfo;
		tsid = request.getParameter("tsid");
		taskinfo = tmgService.getTaskinfoByTsid(tsid);
		map.put("taskinfo", taskinfo);
		return "tmg/updatetask";
	}

	/**
	 * 修改一个任务
	 * 
	 * @param map
	 * @param request
	 * @param taskinfo
	 * @return String
	 */
	@RequestMapping("/updatetask")
	@ResponseBody
	public String updatetask(ModelMap map, HttpServletRequest request, TaskScheduler taskinfo) {
		try {
			tmgService.updateTask(taskinfo);
			return "success";
		} catch (BaseException e) {
			return "提交信息出错";
		}
	}

	/**
	 * 注销/启用一个任务
	 * 
	 * @param map
	 * @param request
	 * @param taskinfo
	 * @return String
	 */
	@RequestMapping("/modtaskyxbz")
	@ResponseBody
	public String modtaskyxbz(ModelMap map, HttpServletRequest request, TaskScheduler taskinfo) {
		String tsid = request.getParameter("tsid");
		String yxbz = request.getParameter("yxbz");
		try {
			tmgService.modTaskYxbz(tsid, yxbz);
			return "success";
		} catch (BaseException e) {
			return "提交信息出错";
		}
	}
}
