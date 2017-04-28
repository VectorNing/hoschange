package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.Message;

public interface HosClothesPressService {
	/**查询所有衣柜信息
	 * @author xujialong
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public Map<String, Object> queryClothesPressAll(Map<String, Object> params) throws BaseException;
	/**
	 * 添加手术衣柜
	* @Title: addClothesPress
	* @author Ning 
	* @data:2017年1月19日
	* @return:int
	* @throws:
	 */
	public int addClothesPress(HosClothesPress hosClothesPress) throws BaseException;
	/**
	 * 根据id查询衣柜信息
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosClothesPress queryClothesPressById(String id) throws BaseException;
	/**修改衣柜信息
	 * @author xujialong
	 * @param hosClothesPress
	 * @return
	 * @throws BaseException
	 */
	public int updateHosClothesPress(HosClothesPress hosClothesPress) throws BaseException;
	/**
	 * 根据衣柜id查询衣柜使用情况
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public Long selectClothesFromClothesPressById(String id) throws BaseException;
	/**
	 * 根据id删除衣柜
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteClothesPressById(String id) throws BaseException;
	/**
	 * 根据用户id和衣柜编号查询是否具有打开小柜的权限
	 * @author xujialong
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> selectClothesPressByUserIdAndNumber(Integer userId,String ClothesPressNum)throws BaseException;
	
	/**查询编号是否重复
	 * @author xujialong
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectClothesPressByNumber(String id , String number) throws BaseException;
	/**
	 * 根据用户卡号和手术衣发放设备编号查询衣柜信息
	 * @author xujialong
	 * @param number
	 * @param cardNum
	 * @return
	 * @throws BaseException
	 */
	public Map<String,Object> selectOpeByCardNumAndNumber (String number,String cardNum) throws BaseException;
	/**
	 * 根据储物柜编号查询储物柜小柜信息
	 * @author xujialong
	 * @param steNumber
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String,Object>> selectClothesPressBySteNumber(String steNumber) throws BaseException;
	/**
	 * 根据储物柜小柜id锁定储物柜小柜
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int updateLockClothesPress (Integer[] ids) throws BaseException;
	/**
	 * 根据储物柜小柜id取消锁定储物柜小柜
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int updateStoplockClothesPress (Integer[] ids) throws BaseException;
	/**
	 * 根据储物柜小柜id和用户id绑定小柜与医护人员
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int updateBindingClothesPressAndUser (Integer id , Integer userId) throws BaseException;
	/**
	 * 根据储物柜小柜id解除绑定小柜与医护人员
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateStopBindingClothesPressAndUser (Integer id ) throws BaseException;
	/**
	 * 医护人员打开储物柜
	* @Title: openClothesPressContainerByUser
	* @author Ning 
	* @data:2017年2月23日
	* @return:Message
	* @throws:
	 */
	public Message openClothesPressContainerByUser (AuthUser authUser,Message message ) throws BaseException;
	/**
	 * 打开储物柜记录日志
	 * @author xujialong
	 * @param deviceNumber
	 * @param numbers
	 * @param sessionUser
	 * @return
	 * @throws BaseException
	 */
	public int openContainerClothesPressLog (String deviceNumber, String[] numbers , SessionUser sessionUser) throws BaseException;
	/**
	 * 设备请求其管理下的柜门编号
	* @Title: getDoorIdByDeviceId
	* @author Ning 
	* @data:2017年3月29日
	* @return:int
	* @throws:
	 */
	public Object getDoorIdByDeviceId(String deviceNumber) throws BaseException;
}
