package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.common.data.ParamMap;

public interface AuthUserDao {

	/**
	 * 根据登陆名查询salt
	 * 
	 * @param loginName
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectSaltByLoginName(String loginName) throws BaseException;

	/**
	 * 用户名 密码 身份证号查询用户信息
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws BaseException
	 */
	public AuthUser selectByLoginNameAndPas(String loginName, String password) throws BaseException;

	public AuthUser findByLoginName(String loginName) throws BaseException;

	public AuthUser findByLoginName2(String loginName2) throws BaseException;

	public AuthUser findBySfzh(String sfzh) throws BaseException;

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
	 * 根据id查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthUser selectByPrimaryKey(Integer id) throws BaseException;

	/**
	 * 根据用户名统计行数(判断重复)
	 * 
	 * @param loginName
	 * @return
	 * @throws BaseException
	 */
	public Long selectCountByLoginName(String loginName) throws BaseException;

	/**
	 * 添加用户信息
	 * 
	 * @param authUser
	 * @return
	 * @throws BaseException
	 */
	public int insertAuthUser(AuthUser authUser) throws BaseException;

	public int updateAuthUser(AuthUser authUser) throws BaseException;

	public int setAuthAuthUserStatus(Integer id, String enabled) throws BaseException;

	/**
	 * 分页加载用户数据列表
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectAuthUserList(ParamMap paramMap) throws BaseException;

	/**
	 * 统计用户数据信息
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectAuthUserCount(ParamMap paramMap) throws BaseException;

	/**
	 * 分配角色信息
	 * 
	 * @param id
	 * @param roleIds
	 * @return
	 * @throws BaseException
	 */
	public int assignRoleToUser(Integer id, Integer roleId) throws BaseException;

	/**
	 * 移除角色信息
	 * 
	 * @param id
	 * @param roleIds
	 * @return
	 * @throws BaseException
	 */
	public int removeRoleFromUser(Integer id, Integer roleId) throws BaseException;

	// /**
	// * 修改用户信息
	// *
	// * @param authUser
	// * @return
	// * @throws BaseException
	// */
	// public int updateAuthUser(AuthUser authUser) throws BaseException;
	/**
	 * 根据用户加载详情
	 * 
	 * @Title: selectAuthUserByUserId
	 * @author Ning
	 * @data:2017年1月16日
	 * @return:AuthUser
	 * @throws:
	 */
	public AuthUser selectAuthUserByUserId(Integer userId) throws BaseException;

	/**
	 * 添加用户信息
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int insertAuthUserDetail(AuthUserDetail authUserDetail) throws BaseException;

	/**
	 * 根据用户id 加载用户登陆及详情信息 11-25
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthUserDetail selectUserDetailByAuthUserId(Integer id) throws BaseException;

	/**
	 * 根据用户id 更新用户详情信息
	 * 
	 * @param authUserDetail
	 * @return
	 * @throws BaseException
	 */
	public int updateAuthUserDetailByUserId(AuthUserDetail authUserDetail) throws BaseException;

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
	public List<Map<String, Object>> selectAuthUserJobNumber() throws BaseException;

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
	public AuthUser selectIdByJobNumber(String jobnumber) throws BaseException;

	/**
	 * 根据用户名模糊查询信息
	 * 
	 * @Title: selectUserDetailByAuthUserName
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public List<AuthUserDetail> selectUserDetailByAuthUserName(String userName) throws BaseException;

	/**
	 * 查询是否存在该工号
	 * 
	 * @Title: selectUserDetailByAuthUserName
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public List<AuthUserDetail> selectJobnumber(String jobnumber) throws BaseException;

	/**
	 * 查询字典表里的衣服尺码
	 * 
	 * @Title: selectUserDetailByAuthUserName
	 * @author xn
	 * @data:2017年4月20日19:19:13
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public List<Map<String, Object>> selectclothesSize(String cType) throws BaseException;

	/**
	 * 查询字典表里的鞋子尺码
	 * 
	 * @Title: selectUserDetailByAuthUserName
	 * @author xn
	 * @data:2017年4月20日19:19:11
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public List<Map<String, Object>> selectShoesSize(String cType) throws BaseException;

	/**
	 * 更新数据
	 * 
	 * @Title: updateUserInfo
	 * @author xn
	 * @data:2017年4月20日19:19:11
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public int updateUserInfo(String jobnumber, String department, String userName, String sex, String clothesType,
			String shoesType) throws BaseException;

	/**
	 * 新增人员
	 * 
	 * @Title: insertUserInfo
	 * @author xn
	 * @data:2017年4月21日14:45:16
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public int insertUserInfo(String jobnumber) throws BaseException;

	/**
	 * 根据jobnumber查询id
	 * 
	 * @Title: selectIdByJobnumber
	 * @author xn
	 * @data:2017年4月21日15:04:37
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public List<Map<String, Object>> selectIdByJobnumber(String jobnumber) throws BaseException;

	/**
	 * 插入auth_user_detail信息
	 * 
	 * @Title: insertUserInfo
	 * @author xn
	 * @data:2017年4月21日15:10:57
	 * @return:List<AuthUserDetail>
	 * @throws:
	 */
	public int insertAuthUserDetailInfo(String userId, String department, String userName, String sex,
			String clothesType, String shoesType) throws BaseException;
}
