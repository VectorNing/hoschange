package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosShoesDao {
	
	/**
	 * 新增手术鞋
	 * @param hosShoes
	 * @return
	 * @throws BaseException
	 */
	public int insertHosShoes(HosShoes hosShoes) throws BaseException;
	
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
	public int updateHosShoes(HosShoes hosShoes) throws BaseException;
	
	/**
	 * 通过id查询手术鞋基本信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosShoes selectHosShoesById(Integer id) throws BaseException;
	
	/**
	 * 根据 主键修改手术鞋状态(模式一)
	 * @param id
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public int updateStateByShoesId(Integer id,String state) throws BaseException ;
	
	/**
	 * 根据 手术鞋编号 修改手术鞋状态(模式二)
	 * @param id
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public int updateStateByShoesNumber(String number,String state) throws BaseException ;
	
	/**
	 * 根据 消毒鞋柜编号 修改手术鞋状态
	 *     ( 模式二 共用)
	 * @param id
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public int updateStateBySteNumber(String steNumber,String state) throws BaseException ;
	
	/**
	 * 根据鞋子尺码查询
	 * @param shoesSize
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public HosShoes selectHosShoesByShoesSize(String shoesSize) throws BaseException ;
	
	/**
	 * 根据鞋码 查询 手术鞋信息
	 * @param shoeSize
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public HosShoes selectHosShoesByShoeSizeAndTheaterNumber(Integer shoeSize,String TheaterNumber) throws BaseException ;
	
	/**
	 * 查询手术鞋数据
	 * @param strategy
	 * @return
	 * @throws BaseException
	 */
	public List<HosShoes> selectHosShoesByTheaterNumber(String number) throws BaseException;
	
	
	/**
	 * 根据手术室查询鞋子详细信息(使用中，已分配)
	 * @author Ning
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectHosShoesListByTheaterNumber(ParamMap paramMap) throws BaseException;
}
