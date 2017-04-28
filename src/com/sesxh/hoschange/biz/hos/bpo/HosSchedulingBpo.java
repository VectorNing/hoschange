package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosSchedulingBpo {

	/**
	 * 新增手术室排班
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public int insertHosOperation(HosScheduling hosScheduling) throws BaseException;

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
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosSchedulingSet(Map<String, Object> params) throws BaseException;

	/**
	 * 获取手术时间大于当前时间的部分
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<String> selectSchedulByOperationTime() throws BaseException;

	/**
	 * 根据手术编号id删除手术排班记录
	 * 
	 * @param operationNumber
	 * @return
	 * @throws BaseException
	 */
	int deleteHosSchedulingByOperationNumber(String operationNumber) throws BaseException;

	/**
	 * 根据手术编号获取手术排班的id
	 * 
	 * @param operationNumber
	 * @return
	 * @throws BaseException
	 */
	public String selectIdByOperationNumber(String operationNumber) throws BaseException;

	/**
	 * 根据时间区间查询手术排班信息
	 * 
	 * @Title: queryHosSchedulingByTime
	 * @author Ning
	 * @data:2017年3月31日
	 * @return:Object
	 * @throws:
	 */
	public List<HosScheduling> queryHosSchedulingByTime(String begintime, String endtime) throws BaseException;

	/**
	 * 
	 * @Title: addUserToSchedul
	 * @author Ning
	 * @data:2017年3月31日
	 * @return:Object
	 * @throws:
	 */
	public Object addUserToSchedul(String scheduleId, String[] ids) throws BaseException;

	public DataSet showUserFromSchedul(String scheduleId) throws BaseException;
}
