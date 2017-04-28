package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosClothesPressDao;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPress;
import com.sesxh.hoschange.biz.hos.entity.HosClothesPressContainer;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosClothesPressDaoImp extends SesframeBaseDao implements HosClothesPressDao {

	@Override
	public Long queryClothesPressCount(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT              ");
		sqlStr.append(" 	1               ");
		sqlStr.append(" FROM                ");
		sqlStr.append(" 	hos_clothespress");
		sqlStr.append(" WHERE               ");
		sqlStr.append(" 	1 = 1           ");
		if (pm.getStrParam("number") != null) {
			sqlStr.append(" AND number LIKE :number");
		}
		sql = SQL.getSQL(sqlStr);
		if (pm.getStrParam("number") != null) {
			sql.setVarChar("number", pm.getStrParam("number"));
		}
		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();

	}

	@Override
	public List<Map<String, Object>> queryClothesPressAll(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                        ");
		sqlStr.append(" 	hc.*, (                                   ");
		sqlStr.append(" 		SELECT                                ");
		sqlStr.append(" 			COUNT(1)                          ");
		sqlStr.append(" 		FROM                                  ");
		sqlStr.append(" 			hos_clothespress_container hcc    ");
		sqlStr.append(" 		WHERE                                 ");
		sqlStr.append(" 			1 = 1                             ");
		sqlStr.append(" 		AND hcc.clothespressId = hc.id        ");
		sqlStr.append(" 		AND hcc.state = :state                ");
		sqlStr.append(" 	) AS count,                               ");
		sqlStr.append(" 	(                                         ");
		sqlStr.append(" 		SELECT                                ");
		sqlStr.append(" 			CONCAT(ht.`name`, hr.description) ");
		sqlStr.append(" 		FROM                                  ");
		sqlStr.append(" 			hos_theater ht,                   ");
		sqlStr.append(" 			hos_room hr                       ");
		sqlStr.append(" 		WHERE                                 ");
		sqlStr.append(" 			1 = 1                             ");
		sqlStr.append(" 		AND ht.number = hc.theaterNumber      ");
		sqlStr.append(" 		AND hr.theaterNumber = ht.number      ");
		sqlStr.append(" 		AND hc.roomId = hr.id                 ");
		sqlStr.append(" 	) AS name                                 ");
		sqlStr.append(" FROM                                          ");
		sqlStr.append(" 	hos_clothespress hc                       ");
		sqlStr.append(" WHERE                                         ");
		sqlStr.append(" 	1 = 1                          	          ");
		if (pm.getStrParam("number") != null) {
			sqlStr.append(" AND hc.number LIKE :number");
		}
		String orderDir = pm.getOrder();
		String sort = pm.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		}
		sqlStr.append(" LIMIT :start ,:size     ");
		sql = SQL.getSQL(sqlStr);
		if (pm.getStrParam("number") != null) {
			sql.setVarChar("number", pm.getStrParam("number"));
		}
		sql.setVarChar("state", BizConst.STATE.USE);
		sql.setInteger("start", pm.getStart());
		sql.setInteger("size", pm.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int addClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" INSERT INTO hos_clothespress (                                      ");
		sqlStr.append(" 	id,                                                             ");
		sqlStr.append(" 	number,                                                         ");
		sqlStr.append(" 	total,                                                        ");
		sqlStr.append(" 	theaterNumber,                                                  ");
		sqlStr.append(" 	description,                                                    ");
		sqlStr.append(" 	roomId,                                                        ");
		sqlStr.append(" 	startDoor                                                       ");
		sqlStr.append(" )                                                                   ");
		sqlStr.append(" VALUES                                                              ");
		sqlStr.append(" 	(                                                               ");
		sqlStr.append(" :id ,:number ,:total,:theaterNumber ,:description,:roomId,:startDoor");
		sqlStr.append(" 	)                                                               ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosClothesPress);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int addClothesPressContainer(HosClothesPressContainer clothesPressContainer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" INSERT INTO hos_clothespress_container ( ");
		sqlStr.append(" 	lockerNumber,                        ");
		sqlStr.append(" 	clothesPressId,                      ");
		sqlStr.append(" 	doorNumber                          ");
		sqlStr.append(" )                                        ");
		sqlStr.append(" VALUES                                   ");
		sqlStr.append(" 	(                                    ");
		sqlStr.append(" :lockerNumber ,:clothesPressId,:doorNumber   ");
		sqlStr.append(" 	)                                    ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(clothesPressContainer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosClothesPress queryClothesPressById(String id) throws BaseException {
		SQL sql = SQL.getSQL("SELECT * FROM hos_clothespress WHERE id=:id");
		sql.setVarChar("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosClothesPress.class);
	}

	@Override
	public int updateHosClothesPress(HosClothesPress hosClothesPress) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_clothespress         ");
		sqlStr.append(" SET number = :number,           ");
		sqlStr.append("  state = :state,                ");
		sqlStr.append("  theaterNumber = :theaterNumber,");
		sqlStr.append("  description = :description,    ");
		sqlStr.append("  roomId = :roomId               ");
		sqlStr.append(" WHERE                           ");
		sqlStr.append(" 	id = :id                    ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosClothesPress);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosClothesPress> selectHosClothesPressByRoomNumber(Integer roomNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                ");
		sqlStr.append(" 	hc.*                              ");
		sqlStr.append(" FROM                                  ");
		sqlStr.append(" 	hos_clothespress hc               ");
		sqlStr.append(" WHERE                                 ");
		sqlStr.append(" 	1 = 1                             ");
		sqlStr.append(" AND hc.roomId = :roomNumber ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("roomNumber", roomNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosClothesPress.class);
	}

	@Override
	public List<HosClothesPressContainer> selectHosClothesPressContainerById(String clothesPressId)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                   ");
		sqlStr.append(" 	hcc.*                                ");
		sqlStr.append(" FROM                                     ");
		sqlStr.append(" 	hos_clothespress_container hcc       ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	1 = 1                                ");
		sqlStr.append(" AND hcc.clothesPressId = :clothesPressId ");
		sqlStr.append(" AND hcc.state = :state                   ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("clothesPressId", clothesPressId);
		sql.setVarChar("state", BizConst.STATE.NOTUSE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosClothesPressContainer.class);
	}

	@Override
	public int updateHosClothesPressContainerById(Integer id, Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_clothespress_container hcc ");
		sqlStr.append(" SET state = :state,                   ");
		sqlStr.append("     userId = :userId                  ");
		sqlStr.append(" WHERE                                 ");
		sqlStr.append(" 	1 = 1                             ");
		sqlStr.append(" AND hcc.id = :id                      ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("state", BizConst.STATE.USER_USE);
		sql.setInteger("id", id);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public Long selectClothesFromClothesPressById(String id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                            ");
		sqlStr.append(" 	*                             ");
		sqlStr.append(" FROM                              ");
		sqlStr.append(" 	hos_clothespress_container hcc");
		sqlStr.append(" WHERE                             ");
		sqlStr.append(" 	hcc.clothesPressId = :id      ");
		sqlStr.append(" AND                               ");
		sqlStr.append(" 	hcc.state = :state            ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("id", id);
		sql.setVarChar("state", BizConst.STATE.USE);
		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public int deleteClothesPressById(String id) throws BaseException {
		SQL sql = SQL.getSQL("DELETE FROM hos_clothespress_container WHERE clothesPressId = :id");
		sql.setVarChar("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteClothesPressByIdContainer(String id) throws BaseException {
		SQL sql = SQL.getSQL("DELETE FROM hos_clothespress WHERE id = :id");
		sql.setVarChar("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateStateAndUserIdByUserId(Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_clothespress_container hcc ");
		sqlStr.append(" SET userId = :newuserId,              ");
		sqlStr.append("  state = :state                       ");
		sqlStr.append(" WHERE                                 ");
		sqlStr.append(" 	1 = 1                             ");
		sqlStr.append(" AND userId = :userId                  ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("newuserId", null);
		sql.setVarChar("state", BizConst.STATE.NOTUSE);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();

	}

	@Override
	public List<Map<String, Object>> selectClothesPressByUserIdAndNumber(Integer userId, String ClothesPressNum)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                             ");
		sqlStr.append(" 	hc.number,                     ");
		sqlStr.append(" 	hcc.lockerNumber               ");
		sqlStr.append(" FROM                               ");
		sqlStr.append(" 	hos_clothespress hc,           ");
		sqlStr.append(" 	hos_clothespress_container hcc ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	1 = 1                          ");
		sqlStr.append(" AND hc.id = hcc.clothesPressId     ");
		sqlStr.append(" AND hc.number = :number            ");
		sqlStr.append(" AND hcc.userId = :userId           ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", ClothesPressNum);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int updateClothesPressContainerById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_clothespress_container");
		sqlStr.append(" SET userId = NULL,               ");
		sqlStr.append("  state = '0'                     ");
		sqlStr.append(" WHERE                            ");
		sqlStr.append(" 	id = :id                     ");
		sqlStr.append(" AND state = :state               ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("state", BizConst.STATE.USER_USE);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public Long selectClothesPressByNumber(String id, String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT               ");
		sqlStr.append(" 	1                ");
		sqlStr.append(" FROM                 ");
		sqlStr.append(" 	hos_clothespress ");
		sqlStr.append(" WHERE                ");
		sqlStr.append(" 	1 = 1            ");
		sqlStr.append(" AND number = :number ");
		if (id != null) {
			sqlStr.append(" AND	id != :id    ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		if (id != null) {
			sql.setVarChar("id", id);
		}
		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectClothesPressContainerByUserIdAndNumber(String number, Integer userId)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                                ");
		sqlStr.append(" 	hc.number,                                                        ");
		sqlStr.append(" 	hcc.lockerNumber,                                                 ");
		sqlStr.append(" 	hcc.id,                                                           ");
		sqlStr.append(" 	hcc.yesOrNoBinding                                                ");
		sqlStr.append(" FROM                                                                  ");
		sqlStr.append(" 	hos_clothespress hc                                               ");
		sqlStr.append(" LEFT JOIN hos_clothespress_container hcc ON hc.id = hcc.clothesPressId");
		sqlStr.append(" WHERE                                                                 ");
		sqlStr.append(" 	hc.theaterNumber = :number                                        ");
		sqlStr.append(" AND hcc.userId = :userId                                              ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public List<Map<String, Object>> selectClothesPressBySteNumber(String steNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                     ");
		sqlStr.append(" 	hcc.*, (                                               ");
		sqlStr.append(" 		SELECT                                             ");
		sqlStr.append(" 			aud.userName                                   ");
		sqlStr.append(" 		FROM                                               ");
		sqlStr.append(" 			auth_user_detail aud                           ");
		sqlStr.append(" 		WHERE                                              ");
		sqlStr.append(" 			aud.userId = hcc.userId                        ");
		sqlStr.append(" 	) AS userName                                          ");
		sqlStr.append(" FROM                                                       ");
		sqlStr.append(" 	hos_clothespress_container hcc                         ");
		sqlStr.append(" LEFT JOIN hos_clothespress hc ON hc.id = hcc.clothesPressId");
		sqlStr.append(" WHERE                                                      ");
		sqlStr.append(" 	1 = 1                                                  ");
		if (steNumber != null) {
			sqlStr.append(" AND hc.number = :steNumber                            ");
		}
		sql = SQL.getSQL(sqlStr);
		if (steNumber != null) {
			sql.setVarChar("steNumber", steNumber);
		}
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int updateLockClothesPress(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("UPDATE hos_clothespress_container hcc SET hcc.yesOrNoLock=:yesOrNoLock WHERE hcc.id=:id");
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.TRUE);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateStoplockClothesPress(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("UPDATE hos_clothespress_container hcc SET hcc.yesOrNoLock=:yesOrNoLock WHERE hcc.id=:id");
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateBindingClothesPressAndUser(Integer id, Integer userId, Integer clothSize) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_clothespress_container hcc    ");
		sqlStr.append(" SET hcc.yesOrNoBinding = :yesOrNoBinding,");
		sqlStr.append("  hcc.userId = :userId,                   ");
		sqlStr.append("  hcc.clothSize = :clothSize              ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	hcc.id = :id                         ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.TRUE);
		sql.setInteger("userId", userId);
		sql.setInteger("clothSize", clothSize);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateStopBindingClothesPressAndUser(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_clothespress_container hcc    ");
		sqlStr.append(" SET hcc.yesOrNoBinding = :yesOrNoBinding,");
		sqlStr.append("  hcc.userId = NULL,                      ");
		sqlStr.append("  hcc.clothSize = NULL                    ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	hcc.id = :id                         ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.FALSE);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosClothesPress selectClothesPressByNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                            ");
		sqlStr.append(" 	*                             ");
		sqlStr.append(" FROM                              ");
		sqlStr.append(" 	hos_clothespress   hc         ");
		sqlStr.append(" WHERE                             ");
		sqlStr.append(" 	hc.number = :number           ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosClothesPress.class);
	}

	@Override
	public HosClothesPressContainer selectBindingClothesPressByUserIdAndTheaterNumber(Integer userId,
			String theaterNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                     ");
		sqlStr.append(" 	hcc.*                                                  ");
		sqlStr.append(" FROM                                                       ");
		sqlStr.append(" 	hos_clothespress_container hcc                         ");
		sqlStr.append(" LEFT JOIN hos_clothespress hc ON hc.id = hcc.clothesPressId");
		sqlStr.append(" WHERE                                                      ");
		sqlStr.append(" 	hcc.userId = :userId                                   ");
		sqlStr.append(" AND hcc.yesOrNoBinding = :yesOrNoBinding                   ");
		sqlStr.append(" AND hc.theaterNumber = :theaterNumber                      ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.TRUE);
		sql.setInteger("userId", userId);
		sql.setVarChar("theaterNumber", theaterNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosClothesPressContainer.class);
	}

	@Override
	public HosClothesPressContainer selectBindingClothesPressContainerByUserIdAndRoomId(Integer userId, Integer roomId,
			String yesOrNoBinding) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                     ");
		sqlStr.append(" 	hcc.*                                                  ");
		sqlStr.append(" FROM                                                       ");
		sqlStr.append(" 	hos_clothespress_container hcc                         ");
		sqlStr.append(" LEFT JOIN hos_clothespress hc ON hc.id = hcc.clothesPressId");
		sqlStr.append(" WHERE                                                      ");
		sqlStr.append(" 	hc.roomId = :roomId                                    ");
		sqlStr.append(" AND hcc.userId = :userId                                   ");
		if (yesOrNoBinding != null) {
			sqlStr.append(" AND hcc.yesOrNoBinding = :yesOrNoBinding               ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("roomId", roomId);
		sql.setInteger("userId", userId);
		if (yesOrNoBinding != null) {
			sql.setVarChar("yesOrNoBinding", yesOrNoBinding);
		}
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosClothesPressContainer.class);
	}

	@Override
	public List<HosClothesPressContainer> selectNotBindingClothesPressContainerByRoomId(Integer roomId)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                     ");
		sqlStr.append(" 	hcc.*                                                  ");
		sqlStr.append(" FROM                                                       ");
		sqlStr.append(" 	hos_clothespress_container hcc                         ");
		sqlStr.append(" LEFT JOIN hos_clothespress hc ON hc.id = hcc.clothesPressId");
		sqlStr.append(" WHERE                                                      ");
		sqlStr.append(" 	hc.roomId = :roomId                                    ");
		sqlStr.append(" AND hcc.yesOrNoLock = :yesOrNoLock                         ");
		sqlStr.append(" AND hcc.yesOrNoBinding = :yesOrNoBinding                   ");
		sqlStr.append(" AND hcc.state= :state                                      ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("roomId", roomId);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("state", BizConst.STATE.NOTUSE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosClothesPressContainer.class);
	}

	@Override
	public int updateBindingClothesPressContainerStateById(Integer id, String state) throws BaseException {
		SQL sql = SQL.getSQL("UPDATE hos_clothespress_container SET state=:state WHERE id=:id");
		sql.setVarChar("state", state);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosClothesPress selectClothesPressById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                     ");
		sqlStr.append(" 	hc.*                                                   ");
		sqlStr.append(" FROM                                                       ");
		sqlStr.append(" 	hos_clothespress_container hcc                         ");
		sqlStr.append(" LEFT JOIN hos_clothespress hc ON hcc.clothesPressId = hc.id");
		sqlStr.append(" WHERE                                                      ");
		sqlStr.append(" 	1 = 1                                                  ");
		sqlStr.append(" AND hcc.id = :id                                           ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosClothesPress.class);
	}

	@Override
	public List<HosClothesPressContainer> selectDoorNumbersByDeviceId(String deviceId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                             ");
		sqlStr.append(" 	hcc.doorNumber                 ");
		sqlStr.append(" FROM                               ");
		sqlStr.append(" 	hos_clothespress hc,           ");
		sqlStr.append(" 	hos_clothespress_container hcc ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	1 = 1                          ");
		sqlStr.append(" AND hc.id = hcc.clothesPressId     ");
		sqlStr.append(" AND hc.number = :deviceId          ");
		sqlStr.append(" ORDER BY                           ");
		sqlStr.append(" 	doorNumber ASC                 ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("deviceId", deviceId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosClothesPressContainer.class);
	}

	@Override
	public HosClothesPressContainer selectLockerIdBydoorNumberAndDeviceId(String deviceId, String doorNumber)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                            ");
		sqlStr.append(" 	hcc.lockerNumber               ");
		sqlStr.append(" FROM                              ");
		sqlStr.append(" 	hos_clothespress hc,           ");
		sqlStr.append(" 	hos_clothespress_container hcc ");
		sqlStr.append(" WHERE                             ");
		sqlStr.append(" 	1 = 1                          ");
		sqlStr.append(" AND hc.id = hcc.clothesPressId    ");
		sqlStr.append(" AND hc.number = :deviceId         ");
		sqlStr.append(" AND hcc.doorNumber=:doorNumber    ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("deviceId", deviceId);
		sql.setVarChar("doorNumber", doorNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosClothesPressContainer.class);
	}

}
