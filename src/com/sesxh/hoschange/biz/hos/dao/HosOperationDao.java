package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosOperationDao {
	
	/**
	 * 添加手术服
	 * @param hosOperation
	 * @return
	 * @throws BaseException
	 */
	public int insertHosOperation(HosOperation hosOperation) throws BaseException,DataAccessException;

	/**
	 * 批量添加手术服
	 * @param hosOperation
	 * @return
	 * @throws BaseException
	 */
	public int[] bacthInsertHosOperation(List<HosOperation> hosOperation) throws BaseException,DataAccessException;
	
	/**
	 * 删除手术服
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosOperationById(Integer id) throws BaseException;
	
	/**
	 * 删除手术服通过尺码
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosOperationByClothSize(Integer clothSize,Integer state) throws BaseException;
	
	/**
	 * 修改手术服
	 * @param hosOperation
	 * @return
	 * @throws BaseException
	 */
	public int updateHosOperation(HosOperation hosOperation) throws BaseException;
	
	/**
	 * 查询手术服数据
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectAllHosOperation(ParamMap paramMap) throws BaseException;
	/**
	 * 根据手术室查询衣物信息
	 * @author xujialong
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectHosOperationLists(Map<String,Object> params)throws BaseException ;
	
	/**
	 * 查询手术服数据数量
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long hosOperationCount(ParamMap paramMap) throws BaseException;

	/**
	 * 通过id查询手术服基本信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosOperation selectHosOperationById(Integer id) throws BaseException;
	
	/**
	 * 通过尺码查询手术服(模式一)
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	HosOperation selectHosOperationByClothSize(String clothSize) throws BaseException;
	/**
	 * 通过尺码和手术室查询手术服(模式一)
	 * @author xujialong
	 * @param clothSize
	 * @param theNumber
	 * @return
	 * @throws BaseException
	 */
	public HosOperation selectHosOperationByClothSizeAndtheNumber(String clothSize,String theNumber) throws BaseException;
	/**
	 * 根据 消毒鞋柜编号 修改手术鞋状态
	 *     (模式一 模式二 共用)
	 * @param id
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public int updateStateByWarNumber(String warNumber,String state) throws BaseException ;
	
	/**
	 * 根据手术室查询手术衣服数据(含手术衣尺码名称数据)
	 * @return
	 * @throws BaseException
	 */
	List<Map<String,Object>> selectHosOperationList(String theNumber) throws BaseException;
	
	/***
	 *  数据字典
	 * 查询现有的手术衣尺码
	 * @return
	 * @throws BaseException
	 */
	public List<ClassConvertDict> selectHosOperation() throws BaseException;
	
}
