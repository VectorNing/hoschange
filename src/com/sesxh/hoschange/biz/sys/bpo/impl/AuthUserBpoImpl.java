package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.AuthUserBpo;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.MD5Util;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.AuthUserBpoImpl")
public class AuthUserBpoImpl extends SesframeBaseBpo implements AuthUserBpo {

	@Autowired
	private AuthUserDao authUserDao;

	public String loadSaltForLoginName(String loginName) throws BaseException {
		// ZAssert.hasText(loginName, "用户名为空或者登录超时！");
		if (loginName == null || loginName.isEmpty()) {
			return null;
		}
		List<Map<String, Object>> lists = authUserDao.selectSaltByLoginName(loginName);
		if (lists == null || lists.size() <= 0) {
			return null;
		}
		String salt = (String) lists.get(0).get("salt");
		return salt;
	}

	public AuthUser loadByLoginNameAndPas(String loginName, String password) throws BaseException {
		if (loginName == null || loginName.isEmpty() || password == null || password.isEmpty()) {
			return null;
		}
		return authUserDao.selectByLoginNameAndPas(loginName, password);
	}

	@Override
	public AuthUser findByLoginName(String loginName) throws BaseException {
		if (loginName == null || loginName.isEmpty()) {
			return null;
		}
		return authUserDao.findByLoginName(loginName);
	}

	@Override
	public AuthUser findByLoginName2(String loginName2) throws BaseException {
		if (loginName2 == null || loginName2.isEmpty()) {
			return null;
		}
		return authUserDao.findByLoginName2(loginName2);
	}

	@Override
	public AuthUser findBySfzh(String sfzh) throws BaseException {
		if (sfzh == null || sfzh.isEmpty()) {
			return null;
		}
		return authUserDao.findBySfzh(sfzh);
	}

	public SessionUser loadUserInfor(Integer id) throws BaseException {
		SessionUser sessionUser = new SessionUser();
		if (id == null) {
			return null;
		}
		AuthUser user = authUserDao.selectByPrimaryKey(id);
		if (user == null) {
			return null;
		}
		sessionUser.setLoginName(user.getLoginName());
		sessionUser.setEnabled(user.getEnabled());
		sessionUser.setLogonTime(user.getLastLoginTime());
		sessionUser.setUserId(user.getId());
		return sessionUser;
	}

