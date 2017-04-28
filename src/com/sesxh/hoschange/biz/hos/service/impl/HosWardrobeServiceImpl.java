package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosWardrobeBpo;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeService;
import com.sesxh.hoschange.common.data.DataSet;

@Service
public class HosWardrobeServiceImpl extends SesframeBaseService implements HosWardrobeService{
	
	@Autowired
	private HosWardrobeBpo hosWardrobeBpo;
	
	@Override
	@Transactional
	public int insertHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		return hosWardrobeBpo.insertHosWardrobe(hosWardrobe);
	}

	@Override
	@Transactional
	public int deleteHosWardrobeByIdAndNumber(Integer id,String number) throws BaseException {
		return hosWardrobeBpo.deleteHosWardrobeByIdAndNumber(id,number);
	}

	@Override
	@Transactional
	public int updateHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		return hosWardrobeBpo.updateHosWardrobe(hosWardrobe);
	}

	@Override
	@Transactional
	public DataSet selectHosWardrobeSet(Map<String, Object> params) throws BaseException {
		return hosWardrobeBpo.queryHosWardrobeSet(params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public HosWardrobe selectHosWardrobeById(Integer id) throws BaseException {
		return hosWardrobeBpo.selectHosWardrobeById(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Long selectOperationFromWardBynumber(String number) throws BaseException {
		return hosWardrobeBpo.selectOperationFromWardBynumber(number);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet loadWardrobeListByTheNumber(@RequestParam Map<String,Object> params ) throws BaseException {
		return hosWardrobeBpo.loadWardrobeListByTheNumber(params);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
    public int assignTheaterToWar(String number, Integer [] ids) throws BaseException {
    	return hosWardrobeBpo.assignTheaterToWar(number, ids);
    }
 	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int removeWarFromTheater(String number,Integer [] ids) throws BaseException {
		return hosWardrobeBpo.removeWarFromTheater(number, ids);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertContainer(Map<String,Object> params)  throws BaseException {
		return hosWardrobeBpo.insertContainer(params);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Long selectWarByNumber(String number,Integer id) throws BaseException{
		return hosWardrobeBpo.selectWarByNumber(number, id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<HosWardrobe> loadWardrobeByThNumber(String number) throws BaseException {
		return hosWardrobeBpo.loadWardrobeByThNumber(number);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<HosWardrobe> loadWardrobeByWarNumber(String warNumber) throws BaseException {
		return hosWardrobeBpo.loadWardrobeByWarNumber(warNumber);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public HosWardrobe selectHosWardrobeEnabledByNumber(String warNumber)throws BaseException{
		return hosWardrobeBpo.selectHosWardrobeEnabledByNumber(warNumber);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateHosWardrobeEnabledByNumber(HosWardrobe hosWardrobe) throws BaseException{
		return hosWardrobeBpo.updateHosWardrobeEnabledByNumber(hosWardrobe);
	}
	
	@Override
	@Transactional
	public DataSet selectHosWardrobeMonitored(Map<String,Object> params)throws BaseException {
		return hosWardrobeBpo.selectHosWardrobeMonitored(params);
	}

	@Override
	@Transactional
	public HosWardrobe selectHosWardrobeByNumber(String number) throws BaseException {
		// TODO Auto-generated method stub
		return hosWardrobeBpo.selectHosWardrobeByNumber(number);
	}
}
