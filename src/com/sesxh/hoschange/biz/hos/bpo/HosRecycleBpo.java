package com.sesxh.hoschange.biz.hos.bpo;

import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.RecycleBinData;

/**
 * 
 * @author Ning
 *
 */
public interface HosRecycleBpo {
	/**
	 * 根据编号查询回收桶
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public HosRecycle selectRecycleByNumber(String number,Integer id) throws BaseException;
	/**
	 * 增加回收桶
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	public int insertRecycle(HosRecycle hosRecycle) throws BaseException;
	/**
	 * 查询所有的回收桶信息
	 * @author Ning
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosRecycleSet(Map<String, Object> params) throws BaseException;
	/**
	 * 通过id查询回收桶信息
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosRecycle queryHosrecycleById(Integer id) throws BaseException;
	/**
	 * 修改回收桶信息
	 * @author Ning
	 * @param hosRecycle
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecycle(HosRecycle hosRecycle) throws BaseException;
	/**
	 * 根据回收桶编号查询回收数量
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectRecycleFromRecycleByNumber(String number) throws BaseException;
	/**
	 * 根据id删除回收桶
	 * @author Ning
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosRecycle(Integer id) throws BaseException;
	/**
	 * 根据回收桶编号和卡号更新回收数量
	 * @author Ning
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int updateHosRecycleCountByNumberAndCardNum(String number,String cardNum) throws BaseException;
	/**
	 * 根据回收桶编号清空回收桶
	 * @author Ning
	 * @param recycleNum
	 * @return
	 * @throws BaseException
	 */
	public int emptyRecycle(String recycleNum) throws BaseException;
	/**
	 * 查询衣物管理员管辖范围内的回收桶
	 * @author Ning
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosRecycleMonitored(Map<String, Object> params) throws BaseException;
	/**
	 * 医护人员刷卡，第一次通信，开桶门
	 * @author xujialong
	 * @param authUser
	 * @param message
	 * @param hosRecord
	 * @return
	 * @throws BaseException
	 */
	public Message openRecycleDoor(AuthUser authUser,Message message,HosRecord hosRecord) throws BaseException;
	/**
	 * 第二次通信，带回感应状态
	* @Title: RecyclingBinData
	* @author Ning 
	* @data:2017年3月20日
	* @return:void
	* @throws:
	 */
	public Message RecyclingBinData(RecycleBinData recycleBinData) throws BaseException ;
	/**
	 * 第二次通信，带回监控图片
	* @Title: RecyclingBinData
	* @author Ning 
	* @data:2017年3月20日
	* @return:void
	* @throws:
	 */
	public Message RecyclingBinImage(RecycleBinData recycleBinData) throws BaseException ;
	
	
}
