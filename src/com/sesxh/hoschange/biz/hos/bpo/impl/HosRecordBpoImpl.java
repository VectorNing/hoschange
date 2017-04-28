package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosRecordBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBlacklistDao;
import com.sesxh.hoschange.biz.hos.dao.HosBlacklistRuleDao;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.dao.HosClothesPressDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecycleDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosRecordBpoImpl")
public class HosRecordBpoImpl extends SesframeBaseBpo implements HosRecordBpo {

	@Autowired
	private HosRecordDao hosRecordDao;
	@Autowired
	private HosRecoveryGoodsDao hosRecoveryGoodsDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private HosBlacklistRuleDao hosBlacklistRuleDao;
	@Autowired
	private HosClothesPressDao hosClothesPressDao;
	@Autowired
	private HosBlacklistDao hosBlacklistDao;
	@Autowired
	private HosBusinessDao hosBusinessDao;
	@Autowired
	private HosWardrobeDao hosWardrobeDao;
	@Autowired
	private HosRecycleDao hosRecycleDao;
	@Autowired
	private HosSterilizerDao hosSterilizerDao;
	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;

	@Override
	public int insertHosRecordService(HosRecord hosRecord) throws BaseException {
		return hosRecordDao.insertHosRecordService(hosRecord);
	}

	@Override
	public int updateHosRecordService(HosRecord hosRecord) throws BaseException {
		List<Map<String, Object>> list = hosClothesPressDao
				.selectClothesPressContainerByUserIdAndNumber(hosRecord.getTheaterNumber(), hosRecord.getUserId());
		if (list != null && list.size() > 0 && BizConst.BOOLEAN.TRUE.equals(list.get(0).get("yesOrNoBinding"))) {
			hosClothesPressDao.updateBindingClothesPressContainerStateById(
					Integer.parseInt(list.get(0).get("id").toString()), BizConst.STATE.NOTUSE);
		} else if (list != null && list.size() > 0) {
			hosClothesPressDao.updateClothesPressContainerById(Integer.parseInt(list.get(0).get("id").toString()));
		}
		List<HosSterilizerContainer> listHos = hosSterilizerContainerDao
				.findSteConNumberByUserIdAndThNumber(hosRecord.getUserId(), hosRecord.getTheaterNumber());
		if (listHos != null && listHos.size() > 0 && BizConst.BOOLEAN.TRUE.equals(listHos.get(0).getYesOrNoBinding())) {// 绑定
			hosSterilizerContainerDao.updateContainerState(listHos.get(0).getId(), BizConst.STATE.NOTUSE);
		} else if (listHos != null && listHos.size() > 0) {// 未绑定
			hosSterilizerContainerDao.updateContainerByUserId(listHos.get(0).getId());
		}
		hosRecord.setSign(BizConst.SIGN.OUT);
		return hosRecordDao.updateHosRecordService(hosRecord);
	}

