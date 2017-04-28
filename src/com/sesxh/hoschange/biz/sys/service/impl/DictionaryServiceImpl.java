package com.sesxh.hoschange.biz.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.bpo.DictionaryBpo;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;
import com.sesxh.hoschange.biz.sys.service.DictionaryService;

@Service
public class DictionaryServiceImpl extends SesframeBaseService implements DictionaryService {

	@Autowired
	private DictionaryBpo dictionaryBpo;

	public String getDictByCode(String type) throws BaseException {
		return dictionaryBpo.getDictByCode(type);
	}
	
	public Dictionary loadDictByTypeAndCode(String type,String code) throws BaseException {
		return dictionaryBpo.loadDictByTypeAndCode(type, code);
	}
}
