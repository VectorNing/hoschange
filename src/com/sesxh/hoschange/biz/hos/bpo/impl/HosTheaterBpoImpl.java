package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.base.BaseException;
import com.sesxh.common.util.DateUtils;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosTheaterBpo;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.dao.HosOperationDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.dao.HosRoomDao;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerDao;
import com.sesxh.hoschange.biz.hos.dao.HosTheaterDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;
import com.sesxh.hoschange.biz.sys.dao.AuthPermissionDao;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.dao.DictionaryDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.ISCALLBACK;
import com.sesxh.hoschange.global.BizConst.RecyclingBin;
import com.sesxh.hoschange.global.BizConst.SYSCONFIG;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosTheaterBpoImpl")
public class HosTheaterBpoImpl extends SesframeBaseBpo implements HosTheaterBpo {

	@Autowired
	private HosTheaterDao hosTheaterDao;
	@Autowired
	private HosWardrobeDao hosWardrobeDao;
	@Autowired
	private HosSterilizerDao hosSterilizerDao;
	@Autowired
	private HosSchedulingDao hosSchedulingDao;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private HosBusinessDao hosBusinessDao;
	@Autowired
	private HosSterilizerContainerDao hosSterilizerContainerDao;
	@Autowired
	private HosWardrobeContainerDao hosWardrobeContainerDao;
	@Autowired
	private HosRecoveryGoodsDao hosRecoveryGoodsDao;
	@Autowired
	private HosOperationDao hosOperationDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private HosRecordDao hosRecordDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;

	@Autowired
	private HosRoomDao hosRoomDao;

	@Override
	public int insertHosTheater(HosTheater hosTheater) throws BaseException {
		validateTheater(hosTheater);
		HosRoom hosRoom = new HosRoom();
		String number = hosTheater.getNumber();

		hosRoom.setName(0);
		hosRoom.setTheaterNumber(number);
		hosRoom.setDescription("男更衣室");
		hosRoomDao.addHosRoom(hosRoom);

		hosRoom.setName(1);
		hosRoom.setDescription("女更衣室");
		hosRoomDao.addHosRoom(hosRoom);

		return hosTheaterDao.insertHosTheater(hosTheater);
	}

	@Override
	public int deleteHosTheaterById(Integer id, String number) throws BaseException {
		HosWardrobe wardrobe = new HosWardrobe();
		HosSterilizer sterilizer = new HosSterilizer();
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		ZAssert.hasText(number, "消毒柜编号不能为空！");

		String state = BizConst.STATE.NOTUSE;
		wardrobe.setOldNumber(number);
		wardrobe.setState(state);
		sterilizer.setOldNumber(number);
		sterilizer.setState(state);

		hosWardrobeDao.updateTheNumberStateByThenumber(wardrobe);
		hosSterilizerDao.updateTheNumberStateByThenumber(sterilizer);
		hosRoomDao.deleteHosRoomByTheaterNumber(number);
		return hosTheaterDao.deleteHosTheaterById(id);
	}

	@Override
	public int updateHosTheater(HosTheater hosTheater) throws BaseException {
		validateTheater(hosTheater);
		HosRoom hosRoom = new HosRoom();
		String oldNum = hosTheater.getOldNumber();// 原编号
		String newNum = hosTheater.getNumber();// 修改的编号

		hosRoom.setTheaterNumber(newNum);
		if (oldNum.equals(newNum)) {// 编码不变
		} else {
			hosWardrobeDao.updateWarTheaterNumberByNumber(oldNum, newNum);// 衣柜
			hosSterilizerDao.updateStrTheaterNumberByNumber(oldNum, newNum);// 鞋柜
			hosRoomDao.updateHosRoomByTheaterNumber(oldNum, newNum);
			hosSchedulingDao.updateSchThNumberByNumber(oldNum, newNum);// 排班
		}
		return hosTheaterDao.updateHosTheater(hosTheater);
	}

