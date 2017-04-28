package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeDao;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobe;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosWardrobeDaoImpl extends SesframeBaseDao implements HosWardrobeDao {

	@Override
	public int insertHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" ( ");
		sqlStr.append(" state, ");
		sqlStr.append(" theaterNumber, ");
		sqlStr.append(" model, ");
		sqlStr.append(" traySum, ");
		sqlStr.append(" total, ");
		sqlStr.append(" number, ");
		sqlStr.append(" description, ");
		sqlStr.append(" roomId ");
		sqlStr.append(" ) ");
		sqlStr.append(" values ");
		sqlStr.append(" ( ");
		sqlStr.append(" :state, ");
		sqlStr.append(" :theaterNumber, ");
		sqlStr.append(" :model, ");
		sqlStr.append(" :traySum, ");
		sqlStr.append(" :total, ");
		sqlStr.append(" :number, ");
		sqlStr.append(" :description, ");
		sqlStr.append(" :roomId ");
		sqlStr.append(" ) ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobe);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosWardrobeById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" delete ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" where ");
		sqlStr.append(" id ");
		sqlStr.append(" = ");
		sqlStr.append(" :id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosWardrobe(HosWardrobe hosWardrobe) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" set ");
		sqlStr.append(" state ");
		sqlStr.append(" = ");
		sqlStr.append(" :state, ");
		sqlStr.append(" model ");
		sqlStr.append(" = ");
		sqlStr.append(" :model, ");
		sqlStr.append(" theaterNumber ");
		sqlStr.append(" = ");
		sqlStr.append(" :theaterNumber, ");
		sqlStr.append(" number ");
		sqlStr.append(" = ");
		sqlStr.append(" :number, ");
		sqlStr.append(" description ");
		sqlStr.append(" = ");
		sqlStr.append(" :description, ");
		sqlStr.append(" roomId ");
		sqlStr.append(" = ");
		sqlStr.append(" :roomId ");
		sqlStr.append(" where ");
		sqlStr.append(" id ");
		sqlStr.append(" = ");
		sqlStr.append(" :id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobe);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectAllHosWardrobe(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                         ");
		sqlStr.append(" 	w.id,                                      ");
		sqlStr.append(" 	w.state,                                   ");
		sqlStr.append(" 	w.theaterNumber,                           ");
		sqlStr.append(" 	w.number,                                  ");
		sqlStr.append(" 	w.traySum,                                 ");
		sqlStr.append(" 	w.description,                             ");
		sqlStr.append(" 	(                                          ");
		sqlStr.append(" 		SELECT                                 ");
		sqlStr.append(" 			SUM(wc.alloutCount)                ");
		sqlStr.append(" 		FROM                                   ");
		sqlStr.append(" 			hos_wardrobe_container wc          ");
		sqlStr.append(" 		WHERE                                  ");
		sqlStr.append(" 			w.number = wc.warNumber            ");
		sqlStr.append(" 	) AS cnt,                                  ");
		sqlStr.append(" 	(                                          ");
		sqlStr.append(" 		SELECT                                 ");
		sqlStr.append(" 			CONCAT(t.`name`, hr.description)   ");
		sqlStr.append(" 		FROM                                   ");
		sqlStr.append(" 			hos_room hr                        ");
		sqlStr.append(" 		WHERE                                  ");
		sqlStr.append(" 			1 = 1                              ");
		sqlStr.append(" 		AND hr.id = w.roomId                   ");
		sqlStr.append(" 		AND hr.theaterNumber = t.number        ");
		sqlStr.append(" 	) AS name                                  ");
		sqlStr.append(" FROM                                           ");
		sqlStr.append(" 	hos_wardrobe w,                            ");
		sqlStr.append(" 	hos_theater t                              ");
		sqlStr.append(" WHERE                                          ");
		sqlStr.append(" 	1 = 1                                      ");
		sqlStr.append(" AND w.theaterNumber = t.number                 ");
		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" and w.number like :number");
		}

		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("number") != null) {
			sql.setVarChar("number", paramMap.getStrParam("number"));
		}
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long HosWardrobeCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");

		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" and ");
			sqlStr.append(" number ");
			sqlStr.append(" like ");
			sqlStr.append(" :number ");
		}

		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("number") != null) {
			sql.setVarChar("number", paramMap.getStrParam("number"));
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	public HosWardrobe selectHosWardrobeById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" w.id, ");
		sqlStr.append(" w.state, ");
		sqlStr.append(" w.model, ");
		sqlStr.append(" w.traySum, ");
		sqlStr.append(" w.total, ");
		sqlStr.append(" w.theaterNumber, ");
		sqlStr.append(" w.number, ");
		sqlStr.append(" w.description, ");
		sqlStr.append(" w.roomId ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe w ");
		sqlStr.append(" where ");
		sqlStr.append(" w.id = :id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		HosWardrobe hosWardrobe = this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosWardrobe.class);
		return hosWardrobe;
	}

	public int deleteHosOpeWardrobeByWarNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_ope_wardrobe  where warNumber = :number");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int deleteHosOpeWarByWarNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_ope_war  where warNumber = :number");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateOpeWarNumberByWarNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_ope_war ");
		sqlStr.append(" set ");
		sqlStr.append(" warNumber=:newNumber ");
		sqlStr.append(" where ");
		sqlStr.append(" warNumber=:oldNumber  ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("oldNumber", oldNumber);
		sql.setVarChar("newNumber", newNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateWarTheaterNumberByNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber ");
		sqlStr.append(" = ");
		sqlStr.append(" :newNumber ");
		sqlStr.append(" where ");
		sqlStr.append(" theaterNumber ");
		sqlStr.append(" = ");
		sqlStr.append(" :oldNumber ");
		sql = SQL.getSQL(sqlStr);

		sql.setVarChar("oldNumber", oldNumber);
		sql.setVarChar("newNumber", newNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<Map<String, Object>> selectOperationFromWardBynumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select SUM(ow.alloutCount) AS cnt from ");
		sqlStr.append(" hos_wardrobe_container ow ");
		sqlStr.append(" where  ");
		sqlStr.append(" ow.warNumber  ");
		sqlStr.append(" =:number  ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;

	}

	public List<Map<String, Object>> selectWardListByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (number != null) {
			sqlStr.append(" number like :number ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" s.theaterNumber ");
		sqlStr.append(" =:theaterNumber ");

		String orderDir = pm.getOrder();
		String sort = pm.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		if (number != null) {
			sqlStr.append(" and ");
			sql.setVarChar("number", number);
		}
		sql.setInteger("start", pm.getStart());
		sql.setInteger("size", pm.getSize());
		sql.setVarChar("theaterNumber", pm.getStrParam("theaterNumber"));
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}

	public Long selectWardCountByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (number != null) {
			sqlStr.append(" and ");
			sqlStr.append(" number like :number ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" s.theaterNumber ");
		sqlStr.append(" =:theaterNumber ");
		sql = SQL.getSQL(sqlStr);
		if (number != null) {
			sql.setVarChar("number", number);
		}
		sql.setVarChar("theaterNumber", pm.getStrParam("theaterNumber"));
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<Map<String, Object>> selectNotWardListByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (number != null) {
			sqlStr.append(" and ");
			sqlStr.append(" number like :number ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" s.state ");
		sqlStr.append(" =:state ");

		sqlStr.append(" and ");
		sqlStr.append(" (s.theaterNumber ");
		sqlStr.append(" !=:theaterNumber ");
		sqlStr.append(" or ");
		sqlStr.append(" s.theaterNumber ");
		sqlStr.append(" is null) ");

		String orderDir = pm.getOrder();
		String sort = pm.getSort();
		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");

		sql = SQL.getSQL(sqlStr);
		if (number != null) {
			sql.setVarChar("number", number);
		}
		sql.setInteger("start", pm.getStart());
		sql.setInteger("size", pm.getSize());
		sql.setVarChar("theaterNumber", pm.getStrParam("theaterNumber"));
		sql.setVarChar("state", BizConst.STATE.NOTUSE);// 未使用
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}

	public Long selectNotWardCountByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (number != null) {
			sqlStr.append(" and ");
			sqlStr.append(" number like :number ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" s.state ");
		sqlStr.append(" =:state ");

		sqlStr.append(" and ");
		sqlStr.append(" (s.theaterNumber ");
		sqlStr.append(" !=:theaterNumber ");
		sqlStr.append(" or ");
		sqlStr.append(" s.theaterNumber ");
		sqlStr.append(" is null) ");
		sql = SQL.getSQL(sqlStr);
		if (number != null) {
			sql.setVarChar("number", number);
		}
		sql.setVarChar("theaterNumber", pm.getStrParam("theaterNumber"));
		sql.setVarChar("state", BizConst.STATE.NOTUSE);// 未使用
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public int assignTheaterToWar(HosWardrobe hosWardrobe) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber=:theaterNumber, ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobe);
		;
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int removeWarFromTheater(HosWardrobe hosWardrobe) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber=:theaterNumber, ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobe);
		;
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateTheNumberStateByThenumber(HosWardrobe hosWardrobe) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber=:theaterNumber, ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" where ");
		sqlStr.append(" theaterNumber=:oldNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobe);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public Long selectWarByNumber(String number, Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" where ");
		sqlStr.append(" number=:number ");

		if (id != null) {
			sqlStr.append(" and ");
			sqlStr.append(" id!=:id ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		if (id != null) {
			sql.setInteger("id", id);
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<HosWardrobe> loadWardrobeByThNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" s.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" s.theaterNumber=:number ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosWardrobe.class);
	}

	public List<HosWardrobe> loadWardrobeByWarNumber(String warNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" s.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" s.number=:number ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", warNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosWardrobe.class);
	}

	public HosWardrobe selectHosWardrobeEnabledByNumber(String warNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" s.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" s.number=:warNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("warNumber", warNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosWardrobe.class);
	}

	public int updateHosWardrobeEnabledByNumber(HosWardrobe hosWardrobe) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_wardrobe ");
		sqlStr.append(" set ");
		sqlStr.append(" enabled=:enabled ");
		sqlStr.append(" where ");
		sqlStr.append(" number=:number ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobe);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<Map<String, Object>> selectHosWardrobeMonitoredByUserId(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT DISTINCT                                       ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	SUM(hwc.trayTotal) AS allOpeCount,                ");
		sqlStr.append(" 	SUM(hwc.alloutCount) AS opeCount,                 ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			hr.description                            ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_wardrobe hw2                          ");
		sqlStr.append(" 		LEFT JOIN hos_room hr ON hw2.roomId = hr.id   ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			hwc.warNumber = hw2.number                ");
		sqlStr.append(" 	) AS room,                                        ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			ht.`name`                                 ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_theater ht                            ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			ht.number = hw.theaterNumber              ");
		sqlStr.append(" 	) AS name                                         ");
		sqlStr.append(" FROM                                                  ");
		sqlStr.append(" 	hos_wardrobe_container hwc                        ");
		sqlStr.append(" LEFT JOIN hos_wardrobe hw ON hwc.warNumber = hw.number");
		sqlStr.append(" WHERE                                                 ");
		sqlStr.append(" 	1 = 1                                             ");
		sqlStr.append(" AND hwc.opeSize IS NOT NULL                           ");
		sqlStr.append(" AND hw.theaterNumber IN (                             ");
		sqlStr.append(" 	SELECT                                            ");
		sqlStr.append(" 		ht.number                                     ");
		sqlStr.append(" 	FROM                                              ");
		sqlStr.append(" 		hos_theater ht                                ");
		sqlStr.append(" 	WHERE                                             ");
		sqlStr.append(" 		ht.id IN (                                    ");
		sqlStr.append(" 			SELECT                                    ");
		sqlStr.append(" 				hut.theaterId                         ");
		sqlStr.append(" 			FROM                                      ");
		sqlStr.append(" 				hos_user_theater hut                  ");
		sqlStr.append(" 			WHERE                                     ");
		sqlStr.append(" 				hut.userId = :userId                  ");
		sqlStr.append(" 		)                                             ");
		sqlStr.append(" )                                                     ");
		sqlStr.append(" GROUP BY                                              ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	room                                              ");
		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by hwc.opeSize asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		sql.setInteger("userId", paramMap.getIntegerParam("userId"));
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public Long HosWardrobeCountMonitoredByUserId(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT DISTINCT                                       ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	SUM(hwc.trayTotal) AS allOpeCount,                ");
		sqlStr.append(" 	SUM(hwc.alloutCount) AS opeCount,                 ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			hr.description                            ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_wardrobe hw2                          ");
		sqlStr.append(" 		LEFT JOIN hos_room hr ON hw2.roomId = hr.id   ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			hwc.warNumber = hw2.number                ");
		sqlStr.append(" 	) AS room,                                        ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			ht.`name`                                 ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_theater ht                            ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			ht.number = hw.theaterNumber              ");
		sqlStr.append(" 	) AS name                                         ");
		sqlStr.append(" FROM                                                  ");
		sqlStr.append(" 	hos_wardrobe_container hwc                        ");
		sqlStr.append(" LEFT JOIN hos_wardrobe hw ON hwc.warNumber = hw.number");
		sqlStr.append(" WHERE                                                 ");
		sqlStr.append(" 	1 = 1                                             ");
		sqlStr.append(" AND hwc.opeSize IS NOT NULL                           ");
		sqlStr.append(" AND hw.theaterNumber IN (                             ");
		sqlStr.append(" 	SELECT                                            ");
		sqlStr.append(" 		ht.number                                     ");
		sqlStr.append(" 	FROM                                              ");
		sqlStr.append(" 		hos_theater ht                                ");
		sqlStr.append(" 	WHERE                                             ");
		sqlStr.append(" 		ht.id IN (                                    ");
		sqlStr.append(" 			SELECT                                    ");
		sqlStr.append(" 				hut.theaterId                         ");
		sqlStr.append(" 			FROM                                      ");
		sqlStr.append(" 				hos_user_theater hut                  ");
		sqlStr.append(" 			WHERE                                     ");
		sqlStr.append(" 				hut.userId = :userId                  ");
		sqlStr.append(" 		)                                             ");
		sqlStr.append(" )                                                     ");
		sqlStr.append(" GROUP BY                                              ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	room                                              ");

		sql = SQL.getSQL(sqlStr);

		sql.setInteger("userId", paramMap.getIntegerParam("userId"));
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectAllHosWardrobeMonitored(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT DISTINCT                                       ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	SUM(hwc.trayTotal) AS allOpeCount,                ");
		sqlStr.append(" 	SUM(hwc.alloutCount) AS opeCount,                 ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			hr.description                            ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_wardrobe hw2                          ");
		sqlStr.append(" 		LEFT JOIN hos_room hr ON hw2.roomId = hr.id   ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			hwc.warNumber = hw2.number                ");
		sqlStr.append(" 	) AS room,                                        ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			ht.`name`                                 ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_theater ht                            ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			ht.number = hw.theaterNumber              ");
		sqlStr.append(" 	) AS name                                         ");
		sqlStr.append(" FROM                                                  ");
		sqlStr.append(" 	hos_wardrobe_container hwc                        ");
		sqlStr.append(" LEFT JOIN hos_wardrobe hw ON hwc.warNumber = hw.number");
		sqlStr.append(" WHERE                                                 ");
		sqlStr.append(" 	1 = 1                                             ");
		sqlStr.append(" AND hwc.opeSize IS NOT NULL                           ");
		sqlStr.append(" AND hw.theaterNumber IN (                             ");
		sqlStr.append(" 	SELECT                                            ");
		sqlStr.append(" 		ht.number                                     ");
		sqlStr.append(" 	FROM                                              ");
		sqlStr.append(" 		hos_theater ht                                ");
		sqlStr.append(" 	WHERE 1=1                                         ");
		sqlStr.append(" )                                                     ");
		sqlStr.append(" GROUP BY                                              ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	room                                              ");

		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by hwc.opeSize asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long HosWardrobeAllCountMonitored(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT DISTINCT                                       ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	SUM(hwc.trayTotal) AS allOpeCount,                ");
		sqlStr.append(" 	SUM(hwc.alloutCount) AS opeCount,                 ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			hr.description                            ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_wardrobe hw2                          ");
		sqlStr.append(" 		LEFT JOIN hos_room hr ON hw2.roomId = hr.id   ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			hwc.warNumber = hw2.number                ");
		sqlStr.append(" 	) AS room,                                        ");
		sqlStr.append(" 	(                                                 ");
		sqlStr.append(" 		SELECT                                        ");
		sqlStr.append(" 			ht.`name`                                 ");
		sqlStr.append(" 		FROM                                          ");
		sqlStr.append(" 			hos_theater ht                            ");
		sqlStr.append(" 		WHERE                                         ");
		sqlStr.append(" 			ht.number = hw.theaterNumber              ");
		sqlStr.append(" 	) AS name                                         ");
		sqlStr.append(" FROM                                                  ");
		sqlStr.append(" 	hos_wardrobe_container hwc                        ");
		sqlStr.append(" LEFT JOIN hos_wardrobe hw ON hwc.warNumber = hw.number");
		sqlStr.append(" WHERE                                                 ");
		sqlStr.append(" 	1 = 1                                             ");
		sqlStr.append(" AND hwc.opeSize IS NOT NULL                           ");
		sqlStr.append(" AND hw.theaterNumber IN (                             ");
		sqlStr.append(" 	SELECT                                            ");
		sqlStr.append(" 		ht.number                                     ");
		sqlStr.append(" 	FROM                                              ");
		sqlStr.append(" 		hos_theater ht                                ");
		sqlStr.append(" 	WHERE 1=1                                         ");
		sqlStr.append(" )                                                     ");
		sqlStr.append(" GROUP BY                                              ");
		sqlStr.append(" 	hwc.opeSize,                                      ");
		sqlStr.append(" 	room                                              ");

		sql = SQL.getSQL(sqlStr);

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();

	}

	@Override
	public HosWardrobe selectHosWardrobeByNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" w.id, ");
		sqlStr.append(" w.state, ");
		sqlStr.append(" w.model, ");
		sqlStr.append(" w.traySum, ");
		sqlStr.append(" w.total, ");
		sqlStr.append(" w.theaterNumber, ");
		sqlStr.append(" w.number, ");
		sqlStr.append(" w.roomId, ");
		sqlStr.append(" w.description ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe w ");
		sqlStr.append(" where ");
		sqlStr.append(" w.number = :number ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		HosWardrobe hosWardrobe = this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosWardrobe.class);
		return hosWardrobe;
	}
}