	@Override
	public HosRecord selectHosRecordLastByUserId(Integer userId, String deviceNumber, String deviceType)
			throws BaseException {
		ZAssert.hasText(userId, "没有获取到用户信息");
		ZAssert.hasText(deviceNumber, "没有获取到当前设备");
		HosRecord hosRecord = null;
		String flag = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();// 是否有鞋柜
		if (BizConst.YESORNO.YES.equals(flag)) {// 有鞋柜
			if (BizConst.DEVICE_TYPE.WARDRODE.equals(deviceType)) {
				HosWardrobe ward = hosWardrobeDao.selectHosWardrobeEnabledByNumber(deviceNumber);
				deviceNumber = ward.getTheaterNumber();
			}
			if (BizConst.DEVICE_TYPE.RECYCLE.equals(deviceType)) {
				HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(deviceNumber);
				deviceNumber = hosRecycle.getTheNumber();
			}
			if (BizConst.DEVICE_TYPE.STERILIZER.equals(deviceType)) {
				HosSterilizer hosSterilizer = hosSterilizerDao.selectSterilizerByNumber(deviceNumber);
				deviceNumber = hosSterilizer.getTheaterNumber();
			}
			if (BizConst.DEVICE_TYPE.CLOTHESPRESS.equals(deviceType)) {
				HosClothesPress clothesPress = hosClothesPressDao.selectClothesPressByNumber(deviceNumber);
				deviceNumber = clothesPress.getTheaterNumber();
			}
			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, deviceNumber, "");// 根据更衣室编号查询刷卡记录
		} else {// 没有鞋柜
			if (deviceType.equals(BizConst.DEVICE_TYPE.RECYCLE)) {// 衣柜的查询
				HosRecycle hosRecycle = hosRecycleDao.selectRecycleFromRecycleByNumber(deviceNumber);
				deviceNumber = hosRecycle.getTheNumber();
				deviceType = BizConst.DEVICE_TYPE.WARDRODE;
			}
			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, deviceNumber, "");
		}

		return hosRecord;
	}

	@Override
	public HosRecord selectHosRecordLastByTheNumber(Integer userId, String theaterNumber, String deviceType)
			throws BaseException {
		ZAssert.hasText(userId, "没有获取到用户信息");
		ZAssert.hasText(theaterNumber, "没有获取更衣室信息");
		HosRecord hosRecord = null;
		hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");// 根据更衣室编号查询刷卡记录

		return hosRecord;
	}

	@Override
	public String selectHosRecordCountByUserIdM1(Integer userId) throws BaseException {
		String model = BizConst.BLACKLIST_MODEL.ONE;// 模式1
		String isCallback = BizConst.ISCALLBACK.NO;// 未回收的
		String greyType = BizConst.BLACKLIST_TYPE.GREY;// 灰名单
		String blackType = BizConst.BLACKLIST_TYPE.BLACK;// 黑名单
		String flag = BizConst.ISORNO_LIST.ALL_FALSE;// 不在名单
		HosBlacklist hosBlacklist = new HosBlacklist();

		HosBlacklist black = hosBlacklistDao.selectHosBlacklistByUserIdAndType(userId, blackType);
		HosBlacklist grey = hosBlacklistDao.selectHosBlacklistByUserIdAndType(userId, greyType);
		if (black != null) {
			flag = BizConst.ISORNO_LIST.BLACK_TRUE;// 在黑名单
			return flag;// "您已在黑名单，不能进行操作，请联系相关管理员！";
		}

		HosBlacklistRule blacklistRule = hosBlacklistRuleDao.selectHosBlacklistRuleByModelAndType(blackType, model);
		HosBlacklistRule greylistRule = hosBlacklistRuleDao.selectHosBlacklistRuleByModelAndType(greyType, model);
		if (blacklistRule == null) {
			ZAlert.Error("没有黑名单的相关配置");
		}
		if (greylistRule == null) {
			ZAlert.Error("没有灰名单的相关配置");
		}

		Integer latelyNum = blacklistRule.getLatelyNum();// 条数
		Long count = hosRecordDao.selectHosRecordCountByUserIdM1(userId, isCallback, latelyNum);// 查询最近几次没有正确回收的次数
		Integer blackSum = blacklistRule.getSums();// 黑名单限制次数
		Integer greySum = greylistRule.getSums();// 灰名单限制次数
		if (count >= blackSum) { // 等于限制的次数，进入黑名单，本次不可以领取
			hosBlacklist.setUserId(userId);
			hosBlacklist.setType(blackType);
			hosBlacklist.setCreatorTime(new Date());
			hosBlacklistDao.insertHosBlacklist(hosBlacklist);// 添加黑名单
			flag = BizConst.ISORNO_LIST.BLACK_TRUE;
		} else if (count >= greySum && count < blackSum) {
			if (grey == null) {
				hosBlacklist.setUserId(userId);
				hosBlacklist.setType(greyType);
				hosBlacklist.setCreatorTime(new Date());
				hosBlacklistDao.insertHosBlacklist(hosBlacklist);// 添加灰名单
			}
			flag = BizConst.ISORNO_LIST.GREY_TRUE;// 在灰名单
			// "您已在灰名单，请保持良好的回收操作，避免进入黑名单！";
		} else {
			if (grey != null) {
				hosBlacklist.setId(grey.getId());
				hosBlacklist.setOutTime(new Date());
				hosBlacklist.setEnabled(BizConst.ENABLED.DISABLE);// 无效
				hosBlacklistDao.updateHosBlacklist(hosBlacklist);// 踢出灰名单
			}
		}
		return flag;
	}

	public String selectHosRecordCountByUserIdM2(Integer userId) throws BaseException {
		String flag = BizConst.ISORNO_LIST.ALL_FALSE;// 不在名单
		HosBlacklist hosBlacklist = new HosBlacklist();
		String isCallback = BizConst.ISCALLBACK.NO;// 未回收的
		String greyType = BizConst.BLACKLIST_TYPE.GREY;// 灰名单
		String blackType = BizConst.BLACKLIST_TYPE.BLACK;// 黑名单
		String model = BizConst.BLACKLIST_MODEL.TWO;// 模式1

		HosBlacklist black = hosBlacklistDao.selectHosBlacklistByUserIdAndType(userId, blackType);
		HosBlacklist grey = hosBlacklistDao.selectHosBlacklistByUserIdAndType(userId, greyType);
		if (black != null) {
			flag = BizConst.ISORNO_LIST.BLACK_TRUE;// 在黑名单
			return flag;// "您已在黑名单，不能进行操作，请联系相关管理员！";
		}

		HosBlacklistRule blacklistRule = hosBlacklistRuleDao.selectHosBlacklistRuleByModelAndType(blackType, model);
		HosBlacklistRule greylistRule = hosBlacklistRuleDao.selectHosBlacklistRuleByModelAndType(greyType, model);
		if (blacklistRule == null) {
			ZAlert.Error("没有黑名单的相关配置");
		}
		if (greylistRule == null) {
			ZAlert.Error("没有灰名单的相关配置");
		}
		Long countBlack = hosRecordDao.selectHosRecordCountByUserIdM2(userId, isCallback,
				blacklistRule.getRosterTime());// (黑名单)查询最近时间段没有正确回收的次数
		Long countGrey = hosRecordDao.selectHosRecordCountByUserIdM2(userId, isCallback, greylistRule.getRosterTime());// (灰名单)查询最近时间段没有正确回收的次数
		Integer blackSum = blacklistRule.getSums();// 黑名单限制次数
		Integer greySum = greylistRule.getSums();// 灰名单限制次数
		if (countBlack >= blackSum) { // 等于限制的次数，进入黑名单，本次可以领取
			hosBlacklist.setUserId(userId);
			hosBlacklist.setType(blackType);
			hosBlacklist.setCreatorTime(new Date());
			hosBlacklistDao.insertHosBlacklist(hosBlacklist);// 添加黑名单
		}
		if (countGrey >= greySum && countBlack < blackSum) {// 灰名单规则查询出次数 大于等于
															// 规则次数。并且没有进黑名单
			if (grey == null) {
				hosBlacklist.setUserId(userId);
				hosBlacklist.setType(greyType);
				hosBlacklist.setCreatorTime(new Date());
				hosBlacklistDao.insertHosBlacklist(hosBlacklist);// 添加灰名单
			}
			flag = BizConst.ISORNO_LIST.GREY_TRUE;// 在灰名单
			// "您已在灰名单，请保持良好的回收操作，避免进入黑名单！";
		} else {
			if (grey != null) {
				hosBlacklist.setId(grey.getId());
				hosBlacklist.setOutTime(new Date());
				hosBlacklist.setEnabled(BizConst.ENABLED.DISABLE);// 无效
				hosBlacklistDao.updateHosBlacklist(hosBlacklist);// 踢出灰名单
			}
		}
		return flag;
	}

	@Override
	public boolean judgeHosRecordForBrushCardTimes(Integer userId, String deviceNumber, String deviceType)
			throws BaseException {
		String brushCardTime = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.BRUSHCARDTIME).getConfig();
		boolean flag = false;
		Long count = hosBusinessDao.selectHosRecordForBrushCardTimes(brushCardTime, userId, deviceType, deviceNumber);
		if (count != null && count > 0) {
			// ZAlert.Error((Integer.valueOf(brushCardTime)/60)+"内不能重复刷卡");
			flag = true;
		}
		return flag;
	}

	public int updateSignOutRecovery(Integer recordId, String cardNum, String state) throws BaseException {
		HosRecord hosRecord = new HosRecord();
		AuthUser authUser = authUserDao.findByLoginName2(cardNum);
		String isCallback = "";
		if (authUser == null) {
			ZAlert.Error("没有获取到用户信息");
		}
		// HosRecord record = hosRecordDao.selectHosRecordById(recordId);
		// if(record==null){
		// ZAlert.Error("没有获取到刷卡信息");
		// }
		if (state.equals(BizConst.STATE.USE)) { // 回收成功
			String type = BizConst.DEVICE_TYPE.WARDRODE;
			hosRecoveryGoodsDao.updateHosRecoveryGoodsStateByUserId(state, recordId, authUser.getId(), type);// 回收
			isCallback = BizConst.ISCALLBACK.YES;// 刷卡记录 回收标志
			hosRecord.setIsCallback(isCallback);
			hosRecord.setCallbackTime(new Date());
		} else {
			isCallback = BizConst.ISCALLBACK.NO;// 刷卡记录 回收标志
			hosRecord.setIsCallback(isCallback);
		}
		hosRecord.setId(recordId);
		hosRecord.setSign(BizConst.SIGN.OUT);
		List<Map<String, Object>> list = hosClothesPressDao
				.selectClothesPressContainerByUserIdAndNumber(hosRecord.getTheaterNumber(), hosRecord.getUserId());
		if (list == null || list.size() <= 0) {
			throw new BusinessException("未查询到该用户使用的小柜");
		}
		hosClothesPressDao.updateClothesPressContainerById(Integer.parseInt(list.get(0).get("id").toString()));
		return hosRecordDao.updateHosRecordService(hosRecord);
	}

	public DataSet selectHosRecordSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String userName = pm.getStrParam("userName");
		if (userName != null) {
			pm.updateParam("userName", "%" + userName + "%");
		}
		String theaterNumber = pm.getStrParam("theaterNumber");
		if (theaterNumber != null) {
			pm.updateParam("theaterNumber", "%" + theaterNumber + "%");
		}
		DataSet ds = DataSet.newDS(hosRecordDao.selectHosRecordCount(pm), hosRecordDao.selectHosRecordSet(pm));
		return ds;
	}

	@Override
	public List<HosRecord> selectOvertimeNoSignOut(String time) throws BaseException {
		return hosRecordDao.selectOvertimeNoSignOut(time);
	}

	@Override
	public HosRecord selectHosRecordLqcsById(Integer id) throws BaseException {
		return hosRecordDao.selectHosRecordLqcsById(id);
	}

	@Override
	public int updateHosRecordLqcs(Integer id) throws BaseException {
		return hosRecordDao.updateHosRecordLqcs(id);
	}

}
