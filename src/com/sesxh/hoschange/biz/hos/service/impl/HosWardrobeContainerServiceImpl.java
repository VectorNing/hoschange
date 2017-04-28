package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosWardrobeContainerBpo;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;
import com.sesxh.hoschange.biz.hos.service.HosWardrobeContainerService;

@Service
public class HosWardrobeContainerServiceImpl extends SesframeBaseService implements HosWardrobeContainerService{

	@Autowired
	private HosWardrobeContainerBpo hosWardrobeContainerBpo;
	
	@Override
	@Transactional
	public List<HosWardrobeContainer> selectHosWardrobeContainerByWarNumber(String warNumber) throws BaseException {
		// TODO Auto-generated method stub
		return hosWardrobeContainerBpo.selectHosWardrobeContainerByWarNumber(warNumber);
	}


	@Override
	@Transactional
	public int allotHosOperationToContainer(String trayNumber, Integer alloutCount, String opeSize) throws BaseException {
		// TODO Auto-generated method stub
		return hosWardrobeContainerBpo.allotHosOperationToContainer(trayNumber, alloutCount, opeSize);
	}

	@Override
	@Transactional
	public int emptyContainer(Integer[] ids) throws BaseException {
		// TODO Auto-generated method stub
		return hosWardrobeContainerBpo.emptyContainer(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int emptyAllContainer(String number) throws BaseException{
		return hosWardrobeContainerBpo.emptyAllContainer(number);
	}

	@Override
	@Transactional
	public int settingOperationSizeForContainer(Integer[] ids, String opeSize) throws BaseException {
		// TODO Auto-generated method stub
		return hosWardrobeContainerBpo.settingOperationSizeForContainer(ids, opeSize);
	}

	@Override
	@Transactional
	public int allotOperationToContainerForZD(String jsonArray) throws BaseException {
		// TODO Auto-generated method stub
		return hosWardrobeContainerBpo.allotOperationToContainerForZD(jsonArray);
	}
	
	@Override
	@Transactional
	public int emptyContainerCloth(String[] ids) throws BaseException{
		return hosWardrobeContainerBpo.emptyContainerCloth(ids);
	}
	
	@Override
	@Transactional
	public int emptyAllContainerCloth(String number) throws BaseException{
		return hosWardrobeContainerBpo.emptyAllContainerCloth(number);
	}
}
