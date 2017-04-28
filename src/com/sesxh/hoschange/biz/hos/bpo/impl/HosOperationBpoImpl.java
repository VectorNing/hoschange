package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosOperationBpo;
import com.sesxh.hoschange.biz.hos.dao.HosOperationDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosOperationBpoImpl")
public class HosOperationBpoImpl extends SesframeBaseBpo implements HosOperationBpo {

	@Autowired
	private HosOperationDao hosOperationDao;
	@Autowired
	private HosWardrobeContainerDao hosWardrobeContainerDao;
	@Autowired
	private HosWardrobeDao hosWardrobeDao;

	@Override
	public int insertHosOperationM1(HosOperation hosOperation) throws BaseException {
		int flag = 0;
		validateHosOperationM1(hosOperation);
		String clothSize = hosOperation.getClothSize();
		String theNumber = hosOperation.getTheNumber();
		HosOperation hosOp = hosOperationDao.selectHosOperationByClothSizeAndtheNumber(clothSize, theNumber);

		if (hosOp != null) {
			hosOp.setCount(hosOperation.getCount() + hosOp.getCount());
			flag = hosOperationDao.updateHosOperation(hosOp);
		} else {
			flag = hosOperationDao.insertHosOperation(hosOperation);
		}

		return flag;
	}

	@Override
	public int[] bacthInsertHosOperation(HosOperation hosOperation) throws BaseException {
		int[] flag = null;

		validateHosOperation(hosOperation, "");
		List<HosOperation> list = Lists.newArrayList();
		if (hosOperation != null) {
			int count = hosOperation.getCount();
			for (int i = 0; i < count; i++) {
				HosOperation ho = new HosOperation();
				ho.setClothSize(hosOperation.getClothSize());
				ho.setNumber(hosOperation.getNumber());
				list.add(ho);
			}
			flag = hosOperationDao.bacthInsertHosOperation(list);
		}
		return flag;
	}

