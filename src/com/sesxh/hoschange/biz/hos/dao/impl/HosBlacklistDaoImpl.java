package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosBlacklistDao;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklist;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosBlacklistDaoImpl extends SesframeBaseDao implements HosBlacklistDao {

	public int insertHosBlacklist(HosBlacklist hosBlacklist) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_blacklist( ");
		sqlStr.append(" userId, ");
		sqlStr.append(" creatorTime, ");
		sqlStr.append(" type ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :userId, ");
		sqlStr.append(" :creatorTime, ");
		sqlStr.append(" :type ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklist);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public HosBlacklist selectHosBlacklistByUserIdAndType(Integer userId, String type) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" b.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_blacklist b ");
		sqlStr.append(" where b.userId=:userId   ");
		sqlStr.append(" and  ");
		sqlStr.append(" b.type=:type ");
		sqlStr.append(" and  ");
		sqlStr.append(" b.enabled=:enabled ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		sql.setVarChar("type", type);
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosBlacklist.class);
	}

	public int updateHosBlacklist(HosBlacklist hosBlacklist) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_blacklist ");
		sqlStr.append(" set ");
		sqlStr.append(" enabled =:enabled, ");
		sqlStr.append(" outTime =:outTime, ");
		sqlStr.append(" operationUserId =:operationUserId ");
		sqlStr.append(" where ");
		sqlStr.append(" id =:id ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklist);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectHosBlacklistSet(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                      ");
		sqlStr.append(" 	hb.*, (                                 ");
		sqlStr.append(" 		SELECT                              ");
		sqlStr.append(" 			aud.userName                    ");
		sqlStr.append(" 		FROM                                ");
		sqlStr.append(" 			auth_user_detail aud            ");
		sqlStr.append(" 		WHERE                               ");
		sqlStr.append(" 			1 = 1                           ");
		sqlStr.append(" 		AND aud.userId = hb.userId          ");
		sqlStr.append(" 	) AS userName,                          ");
		sqlStr.append(" 	(                                       ");
		sqlStr.append(" 		SELECT                              ");
		sqlStr.append(" 			ad.userName                     ");
		sqlStr.append(" 		FROM                                ");
		sqlStr.append(" 			auth_user_detail ad             ");
		sqlStr.append(" 		WHERE                               ");
		sqlStr.append(" 			1 = 1                           ");
		sqlStr.append(" 		AND hb.operationUserId = ad.userId  ");
		sqlStr.append(" 	) AS operationName                      ");
		sqlStr.append(" FROM                                        ");
		sqlStr.append(" 	hos_blacklist hb,                       ");
		sqlStr.append(" 	auth_user_detail a                      ");
		sqlStr.append(" WHERE                                       ");
		sqlStr.append(" 	1 = 1                                   ");
		sqlStr.append(" AND hb.userId = a.userId                    ");
		String enabled = pm.getStrParam("enabled");
		if (enabled != null) {
			sqlStr.append(" and hb.enabled = :enabled ");
		}
		String type = pm.getStrParam("type");
		if (type != null) {
			sqlStr.append(" and hb.type = :type ");
		}
		String userName = pm.getStrParam("userName");
		if (userName != null) {
			sqlStr.append(" and a.userName like :userName ");
		}
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
		if (enabled != null) {
			sql.setVarChar("enabled", enabled);
		}
		if (type != null) {
			sql.setVarChar("type", type);
		}
		if (userName != null) {
			sql.setVarChar("userName", userName);
		}

		sql.setInteger("start", pm.getStart());
		sql.setInteger("size", pm.getSize());

		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long selectHosBlacklistCount(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_blacklist ");
		sqlStr.append(" where 1=1 ");

		String enabled = pm.getStrParam("enabled");
		if (enabled != null) {
			sqlStr.append(" and enabled = :enabled ");
		}

		String type = pm.getStrParam("type");
		if (type != null) {
			sqlStr.append(" and type = :type ");
		}
		sql = SQL.getSQL(sqlStr);

		if (enabled != null) {
			sql.setVarChar("enabled", enabled);
		}
		if (type != null) {
			sql.setVarChar("type", type);
		}

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	public int displayScreenHosBacklist(HosBlacklist hosBlacklist) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_blacklist ");
		sqlStr.append(" set ");
		sqlStr.append(" display =:display ");
		sqlStr.append(" where ");
		sqlStr.append(" id =:id ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklist);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
}
