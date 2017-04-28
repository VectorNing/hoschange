package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.common.util.DateUtils;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosClothesPressBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.dao.HosClothesPressDao;
import com.sesxh.hoschange.biz.hos.dao.HosRoomDao;
import com.sesxh.hoschange.biz.hos.dao.HosTheaterDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPressContainer;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.common.auth.session.SessionUser;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.common.websocket.WebSocketChanne;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.DOORANDLOCK;
import com.sesxh.hoschange.global.BizConst.DOORANDLOCKSTATE;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosClothesPressBpoImp")
public class HosClothesPressBpoImp extends SesframeBaseBpo implements HosClothesPressBpo {
	@Autowired
	private HosClothesPressDao hosClothesPressDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private HosWardrobeDao hosWardrobeDao;
	@Autowired
	private HosRoomDao hosRoomDao;
	@Autowired
	private HosBusinessDao hosBusinessDao;
	@Autowired
	private HosTheaterDao hosTheaterDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Map<String, Object> queryClothesPressAll(Map<String, Object> params) throws BaseException {
		Map<String, Object> map = new HashMap<>();
		ParamMap pm = ParamMap.filterParam(params);
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		Long total = hosClothesPressDao.queryClothesPressCount(pm);
		List<Map<String, Object>> maps = hosClothesPressDao.queryClothesPressAll(pm);
		map.put("total", total);
		map.put("rows", maps);
		return map;
	}

	@Override
	public int addClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		validateClothesPress(hosClothesPress);
		String theaterNumber = hosClothesPress.getTheaterNumber();
		Integer roomNumber = hosClothesPress.getRoomId();
		HosRoom hosRoom = hosRoomDao.selectHosRoomByTheaterAndRoomId(theaterNumber, roomNumber);
		hosClothesPress.setRoomId(hosRoom.getId());

		int num = 1;
		String id = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		hosClothesPress.setId(id);
		HosClothesPressContainer clothesPressContainer = new HosClothesPressContainer();
		Integer total = hosClothesPress.getTotal();
		Integer startDoor = hosClothesPress.getStartDoor();
		for (int i = 0; i < total; i++) {
			String number = "000000" + num;
			number = number.substring(number.length() - 3);

			String doorNumber = "000000" + startDoor;
			doorNumber = doorNumber.substring(doorNumber.length() - 3);
			clothesPressContainer.setClothesPressId(id);
			clothesPressContainer.setLockerNumber(number);
			clothesPressContainer.setDoorNumber(doorNumber);
			hosClothesPressDao.addClothesPressContainer(clothesPressContainer);
			num++;
			startDoor++;

		}

