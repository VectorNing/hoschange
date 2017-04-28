package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecycleBpo;
import com.sesxh.hoschange.biz.hos.dao.HosClothesPressDao;
import com.sesxh.hoschange.biz.hos.dao.HosImageDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecycleDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.entity.HosRecycleContainer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.sys.dao.AuthPermissionDao;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Image;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.data.RecycleBinData;
import com.sesxh.hoschange.common.util.ImageUtils;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosRecycleBpoImp")
public class HosRecycleBpoImp extends SesframeBaseBpo implements HosRecycleBpo {
	@Autowired
	private HosRecycleDao hosRecycleDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private HosRecoveryGoodsDao hosRecoveryGoodsDao;
	@Autowired
	private HosRecordDao hosRecordDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;
	@Autowired
	private HosClothesPressDao hosClothesPressDao;
	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;
	@Autowired
	private HosImageDao hosImageDao;

	@Override
	public HosRecycle selectRecycleByNumber(String number, Integer id) throws BaseException {
		return hosRecycleDao.selectRecycleByNumber(number, id);
	}

	@Override
	public int insertRecycle(HosRecycle hosRecycle) throws BaseException {
		validateRecycle(hosRecycle);

		hosRecycle.setRecycle(0);
		hosRecycle.setState("1");

		return hosRecycleDao.insertRecycle(hosRecycle);
	}

	private void validateRecycle(HosRecycle hosRecycle) throws BaseException {
		String description = hosRecycle.getDescription();
		ZAssert.hasText(hosRecycle.getNumber(), "回收桶编号不能为空！");

		ZAssert.lenLessTan(hosRecycle.getNumber(), 100, "回收桶编号不能超过100字符");
		if (StringUtils.hasText(description)) {
			ZAssert.lenLessTan(description, 200, "回收桶描述不能超过200字符");
		}
	}

