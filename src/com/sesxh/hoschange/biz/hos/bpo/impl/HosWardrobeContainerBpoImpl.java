package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosWardrobeContainerBpo;
import com.sesxh.hoschange.biz.hos.dao.HosOperationDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeContainerDao;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosWardrobeContainerBpoImpl")
public class HosWardrobeContainerBpoImpl extends SesframeBaseBpo implements HosWardrobeContainerBpo {

	@Autowired
	private HosWardrobeContainerDao hosWardrobeContainerDao;

	@Autowired
	private HosOperationDao hosOperationDao;

	@Override
	public List<HosWardrobeContainer> selectHosWardrobeContainerByWarNumber(String warNumber) throws BaseException {
		ZAssert.hasText(warNumber, "编号不能为空！");
		return hosWardrobeContainerDao.selectHosWardrobeContainerByWarNumber(warNumber);
	}

	@Override
	public int allotHosOperationToContainer(String trayNumber, Integer alloutCount, String opeSize)
			throws BaseException {
		HosWardrobeContainer hwl = hosWardrobeContainerDao.selectContainerById(Integer.valueOf(trayNumber));
		int size = hwl.getOpeSize();
		if (size != 0) {
			if (!opeSize.equals("" + size)) {
				ZAlert.Error("每个托盘只能分配相同尺码的手术服");
			}
			if ((alloutCount + hwl.getAlloutCount()) > hwl.getTrayTotal()) {
				ZAlert.Error("每个托盘分配的数量不能大于该托盘的容量");
			}
		}
		HosOperation hosOperation = hosOperationDao.selectHosOperationByClothSize(opeSize);
		if (hosOperation != null) {
			hosOperationDao.updateHosOperation(hosOperation);
			alloutCount = hwl.getAlloutCount() + alloutCount;
			hosWardrobeContainerDao.updateContainerById(trayNumber, alloutCount, opeSize);
		}
		return 0;
	}

	@Override
	public int emptyContainer(Integer[] ids) throws BaseException {
		ZAssert.hasText(ids, "小柜编号不能为空");
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				HosWardrobeContainer hosWardrobeContainer = hosWardrobeContainerDao.selectContainerById(id);
				int clothSize = hosWardrobeContainer.getOpeSize();
				int count = hosWardrobeContainer.getAlloutCount();
				if (clothSize > 0) {
					if (count > 0) {
						HosOperation hosOperation = hosOperationDao.selectHosOperationByClothSize("" + clothSize);
						hosOperationDao.updateHosOperation(hosOperation);
					}
					hosWardrobeContainerDao.emptyContainerById(id);
				}
			}

		}
		return 0;
	}

	public int emptyAllContainer(String number) throws BaseException {
		int flag = 0;
		ZAssert.hasText(number, "编号不能为空，请查证！");
		List<Map<String, Object>> cont = hosWardrobeContainerDao.selectContainerByNumber(number);
		if (cont == null || cont.size() <= 0) {
			ZAlert.Error("请选择要清空的手术衣柜");
		}
		for (Map<String, Object> map : cont) {
			Object co = map.get("id");
			Integer id = Integer.parseInt(co.toString());
			HosWardrobeContainer hosWardrobeContainer = hosWardrobeContainerDao.selectContainerById(id);
			int clothSize = hosWardrobeContainer.getOpeSize();
			int count = hosWardrobeContainer.getAlloutCount();
			if (clothSize > 0) {
				if (count > 0) {
					HosOperation hosOperation = hosOperationDao.selectHosOperationByClothSize("" + clothSize);
					hosOperationDao.updateHosOperation(hosOperation);
				}
				hosWardrobeContainerDao.emptyContainerById(id);
			}
		}
		return flag;
	}

	@Override
	public int settingOperationSizeForContainer(Integer[] ids, String opeSize) throws BaseException {
		if ("".equals(opeSize) || opeSize == null || opeSize.isEmpty()) {
			ZAlert.Error("请选择要指定的尺码,请检查");
		}
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				HosWardrobeContainer container = hosWardrobeContainerDao.selectContainerById(id);
				Integer alloutCount = container.getAlloutCount();// 已分配数量
				if (alloutCount != null && alloutCount > 0)
					ZAlert.Error("编号:" + container.getTrayNumber() + "的托盘还有衣物不能进行设定尺码,请先清空托盘!");
				else
					hosWardrobeContainerDao.settingOperationSizeForContainer(id, opeSize);
			}
		} else {
			ZAlert.Error("请选择要设定的托盘,请检查");
		}
		return 0;
	}

	@Override
	public int allotOperationToContainerForZD(String jsonArray) throws BaseException {
		// String jsonArray =
		// "[{\"id\":\"0377\",\"size\":\"1\",\"count\":\"10\"},{\"id\":\"0377\",\"size\":\"2\",\"count\":\"20\"}]";
		int flag = 0;
		// HosWardrobe hosWardrobe = new HosWardrobe();
		JSONArray json = JSON.parseArray(jsonArray);
		if (jsonArray == null || jsonArray.isEmpty() || json.size() <= 0) {
			ZAlert.Error("请选择分配的数量");
		}
		Iterator<Object> it = json.iterator();
		while (it.hasNext()) {
			JSONObject sObj = (JSONObject) it.next();
			Integer id = Integer.parseInt((String) sObj.get("id"));
			Integer count = Integer.parseInt((String) sObj.get("count"));
			// 更新托盘中数量
			flag = hosWardrobeContainerDao.updateAlloutCount(id, count);
		}
		return flag;
	}

	public int emptyContainerCloth(String[] ids) throws BaseException {
		int flag = 0;
		ZAssert.hasText(ids, "小柜编号不能为空");
		if (ids != null && ids.length > 0) {
			for (String idStr : ids) {
				Integer id = Integer.parseInt(idStr);
				HosWardrobeContainer hosWardrobeContainer = hosWardrobeContainerDao.selectContainerById(id);
				int clothSize = hosWardrobeContainer.getOpeSize();
				if (clothSize > 0) {
					hosWardrobeContainerDao.emptyContainerCountById(id);
				}
			}

		}
		return flag;
	}

	public int emptyAllContainerCloth(String number) throws BaseException {
		int flag = 0;
		ZAssert.hasText(number, "编号不能为空，请查证！");
		List<Map<String, Object>> cont = hosWardrobeContainerDao.selectContainerByNumber(number);
		if (cont == null || cont.size() <= 0) {
			ZAlert.Error("请选择要清空的手术衣柜");
		}
		for (Map<String, Object> map : cont) {
			Object co = map.get("id");
			Integer id = Integer.parseInt(co.toString());
			HosWardrobeContainer hosWardrobeContainer = hosWardrobeContainerDao.selectContainerById(id);
			int clothSize = hosWardrobeContainer.getOpeSize();
			if (clothSize > 0) {
				hosWardrobeContainerDao.emptyContainerCountById(id);
			}
		}
		return flag;
	}
}
