package com.sesxh.hoschange.biz.sys.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.AuthUserBpo;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.biz.sys.util.ReadExcel;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class AuthUserServiceImpl extends SesframeBaseService implements AuthUserService {

	@Autowired
	private AuthUserBpo authUserBpo;

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public String findSaltForLoginName(String loginName) throws BaseException {
		return authUserBpo.loadSaltForLoginName(loginName);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthUser findByLoginNameAndPas(String loginName, String password) throws BaseException {
		return authUserBpo.loadByLoginNameAndPas(loginName, password);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthUser findByLoginName(String loginName) throws BaseException {
		return authUserBpo.findByLoginName(loginName);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthUser findByLoginName2(String loginName2) throws BaseException {
		return authUserBpo.findByLoginName2(loginName2);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthUser findBySfzh(String sfzh) throws BaseException {
		return authUserBpo.findBySfzh(sfzh);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Long selectUserByLoginName(String loginName, Integer id) throws BaseException {
		return authUserBpo.selectUserByLoginName(loginName, id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Long selectUserByLoginName2(String loginName2, Integer id) throws BaseException {
		return authUserBpo.selectUserByLoginName2(loginName2, id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public SessionUser loadUserInfor(Integer id) throws BaseException {
		return authUserBpo.loadUserInfor(id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthUser loadUserInById(Integer id) throws BaseException {
		return authUserBpo.loadUserInById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet queryAuthRoleSet(Map<String, Object> params) throws BaseException {
		return authUserBpo.loadAuthRoleSet(params);
	}

	@Transactional(rollbackFor = Exception.class)
	public int assignRoleToUser(Integer id, List<Integer> roleIds) throws BaseException {
		return authUserBpo.assignRoleToUser(id, roleIds);
	}

	@Transactional(rollbackFor = Exception.class)
	public int removeRoleToUser(Integer id, List<Integer> roleIds) throws BaseException {
		return authUserBpo.removeRoleFromUser(id, roleIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createAuthUserAll(AuthUserDetail authUserDetail) throws BaseException {
		return authUserBpo.createAuthUserAll(authUserDetail);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AuthUserDetail selectUserDetailByAuthUserId(Integer id) throws BaseException {
		return authUserBpo.selectUserDetailByAuthUserId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateUserDedailAllById(AuthUserDetail AuthUserDetail) throws BaseException {
		return authUserBpo.updateUserDedailAllById(AuthUserDetail);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int disableBatchAuthUser(Integer[] ids) throws BaseException {
		return authUserBpo.disableBatchAuthUser(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int enableBatchAuthUser(Integer[] ids) throws BaseException {
		return authUserBpo.enableBatchAuthUser(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int changePasswordSalt(AuthUser user) throws BaseException {
		return authUserBpo.changePasswordSalt(user);
	}

	@Override
	@Transactional
	public List<AuthUserDetail> selectUserDetailByAuthUserName(String userName) throws BaseException {
		return authUserBpo.selectUserDetailByAuthUserName(userName);
	}

	@Override
	@Transactional
	public String uploadUserInfoExcel(InputStream file, AuthUserDetail AuthUserDetail) throws BaseException {
		ReadExcel xlsMain = new ReadExcel();
		try {
			List<AuthUserDetail> list = xlsMain.readXls(file);
			for (int i = 0; i < list.size(); i++) {
				AuthUserDetail st = list.get(i);
				String jobnumber = st.getJobnumber().split("\\.")[0];
				// 判断工号是否为空，工号为空该条数据不做处理
				if (jobnumber == null || "".equals(jobnumber.trim())) {
					return "第" + (i + 1) + "行工号为空,请检查！";
				}
				String department = st.getDepartment();
				String userName = st.getUserName();
				// 判断性别 1男 2女
				String xb = st.getSex();
				String sex = "";
				if ("男".equals(xb)) {
					sex = "1";
				}
				if ("女".equals(xb)) {
					sex = "2";
				}

				// 根据衣服型号去字典表查询对应code
				String cType = st.getClothesType();
				String clothesType = authUserBpo.selectclothesSize(cType);
				if (clothesType == null || "".equals(clothesType)) {
					return "第" + (i + 1) + "行衣裤型号格式错误,请检查！";
				}
				// 根据鞋子型号去字典表查询对应code，格式为 36男，38女
				String shoesNum = st.getShoesType().split("\\.")[0];
				String sType = shoesNum + xb;
				String shoesType = authUserBpo.selectShoesSize(sType);
				if (shoesType == null || "".equals(shoesType)) {
					return "第" + (i + 1) + "行鞋子型号格式错误,请检查！";
				}

				// 查询工号是否重复
				int flag = authUserBpo.selectJobnumber(jobnumber);
				// 根据工号查询
				if (flag > 0) {
					// 更新
					authUserBpo.updateUserInfo(jobnumber, department, userName, sex, clothesType, shoesType);
				} else {
					authUserBpo.insertUserInfo(jobnumber, department, userName, sex, clothesType, shoesType);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传成功";
	}
}
