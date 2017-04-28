package com.sesxh.hoschange.biz.sys.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;

public interface AuthUserBpo {

	/**
	 * 根据登陆名查询salt
	 * 
	 * @param loginName
	 * @return
	 * @throws BaseException
	 */
	public String loadSaltForLoginName(String loginName) throws BaseException;

	/**
	 * 用户名 密码 身份证号查询用户信息
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws BaseException
	 */
	public AuthUser loadByLoginNameAndPas(String loginName, String password) throws BaseException;

	public AuthUser findByLoginName(String loginName) throws BaseException;

	public AuthUser findByLoginName2(String loginName2) throws BaseException;

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
	 * 加载用户数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadAuthRoleSet(Map<String, Object> params) throws BaseException;

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
	public int removeRoleFromUser(Integer id, List<Integer> roleIds) throws BaseException;

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
	 * 批量注销用户信息 11-25
	 * 
	 * @param ids
	 *            用户id
	 * @return
	 * @throws BaseException
	 */
	public int disableBatchAuthUser(Integer[] ids) throws BaseException;

	/**
	 * 批量启用用户信息 11-25
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
	 * 获取authorUser里的所有loginname2
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<String> selectAuthUserJobNumber() throws BaseException;

	/**
	 * 
	 * @param authUser
	 * @throws BaseException
	 */
	public void insertAuthUser(AuthUser authUser) throws BaseException;

	/**
	 * 根据jobnumber修改人员信息的状态为不可用
	 * 
	 * @param jobnumber
	 * @return
	 * @throws BaseException
	 */
	public int updateAuthUserDisableByJobNumber(String jobnumber) throws BaseException;

	/**
	 * 根据工号获取对应的id
	 * 
	 * @param jobnumber
	 * @return
	 * @throws BaseException
	 */
	public Integer selectIdByJobNumber(String jobnumber) throws BaseException;

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
	 * 添加用户详细信息
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int insertAuthUserDetail(AuthUserDetail authUserDetail) throws BaseException;

	/**
	 * 查询是否存在该工号
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int selectJobnumber(String jobnumber) throws BaseException;

	/**
	 * 查询字典表里的衣服尺码
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public String selectclothesSize(String cType) throws BaseException;

	/**
	 * 查询字典表里的鞋子尺码
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public String selectShoesSize(String sType) throws BaseException;

	/**
	 * 更新人员信息
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int updateUserInfo(String jobnumber, String department, String userName, String sex, String clothesType,
			String shoesType) throws BaseException;

	/**
	 * 新增人员信息
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int insertUserInfo(String jobnumber, String department, String userName, String sex, String clothesType,
			String shoesType) throws BaseException;
}
