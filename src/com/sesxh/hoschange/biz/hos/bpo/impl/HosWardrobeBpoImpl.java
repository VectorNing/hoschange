package com.sesxh.hoschange.biz.hos.bpo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.bpo.HosWardrobeBpo;
import com.sesxh.hoschange.biz.hos.dao.HosRoomDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeContainerDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;
import com.sesxh.hoschange.biz.sys.dao.AuthPermissionDao;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZAlert;
import com.sesxh.hoschange.common.util.ZAssert;
import com.sesxh.hoschange.global.BizConst;

@Component("com.sesxh.hoschange.biz.hos.bpo.impl.HosWardrobeBpoImpl")
public class HosWardrobeBpoImpl extends SesframeBaseBpo implements HosWardrobeBpo {

	@Autowired
	private HosWardrobeDao hosWardrobeDao;
	@Autowired
	private HosWardrobeContainerDao hosWardrobeContainerDao;
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;
	@Autowired
	private HosRoomDao hosRoomDao;

	@Override
	public int insertHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		validate(hosWardrobe);
		String theaterNumber = hosWardrobe.getTheaterNumber();
		Integer roomNumber = hosWardrobe.getRoomId();
		HosRoom hosRoom = hosRoomDao.selectHosRoomByTheaterAndRoomId(theaterNumber, roomNumber);
		// 分配到具体更衣室
		hosWardrobe.setRoomId(hosRoom.getId());
		// hosWardrobe.setState(BizConst.STATE.NOTUSE);//
		Integer sum = hosWardrobe.getTraySum();
		HosWardrobeContainer hos = new HosWardrobeContainer();
		hos.setTrayTotal(hosWardrobe.getTotal());
		hos.setWarNumber(hosWardrobe.getNumber());
		int num = 0;
		for (int i = 0; i < sum; i++) {
			hos.setTrayNumber("" + num);
			hosWardrobeContainerDao.insertHosWardrobeContainer(hos);
			num++;
		}
		if (theaterNumber != null && !theaterNumber.isEmpty()) {
			hosWardrobe.setState(BizConst.STATE.USE);
		}
		return hosWardrobeDao.insertHosWardrobe(hosWardrobe);
	}

	@Override
	public int deleteHosWardrobeByIdAndNumber(Integer id, String number) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		ZAssert.hasText(number, "衣柜编号有误或不存在，请查证！");

		/*
		 * // 修改手术衣 状态 hosOperationDao.updateStateByWarNumber(number,
		 * BizConst.STATE.NOTUSE);
		 * 
		 * // 删除 手术衣对应使用 hosWardrobeDao.deleteHosOpeWardrobeByWarNumber(number);
		 */
		// 删除托盘
		hosWardrobeContainerDao.deleteContainerByWarNumber(number);

		return hosWardrobeDao.deleteHosWardrobeById(id);
	}

	@Override
	public int updateHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		validate(hosWardrobe);
		String theaterNumber = hosWardrobe.getOldTheaterNumber();
		Integer roomNumber = hosWardrobe.getRoomId();

		HosRoom hosRoom = hosRoomDao.selectHosRoomByTheaterAndRoomId(theaterNumber, roomNumber);
		// 分配到具体更衣室
		hosWardrobe.setRoomId(hosRoom.getId());

		String oldNum = hosWardrobe.getOldNumber();
		String newNum = hosWardrobe.getNumber();

		if (oldNum.equals(newNum)) {

		} else {
			hosWardrobeContainerDao.updateContainerByWarNumber(oldNum, newNum);
			/* hosWardrobeDao.updateOpeWarNumberByWarNumber(oldNum, newNum); */
		}

		return hosWardrobeDao.updateHosWardrobe(hosWardrobe);
	}

	public Long selectWarByNumber(String number, Integer id) throws BaseException {
		// ZAssert.hasText(number, "手术衣柜编号不能为空！");
		return hosWardrobeDao.selectWarByNumber(number, id);
	}

	@Override
	public DataSet queryHosWardrobeSet(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		DataSet ds = DataSet.newDS(hosWardrobeDao.HosWardrobeCount(pm), hosWardrobeDao.selectAllHosWardrobe(pm));
		return ds;
	}

	public HosWardrobe selectHosWardrobeById(Integer id) throws BaseException {
		ZAssert.bigThanZero(id, "请选择数据", "参数类型错误");
		return hosWardrobeDao.selectHosWardrobeById(id);
	}

	// 校验
	private void validate(HosWardrobe hosWardrobe) throws BaseException {
		String model = hosWardrobe.getModel();
		String number = hosWardrobe.getNumber();
		// String descpription = hosWardrobe.getDescription();

		ZAssert.hasText(model, "衣柜型号不能为空");
		ZAssert.hasText(number, "衣柜编号不能为空");

		ZAssert.lenLessTan(model, 100, "衣柜型号编码不能超过100字符");
		ZAssert.lenLessTan(number, 100, "衣柜编号不能超过100字符");
		// ZAssert.lenLessTan(descpription, 255, "描述不能超过255字符");
	}

	public Long selectOperationFromWardBynumber(String number) throws BaseException {
		long count = 0;
		ZAssert.hasText(number, "衣柜编号不能为空");
		List<Map<String, Object>> list = hosWardrobeDao.selectOperationFromWardBynumber(number);
		if (list != null && list.size() > 0) {
			BigDecimal b = (BigDecimal) list.get(0).get("cnt");
			count = b.longValue();
		}
		return count;
	}

	public DataSet loadWardrobeListByTheNumber(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet ds = null;
		ParamMap pm = ParamMap.filterParam(params);
		String isInWar = pm.getStrParam("isInWar");
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		if ("true".equals(isInWar)) {
			ds = DataSet.newDS(hosWardrobeDao.selectWardCountByTheNumber(pm),
					hosWardrobeDao.selectWardListByTheNumber(pm));
		} else {
			ds = DataSet.newDS(hosWardrobeDao.selectNotWardCountByTheNumber(pm),
					hosWardrobeDao.selectNotWardListByTheNumber(pm));
		}
		return ds;
	}

	public int assignTheaterToWar(String number, Integer[] ids) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空");
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		HosWardrobe hosWardrobe = new HosWardrobe();
		List<Integer> id = Arrays.asList(ids);
		int flag = 0;
		hosWardrobe.setTheaterNumber(number);
		hosWardrobe.setState(BizConst.STATE.USE);
		for (Integer a : id) {
			hosWardrobe.setId(a);
			flag = hosWardrobeDao.assignTheaterToWar(hosWardrobe);
		}
		return flag;
	}

	public int removeWarFromTheater(String number, Integer[] ids) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空");
		HosWardrobe hosWardrobe = new HosWardrobe();
		int flag = 0;
		if (ids == null || ids.length <= 0) {
			ZAlert.Error("请选择数据");
		}
		List<Integer> id = Arrays.asList(ids);
		hosWardrobe.setState(BizConst.STATE.NOTUSE);
		for (Integer a : id) {
			hosWardrobe.setId(a);
			flag = hosWardrobeDao.removeWarFromTheater(hosWardrobe);
		}
		return flag;
	}

	// @SuppressWarnings("unused")
	public int insertContainer(Map<String, Object> params) throws BaseException {
		String number = (String) params.get("number");
		Integer total = Integer.parseInt((String) params.get("total"));
		Integer traySum = Integer.parseInt((String) params.get("traySum"));
		int flag = 0;
		boolean aa = true;
		HosWardrobeContainer war = new HosWardrobeContainer();
		ZAssert.hasText(number, "请选择要添加托盘的衣柜编号");
		war.setWarNumber(number);
		war.setTrayTotal(total);
		List<HosWardrobeContainer> hos = hosWardrobeContainerDao.selectHosWardrobeContainerByWarNumber(number);
		for (int i = 0; i < traySum; i++) {
			String keyNum = "trayNumber" + (i + 1);
			String keyId = "id" + (i + 1);
			Integer id = Integer.parseInt((String) params.get(keyId));
			String trayNumber = (String) params.get(keyNum);
			war.setTrayNumber(trayNumber);

			for (int j = 0; j < hos.size(); j++) {
				Integer hosId = hos.get(j).getId();
				if (hosId.equals(id)) {
					war.setId(id);
					aa = false;
				} else {
				}
			}
			if (aa) {
				flag = hosWardrobeContainerDao.insertHosWardrobeContainer(war);
			} else {
				flag = hosWardrobeContainerDao.updateContainerById(war);
			}
		}
		return flag;
	}

	public List<HosWardrobe> loadWardrobeByThNumber(String number) throws BaseException {
		ZAssert.hasText(number, "手术室编号不能为空");
		List<HosWardrobe> lists = hosWardrobeDao.loadWardrobeByThNumber(number);
		for (HosWardrobe hos : lists) {
			List<HosWardrobeContainer> hosWar = hosWardrobeContainerDao
					.selectHosWardrobeContainerByWarNumber(hos.getNumber());
			hos.setContainerList(hosWar);
		}
		return lists;
	}

	public List<HosWardrobe> loadWardrobeByWarNumber(String warNumber) throws BaseException {
		ZAssert.hasText(warNumber, "手术衣柜编号不能为空");
		List<HosWardrobe> lists = hosWardrobeDao.loadWardrobeByWarNumber(warNumber);
		for (HosWardrobe hos : lists) {
			List<HosWardrobeContainer> hosWar = hosWardrobeContainerDao
					.selectHosWardrobeContainerByWarNumber(hos.getNumber());
			hos.setContainerList(hosWar);
		}
		return lists;
	}

	public HosWardrobe selectHosWardrobeEnabledByNumber(String warNumber) throws BaseException {
		ZAssert.hasText(warNumber, "手术衣柜编号不能为空");
		HosWardrobe hosWardrobe = hosWardrobeDao.selectHosWardrobeEnabledByNumber(warNumber);
		return hosWardrobe;
	}

	public int updateHosWardrobeEnabledByNumber(HosWardrobe hosWardrobe) throws BaseException {
		ZAssert.hasText(hosWardrobe.getNumber(), "手术衣柜编号不能为空");
		return hosWardrobeDao.updateHosWardrobeEnabledByNumber(hosWardrobe);
	}

	public DataSet selectHosWardrobeMonitored(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String number = pm.getStrParam("number");
		if (number != null) {
			pm.updateParam("number", "%" + number + "%");
		}
		Long count;
		List<Map<String, Object>> maps = new ArrayList<>();
		Integer userId = pm.getIntegerParam("userId");
		AuthUser authUser = authUserDao.selectAuthUserByUserId(userId);
		List<AuthPermission> authPermissions = authPermissionDao.selectPermListByUserId(userId);
		if (BizConst.SFCJGLY.TURE.equals(authUser.getSfcjgly())
				|| isOrNoAdmin(BizConst.PERMISSION_CODE.BACKADMIN, authPermissions)) {
			count = hosWardrobeDao.HosWardrobeAllCountMonitored(pm);
			maps = hosWardrobeDao.selectAllHosWardrobeMonitored(pm);
		} else {
			count = hosWardrobeDao.HosWardrobeCountMonitoredByUserId(pm);
			maps = hosWardrobeDao.selectHosWardrobeMonitoredByUserId(pm);
		}

		DataSet ds = DataSet.newDS(count, maps);
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
	public HosWardrobe selectHosWardrobeByNumber(String number) throws BaseException {
		return hosWardrobeDao.selectHosWardrobeByNumber(number);
	}
}
