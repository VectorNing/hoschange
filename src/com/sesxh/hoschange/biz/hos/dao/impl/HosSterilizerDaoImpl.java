package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerDao;
import com.sesxh.hoschange.biz.hos.entity.HosShoesSterilizer;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizer;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosSterilizerDaoImpl extends SesframeBaseDao implements HosSterilizerDao {

	@Override
	public int insertHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" Hos_sterilizer( ");
		sqlStr.append(" state, ");
		sqlStr.append(" theaterNumber, ");
		sqlStr.append(" number, ");
		sqlStr.append(" description, ");
		sqlStr.append(" total, ");
		sqlStr.append(" startDoor ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :state, ");
		sqlStr.append(" :theaterNumber, ");
		sqlStr.append(" :number, ");
		sqlStr.append(" :description, ");
		sqlStr.append(" :total, ");
		sqlStr.append(" :startDoor ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosSterilizer);

		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosSterilizerById(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("delete from Hos_sterilizer where id = :id");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosSterilizer(HosSterilizer hosSterilizer) throws BaseException {
		SQL sql;
		sql = SQL.getSQL(
				"update Hos_sterilizer set state = :state,number = :number,description = :description,theaterNumber=:theaterNumber where id = :id ");
		sql.setParas(hosSterilizer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectAllHosSterilizer(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" st.id, ");
		sqlStr.append(" st.state, ");
		sqlStr.append(" st.theaterNumber, ");
		sqlStr.append(" th.name AS name, ");
		sqlStr.append(" st.number, ");
		sqlStr.append(" st.total, ");
		sqlStr.append(" st.description, ");
		sqlStr.append(" (SELECT ");
		sqlStr.append(" SUM(1) ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_sterilizer_container s  ");
		sqlStr.append(" WHERE st.number = s.steNumber AND s.state='1') AS cnt  ");
		sqlStr.append(" FROM ");
		sqlStr.append(" Hos_sterilizer st  ");
		sqlStr.append(" LEFT JOIN hos_theater th ");
		sqlStr.append(" ON st.theaterNumber = th.number ");
		sqlStr.append(" WHERE 1 = 1  ");

		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" and st.number like :number");
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
	public Long HosSterilizerCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" FROM ");
		sqlStr.append(" Hos_sterilizer st  ");
		sqlStr.append(" LEFT JOIN hos_theater th ");
		sqlStr.append(" ON st.theaterNumber = th.id ");
		sqlStr.append(" WHERE 1 = 1  ");
		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" and st.number like :number");
		}

		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("number") != null) {
			sql.setVarChar("number", paramMap.getStrParam("number"));
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public int allotShoesToSterilizer(HosShoesSterilizer hosShoesSterilizer) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("insert into hos_shoes_sterilizer(shId,stId,count) values(:shId,:stId,:count)");
		sql.setParas(hosShoesSterilizer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectShoeBySterilizerId(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(
				" SELECT ss.id,ss.shId,ss.stId,ss.count,s.shoeSize,t.number FROM hos_shoes_sterilizer ss LEFT JOIN"
						+ " hos_shoes s ON ss.shId = s.id LEFT JOIN hos_sterilizer t ON ss.stId = t.id   WHERE 1=1 and ss.stId = :stId ");
		sql = SQL.getSQL(sqlStr);
		if (id == null) {
			return Lists.newArrayList();
		}
		sql.setInteger("stId", id);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public HosSterilizer selectHosShoesSterilizerById(Integer id) throws BaseException {
		String sqlStr = " SELECT id, state,theaterNumber,number,description FROM Hos_sterilizer where 1=1 and id = :id ";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizer.class);
	}

	public Long selectSteByNumber(String number, Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" Hos_sterilizer ");
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

	public int deleteShoesSterilizerBySteNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_shoes_sterilizer  where steNumber = :number");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int deleteShoesSteBySteNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_shoes_ste  where steNumber = :number");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<Map<String, Object>> selectShoesIdByWarNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("select s.shoesId as id from hos_shoes_sterilizer s  where steNumber = :number");
		sql.setVarChar("number", number);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}

	public List<Map<String, Object>> selectShoNumberByWarNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("select s.shoNumber as number from hos_shoes_ste s  where steNumber = :number");
		sql.setVarChar("number", number);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}

	public Long selectShoesFromSterilizerByNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String state = BizConst.STATE.USE;
		sqlStr.append(" select 1 from ");
		// sqlStr.append(" hos_shoes_sterilizer ss ");
		sqlStr.append(" hos_sterilizer_container s ");
		sqlStr.append(" where ");
		sqlStr.append(" s.steNumber  ");
		sqlStr.append(" =:number  ");
		sqlStr.append(" and ");
		sqlStr.append(" s.state ");
		sqlStr.append(" =:state ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		sql.setVarChar("state", state);
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public int updateShoSteSteNumberByNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_shoes_ste ");
		sqlStr.append(" set ");
		sqlStr.append(" steNumber = :newNumber ");
		sqlStr.append(" where ");
		sqlStr.append(" steNumber = :oldNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("oldNumber", oldNumber);
		sql.setVarChar("newNumber", newNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateStrTheaterNumberByNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer ");
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

	public Long selectSterCountByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_sterilizer s ");
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

	public List<Map<String, Object>> selectSterListByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_sterilizer s ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (number != null) {
			sqlStr.append(" and ");
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

	public Long selectNotSterCountByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_sterilizer s ");
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

	public List<Map<String, Object>> selectNotSterListByTheNumber(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String number = pm.getStrParam("number");
		sqlStr.append(" select s.* from ");
		sqlStr.append(" hos_sterilizer s ");
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

	public int assignTheaterToSte(HosSterilizer hosSterilizer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber=:theaterNumber, ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosSterilizer);
		;
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int removeSteFromTheater(HosSterilizer hosSterilizer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber=:theaterNumber, ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosSterilizer);
		;
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateTheNumberStateByThenumber(HosSterilizer hosSterilizer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer ");
		sqlStr.append(" set ");
		sqlStr.append(" theaterNumber=:theaterNumber, ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" where ");
		sqlStr.append(" theaterNumber=:oldNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosSterilizer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<HosSterilizer> selectSterilizerByThNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" s.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer s ");
		sqlStr.append(" where ");
		sqlStr.append(" s.theaterNumber=:number ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosSterilizer.class);
	}

	public List<Map<String, Object>> selectHosSterilizerMonitoredByUserId(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT DISTINCT                                         ");
		sqlStr.append(" 	hsc.shoesSize,                                      ");
		sqlStr.append(" 	COUNT(hsc.shoesSize) AS sumCount,                   ");
		sqlStr.append(" 	(                                                   ");
		sqlStr.append(" 		SELECT                                          ");
		sqlStr.append(" 			COUNT(hsc2.shoesSize)                       ");
		sqlStr.append(" 		FROM                                            ");
		sqlStr.append(" 			hos_sterilizer_container hsc2               ");
		sqlStr.append(" 		WHERE                                           ");
		sqlStr.append(" 			hsc2.yesOrNoLock = :yesOrNoLock             ");
		sqlStr.append(" 		AND hsc2.yesOrNoBinding = :yesOrNoBinding       ");
		sqlStr.append(" 		AND hsc2.state = :state                         ");
		sqlStr.append(" 		AND hsc2.shoesSize = hsc.shoesSize              ");
		sqlStr.append(" 	) AS haveShoesCount,                                ");
		sqlStr.append(" 	(                                                   ");
		sqlStr.append(" 		SELECT                                          ");
		sqlStr.append(" 			ht2. NAME                                   ");
		sqlStr.append(" 		FROM                                            ");
		sqlStr.append(" 			hos_theater ht2                             ");
		sqlStr.append(" 		WHERE                                           ");
		sqlStr.append(" 			ht2.number = hs.theaterNumber               ");
		sqlStr.append(" 	) AS name                                           ");
		sqlStr.append(" FROM                                                    ");
		sqlStr.append(" 	hos_sterilizer_container hsc                        ");
		sqlStr.append(" LEFT JOIN hos_sterilizer hs ON hs.number = hsc.steNumber");
		sqlStr.append(" WHERE                                                   ");
		sqlStr.append(" 	1 = 1                                               ");
		sqlStr.append(" AND hsc.shoesSize is NOT NULL                           ");
		sqlStr.append(" AND hs.theaterNumber IN (                               ");
		sqlStr.append(" 	SELECT                                              ");
		sqlStr.append(" 		ht.number                                       ");
		sqlStr.append(" 	FROM                                                ");
		sqlStr.append(" 		hos_theater ht                                  ");
		sqlStr.append(" 	WHERE                                               ");
		sqlStr.append(" 		ht.id IN (                                      ");
		sqlStr.append(" 			SELECT                                      ");
		sqlStr.append(" 				hut.theaterId                           ");
		sqlStr.append(" 			FROM                                        ");
		sqlStr.append(" 				hos_user_theater hut                    ");
		sqlStr.append(" 			WHERE                                       ");
		sqlStr.append(" 				hut.userId = :userId                    ");
		sqlStr.append(" 		)                                               ");
		sqlStr.append(" )                                                       ");
		sqlStr.append(" GROUP BY                                                ");
		sqlStr.append(" 	hsc.shoesSize,name                                  ");

		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by hsc.shoesSize  asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("state", BizConst.STATE.USE);
		sql.setInteger("userId", paramMap.getIntegerParam("userId"));
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public Long HosSterilizerCountMonitoredByUserId(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT DISTINCT                                         ");
		sqlStr.append(" 	hsc.shoesSize,                                      ");
		sqlStr.append(" 	(                                                   ");
		sqlStr.append(" 		SELECT                                          ");
		sqlStr.append(" 			ht2. NAME                                   ");
		sqlStr.append(" 		FROM                                            ");
		sqlStr.append(" 			hos_theater ht2                             ");
		sqlStr.append(" 		WHERE                                           ");
		sqlStr.append(" 			ht2.number = hs.theaterNumber               ");
		sqlStr.append(" 	) AS name                                           ");
		sqlStr.append(" FROM                                                    ");
		sqlStr.append(" 	hos_sterilizer_container hsc                        ");
		sqlStr.append(" LEFT JOIN hos_sterilizer hs ON hs.number = hsc.steNumber");
		sqlStr.append(" WHERE                                                   ");
		sqlStr.append(" 	1 = 1                                               ");
		sqlStr.append(" AND hsc.shoesSize is NOT NULL                           ");
		sqlStr.append(" AND hs.theaterNumber IN (                               ");
		sqlStr.append(" 	SELECT                                              ");
		sqlStr.append(" 		ht.number                                       ");
		sqlStr.append(" 	FROM                                                ");
		sqlStr.append(" 		hos_theater ht                                  ");
		sqlStr.append(" 	WHERE                                               ");
		sqlStr.append(" 		ht.id IN (                                      ");
		sqlStr.append(" 			SELECT                                      ");
		sqlStr.append(" 				hut.theaterId                           ");
		sqlStr.append(" 			FROM                                        ");
		sqlStr.append(" 				hos_user_theater hut                    ");
		sqlStr.append(" 			WHERE                                       ");
		sqlStr.append(" 				hut.userId = :userId                    ");
		sqlStr.append(" 		)                                               ");
		sqlStr.append(" )                                                       ");
		sqlStr.append(" GROUP BY                                                ");
		sqlStr.append(" 	hsc.shoesSize,name                                  ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", paramMap.getIntegerParam("userId"));

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public HosSterilizer selectSterilizerByNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT              ");
		sqlStr.append(" 	*               ");
		sqlStr.append(" FROM                ");
		sqlStr.append(" 	hos_sterilizer  ");
		sqlStr.append(" WHERE               ");
		sqlStr.append(" number = :number    ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizer.class);
	}

	@Override
	public List<HosSterilizer> selectAllHosSterilizer(String theaterNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                              ");
		sqlStr.append(" 	*                               ");
		sqlStr.append(" FROM                                ");
		sqlStr.append(" 	hos_sterilizer                  ");
		sqlStr.append(" WHERE                               ");
		sqlStr.append(" 	theaterNumber = :theaterNumber  ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("theaterNumber", theaterNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosSterilizer.class);
	}

	@Override
	public List<Map<String, Object>> selectAllHosSterilizerMonitored(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT DISTINCT                                         ");
		sqlStr.append(" 	hsc.shoesSize,                                      ");
		sqlStr.append(" 	COUNT(hsc.shoesSize) AS sumCount,                   ");
		sqlStr.append(" 	(                                                   ");
		sqlStr.append(" 		SELECT                                          ");
		sqlStr.append(" 			COUNT(hsc2.shoesSize)                       ");
		sqlStr.append(" 		FROM                                            ");
		sqlStr.append(" 			hos_sterilizer_container hsc2               ");
		sqlStr.append(" 		WHERE                                           ");
		sqlStr.append(" 			hsc2.yesOrNoLock = :yesOrNoLock             ");
		sqlStr.append(" 		AND hsc2.yesOrNoBinding = :yesOrNoBinding       ");
		sqlStr.append(" 		AND hsc2.state = :state                         ");
		sqlStr.append(" 		AND hsc2.shoesSize = hsc.shoesSize              ");
		sqlStr.append(" 	) AS haveShoesCount,                                ");
		sqlStr.append(" 	(                                                   ");
		sqlStr.append(" 		SELECT                                          ");
		sqlStr.append(" 			ht2. NAME                                   ");
		sqlStr.append(" 		FROM                                            ");
		sqlStr.append(" 			hos_theater ht2                             ");
		sqlStr.append(" 		WHERE                                           ");
		sqlStr.append(" 			ht2.number = hs.theaterNumber               ");
		sqlStr.append(" 	) AS name                                           ");
		sqlStr.append(" FROM                                                    ");
		sqlStr.append(" 	hos_sterilizer_container hsc                        ");
		sqlStr.append(" LEFT JOIN hos_sterilizer hs ON hs.number = hsc.steNumber");
		sqlStr.append(" WHERE                                                   ");
		sqlStr.append(" 	1 = 1                                               ");
		sqlStr.append(" AND hsc.shoesSize is NOT NULL                           ");
		sqlStr.append(" AND hs.theaterNumber IN (                               ");
		sqlStr.append(" 	SELECT                                              ");
		sqlStr.append(" 		ht.number                                       ");
		sqlStr.append(" 	FROM                                                ");
		sqlStr.append(" 		hos_theater ht                                  ");
		sqlStr.append(" 	WHERE 1=1                                           ");
		sqlStr.append(" )                                                       ");
		sqlStr.append(" GROUP BY                                                ");
		sqlStr.append(" 	hsc.shoesSize,name                                  ");

		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by hsc.shoesSize  asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("state", BizConst.STATE.USE);
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long HosSterilizerAllCountMonitored(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT DISTINCT                                         ");
		sqlStr.append(" 	hsc.shoesSize,                                      ");
		sqlStr.append(" 	(                                                   ");
		sqlStr.append(" 		SELECT                                          ");
		sqlStr.append(" 			ht2. NAME                                   ");
		sqlStr.append(" 		FROM                                            ");
		sqlStr.append(" 			hos_theater ht2                             ");
		sqlStr.append(" 		WHERE                                           ");
		sqlStr.append(" 			ht2.number = hs.theaterNumber               ");
		sqlStr.append(" 	) AS name                                           ");
		sqlStr.append(" FROM                                                    ");
		sqlStr.append(" 	hos_sterilizer_container hsc                        ");
		sqlStr.append(" LEFT JOIN hos_sterilizer hs ON hs.number = hsc.steNumber");
		sqlStr.append(" WHERE                                                   ");
		sqlStr.append(" 	1 = 1                                               ");
		sqlStr.append(" AND hsc.shoesSize is NOT NULL                           ");
		sqlStr.append(" AND hs.theaterNumber IN (                               ");
		sqlStr.append(" 	SELECT                                              ");
		sqlStr.append(" 		ht.number                                       ");
		sqlStr.append(" 	FROM                                                ");
		sqlStr.append(" 		hos_theater ht                                  ");
		sqlStr.append(" 	WHERE 1=1                                           ");
		sqlStr.append(" )                                                       ");
		sqlStr.append(" GROUP BY                                                ");
		sqlStr.append(" 	hsc.shoesSize,name                                  ");
		sql = SQL.getSQL(sqlStr);
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}
}
