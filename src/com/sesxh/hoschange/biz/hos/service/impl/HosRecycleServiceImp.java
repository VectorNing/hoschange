package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosRecordBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecycleBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.service.HosRecycleService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.RecycleBinData;

/**
 * 
 * @author Ning
 *
 */
@Service
public class HosRecycleServiceImp extends SesframeBaseService implements HosRecycleService {
	@Autowired
	private HosRecycleBpo hosRecycleBpo;
	@Autowired
	private HosRecoveryGoodsBpo hosRecoveryGoodsBpo;
	@Autowired
	private HosRecordBpo hosRecordBpo;

	@Override
	@Transactional
	public HosRecycle selectRecycleByNumber(String number, Integer id) throws BaseException {
		return hosRecycleBpo.selectRecycleByNumber(number, id);
	}

	@Override
	@Transactional
	public int insertRecycle(HosRecycle hosRecycle) throws BaseException {
		return hosRecycleBpo.insertRecycle(hosRecycle);
	}

	@Override
	@Transactional
	public DataSet queryHosRecycleSet(Map<String, Object> params) throws BaseException {
		return hosRecycleBpo.queryHosRecycleSet(params);
	}

	@Override
	@Transactional
	public HosRecycle queryHosrecycleById(Integer id) throws BaseException {
		return hosRecycleBpo.queryHosrecycleById(id);
	}

	@Override
	@Transactional
	public int updateHosRecycle(HosRecycle hosRecycle) throws BaseException {
		return hosRecycleBpo.updateHosRecycle(hosRecycle);
	}

	@Override
	@Transactional
	public Long selectRecycleFromRecycleByNumber(String number) throws BaseException {
		return hosRecycleBpo.selectRecycleFromRecycleByNumber(number);
	}

	@Override
	@Transactional
	public int deleteHosRecycle(Integer id) throws BaseException {
		return hosRecycleBpo.deleteHosRecycle(id);
	}

	@Override
	@Transactional
	public int updateHosRecycleAndHosRecordAndHosRecovery(String number, String cardNum, String state)
			throws BaseException {
		int flag = hosRecoveryGoodsBpo.updateRecoveryGoodsByCardNumAndState(cardNum, number, state);
		if (flag == 1) {
			hosRecycleBpo.updateHosRecycleCountByNumberAndCardNum(number, cardNum);
		}
		return 0;
	}

	@Override
	@Transactional
	public int updateHosRecycleAndHosRecordAndHosRecovery(Integer recordId, String number, String cardNum, String state)
			throws BaseException {
		hosRecordBpo.updateSignOutRecovery(recordId, cardNum, state);
		hosRecycleBpo.updateHosRecycleCountByNumberAndCardNum(number, cardNum);
		return 0;
	}

	@Override
	@Transactional
	public int emptyRecycle(String recycleNum) throws BaseException {
		return hosRecycleBpo.emptyRecycle(recycleNum);
	}

	@Override
	@Transactional
	public DataSet queryHosRecycleMonitored(Map<String, Object> params) throws BaseException {
		return hosRecycleBpo.queryHosRecycleMonitored(params);
	}

	@Override
	@Transactional
	public Message openRecycleDoor(AuthUser authUser, Message message, HosRecord hosRecord) throws BaseException {
		return hosRecycleBpo.openRecycleDoor(authUser, message, hosRecord);
	}

	@Override
	@Transactional
	public Message RecyclingBinData(RecycleBinData recycleBinData) throws BaseException {
		return hosRecycleBpo.RecyclingBinData(recycleBinData);

	}

	@Override
	public Message RecyclingBinImage(RecycleBinData recycleBinData) throws BaseException {
		return hosRecycleBpo.RecyclingBinImage(recycleBinData);
	}

}
