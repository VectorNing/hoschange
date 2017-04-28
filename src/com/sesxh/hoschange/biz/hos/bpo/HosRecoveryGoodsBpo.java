package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.common.data.DataSet;

/**
 * @title : HosRecoveryGoodsBpo.java
 * @author 作者 E-mail: wwb
 * @date 创建时间：2016年10月21日 下午4:41:27
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface HosRecoveryGoodsBpo {
	/**
	 * 查询回收单元信息
	 * 
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosRecoveryGoodsSet(Map<String, Object> params) throws BaseException;

	/**
	 * 根据用户 更新签退状态
	 * 
	 * @param state
	 * @param userId
	 * @return
	 */
	public int updateHosRecoveryGoodsStateByUserId(Integer userId) throws BaseException;

	/**
	 * 根据用户卡号 更新回收状态 手术衣柜 回收手术衣
	 * 
	 * @param state
	 * @param userId
	 * @return
	 */
	public int updateRecoveryGoodsByCardNumAndState(String cardNum, String warNumber, String state)
			throws BaseException;

	/**
	 * 根据用户 id,设备类型清除；领取异常记录
	 * 
	 * @author xujialong
	 * @param type
	 *            设备类型
	 * @param userId
	 *            用户id
	 * @param theNumber
	 *            手术室编号
	 * @return 是否是重新领取
	 */
	public int deleteHosRecoveryGoodsByUserIdAndType(Integer userId, String type, String theNumber)
			throws BaseException;

	/**
	 * 根据签到id 设备类型查询领取信息
	 * 
	 * @author xujialong
	 * @param type
	 *            设备类型
	 * @param id
	 *            签到id
	 * @return 领取信息
	 */
	public HosRecoveryGoods selectHosRecoveryGoodsByIdAndType(Integer id, String deviceType) throws BaseException;

	/**
	 * 根据回收ID查询其监控图片
	 * 
	 * @Title: selectImageBase64
	 * @author Ning
	 * @data:2017年3月25日
	 * @return:List<String>
	 * @throws:
	 */
	public List<String> selectImageBase64(Integer id) throws BaseException;

	/**
	 * 根据id查询领取记录
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public List<HosRecoveryGoods> selectHosRecoveryGoodsById(Integer id) throws BaseException;

	/**
	 * 插入回收记录
	 * 
	 * @param hosRecoveryGoods
	 * @throws BaseException
	 */
	public int insertHosRecoveryGoods(HosRecoveryGoods hosRecoveryGoods) throws BaseException;

}
