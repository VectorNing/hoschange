package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosSchedulingDao {

	/**
	 * 新增手术室排班
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public int insertHosScheduling(HosScheduling hosScheduling) throws BaseException;

	/**
	 * 删除手术室排班
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosSchedulingById(Integer id) throws BaseException;

	/**
	 * 修改手术室排班
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public int updateHosScheduling(HosScheduling hosScheduling) throws BaseException;

	/**
	 * 查询手术室排班数据
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectAllHosScheduling(ParamMap paramMap) throws BaseException;

	/**
	 * 查询手术室排班数量
	 * 
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosHosSchedulingCount(ParamMap paramMap) throws BaseException;

	/**
	 * 根据手术室编号 修改排班对应手术室编号
	 * 
	 * @param oldNumber
	 * @param newNumber
	 * @return
	 * @throws BaseException
	 */
	public int updateSchThNumberByNumber(String oldNumber, String newNumber) throws BaseException;

	/**
	 * 获取手术时间大于当前时间的部分
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectSchedulByOperationTime() throws BaseException;

	/**
	 * 根据手术编号删除手术排班
	 * 
	 * @param operationNumber
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosSchedulingByOperationNumber(String operationNumber) throws BaseException;

	/**
	 * 根据手术编号获取手术排班的id
	 * 
	 * @param operationNumber
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectIdByOperationNumber(String operationNumber) throws BaseException;

	/**
	 * 通过id获取手术排班信息
	 * 
	 * @Title: selectHosSchedulingById
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:HosScheduling
	 * @throws:
	 */
	public HosScheduling selectHosSchedulingById(String id) throws BaseException;

	/**
	 * 
	 * @Title: queryHosSchedulingByTime
	 * @author Ning
	 * @data:2017年3月31日
	 * @return:List<HosScheduling>
	 * @throws:
	 */
	public List<HosScheduling> queryHosSchedulingByTime(String begintime, String endtime) throws BaseException;

	/**
	 * 查询该手术排班中人员
	 * 
	 * @Title: selectUserNameByUserId
	 * @author Ning
	 * @data:2017年3月31日
	 * @return:List<Map<String,Object>>
	 * @throws:
	 */
	public List<Map<String, Object>> selectUserNameByUserId(String userId, String scheduleId) throws BaseException;

	/**
	 * 
	 * @Title: showUserFromSch
	 * @author Ning
	 * @data:2017年3月31日
	 * @return:List<Map<String,Object>>
	 * @throws:
	 */
	public List<Map<String, Object>> showUserFromSch(String scheduleId) throws BaseException;

	/**
	 * 
	 * @Title: showUserFromSchCount
	 * @author Ning
	 * @data:2017年3月31日
	 * @return:int
	 * @throws:
	 */
	public Long showUserFromSchCount(String scheduleId) throws BaseException;
}
