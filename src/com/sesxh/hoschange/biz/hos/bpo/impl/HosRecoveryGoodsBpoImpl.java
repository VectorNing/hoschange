package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.dao.HosClothesPressDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecycleDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Image;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ImageUtils;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

/**
 * @title : HosRecoveryGoodsBpoImpl.java
 * @author 作者 E-mail: wwb
 * @date 创建时间：2016年10月21日 下午4:43:03
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosRecoveryGoodsBpoImpl")
public class HosRecoveryGoodsBpoImpl extends SesframeBaseBpo implements HosRecoveryGoodsBpo {

	@Autowired
	private HosRecoveryGoodsDao hosRecoveryGoodsDao;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private HosRecordDao hosRecordDao;
	@Autowired
	private HosWardrobeDao hosWardrobeDao;
	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;
	@Autowired
	private HosBusinessDao hosBusinessDao;
	@Autowired
	private HosRecycleDao hosRecycleDao;
	@Autowired
	private HosClothesPressDao hosClothesPressDao;

	@Override
	public DataSet queryHosRecoveryGoodsSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String type = pm.getStrParam("type");
		String state = pm.getStrParam("state");
		String name = pm.getStrParam("name");
		String userName = pm.getStrParam("userName");
		if (type != null) {
			pm.updateParam("type", type);
		}
		if (state != null) {
			pm.updateParam("state", state);
		}
		if (name != null) {
			pm.updateParam("name", "%" + name + "%");
		}
		if (userName != null) {
			pm.updateParam("userName", "%" + userName + "%");
		}
		DataSet ds = DataSet.newDS(hosRecoveryGoodsDao.HosRecoveryGoodsCount(pm),
				hosRecoveryGoodsDao.selectHosRecoveryGoods(pm));
		return ds;
	}

	public int updateHosRecoveryGoodsStateByUserId(Integer userId) throws BaseException {
		ZAssert.hasText(userId, "没有获取到用户信息！");
		return 1;
	}

	public int updateRecoveryGoodsByCardNumAndState(String cardNum, String recycleNum, String state)
			throws BaseException {
		AuthUser authUser = authUserDao.findByLoginName2(cardNum);
		if (authUser == null) {
			ZAlert.Error("没有获取到用户信息");
		}
		Integer userId = authUser.getId();
		// 只用于回收 在鞋柜查询刷卡
		HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(recycleNum);
		HosRecord hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, hosRecycle.getTheNumber(),
				BizConst.DEVICE_TYPE.STERILIZER);
		Integer recordId = hosRecord.getId();
		String type = BizConst.DEVICE_TYPE.WARDRODE;
		hosRecoveryGoodsDao.updateHosRecoveryGoodsStateByUserId(state, recordId, userId, type);

		hosRecord.setIsCallback(BizConst.ISCALLBACK.YES);
		hosRecord.setCallbackTime(new Date());
		List<Map<String, Object>> list = hosClothesPressDao
				.selectClothesPressContainerByUserIdAndNumber(hosRecord.getTheaterNumber(), hosRecord.getUserId());
		if (list == null || list.size() <= 0) {
			throw new BusinessException("未查询到该用户使用的小柜");
		}
		hosClothesPressDao.updateClothesPressContainerById(Integer.parseInt(list.get(0).get("id").toString()));
		int num = 0;
		HosRecord newHosRecord = hosRecordDao.selectHosRecordById(recordId);
		if (newHosRecord.getIsCallback().equals("0")) {
			hosRecordDao.updateHosRecordService(hosRecord);
			num = 1;
		}
		return num;
	}

	public int deleteHosRecoveryGoodsByUserIdAndType(Integer userId, String type, String theNumber)
			throws BaseException {
		// 查询信息并更改，重新领取
		Map<String, Object> map = Maps.newHashMap();
		ZAssert.hasText(userId, "没有获取到磁卡信息，请查证！");
		ZAssert.hasText(theNumber, "没有获取到机器信息，请查证！");
		HosRecord hosRecord = new HosRecord();
		if (BizConst.DEVICE_TYPE.WARDRODE.equals(type)) { // 重新领取手术衣 查询签到信息
			String yesOrNoShoe = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX)
					.getConfig();// 是否有鞋柜
			if (yesOrNoShoe.equals(BizConst.YESORNO.NO)) {// 没有鞋柜
				type = BizConst.DEVICE_TYPE.WARDRODE;
			} else {// 有鞋柜
				type = BizConst.DEVICE_TYPE.STERILIZER;
			}
			// 只用于回收 在鞋柜查询刷卡
			HosWardrobe ward = hosWardrobeDao.selectHosWardrobeEnabledByNumber(theNumber);
			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, ward.getTheaterNumber(), type);
			if (hosRecord == null) {
				return 0;// 如果是null，不需要处理之前的领取信息
			}
			// 查询签到信息
			type = BizConst.DEVICE_TYPE.WARDRODE;
		} else {// 重新领取手术鞋，修改之前领取的小柜信息，查询签到信息
			List<HosSterilizerContainer> hos = hosSterilizerContainerDao.findSteConNumberByUserIdAndThNumber(userId,
					theNumber);
			if (hos == null || hos.size() <= 0) {
				return 0;// 如果是null，不需要处理之前的领取信息
			}
			for (HosSterilizerContainer a : hos) {// 修改小柜状态
				map.put("number", a.getLockerNumber());
				map.put("steNumber", a.getSteNumber());
				a.setUserId(null);
				a.setState(BizConst.STATE.NOTUSE);
				hosSterilizerContainerDao.updateContainer(a);
			}
			// 查询签到信息
			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, theNumber, BizConst.DEVICE_TYPE.STERILIZER);
			type = BizConst.DEVICE_TYPE.STERILIZER;
		}
		HosBusiness bus = new HosBusiness();
		// 根据查询出的hosrecoverygoods记录异常信息，
		// 只有一条信息，改完重复领取的次数校验取消遍历
		bus = hosBusinessDao.selectDeviceLogByUseridAndType(userId, type);
		bus.setOperationType(BizConst.OPERATION_TYPE.ABNORMAL);
		bus.setDescription(bus.getDescription() + "出现异常");
		hosBusinessDao.updateDeviceLogById(bus);// 更新日志为领取异常
		hosRecoveryGoodsDao.deleteHosRecoveryGoodsByIdAndType(type, hosRecord.getId());// 删除领取异常记录
		return 1;
	}

	public HosRecoveryGoods selectHosRecoveryGoodsByIdAndType(Integer id, String deviceType) throws BaseException {
		return hosRecoveryGoodsDao.selectHosRecoveryGoodsByIdAndType(id, deviceType);
	}

	@Override
	public List<String> selectImageBase64(Integer id) throws BaseException {
		List<Image> images = hosRecoveryGoodsDao.selectImageByRecoveryGoodsID(id);
		List<String> base64Strs = new ArrayList<>();
		for (Image image : images) {
			String path = image.getFilePath();
			String base64 = ImageUtils.readFileByBytesToBase64(path, "jpg");
			if (base64 != null) {
				base64Strs.add(base64);
			}
		}
		return base64Strs;
	}

	@Override
	public List<HosRecoveryGoods> selectHosRecoveryGoodsById(Integer id) throws BaseException {

		return hosRecoveryGoodsDao.selectHosRecoveryGoodsById(id);
	}

	@Override
	public int insertHosRecoveryGoods(HosRecoveryGoods hosRecoveryGoods) throws BaseException {

		return hosRecoveryGoodsDao.insertHosRecoveryGoods(hosRecoveryGoods);
	}
}
