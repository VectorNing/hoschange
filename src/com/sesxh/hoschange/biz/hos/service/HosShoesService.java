package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;

public interface HosShoesService {
	
	/**
	 * 新增手术鞋
	 * @param hosShoes
	 * @return
	 * @throws BaseException
	 */
	public int insertHosShoesM1(HosShoes hosShoes) throws BaseException;
	
	
	/**
	 * 删除手术鞋
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosShoesById(Integer id) throws BaseException;
	
	/**
	 * 修改手术鞋
	 * @param hosShoes
	 * @return
	 * @throws BaseException
	 */
	public int updateHosShoesM1(HosShoes hosShoes) throws BaseException;
	
	/**
	 * 根据尺码加载 不同的分配，使用中，回收的消毒鞋信息
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectHosShoesList(Map<String,Object> params)throws BaseException ;
	
	/**
	 * 通过id查询手术鞋基本信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosShoes findHosShoesById(Integer id) throws BaseException;

	/**
	 * 通过鞋子尺码查询手术鞋基本信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	HosShoes findHosShoesByShoesSize(String shoesSize) throws BaseException;
	
	/**
	 * 根据手术室查询手术鞋数据
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public List<HosShoes> selectHosShoesByTheaterNumber(String number) throws BaseException;
	
	/**
	 * 根据鞋柜编号加载该鞋柜所属区域内分配的鞋码信息(鞋码转换）
	 * @author Ning
	 * @param SteNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosShoes> loadShoeSizeBySteNumber(String SteNumber) throws BaseException;
}
