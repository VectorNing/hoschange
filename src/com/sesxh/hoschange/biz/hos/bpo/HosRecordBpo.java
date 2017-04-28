package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosRecordBpo {

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
	 * 根据用户id,设备编号 查询最后一次刷卡记录
	 * 
	 * @param userId
	 * @param deviceNumber
	 *            设备编号
	 * @return
	 * @throws BaseException
	 */
	public HosRecord selectHosRecordLastByUserId(Integer userId, String deviceNumber, String deviceType)
			throws BaseException;

	public HosRecord selectHosRecordLastByTheNumber(Integer userId, String theaterNumber, String deviceType)
			throws BaseException;

	/**
	 * 根据用户id 查询最后n次是否正确回收 次数 用于确定是否进入灰黑名单 模式1
	 * 
	 * @param userId
	 *            用户id
	 * @return true 在灰名单 false 不在灰名单 在黑名单直接提示并返回
	 * @throws BaseException
	 */
	public String selectHosRecordCountByUserIdM1(Integer userId) throws BaseException;

	/**
	 * 根据用户id 查询最后n次是否正确回收 次数 用于确定是否进入灰黑名单 模式2
	 * 
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public String selectHosRecordCountByUserIdM2(Integer userId) throws BaseException;

	/**
	 * 判断规定时间段内是否重复刷卡
	 * 
	 * @param userId
	 *            用户id
	 * @param deviceNumber
	 *            设备编码
	 * @return
	 * @throws BaseException
	 */
	public boolean judgeHosRecordForBrushCardTimes(Integer userId, String deviceNumber, String deviceType)
			throws BaseException;

	/**
	 * 回收并且 签退
	 * 
	 * @param recordId
	 * @param cardNum
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public int updateSignOutRecovery(Integer recordId, String cardNum, String state) throws BaseException;

	/**
	 * 加载刷卡记录数据列表
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectHosRecordSet(Map<String, Object> params) throws BaseException;

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
	 * 根据用户id查询出签到表中衣物的领取次数
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
}
