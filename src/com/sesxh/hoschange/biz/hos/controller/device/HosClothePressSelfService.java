package com.sesxh.hoschange.biz.hos.controller.device;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.bpo.HosClothesPressBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosPowerBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecordBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.service.HosClothesPressService;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.biz.sys.bpo.SysConfigBpo;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.ISCALLBACK;
import com.sesxh.hoschange.global.BizConst.RecyclingBin;
import com.sesxh.hoschange.global.BizConst.SYSCONFIG;
import com.sesxh.hoschange.global.BizConst.StorageWardrobe;

@Component
public class HosClothePressSelfService {
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private HosRecordService hosRecordService;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private HosClothesPressService hosClothesPressService;
	@Autowired
	private HosPowerBpo hosPowerBpo;
	@Autowired
	private SysConfigBpo sysConfigBpo;
	@Autowired
	private HosClothesPressBpo hosClothesPressBpo;
	@Autowired
	private HosRecordBpo hosRecordBpo;
	@Autowired
	private HosRecoveryGoodsBpo hosRecoveryGoodsBpo;


	/**
	 * 
	 * @Title: ClothesPress
	 * @author Ning
	 * @data:2017年3月21日
	 * @return:Message
	 * @throws:
	 */
	public Object ClothesPress(JSONObject jsonObject) throws BaseException {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		AuthUser authUser = authUserService.findByLoginName2(message.getCardNumber());
		JSONObject data = new JSONObject();
		JSONObject parameter = new JSONObject();
		String deviceType = BizConst.DEVICE_TYPE.CLOTHESPRESS;
		message.setDeviceType(deviceType);
		data.put("Method", "OpenDoorMsg");

		if (authUser == null) {
			parameter.put("ErrorMessage", "查无此人");
			data.put("Parameter", parameter);
			return data;
		}
		Integer userid = authUser.getId();
		int count = hosPowerBpo.selectIfHavePower(userid + "");
		if (count == 0) {
			parameter.put("ErrorMessage", "您还没有申请权限");
			data.put("Parameter", parameter);
			return data;
		}

		String type = authPermissionService.loadPermCodeListByUserId(userid);
		if ("0".equals(type)) {// 医护人员，打开已经分配的储物柜
			String signState = sign(authUser, message.getDeviceID(), deviceType);
			if (BizConst.SIGN.OUT.equals(signState)) {
				SysConfig sysConfig = sysConfigBpo.querySysConfigByConfigName(SYSCONFIG.DISTRIBUTEWARDROBE);
				if (StorageWardrobe.YES.equals(sysConfig.getConfig())) {
					parameter.put("ErrorMessage", "请先去发衣柜取衣服");
					data.put("Parameter", parameter);
					return data;
				} else {
					String grey = hosRecordService.selectHosRecordCountByUserId(userid);
					if (BizConst.ISORNO_LIST.BLACK_TRUE.equals(grey)) {// 不在黑名单
						parameter.put("ErrorMessage", "您已进入黑名单，请联系管理员");
						data.put("Parameter", parameter);
						return data;
					}
					HosRecord hosRecord = new HosRecord();
					hosRecord.setUserId(userid);
					hosRecord.setSign(BizConst.SIGN.IN);
					hosRecord.setSignInTime(new Date());
					hosRecord.setDeviceType(message.getDeviceType());
					hosRecord.setDeviceNumber(message.getDeviceID());
					List<Map<String, Object>> list = hosClothesPressBpo
							.selectClothesPressBySteNumber(message.getDeviceID());
					hosRecord.setTheaterNumber((String) list.get(0).get("theaterNumber"));// 手术室编号，用于登录是判断是否签到签退
					hosRecordBpo.insertHosRecordService(hosRecord);// 签到

					SysConfig sysConfigRecycle = sysConfigBpo.querySysConfigByConfigName(SYSCONFIG.RECYCLINGBIN);
					String config = sysConfigRecycle.getConfig();
					// 生成回收记录
					HosRecoveryGoods hosRecoveryGoods = new HosRecoveryGoods();
					HosRecord record = hosRecordBpo.selectHosRecordLastByUserId(userid,
							(String) list.get(0).get("theaterNumber"), "");
					Integer recordId = record.getId();
					AuthUserDetail authUserDetail = new AuthUserDetail();
					authUserDetail = authUserService.selectUserDetailByAuthUserId(userid);
					hosRecoveryGoods.setSize(authUserDetail.getClothesSize());
					hosRecoveryGoods.setTheNumber((String) list.get(0).get("theaterNumber"));
					hosRecoveryGoods.setType(BizConst.GOODS_TYPE.SHOES);
					hosRecoveryGoods.setUserId(userid);
					hosRecoveryGoods.setRecordId(recordId);
					hosRecoveryGoods.setState(ISCALLBACK.NO);
					if (RecyclingBin.NO.equals(config)) {
						hosRecoveryGoods.setState(ISCALLBACK.YES);
					}
					hosRecoveryGoodsBpo.insertHosRecoveryGoods(hosRecoveryGoods);

				}
			}
			message = hosClothesPressService.openClothesPressContainerByUser(authUser, message);
			String errorMessage = message.getErrorMessage();
			if (errorMessage == null) {
				parameter.put("ErrorMessage", "null");
			} else {
				parameter.put("ErrorMessage", message.getErrorMessage());
			}
			parameter.put("Message", message.getMessage());
			data.put("Parameter", parameter);
			return data;
		} else {// 管理员，不能刷卡打开储物柜
			parameter.put("ErrorMessage", "无权限");
			data.put("Parameter", parameter);
			return data;
		}
	}

	/**
	 * 判断签到签退
	 * 
	 * @Title: sign
	 * @author Ning
	 * @data:2017年2月21日
	 * @return:String
	 * @throws:
	 */
	private String sign(AuthUser authUser, String deviceNumber, String deviceType) throws BaseException {
		HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(authUser.getId(), deviceNumber, deviceType);
		if (hosRecord == null || BizConst.SIGN.OUT.equals(hosRecord.getSign())) {
			return BizConst.SIGN.OUT;// 签退
		} else {
			return BizConst.SIGN.IN;// 签到
		}
	}

}
