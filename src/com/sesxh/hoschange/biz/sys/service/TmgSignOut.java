package com.sesxh.hoschange.biz.sys.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosClothesPressBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecordBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.sys.bpo.SysConfigBpo;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.biz.tmg.core.SesTaskSlice;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.SYSCONFIG;

@Service("com.sesxh.hoschange.biz.sys.service.TmgSignOut")
public class TmgSignOut extends SesframeBaseService implements SesTaskSlice {
	@Autowired
	HosRecordBpo hosRecordBpo;
	@Autowired
	HosClothesPressBpo hosClothesPressBpo;
	@Autowired
	HosRecoveryGoodsBpo hosRecoveryGoodsBpo;
	@Autowired
	SysConfigBpo sysConfigBpo;

	@Override
	public boolean execute(String paras) throws BaseException {

		Calendar calendar = Calendar.getInstance();
		SysConfig sysConfig = sysConfigBpo.querySysConfigByConfigName(SYSCONFIG.SINGOUT);
		Integer singOutTime = Integer.valueOf(sysConfig.getConfig());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - singOutTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(calendar.getTime());
		List<HosRecord> listHosRecord = hosRecordBpo.selectOvertimeNoSignOut(time);
		for (int i = 0; i < listHosRecord.size(); i++) {
			HosRecord hosRecord = new HosRecord();
			hosRecord = listHosRecord.get(i);
			int Recoverystate = 0;
			List<HosRecoveryGoods> recoveryGoods = hosRecoveryGoodsBpo.selectHosRecoveryGoodsById(hosRecord.getId());
			for (HosRecoveryGoods goods : recoveryGoods) {
				int num = Integer.parseInt(goods.getState());
				Recoverystate += num;
			}
			// 如果多次回收记录均成功回收，这一流程记录为回收成功
			if (Recoverystate >= recoveryGoods.size()) {
				hosRecord.setIsCallback("1");
				hosRecord.setCallbackTime(new Date());
				hosRecord.setSign(BizConst.SIGN.OUT);
			}
			hosRecordBpo.updateHosRecordService(hosRecord);
		}

		return true;
	}
}