		return hosClothesPressDao.addClothesPress(hosClothesPress);
	}

	/**
	 * 校验
	 * 
	 * @Title: validateSterilizer
	 * @author Ning
	 * @data:2017年1月19日
	 * @return:void
	 * @throws:
	 */
	private void validateClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		String description = hosClothesPress.getDescription();
		ZAssert.hasText(hosClothesPress.getNumber(), "手术衣柜编号不能为空！");

		ZAssert.lenLessTan(hosClothesPress.getNumber(), 100, "手术衣柜编号不能超过100字符");
		if (StringUtils.hasText(description)) {
			ZAssert.lenLessTan(description, 200, "手术衣柜描述不能超过200字符");
		}
	}

	public HosClothesPress queryClothesPressById(String id) throws BaseException {
		return hosClothesPressDao.queryClothesPressById(id);
	}

	@Override
	public int updateHosClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		String theaterNumber = hosClothesPress.getTheaterNumber();
		Integer roomNumber = hosClothesPress.getRoomId();
		HosRoom hosRoom = hosRoomDao.selectHosRoomByTheaterAndRoomId(theaterNumber, roomNumber);
		hosClothesPress.setRoomId(hosRoom.getId());

		return hosClothesPressDao.updateHosClothesPress(hosClothesPress);
	}

	@Override
	public Map<String, Object> allotClothesPressContainer(Integer roomNumber, Integer userId, Map<String, Object> map)
			throws BaseException {
		// 所有空柜容器
		List<HosClothesPressContainer> allClothesPressContainers = new ArrayList<>();
		// 要分配的衣柜
		HosClothesPress hosClothesPress = new HosClothesPress();
		// 要分配的小柜
		HosClothesPressContainer clothesPressContainer = new HosClothesPressContainer();
		// 判断该用户在该更衣室有没有绑定储物柜
		clothesPressContainer = hosClothesPressDao.selectBindingClothesPressContainerByUserIdAndRoomId(userId,
				roomNumber, BizConst.BOOLEAN.TRUE);
		if (clothesPressContainer != null) {// 一对一绑定，固定分配储物柜
			hosClothesPress = hosClothesPressDao.queryClothesPressById(clothesPressContainer.getClothesPressId());
			// 更改储物柜小柜状态
			hosClothesPressDao.updateBindingClothesPressContainerStateById(clothesPressContainer.getId(),
					BizConst.STATE.USER_USE);
			map.put("clothesPress", hosClothesPress.getNumber());
			map.put("lockerNumber", clothesPressContainer.getLockerNumber());
		} else {// 随机分配该更衣室下的空储物柜
			allClothesPressContainers = hosClothesPressDao.selectNotBindingClothesPressContainerByRoomId(roomNumber);// 该更衣室下所有未绑定空柜信息
			// 校验衣柜信息
			validateallotClothesPressContainer(allClothesPressContainers);

			// 随机获取空柜
			int random = (int) (Math.random() * (allClothesPressContainers.size()));
			clothesPressContainer = allClothesPressContainers.get(random);
			hosClothesPress = hosClothesPressDao.queryClothesPressById(clothesPressContainer.getClothesPressId());
			// 更改储物柜小柜状态
			hosClothesPressDao.updateHosClothesPressContainerById(clothesPressContainer.getId(), userId);
			// 记录分配信息
			map.put("clothesPress", hosClothesPress.getNumber());
			map.put("lockerNumber", clothesPressContainer.getLockerNumber());
		}
		return map;
	}

	private void validateallotClothesPressContainer(List<HosClothesPressContainer> clothesPressContainers)
			throws BusinessException {
		if (clothesPressContainers == null || clothesPressContainers.size() == 0) {
			throw new BusinessException("当前衣柜已满，请稍后领取");
		}
	}

	@Override
	public Long selectClothesFromClothesPressById(String id) throws BaseException {
		return hosClothesPressDao.selectClothesFromClothesPressById(id);
	}

	@Override
	public int deleteClothesPressById(String id) throws BaseException {
		hosClothesPressDao.deleteClothesPressByIdContainer(id);
		return hosClothesPressDao.deleteClothesPressById(id);
	}

	@Override
	public int updateStateAndUserIdByUserId(Integer userId) throws BaseException {
		return hosClothesPressDao.updateStateAndUserIdByUserId(userId);
	}

	@Override
	public Map<String, Object> selectClothesPressByUserIdAndNumber(Integer userId, String ClothesPressNum)
			throws BaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = hosClothesPressDao.selectClothesPressByUserIdAndNumber(userId,
				ClothesPressNum);
		if (list == null || list.size() == 0) {
			map.put("state", 0);// 0该用户不能打开衣柜
		} else {
			map = list.get(0);
			map.put("state", 1);// 1该用户能打开衣柜
		}
		return map;
	}

	@Override
	public Long selectClothesPressByNumber(String id, String number) throws BaseException {
		return hosClothesPressDao.selectClothesPressByNumber(id, number);
	}

	@Override
	public Map<String, Object> selectOpeByCardNumAndNumber(String number, String cardNum) throws BaseException {
		Map<String, Object> map = Maps.newHashMap();
		ZAssert.hasText(cardNum, "没有获取到磁卡信息，请查证！");
		ZAssert.hasText(number, "没有获取到机器信息，请查证！");
		AuthUser authUser = authUserDao.findByLoginName2(cardNum);
		HosWardrobe hosWardrobe = hosWardrobeDao.selectHosWardrobeEnabledByNumber(number);
		if (authUser == null) {
			ZAlert.Error("没有获取到用户信息，请联系相关管理员！");
		}
		Integer userId = authUser.getId();// 用户id
		String theNumber = hosWardrobe.getTheaterNumber();// 手术室编号
		List<Map<String, Object>> list = hosClothesPressDao.selectClothesPressContainerByUserIdAndNumber(theNumber,
				userId);
		if (list == null || list.size() <= 0) {
			ZAlert.Error("当前没有查询到您的物品，请确定是否放入或者联系相关管理员！");
		}
		for (Map<String, Object> a : list) {
			map.put("number", a.get("number"));
			map.put("steNumber", a.get("lockerNumber"));
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> selectClothesPressBySteNumber(String steNumber) throws BaseException {
		ZAssert.hasText(steNumber, "消毒柜编号不能为空，请查证！");
		return hosClothesPressDao.selectClothesPressBySteNumber(steNumber);
	}

	@Override
	public int updateLockClothesPress(Integer[] ids) throws BaseException {
		int i = 0;
		for (Integer id : ids) {
			i += hosClothesPressDao.updateLockClothesPress(id);
		}
		return i;
	}

	@Override
	public int updateStoplockClothesPress(Integer[] ids) throws BaseException {
		int i = 0;
		for (Integer id : ids) {
			i += hosClothesPressDao.updateStoplockClothesPress(id);
		}
		return i;
	}

	@Override
	public int updateBindingClothesPressAndUser(Integer id, Integer userId) throws BaseException {
		AuthUserDetail authUserDetail = authUserDao.selectUserDetailByAuthUserId(userId);
		HosClothesPress hosClothesPress = hosClothesPressDao.selectClothesPressById(id);
		HosClothesPressContainer hosClothesPressContainer = hosClothesPressDao
				.selectBindingClothesPressByUserIdAndTheaterNumber(userId, hosClothesPress.getTheaterNumber());
		if (hosClothesPressContainer != null) {
			throw new BusinessException("该用户已经绑定小柜");
		}
		return hosClothesPressDao.updateBindingClothesPressAndUser(id, userId, authUserDetail.getShoesSize());
	}

	@Override
	public int updateStopBindingClothesPressAndUser(Integer id) throws BaseException {
		return hosClothesPressDao.updateStopBindingClothesPressAndUser(id);
	}

	@Override
	public Message openClothesPressContainerByUser(AuthUser authUser, Message message) throws BaseException {
		List<String> lockerId = new ArrayList<String>();
		Integer userId = authUser.getId();
		String deviceNumber = message.getDeviceID();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		// 要分配的衣柜
		HosClothesPress hosClothesPress = new HosClothesPress();
		hosClothesPress = hosClothesPressDao.selectClothesPressByNumber(deviceNumber);

		if (hosClothesPress == null) {
			message.setErrorMessage("没有该设备信息，请联系管理员");
			return message;
		}
		HosClothesPressContainer clothesPressContainer = hosClothesPressDao
				.selectBindingClothesPressContainerByUserIdAndRoomId(userId, hosClothesPress.getRoomId(), null);
		if (clothesPressContainer == null) {// 没有绑定，没有分配，随机分配空柜子
			return randomAssortment(hosClothesPress, clothesPressContainer, message, userId);
		}
		String deviceId = hosClothesPress.getNumber();
		// 获取该设备对应socket连接
		WebSocketSession session = WebSocketChanne.deviceMap.get(deviceId);
		if (session == null) {
			message.setErrorMessage("设备" + deviceId + "未连接");
			return message;
		} else {
			// 更改储物柜小柜状态
			hosClothesPressDao.updateHosClothesPressContainerById(clothesPressContainer.getId(), userId);
			// 给开门设备发送指令
			if (DOORANDLOCKSTATE.STATE.equals(DOORANDLOCK.YIYANG)) {
				lockerId.add(clothesPressContainer.getDoorNumber());
			} else if (DOORANDLOCKSTATE.STATE.equals(DOORANDLOCK.BUYIYANG)) {
				lockerId.add(clothesPressContainer.getLockerNumber());
			}
			parameter.put("DoorId", lockerId);
			data.put("Method", "OpenDoor");
			data.put("Parameter", parameter);
			String str = JSON.toJSONString(data);
			try {
				session.sendMessage(new TextMessage(str));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 返回原设备显示信息
			message.setMessage("柜子" + clothesPressContainer.getDoorNumber() + "已打开");
			return message;
		}
	}

	@Override
	public int openContainerClothesPressLog(String deviceNumber, String[] numbers, SessionUser sessionUser)
			throws BaseException {
		HosBusiness business = new HosBusiness();// 日志
		Date date = DateUtils.getLocalDate();// 当前时间
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		List<String> lockerId = new ArrayList<>();
		if (numbers.length <= 0) {
			throw new BusinessException("请选择您要打开的小柜");
		}
		// 得到用户的详细信息
		AuthUserDetail authUserDetail = hosTheaterDao.loadUserDetailById(sessionUser.getUserId());
		for (String number : numbers) {
			business.setUserId(sessionUser.getUserId());
			business.setOperateTime(date);
			business.setDeviceType(BizConst.DEVICE_TYPE.CLOTHESPRESS);
			business.setDeviceNumber(deviceNumber);
			business.setConNumber(number);
			business.setOperationType(BizConst.OPERATION_TYPE.OPENSTERILIZER);
			String time = DateUtils.FormatDateToYear_Month_Day(date);
			String des = authUserDetail.getUserName() + "在" + time + "打开了编号为【" + deviceNumber + "】的储物柜内的【" + number
					+ "】号柜";
			business.setDescription(des);
			hosBusinessDao.insertDeviceLog(business);
			// 根据门号和设备号查出锁号
			HosClothesPressContainer clothesPressContainer = hosClothesPressDao
					.selectLockerIdBydoorNumberAndDeviceId(deviceNumber, number);
			lockerId.add(clothesPressContainer.getLockerNumber());
		}
		WebSocketSession session = WebSocketChanne.deviceMap.get(deviceNumber);
		if (session == null) {
			throw new BusinessException("设备未连接");
		}

		parameter.put("DoorId", lockerId);
		data.put("Method", "OpenDoor");
		data.put("Parameter", parameter);
		String str = JSON.toJSONString(data);

		try {
			session.sendMessage(new TextMessage(str));
		} catch (IOException e) {
			logger.error(e);
		}
		return 0;
	}

	private Message randomAssortment(HosClothesPress hosClothesPress, HosClothesPressContainer clothesPressContainer,
			Message message, Integer userId) throws BaseException {
		// 所有空柜容器
		List<HosClothesPressContainer> allClothesPressContainers = new ArrayList<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		List<String> lockerId = new ArrayList<>();
		// 该更衣室下所有未绑定空柜信息
		allClothesPressContainers = hosClothesPressDao
				.selectNotBindingClothesPressContainerByRoomId(hosClothesPress.getRoomId());
		// 校验衣柜信息
		validateallotClothesPressContainer(allClothesPressContainers);
		// 随机获取空柜
		int random = (int) (Math.random() * (allClothesPressContainers.size()));
		clothesPressContainer = allClothesPressContainers.get(random);
		hosClothesPress = hosClothesPressDao.queryClothesPressById(clothesPressContainer.getClothesPressId());
		String deviceId = hosClothesPress.getNumber();
		// 获取该设备对应socket连接
		WebSocketSession session = WebSocketChanne.deviceMap.get(deviceId);
		if (session == null) {
			message.setErrorMessage("设备" + deviceId + "未连接");
			return message;
		} else {
			// 更改储物柜小柜状态
			hosClothesPressDao.updateHosClothesPressContainerById(clothesPressContainer.getId(), userId);
			if (DOORANDLOCKSTATE.STATE.equals(DOORANDLOCK.YIYANG)) {
				lockerId.add(clothesPressContainer.getDoorNumber());
			} else if (DOORANDLOCKSTATE.STATE.equals(DOORANDLOCK.BUYIYANG)) {
				lockerId.add(clothesPressContainer.getLockerNumber());
			}
			// 给开门设备发送指令
			parameter.put("DoorId", lockerId);
			data.put("Method", "OpenDoor");
			data.put("Parameter", parameter);
			String str = JSON.toJSONString(data);
			try {
				session.sendMessage(new TextMessage(str));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 返回原设备显示信息
			message.setMessage("柜子" + clothesPressContainer.getDoorNumber() + "已打开");
			return message;
		}

	}

	@Override
	public Object getDoorIdByDeviceId(String deviceNumber) throws BaseException {
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> parameter = new HashMap<>();
		List<String> doors = new ArrayList<>();
		List<HosClothesPressContainer> containers = hosClothesPressDao.selectDoorNumbersByDeviceId(deviceNumber);
		for (HosClothesPressContainer clothesPressContainer : containers) {
			doors.add(clothesPressContainer.getDoorNumber());
		}
		data.put("Method", "DoorId");
		parameter.put("DoorId", doors);
		data.put("Parameter", parameter);
		return data;
	}
}
