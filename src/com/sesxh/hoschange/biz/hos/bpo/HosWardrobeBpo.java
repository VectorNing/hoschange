package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosWardrobeBpo {
	
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
	public int deleteHosWardrobeByIdAndNumber(Integer id,String number) throws BaseException;

	/**
	 * 修改手术服衣柜
	 * @param hosWardrobe
	 * @return
	 * @throws BaseException
	 */
	public int updateHosWardrobe(HosWardrobe hosWardrobe) throws BaseException;

	/**
	 * 查询手术服衣柜数据
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosWardrobeSet(Map<String,Object> params)throws BaseException ;
	
	/**
	 * 根据编号查询手术服衣柜信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosWardrobe selectHosWardrobeById(Integer id) throws BaseException ;
	
	/**
	 * 根据衣柜编号 查询衣柜内手术衣
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectOperationFromWardBynumber(String number) throws BaseException ;
	
	/**
	 * 加载手术室 衣柜
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadWardrobeListByTheNumber(@RequestParam Map<String,Object> params ) throws BaseException ; 
	
	/**
	 * 分配手术衣柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int assignTheaterToWar(String number, Integer [] ids) throws BaseException ;
	
	/**
	 * 移除手术衣柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int removeWarFromTheater(String number,Integer [] ids) throws BaseException ;
	
	
	/**
	 * 添加托盘 信息
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public int insertContainer(Map<String,Object> params)  throws BaseException ;
	
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
	 * 根据衣柜编号查询衣柜信息
	 * @param warNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosWardrobe> loadWardrobeByWarNumber(String warNumber) throws BaseException ;
	
	
	
	/**
	 * 根据衣柜编号查询 衣柜是否暂停服务
	 * @author xujialong
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
	 * 衣物管理员 加载自己所属更衣室范围的衣柜信息
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectHosWardrobeMonitored(Map<String,Object> params)throws BaseException ;
	/**
	 * 
	* @Title: selectHosWardrobeById
	* @author Ning 
	* @data:2017年1月19日
	* @return:HosWardrobe
	* @throws:
	 */
	public HosWardrobe selectHosWardrobeByNumber(String number) throws BaseException ;
}
