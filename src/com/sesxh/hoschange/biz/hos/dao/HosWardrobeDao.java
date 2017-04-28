package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosWardrobeDao {
	
	/**
	 * 新增手术服衣柜
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	public int insertHosWardrobe(HosWardrobe hosWardrobe) throws BaseException;

	/**
	 * 删除手术服衣柜
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosWardrobeById(Integer id) throws BaseException;

	/**
	 * 修改手术服衣柜
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	public int updateHosWardrobe(HosWardrobe hosWardrobe) throws BaseException;

	/**
	 * 查询手术服衣柜数据
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAllHosWardrobe(ParamMap paramMap) throws BaseException;
	/**
	 * 
	* @Title: selectHosWardrobeById
	* @author Ning 
	* @data:2017年1月19日
	* @return:HosWardrobe
	* @throws:
	 */
	public HosWardrobe selectHosWardrobeByNumber(String number) throws BaseException ;

	/**
	 * 查询手术服衣柜数据数量
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosWardrobeCount(ParamMap paramMap) throws BaseException;
	
	/**
	 * 根据id查询手术服衣柜信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosWardrobe selectHosWardrobeById(Integer id) throws BaseException ;
	
	/**
	 * 根据衣柜编号 删除 模式一 衣柜手术衣
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosOpeWardrobeByWarNumber(String number) throws BaseException ;
	
	/**
	 * 根据衣柜编号 删除 模式二 衣柜手术衣
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosOpeWarByWarNumber(String number) throws BaseException ;
	
	/**
	 * 根据衣柜编码 
	 *   修改模式二 对应 新 编号
	 * @param oldNumber
	 * @param newNumber
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public int updateOpeWarNumberByWarNumber(String oldNumber,String newNumber)throws BaseException ;
	
	/**
	 * 根据手术室编号 修改 衣柜手术室编号
	 * @param oldNumber
	 * @param newNumber
	 * @return
	 * @throws BaseException
	 */
	public int updateWarTheaterNumberByNumber(String oldNumber,String newNumber) throws BaseException ;
	
	/**
	 * 根据衣柜编号 查询衣柜内手术衣
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectOperationFromWardBynumber(String number) throws BaseException ;
	
	/**
	 * 根据手术室编号 加载 衣柜
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectWardListByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 根据手术室编号 加载 衣柜
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public Long selectWardCountByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 根据编号 加载不在手术室 的 衣柜
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectNotWardListByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 根据编号 加载不在手术室 的 衣柜
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public Long selectNotWardCountByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 分配手术衣柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int assignTheaterToWar(HosWardrobe hosWardrobe) throws BaseException ;
	
	/**
	 * 移除手术衣柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int removeWarFromTheater(HosWardrobe hosWardrobe) throws BaseException ;
	
	/**
	 * 根据手术室编号 更新状态 手术室编号
	 *     用于删除手术室
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	public int updateTheNumberStateByThenumber(HosWardrobe hosWardrobe) throws BaseException ;
	
	/**
	 * 根据衣柜编号查询 条数
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectWarByNumber(String number,Integer id) throws BaseException;
	
	/**
	 * 根据手术室编号 查询衣柜信息
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<HosWardrobe> loadWardrobeByThNumber(String number) throws BaseException ;
	
	/**
	 * 根据 衣柜编号查询手术服衣柜信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public List<HosWardrobe> loadWardrobeByWarNumber(String warNumber) throws BaseException ;
	
	
	
	
	/**
	 * 根据衣柜编号查询 衣柜信息
	 *   是否暂停服务，更衣室编号
	 * 
	 * @param warNumber
	 * @return
	 * @throws BaseException
	 */
	public HosWardrobe selectHosWardrobeEnabledByNumber(String warNumber)throws BaseException;
	
	/**
	 * 暂停或者恢复 服务使用
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	public int updateHosWardrobeEnabledByNumber(HosWardrobe hosWardrobe) throws BaseException;
	
	/**
	 * 衣物管理员 加载自己所属更衣室范围的手术衣信息
	 * @author xujialong
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectHosWardrobeMonitoredByUserId(ParamMap paramMap) throws BaseException;
	public Long HosWardrobeCountMonitoredByUserId(ParamMap paramMap) throws BaseException;
	/**
	 * 超级管理员 管理员加载所有手术衣信息
	* @Title: selectAllHosWardrobeMonitored
	* @author Ning 
	* @data:2017年1月16日
	* @return:List<Map<String,Object>>
	* @throws:
	 */
	public List<Map<String,Object>> selectAllHosWardrobeMonitored(ParamMap paramMap) throws BaseException;
	public Long HosWardrobeAllCountMonitored(ParamMap paramMap) throws BaseException;
}
