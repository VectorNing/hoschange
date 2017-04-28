package com.sesxh.hoschange.biz.hos.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.tmg.core.SesTaskSlice;

@Service("com.sesxh.hoschange.biz.hos.service.TmgDeleteInvalidPower")
public class TmgDeleteInvalidPower extends SesframeBaseService implements SesTaskSlice {
	@Autowired
	HosPowerBpo hosPowerBpo;

	@Override
	public boolean execute(String paras) throws BaseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String advanceTime = formatter.format(calendar.getTime());
		 hosPowerBpo.deleteInvilidPower(advanceTime);
		return true;
	}
	
}
