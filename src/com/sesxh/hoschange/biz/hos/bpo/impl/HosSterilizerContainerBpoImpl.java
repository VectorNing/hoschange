package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.common.util.DateUtils;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSterilizerContainerBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.dao.HosClothesPressDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerDao;
import com.sesxh.hoschange.biz.hos.dao.HosTheaterDao;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.dao.DictionaryDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.common.websocket.WebSocketChanne;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosSterilizerContainerBpoImpl")
public class HosSterilizerContainerBpoImpl extends SesframeBaseBpo implements HosSterilizerContainerBpo {

	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;
	@Autowired
	private HosRecordDao hosRecordDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private HosRecoveryGoodsDao hosRecoveryGoodsDao;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private HosClothesPressDao hosClothesPressDao;
	@Autowired
	private HosSterilizerDao hosSterilizerDao;
	@Autowired
	private HosTheaterDao hosTheaterDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private HosBusinessDao hosBusinessDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public List<HosSterilizerContainer> selectSterilizerContainerBySteNumber(String steNumber) throws BaseException {
		ZAssert.hasText(steNumber, "消毒柜编号不能为空，请查证！");
		List<HosSterilizerContainer> hos = Lists.newArrayList();
		hos = hosSterilizerContainerDao.selectSterilizerContainerOneBySteNumber(steNumber);
		return hos;
	}

	@Override
	public int allotShoesToContainer(Integer[] lockerNumbers, String shoesSize, int allotCount) throws BaseException {
		if (allotCount > 0) {
			if (lockerNumbers != null) {
				int count = lockerNumbers.length;
				if (count > allotCount) {
					ZAlert.Error("所选小柜数不能大于可分配数量");
				}
				for (int i = 0; i < count; i++) {
					int id = lockerNumbers[i];
					hosSterilizerContainerDao.updateContainerById(id, BizConst.STATE.USE, shoesSize);
				}
			}
		}

		return 0;
	}

	public int allotShoesTwoToContainer(Integer[] lockerNumbers, Integer[] ids) throws BaseException {

		return 0;
	}

	@Override
	public int emptyContainer(Integer[] ids) throws BaseException {
		if (ids != null) {
			int count = ids.length;
			for (int i = 0; i < count; i++) {
				int id = ids[i];
				hosSterilizerContainerDao.updateContainerById(id, BizConst.STATE.NOTUSE, "0");
			}
		} else {
			ZAlert.Error("请选择要清空的小柜子");
		}
		return 0;
	}

	public int emptyAllContainer(String number) throws BaseException {
		int flag = 0;
		ZAssert.hasText(number, "编号不能为空，请查证！");
		List<Map<String, Object>> cont = hosSterilizerContainerDao.selectContainerBySteNumber(number);
		if (cont == null || cont.size() <= 0) {
			ZAlert.Error("请选择要清空的柜子");
		}
		for (Map<String, Object> map : cont) {
			Object co = map.get("id");
			Integer id = Integer.parseInt(co.toString());
			flag = hosSterilizerContainerDao.updateContainerById(id, BizConst.STATE.NOTUSE, "0");
		}
		return flag;
	}

