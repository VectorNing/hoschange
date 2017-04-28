package com.sesxh.hoschange.biz.hos.bpo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRoomBpo;
import com.sesxh.hoschange.biz.hos.dao.HosRoomDao;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosRoomBpoImp")
public class HosRoomBpoImp extends SesframeBaseBpo implements HosRoomBpo {
	@Autowired
	private HosRoomDao hosRoomDao;
	@Override
	public int addHosRoom(HosRoom hosRoom) throws BaseException {
		return hosRoomDao.addHosRoom(hosRoom);
	}
	@Override
	public HosRoom selectHosRoomByRoomId(Integer roomId) throws BaseException {
		return hosRoomDao.selectHosRoomByRoomId(roomId);
	}

}
