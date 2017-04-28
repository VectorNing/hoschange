package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosClothesPressBpo;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.hos.service.HosClothesPressService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.Message;
@Service
public class HosClothesPressServiceImp extends SesframeBaseService implements HosClothesPressService {
	@Autowired
	private HosClothesPressBpo hosClothesPressBpo;
	@Override
	@Transactional
	public Map<String, Object> queryClothesPressAll(Map<String, Object> params) throws BaseException {
		return hosClothesPressBpo.queryClothesPressAll(params);
	}
	@Override
	@Transactional
	public int addClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		return hosClothesPressBpo.addClothesPress(hosClothesPress);
	}
	@Override
	@Transactional
	public HosClothesPress queryClothesPressById(String id) throws BaseException{
		return hosClothesPressBpo.queryClothesPressById(id);
	}
	@Override
	@Transactional
	public int updateHosClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		return hosClothesPressBpo.updateHosClothesPress(hosClothesPress);
	}
	@Override
	@Transactional
	public Long selectClothesFromClothesPressById(String id) throws BaseException {
		return hosClothesPressBpo.selectClothesFromClothesPressById(id);
	}
	@Override
	@Transactional
	public int deleteClothesPressById(String id) throws BaseException {
		return hosClothesPressBpo.deleteClothesPressById(id);
	}
	@Override
	@Transactional
	public Map<String, Object> selectClothesPressByUserIdAndNumber(Integer userId, String ClothesPressNum) throws BaseException {
		return hosClothesPressBpo.selectClothesPressByUserIdAndNumber(userId, ClothesPressNum);
	}
	@Override
	@Transactional
	public Long selectClothesPressByNumber(String id, String number) throws BaseException {
		return hosClothesPressBpo.selectClothesPressByNumber(id, number);
	}
	@Override
	@Transactional
	public Map<String, Object> selectOpeByCardNumAndNumber(String number, String cardNum) throws BaseException {
		return hosClothesPressBpo.selectOpeByCardNumAndNumber(number, cardNum);
	}
	@Override
	@Transactional
	public List<Map<String,Object>> selectClothesPressBySteNumber(String steNumber) throws BaseException {
		return hosClothesPressBpo.selectClothesPressBySteNumber(steNumber);
	}
	@Override
	@Transactional
	public int updateLockClothesPress(Integer[] ids) throws BaseException {
		return hosClothesPressBpo.updateLockClothesPress(ids);
	}
	@Override
	@Transactional
	public int updateStoplockClothesPress(Integer[] ids) throws BaseException {
		return hosClothesPressBpo.updateStoplockClothesPress(ids);
	}
	@Override
	@Transactional
	public int updateBindingClothesPressAndUser(Integer id, Integer userId) throws BaseException {
		return hosClothesPressBpo.updateBindingClothesPressAndUser(id, userId);
	}
	@Override
	@Transactional
	public int updateStopBindingClothesPressAndUser(Integer id) throws BaseException {
		return hosClothesPressBpo.updateStopBindingClothesPressAndUser(id);
	}
	@Override
	@Transactional
	public Message openClothesPressContainerByUser(AuthUser authUser, Message message) throws BaseException {
		return hosClothesPressBpo.openClothesPressContainerByUser(authUser, message);
	}
	@Override
	public int openContainerClothesPressLog(String deviceNumber, String[] numbers, SessionUser sessionUser)
			throws BaseException {
		return hosClothesPressBpo.openContainerClothesPressLog(deviceNumber, numbers, sessionUser);
	}
	@Override
	public Object getDoorIdByDeviceId(String deviceNumber) throws BaseException {
		return hosClothesPressBpo.getDoorIdByDeviceId(deviceNumber);
	}
}
