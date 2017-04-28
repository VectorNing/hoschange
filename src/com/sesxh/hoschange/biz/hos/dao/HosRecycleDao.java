package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.entity.HosRecycleContainer;
import com.sesxh.hoschange.common.data.ParamMap;

/**
 * 
 * @author Ning
 *
 */
public interface HosRecycleDao {
	/**
	 * 根据编号查询回收桶
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public HosRecycle selectRecycleByNumber(String number,Integer id) throws BaseException;
	/**
	 * 增加回收桶
	 * @author Ning
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	public int insertRecycle(HosRecycle hosRecycle) throws BaseException;
	/**
	 * 查询所有的回收桶信息
	 * @author Ning
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> queryRecycleAll(ParamMap paramMap) throws BaseException;
	/**
	 * 查询回收桶数量
	 * @author Ning
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosRecycleCount(ParamMap paramMap) throws BaseException; 
	/**通过id查询回收桶信息
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosRecycle queryHosrecycleById(Integer id) throws BaseException;
	/**
	 * 修改回收桶信息
	 * @author Ning
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecycle(HosRecycle hosRecycle) throws BaseException;
	/**
	 * 根据回收桶编号查询回收桶信息
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public HosRecycle selectRecycleFromRecycleByNumber(String number) throws BaseException;
	/**
	 * 根据id删除回收桶
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosRecycle(Integer id) throws BaseException;
	/**
	 * 根据回收桶id删除回收桶详细信息
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosRecycleContainerByRecycleId(Integer id) throws BaseException;
	/**
	 * 根据回收桶编号更新回收数量
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecycleCountByNumber(String number,Integer recycle) throws BaseException;
	/**
	 * 添加回收衣物详细信息
	 * @author Ning
	 * @param hosRecycleContainer
	 * @return
	 * @throws BaseException
	 */
	public int insertToRecycleContainer(HosRecycleContainer hosRecycleContainer) throws BaseException;
	/**
	 * 根据回收桶编号清空回收桶
	 * @author Ning
	 * @param recycleNumber
	 * @return
	 * @throws BaseException
	 */
	public int emptyRecycle(String recycleNumber) throws BaseException;
	/**
	 * 根据回收桶id清空回收桶详细信息
	 * @author Ning
	 * @param recycleId
	 * @return
	 * @throws BaseException
	 */
	public int emptyRecycle(Integer recycleId) throws BaseException;
	/**
	 * 查询衣物管理员管辖范围内回收桶的数量(分页使用)
	 * @author Ning
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosRecycleCountMonitoredByUserId (ParamMap paramMap) throws BaseException;
	/**
	 * 查询衣物管理员管辖范围内回收桶信息(分页使用)
	 * @author Ning
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectHosRecycleMonitoredByUserId (ParamMap paramMap) throws BaseException;
	/**
	 * 超级管理员，管理员监控
	* @Title: AllHosRecycleCountMonitored
	* @author Ning 
	* @data:2017年1月16日
	* @return:Long
	* @throws:
	 */
	public Long AllHosRecycleCountMonitored (ParamMap paramMap) throws BaseException;
	public List<Map<String, Object>> selectAllHosRecycleMonitored (ParamMap paramMap) throws BaseException;
}
