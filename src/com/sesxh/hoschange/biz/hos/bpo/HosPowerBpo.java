package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosPower;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosPowerBpo {
	/**
	 * 删除超过时效的权限
	 * 
	 * @throws BaseException
	 */

	public int deleteInvilidPower(String time) throws BaseException;

	/**
	 * 添加权限
	 * 
	 * @param hosPower
	 * @throws BaseException
	 */
	public int insertPower(HosPower hosPower) throws BaseException;

	/**
	 * 修改权限
	 * 
	 * @param hosPower
	 * @return
	 * @throws BaseException
	 */
	public int updatePower(HosPower hosPower) throws BaseException;

	/**
	 * 根据人员工号和手术排班id来查询权限表内是否有该记录
	 * 
	 * @param jobNumber
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int selectPowerCount(String jobNumber, String operschedulid) throws BaseException;

	/**
	 * 根据手术排班id查询所有的人员工号
	 * 
	 * @param jobNumber
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public List<String> selectPowerJobNumber(String operschedulid) throws BaseException;

	/**
	 * 
	 * @param jobNumber
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int deleteInvilidPower(String jobNumber, String operschedulid) throws BaseException;

	/**
	 * 
	 * @Title: queryHosPowerSet
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:DataSet
	 * @throws:
	 */
	public DataSet queryHosPowerSet(Map<String, Object> params) throws BaseException;

	/**
	 * 为跟台人员添加权限
	 * 
	 * @Title: addFollower
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:int
	 * @throws:
	 */
	public String addFollower(String userId, String schedulid) throws BaseException;

	/**
	 * 根据userid是否有权限
	 * 
	 * @param userid
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int selectIfHavePower(String userid) throws BaseException;
	/**
	 * 
	* @Title: addTemporaries
	* @author Ning 
	* @data:2017年4月1日
	* @return:String
	* @throws:
	 */
	public String addTemporaries(String userId,String endtime,String begintime) throws BaseException;
}
