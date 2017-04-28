package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosTheaterService {
	
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
	public int deleteHosTheaterById(Integer id,String number) throws BaseException;

	/**
	 * 修改手术室基本信息
	 * @param hosTheater
	 * @return
	 * @throws BaseException
	 */
	public int updateHosTheater(HosTheater hosTheater) throws BaseException;

	/**
	 * 查询手术室基本信息数据
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectHosTheaterSet(Map<String,Object> params)throws BaseException ;
	
	/**
	 * 根据编号查询手术室信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosTheater selectTheaterById(Integer id) throws BaseException ;
	
	/**
	 * 根据手术室编号查询 条数
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectTheByNumber(String number,Integer id) throws BaseException;
	
//-------------------------------------领取------------------------------------------
	
	/**
	 * 根据 用户id 查询 用户详细信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthUserDetail loadUserDetailById(Integer id) throws BaseException ;
	
	/**
	 * 根据用户id 消毒柜编号
	 *   查询相对应 的 手术鞋的尺码所在地
	 * @param id 
	 *   number 柜子编号
	 *   size 尺码
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> receiveDeviceShoByUserIdM1(Integer id,String number,String size) throws BaseException ;
	
	/**
	 * 领取对应的小柜 手术鞋
	 *   存放记录
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int receiveShoesBySteNumberM1(Map<String,Object> params) throws BaseException ;
	
	/**
	 * 根据用户id,衣柜编号
	 *    查询相对应 的手术衣 尺码所在地
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> receiveDeviceOpeByUserIdM1(Integer id,String number) throws BaseException ;
	
	/**
	 * 领取对应的托盘 手术衣
	 *   存放记录
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int receiveOpeByWarNumberM1(Map<String,Object> params) throws BaseException ;
	
	/**
	 * 根据用户id，手术室编号
	 *   随机领取手术鞋
	 * @author xujialong
	 * @param userId   用户id
	 * @param theNumber  手术室编号
	 * @param mark  标志领取或者从新领取
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> randomReceiveShoeByUserIdTheNumber(Integer userId,String theNumber,Integer mark) throws BaseException ; 
	
	/**
	 * 根据用户id，手术室编号
	 *   随机领取手术鞋 模式1
	 * @param userId
	 * @param theNumber
	 * @return
	 * @throws BaseException
	 */
	//public Map<String,Object> randomReceiveShoeByUserIdTheNumberM1(Integer userId,String theNumber) throws BaseException ; 
	
	/**
	 * 根据用户id，手术室编号
	 *   随机领取手术衣  模式1
	 * @param userId
	 * @param theNumber
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> randomReceiveOpeByUserIdTheNumberM1(Integer userId,String theNumber) throws BaseException ; 
	
	/**
	 * 清空手术室下 所有消毒柜
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int emptyTouchAllSteContainer(String number) throws BaseException ;
	
	/**
	 * 清空手术室下 所有衣柜
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int emptyTouchAllWarContainer(String number) throws BaseException ;
	
	/**
	 * 根据用户id，衣柜编号
	 *   随机领取手术衣
	 * @author xujialong
	 * @param warNumber 手术衣发放设备编号
	 * @param userId  用户id
	 * @param mark 标志是领取或者重新领取
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> randomReceiveOpeByUserIdWarNumber(Integer userId,String warNumber,Integer mark) throws BaseException ; 
	
	/**
	 * 加载手术室数据字典
	 * @return
	 * @throws BaseException
	 */
	public List<ClassConvertDict> loadTheNumberConvertDict() throws BaseException ;
	
	//########################## 分配更衣室用户  #####################################//
	
	/**
	 * 加载属于更衣室的用户列表
	 *   分配的，未分配的
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadTheaterUserByTheaterId(Map<String,Object> params) throws BaseException;
	
	/**
	 * 分配更衣室用户
	 * @param theaterId
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int assignUserToTheater(Integer theaterId, List<Integer> userIds) throws BaseException;
	
	/**
	 * 移除更衣室用户
	 * @param theaterId
	 * @param userIds
	 * @return
	 * @throws BaseException
	 */
	public int removeTheaterFromUser(Integer theaterId, List<Integer> userIds) throws BaseException;
	
	/**
	 * 查询用户是否有更衣室的权限
	 *   设备上刷卡分配时校验
	 * @param theNumber  更衣室编号
	 * @param cardNum  卡号
	 * @return  true 有  false 没有
	 * @throws BaseException
	 */
	public boolean selectTheaterUserByNumberAndCardNum(String theNumber,String cardNum) throws BaseException;
	/**
	 * 根据用户id查看拥有权限的更衣室信息
	 * @author xujialong
	 * @param UserId 用户id
	 * @return 更衣室信息
	 * @throws BaseException
	 */
	public List<HosTheater> selectTheaterByUserId(Integer UserId) throws BaseException;
	
	
}