	public AuthUser loadUserInById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		AuthUser user = authUserDao.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public DataSet loadAuthRoleSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String userName = pm.getStrParam("userName");
		if (!ZStrUtil.isEmptyAfterTrimE(userName)) {
			pm.updateParam("userName", "%" + userName + "%");
		}
		Integer type = pm.getIntegerParam("type");
		if (type != null) {
			pm.updateParam("type", type);
		}
		DataSet ds = DataSet.newDS(authUserDao.selectAuthUserCount(pm), authUserDao.selectAuthUserList(pm));
		return ds;
	}

	public int assignRoleToUser(Integer id, List<Integer> roleIds) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		int falg = 0;
		if (roleIds == null || roleIds.isEmpty()) {
			ZAlert.Error("请选择数据");
		}
		for (Integer roleId : roleIds) {
			falg = authUserDao.removeRoleFromUser(id, roleId);
		}

		for (Integer roleId : roleIds) {
			falg = authUserDao.assignRoleToUser(id, roleId);
		}
		return falg;
	}

	public int removeRoleFromUser(Integer id, List<Integer> roleIds) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		int falg = 0;
		if (roleIds == null || roleIds.isEmpty()) {
			ZAlert.Error("请选择数据");
		}
		for (Integer roleId : roleIds) {
			falg = authUserDao.removeRoleFromUser(id, roleId);
		}
		return falg;
	}

	public Long selectUserByLoginName(String loginName, Integer id) throws BaseException {
		// ZAssert.hasText(loginName, "用户名不能为空");
		return authUserDao.selectUserByLoginName(loginName, id);
	}

	public Long selectUserByLoginName2(String loginName2, Integer id) throws BaseException {
		// ZAssert.hasText(loginName2, "卡号不能为空");
		return authUserDao.selectUserByLoginName2(loginName2, id);
	}

	// -----------------------------------11-25---------------------

	public int createAuthUserAll(AuthUserDetail authUserDetail) throws BaseException {
		AuthUser authUser = new AuthUser();
		String loginName = authUserDetail.getLoginName();
		ZAssert.hasText(loginName, "用户名不能为空");
		// ZAssert.bigThanZero(authUserDao.selectCountByLoginName(authUser.getLoginName()),
		// "该用户名已经存在", "格式转化错误");
		Long count = authUserDao.selectCountByLoginName(loginName);
		if (count > 0) {
			ZAlert.Error("该用户名已经存在");
		}
		ZAssert.hasText(authUserDetail.getPassword(), "密码不能为空");

		String salt = MD5Util.genRandomSalt();
		String password = MD5Util.encrypt(authUserDetail.getPassword(), salt);
		authUser.setLoginName(loginName);
		authUser.setSalt(salt);// 加密盐值
		authUser.setPassword(password);// 加密密码
		authUser.setEnabled("1");// 用户状态 1可用 0 不可用
		authUser.setAccountExpired("0");// 账户是否过期 0 否 1 是
		authUser.setAccountLocked("0");// 账户是否所定 0 否 1 是
		authUser.setCredentialsExpired("0");// 密码是否过期 0 否 1 是
		authUser.setLoginName2(authUserDetail.getLoginName2());// 卡号
		authUserDao.insertAuthUser(authUser);
		// authUserDao.assignRoleToUser(id, roleId)

		AuthUser user = authUserDao.selectByLoginNameAndPas(loginName, password);
		String roleId = authUserDetail.getRoleId();// 角色id
		if (roleId != null && !roleId.isEmpty()) {
			authUserDao.assignRoleToUser(user.getId(), Integer.parseInt(roleId));
		}

		authUserDetail.setUserId(String.valueOf(user.getId()));
		return authUserDao.insertAuthUserDetail(authUserDetail);
	}

	public AuthUserDetail selectUserDetailByAuthUserId(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return authUserDao.selectUserDetailByAuthUserId(id);
	}

	public int updateUserDedailAllById(AuthUserDetail authUserDetail) throws BaseException {
		AuthUser authUser = new AuthUser();
		String loginName = authUserDetail.getLoginName();
		ZAssert.hasText(loginName, "用户名不能为空");
		Long count = authUserDao.selectCountByLoginName(authUser.getLoginName());
		if (count < 0) {
			ZAlert.Error("该用户名已经存在");
		}
		authUser.setId(Integer.parseInt(authUserDetail.getUserId()));
		authUser.setLoginName(loginName);
		authUser.setLoginName2(authUserDetail.getLoginName2());
		authUserDao.updateAuthUser(authUser);

		return authUserDao.updateAuthUserDetailByUserId(authUserDetail);
	}

	public int disableBatchAuthUser(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		for (Integer id : ids) {
			falg = authUserDao.setAuthAuthUserStatus(id, BizConst.ENABLED.DISABLE);
		}
		return falg;
	}

	public int enableBatchAuthUser(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		for (Integer id : ids) {
			falg = authUserDao.setAuthAuthUserStatus(id, BizConst.ENABLED.ENABLE);
		}
		return falg;
	}

	public int changePasswordSalt(AuthUser user) throws BaseException {
		String newPws = user.getPassword();
		ZAssert.hasText(newPws, "新密码不能为空");
		AuthUser auth = authUserDao.findByLoginName(user.getLoginName());
		if (auth == null) {
			ZAlert.Error("未找到用户信息或登陆超时！");
		}
		String salt = MD5Util.genRandomSalt();
		String password = MD5Util.encrypt(newPws, salt);
		user.setPassword(password);
		user.setSalt(salt);
		user.setId(auth.getId());
		return authUserDao.changePasswordSalt(user);
	}

	@Override
	public List<String> selectAuthUserJobNumber() throws BaseException {
		List<String> list = new ArrayList<String>();
		List<Map<String, Object>> listMap = authUserDao.selectAuthUserJobNumber();
		for (int i = 0; i < listMap.size(); i++) {
			list.add((String) listMap.get(i).get("jobnumber"));
		}
		return list;
	}

	@Override
	public void insertAuthUser(AuthUser authUser) throws BaseException {
		ZAssert.hasText(authUser.getJobnumber(), "工号不能为空");
		authUserDao.insertAuthUser(authUser);
	}

	@Override
	public int updateAuthUserDisableByJobNumber(String jobnumber) throws BaseException {

		return authUserDao.updateAuthUserDisableByJobNumber(jobnumber);
	}

	@Override
	public Integer selectIdByJobNumber(String jobnumber) throws BaseException {
		AuthUser authUser = authUserDao.selectIdByJobNumber(jobnumber);
		if (authUser == null) {
			ZAlert.Error("该工号对应的人员id不存在");
		}
		return authUser.getId();
	}

	@Override
	public List<AuthUserDetail> selectUserDetailByAuthUserName(String userName) throws BaseException {
		userName = "%" + userName + "%";
		return authUserDao.selectUserDetailByAuthUserName(userName);
	}

	@Override
	public int insertAuthUserDetail(AuthUserDetail authUserDetail) throws BaseException {
		return authUserDao.insertAuthUserDetail(authUserDetail);
	}

	@Override
	public int selectJobnumber(String jobnumber) throws BaseException {

		List<AuthUserDetail> list = authUserDao.selectJobnumber(jobnumber);
		int i = list.size();
		return i;
	}

	@Override
	public String selectclothesSize(String cType) throws BaseException {

		List<Map<String, Object>> list = authUserDao.selectclothesSize(cType);
		if (list.size() <= 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "");
			list.add(map);
		}
		return (String) list.get(0).get("code");
	}

	@Override
	public String selectShoesSize(String sType) throws BaseException {

		List<Map<String, Object>> list = authUserDao.selectShoesSize(sType);
		if (list.size() <= 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "");
			list.add(map);
		}
		return (String) list.get(0).get("code");
	}

	@Override
	public int updateUserInfo(String jobnumber, String department, String userName, String sex, String clothesType,
			String shoesType) throws BaseException {
		authUserDao.updateUserInfo(jobnumber, department, userName, sex, clothesType, shoesType);
		return 0;
	}

	@Override
	public int insertUserInfo(String jobnumber, String department, String userName, String sex, String clothesType,
			String shoesType) throws BaseException {
		// 新增auth_user人员信息，生成ID，工号
		int i = authUserDao.insertUserInfo(jobnumber);
		if (i > 0) {
			// 查询新生成的id
			List<Map<String, Object>> list = authUserDao.selectIdByJobnumber(jobnumber);
			String userID = list.get(0).get("id").toString();
			// 插入auth_user_detail信息
			authUserDao.insertAuthUserDetailInfo(userID, department, userName, sex, clothesType, shoesType);
		}
		return 0;
	}
}
