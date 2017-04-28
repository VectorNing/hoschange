package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.sesxh.base.BaseException;
import com.sesxh.common.util.DateUtils;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosSterilizerBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerDao;
import com.sesxh.hoschange.biz.hos.dao.HosTheaterDao;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.sys.dao.AuthPermissionDao;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.dao.DictionaryDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.Message;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.ISCALLBACK;
import com.sesxh.hoschange.global.BizConst.RecyclingBin;
import com.sesxh.hoschange.global.BizConst.SIGN;
import com.sesxh.hoschange.global.BizConst.SYSCONFIG;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosSterilizerBpoImpl")
public class HosSterilizerBpoImpl extends SesframeBaseBpo implements HosSterilizerBpo {

	@Autowired
	private HosSterilizerDao hosSterilizerDao;
	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;
	@Autowired
	private HosTheaterDao hosTheaterDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private HosBusinessDao hosBusinessDao;
	@Autowired
	private HosRecordDao hosRecordDao;
	@Autowired
	private HosRecoveryGoodsDao hosRecoveryGoodsDao;
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public int insertHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		validateSterilizer(hosSterilizer);
		/**
		 * 
		 */
		String theaterNumber = hosSterilizer.getTheaterNumber();

		Integer row = hosSterilizer.getRows();
		Integer column = hosSterilizer.getColumns();
		Integer total = hosSterilizer.getTotal();
		Integer startDoor = hosSterilizer.getStartDoor();
		HosSterilizerContainer hos = new HosSterilizerContainer();
		int num = 1;
		String lockerNumber = "", doorNumber = "";
		hos.setSteNumber(hosSterilizer.getNumber());
		for (int i = 0; i < total; i++) {
			lockerNumber = "000000" + num;
			doorNumber = "000000" + startDoor;
			lockerNumber = lockerNumber.substring(lockerNumber.length() - 3);
			doorNumber = doorNumber.substring(doorNumber.length() - 3);
			hos.setLockerNumber(lockerNumber);
			hos.setDoorNumber(doorNumber);
			hosSterilizerContainerDao.insertSterilizerContainer(hos);
			num++;
			startDoor++;
		}

