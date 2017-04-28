package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;

public interface HosOperationService {
	
	/**
	 * 添加手术服M1
	 * @param hosOperation
	 * @return
	 * @throws BaseException
	 */
	public int insertHosOperationM1(HosOperation hosOperation) throws BaseException;

	/**
	 * 批量添加手术服
	 * @param hosOperation
	 * @return
	 * @throws BaseException
	 */
	public int[] bacthInsertHosOperation(HosOperation hosOperation) throws BaseException,DataAccessException;
	/**
	 * 删除手术服
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosOperationById(Integer id) throws BaseException;

	/**
	 * 修改手术服M1
	 * @param hosOperation
	 * @return
	 * @throws BaseException
	 */
	public int updateHosOperationM1(HosOperation hosOperation) throws BaseException;
	/**
	 * 修改手术服数量(模式一)
	 * @param count
	 * @return
	 * @throws BaseException
	 */
	public int[] updateHosOperationNumM1(HosOperation hosOperation) throws BaseException;
	
	/**
	 * 查询手术服数据
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectHosOperationSet(Map<String,Object> params)throws BaseException ;
	
	/**
	 * 根据尺码加载 相关分配，使用中，回收的手术衣信息
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectHosOperationLists(Map<String,Object> params)throws BaseException ;

	/**
	 * 通过id查询手术服基本信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosOperation selectHosOperationById(Integer id) throws BaseException;
	
	
	/**
	 * 通过id查询手术服数量
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int findHosOperationNumById(Integer id) throws BaseException;
	
	/**
	 * 删除手术服通过尺码
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosOperationByClothSize(Integer clothSize) throws BaseException;
	
	/**
	 * 通过尺码查询手术服(模式一)
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	HosOperation findHosOperationByClothSize(String clothSize) throws BaseException;
	
	/**
	 * 根据手术室查询手术衣服数据(含手术衣尺码名称数据)
	 * @return
	 * @throws BaseException
	 */
	List<Map<String,Object>> queryHosOperationList(String warNumber) throws BaseException;
	
	/***
	 *  数据字典
	 * 查询现有的手术衣尺码
	 * @return
	 * @throws BaseException
	 */
	public List<ClassConvertDict> selectHosOperation() throws BaseException;
	
}
