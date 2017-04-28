package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;

public interface HosWardrobeContainerDao {

	/**
	 * 新增手术衣柜托盘详细
	 * @return
	 * @throws BaseException
	 */
	public int insertHosWardrobeContainer(HosWardrobeContainer hoswardrobeContainer) throws BaseException;
	
	/**
	 * 修改手术衣柜托盘详细
	 * @return
	 * @throws BaseException
	 */
	public int updateHosWardrobeContainer(HosWardrobeContainer hoswardrobeContainer) throws BaseException;
	
	/**
	 * 根据衣柜编号 查询托盘
	 * @param warNumber
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosWardrobeContainer> selectHosWardrobeContainerByWarNumber(String warNumber) throws BaseException;
	
	
	
	
	/**
	 * 更新 衣柜托盘 衣柜编号
	 * @param oldNumber
	 * @param newNumber
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerByWarNumber(String oldNumber,String newNumber) throws BaseException ;
	
	/**
	 * 根据衣柜编号  删除 托盘信息
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int deleteContainerByWarNumber(String number) throws BaseException ;
	 
	
	/**
	 * 通过尺码和手术室更新托盘
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerByClothSizeAndtheNumber(String clothSize,String theNumber) throws BaseException;
	
	/**
	 * 通过托盘id 更新托盘
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerById(String id, Integer alloutCount, String opeSize) throws BaseException;
	
	/**
	 * 通过托盘编号查询托盘
	 * @return
	 * @param trayNumber
	 * @throws BaseException
	 */
	public HosWardrobeContainer selectContainerById(Integer id) throws BaseException;

	/**
	 * 清空托盘手术服
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	public int emptyContainerById(Integer id) throws BaseException;
	
	/**
	 * 根据id 修改托盘编号
	 * @param HosWardrobeContainer
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerById(HosWardrobeContainer HosWardrobeContainer) throws BaseException;
	
	/**
	 * 根据衣柜编号  查询 托盘id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectContainerByNumber(String number) throws BaseException;
	
	/**
	 * 根据手术室编号  查询 托盘id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectContainerByThNumber(String number) throws BaseException;
	/**
	 * 通过托盘id 修改托盘剩余量
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerByWarTarNumber(Integer id) throws BaseException;
	
	
	
	/**
	 * 设定托盘分配尺码
	 * @param id 托盘id
	 * @param opeSize 衣服尺码
	 * @return
	 * @throws BaseException
	 */
	public int settingOperationSizeForContainer(Integer id,String opeSize) throws BaseException;
	
	/**
	 * 更新已分配数量
	 * @param id 衣柜托盘id
	 * @param count 新增的数量
	 * @return
	 * @throws BaseException
	 */
	public int updateAlloutCount(Integer id,Integer count) throws BaseException;
	
	/**
	 * 清空托盘  不清除尺码
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int emptyContainerCountById(Integer id) throws BaseException;
}