		if (theaterNumber != null && !theaterNumber.isEmpty()) {
			hosSterilizer.setState(BizConst.STATE.USE);
		}
		return hosSterilizerDao.insertHosSterilizer(hosSterilizer);
	}

	@Override
	public int deleteHosSterilizerById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		HosSterilizer hosSterilizer = hosSterilizerDao.selectHosShoesSterilizerById(id);
		String number = hosSterilizer.getNumber();// 消毒柜编号
		ZAssert.hasText(number, "没有找到相对应的消毒柜编号，请查证！");
		// 删除小柜
		hosSterilizerContainerDao.deleteContainerBySteNumber(number);
		return hosSterilizerDao.deleteHosSterilizerById(id);
	}

	@Override
	public int updateHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		validateSterilizer(hosSterilizer);

		String oldNum = hosSterilizer.getOldNumber();// 原编号
		String newNum = hosSterilizer.getNumber();// 修改的编号
		if (oldNum.equals(hosSterilizer.getNumber())) {// 编码不变
		} else {
			hosSterilizerContainerDao.updateContainerBySteNumber(oldNum, newNum);// 修改小柜
			hosSterilizerDao.updateShoSteSteNumberByNumber(oldNum, newNum);// 模式二
		}
		return hosSterilizerDao.updateHosSterilizer(hosSterilizer);
	}

	@Override
	public DataSet queryHosSterilizerSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		// 1:模式一（尺码）2://模式二（编码）
		DataSet ds = DataSet.newDS(hosSterilizerDao.HosSterilizerCount(pm),
				hosSterilizerDao.selectAllHosSterilizer(pm));
		return ds;
	}

	@Override
	public Long selectSteByNumber(String number, Integer id) throws BaseException {
		ZAssert.hasText(number, "消毒柜编号不能为空！");
		return hosSterilizerDao.selectSteByNumber(number, id);
	}

	@Override
	public void allotShoesToSterilizer(List<Map<String, Object>> list) throws BaseException {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			// Map<String, Object> map = list.get(i);
			// Integer shId = Integer.valueOf((String)map.get("shId"));
			// Integer stId = Integer.valueOf((String)map.get("stId"));
			// Integer count = Integer.valueOf((String)map.get("count"));
			// HosShoesSterilizer hosShoesSterilizer = new HosShoesSterilizer();
			// hosShoesSterilizer.setShNumber(shId);;//shNumber
			// hosShoesSterilizer.setStSize(stId);//stSize
			// hosShoesSterilizer.setCount(count);
			// hosSterilizerDao.allotShoesToSterilizer(hosShoesSterilizer);
		}
	}

	@Override
	public HosSterilizer findHosShoesSterilizerById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosSterilizerDao.selectHosShoesSterilizerById(id);
	}

	private void validateSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		String description = hosSterilizer.getDescription();
		ZAssert.hasText(hosSterilizer.getNumber(), "消毒柜编号不能为空！");

		ZAssert.lenLessTan(hosSterilizer.getNumber(), 100, "消毒柜编号不能超过100字符");
		if (StringUtils.hasText(description)) {
			ZAssert.lenLessTan(description, 200, "消毒鞋柜描述不能超过200字符");
		}
	}

	public Long selectShoesFromSterilizerByNumber(String number) throws BaseException {
		ZAssert.hasText(number, "消毒柜编号不能为空！");

		return hosSterilizerDao.selectShoesFromSterilizerByNumber(number);
	}

	public DataSet loadSterilizerListByTheNumber(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet ds = null;
		ParamMap pm = ParamMap.filterParam(params);
		String isInSte = pm.getStrParam("isInSte");
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		if ("true".equals(isInSte)) {
			ds = DataSet.newDS(hosSterilizerDao.selectSterCountByTheNumber(pm),
					hosSterilizerDao.selectSterListByTheNumber(pm));
		} else {
			ds = DataSet.newDS(hosSterilizerDao.selectNotSterCountByTheNumber(pm),
					hosSterilizerDao.selectNotSterListByTheNumber(pm));
		}
		return ds;
	}

	public int assignTheaterToSte(String number, Integer[] ids) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空");
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		HosSterilizer hosSterilizer = new HosSterilizer();
		List<Integer> id = Arrays.asList(ids);
		int flag = 0;
		hosSterilizer.setTheaterNumber(number);
		hosSterilizer.setState(BizConst.STATE.USE);
		for (Integer a : id) {
			hosSterilizer.setId(a);
			flag = hosSterilizerDao.assignTheaterToSte(hosSterilizer);
		}
		return flag;
	}

	public int removeSteFromTheater(String number, Integer[] ids) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空");
		HosSterilizer hosSterilizer = new HosSterilizer();
		int flag = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		List<Integer> id = Arrays.asList(ids);
		hosSterilizer.setState(BizConst.STATE.NOTUSE);
		for (Integer a : id) {
			hosSterilizer.setId(a);
			flag = hosSterilizerDao.removeSteFromTheater(hosSterilizer);
		}
		return flag;
	}

	public List<HosSterilizer> loadSterilizerByThNumber(String number) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空");
		List<HosSterilizer> lists = hosSterilizerDao.selectSterilizerByThNumber(number);
		for (HosSterilizer hos : lists) {
			List<HosSterilizerContainer> hosSte = hosSterilizerContainerDao
					.selectSterilizerContainerOneBySteNumber(hos.getNumber());
			List<Map<String, Object>> size = hosSterilizerContainerDao.selectCountShoesSize(hos.getNumber());
			hos.setSizes(size);
			hos.setContainerList(hosSte);
		}
		return lists;
	}

	public DataSet selectHosSterilizerMonitored(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		Long count;
		List<Map<String, Object>> maps = new ArrayList<>();
		Integer userId = pm.getIntegerParam("userId");
		AuthUser authUser = authUserDao.selectAuthUserByUserId(userId);
		List<AuthPermission> authPermissions = authPermissionDao.selectPermListByUserId(userId);
		if (BizConst.SFCJGLY.TURE.equals(authUser.getSfcjgly())
				|| isOrNoAdmin(BizConst.PERMISSION_CODE.BACKADMIN, authPermissions)) {
			count = hosSterilizerDao.HosSterilizerAllCountMonitored(pm);
			maps = hosSterilizerDao.selectAllHosSterilizerMonitored(pm);
		} else {
			count = hosSterilizerDao.HosSterilizerCountMonitoredByUserId(pm);
			maps = hosSterilizerDao.selectHosSterilizerMonitoredByUserId(pm);
		}

		DataSet ds = DataSet.newDS(count, maps);
		return ds;
	}

	@Override
	public HosSterilizer selectHosSterilizerBySteNumber(String SteNumber) throws BaseException {
		return hosSterilizerDao.selectSterilizerByNumber(SteNumber);
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
	public Message bindingReceiveShoeByUser(AuthUser authUser, Message message) throws BaseException {
		HosBusiness business = new HosBusiness();// 日志
		HosSterilizerContainer container = new HosSterilizerContainer();// 小柜，更新小柜状态
		HosRecoveryGoods hosRecoveryGoods = new HosRecoveryGoods();// 回收记录，领取鞋子，生成一条回收记录
		HosRecord hosRecord = new HosRecord();// 签到记录，领取鞋子，签到
		Date date = DateUtils.getLocalDate();// 当前时间
		List<String> lockerId = new ArrayList<>();// 开门的小柜信息

		String deviceNumber = message.getDeviceID();// 设备号
		Integer userId = authUser.getId();//
		HosSterilizer hosSterilizer = hosSterilizerDao.selectSterilizerByNumber(deviceNumber);
		String theaterNumber = hosSterilizer.getTheaterNumber();// 手术区编号

		// 得到用户的详细信息
		AuthUserDetail authUserDetail = hosTheaterDao.loadUserDetailById(userId);
		if (authUserDetail == null) {
			message.setMessageType("12");
			message.setErrorMessage("系统中查询不到用户信息，请联系管理员！！！");
			return message;
		}
		Integer shoeSize = authUserDetail.getShoesSize();// 鞋子尺码编码
		if (shoeSize == 0 || shoeSize == null) {
			message.setMessageType("08");
			message.setErrorMessage("您还没有维护鞋码，请联系管理员！！！");
			return message;
		}
		Dictionary dictionary = dictionaryDao.selectDictByTypeAndCode("shoes", shoeSize.toString());
		String shoeName = dictionary.getName();// 鞋子尺码名字

		// 固定分配
		container = hosSterilizerContainerDao.selectBindingContainer(deviceNumber, userId);
		if (container == null) {
			message.setMessageType("09");
			message.setErrorMessage("您的柜子不在此设备上");
			return message;
		}
		if (BizConst.BOOLEAN.TRUE.equals(container.getYesOrNoLock())) {// 柜子被锁定
			message.setMessageType("10");
			message.setErrorMessage("您的柜子被锁定");
			return message;
		}
		message.setMessageType("01");
		lockerId.add(container.getLockerNumber());
		message.setOperation(lockerId);
		message.setErrorMessage("请打开" + deviceNumber + "号柜" + container.getLockerNumber() + "号小柜");

		// 更新小柜状态
		container.setState(BizConst.STATE.USER_USE);
		hosSterilizerContainerDao.updataBindingContainerState(container);

		// 记录领取日志
		business.setUserId(userId);
		business.setOperateTime(date);
		business.setDeviceType(message.getDeviceType());
		business.setSize(shoeSize);
		business.setDeviceNumber(deviceNumber);
		business.setConNumber(container.getLockerNumber());
		business.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = authUserDetail.getUserName() + "在" + time + "在编号为【" + deviceNumber + "】的消毒鞋柜内的【"
				+ container.getLockerNumber() + "】号柜， 领取 " + shoeName + "码手术鞋";
		business.setDescription(des);
		hosBusinessDao.insertDeviceLog(business);
		HosRecord rosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");
		if (rosRecord == null || SIGN.OUT.equals(rosRecord.getSign())) {
			// 签到
			hosRecord.setUserId(userId);
			hosRecord.setSign(BizConst.SIGN.IN);
			hosRecord.setSignInTime(new Date());
			hosRecord.setDeviceType(message.getDeviceType());
			hosRecord.setDeviceNumber(deviceNumber);
			hosRecord.setTheaterNumber(theaterNumber);// 手术室编号，用于登录是判断是否签到签退
			hosRecordDao.insertHosRecordService(hosRecord);// 签到
		}
		SysConfig sysConfig = sysConfigDao.querySysConfigByConfigName(SYSCONFIG.RECYCLINGBIN);
		String config = sysConfig.getConfig();
		// 生成回收记录
		HosRecord record = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");
		Integer recordId = record.getId();
		hosRecoveryGoods.setSize(shoeSize);
		hosRecoveryGoods.setTheNumber(theaterNumber);
		hosRecoveryGoods.setType(BizConst.GOODS_TYPE.SHOES);
		hosRecoveryGoods.setUserId(userId);
		hosRecoveryGoods.setRecordId(recordId);
		hosRecoveryGoods.setState(ISCALLBACK.NO);
		if (RecyclingBin.NO.equals(config)) {
			hosRecoveryGoods.setState(ISCALLBACK.YES);
		}
		hosRecoveryGoodsDao.insertHosRecoveryGoods(hosRecoveryGoods);
		// 返回开柜信息
		return message;
	}

	@Override
	public Message randomReceiveShoeByUser(AuthUser authUser, Message message) throws BaseException {
		HosBusiness business = new HosBusiness();// 日志
		List<HosSterilizerContainer> localContainers = new ArrayList<>();// 该鞋柜中符合鞋码的所有小柜
		List<HosSterilizerContainer> allContainers = new ArrayList<>();// 其他鞋柜中符合鞋码的所有小柜
		HosSterilizerContainer container = new HosSterilizerContainer();// 小柜，更新小柜状态
		HosRecoveryGoods hosRecoveryGoods = new HosRecoveryGoods();// 回收记录，领取鞋子，生成一条回收记录
		HosRecord hosRecord = new HosRecord();// 签到记录，领取鞋子，签到
		Date date = DateUtils.getLocalDate();// 当前时间
		List<String> lockerId = new ArrayList<>();// 开门的小柜信息

		String deviceNumber = message.getDeviceID();// 设备号
		Integer userId = authUser.getId();//
		HosSterilizer hosSterilizer = hosSterilizerDao.selectSterilizerByNumber(deviceNumber);
		String theaterNumber = hosSterilizer.getTheaterNumber();// 手术区编号

		// 得到用户的详细信息
		AuthUserDetail authUserDetail = hosTheaterDao.loadUserDetailById(userId);
		if (authUserDetail == null) {
			message.setMessageType("12");
			message.setErrorMessage("系统中查询不到用户信息，请联系管理员！！！");
			return message;
		}
		Integer shoeSize = authUserDetail.getShoesSize();// 鞋子尺码编码
		if (shoeSize == 0 || shoeSize == null) {
			message.setMessageType("08");
			message.setErrorMessage("您还没有维护鞋码，请联系管理员！！！");
			return message;
		}
		Dictionary dictionary = dictionaryDao.selectDictByTypeAndCode("shoes", shoeSize.toString());
		String shoeName = dictionary.getName();// 鞋子尺码名字

		// 优先查询这个柜子里能用的所有的小柜子
		localContainers = hosSterilizerContainerDao.selectRandomContainer(deviceNumber, shoeSize);
		if (localContainers.size() == 0 || localContainers == null) {

			allContainers = hosSterilizerContainerDao.selectRandomContainer("", shoeSize);
			if (allContainers.size() == 0 || allContainers == null) {
				message.setMessageType("11");
				message.setErrorMessage("本手术区没有您的尺码");
				return message;
			}
			int randomNumber = (int) (Math.random() * (allContainers.size()));// 获取随机数
			container = allContainers.get(randomNumber);
			deviceNumber = container.getSteNumber();
			message.setDeviceID(deviceNumber);
			lockerId.add(container.getLockerNumber());
			message.setMessageType("01");
			message.setOperation(lockerId);
			message.setErrorMessage("请打开" + deviceNumber + "号柜" + container.getLockerNumber() + "号小柜");

		}

		else {
			int randomNumber = (int) (Math.random() * (localContainers.size()));// 获取随机数
			container = localContainers.get(randomNumber);// 随机分配一个小柜
			lockerId.add(container.getLockerNumber());
			message.setOperation(lockerId);
			message.setMessageType("01");
			message.setErrorMessage("请打开" + container.getSteNumber() + "号柜" + container.getLockerNumber() + "号小柜");
		}
		// 更新随机分配小柜状态
		container.setUserId(userId);
		container.setState(BizConst.STATE.USER_USE);
		hosSterilizerContainerDao.updateContainer(container);

		// 记录领取日志
		business.setUserId(userId);
		business.setOperateTime(date);
		business.setDeviceType(message.getDeviceType());
		business.setSize(shoeSize);
		business.setDeviceNumber(deviceNumber);
		business.setConNumber(container.getLockerNumber());
		business.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = authUserDetail.getUserName() + "在" + time + "在编号为【" + deviceNumber + "】的消毒鞋柜内的【"
				+ container.getLockerNumber() + "】号柜， 领取 " + shoeName + "码手术鞋";
		business.setDescription(des);
		hosBusinessDao.insertDeviceLog(business);
		HosRecord rosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");
		if (rosRecord == null || SIGN.OUT.equals(rosRecord.getSign())) {
			// 签到
			hosRecord.setUserId(userId);
			hosRecord.setSign(BizConst.SIGN.IN);
			hosRecord.setSignInTime(new Date());
			hosRecord.setDeviceType(message.getDeviceType());
			hosRecord.setDeviceNumber(deviceNumber);
			hosRecord.setTheaterNumber(theaterNumber);// 手术室编号，用于登录是判断是否签到签退
			hosRecordDao.insertHosRecordService(hosRecord);// 签到
		}

		SysConfig sysConfig = sysConfigDao.querySysConfigByConfigName(SYSCONFIG.RECYCLINGBIN);
		String config = sysConfig.getConfig();
		// 生成回收记录
		HosRecord record = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");
		Integer recordId = record.getId();
		hosRecoveryGoods.setSize(shoeSize);
		hosRecoveryGoods.setTheNumber(theaterNumber);
		hosRecoveryGoods.setType(BizConst.GOODS_TYPE.SHOES);
		hosRecoveryGoods.setUserId(userId);
		hosRecoveryGoods.setRecordId(recordId);
		hosRecoveryGoods.setState(ISCALLBACK.NO);
		if (RecyclingBin.NO.equals(config)) {
			hosRecoveryGoods.setState(ISCALLBACK.YES);
		}
		hosRecoveryGoodsDao.insertHosRecoveryGoods(hosRecoveryGoods);

		// 返回开柜信息
		return message;
	}
}
