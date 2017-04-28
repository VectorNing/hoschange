package com.sesxh.hoschange.biz.hos.service;

import java.util.List;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;

public interface HosWardrobeContainerService {
	/**
	 * 根据衣柜编号 查询托盘
	 * @param warNumber
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	public List<HosWardrobeContainer> selectHosWardrobeContainerByWarNumber(String warNumber) throws BaseException;
	/**
	 * 分配手术服到托盘
	 * @param clothSize
	 * @return
	 * @throws BaseException
	 */
	int allotHosOperationToContainer(String trayNumber, Integer alloutCount, String opeSize) throws BaseException;
	
	/**
	 * 清空托盘中衣物
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	int emptyContainer(Integer[] ids) throws BaseException;
	
	/**
	 * 清空全部托盘
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int emptyAllContainer(String number) throws BaseException;
	
	/**
	 * 设定托盘尺码
	 * @param ids 托盘id数组
	 * @param opeSize 衣服尺码
	 * @return
	 * @throws BaseException
	 */
	public int settingOperationSizeForContainer(Integer[] ids,String opeSize) throws BaseException;
	
	/**
	 * 给手术衣柜托盘分配衣服(终端)
	 * @param jsonArray 尺码 对应数量的 json数组 [{"id":"","size(尺码)":"","count(数量)":""}]
	 * @return
	 * @throws BaseException
	 */
	public int allotOperationToContainerForZD(String jsonArray) throws BaseException;
	
	/**
	 * 清空托盘中衣物 不清除尺码
	 * @param trayNumber
	 * @return
	 * @throws BaseException
	 */
	int emptyContainerCloth(String[] ids) throws BaseException;
	
	/**
	 * 清空全部托盘  不清除尺码
	 * @param number
	 * @return
	 * @throws BaseException
	 */
	public int emptyAllContainerCloth(String number) throws BaseException;
}
