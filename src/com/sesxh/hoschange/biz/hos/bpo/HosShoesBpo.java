package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;

public interface HosShoesBpo {
	
	/**
	 * 新增手术鞋 模式一
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
	 * 修改手术鞋 模式一
	 * @param hosShoes
	 * @return
	 * @throws BaseException
	 */
	public int updateHosShoesM1(HosShoes hosShoes) throws BaseException;
	
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
	public HosShoes findHosShoesByShoesSize(String shoesSize) throws BaseException;
	
	/**
	 * 查询手术鞋数据
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public List<HosShoes> selectHosShoesByTheaterNumber(String number) throws BaseException;
	
	/**
	 * 根据鞋柜编号加载该鞋柜所在区域的鞋子分配信息
	 * @author Ning
	 * @param SteNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosShoes> loadShoeSizeBySteNumber(String SteNumber) throws BaseException;
}
