package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosTheaterDao {
	
	/**
	 * 新增手术室基本信息
	 * @param hosTheater
	 * @return
	 * @throws BaseException
	 */
	public int insertHosTheater(HosTheater hosTheater) throws BaseException;

	/**
	 * 删除手术室基本信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosTheaterById(Integer id) throws BaseException;

	/**
	 * 修改手术室基本信息
	 * @param hosTheater
	 * @return
	 * @throws BaseException
	 */
	public int updateHosTheater(HosTheater hosTheater) throws BaseException;

	/**
	 * 查询手术室基本信息数据
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAllHosTheater(ParamMap paramMap) throws BaseException;

	/**
	 * 查询手术室基本信息数据数量
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosTheaterCount(ParamMap paramMap) throws BaseException;
	
	/**
	 * 根据手术室编号查询 条数
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectTheByNumber(String number,Integer id) throws BaseException;
	
	/**
	 * 根据id查询手术室信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosTheater selectTheaterById(Integer id) throws BaseException ;
	
	/**
	 * 根据编号查询更衣室信息
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public HosTheater selectTheaterByNumber(String number) throws BaseException ;
	
	/**
	 * 查询用户详情信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthUserDetail loadUserDetailById(Integer id) throws BaseException ;
	
	/**
	 * 根据 尺码，手术衣柜编号
	 *   随机领取手术衣
	 * @param size
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectOperationFromContainerBySize(Integer size,String number) throws BaseException ;
	
	/**
	 * 根据 尺码，消毒柜编号
	 *   随机领取手术鞋
	 * @param size
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectShoesFromContainerBySize(Integer size,String number) throws BaseException ;
	
	/**
	 * 根据 尺码，手术室编号
	 *   随机领取手术鞋
	 * @param size
	 * @param theNumber
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectShoesFromConBySizeAndTheNumber(Integer size,String theNumber) throws BaseException ; 
	
	/**
	 * 根据 尺码，手术室编号
	 *   随机领取手术衣
	 * @param size
	 * @param theNumber
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectOpeFromConBySizeAndTheNumber(Integer size,String theNumber) throws BaseException ; 
	
	
	/**
	 * 加载手术室数据字典
	 * @return
	 * @throws BaseException
	 */
	public List<ClassConvertDict> loadTheNumberConvertDict() throws BaseException ;
	
	
	/**
	 * 加载属于更衣室的用户列表
	 *   
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectTheaterUserByTheaterIdList(ParamMap paramMap) throws BaseException;
	public Long selectTheaterUserByTheaterIdCount(ParamMap paramMap) throws BaseException;

	/**
	 * 加载不属于更衣室的用户列表
	 * @param params
	 * @param id 角色id
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectNotTheaterUserByTheaterIdList(ParamMap paramMap) throws BaseException;
	public Long selectNotTheaterUserByTheaterIdCount(ParamMap paramMap) throws BaseException;
	
	/**
	 * 分配更衣室用户
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int assignUserToTheater(Integer theaterId, Integer userId) throws BaseException;
	
	/**
	 * 移除更衣室用户
	 * @param id
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int removeTheaterFromUser(Integer theaterId, Integer userId) throws BaseException;

	/**
	 * 根据用户id 更衣室id 查询是否属于该手术室
	 * @param theaterId
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public Long selectTheaterUserByAllId(Integer theaterId, Integer userId) throws BaseException ;
	/**
	 * 根据卡号 手术室编号 查询是否属于该手术室
	 * @param loginName2
	 * @param theNumber
	 * @return
	 * @throws BaseException
	 */
	public Long selectTheaterUserByNumberAndLoginName2(String loginName2,String theNumber) throws BaseException ;
	/**
	 * 根据用户id查看拥有权限的更衣室信息
	 * @author xujialong
	 * @param UserId 用户id
	 * @return
	 * @throws BaseException
	 */
	public List<HosTheater> selectTheaterByUserId(Integer UserId) throws BaseException;
	/**
	 * @author xujialong
	 * 查询所有更衣室信息
	 * @return
	 * @throws BaseException
	 */
	public List<HosTheater> selectTheaterAll()throws BaseException;
}
