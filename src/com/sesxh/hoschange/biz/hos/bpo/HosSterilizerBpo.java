package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;

public interface HosSterilizerBpo {
	
	/**
	 * 新增消毒鞋柜
	 * @param hosSterilizer
	 * @return
	 * @throws BaseException
	 */
	public int insertHosSterilizer(HosSterilizer hosSterilizer) throws BaseException;

	/**
	 * 删除消毒鞋柜
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosSterilizerById(Integer id) throws BaseException;

	/**
	 * 修改消毒鞋柜
	 * @param hosSterilizer
	 * @return
	 * @throws BaseException
	 */
	public int updateHosSterilizer(HosSterilizer hosSterilizer) throws BaseException;

	/**
	 * 查询消毒鞋柜数据
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet queryHosSterilizerSet(Map<String,Object> params)throws BaseException;
	
	/**
	 * 分配消毒鞋到鞋柜
	 * @return
	 * @throws BaseException
	 */
	public void allotShoesToSterilizer(List<Map<String, Object>> list) throws BaseException;
	
	/**
	 * 查询相应消毒鞋柜
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizer findHosShoesSterilizerById(Integer id) throws BaseException;
	
	/**
	 * 根据消毒柜(编号)查手术鞋 的 数量
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public Long selectShoesFromSterilizerByNumber(String number) throws BaseException ;
	
	/**
	 * 加载手术室 消毒柜
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet loadSterilizerListByTheNumber(@RequestParam Map<String,Object> params ) throws BaseException ; 
	
	/**
	 * 分配手术 消毒柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int assignTheaterToSte(String number, Integer [] ids) throws BaseException ;
	
	/**
	 * 移除手术 消毒柜
	 * @param id
	 * @param numbers
	 * @return
	 * @throws BaseException
	 */
	public int removeSteFromTheater(String number,Integer [] ids) throws BaseException ;
	
	/**
	 * 根据手术室编号 查询消毒鞋柜信息
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public List<HosSterilizer> loadSterilizerByThNumber(String number) throws BaseException ;
	
	/**
	 * 根据消毒柜编号查询 条数
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public Long selectSteByNumber(String number,Integer id) throws BaseException;
	
	
	/**
	 * 衣物管理员 加载所属手术区的消毒鞋列表
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	public DataSet selectHosSterilizerMonitored(Map<String,Object> params)throws BaseException ;
	/**
	 * 根据鞋柜编号加载鞋柜信息
	 * @author Ning
	 * @param SteNumber
	 * @return
	 * @throws BaseException
	 */
	public HosSterilizer selectHosSterilizerBySteNumber(String SteNumber) throws BaseException;
	/**
	 * 固定分配
	* @Title: bindingReceiveShoeByUser
	* @author Ning 
	* @data:2017年2月22日
	* @return:Message
	* @throws:
	 */
	public Message bindingReceiveShoeByUser(AuthUser authUser, Message message) throws BaseException ;
	/**
	 * 随机分配
	* @Title: randomReceiveShoeByUser
	* @author Ning 
	* @data:2017年2月22日
	* @return:Message
	* @throws:
	 */
	public Message randomReceiveShoeByUser(AuthUser authUser, Message message) throws BaseException ;
}
