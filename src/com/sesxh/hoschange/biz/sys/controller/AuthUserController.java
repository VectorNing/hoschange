package com.sesxh.hoschange.biz.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Controller
@RequestMapping("/authUser")
public class AuthUserController extends ZBaseController {

	@Autowired
	private AuthUserService authUserService;

	private String prefix = "auth";

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String enterAuthUser() throws BaseException {
		return prefix + "/pageUser";
	}

	@RequestMapping(value = "/queryAuthUserSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object queryAuthUserSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = authUserService.queryAuthRoleSet(params);
		Map<String, Object> map = new HashMap<>();
		map.put("total", lists.getTotal());
		List<Map<String, Object>> list = lists.getRows();
		map.put("rows", list);
		String json = JSON.toJSONString(map);
		return json;
	}

	@RequestMapping(value = "enterAddAuthUser", method = RequestMethod.GET)
	public String enterAddAuthUser() throws BaseException {
		return prefix + "/respUserAdd";
	}

	/**
	 * 查重用户名
	 * 
	 * @param loginName
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/selectUserByLoginName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectUserByLoginName(String loginName, Integer id) throws BaseException {
		Long count = authUserService.selectUserByLoginName(loginName, id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	@RequestMapping(value = "/selectUserByLoginName2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectUserByLoginName2(String loginName2, Integer id) throws BaseException {
		Long count = authUserService.selectUserByLoginName2(loginName2, id);
		BizMsg biz = new BizMsg();
		biz.setStatusCode(BizConst.SC.SC_200);
		biz.setStrData(String.valueOf(count));
		return biz;
	}

	@RequestMapping(value = "enterAssignUserToRole", method = RequestMethod.GET)
	public String enterAssignUserToRole(Model model, String id) throws BaseException {
		model.addAttribute("userId", id);
		return prefix + "/respUserRoleAssign";
	}

	@RequestMapping(value = "/assignUserToRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg assignUserToRole(Integer id, Integer[] roleIds) throws BaseException {
		authUserService.assignRoleToUser(id, Arrays.asList(roleIds));
		return ajaxDoneSuccess("分配角色成功！");
	}

	@RequestMapping(value = "/removeUserFromRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg removeUserFromRole(Integer id, Integer[] roleIds) throws BaseException {
		authUserService.removeRoleToUser(id, Arrays.asList(roleIds));
		return ajaxDoneSuccess("移除角色成功！");
	}

	/**
	 * 添加用户信息 (详情+登陆)11-25
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/createAuthUserAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object createAuthUserAll(AuthUserDetail authUserDetail) throws BaseException {
		authUserService.createAuthUserAll(authUserDetail);
		return ajaxDoneSuccess("操作成功！");
	}

	/**
	 * 加载修改用户 (详情+登陆)11-25
	 * 
	 * @param model
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "enterEditAuthUserAll", method = RequestMethod.GET)
	public String enterEditAuthUserAll(Model model, Integer id) throws BaseException {
		AuthUserDetail user = authUserService.selectUserDetailByAuthUserId(id);
		model.addAttribute("userDetail", user);
		return prefix + "/respUserEdit";
	}

	/**
	 * 修改用户信息(详情+登陆)11-25
	 * 
	 * @param AuthUserDetail
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/updateUserDedailAllById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object updateUserDedailAllById(AuthUserDetail AuthUserDetail) throws BaseException {
		authUserService.updateUserDedailAllById(AuthUserDetail);
		return ajaxDoneSuccess("信息更新成功！");
	}

	/**
	 * 批量注销用户信息11-25
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/disableBatchAuthUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object disableBatchAuthUser(Integer[] ids) throws BaseException {
		authUserService.disableBatchAuthUser(ids);
		return ajaxDoneSuccess("用户注销成功！");
	}

	/**
	 * 批量启用用户信息 11-25
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/enableBatchAuthUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object enableBatchAuthUser(Integer[] ids) throws BaseException {
		authUserService.enableBatchAuthUser(ids);
		return ajaxDoneSuccess("用户启用成功！");
	}

	/**
	 * 根据姓名模糊查询出人员信息
	 * 
	 * @Title: queryUserByName
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:Object
	 * @throws:
	 */
	@RequestMapping(value = "/queryUserByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryUserByName(String userName) throws BaseException {
		Map<String, Object> map = new HashMap<>();
		List<AuthUserDetail> lists = authUserService.selectUserDetailByAuthUserName(userName);
		map.put("message", "");
		map.put("value", lists);
		map.put("code", 200);
		map.put("redirect", "");
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = "uploadExcel", method = RequestMethod.GET)
	public String uploadExcel() throws BaseException {
		return prefix + "/respUploadExcel";
	}

	/**
	 * 上传Excel
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadUserInfoExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg uploadUserInfoExcel(@RequestParam("fileupload") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response, AuthUserDetail AuthUserDetail) throws BaseException, IOException {
		BizMsg bizMsg = new BizMsg();
		InputStream io = file.getInputStream();
		String msg = authUserService.uploadUserInfoExcel(io, AuthUserDetail);
		bizMsg.setMessage(msg);
		return bizMsg;
	}
}
