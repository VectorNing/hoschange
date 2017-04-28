package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosShoesSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosSterilizerDao {
	
	/**
	 * 新增消毒鞋柜
	 * @param hosSterilizer
	 * @return
	 * @throws BaseException
	 */
	public int insertHosSterilizer(HosSterilizer hosSterilizer) throws BaseException;

	/**
	 * 删除消毒鞋柜
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosSterilizerById(Integer id) throws BaseException;

	/**
	 * 修改消毒鞋柜
	 * @param hosSterilizer
	 * @return
	 * @throws BaseException
	 */
	public int updateHosSterilizer(HosSterilizer hosSterilizer) throws BaseException;

	/**
	 * 查询消毒鞋柜数据
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAllHosSterilizer(ParamMap paramMap) throws BaseException;
	/**
	 * 根据更衣室查找该更衣室所有的亵渎鞋柜
	 * @author Ning
	 * @param paramMap
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizer> selectAllHosSterilizer(String theaterNumber) throws BaseException;

	/**
	 * 查询消毒鞋柜数据数量
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosSterilizerCount(ParamMap paramMap) throws BaseException;
	
	/**
	 * 分配消毒鞋到鞋柜
	 * @return
	 * @throws BaseException
	 */
	public int allotShoesToSterilizer(HosShoesSterilizer hosShoesSterilizer) throws BaseException;
	
	/**
	 * 查询相应消毒鞋柜中分配的鞋子
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectShoeBySterilizerId(Integer id) throws BaseException;
	
	/**
	 * 查询相应消毒鞋柜
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizer selectHosShoesSterilizerById(Integer id) throws BaseException;
	
	/**
	 * 根据消毒鞋柜编号 删除 模式一 鞋柜手术鞋
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int deleteShoesSterilizerBySteNumber(String number) throws BaseException ;
	
	/**
	 * 根据消毒鞋柜编号 删除 模式二 鞋柜手术鞋
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int deleteShoesSteBySteNumber(String number) throws BaseException ;
	
	/**
	 * 根据消毒鞋柜编号 查询 所属手术鞋主键 (模式一)
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectShoesIdByWarNumber(String number) throws BaseException ;
	
	/**
	 * 根据消毒鞋柜编号 查询 所属手术鞋编号(模式二)
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectShoNumberByWarNumber(String number) throws BaseException ;
	
	/**
	 * 根据 消毒柜(编号)查询手术鞋 的 数量
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public Long selectShoesFromSterilizerByNumber(String number) throws BaseException ;
	
	/**
	 * 根据消毒柜编码 
	 *   修改模式一 ，二 对应 新 编号
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int updateShoSteSteNumberByNumber(String oldNumber,String newNumber) throws BaseException ;
	
	/**
	 * 根据手术室编号修改 鞋柜手术室编号
	 * @param oldNumber
	 * @param newNumber
	 * @return
	 * @throws BaseException
	 */
	public int updateStrTheaterNumberByNumber(String oldNumber,String newNumber) throws BaseException ;
	
	/**
	 * 根据 手术室编号 加载 消毒柜列表
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public Long selectSterCountByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 根据 手术室编号 加载 消毒柜列表
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectSterListByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 根据 手术室编号 加载不属于 的消毒柜列表
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public Long selectNotSterCountByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 根据 手术室编号 加载不属于 的 消毒柜列表
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectNotSterListByTheNumber(ParamMap pm) throws BaseException ;
	
	/**
	 * 分配手术消毒柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int assignTheaterToSte(HosSterilizer hosSterilizer) throws BaseException ;
	
	/**
	 * 移除手术消毒柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int removeSteFromTheater(HosSterilizer hosSterilizer) throws BaseException ;
	
	/**
	 * 根据手术室编号 更新状态 手术室编号
	 *     用于删除手术室
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	public int updateTheNumberStateByThenumber(HosSterilizer hosSterilizer) throws BaseException ;
	
	/**
	 * 根据手术室编号 查询消毒鞋柜信息
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizer> selectSterilizerByThNumber(String number) throws BaseException ;
	/**
	 * 
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizer selectSterilizerByNumber(String number) throws BaseException ;
	/**
	 * 根据消毒柜编号查询 条数
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectSteByNumber(String number,Integer id) throws BaseException;
	
	/**
	 * 衣物管理员加载 所属更衣室 下的消毒鞋列表
	 * @author xujialong
	 * @param paramMap
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectHosSterilizerMonitoredByUserId(ParamMap paramMap) throws BaseException;
	public Long HosSterilizerCountMonitoredByUserId(ParamMap paramMap) throws BaseException;
	/**
	 * 超级管理员后台管理员加载消毒鞋列表
	* @Title: selectAllHosSterilizerMonitored
	* @author Ning 
	* @data:2017年1月16日
	* @return:List<Map<String,Object>>
	* @throws:
	 */
	public List<Map<String,Object>> selectAllHosSterilizerMonitored(ParamMap paramMap) throws BaseException;
	public Long HosSterilizerAllCountMonitored(ParamMap paramMap) throws BaseException;
}
