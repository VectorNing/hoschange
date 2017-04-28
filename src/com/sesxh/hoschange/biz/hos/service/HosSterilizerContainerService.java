package com.sesxh.hoschange.biz.hos.service;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;

public interface HosSterilizerContainerService {
	/**
	 * 通过消毒柜号查询小柜
	 * 
	 * @param steNumber
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizerContainer selectSterilizerContainerById(Integer id) throws BaseException;

	/**
	 * 通过id查询小柜
	 * 
	 * @Title: selectSterilizerContainerBySteNumber
	 * @author Ning
	 * @data:2017年2月6日
	 * @return:List<HosSterilizerContainer>
	 * @throws:
	 */
	public List<HosSterilizerContainer> selectSterilizerContainerBySteNumber(String steNumber) throws BaseException;

	/**
	 * 给鞋小柜分配鞋子
	 * 
	 * @param lockerNumbers
	 * @param shoesSize
	 * @return
	 * @throws BaseException
	 */
	public int allotShoesToContainer(Integer[] lockerNumbers, String shoesSize, int allotCount) throws BaseException;

	public int allotShoesTwoToContainer(Integer[] lockerNumbers, Integer[] ids) throws BaseException;

	/**
	 * 清空小柜子
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int emptyContainer(Integer[] ids) throws BaseException;

	/**
	 * 清空全部小柜子
	 * 
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int emptyAllContainer(String number) throws BaseException;

	/**
	 * 根据用户id 手术室编号 查询所使用的小柜信息
	 * 
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Map<String, Object> findSteConNumberByUserIdAndThNumber(Integer id, String number) throws BaseException;

	/**
	 * 给小柜分配鞋子(终端)
	 * 
	 * @param ids
	 *            小柜id数组
	 * @param jsonArray
	 *            尺码 对应数量的 json数组 [{"size(尺码)":"","count(数量)":""}]
	 * @return
	 * @throws BaseException
	 */
	public int allotShoesToContainerForZD(Integer[] ids, String jsonArray) throws BaseException;

	// ################################ 11-30 最新
	// ################################//

	/**
	 * 管理员给小柜分配鞋子尺码
	 * 
	 * @param ids
	 *            小柜id
	 * @param shoesSize
	 *            分配的尺码
	 * @return
	 * @throws BaseException
	 */
	public int allotSizeShoesToSterilizer(Integer[] ids, String shoesSize) throws BaseException;

	/**
	 * 用户查询 自己鞋子的位置
	 * 
	 * @param id
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Map<String, Object> selectShoesByUserIdAndThNumber(String cardNum, String number) throws BaseException;

	/**
	 * 清空消毒柜 所选小柜 保留尺码
	 * 
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int emptyContainerShoes(Integer[] ids) throws BaseException;

	public int emptyContainerShoes2(Integer[] ids) throws BaseException;

	/**
	 * 根据鞋柜小柜id锁定鞋柜小柜
	 * 
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int lockSterilizerContainer(Integer[] ids) throws BaseException;

	/**
	 * 根据鞋柜小柜id取消锁定鞋柜小柜
	 * 
	 * @author xujialong
	 * @param ids
	 * @return
	 * @throws BaseException
	 */
	public int stopLockSterilizer(Integer[] ids) throws BaseException;

	/**
	 * 根据小柜id和用户id绑定小柜与用户
	 * 
	 * @author xujialong
	 * @param ids
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int bindingSterilizerAndUser(Integer id, Integer userId) throws BaseException;

	/**
	 * 根据小柜id解除绑定小柜与用户
	 * 
	 * @author xujialong
	 * @param id
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public int stopBindingSterilizerAndUser(Integer id) throws BaseException;

	/**
	 * 查看添加消毒鞋规则
	 * 
	 * @author xujialong
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectAddShoeRule(Map<String, Object> params) throws BaseException;

	/**
	 * 设定衣物管理员添加鞋子规则
	 * 
	 * @author xujialong
	 * @param code
	 * @param userId
	 * @param size
	 * @return
	 * @throws BaseException
	 */
	public int setUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException;

	/**
	 * 查询该用户是否已经绑定小柜
	 * 
	 * @param userId
	 * @param deviceNumber
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizerContainer selectBindingSterilizerAndUser(Integer userId,String deviceNumber) throws BaseException;
	/**
	 * 用户在签到的状态下，直接打开柜门
	* @Title: OpenLockerUnderSingIn
	* @author Ning 
	* @data:2017年2月22日
	* @return:Message
	* @throws:
	 */
	public Message OpenLockerUnderSingIn(AuthUser authUser, Message message) throws BaseException;
	/**
	 * 管理员在鞋柜上刷卡根据设定规则开门
	* @Title: OpenLockerByAdmin
	* @author Ning 
	* @data:2017年2月22日
	* @return:Message
	* @throws:
	 */
	public Message OpenLockerByAdmin(AuthUser authUser, Message message) throws BaseException;
	/**
	 * 打开鞋柜记录日志
	 * @author xujialong
	 * @param deviceNumber
	 * @param numbers
	 * @param sessionUser
	 * @return
	 * @throws BaseException
	 */
	public int openContainerShoesLog(String deviceNumber,String[] numbers,SessionUser sessionUser) throws BaseException;
	
}
