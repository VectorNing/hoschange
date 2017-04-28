package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosOperationBpo;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.hos.service.HosOperationService;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class HosOperationServiceImpl extends SesframeBaseService implements HosOperationService {

	@Autowired
	private HosOperationBpo hosOperationBpo;

	@Override
	@Transactional
	public int insertHosOperationM1(HosOperation hosOperation) throws BaseException {
		return hosOperationBpo.insertHosOperationM1(hosOperation);
	}
	@Override
	@Transactional
	public int[] bacthInsertHosOperation(HosOperation hosOperation) throws BaseException,DataAccessException{
		return hosOperationBpo.bacthInsertHosOperation(hosOperation);
	}
	@Override
	@Transactional
	public int deleteHosOperationById(Integer id) throws BaseException {
		return hosOperationBpo.deleteHosOperationById(id);
	}

	@Override
	@Transactional
	public int updateHosOperationM1(HosOperation hosOperation) throws BaseException {
		return hosOperationBpo.updateHosOperationM1(hosOperation);
	}

	@Override
	@Transactional
	public DataSet selectHosOperationSet(Map<String, Object> params) throws BaseException {
		return hosOperationBpo.queryHosOperationSet(params);
	}

	@Override
	@Transactional
	public HosOperation selectHosOperationById(Integer id) throws BaseException {
		return hosOperationBpo.selectHosOperationById(id);
	}

	@Override
	@Transactional
	public int[] updateHosOperationNumM1(HosOperation hosOperation) throws BaseException {
		return hosOperationBpo.updateHosOperationNum(hosOperation);
	}

	@Override
	@Transactional
	public int findHosOperationNumById(Integer id) throws BaseException {
		return hosOperationBpo.findHosOperationNumById(id);
	}

	@Override
	@Transactional
	public int deleteHosOperationByClothSize(Integer clothSize) throws BaseException {
		return hosOperationBpo.deleteHosOperationByClothSize(clothSize);
	}
	@Override
	@Transactional
	public HosOperation findHosOperationByClothSize(String clothSize) throws BaseException{
		return hosOperationBpo.findHosOperationByClothSize(clothSize);
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> queryHosOperationList(String warNumber) throws BaseException {
		return hosOperationBpo.queryHosOperationList(warNumber);
	}

	@Override
	@Transactional
	public List<ClassConvertDict> selectHosOperation() throws BaseException{
		return hosOperationBpo.selectHosOperation();
	}
	
	@Override
	@Transactional
	public List<Map<String,Object>> selectHosOperationLists(Map<String,Object> params)throws BaseException  {
		return hosOperationBpo.selectHosOperationLists(params);
	}
}
