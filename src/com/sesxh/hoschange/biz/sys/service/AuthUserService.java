package com.sesxh.hoschange.biz.sys.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;

public interface AuthUserService {

	/**
	 * 根据登陆名查询salt
	 * 
	 * @param loginName
	 * @return
	 * @throws BaseException
	 */
	public String findSaltForLoginName(String loginName) throws BaseException;

	/**
	 * 用户名 密码 查询用户信息
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws BaseException
	 */
	public AuthUser findByLoginNameAndPas(String loginName, String password) throws BaseException;

	/**
	 * 查重用户名
	 * 
	 * @param loginName
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public Long selectUserByLoginName(String loginName, Integer id) throws BaseException;

	public Long selectUserByLoginName2(String loginName2, Integer id) throws BaseException;

	/**
	 * 
	 * @param loginName
	 * @return
	 * @throws BaseException
	 */
	public AuthUser findByLoginName(String loginName) throws BaseException;

	/**
	 * 
	 * @param loginName2
	 * @return
	 * @throws BaseException
	 */
	public AuthUser findByLoginName2(String loginName2) throws BaseException;

	/**
	 * 
	 * @param sfzh
	 * @return
	 * @throws BaseException
	 */
	public AuthUser findBySfzh(String sfzh) throws BaseException;

	/**
	 * 加载SessionUser信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public SessionUser loadUserInfor(Integer id) throws BaseException;

	/**
	 * 根据编号查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthUser loadUserInById(Integer id) throws BaseException;

	/**
	 * 加载用户数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryAuthRoleSet(Map<String, Object> params) throws BaseException;

	/**
	 * 分配用户角色
	 * 
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int assignRoleToUser(Integer id, List<Integer> roleIds) throws BaseException;

	/**
	 * 移除用户角色
	 * 
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int removeRoleToUser(Integer id, List<Integer> roleIds) throws BaseException;

	/**
	 * 添加用户及 详情信息 11-25
	 * 
	 * @param authUser
	 * @return
	 * @throws BaseException
	 */
	public int createAuthUserAll(AuthUserDetail authUserDetail) throws BaseException;

	/**
	 * 根据用户id 加载用户登陆及详情信息 11-25
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthUserDetail selectUserDetailByAuthUserId(Integer id) throws BaseException;

	/**
	 * 根据用户id 修改用户(登录+详情)
	 * 
	 * @param AuthUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int updateUserDedailAllById(AuthUserDetail AuthUserDetail) throws BaseException;

	/**
	 * 批量注销用户信息
	 * 
	 * @param ids
	 *            用户id
	 * @return
	 * @throws BaseException
	 */
	public int disableBatchAuthUser(Integer[] ids) throws BaseException;

	/**
	 * 批量启用用户信息
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int enableBatchAuthUser(Integer[] ids) throws BaseException;

	/**
	 * 修改密码，盐值
	 * 
	 * @param user
	 * @return
	 * @throws BaseException
	 */
	public int changePasswordSalt(AuthUser user) throws BaseException;

	/**
	 * 
	 * @Title: selectUserDetailByAuthUserName
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public List<AuthUserDetail> selectUserDetailByAuthUserName(String userName) throws BaseException;

	/**
	 * 上传Excel
	 * 
	 * @param authUser
	 * @author xn
	 * @return
	 * @throws BaseException
	 */
	public String uploadUserInfoExcel(InputStream file, AuthUserDetail AuthUserDetail) throws BaseException;
}