	public Map<String, Object> findSteConNumberByUserIdAndThNumber(Integer userId, String number) throws BaseException {
		Map<String, Object> map = Maps.newHashMap();
		ZAssert.hasText(userId, "没有获取到磁卡信息，请查证！");
		ZAssert.hasText(number, "没有获取到机器信息，请查证！");
		HosRecord hosRecord = new HosRecord();
		String yesOrNoShoe = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();
		;
		List<HosSterilizerContainer> hos = hosSterilizerContainerDao.findSteConNumberByUserIdAndThNumber(userId,
				number);
		if (hos == null || hos.size() <= 0) {
			ZAlert.Error("当前没有可取回物品！");
		}
		for (HosSterilizerContainer a : hos) {
			map.put("number", a.getLockerNumber());
			map.put("steNumber", a.getSteNumber());
			a.setUserId(null);
			a.setState(BizConst.STATE.NOTUSE);
			hosSterilizerContainerDao.updateContainer(a);
		}
		hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, number, BizConst.DEVICE_TYPE.STERILIZER);
		String type = BizConst.DEVICE_TYPE.STERILIZER;
		hosRecoveryGoodsDao.updateHosRecoveryGoodsStateByUserId(BizConst.STATE.USE, hosRecord.getId(), userId, type);
		List<Map<String, Object>> list = hosClothesPressDao
				.selectClothesPressContainerByUserIdAndNumber(hosRecord.getTheaterNumber(), hosRecord.getUserId());
		if (list == null || list.size() <= 0) {
			throw new BusinessException("未查询到该用户使用的小柜");
		}
		hosClothesPressDao.updateClothesPressContainerById(Integer.parseInt(list.get(0).get("id").toString()));
		if (yesOrNoShoe.equals(BizConst.YESORNO.YES)) {
			hosRecord.setSign(BizConst.SIGN.OUT);
			// hosRecord.setIsCallback(BizConst.ISCALLBACK.YES);
			// hosRecord.setCallbackTime(new Date());
			hosRecordDao.updateHosRecordService(hosRecord);// 签退
		}
		return map;
	}

	// ##############################--- 11-30 最新
	// ---#################################//
	@Override
	public int allotShoesToContainerForZD(Integer[] ids, String jsonArray) throws BaseException {
		/*
		 * JSONArray json = JSON.parseArray(jsonArray); Iterator<Object> it =
		 * json.iterator(); Integer count = 0; while(it.hasNext()){ JSONObject
		 * sObj = (JSONObject)it.next(); count =
		 * Integer.parseInt((String.valueOf(sObj.get("count")))); String size =
		 * (String)sObj.get("size");
		 * 
		 * 
		 * HosShoes hosShoes =
		 * hosShoesDao.selectHosShoesByShoeSize(Integer.parseInt(size), "1");
		 * Integer residue = hosShoes.getResidueCount(); if(count>residue){
		 * count = residue;//大于剩余数量，按可分配数量 } //更新手术鞋剩余数量
		 * hosShoesDao.updateResidueCountBySize(size, -count); } ids =
		 * Arrays.copyOfRange(ids, 0, count);//截取分配的小柜 if(ids != null &&
		 * ids.length > 0){ for(int id : ids){ //更新鞋柜小柜使用状态
		 * hosSterilizerContainerDao.updateContainerState(id,
		 * BizConst.USE_STATE.USE); } }
		 */
		for (int id : ids) {
			// 更新鞋柜小柜使用状态
			hosSterilizerContainerDao.updateContainerState(id, BizConst.USE_STATE.USE);
		}
		return 0;
	}

	public int allotSizeShoesToSterilizer(Integer[] ids, String shoesSize) throws BaseException {
		int falg = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择要设定尺码的小柜");
		}
		if (shoesSize == null || "".equals(shoesSize) || shoesSize.isEmpty()) {
			ZAlert.Error("请选择要设定的尺码");
		}
		for (Integer id : ids) {
			HosSterilizerContainer sterilizerContainer = hosSterilizerContainerDao.selectHosSterilizerContainerById(id);
			String state = sterilizerContainer.getState();
			String number = sterilizerContainer.getLockerNumber();// 小柜编号
			if (state.equals(BizConst.STATE.USE)) {
				ZAlert.Error(number + "号小柜存放有消毒鞋，不能重新设定尺码");
			}
			falg = hosSterilizerContainerDao.updateSizeShoesToSterilizer(id, shoesSize);
		}
		return falg;
	}

	public Map<String, Object> selectShoesByUserIdAndThNumber(String cardNum, String number) throws BaseException {
		Map<String, Object> map = Maps.newHashMap();
		ZAssert.hasText(cardNum, "没有获取到磁卡信息，请查证！");
		ZAssert.hasText(number, "没有获取到机器信息，请查证！");
		AuthUser authUser = authUserDao.findByLoginName2(cardNum);
		if (authUser == null) {
			ZAlert.Error("没有获取到用户信息，请联系相关管理员！");
		}
		Integer userId = authUser.getId();// 用户id
		List<HosSterilizerContainer> hos = hosSterilizerContainerDao.findSteConNumberByUserIdAndThNumber(userId,
				number);
		if (hos == null || hos.size() <= 0) {
			ZAlert.Error("当前没有查询到您的物品，请确定是否放入或者联系相关管理员！");
		}
		for (HosSterilizerContainer a : hos) {
			map.put("number", a.getLockerNumber());
			map.put("steNumber", a.getSteNumber());
		}
		return map;
	}

	public int emptyContainerShoes(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids != null) {
			int count = ids.length;
			for (int i = 0; i < count; i++) {
				int id = ids[i];
				HosSterilizerContainer container = hosSterilizerContainerDao.selectHosSterilizerContainerById(id);
				int shoesSize = container.getShoesSize();
				hosSterilizerContainerDao.updateContainerById(id, BizConst.STATE.NOTUSE, String.valueOf(shoesSize));
			}
		} else {
			ZAlert.Error("请选择要清空的小柜子");
		}
		return falg;
	}

	public int emptyContainerShoes2(Integer[] ids) throws BaseException {
		int falg = 0;
		if (ids != null) {
			int count = ids.length;
			for (int i = 0; i < count; i++) {
				int id = ids[i];
				HosSterilizerContainer container = hosSterilizerContainerDao.selectHosSterilizerContainerById(id);
				int shoesSize = container.getShoesSize();
				hosSterilizerContainerDao.updateContainerById(id, BizConst.STATE.NOTUSE, String.valueOf(shoesSize));
			}
		} else {
			ZAlert.Error("请选择要清空的小柜子");
		}
		return falg;
	}

	@Override
	public HosSterilizerContainer selectSterilizerContainerById(Integer id) throws BaseException {
		return hosSterilizerContainerDao.selectSterilizerContainerById(id);
	}

	@Override
	public int lockSterilizerContainer(Integer[] ids) throws BaseException {
		int i = 0;
		for (Integer id : ids) {
			i += hosSterilizerContainerDao.updateLockSterilizerContainer(id);
		}
		return i;
	}

	@Override
	public int stopLockSterilizer(Integer[] ids) throws BaseException {
		int i = 0;
		for (Integer id : ids) {
			i += hosSterilizerContainerDao.updateStopLockSterilizer(id);
		}
		return i;
	}

	@Override
	public int bindingSterilizerAndUser(Integer id, Integer userId) throws BaseException {
		AuthUserDetail authUserDetail = authUserDao.selectUserDetailByAuthUserId(userId);
		HosSterilizerContainer hosSterilizerContainer = hosSterilizerContainerDao.selectHosSterilizerContainerById(id);
		HosSterilizer hosSterilizer = hosSterilizerDao.selectSterilizerByNumber(hosSterilizerContainer.getSteNumber());
		HosSterilizerContainer hosSterilizerContainer1 = hosSterilizerContainerDao
				.selectBindingSterilizerAndUser(userId, hosSterilizer.getTheaterNumber());
		if (hosSterilizerContainer1 != null) {
			throw new BusinessException("该用户已经绑定小柜");
		}
		return hosSterilizerContainerDao.updateBindingSterilizerAndUser(id, userId, authUserDetail.getShoesSize());
	}

	@Override
	public int stopBindingSterilizerAndUser(Integer id) throws BaseException {
		return hosSterilizerContainerDao.updateStopBindingSterilizerAndUser(id);
	}

	@Override
	public DataSet selectAddShoeRule(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		DataSet ds = DataSet.newDS(hosSterilizerContainerDao.selectAddShoeRuleCount(pm),
				hosSterilizerContainerDao.selectAddShoeRule(pm));
		return ds;
	}

	@Override
	public int setUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException {
		int flag = hosSterilizerContainerDao.updateSetUserAddShoeRule(code, userId, size);
		if (flag == 0) {
			hosSterilizerContainerDao.insertSetUserAddShoeRule(code, userId, size);
		}
		return flag;
	}

	@Override
	public HosSterilizerContainer selectBindingSterilizerAndUser(Integer userId, String deviceNumber)
			throws BaseException {
		HosSterilizer hosSterilizer = hosSterilizerDao.selectSterilizerByNumber(deviceNumber);
		return hosSterilizerContainerDao.selectBindingSterilizerAndUser(userId, hosSterilizer.getTheaterNumber());
	}

	@Override
	public Message OpenLockerUnderSingIn(AuthUser authUser, Message message) throws BaseException {
		List<String> lockerId = new ArrayList<>();
		HosSterilizerContainer container = hosSterilizerContainerDao
				.selectSterilizerContainerByUserId(authUser.getId());
		lockerId.add(container.getLockerNumber());
		message.setMessageType("01");
		message.setOperation(lockerId);
		message.setDeviceID(container.getSteNumber());
		message.setErrorMessage("请打开" + container.getSteNumber() + "号柜" + container.getLockerNumber() + "号小柜");
		return message;
	}

	@Override
	public Message OpenLockerByAdmin(AuthUser authUser, Message message) throws BaseException {
		List<HosSterilizerContainer> containers = new ArrayList<>();// 符合管理员开门的小柜
		List<String> lockerId = new ArrayList<>();// 小柜门号
		Integer userId = authUser.getId();// 用户id
		String deviceNumber = message.getDeviceID();// 设备号

		containers = hosSterilizerContainerDao.selectContainerByAdminCodeAndShoSizeAndState(deviceNumber, null,
				BizConst.STATE.ADMIN_ALLOTING);
		if (containers.size() > 0 && containers != null) {// 添加完成确认
			for (HosSterilizerContainer container : containers) {
				hosSterilizerContainerDao.updateContainerState(container.getId(), BizConst.STATE.USE);
				HosBusiness business = new HosBusiness();// 日志
				Date date = DateUtils.getLocalDate();// 当前时间
				// 得到用户的详细信息
				AuthUserDetail authUserDetail = hosTheaterDao.loadUserDetailById(userId);
				Dictionary dictionary = dictionaryDao.selectDictByTypeAndCode("shoes",
						container.getShoesSize().toString());
				String shoeName = dictionary.getName();// 鞋子尺码名字
				business.setUserId(userId);
				business.setOperateTime(date);
				business.setDeviceType(message.getDeviceType());
				business.setSize(container.getShoesSize());
				business.setDeviceNumber(deviceNumber);
				business.setConNumber(container.getLockerNumber());
				business.setOperationType(BizConst.OPERATION_TYPE.ALLOT);
				String time = DateUtils.FormatDateToYear_Month_Day(date);
				String des = authUserDetail.getUserName() + "在" + time + "在编号为【" + deviceNumber + "】的消毒鞋柜内的【"
						+ container.getLockerNumber() + "】号柜， 添加【 " + shoeName + "】手术鞋";
				business.setDescription(des);
				hosBusinessDao.insertDeviceLog(business);
			}
			message.setMessageType("05");
			message.setMessage("添加成功");
			return message;
		}
		// 准备添加
		// 查询管理员设置开柜规则
		List<Map<String, Object>> rule = hosSterilizerContainerDao.selectAddShowRuleByUserId(userId);
		if (rule.size() > 0 && rule != null && "2".equals((String) rule.get(0).get("addShoesCode"))) {// 该柜某一尺码
			Integer shoeSize = Integer.parseInt((rule.get(0).get("size").toString()));
			containers = hosSterilizerContainerDao.selectContainerByAdminCodeAndShoSizeAndState(deviceNumber, shoeSize,
					BizConst.STATE.NOTUSE);
			message = getLockerIdAndUpDateSterilizer(containers, lockerId, message);
		} else {// 该柜所有空柜
			containers = hosSterilizerContainerDao.selectContainerByAdminCodeAndShoSizeAndState(deviceNumber, null,
					BizConst.STATE.NOTUSE);
			message = getLockerIdAndUpDateSterilizer(containers, lockerId, message);
			if (containers == null || containers.size() == 0) {
				message.setMessageType("03");
				message.setErrorMessage("该柜没有可打开的空柜");
			} else {
				message.setMessageType("04");
				message.setErrorMessage("柜门打开，请添加消毒鞋");
			}
		}

		return message;
	}

	/**
	 * 获得管理员要打开的小柜门并打开。且更新小柜状态
	 * 
	 * @Title: getLockerIdAndUpDateSterilizer
	 * @author Ning
	 * @data:2017年2月22日
	 * @return:Message
	 * @throws BaseException
	 * @throws:
	 */
	private Message getLockerIdAndUpDateSterilizer(List<HosSterilizerContainer> containers, List<String> lockerId,
			Message message) throws BaseException {
		for (HosSterilizerContainer container : containers) {
			lockerId.add(container.getLockerNumber());
			message.setOperation(lockerId);
			hosSterilizerContainerDao.updateContainerState(container.getId(), BizConst.STATE.ADMIN_ALLOTING);
		}
		return message;
	}

	@Override
	public int openContainerShoesLog(String deviceNumber, String[] numbers, SessionUser sessionUser)
			throws BaseException {
		HosBusiness business = new HosBusiness();// 日志
		Date date = DateUtils.getLocalDate();// 当前时间
		Message message = new Message();
		Map<String, Object> map = new HashMap<>();
		List<String> oprations = new ArrayList<>();
		if (numbers.length <= 0) {
			throw new BusinessException("请选择您要打开的小柜");
		}
		// 得到用户的详细信息
		AuthUserDetail authUserDetail = hosTheaterDao.loadUserDetailById(sessionUser.getUserId());
		int count = 0;
		for (String number : numbers) {
			business.setUserId(sessionUser.getUserId());
			business.setOperateTime(date);
			business.setDeviceType(BizConst.DEVICE_TYPE.STERILIZER);
			business.setDeviceNumber(deviceNumber);
			business.setConNumber(number);
			business.setOperationType(BizConst.OPERATION_TYPE.OPENSTERILIZER);
			String time = DateUtils.FormatDateToYear_Month_Day(date);
			String des = authUserDetail.getUserName() + "在" + time + "打开了编号为【" + deviceNumber + "】的消毒鞋柜内的【" + number
					+ "】号柜";
			business.setDescription(des);
			hosBusinessDao.insertDeviceLog(business);
			// TO-DO转换调用命令
			oprations.add(number);
			count++;
		}

		WebSocketSession session = WebSocketChanne.deviceMap.get("ShoeBox");
		if (session == null) {
			throw new BusinessException("设备未连接");
		}
		message.setDeviceID(deviceNumber);
		message.setMessage("管理员开柜");
		message.setOperation(oprations);
		message.setErrorMessage("null");
		map.put("ErrorMessage", message.getErrorMessage());
		map.put("Parameter", message);
		String sendToDevice = JSON.toJSONString(map);

		try {
			session.sendMessage(new TextMessage(sendToDevice));
		} catch (IOException e) {
			logger.error(e);
		}
		return count;
	}

	@Override
	public int selectRecordByUseridAndSteNumber(String userid, String steNumber) throws BaseException {
		return hosSterilizerContainerDao.selectRecordByUseridAndSteNumber(userid, steNumber);

	}

}
