package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosShoesBpo;
import com.sesxh.hoschange.biz.hos.dao.HosShoesDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerDao;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosShoesBpoImpl")
public class HosShoesBpoImpl extends SesframeBaseBpo implements HosShoesBpo {

	@Autowired
	private HosShoesDao hosShoesDao;
	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;
	@Autowired
	private HosSterilizerDao hosSterilizerDao;

	@Override
	public int insertHosShoesM1(HosShoes hosShoes) throws BaseException {
		int flag = 0;
		Integer shoeSize = hosShoes.getShoeSize();
		String theaterNumber = hosShoes.getTheaterNumber();
		validateShoesM1(hosShoes);
		HosShoes hos = hosShoesDao.selectHosShoesByShoeSizeAndTheaterNumber(shoeSize, theaterNumber);
		if (hos != null) {
			int count = hos.getCount();
			hos.setCount(count + hosShoes.getCount());
			flag = hosShoesDao.updateHosShoes(hos);
		} else {
			flag = hosShoesDao.insertHosShoes(hosShoes);
		}

		return flag;
	}

	@Override
	public int deleteHosShoesById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		HosSterilizerContainer hosSterilizerContainer = new HosSterilizerContainer();
		HosShoes hosShoes = hosShoesDao.selectHosShoesById(id);
		if (hosShoes == null) {
			ZAlert.Error("未找到该手术鞋信息，请查证！");
		}
		List<HosSterilizer> hosSterilizers = hosSterilizerDao.selectAllHosSterilizer(hosShoes.getTheaterNumber());
		for (HosSterilizer hosSterilizer : hosSterilizers) {
			hosSterilizerContainer.setSteNumber(hosSterilizer.getNumber());
			hosSterilizerContainer.setOldShoesSize(hosShoes.getShoeSize());
			hosSterilizerContainer.setState(BizConst.STATE.NOTUSE);
			hosSterilizerContainerDao.updateSterilizerContainer(hosSterilizerContainer);
		}
		return hosShoesDao.deleteHosShoesById(id);
	}

	@Override
	public int updateHosShoesM1(HosShoes hosShoes) throws BaseException {
		validateShoesM1(hosShoes);
		Integer count = hosShoes.getCount();// 新总数
		// 前端进行了校验，此处未进行校验
		if (count == 0)
			return hosShoesDao.deleteHosShoesById(hosShoes.getId());
		else
			return hosShoesDao.updateHosShoes(hosShoes);
	}

	@Override
	public List<Map<String, Object>> selectHosShoesList(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String shoeSize = pm.getStrParam("shoeSize");
		if (shoeSize != null) {
			pm.updateParam("shoeSize", shoeSize);
		}
		return hosShoesDao.selectHosShoesListByTheaterNumber(pm);
	}

	@Override
	public HosShoes findHosShoesById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosShoesDao.selectHosShoesById(id);
	}

	@Override
	public HosShoes findHosShoesByShoesSize(String shoesSize) throws BaseException {
		return hosShoesDao.selectHosShoesByShoesSize(shoesSize);
	}

	private void validateShoesM1(HosShoes hosShoes) throws BaseException {
		Integer shoeSize = hosShoes.getShoeSize();
		Integer count = hosShoes.getCount();
		ZAssert.lenLessTan(String.valueOf(shoeSize), 11, "手术鞋尺码长度输入不正确");
		ZAssert.lenLessTan(String.valueOf(count), 11, "手术鞋数量输入不正确");
	}

	@Override
	public List<HosShoes> selectHosShoesByTheaterNumber(String number) throws BaseException {
		List<HosShoes> hosShoesList = hosShoesDao.selectHosShoesByTheaterNumber(number);
		return hosShoesList;
	}

	@Override
	public List<HosShoes> loadShoeSizeBySteNumber(String SteNumber) throws BaseException {
		List<HosShoes> shoesList = new ArrayList<>();
		// 根据鞋柜编号查询出鞋柜详细信息
		HosSterilizer hosSterilizer = hosSterilizerDao.selectSterilizerByNumber(SteNumber);
		// 根据鞋柜所在区域查询出该区域鞋子分配详细信息
		if (hosSterilizer != null) {
			shoesList = hosShoesDao.selectHosShoesByTheaterNumber(hosSterilizer.getTheaterNumber());
		}
		return shoesList;
	}
}
