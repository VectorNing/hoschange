package com.sesxh.hoschange.biz.hos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosRoomBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
import com.sesxh.hoschange.biz.hos.service.HosRoomService;
@Service
public class HosRoomServiceImp extends SesframeBaseService implements HosRoomService {
	@Autowired
	private HosRoomBpo hosRoomBpo;
	@Override
	@Transactional
	public int addHosRoom(HosRoom hosRoom) throws BaseException {
		return hosRoomBpo.addHosRoom(hosRoom);
	}
	@Override
	public HosRoom selectHosRoomByRoomId(Integer roomId) throws BaseException {
		return hosRoomBpo.selectHosRoomByRoomId(roomId);
	}

}
