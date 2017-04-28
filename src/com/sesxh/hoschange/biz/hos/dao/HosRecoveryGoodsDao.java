package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.common.data.Image;
import com.sesxh.hoschange.common.data.ParamMap;

/** 
  * @title : HosRecoveryGoodsDao.java
  * @author  作者 E-mail: 	wwb										
  * @date 创建时间：2016年10月21日 下午3:04:47 
  * @version 1.0 
  * @parameter  
  * @since  
  * @return 
  */
public interface HosRecoveryGoodsDao {
	
	/**
	 * 添加回收物品单元信息
	 * @param hosRecoveryGoods
	 * @return
	 */
	public int insertHosRecoveryGoods(HosRecoveryGoods hosRecoveryGoods) throws BaseException;
	
	/**
	 * 更新是否回收的状态
	 * @param state
	 * @param userId
	 * @return
	 */
	public int updateHosRecoveryGoodsStateByUserId(String state,Integer recordId,Integer userId,String type) throws BaseException;
	
	
	/**
	 * 通过用户id查询回收单元信息
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public HosRecoveryGoods selectHosRecoveryGoodsByUserId(Integer userId) throws BaseException;
	
	/**
	 * 通过签到id和设备类型查询回收单元信息
	 * @author xujialong
	 * @param id 签到id
	 * @param  type   设备类型
	 * @return
	 * @throws BaseException
	 */
	public HosRecoveryGoods selectHosRecoveryGoodsByIdAndType(Integer id,String deviceType) throws BaseException;

	/**
	 * 通过签退状态查询回收单元信息
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<HosRecoveryGoods> selectHosRecoveryGoodsByState(String thNumber,String state) throws BaseException;
	
	/**
	 * 查询回收单元信息数量
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long HosRecoveryGoodsCount(ParamMap paramMap) throws BaseException;
	
	/**
	 * 查询回收单元信息
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	List<Map<String, Object>> selectHosRecoveryGoods(ParamMap paramMap) throws BaseException;
	
	/**
	 * 通过id修改回收单元状态
	 * @param state
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecoveryGoodsStateById(String state, Integer id,String deviceNumber) throws BaseException;

	/**
	 * 通过设备类型和尺码删除回收单元
	 * @param type
	 * @param size
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosRecoveryGoodsByTypeAndSize(String type, Integer size) throws BaseException;
	
	/**
	 * 通过设备类型和签到id删除回收单元
	 * @author xujialong
	 * @param type  设备类型
	 * @param id   签到id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosRecoveryGoodsByIdAndType(String type, Integer id) throws BaseException;
	
	public List<HosRecoveryGoods> selectHosRecoveryGoodsById(Integer id) throws BaseException;
	/**
	 * 根据设备号和回收状态查找回收记录
	* @Title: selectHosRecoveryGoodsByDeviceNumberAndReadyCallBack
	* @author Ning 
	* @data:2017年3月20日
	* @return:HosRecoveryGoods
	* @throws:
	 */
	public HosRecoveryGoods selectHosRecoveryGoodsByDeviceNumberAndReadyCallBack(String deviceNumber,String readyCallBack) throws BaseException;
	/**
	 * 根据回收记录id查询其监控图片
	* @Title: selectImageByRecoveryGoods
	* @author Ning 
	* @data:2017年3月25日
	* @return:List<Image>
	* @throws:
	 */
	public List<Image> selectImageByRecoveryGoodsID(Integer id) throws BaseException;
	
}