	@Override
	public DataSet queryHosRecycleSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}

		DataSet ds = DataSet.newDS(hosRecycleDao.HosRecycleCount(pm), hosRecycleDao.queryRecycleAll(pm));
		return ds;
	}

	@Override
	public HosRecycle queryHosrecycleById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosRecycleDao.queryHosrecycleById(id);
	}

	@Override
	public int updateHosRecycle(HosRecycle hosRecycle) throws BaseException {
		validateRecycle(hosRecycle);

		return hosRecycleDao.updateHosRecycle(hosRecycle);
	}

	@Override
	public Long selectRecycleFromRecycleByNumber(String number) throws BaseException {
		ZAssert.hasText(number, "消毒柜编号不能为空！");
		HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(number);
		Integer count = hosRecycle.getRecycle();
		return count.longValue();
	}

	@Override
	public int deleteHosRecycle(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		HosRecycle hosRecycle = hosRecycleDao.queryHosrecycleById(id);
		String number = hosRecycle.getNumber();
		ZAssert.hasText(number, "没有找到相对应的消毒柜编号，请查证！");
		hosRecycleDao.deleteHosRecycleContainerByRecycleId(id);
		return hosRecycleDao.deleteHosRecycle(id);
	}

	@Override
	public int updateHosRecycleCountByNumberAndCardNum(String number, String cardNum) throws BaseException {
		String type = BizConst.DEVICE_TYPE.WARDRODE;
		HosRecord hosRecord = new HosRecord();
		AuthUser authUser = authUserDao.findByLoginName2(cardNum);
		if (authUser == null) {
			ZAlert.Error("没有获取到用户信息");
		}
		Integer userId = authUser.getId();
		HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(number);
		validateRecycle(hosRecycle);
		String yesOrNoShoe = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();// 是否有鞋柜

		if (yesOrNoShoe.equals(BizConst.YESORNO.NO)) {// 如果没有鞋柜
			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, hosRecycle.getTheNumber(),
					BizConst.DEVICE_TYPE.WARDRODE);
		} else {// 有鞋柜

			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, hosRecycle.getTheNumber(),
					BizConst.DEVICE_TYPE.STERILIZER);
		}
		HosRecoveryGoods hosRecoveryGoods = hosRecoveryGoodsDao.selectHosRecoveryGoodsByIdAndType(hosRecord.getId(),
				type);

		HosRecycleContainer hosRecycleContainer = new HosRecycleContainer();
		int size = hosRecoveryGoods.getSize();
		int recycleId = hosRecycle.getId();
		hosRecycleContainer.setRecycleId(recycleId);
		hosRecycleContainer.setSize(size);
		hosRecycleContainer.setType(type);
		hosRecycleDao.insertToRecycleContainer(hosRecycleContainer);

		Integer recycle = hosRecycle.getRecycle() + 1;
		return hosRecycleDao.updateHosRecycleCountByNumber(number, recycle);
	}

	@Override
	public int emptyRecycle(String recycleNumber) throws BaseException {

		HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(recycleNumber);
		validateRecycle(hosRecycle);
		int recycleId = hosRecycle.getId();

		// 清空回收桶信息和回收桶详细信息
		hosRecycleDao.emptyRecycle(recycleNumber);
		hosRecycleDao.emptyRecycle(recycleId);
		return 0;
	}

	@Override
	public DataSet queryHosRecycleMonitored(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		Long total;
		List<Map<String, Object>> lists = new ArrayList<>();

		Integer userId = pm.getIntegerParam("userId");
		AuthUser authUser = authUserDao.selectAuthUserByUserId(userId);
		List<AuthPermission> authPermissions = authPermissionDao.selectPermListByUserId(userId);
		if (BizConst.SFCJGLY.TURE.equals(authUser.getSfcjgly())
				|| isOrNoAdmin(BizConst.PERMISSION_CODE.BACKADMIN, authPermissions)) {
			total = hosRecycleDao.AllHosRecycleCountMonitored(pm);
			lists = hosRecycleDao.selectAllHosRecycleMonitored(pm);
		} else {
			total = hosRecycleDao.HosRecycleCountMonitoredByUserId(pm);
			lists = hosRecycleDao.selectHosRecycleMonitoredByUserId(pm);
		}

		DataSet ds = DataSet.newDS(total, lists);
		return ds;
	}

	private boolean isOrNoAdmin(String permCode, List<AuthPermission> list) {
		boolean flag = false;
		for (AuthPermission authPermission : list) {
			if (permCode.equals(authPermission.getPermCode())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public Message openRecycleDoor(AuthUser authUser, Message message, HosRecord hosRecord) throws BaseException {
		HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(message.getDeviceID());
		HosRecoveryGoods hosRecoveryGoods = hosRecoveryGoodsDao.selectHosRecoveryGoodsByIdAndType(hosRecord.getId(),
				hosRecycle.getType());
		List<String> operation = new ArrayList<String>();
		if (BizConst.ISCALLBACK.YES.equals(hosRecoveryGoods.getState())) {// 该类型物品是否回收成功
			message.setErrorMessage("您已回收成功，请勿重复刷卡");
			return message;
		} else {
			if (BizConst.GOODS_TYPE.CLOTHES.equals(hosRecycle.getType())) {// 在手术衣回收桶刷卡
																			// 解除绑定
				List<Map<String, Object>> list = hosClothesPressDao
						.selectClothesPressContainerByUserIdAndNumber(hosRecycle.getTheNumber(), authUser.getId());
				if (list != null && list.size() > 0
						&& BizConst.BOOLEAN.TRUE.equals(list.get(0).get("yesOrNoBinding"))) {// 绑定
					hosClothesPressDao.updateBindingClothesPressContainerStateById(
							Integer.parseInt(list.get(0).get("id").toString()), BizConst.STATE.NOTUSE);
				} else if (list != null && list.size() > 0
						&& BizConst.BOOLEAN.FALSE.equals(list.get(0).get("yesOrNoBinding"))) {// 未绑定
					hosClothesPressDao
							.updateClothesPressContainerById(Integer.parseInt(list.get(0).get("id").toString()));
				}
			} else if (BizConst.GOODS_TYPE.SHOES.equals(hosRecycle.getType())) {// 在手术鞋回收桶刷卡
																				// 解除绑定
																				// 签退
				List<HosSterilizerContainer> list = hosSterilizerContainerDao
						.findSteConNumberByUserIdAndThNumber(hosRecord.getUserId(), hosRecord.getTheaterNumber());
				if (list != null && list.size() > 0 && BizConst.BOOLEAN.TRUE.equals(list.get(0).getYesOrNoBinding())) {// 绑定
					hosSterilizerContainerDao.updateContainerState(list.get(0).getId(), BizConst.STATE.NOTUSE);
				} else if (list != null && list.size() > 0) {// 未绑定
					hosSterilizerContainerDao.updateContainerByUserId(list.get(0).getId());
				}
			} else if (BizConst.GOODS_TYPE.BOX.equals(hosRecycle.getType())) {

			}

			hosRecoveryGoodsDao.updateHosRecoveryGoodsStateById(BizConst.ISCALLBACK.READYCALLBACK,
					hosRecoveryGoods.getId(), message.getDeviceID());
			operation.add("open");
			message.setOperation(operation);
			message.setRecoveryID(hosRecoveryGoods.getId().toString());
			return message;
		}
	}

	@Override
	public Message RecyclingBinData(RecycleBinData recycleBinData) throws BaseException {
		String deviceNumber = recycleBinData.getDeviceID();
		String state = recycleBinData.getRecoveryState();
		HosRecycleContainer hosRecycleContainer = new HosRecycleContainer();
		HosRecord hosRecord = new HosRecord();
		Message message = new Message();
		HosRecycle hosRecycle = hosRecycleDao.selectRecycleByNumber(deviceNumber, null);// 回收桶回收数量
		HosRecoveryGoods hosRecoveryGoods = hosRecoveryGoodsDao
				.selectHosRecoveryGoodsByDeviceNumberAndReadyCallBack(deviceNumber, BizConst.ISCALLBACK.READYCALLBACK);
		message.setDeviceID(deviceNumber);
		if ("1".equals(state)) {// 感应到回收信息
			int recycleNum = hosRecycle.getRecycle() + 1;
			hosRecycleDao.updateHosRecycleCountByNumber(deviceNumber, recycleNum);
			hosRecycleContainer.setRecycleId(hosRecycle.getId());
			hosRecycleContainer.setSize(hosRecoveryGoods.getSize());
			hosRecycleContainer.setType(hosRecycle.getType());
			hosRecycleDao.insertToRecycleContainer(hosRecycleContainer);
			hosRecoveryGoodsDao.updateHosRecoveryGoodsStateById(BizConst.ISCALLBACK.YES, hosRecoveryGoods.getId(),
					null);
			message.setMessage("回收成功");
		}
		if ("0".equals(state)) {
			hosRecoveryGoodsDao.updateHosRecoveryGoodsStateById(BizConst.ISCALLBACK.NO, hosRecoveryGoods.getId(), null);
			message.setMessage("回收失败");
		}

		if ("0".equals(hosRecoveryGoods.getType())) {// 如果是在消毒鞋柜上，签退，计算签到表回收状态
			// 查询这一次记录中多次回收状态
			int Recoverystate = 0;
			List<HosRecoveryGoods> recoveryGoods = hosRecoveryGoodsDao
					.selectHosRecoveryGoodsById(hosRecoveryGoods.getRecordId());
			for (HosRecoveryGoods goods : recoveryGoods) {
				int num = Integer.parseInt(goods.getState());
				Recoverystate += num;
			}
			// 如果多次回收记录均成功回收，这一流程记录为回收成功
			if (Recoverystate >= recoveryGoods.size()) {
				hosRecord.setIsCallback("1");
				hosRecord.setCallbackTime(new Date());
				hosRecord.setSign(BizConst.SIGN.OUT);
			} else {
				hosRecord.setIsCallback("0");
			}
			hosRecord.setId(hosRecoveryGoods.getRecordId());

			hosRecordDao.updateHosRecordService(hosRecord);
		}
		return message;
	}

	@Override
	public Message RecyclingBinImage(RecycleBinData recycleBinData) throws BaseException {
		Message message = new Message();
		Image image = recycleBinData.getImage();
		String recoveryID = recycleBinData.getRecoveryID();
		String date = image.getDate();
		String base64 = image.getBase64();
		String base64Str = base64.split(";")[1].split(",")[1];
		String mainFilePath = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.MAINFILEPATH).getConfig();
		image.setHrgid(recoveryID);
		image.setFilePath(mainFilePath + recoveryID + "/" + date);
		if (ImageUtils.GenerateImage(base64Str, mainFilePath, recoveryID, date,"jpg")) {// base64字符串转化成图片
			message.setMessage("图片储存成功");
			hosImageDao.insertImage(image);
		} else {
			message.setMessage("图片储存失败");
		}
		return message;

	}

}
