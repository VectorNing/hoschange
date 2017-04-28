package com.sesxh.hoschange.biz.sys.bpo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.sys.bpo.DictionaryBpo;
import com.sesxh.hoschange.biz.sys.dao.DictionaryDao;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;

@Component("com.sesxh.hoschange.biz.sys.bpo.impl.DictionaryBpoImpl")
public class DictionaryBpoImpl extends SesframeBaseBpo implements DictionaryBpo {

	@Autowired
	private DictionaryDao dictionaryDao;
	
	public String getDictByCode(String type) throws BaseException {
		if(!StringUtils.hasText(type)){
			return null;
		}
		List<Map<String,Object>> lists = dictionaryDao.selectDictByCode(type);
		return JSON.toJSONString(lists);
	}
	
	public Dictionary loadDictByTypeAndCode(String type,String code) throws BaseException {
		if(!StringUtils.hasText(type)||!StringUtils.hasText(code)){
			return null;
		} 
		return dictionaryDao.selectDictByTypeAndCode(type, code);
	}
}
