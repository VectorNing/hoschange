package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosPower;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosPowerDao {
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
	 * 根据人员id和手术排班id来查询权限表内是否有该记录
	 * 
	 * @param jobNumber
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int selectPowerCount(String userid, String operschedulid) throws BaseException;

	/**
	 * 根据手术排班id查询所有的人员id
	 * 
	 * @param jobNumber
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectPowerUserid(String operschedulid) throws BaseException;

	/**
	 * 
	 * @param userid
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int deleteInvilidPower(String userid, String operschedulid) throws BaseException;

	/**
	 * 根据userid查询是否有权限
	 * 
	 * @param userid
	 * @param operschedulid
	 * @return
	 * @throws BaseException
	 */
	public int selectIfHavePower(String userid) throws BaseException;

	/**
	 * 
	 * @Title: HosPowerCount
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:Long
	 * @throws:
	 */
	public Long HosPowerCount(ParamMap paramMap) throws BaseException;

	/**
	 * 
	 * @Title: queryRecycleAll
	 * @author Ning
	 * @data:2017年3月30日
	 * @return:List<Map<String,Object>>
	 * @throws:
	 */
	public List<Map<String, Object>> queryHosPowerAll(ParamMap paramMap) throws BaseException;

}
