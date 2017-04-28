package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPressContainer;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosClothesPressDao {
	/**
	 * 查询衣柜信息条数
	 * 
	 * @author xujialong
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public Long queryClothesPressCount(ParamMap pm) throws BaseException;

	/**
	 * 查询衣柜信息
	 * 
	 * @author xujialong
	 * @param pm
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> queryClothesPressAll(ParamMap pm) throws BaseException;

	/**
	 * 添加手术衣柜
	 * 
	 * @Title: addClothesPress
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:int
	 * @throws:
	 */
	public int addClothesPress(HosClothesPress hosClothesPress) throws BaseException;

	/**
	 * 添加手术衣柜小柜
	 * 
	 * @Title: addClothesPressContainer
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:int
	 * @throws:
	 */
	public int addClothesPressContainer(HosClothesPressContainer clothesPressContainer) throws BaseException;

	/**
	 * 根据id查询衣柜信息
	 * 
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosClothesPress queryClothesPressById(String id) throws BaseException;

	/**
	 * 修改衣柜信息
	 * 
	 * @author xujialong
	 * @param hosClothesPress
	 * @return
	 * @throws BaseException
	 */
	public int updateHosClothesPress(HosClothesPress hosClothesPress) throws BaseException;

	/**
	 * 根据更衣室编号查询该更衣室所有的衣柜
	 * 
	 * @Title: selectHosClothesPressByTheaterNumber
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:List<HosClothesPress>
	 * @throws:
	 */
	public List<HosClothesPress> selectHosClothesPressByRoomNumber(Integer roomNumber) throws BaseException;

	/**
	 * 根据衣柜的id查询该衣柜所有空柜信息
	 * 
	 * @Title: selectHosClothesPressContainerById
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:List<HosClothesPressContainer>
	 * @throws:
	 */
	public List<HosClothesPressContainer> selectHosClothesPressContainerById(String clothesPressId)
			throws BaseException;

	/**
	 * 根据小柜id修改小柜状态,锁定人员信息
	 * 
	 * @Title: updateHosClothesPressContainerById
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:int
	 * @throws:
	 */
	public int updateHosClothesPressContainerById(Integer id, Integer userId) throws BaseException;

	/**
	 * 根据衣柜id查询衣柜使用情况
	 * 
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public Long selectClothesFromClothesPressById(String id) throws BaseException;

	/**
	 * 根据衣柜id删除衣柜
	 * 
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteClothesPressById(String id) throws BaseException;

	/**
	 * 根据衣柜id删除衣柜小柜信息
	 * 
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteClothesPressByIdContainer(String id) throws BaseException;

	/**
	 * 重新领取时修改小柜分配信息
	 * 
	 * @Title: updateStateAndUserIdByUserId
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:int
	 * @throws:
	 */
	public int updateStateAndUserIdByUserId(Integer userId) throws BaseException;

	/**
	 * 根据用户id和衣柜编号查询是否具有打开小柜的权限
	 * 
	 * @author xujialong
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectClothesPressByUserIdAndNumber(Integer userId, String ClothesPressNum)
			throws BaseException;

	/**
	 * 根据小柜id清空使用的衣柜
	 * 
	 * @author xujialong
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int updateClothesPressContainerById(Integer id) throws BaseException;

	/**
	 * 查询编号是否重复
	 * 
	 * @author xujialong
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectClothesPressByNumber(String id, String number) throws BaseException;

	/**
	 * 根据用户id和手术室编号查询衣柜小柜信息
	 * 
	 * @author xujialong
	 * @param number
	 * @param cardNum
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectClothesPressContainerByUserIdAndNumber(String number, Integer userId)
			throws BaseException;
	/**
	 * 
	* @Title: selectClothesPressByNumber
	* @author Ning 
	* @data:2017年2月21日
	* @return:HosClothesPress
	* @throws:
	 */
	public HosClothesPress selectClothesPressByNumber(String number) throws BaseException;
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
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateLockClothesPress (Integer id) throws BaseException;
	/**
	 * 根据储物柜小柜id取消锁定储物柜小柜
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateStoplockClothesPress (Integer id) throws BaseException;
	/**
	 * 根据储物柜小柜id和用户id绑定小柜与医护人员
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	public int updateBindingClothesPressAndUser (Integer id , Integer userId, Integer clothSize) throws BaseException;
	/**
	 * 根据储物柜小柜id解除绑定小柜与医护人员
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int updateStopBindingClothesPressAndUser (Integer id ) throws BaseException;
	/**
	 * 根据用户id及小柜id查询小柜所在手术区内是否已经有绑定的储物柜小柜
	 * @author xujialong
	 * @param userId
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosClothesPressContainer selectBindingClothesPressByUserIdAndTheaterNumber(Integer userId,String theaterNumber) throws BaseException;
	/**
	 * 根据用户id和更衣室id（是否绑定）查询储物柜小柜信息
	 * @author xujialong
	 * @param userId
	 * @param roomId
	 * @param yesOrNoBinding
	 * @return
	 * @throws BaseException
	 */
	public HosClothesPressContainer selectBindingClothesPressContainerByUserIdAndRoomId(Integer userId,Integer roomId,String yesOrNoBinding) throws BaseException;
	/**
	 * 根据更衣室id查询储物柜空柜
	 * @author xujialong
	 * @param roomId
	 * @return
	 * @throws BaseException
	 */
	public List<HosClothesPressContainer> selectNotBindingClothesPressContainerByRoomId(Integer roomId) throws BaseException;
	/**
	 * 根据已绑定小柜id修改小柜状态
	 * @author xujialong
	 * @param id
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public int updateBindingClothesPressContainerStateById(Integer id,String state) throws BaseException;
	/**
	 * 根据储物柜小柜id查询所在储物柜信息
	 * @author xujialong
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosClothesPress selectClothesPressById(Integer id) throws BaseException;
	/**
	 * 根据设备号查询该设备下的柜门编号
	* @Title: selectDoorNumbersByDeviceId
	* @author Ning 
	* @data:2017年3月29日
	* @return:List<String>
	* @throws:
	 */
	public List<HosClothesPressContainer> selectDoorNumbersByDeviceId(String deviceId) throws BaseException;
	/**
	 * 根据设备id和门号找到相应的锁号
	* @Title: selectLockerIdBydoorNumberAndDeviceId
	* @author Ning 
	* @data:2017年3月29日
	* @return:HosClothesPressContainer
	* @throws:
	 */
	public HosClothesPressContainer selectLockerIdBydoorNumberAndDeviceId(String deviceId,String doorNumber) throws BaseException;
	
	
}