	@Override
	public DataSet queryHosTheaterSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String name = pm.getStrParam("name");
		String address = pm.getStrParam("address");
		if (name != null) {
			pm.updateParam("name", "%" + name + "%");
		}
		if (address != null) {
			pm.updateParam("address", "%" + address + "%");
		}
		DataSet ds = DataSet.newDS(hosTheaterDao.HosTheaterCount(pm), hosTheaterDao.selectAllHosTheater(pm));
		return ds;
	}

	public Long selectTheByNumber(String number, Integer id) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空！");
		return hosTheaterDao.selectTheByNumber(number, id);
	}

	public HosTheater selectTheaterById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosTheaterDao.selectTheaterById(id);
	}

	private void validateTheater(HosTheater hosTheater) throws BaseException {
		String description = hosTheater.getDescription();
		if (StringUtils.hasText(description)) {
			ZAssert.lenLessTan(description, 200, "消毒鞋柜描述不能超过100字符");
		}
		ZAssert.hasText(hosTheater.getNumber(), "消毒柜编号不能为空！");

		ZAssert.lenLessTan(hosTheater.getNumber(), 100, "消毒柜编号不能超过100字符");
	}

	public AuthUserDetail loadUserDetailById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosTheaterDao.loadUserDetailById(id);
	}

	public Map<String, Object> receiveDeviceShoByUserIdM1(Integer id, String number, String size) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		HosBusiness bus = new HosBusiness();
		HosSterilizerContainer hosCon = new HosSterilizerContainer();
		Date date = DateUtils.getLocalDate();
		Map<String, Object> map = Maps.newHashMap();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(id);
		Integer shoes = user.getShoesSize();
		if (size != null && !size.isEmpty()) {
			shoes = Integer.parseInt(size);
		}
		List<Map<String, Object>> sho = hosTheaterDao.selectShoesFromContainerBySize(shoes, number);
		if (sho == null || sho.size() <= 0) {
			ZAlert.Error("没有找到对应尺码的手术鞋！");
		}
		String lockerNumber = (String) sho.get(0).get("lockerNumber");
		Integer lockId = Integer.parseInt((String) sho.get(0).get("id"));// 小柜id
		map.put("shoes", sho.get(0).get("lockerNumber"));// 获取第一个小柜编号

		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.STERILIZER);
		bus.setSize(shoes);
		bus.setDeviceNumber(number);
		bus.setConNumber(lockerNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + number + "】的消毒鞋柜内的【" + lockerNumber + "】号柜， 领取 "
				+ shoes + "码手术鞋";
		bus.setDescription(des);

		hosCon.setId(lockId);
		hosCon.setUserId(id);
		hosCon.setState(BizConst.STATE.NOTUSE);
		hosCon.setShoesSize(null);

		hosBusinessDao.insertDeviceLog(bus);
		// hosSterilizerContainerDao.updateContainerById(lockId,
		// BizConst.STATE.NOTUSE, null);//更新小柜
		hosSterilizerContainerDao.updateContainer(hosCon);
		return map;
	}

	public int receiveShoesBySteNumberM1(Map<String, Object> params) throws BaseException {
		HosBusiness bus = new HosBusiness();
		Integer id = Integer.parseInt((String) params.get("id"));// 用户id
		String number = (String) params.get("number");
		String lockerNumber = (String) params.get("lockerNumber");
		Integer shoesSize = Integer.parseInt((String) params.get("shoesSize"));
		Date date = DateUtils.getLocalDate();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(id);
		HosSterilizerContainer con = hosSterilizerContainerDao.selectSteContainerByNumber(number, lockerNumber);
		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.STERILIZER);
		bus.setSize(shoesSize);
		bus.setDeviceNumber(number);
		bus.setConNumber(lockerNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + number + "】的消毒鞋柜内的【" + lockerNumber + "】号柜， 领取 "
				+ shoesSize + "码手术鞋";
		bus.setDescription(des);

		con.setUserId(id);
		con.setState(BizConst.STATE.NOTUSE);
		con.setShoesSize(null);

		// hosSterilizerContainerDao.updateContainerById(con.getId(),
		// BizConst.STATE.NOTUSE, null);//更新小柜
		hosSterilizerContainerDao.updateContainer(con);
		return hosBusinessDao.insertDeviceLog(bus);
	}

	public Map<String, Object> receiveDeviceOpeByUserIdM1(Integer id, String number) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		Map<String, Object> map = Maps.newHashMap();
		HosBusiness bus = new HosBusiness();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(id);
		Integer cloth = user.getClothesSize();
		List<Map<String, Object>> ope = hosTheaterDao.selectOperationFromContainerBySize(cloth, number);
		if (ope == null || ope.size() <= 0) {
			ZAlert.Error("没有找到对应尺码的手术衣！");
		}
		String trayNumber = (String) ope.get(0).get("trayNumber");
		map.put("operation", ope.get(0).get("trayNumber"));// 获取第一个托盘编号

		Date date = DateUtils.getLocalDate();
		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.WARDRODE);
		bus.setSize(cloth);
		bus.setDeviceNumber(number);
		bus.setConNumber(trayNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + number + "】的手术服衣柜内的【" + trayNumber + "】号柜， 领取 " + cloth
				+ "码手术衣";
		bus.setDescription(des);
		hosBusinessDao.insertDeviceLog(bus);
		return map;
	}

	public int receiveOpeByWarNumberM1(Map<String, Object> params) throws BaseException {
		Integer id = Integer.parseInt((String) params.get("id"));// 用户id
		String number = (String) params.get("number");
		String trayNumber = (String) params.get("trayNumber");
		Integer opeSize = Integer.parseInt((String) params.get("opeSize"));
		Integer alloutCount = Integer.parseInt((String) params.get("alloutCount"));
		if (alloutCount <= 0) {
			ZAlert.Error("该托盘内已没有能领取的手术衣！");
		}
		HosBusiness bus = new HosBusiness();
		Date date = DateUtils.getLocalDate();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(id);
		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.STERILIZER);
		bus.setSize(opeSize);
		bus.setDeviceNumber(number);
		bus.setConNumber(trayNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + number + "】的消毒鞋柜内的【" + trayNumber + "】号柜， 领取 "
				+ opeSize + "码手术鞋";
		bus.setDescription(des);

		// hosWardrobeContainerDao.updateContainerByWarTarNumber(number, 1,
		// trayNumber);
		return hosBusinessDao.insertDeviceLog(bus);
	}

	@Override
	public Map<String, Object> randomReceiveShoeByUserIdTheNumber(Integer userId, String theNumber, Integer mark)
			throws BaseException {
		// System.err.println(" 开始时间： "+DateUtils.getLocalDateToString());
		ZAssert.bigThanZero(userId, "请选择数据", "没有获取到用户信息");
		HosBusiness bus = new HosBusiness();
		HosSterilizerContainer hosCon = new HosSterilizerContainer();
		HosRecoveryGoods goods = new HosRecoveryGoods();
		HosRecord hosRecord = new HosRecord();
		String yesOrNoShoe = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();
		Date date = DateUtils.getLocalDate();
		String types = BizConst.DEVICE_TYPE.STERILIZER;
		Map<String, Object> map = Maps.newHashMap();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(userId);
		if (user == null) {
			ZAlert.Error("系统中查询不到用户信息，请联系管理员！！！");
		}

		Integer shoes = user.getShoesSize();
		if (shoes == 0 || shoes == null) {
			ZAlert.Error("您还没有维护鞋码，请联系管理员！！！");
		}
		List<Map<String, Object>> sho = hosTheaterDao.selectShoesFromConBySizeAndTheNumber(shoes, theNumber);
		// if(sho == null || sho.size()<=0){}
		// List<Integer> shoSize = BizConst.SIZE.SHOESSIZE;

		List<Map<String, Object>> dictShoes = dictionaryDao.selectDictByCode(BizConst.DICT.SHOES);
		List<Integer> shoSize = Lists.newArrayList();
		List<String> shoName = Lists.newArrayList();
		for (Map<String, Object> as : dictShoes) {
			shoSize.add(Integer.parseInt((String) as.get("code")));
			shoName.add((String) as.get("name"));
		}

		int i = shoSize.indexOf(shoes);
		int size = shoSize.size();
		while (sho == null || sho.size() <= 0) {
			i++;
			if (i >= size) {
				sho = hosTheaterDao.selectShoesFromConBySizeAndTheNumber(null, theNumber);
				break;
			}
			shoes = shoSize.get(i);
			sho = hosTheaterDao.selectShoesFromConBySizeAndTheNumber(shoes, theNumber);
		}

		if (sho == null || sho.size() <= 0) {
			ZAlert.Error("当前手术室消毒柜内没有可领取的鞋子！");
		}

		int result = (int) (Math.random() * (sho.size()));// 随机

		String lockerNumber = (String) sho.get(result).get("lockerNumber");
		String steNumber = (String) sho.get(result).get("steNumber");
		Integer shoesSize = Integer.parseInt(String.valueOf(sho.get(result).get("shoesSize")));
		Integer lockId = Integer.parseInt(String.valueOf(sho.get(result).get("id")));// 小柜id
		map.put("lockerNumber", lockerNumber);// 获取第一个小柜编号
		map.put("steNumber", steNumber);// 获取大柜编号
		int j = shoSize.indexOf(shoesSize);
		String name = shoName.get(j);
		map.put("shoesSize", name);

		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(types);// BizConst.DEVICE_TYPE.STERILIZER
		bus.setSize(shoesSize);
		bus.setDeviceNumber(theNumber);// 手术室编号 用于控制刷卡时间
		bus.setConNumber(lockerNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + steNumber + "】的消毒鞋柜内的【" + lockerNumber + "】号柜， 领取 "
				+ shoes + "码手术鞋";
		bus.setDescription(des);

		hosCon.setId(lockId);
		hosCon.setUserId(userId);
		hosCon.setState(BizConst.STATE.USER_USE);
		hosCon.setShoesSize(null);

		hosBusinessDao.insertDeviceLog(bus);
		hosSterilizerContainerDao.updateContainer(hosCon);// 更新小柜
		// System.err.println(" 结束时间： "+DateUtils.getLocalDateToString());

		if (yesOrNoShoe.equals(BizConst.YESORNO.YES) && mark == 0) {
			hosRecord.setUserId(Integer.parseInt(user.getUserId()));
			hosRecord.setSign(BizConst.SIGN.IN);
			hosRecord.setSignInTime(new Date());
			hosRecord.setDeviceType(types);
			hosRecord.setDeviceNumber(steNumber);// 12-14
			hosRecord.setTheaterNumber(theNumber);// 手术室编号，用于登录是判断是否签到签退
			hosRecordDao.insertHosRecordService(hosRecord);// 签到
		}
		HosRecord record = hosRecordDao.selectHosRecordLastByUserId(userId, theNumber, types);
		Integer recordId = record.getId();
		String shoesType = BizConst.GOODS_TYPE.SHOES;
		goods.setSize(shoesSize);
		goods.setTheNumber(theNumber);
		goods.setType(shoesType);// BizConst.DEVICE_TYPE.STERILIZER
		goods.setUserId(userId);
		goods.setRecordId(recordId);
		hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
		return map;
	}

	@Override
	public Map<String, Object> randomReceiveOpeByUserIdTheNumber(Integer userId, String theNumber)
			throws BaseException {
		ZAssert.bigThanZero(userId, "请选择数据", "没有获取到用户信息");
		Map<String, Object> map = Maps.newHashMap();
		HosBusiness bus = new HosBusiness();
		HosRecoveryGoods goods = new HosRecoveryGoods();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(userId);

		if (user == null) {
			ZAlert.Error("系统中查询不到用户信息，请联系管理员！！！");
		}

		Integer cloth = user.getClothesSize();
		if (cloth == 0 || cloth == null) {
			ZAlert.Error("您还没有维护衣码，请联系管理员！！！");
		}
		List<Map<String, Object>> ope = hosTheaterDao.selectOpeFromConBySizeAndTheNumber(cloth, theNumber);

		// List<Integer> clothSize = BizConst.SIZE.OPERATIONSIZE;
		List<Map<String, Object>> dictShoes = dictionaryDao.selectDictByCode(BizConst.DICT.CLOTH);
		List<Integer> clothSize = Lists.newArrayList();
		List<String> clothName = Lists.newArrayList();
		for (Map<String, Object> as : dictShoes) {
			clothSize.add(Integer.parseInt((String) as.get("code")));
			clothName.add((String) as.get("name"));
		}

		int i = clothSize.indexOf(cloth);
		int size = clothSize.size();
		while (ope == null || ope.size() <= 0) {
			i++;
			if (i >= size) {
				ope = hosTheaterDao.selectOpeFromConBySizeAndTheNumber(null, theNumber);
				break;
			}
			cloth = clothSize.get(i);
			ope = hosTheaterDao.selectOpeFromConBySizeAndTheNumber(cloth, theNumber);
		}

		if (ope == null || ope.size() <= 0) {
			ZAlert.Error("当前手术室衣柜内没有可领取的手术衣！");
		}
		String trayNumber = (String) ope.get(0).get("trayNumber");
		String warNumber = (String) ope.get(0).get("warNumber");
		Integer opeSize = Integer.parseInt(String.valueOf(ope.get(0).get("opeSize")));
		Integer id = Integer.parseInt(String.valueOf(ope.get(0).get("id")));
		map.put("trayNumber", trayNumber);// 获取第一个托盘编号
		map.put("warNumber", warNumber);// 获取第一个托盘编号
		int j = clothSize.indexOf(opeSize);
		String name = clothName.get(j);
		map.put("opeSize", name);

		Date date = DateUtils.getLocalDate();
		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.WARDRODE);
		bus.setSize(cloth);
		bus.setDeviceNumber(trayNumber);
		bus.setConNumber(trayNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + warNumber + "】的手术服衣柜内的【" + trayNumber + "】号柜， 领取 "
				+ cloth + "码手术衣";
		bus.setDescription(des);

		goods.setSize(cloth);
		goods.setTheNumber(theNumber);
		goods.setType(BizConst.DEVICE_TYPE.WARDRODE);
		// goods.setRecoveryTime(DateUtils.getLocalDate());
		goods.setUserId(userId);

		hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
		hosBusinessDao.insertDeviceLog(bus);
		hosWardrobeContainerDao.updateContainerByWarTarNumber(id);
		return map;
	}

	public Map<String, Object> randomReceiveShoeByUserIdTheNumberM1(Integer userId, String theNumber, Integer mark)
			throws BaseException {
		ZAssert.bigThanZero(userId, "请选择数据", "没有获取到用户信息");
		HosBusiness bus = new HosBusiness();
		HosSterilizerContainer hosCon = new HosSterilizerContainer();
		HosRecoveryGoods goods = new HosRecoveryGoods();
		HosRecord hosRecord = new HosRecord();
		String yesOrNoShoe = sysConfigDao.querySysConfigByConfigName(BizConst.SYSCONFIG.YESORNOHAVESHOWBOX).getConfig();
		Date date = DateUtils.getLocalDate();
		Map<String, Object> map = Maps.newHashMap();
		String types = BizConst.DEVICE_TYPE.STERILIZER;
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(userId);
		if (user == null) {
			ZAlert.Error("系统中查询不到用户信息，请联系管理员！！！");
		}

		Integer shoes = user.getShoesSize();
		if (shoes == 0 || shoes == null) {
			ZAlert.Error("您还没有维护鞋码，请联系管理员！！！");
		}
		List<Map<String, Object>> sho = hosTheaterDao.selectShoesFromConBySizeAndTheNumber(shoes, theNumber);
		if (sho == null || sho.size() <= 0) {
			ZAlert.Error("当前手术室消毒柜内没有符合尺码的的手术鞋！");
		}

		List<Map<String, Object>> dictShoes = dictionaryDao.selectDictByCode(BizConst.DICT.SHOES);
		List<Integer> shoSize = Lists.newArrayList();
		List<String> shoName = Lists.newArrayList();
		for (Map<String, Object> as : dictShoes) {
			shoSize.add(Integer.parseInt((String) as.get("code")));
			shoName.add((String) as.get("name"));
		}
		int result = (int) (Math.random() * (sho.size()));// 随机

		String lockerNumber = (String) sho.get(result).get("lockerNumber");
		String steNumber = (String) sho.get(result).get("steNumber");
		Integer shoesSize = Integer.parseInt(String.valueOf(sho.get(result).get("shoesSize")));
		Integer lockId = Integer.parseInt(String.valueOf(sho.get(result).get("id")));// 小柜id
		map.put("lockerNumber", lockerNumber);// 获取第一个小柜编号
		map.put("steNumber", steNumber);// 获取大柜编号
		int j = shoSize.indexOf(shoesSize);
		String name = shoName.get(j);
		map.put("shoesSize", name);

		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.STERILIZER);
		bus.setSize(shoes);
		bus.setDeviceNumber(theNumber);
		bus.setConNumber(lockerNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + steNumber + "】的消毒鞋柜内的【" + lockerNumber + "】号柜， 领取 "
				+ shoes + "码手术鞋";
		bus.setDescription(des);

		hosCon.setId(lockId);
		hosCon.setUserId(userId);
		hosCon.setState(BizConst.STATE.USER_USE);

		hosBusinessDao.insertDeviceLog(bus);
		hosSterilizerContainerDao.updateContainer(hosCon);// 更新小柜

		if (yesOrNoShoe.equals(BizConst.YESORNO.YES) && mark == 0) {
			hosRecord.setUserId(user.getId());
			hosRecord.setSign(BizConst.SIGN.IN);
			hosRecord.setSignInTime(new Date());
			hosRecord.setDeviceType(types);
			hosRecord.setDeviceNumber(steNumber);
			hosRecord.setTheaterNumber(theNumber);
			hosRecordDao.insertHosRecordService(hosRecord);// 签到
		}
		HosRecord record = hosRecordDao.selectHosRecordLastByUserId(userId, theNumber, types);
		Integer recordId = record.getId();

		goods.setSize(shoes);
		goods.setTheNumber(theNumber);
		goods.setType(BizConst.DEVICE_TYPE.STERILIZER);
		goods.setUserId(userId);
		goods.setRecordId(recordId);
		hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
		return map;
	}

	public Map<String, Object> randomReceiveOpeByUserIdTheNumberM1(Integer userId, String theNumber)
			throws BaseException {
		ZAssert.bigThanZero(userId, "请选择数据", "没有获取到用户信息");
		Map<String, Object> map = Maps.newHashMap();
		HosBusiness bus = new HosBusiness();
		HosRecoveryGoods goods = new HosRecoveryGoods();
		AuthUserDetail user = hosTheaterDao.loadUserDetailById(userId);

		if (user == null) {
			ZAlert.Error("系统中查询不到用户信息，请联系管理员！！！");
		}

		Integer cloth = user.getClothesSize();
		if (cloth == 0 || cloth == null) {
			ZAlert.Error("您还没有维护衣码，请联系管理员！！！");
		}
		List<Map<String, Object>> ope = hosTheaterDao.selectOpeFromConBySizeAndTheNumber(cloth, theNumber);
		if (ope == null || ope.size() <= 0) {
			ZAlert.Error("当前手术室衣柜内没有符合尺码的手术衣！");
		}

		List<Map<String, Object>> dictShoes = dictionaryDao.selectDictByCode(BizConst.DICT.CLOTH);
		List<Integer> clothSize = Lists.newArrayList();
		List<String> clothName = Lists.newArrayList();
		for (Map<String, Object> as : dictShoes) {
			clothSize.add(Integer.parseInt((String) as.get("code")));
			clothName.add((String) as.get("name"));
		}

		String trayNumber = (String) ope.get(0).get("trayNumber");
		String warNumber = (String) ope.get(0).get("warNumber");
		Integer opeSize = Integer.parseInt(String.valueOf(ope.get(0).get("opeSize")));
		Integer id = Integer.parseInt(String.valueOf(ope.get(0).get("id")));
		map.put("trayNumber", trayNumber);// 获取第一个托盘编号
		map.put("warNumber", warNumber);// 获取第一个托盘编号
		int j = clothSize.indexOf(opeSize);
		String name = clothName.get(j);
		map.put("opeSize", name);

		Date date = DateUtils.getLocalDate();
		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.WARDRODE);
		bus.setSize(cloth);
		bus.setDeviceNumber(trayNumber);
		bus.setConNumber(trayNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + warNumber + "】的手术服衣柜内的【" + trayNumber + "】号柜， 领取 "
				+ cloth + "码手术衣";
		bus.setDescription(des);

		goods.setSize(cloth);
		goods.setTheNumber(theNumber);
		goods.setType(BizConst.DEVICE_TYPE.WARDRODE);
		// goods.setRecoveryTime(DateUtils.getLocalDate());
		goods.setUserId(userId);

		hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
		hosBusinessDao.insertDeviceLog(bus);
		hosWardrobeContainerDao.updateContainerByWarTarNumber(id);
		return map;
	}

	public int emptyTouchAllSteContainer(String number) throws BaseException {
		int flag = 0;
		ZAssert.hasText(number, "编号不能为空，请查证！");
		List<Map<String, Object>> cont = hosSterilizerContainerDao.selectContainerByThNumber(number);
		if (cont == null || cont.size() <= 0) {
			ZAlert.Error("没有获取到要清空的小柜信息！");
		}
		for (Map<String, Object> map : cont) {
			Object co = map.get("id");
			Integer id = Integer.parseInt(co.toString());
			flag = hosSterilizerContainerDao.updateContainerById(id, BizConst.STATE.NOTUSE, "0");
		}
		return flag;
	}

	public int emptyTouchAllWarContainer(String number) throws BaseException {
		int flag = 0;
		ZAssert.hasText(number, "编号不能为空，请查证！");
		List<Map<String, Object>> cont = hosWardrobeContainerDao.selectContainerByThNumber(number);
		if (cont == null || cont.size() <= 0) {
			ZAlert.Error("没有获取到要清空的手术衣柜信息");
		}
		for (Map<String, Object> map : cont) {
			Object co = map.get("id");
			Integer id = Integer.parseInt(co.toString());
			HosWardrobeContainer hosWardrobeContainer = hosWardrobeContainerDao.selectContainerById(id);
			int clothSize = hosWardrobeContainer.getOpeSize();
			if (clothSize > 0) {
				HosOperation hosOperation = hosOperationDao.selectHosOperationByClothSize("" + clothSize);
				hosOperationDao.updateHosOperation(hosOperation);
				hosWardrobeContainerDao.emptyContainerById(id);
			}
		}
		return flag;
	}

	public Map<String, Object> randomReceiveOpeByUserIdWarNumber(Integer userId, String warNumber, Integer mark)
			throws BaseException {
		Map<String, Object> map = Maps.newHashMap();
		HosBusiness bus = new HosBusiness();
		AuthUserDetail user = new AuthUserDetail();
		HosRecoveryGoods goods = new HosRecoveryGoods();
		HosRecord hosRecord = new HosRecord();
		user = hosTheaterDao.loadUserDetailById(userId);
		if (user == null) {
			map.put("ErrorMessage", "系统中查询不到用户信息，请联系管理员！！！");
			return map;
		}

		Integer cloth = user.getClothesSize();
		if (cloth == 0 || cloth == null) {
			map.put("ErrorMessage", "您还没有维护衣码，请联系管理员！！！");
			return map;
		}
		// 转换尺码
		List<Map<String, Object>> dictShoes = dictionaryDao.selectDictByCode(BizConst.DICT.CLOTH);
		List<Integer> clothSize = Lists.newArrayList();
		List<String> clothName = Lists.newArrayList();
		for (Map<String, Object> as : dictShoes) {
			clothSize.add(Integer.parseInt((String) as.get("code")));
			clothName.add((String) as.get("name"));
		}

		// 在该发衣柜上查询该尺码衣服
		List<Map<String, Object>> ope = hosTheaterDao.selectOperationFromContainerBySize(cloth, warNumber);
		// 如果该设备上没有此型号衣服
		if (ope == null || ope.size() <= 0) {
			map.put("ErrorMessage", "当前设备内没有适合您尺码的手术衣，请换台设备！");
			return map;
		}
		// 获取托盘号
		String trayNumber = (String) ope.get(0).get("trayNumber");
		// 获取该设备所在手术区编号
		String theaterNumber = (String) ope.get(0).get("theaterNumber");// 手术室编号
		Integer opeSize = Integer.parseInt(String.valueOf(ope.get(0).get("opeSize")));
		Integer id = Integer.parseInt(String.valueOf(ope.get(0).get("id")));
		// 返回托盘编号
		map.put("trayNumber", trayNumber);
		// 返回衣柜编号
		map.put("warNumber", warNumber);
		int j = clothSize.indexOf(opeSize);
		String name = clothName.get(j);
		// 返回衣服尺码
		map.put("opeSize", name);

		Date date = DateUtils.getLocalDate();
		bus.setUserId(Integer.parseInt(user.getUserId()));
		bus.setOperateTime(date);
		bus.setDeviceType(BizConst.DEVICE_TYPE.WARDRODE);
		bus.setSize(opeSize);
		bus.setDeviceNumber(warNumber);
		bus.setConNumber(trayNumber);
		bus.setOperationType(BizConst.OPERATION_TYPE.RECEIVE);
		String time = DateUtils.FormatDateToYear_Month_Day(date);
		String des = user.getUserName() + "在" + time + "在编号为【" + warNumber + "】的手术服衣柜内的【" + trayNumber + "】号柜， 领取 "
				+ name + "手术衣";
		bus.setDescription(des);

		hosBusinessDao.insertDeviceLog(bus);
		hosWardrobeContainerDao.updateContainerByWarTarNumber(id);
		// AuthRole authRole = hosRecordDao.selectAuthRoleLqcsById(userId);
		hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");
		if (hosRecord == null) {
			HosRecord hos = new HosRecord();
			hos.setUserId(userId);
			hos.setSign(BizConst.SIGN.IN);
			hos.setSignInTime(new Date());
			hos.setDeviceType(BizConst.DEVICE_TYPE.WARDRODE);
			hos.setDeviceNumber(warNumber);
			hos.setTheaterNumber(theaterNumber);
			// hos.setLqcs((Integer.parseInt(authRole.getLqcs()) - 1) + "");
			hosRecordDao.insertHosRecordService(hos);// 签到
			hosRecord = hosRecordDao.selectHosRecordLastByUserId(userId, theaterNumber, "");
		}

		String clothesType = BizConst.GOODS_TYPE.CLOTHES;
		String boxType = BizConst.GOODS_TYPE.BOX;
		String yesOrNoRecyclingbin = sysConfigDao.querySysConfigByConfigName(SYSCONFIG.RECYCLINGBIN).getConfig();
		Integer recordId = hosRecord.getId();
		goods.setTheNumber(theaterNumber);
		goods.setUserId(userId);
		goods.setRecordId(recordId);
		if (RecyclingBin.YES.equals(yesOrNoRecyclingbin)) {
			// 插入手术衣回收记录
			goods.setSize(opeSize);
			goods.setType(clothesType);
			goods.setState(ISCALLBACK.NO);
			hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
			// 插入收纳盒回收记录
			goods.setSize(null);
			goods.setType(boxType);
			hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
		} else if (RecyclingBin.NO.equals(yesOrNoRecyclingbin)) {
			// 插入手术衣回收记录
			goods.setSize(opeSize);
			goods.setType(clothesType);
			goods.setState(ISCALLBACK.YES);
			hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
			// 插入收纳盒回收记录
			goods.setSize(null);
			goods.setType(boxType);
			hosRecoveryGoodsDao.insertHosRecoveryGoods(goods);
		}

		return map;
	}

	@Override
	public List<ClassConvertDict> loadTheNumberConvertDict() throws BaseException {
		return hosTheaterDao.loadTheNumberConvertDict();
	}

	public DataSet loadTheaterUserByTheaterId(Map<String, Object> params) throws BaseException {
		DataSet ds = null;
		if (params == null) {
			return DataSet.emptyDS();
		}
		ParamMap pm = ParamMap.filterParam(params);
		String isInTheater = pm.getStrParam("isInTheater");
		String loginName = pm.getStrParam("loginName");
		if (loginName != null) {
			pm.updateParam("loginName", "%" + loginName + "%");
		}
		if ("true".equals(isInTheater)) {
			ds = DataSet.newDS(hosTheaterDao.selectTheaterUserByTheaterIdCount(pm),
					hosTheaterDao.selectTheaterUserByTheaterIdList(pm));
		} else {
			ds = DataSet.newDS(hosTheaterDao.selectNotTheaterUserByTheaterIdCount(pm),
					hosTheaterDao.selectNotTheaterUserByTheaterIdList(pm));
		}
		return ds;
	}

	public int assignUserToTheater(Integer theaterId, List<Integer> userIds) throws BaseException {
		ZAssert.bigThanZero(theaterId, "请选择数据", "参数类型错误");
		int flag = 1;
		if (userIds == null || userIds.isEmpty()) {
			ZAlert.Error("请选择数据");
		}
		for (Integer userId : userIds) {
			flag = hosTheaterDao.removeTheaterFromUser(theaterId, userId);
		}

		for (Integer userId : userIds) {
			flag = hosTheaterDao.assignUserToTheater(theaterId, userId);
		}
		return flag;
	}

	public int removeTheaterFromUser(Integer theaterId, List<Integer> userIds) throws BaseException {
		ZAssert.bigThanZero(theaterId, "请选择数据", "参数类型错误");
		int flag = 1;
		if (userIds == null || userIds.isEmpty()) {
			ZAlert.Error("请选择数据");
		}
		for (Integer userId : userIds) {
			flag = hosTheaterDao.removeTheaterFromUser(theaterId, userId);
		}
		return flag;
	}

	public boolean selectTheaterUserByNumberAndCardNum(String theNumber, String cardNum) throws BaseException {
		// ZAssert.hasText(theNumber, "没有获取到更衣室信息！");
		// ZAssert.hasText(cardNum, "没有获取到用户信息！");
		AuthUser user = authUserDao.findByLoginName2(cardNum);
		if (user == null) {
			ZAlert.Error("没有获取到用户信息，请查证");
		}
		HosTheater the = hosTheaterDao.selectTheaterByNumber(theNumber);
		if (the == null) {
			ZAlert.Error("没有获取到更衣室信息，请查证");
		}
		Long count = hosTheaterDao.selectTheaterUserByAllId(the.getId(), user.getId());
		if (count > 0) {
			return true;
		}
		return false;
	}

	public List<HosTheater> selectTheaterByUserId(Integer UserId) throws BaseException {
		List<Map<String, Object>> lists = authPermissionDao.selectPermCodeListByUserId(UserId);
		String sfcjgly = authUserDao.selectByPrimaryKey(UserId).getSfcjgly();
		boolean yesOrNoIsAdmin = false;
		if (BizConst.SFCJGLY.TURE.equals(sfcjgly)) {
			yesOrNoIsAdmin = true;
		} else {
			for (Map<String, Object> map : lists) {
				if (BizConst.PERMISSION_CODE.BACKADMIN.equals(map.get("code"))) {
					yesOrNoIsAdmin = true;
				}
			}
		}
		if (yesOrNoIsAdmin) {
			return hosTheaterDao.selectTheaterAll();

		} else {
			return hosTheaterDao.selectTheaterByUserId(UserId);
		}
	}
}
