package com.sesxh.hoschange.biz.hos.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.common.data.ParamMap;

public interface HosSterilizerContainerDao {

	/**
	 * 修改小柜 消毒柜编号
	 * 
	 * @param oldNumber
	 * @param newNumber
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerBySteNumber(String oldNumber, String newNumber) throws BaseException;

	/**
	 * 通过id查询小柜
	 * 
	 * @Title: selectSterilizerContainerBySteNumber
	 * @author Ning
	 * @data:2017年2月6日
	 * @return:List<HosSterilizerContainer>
	 * @throws:
	 */
	public HosSterilizerContainer selectSterilizerContainerById(Integer id) throws BaseException;

	/**
	 * 通过userId查询小柜信息
	 * 
	 * @Title: selectSterilizerContainerByUserId
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:HosSterilizerContainer
	 * @throws:
	 */
	public HosSterilizerContainer selectSterilizerContainerByUserId(Integer userId) throws BaseException;

	/**
	 * 根据消毒柜编号 删除小柜信息
	 * 
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int deleteContainerBySteNumber(String number) throws BaseException;

	/**
	 * 根据消毒柜编号 查询 柜内 尺码 及数量
	 * 
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectCountShoesSize(String number) throws BaseException;

	/**
	 * 更新 小柜使用信息
	 * 
	 * @param hos
	 * @return
	 * @throws BaseException
	 */
	public int updateSterilizerContainer(HosSterilizerContainer hos) throws BaseException;

	/**
	 * 添加小柜
	 * 
	 * @param hos
	 * @return
	 * @throws BaseException
	 */
	public int insertSterilizerContainer(HosSterilizerContainer hos) throws BaseException;

	/**
	 * 通过消毒柜号查询小柜 模式1
	 * 
	 * @param steNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizerContainer> selectSterilizerContainerOneBySteNumber(String steNumber) throws BaseException;

	/**
	 * 通过消毒柜号查询小柜 模式二
	 * 
	 * @param steNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizerContainer> selectSterilizerContainerTwoBySteNumber(String steNumber) throws BaseException;

	/**
	 * 更新小柜通过小柜id
	 * 
	 * @param id
	 * @param state
	 * @param shoesSize
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerById(Integer id, String state, String shoesSize) throws BaseException;

	/**
	 * 通过id查询鞋柜小柜信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizerContainer selectHosSterilizerContainerById(Integer id) throws BaseException;

	/**
	 * 根据消毒柜编号 查询 小柜id
	 * 
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectContainerBySteNumber(String number) throws BaseException;

	/**
	 * 根据手术室编号 查询 小柜id
	 * 
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectContainerByThNumber(String number) throws BaseException;

	/**
	 * 通过消毒柜，小柜编号 查询小柜信息
	 * 
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizerContainer selectSteContainerByNumber(String number, String lockerNumber) throws BaseException;

	/**
	 * 更新小柜 使用状态 尺码 使用用户
	 * 
	 * @param number
	 * @param LockerNumber
	 * @return
	 * @throws BaseException
	 */
	public int updateContainer(HosSterilizerContainer HosSterilizerContainer) throws BaseException;

	/**
	 * 根据用户id 手术室编号 查询所使用的小柜信息
	 * 
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizerContainer> findSteConNumberByUserIdAndThNumber(Integer id, String number)
			throws BaseException;

	/**
	 * 终端分配鞋子使用 更改小柜使用状态
	 * 
	 * @param id
	 *            小柜id
	 * @param state
	 *            使用状态
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerState(Integer id, String state) throws BaseException;

	// -------------------------------11-30-------------------------------------------

	/**
	 * 管理员给小柜分配鞋子尺码
	 * 
	 * @param id
	 *            小柜id
	 * @param shoesSize
	 *            尺码
	 * @return
	 * @throws BaseException
	 */
	public int updateSizeShoesToSterilizer(Integer id, String shoesSize) throws BaseException;

	/**
	 * 根据鞋柜小柜id锁定鞋柜小柜
	 * 
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int updateLockSterilizerContainer(Integer id) throws BaseException;

	/**
	 * 根据鞋柜小柜id取消锁定鞋柜小柜
	 * 
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int updateStopLockSterilizer(Integer id) throws BaseException;

	/**
	 * 根据小柜id和用户id绑定小柜与用户
	 * 
	 * @author xujialong
	 * @param ids
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int updateBindingSterilizerAndUser(Integer id, Integer userId, Integer shoesSize) throws BaseException;

	/**
	 * 查询该用户是否已经绑定小柜
	 * 
	 * @author xujialong
	 * @param userId
	 * @param theaterNumber
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizerContainer selectBindingSterilizerAndUser(Integer userId, String theaterNumber)
			throws BaseException;

	/**
	 * 根据小柜id解除绑定小柜与用户
	 * 
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int updateStopBindingSterilizerAndUser(Integer id) throws BaseException;

	/**
	 * 查看添加消毒鞋规则
	 * 
	 * @author xujialong
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectAddShoeRule(ParamMap paramMap) throws BaseException;

	/**
	 * 根据userId查询添加消毒鞋规则
	 * 
	 * @author xujialong
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> selectAddShowRuleByUserId(Integer userId) throws BaseException;

	/**
	 * 查看添加消毒鞋规则count
	 * 
	 * @author xujialong
	 * @param paramMap
	 * @return
	 * @throws BaseException
	 */
	public Long selectAddShoeRuleCount(ParamMap paramMap) throws BaseException;

	/**
	 * 修改衣物管理员添加鞋子规则
	 * 
	 * @author xujialong
	 * @param code
	 * @param userId
	 * @param size
	 * @return
	 * @throws BaseException
	 */
	public int updateSetUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException;

	/**
	 * 添加衣物管理员添加鞋子规则
	 * 
	 * @author xujialong
	 * @param code
	 * @param userId
	 * @param size
	 * @return
	 * @throws BaseException
	 */
	public int insertSetUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException;

	/**
	 * 根据设备号、userId查找绑定小柜信息
	 * 
	 * @Title: selectBindingContainer
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:HosSterilizerContainer
	 * @throws:
	 */
	public HosSterilizerContainer selectBindingContainer(String deviceNumber, Integer userId) throws BaseException;

	/**
	 * 更新绑定小柜的状态
	 * 
	 * @Title: updataBindingContainerState
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:HosSterilizerContainer
	 * @throws:
	 */
	public int updataBindingContainerState(HosSterilizerContainer container) throws BaseException;

	/**
	 * 根据设备号，鞋码随机分配小柜
	 * 
	 * @Title: selectRandomContainer
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:HosSterilizerContainer
	 * @throws:
	 */
	public List<HosSterilizerContainer> selectRandomContainer(String deviceNumber, Integer shoeSize)
			throws BaseException;

	/**
	 * 根据鞋柜编号，状态（尺码）查询鞋柜小柜
	 * 
	 * @author xujialong
	 * @param deviceNumber
	 * @param shoeSize
	 * @param state
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizerContainer> selectContainerByAdminCodeAndShoSizeAndState(String deviceNumber,
			Integer shoeSize, String state) throws BaseException;

	/**
	 * 根据id修改鞋柜小柜的使用状态及使用人
	 * 
	 * @author xujialong
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int updateContainerByUserId(Integer id) throws BaseException;

	/**
	 * 根据useri和steNumber查询是否有该记录
	 * 
	 * @author xujialong
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int selectRecordByUseridAndSteNumber(String userid, String steNumber) throws BaseException;

}
