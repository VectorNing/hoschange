package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosShoesBpo;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.biz.hos.service.HosShoesService;

@Service
public class HosShoesServiceImpl extends SesframeBaseService implements HosShoesService {

	@Autowired
	private HosShoesBpo hosShoesBpo;

	@Override
	@Transactional
	public int insertHosShoesM1(HosShoes hosShoes) throws BaseException {
		return hosShoesBpo.insertHosShoesM1(hosShoes);
	}


	@Override
	@Transactional
	public int deleteHosShoesById(Integer id) throws BaseException {
		return hosShoesBpo.deleteHosShoesById(id);
	}

	@Override
	@Transactional
	public int updateHosShoesM1(HosShoes hosShoes) throws BaseException {
		return hosShoesBpo.updateHosShoesM1(hosShoes);
	}

	@Override
	@Transactional
	public List<Map<String, Object>> selectHosShoesList(Map<String, Object> params) throws BaseException {
		return hosShoesBpo.selectHosShoesList(params);
	}

	@Override
	@Transactional
	public HosShoes findHosShoesById(Integer id) throws BaseException {
		return hosShoesBpo.findHosShoesById(id);
	}

	@Override
	@Transactional
	public HosShoes findHosShoesByShoesSize(String shoesSize) throws BaseException {
		return hosShoesBpo.findHosShoesByShoesSize(shoesSize);
	}

	@Override
	@Transactional
	public List<HosShoes> selectHosShoesByTheaterNumber( String number) throws BaseException {
		List<HosShoes> hosShoesList = hosShoesBpo.selectHosShoesByTheaterNumber( number);
		return hosShoesList;
	}

	@Override
	@Transactional
	public List<HosShoes> loadShoeSizeBySteNumber(String SteNumber) throws BaseException {
		return hosShoesBpo.loadShoeSizeBySteNumber(SteNumber);
	}
}
