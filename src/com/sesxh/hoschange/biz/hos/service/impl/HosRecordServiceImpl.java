package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosBlacklistRuleBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecordBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.biz.sys.bpo.SysConfigBpo;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.global.BizConst;

@Service
public class HosRecordServiceImpl extends SesframeBaseService implements HosRecordService {

	@Autowired
	private HosRecordBpo hosRecordBpo;
	@Autowired
	private HosRecoveryGoodsBpo hosRecoveryGoodsBpo;
	@Autowired
	private HosBlacklistRuleBpo hosBlacklistRuleBpo;
	@Autowired
	private SysConfigBpo sysConfigBpo;
	
	@Override
	@Transactional
	public int insertHosRecordService(HosRecord hosRecord) throws BaseException {
		return hosRecordBpo.insertHosRecordService(hosRecord);
	}

	@Override
	@Transactional
	public int updateHosRecordService(HosRecord hosRecord) throws BaseException {
		return hosRecordBpo.updateHosRecordService(hosRecord);
	}

	@Override
	@Transactional
	public HosRecord selectHosRecordLastByUserId(Integer userId,String deviceNumber,String deviceType) throws BaseException {
		return hosRecordBpo.selectHosRecordLastByUserId(userId,deviceNumber,deviceType);
	}
	
	@Override
	@Transactional
	public HosRecord selectHosRecordLastByTheNumber(Integer userId,String theaterNumber,String deviceType) throws BaseException{
		return hosRecordBpo.selectHosRecordLastByTheNumber(userId, theaterNumber, deviceType);
	}
	
	@Override
	@Transactional
	public String selectHosRecordCountByUserId(Integer userId) throws BaseException {
		String bm = null;
		String rosterModel=hosBlacklistRuleBpo.selectEnabledHosBlacklistRule();
		if(rosterModel.equals(BizConst.BLACKLIST_MODEL.ONE)){
			bm = hosRecordBpo.selectHosRecordCountByUserIdM1(userId);
		}else if(rosterModel.equals(BizConst.BLACKLIST_MODEL.TWO)){
			bm = hosRecordBpo.selectHosRecordCountByUserIdM2(userId);
		}
		return bm;
	}

	@Override
	@Transactional
	public boolean judgeHosRecordForBrushCardTimes(Integer userId, String deviceNumber,String deviceType) throws BaseException {
		boolean times = hosRecordBpo.judgeHosRecordForBrushCardTimes(userId, deviceNumber,deviceType);
		if(times){
			return times;
		}
		String yesOrNoShoe = sysConfigBpo.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();//是否有鞋柜
		if(yesOrNoShoe.equals(BizConst.YESORNO.YES) && deviceType.equals(BizConst.DEVICE_TYPE.WARDRODE)){
			HosRecord hosRecord = hosRecordBpo.selectHosRecordLastByUserId(userId, deviceNumber, deviceType);
			HosRecoveryGoods hosRecoveryGoods = hosRecoveryGoodsBpo.selectHosRecoveryGoodsByIdAndType(hosRecord.getId(), deviceType);
			if(hosRecoveryGoods!=null && BizConst.SIGN.IN.equals(hosRecord.getSign())){
				//查询出的签到信息为签到且领取过手术衣
				times = true;
			}
		}
		return times;
	}
	
	@Override
	@Transactional
	public int updateSignOutRecovery(Integer recordId,String cardNum,String state) throws BaseException {
		return hosRecordBpo.updateSignOutRecovery(recordId, cardNum, state);
	}
	
	@Override
	@Transactional
	public DataSet selectHosRecordSet(Map<String,Object> params) throws BaseException {
		return hosRecordBpo.selectHosRecordSet(params);
	}
}
