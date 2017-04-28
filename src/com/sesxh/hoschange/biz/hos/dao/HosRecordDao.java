package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosRecordDao {

	/**
	 * 添加刷卡记录信息
	 * 
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public int insertHosRecordService(HosRecord hosRecord) throws BaseException;

	/**
	 * 修改刷卡记录信息
	 * 
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecordService(HosRecord hosRecord) throws BaseException;

	/**
	 * 查询规定时间段内是否重复刷卡
	 * 
	 * @param brushCardTime
	 *            不能重复刷卡规定时间段
	 * @param userId
	 *            用户id
	 * @param deviceType
	 *            设备类型
	 * @param deviceNumber
	 *            设备编码
	 * @return
	 * @throws BaseException
	 */
	public Long selectHosRecordForBrushCardTimes(String brushCardTime, Integer userId, String deviceType,
			String deviceNumber) throws BaseException;

	/**
	 * 根据用户id 查询最后n次是否正确回收 次数 用于确定是否进入灰黑名单 模式1
	 * 
	 * @param userId
	 *            用户id
	 * @param isCallback
	 *            是否回收
	 * @param latelyNum
	 *            最近多少条记录
	 * @return
	 * @throws BaseException
	 */
	public Long selectHosRecordCountByUserIdM1(Integer userId, String isCallback, Integer latelyNum)
			throws BaseException;

	/**
	 * 根据用户id 查询最后n次是否正确回收 次数 用于确定是否进入灰黑名单 模式1
	 * 
	 * @param userId
	 *            用户id
	 * @param isCallback
	 *            未回收
	 * @param rosterTime
	 *            时间段(天)
	 * @return
	 * @throws BaseException
	 */
	public Long selectHosRecordCountByUserIdM2(Integer userId, String isCallback, String rosterTime)
			throws BaseException;

	/**
	 * 根据用户id,手术区 查询最后一次刷卡记录
	 * 
	 * @param userId
	 * @param
	 * @return
	 * @throws BaseException
	 */
	public HosRecord selectHosRecordLastByUserId(Integer userId, String theaterNumber, String type)
			throws BaseException;

	/**
	 * 加载刷卡记录数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectHosRecordSet(ParamMap pm) throws BaseException;

	public Long selectHosRecordCount(ParamMap pm) throws BaseException;

	/**
	 * 根据id查询刷卡记录
	 * 
	 * @return
	 * @throws BaseException
	 */
	public HosRecord selectHosRecordById(Integer id) throws BaseException;

	/**
	 * 根据用户id和回收状态查询记录表
	 * 
	 * @author Ning
	 * @param userId
	 * @param isCallBack
	 * @return
	 * @throws BaseException
	 */
	public List<HosRecord> selectHosRecordByUserIdAndIsCallBack(Integer userId, String isCallBack) throws BaseException;

	/**
	 * 根据记录id修改回收状态
	 * 
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecordIsCallbackById(Integer id, String isCallback) throws BaseException;

	/**
	 * 选择超时的未签退的记录
	 * 
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public List<HosRecord> selectOvertimeNoSignOut(String time) throws BaseException;

	/**
	 * wzg
	 * 
	 * 根据用户id查询领取衣物次数
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosRecord selectHosRecordLqcsById(Integer id) throws BaseException;

	/**
	 * wzg
	 * 
	 * 领取一次衣物，相应的lqcs减少一次
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecordLqcs(Integer id) throws BaseException;

	/**
	 * wzg
	 * 
	 * 根据userid查询对应角色表中的lqcs
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public AuthRole selectAuthRoleLqcsById(Integer id) throws BaseException;
}
