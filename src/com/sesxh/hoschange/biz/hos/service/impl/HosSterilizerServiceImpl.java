package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosSterilizerBpo;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;

@Service
public class HosSterilizerServiceImpl extends SesframeBaseService implements HosSterilizerService {

	@Autowired
	private HosSterilizerBpo hosSterilizerBpo;

	@Override
	@Transactional
	public int insertHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		return hosSterilizerBpo.insertHosSterilizer(hosSterilizer);
	}

	@Override
	@Transactional
	public int deleteHosSterilizerById(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return hosSterilizerBpo.deleteHosSterilizerById(id);
	}

	@Override
	@Transactional
	public int updateHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		// TODO Auto-generated method stub
		return hosSterilizerBpo.updateHosSterilizer(hosSterilizer);
	}

	@Override
	@Transactional
	public DataSet selectHosSterilizerSet(Map<String, Object> params) throws BaseException {
		// TODO Auto-generated method stub
		return hosSterilizerBpo.queryHosSterilizerSet(params);
	}

	@Override
	@Transactional
	public void allotShoesToSterilizer(List<Map<String, Object>> list) throws BaseException {
		// TODO Auto-generated method stub
		hosSterilizerBpo.allotShoesToSterilizer(list);
	}

	@Override
	@Transactional
	public HosSterilizer findHosShoesSterilizerById(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return hosSterilizerBpo.findHosShoesSterilizerById(id);
	}

	@Override
	@Transactional
	public Long selectShoesFromSterilizerByNumber(String number) throws BaseException {
		return hosSterilizerBpo.selectShoesFromSterilizerByNumber(number);
	}

	@Override
	@Transactional
	public DataSet loadSterilizerListByTheNumber(@RequestParam Map<String, Object> params) throws BaseException {
		return hosSterilizerBpo.loadSterilizerListByTheNumber(params);
	}

	@Override
	@Transactional
	public int assignTheaterToSte(String number, Integer[] ids) throws BaseException {
		return hosSterilizerBpo.assignTheaterToSte(number, ids);
	}

	@Override
	@Transactional
	public int removeSteFromTheater(String number, Integer[] ids) throws BaseException {
		return hosSterilizerBpo.removeSteFromTheater(number, ids);
	}

	@Override
	@Transactional
	public List<HosSterilizer> loadSterilizerByThNumber(String number) throws BaseException {
		return hosSterilizerBpo.loadSterilizerByThNumber(number);
	}

	@Override
	@Transactional
	public Long selectSteByNumber(String number, Integer id) throws BaseException {
		return hosSterilizerBpo.selectSteByNumber(number, id);
	}

	@Override
	@Transactional
	public DataSet selectHosSterilizerMonitored(Map<String, Object> params) throws BaseException {
		return hosSterilizerBpo.selectHosSterilizerMonitored(params);
	}

	@Override
	@Transactional
	public Message bindingReceiveShoeByUser(AuthUser authUser, Message message) throws BaseException {
		return hosSterilizerBpo.bindingReceiveShoeByUser(authUser, message);
	}

	@Override
	@Transactional
	public Message randomReceiveShoeByUser(AuthUser authUser, Message message) throws BaseException {
		return hosSterilizerBpo.randomReceiveShoeByUser(authUser, message);
	}

}
