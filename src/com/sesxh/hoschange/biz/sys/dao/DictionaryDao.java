package com.sesxh.hoschange.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;

public interface DictionaryDao {

	public List<Map<String,Object>> selectDictByCode(String type) throws BaseException ;
	
	public Dictionary selectDictByTypeAndCode(String type,String code) throws BaseException;
}
