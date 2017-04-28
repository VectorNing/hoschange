package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosSterilizerContainerBpo;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.service.HosSterilizerContainerService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;

@Service
public class HosSterilizerContainerServiceImpl extends SesframeBaseService implements HosSterilizerContainerService {

	@Autowired
	private HosSterilizerContainerBpo hosSterilizerContainerBpo;

	@Override
	@Transactional
	public List<HosSterilizerContainer> selectSterilizerContainerBySteNumber(String steNumber) throws BaseException {
		return hosSterilizerContainerBpo.selectSterilizerContainerBySteNumber(steNumber);
	}

	@Override
	@Transactional
	public int allotShoesToContainer(Integer[] lockerNumbers, String shoesSize, int allotCount) throws BaseException {
		return hosSterilizerContainerBpo.allotShoesToContainer(lockerNumbers, shoesSize, allotCount);
	}

	@Override
	@Transactional
	public int allotShoesTwoToContainer(Integer[] lockerNumbers, Integer[] ids) throws BaseException {
		return hosSterilizerContainerBpo.allotShoesTwoToContainer(lockerNumbers, ids);
	}

	@Override
	@Transactional
	public int emptyContainer(Integer[] ids) throws BaseException {
		return hosSterilizerContainerBpo.emptyContainer(ids);
	}

	@Override
	@Transactional
	public int emptyAllContainer(String number) throws BaseException {
		return hosSterilizerContainerBpo.emptyAllContainer(number);
	}

	@Override
	@Transactional
	public Map<String, Object> findSteConNumberByUserIdAndThNumber(Integer id, String number) throws BaseException {
		return hosSterilizerContainerBpo.findSteConNumberByUserIdAndThNumber(id, number);
	}

	@Override
	@Transactional
	public int allotShoesToContainerForZD(Integer[] ids, String jsonArray) throws BaseException {
		return hosSterilizerContainerBpo.allotShoesToContainerForZD(ids, jsonArray);
	}

	@Override
	@Transactional
	public int allotSizeShoesToSterilizer(Integer[] ids, String shoesSize) throws BaseException {
		return hosSterilizerContainerBpo.allotSizeShoesToSterilizer(ids, shoesSize);
	}

	@Override
	@Transactional
	public Map<String, Object> selectShoesByUserIdAndThNumber(String cardNum, String number) throws BaseException {
		return hosSterilizerContainerBpo.selectShoesByUserIdAndThNumber(cardNum, number);
	}

	@Override
	@Transactional
	public int emptyContainerShoes(Integer[] ids) throws BaseException {
		return hosSterilizerContainerBpo.emptyContainerShoes(ids);
	}

	@Override
	@Transactional
	public int emptyContainerShoes2(Integer[] ids) throws BaseException {
		return hosSterilizerContainerBpo.emptyContainerShoes2(ids);
	}

	@Override
	@Transactional
	public HosSterilizerContainer selectSterilizerContainerById(Integer id) throws BaseException {
		return hosSterilizerContainerBpo.selectSterilizerContainerById(id);
	}

	@Override
	@Transactional
	public int lockSterilizerContainer(Integer[] ids) throws BaseException {
		return hosSterilizerContainerBpo.lockSterilizerContainer(ids);
	}

	@Override
	@Transactional
	public int stopLockSterilizer(Integer[] ids) throws BaseException {
		return hosSterilizerContainerBpo.stopLockSterilizer(ids);
	}

	@Override
	@Transactional
	public int bindingSterilizerAndUser(Integer id, Integer userId) throws BaseException {
		return hosSterilizerContainerBpo.bindingSterilizerAndUser(id, userId);
	}

	@Override
	@Transactional
	public int stopBindingSterilizerAndUser(Integer id) throws BaseException {
		return hosSterilizerContainerBpo.stopBindingSterilizerAndUser(id);
	}

	@Override
	@Transactional
	public DataSet selectAddShoeRule(Map<String, Object> params) throws BaseException {
		return hosSterilizerContainerBpo.selectAddShoeRule(params);
	}

	@Override
	@Transactional
	public int setUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException {
		return hosSterilizerContainerBpo.setUserAddShoeRule(code, userId, size);
	}

	@Override
	@Transactional
	public HosSterilizerContainer selectBindingSterilizerAndUser(Integer userId, String deviceNumber)
			throws BaseException {
		return hosSterilizerContainerBpo.selectBindingSterilizerAndUser(userId, deviceNumber);
	}

	@Override
	@Transactional
	public Message OpenLockerUnderSingIn(AuthUser authUser, Message message) throws BaseException {
		return hosSterilizerContainerBpo.OpenLockerUnderSingIn(authUser, message);
	}

	@Override
	@Transactional
	public Message OpenLockerByAdmin(AuthUser authUser, Message message) throws BaseException {
		return hosSterilizerContainerBpo.OpenLockerByAdmin(authUser, message);
	}

	@Override
	@Transactional
	public int openContainerShoesLog(String deviceNumber, String[] numbers, SessionUser sessionUser)
			throws BaseException {
		return hosSterilizerContainerBpo.openContainerShoesLog(deviceNumber, numbers, sessionUser);
	}

}
