package com.sesxh.hoschange.biz.hos.controller.device;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.biz.hos.service.HosRecycleService;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.service.AuthPermissionService;
import com.sesxh.hoschange.biz.sys.service.AuthUserService;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.RecycleBinData;
import com.sesxh.hoschange.global.BizConst;

@Component
public class HosRecycleSelfService {
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private HosRecordService hosRecordService;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private HosRecycleService hosRecycleService;


	/**
	 * 
	 * @Title: Recyle
	 * @author Ning
	 * @data:2017年3月21日
	 * @return:Message
	 * @throws:
	 */
	public Message Recyle(JSONObject jsonObject) throws BaseException {
		Message message = JSONObject.toJavaObject(jsonObject, Message.class);
		AuthUser authUser = authUserService.findByLoginName2(message.getCardNumber());
		String deviceType = BizConst.DEVICE_TYPE.RECYCLE;
		message.setDeviceType(deviceType);

		if (authUser == null) {
			message.setErrorMessage("查无此人");
			return message;
		}

		String type = authPermissionService.loadPermCodeListByUserId(authUser.getId());
		if ("0".equals(type)) {// 医护人员
			HosRecord hosRecord = hosRecordService.selectHosRecordLastByUserId(authUser.getId(), message.getDeviceID(),
					deviceType);
			if (hosRecord != null && BizConst.ISCALLBACK.NO.equals(hosRecord.getIsCallback())) {// 未成功回收
				return recoveryByDoctor(authUser, message, hosRecord);
			} else {
				message.setErrorMessage("没有签到");
				return message;
			}
		} else {// 管理员，清空回收桶
			hosRecycleService.emptyRecycle(message.getDeviceID());
			return message;
		}

	}

	/**
	 * 
	 * @Title: recoveryByDoctor
	 * @author Ning
	 * @data:2017年3月21日
	 * @return:Message
	 * @throws:
	 */
	private Message recoveryByDoctor(AuthUser authUser, Message message, HosRecord hosRecord) throws BaseException {
		return hosRecycleService.openRecycleDoor(authUser, message, hosRecord);
	}

	/**
	 * 感应回收状态
	 * 
	 * @Title: RecyclingBinData
	 * @author Ning
	 * @data:2017年3月16日
	 * @return:void
	 * @throws:
	 */
	public Message RecyclingBinData(JSONObject jsonObject) throws BaseException {
		RecycleBinData recycleBinData = JSONObject.toJavaObject(jsonObject, RecycleBinData.class);
		return hosRecycleService.RecyclingBinData(recycleBinData);

	}
	/**
	 * 带回监控照片
	* @Title: RecyclingBinImage
	* @author Ning 
	* @data:2017年4月26日
	* @return:Message
	* @throws:
	 */
	public Message RecyclingBinImage(JSONObject jsonObject) throws BaseException {
		RecycleBinData recycleBinData = JSONObject.toJavaObject(jsonObject, RecycleBinData.class);
		return hosRecycleService.RecyclingBinImage(recycleBinData);

	}

}