	@Override
	public int deleteHosOperationById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		HosOperation hosOperation = hosOperationDao.selectHosOperationById(id);
		if (hosOperation == null) {
			ZAlert.Error("未找到该手术衣信息，请查证！");
		}
		hosWardrobeContainerDao.updateContainerByClothSizeAndtheNumber(hosOperation.getClothSize(),
				hosOperation.getTheNumber());
		return hosOperationDao.deleteHosOperationById(id);
	}

	@Override
	public int updateHosOperationM1(HosOperation hosOperation) throws BaseException {
		// 校验数据
		validateHosOperationM1(hosOperation);
		int count = hosOperation.getCount();
		if (count == 0)
			return hosOperationDao.deleteHosOperationById(hosOperation.getId());
		else
			return hosOperationDao.updateHosOperation(hosOperation);
	}

	@Override
	public DataSet queryHosOperationSet(Map<String, Object> params) throws BaseException {

		ParamMap pm = ParamMap.filterParam(params);
		String clothSize = pm.getStrParam("clothSize");
		String number = pm.getStrParam("number");
		if (clothSize != null) {
			pm.updateParam("clothSize", clothSize);
		}
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		DataSet ds = DataSet.newDS(hosOperationDao.hosOperationCount(pm), hosOperationDao.selectAllHosOperation(pm));
		return ds;
	}

	@Override
	public List<Map<String, Object>> selectHosOperationLists(Map<String, Object> params) throws BaseException {

		ParamMap pm = ParamMap.filterParam(params);

		String listRoomNumber = pm.getStrParam("listRoomNumber");
		ZAssert.hasText(listRoomNumber, "没有找到您管辖的手术室");
		pm.updateParam("listRoom", listRoomNumber);
		return hosOperationDao.selectHosOperationLists(params);
	}

	@Override
	public HosOperation selectHosOperationById(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return hosOperationDao.selectHosOperationById(id);
	}

	@Override
	public int[] updateHosOperationNum(HosOperation hosOperation) throws BaseException {
		int flag[] = null;
		// 校验数据
		validateHosOperation(hosOperation, "update");

		int count = hosOperation.getCount();// 原始总数量
		int updateCount = Integer.parseInt(hosOperation.getUpdateCount());// 修改后的数量
		int notAllocatedCount = Integer.parseInt(hosOperation.getNotAllocatedCount());// 已分配的数量

		// 删除
		long start = System.currentTimeMillis();
		if (count > updateCount) {
			if (updateCount < notAllocatedCount) {
				ZAlert.Error("修改数量不能小于已分配数量");
			} else {
				int nowCount = updateCount - notAllocatedCount;

				List<HosOperation> list = Lists.newArrayList();

				Integer size = Integer.parseInt(hosOperation.getClothSize());
				int a = hosOperationDao.deleteHosOperationByClothSize(size, 0);
				System.err.println("count:" + a);
				for (int i = 0; i < nowCount; i++) {
					HosOperation ho = new HosOperation();
					ho.setClothSize(hosOperation.getClothSize());
					ho.setNumber(hosOperation.getNumber());
					list.add(ho);
					hosOperationDao.insertHosOperation(hosOperation);
				}

			}

			// 新增
		} else if (count < updateCount) {
			int nowCount = updateCount - count;
			List<HosOperation> list = Lists.newArrayList();
			for (int i = 0; i < nowCount; i++) {
				HosOperation ho = new HosOperation();
				ho.setClothSize(hosOperation.getClothSize());
				ho.setNumber(hosOperation.getNumber());
				list.add(ho);
			}
			flag = hosOperationDao.bacthInsertHosOperation(list);
		}
		long end = System.currentTimeMillis();
		System.out.println("bpo:" + (end - start));
		return flag;
	}

	@Override
	public int findHosOperationNumById(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteHosOperationByClothSize(Integer clothSize) throws BaseException {
		// TODO Auto-generated method stub
		return hosOperationDao.deleteHosOperationByClothSize(clothSize, null);
	}

	private void validateHosOperationM1(HosOperation hosOperation) throws BusinessException {
		ZAssert.isNumber(hosOperation.getClothSize(), "尺码不能为空且只能输入数字!");
		ZAssert.isNumber(String.valueOf(hosOperation.getCount()), "数量只能输入数字!");
		ZAssert.lenLessTan(String.valueOf(hosOperation.getCount()), 11, "输入的数量值太大!");
	}

	/**
	 * 
	 * @param hosOperation
	 *            手术服对象
	 * @param strategy
	 *            模式种类
	 * @param method
	 *            方法参数
	 * @throws BusinessException
	 */
	private void validateHosOperation(HosOperation hosOperation, String method) throws BusinessException {

		ZAssert.isNumber(String.valueOf(hosOperation.getCount()), "数量只能输入数字!");
		ZAssert.lenLessTan(String.valueOf(hosOperation.getCount()), 11, "输入的数量值太大!");
		// 模式一修改
		if ("update".equals(method)) {
			ZAssert.isNumber(hosOperation.getUpdateCount(), "数量只能输入数字!");
			ZAssert.lenLessTan(hosOperation.getUpdateCount(), 11, "输入的数量值太大!");
		}
	}

	@Override
	public HosOperation findHosOperationByClothSize(String clothSize) throws BaseException {
		return hosOperationDao.selectHosOperationByClothSize(clothSize);
	}

	@Override
	public List<Map<String, Object>> queryHosOperationList(String warNumber) throws BaseException {
		// TODO Auto-generated method stub
		String theNumber = hosWardrobeDao.selectHosWardrobeEnabledByNumber(warNumber).getTheaterNumber();
		return hosOperationDao.selectHosOperationList(theNumber);
	}

	public List<ClassConvertDict> selectHosOperation() throws BaseException {
		return hosOperationDao.selectHosOperation();
	}

}
