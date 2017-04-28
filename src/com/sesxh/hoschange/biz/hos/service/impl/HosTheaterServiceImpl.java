package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosClothesPressBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecordBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosTheaterBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosWardrobeBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.service.HosTheaterService;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.SIGN;

@Service
public class HosTheaterServiceImpl extends SesframeBaseService implements HosTheaterService {

	@Autowired
	private HosTheaterBpo hosTheaterBpo;
	@Autowired
	private HosRecoveryGoodsBpo hosRecoveryGoodsBpo;
	@Autowired
	private HosWardrobeBpo hosWardrobeBpo;
	@Autowired
	private HosClothesPressBpo hosClothesPressBpo;
	@Autowired
	private HosRecordBpo hosRecordBpo;
	@Autowired
	private HosPowerBpo hosPowerBpo;

	@Override
	@Transactional
	public int insertHosTheater(HosTheater hosTheater) throws BaseException {
		return hosTheaterBpo.insertHosTheater(hosTheater);
	}

	@Override
	@Transactional
	public int deleteHosTheaterById(Integer id, String number) throws BaseException {
		// TODO Auto-generated method stub
		return hosTheaterBpo.deleteHosTheaterById(id, number);
	}

	@Override
	@Transactional
	public int updateHosTheater(HosTheater hosTheater) throws BaseException {
		// TODO Auto-generated method stub
		return hosTheaterBpo.updateHosTheater(hosTheater);
	}

	@Override
	@Transactional
	public DataSet selectHosTheaterSet(Map<String, Object> params) throws BaseException {
		// TODO Auto-generated method stub
		return hosTheaterBpo.queryHosTheaterSet(params);
	}

	@Override
	@Transactional
	public HosTheater selectTheaterById(Integer id) throws BaseException {
		return hosTheaterBpo.selectTheaterById(id);
	}

	@Transactional
	public AuthUserDetail loadUserDetailById(Integer id) throws BaseException {
		return hosTheaterBpo.loadUserDetailById(id);
	}

	@Transactional
	public Map<String, Object> receiveDeviceShoByUserIdM1(Integer id, String number, String size) throws BaseException {
		return hosTheaterBpo.receiveDeviceShoByUserIdM1(id, number, size);
	}

	@Transactional
	public Long selectTheByNumber(String number, Integer id) throws BaseException {
		return hosTheaterBpo.selectTheByNumber(number, id);
	}

	@Transactional
	public Map<String, Object> receiveDeviceOpeByUserIdM1(Integer id, String number) throws BaseException {
		return hosTheaterBpo.receiveDeviceOpeByUserIdM1(id, number);
	}

	@Transactional
	public int receiveOpeByWarNumberM1(Map<String, Object> params) throws BaseException {
		return hosTheaterBpo.receiveOpeByWarNumberM1(params);
	}

	@Override
	@Transactional
	public int receiveShoesBySteNumberM1(Map<String, Object> params) throws BaseException {
		return hosTheaterBpo.receiveShoesBySteNumberM1(params);
	}

	@Override
	@Transactional
	public Map<String, Object> randomReceiveShoeByUserIdTheNumber(Integer userId, String theNumber, Integer mark)
			throws BaseException {
		if (mark == 1) {
			mark = hosRecoveryGoodsBpo.deleteHosRecoveryGoodsByUserIdAndType(userId, BizConst.DEVICE_TYPE.STERILIZER,
					theNumber);
		}
		return hosTheaterBpo.randomReceiveShoeByUserIdTheNumber(userId, theNumber, mark);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public Map<String,Object>
	 * randomReceiveShoeByUserIdTheNumberM1(Integer userId,String theNumber)
	 * throws BaseException { return
	 * hosTheaterBpo.randomReceiveShoeByUserIdTheNumberM1(userId, theNumber); }
	 */

	@Override
	@Transactional
	public Map<String, Object> randomReceiveOpeByUserIdTheNumberM1(Integer userId, String theNumber)
			throws BaseException {
		return hosTheaterBpo.randomReceiveOpeByUserIdTheNumberM1(userId, theNumber);
	}

	@Override
	@Transactional
	public int emptyTouchAllSteContainer(String number) throws BaseException {
		return hosTheaterBpo.emptyTouchAllSteContainer(number);
	}

	@Override
	@Transactional
	public int emptyTouchAllWarContainer(String number) throws BaseException {
		return hosTheaterBpo.emptyTouchAllWarContainer(number);
	}

	@Override
	@Transactional
	public Map<String, Object> randomReceiveOpeByUserIdWarNumber(Integer userId, String warNumber, Integer mark)
			throws BaseException {
		Map<String, Object> map = new HashMap<>();
		HosWardrobe hosWardrobe = hosWardrobeBpo.selectHosWardrobeByNumber(warNumber);
		String theaterNumber = hosWardrobe.getTheaterNumber();
		HosRecord hosRecord = hosRecordBpo.selectHosRecordLastByTheNumber(userId, theaterNumber, "");
		if (hosRecord == null || hosRecord.getSign().equals(SIGN.OUT)) {// 已签退
			map = hosTheaterBpo.randomReceiveOpeByUserIdWarNumber(userId, warNumber, mark);
			Integer roomNumber = hosWardrobe.getRoomId();
			map = hosClothesPressBpo.allotClothesPressContainer(roomNumber, userId, map);
		} else {// 已签到
			HosRecoveryGoods hosRecoveryGoods = hosRecoveryGoodsBpo.selectHosRecoveryGoodsByIdAndType(hosRecord.getId(),
					BizConst.GOODS_TYPE.CLOTHES);
			if (hosRecoveryGoods != null) {
				map.put("ErrorMessage", "您已经领取过手术衣，请勿重复领取");
				return map;
			}
			// 发衣
			map = hosTheaterBpo.randomReceiveOpeByUserIdWarNumber(userId, warNumber, mark);
			// 发衣无异常，分配存衣柜
			if (map.get("ErrorMessage") == null) {
				Integer roomNumber = hosWardrobe.getRoomId();
				map = hosClothesPressBpo.allotClothesPressContainer(roomNumber, userId, map);
			}
		}
		return map;
	}

	@Override
	@Transactional
	public List<ClassConvertDict> loadTheNumberConvertDict() throws BaseException {
		// TODO Auto-generated method stub
		return hosTheaterBpo.loadTheNumberConvertDict();
	}

	@Override
	@Transactional
	public DataSet loadTheaterUserByTheaterId(Map<String, Object> params) throws BaseException {
		return hosTheaterBpo.loadTheaterUserByTheaterId(params);
	}

	@Override
	@Transactional
	public int assignUserToTheater(Integer theaterId, List<Integer> userIds) throws BaseException {
		return hosTheaterBpo.assignUserToTheater(theaterId, userIds);
	}

	@Override
	@Transactional
	public int removeTheaterFromUser(Integer theaterId, List<Integer> userIds) throws BaseException {
		return hosTheaterBpo.removeTheaterFromUser(theaterId, userIds);
	}

	@Override
	@Transactional
	public boolean selectTheaterUserByNumberAndCardNum(String theNumber, String cardNum) throws BaseException {
		return hosTheaterBpo.selectTheaterUserByNumberAndCardNum(theNumber, cardNum);
	}

	@Override
	@Transactional
	public List<HosTheater> selectTheaterByUserId(Integer UserId) throws BaseException {
		return hosTheaterBpo.selectTheaterByUserId(UserId);
	}
}
