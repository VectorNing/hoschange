package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecycleDao;
import com.sesxh.hoschange.biz.hos.entity.HosRecycle;
import com.sesxh.hoschange.biz.hos.entity.HosRecycleContainer;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;

@Repository
public class HosRecycleDaoImp extends SesframeBaseDao implements HosRecycleDao {

	@Override
	public HosRecycle selectRecycleByNumber(String number, Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT               ");
		sqlStr.append(" 	*                ");
		sqlStr.append(" FROM                 ");
		sqlStr.append(" 	hos_recycle      ");
		sqlStr.append(" WHERE                ");
		sqlStr.append(" 	number = :number ");
		if (id != null) {
			sqlStr.append("and id!=:id ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		if (id != null) {
			sql.setInteger("id", id);
		}
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecycle.class);
	}

	@Override
	public int insertRecycle(HosRecycle hosRecycle) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" INSERT INTO hos_recycle (           ");
		sqlStr.append(" 	number,                         ");
		sqlStr.append(" 	theNumber,                      ");
		sqlStr.append(" 	state,                          ");
		sqlStr.append(" 	recycle,                        ");
		sqlStr.append(" 	type,                           ");
		sqlStr.append(" 	description                     ");
		sqlStr.append(" )                                   ");
		sqlStr.append(" VALUES                              ");
		sqlStr.append(" 	(:number, :theNumber, :state, :recycle,:type,:description)	");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRecycle);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> queryRecycleAll(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                        ");
		sqlStr.append(" 	hr.*, ht.`name`           ");
		sqlStr.append(" FROM                          ");
		sqlStr.append(" 	hos_recycle hr,           ");
		sqlStr.append(" 	hos_theater ht            ");
		sqlStr.append(" WHERE                         ");
		sqlStr.append(" 	hr.theNumber = ht.number  ");
		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" AND hr.number LIKE :number  ");
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
	public Long HosRecycleCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                       ");
		sqlStr.append(" 	1                         ");
		sqlStr.append(" FROM                         ");
		sqlStr.append(" 	hos_recycle hr,           ");
		sqlStr.append(" 	hos_theater ht            ");
		sqlStr.append(" WHERE                        ");
		sqlStr.append(" 	hr.theNumber = ht.number  ");
		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" AND hr.number LIKE :number  ");
		}
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("number") != null) {
			sql.setVarChar("number", paramMap.getStrParam("number"));
		}
		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public HosRecycle queryHosrecycleById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT           ");
		sqlStr.append(" 	*            ");
		sqlStr.append(" FROM             ");
		sqlStr.append(" 	hos_recycle  ");
		sqlStr.append(" WHERE            ");
		sqlStr.append(" 	id = :id     ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecycle.class);
	}

	@Override
	public int updateHosRecycle(HosRecycle hosRecycle) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_recycle          ");
		sqlStr.append(" SET number = :number,       ");
		sqlStr.append("  theNumber = :theNumber,    ");
		sqlStr.append("  state = :state,            ");
		sqlStr.append("  recycle = :recycle,        ");
		sqlStr.append("  type = :type,              ");
		sqlStr.append("  description = :description ");
		sqlStr.append(" WHERE                       ");
		sqlStr.append(" 	id = :id                ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRecycle);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosRecycle selectRecycleFromRecycleByNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                  ");
		sqlStr.append(" 	*                   ");
		sqlStr.append(" FROM                    ");
		sqlStr.append(" 	hos_recycle         ");
		sqlStr.append(" WHERE                   ");
		sqlStr.append(" 	number = :number    ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		HosRecycle hosRecycle = this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecycle.class);
		return hosRecycle;
	}

	@Override
	public int deleteHosRecycle(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" DELETE            ");
		sqlStr.append(" FROM              ");
		sqlStr.append(" 	hos_recycle   ");
		sqlStr.append(" WHERE             ");
		sqlStr.append(" 	id = :id        ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosRecycleCountByNumber(String number, Integer recycle) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_recycle      ");
		sqlStr.append(" SET recycle = :recycle  ");
		sqlStr.append(" WHERE                   ");
		sqlStr.append(" 	1 = 1               ");
		sqlStr.append(" AND number = :number    ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		sql.setInteger("recycle", recycle);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();

	}

	@Override
	public int insertToRecycleContainer(HosRecycleContainer hosRecycleContainer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" INSERT INTO hos_recycle_container (recycleId, size, type) ");
		sqlStr.append(" VALUES                                                    ");
		sqlStr.append(" 	(:recycleId, :size, :type)                         ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRecycleContainer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int emptyRecycle(String recycleNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_recycle    ");
		sqlStr.append(" SET recycle = 0       ");
		sqlStr.append(" WHERE                 ");
		sqlStr.append(" 	number = :number  ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", recycleNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int emptyRecycle(Integer recycleId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" DELETE                       ");
		sqlStr.append(" FROM                         ");
		sqlStr.append(" 	hos_recycle_container    ");
		sqlStr.append(" WHERE                        ");
		sqlStr.append(" 	recycleId = :recycleId   ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("recycleId", recycleId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public Long HosRecycleCountMonitoredByUserId(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                               ");
		sqlStr.append(" 	1                                                ");
		sqlStr.append(" FROM                                                 ");
		sqlStr.append(" 	hos_recycle hr                                   ");
		sqlStr.append(" LEFT JOIN hos_theater th ON hr.theNumber = th.number ");
		sqlStr.append(" WHERE                                                ");
		sqlStr.append(" 	1 = 1                                            ");
		sqlStr.append(" AND th.id IN (                                       ");
		sqlStr.append(" 	SELECT                                           ");
		sqlStr.append(" 		ht.theaterId                                 ");
		sqlStr.append(" 	FROM                                             ");
		sqlStr.append(" 		hos_user_theater ht                          ");
		sqlStr.append(" 	WHERE                                            ");
		sqlStr.append(" 		ht.userId = :userId                          ");
		sqlStr.append(" )                                                    ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("userId", paramMap.getStrParam("userId"));

		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectHosRecycleMonitoredByUserId(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                               ");
		sqlStr.append(" 	hr.*, th.`name`                                  ");
		sqlStr.append(" FROM                                                 ");
		sqlStr.append(" 	hos_recycle hr                                   ");
		sqlStr.append(" LEFT JOIN hos_theater th ON hr.theNumber = th.number ");
		sqlStr.append(" WHERE                                                ");
		sqlStr.append(" 	1 = 1                                            ");
		sqlStr.append(" AND th.id IN (                                       ");
		sqlStr.append(" 	SELECT                                           ");
		sqlStr.append(" 		ht.theaterId                                 ");
		sqlStr.append(" 	FROM                                             ");
		sqlStr.append(" 		hos_user_theater ht                          ");
		sqlStr.append(" 	WHERE                                            ");
		sqlStr.append(" 		ht.userId = :userId                          ");
		sqlStr.append(" )                                                    ");

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
		sql.setVarChar("userId", paramMap.getStrParam("userId"));
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int deleteHosRecycleContainerByRecycleId(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" DELETE                       ");
		sqlStr.append(" FROM                         ");
		sqlStr.append(" 	hos_recycle_container    ");
		sqlStr.append(" WHERE                        ");
		sqlStr.append(" 	recycleId = :recycleId   ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("recycleId", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public Long AllHosRecycleCountMonitored(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                               ");
		sqlStr.append(" 	1                                                ");
		sqlStr.append(" FROM                                                 ");
		sqlStr.append(" 	hos_recycle hr                                   ");
		sqlStr.append(" LEFT JOIN hos_theater th ON hr.theNumber = th.number ");
		sqlStr.append(" WHERE                                                ");
		sqlStr.append(" 	1 = 1                                            ");

		sql = SQL.getSQL(sqlStr);

		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectAllHosRecycleMonitored(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                               ");
		sqlStr.append(" 	hr.*, th.`name`                                  ");
		sqlStr.append(" FROM                                                 ");
		sqlStr.append(" 	hos_recycle hr                                   ");
		sqlStr.append(" LEFT JOIN hos_theater th ON hr.theNumber = th.number ");
		sqlStr.append(" WHERE                                                ");
		sqlStr.append(" 	1 = 1                                            ");

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
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

}
