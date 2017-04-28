package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.common.data.DataSet;

/** 
  * @title : HosRecoveryGoodsService.java
  * @author  作者 E-mail: 	wwb										
  * @date 创建时间：2016年10月21日 下午4:46:49 
  * @version 1.0 
  * @parameter  
  * @since  
  * @return 
  */
public interface HosRecoveryGoodsService {
	/**
	 * 查询回收单元信息
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosRecoveryGoodsSet(Map<String,Object> params)throws BaseException;
	
	
	
	/**
	 * 根据用户 更新签退状态
	 * @param state
	 * @param userId
	 * @return
	 */
	public int updateHosRecoveryGoodsStateByUserId(Integer userId) throws BaseException;
	
	
	/**
	 * 根据用户卡号  更新回收状态
	 * @param state
	 * @param userId
	 * @return
	 */
	public int updateRecoveryGoodsByCardNumAndState(String cardNum,String warNumber,String state) throws BaseException;
	/**
	 * 根据签到id和设备类型查看领取记录
	 * @author xujialong
	 * @param id
	 * @param type
	 * @return
	 * @throws BaseException
	 */
	public HosRecoveryGoods selectHosRecoveryGoodsByIdAndType(Integer id,String type) throws BaseException;
	/**
	 * 
	* @Title: selectImageBase64
	* @author Ning 
	* @data:2017年3月25日
	* @return:List<String>
	* @throws:
	 */
	public List<String> selectImageBase64(Integer id) throws BaseException ;
	
}
