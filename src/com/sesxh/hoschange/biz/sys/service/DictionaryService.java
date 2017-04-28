package com.sesxh.hoschange.biz.sys.service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;

public interface DictionaryService {

	/**
	 * 加载数据字典
	 * @param type
	 * @return
	 * @throws BaseException
	 */
	public String getDictByCode(String type) throws BaseException ;
	
	/**
	 * 根据type code 查询详情
	 * @param type
	 * @param code
	 * @return
	 * @throws BaseException
	 */
	public Dictionary loadDictByTypeAndCode(String type,String code) throws BaseException ;
}
