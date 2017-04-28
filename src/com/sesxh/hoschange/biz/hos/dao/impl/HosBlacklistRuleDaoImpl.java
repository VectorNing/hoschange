package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosBlacklistRuleDao;
import com.sesxh.hoschange.biz.hos.entity.HosBlacklistRule;
import com.sesxh.hoschange.common.data.ParamMap;

@Repository
public class HosBlacklistRuleDaoImpl extends SesframeBaseDao implements HosBlacklistRuleDao {

	public HosBlacklistRule selectHosBlacklistRuleByModelAndType(String type, String model) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" id ");
		sqlStr.append(" rosterType, ");
		sqlStr.append(" rosterModel, ");
		sqlStr.append(" sums, ");
		sqlStr.append(" latelyNum, ");
		sqlStr.append(" rosterTime ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_blacklist_rule r ");
		sqlStr.append(" where ");
		sqlStr.append(" r.rosterType=:rosterType ");
		sqlStr.append(" and ");
		sqlStr.append(" r.rosterModel=:rosterModel ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("rosterType", type);
		sql.setVarChar("rosterModel", model);
		HosBlacklistRule hosBlacklistRule = this.getSession().getSQLExecutor().setSQL(sql)
				.executeQueryBean(HosBlacklistRule.class);
		return hosBlacklistRule;
	}

	@Override
	public List<Map<String, Object>> selectHosBlacklistRuleSet(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT ");
		sqlStr.append(" * ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_blacklist_rule ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and  ");
		sqlStr.append(" rosterModel = :rosterModel ");

		sql = SQL.getSQL(sqlStr);

		sql.setVarChar("rosterModel", (String) paramMap.get("rosterModel"));
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long selectHosBlacklistRuleCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_blacklist_rule ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and  ");
		sqlStr.append(" rosterModel = :rosterModel ");

		sql = SQL.getSQL(sqlStr);

		sql.setVarChar("rosterModel", (String) paramMap.get("rosterModel"));
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public int insertHosBlacklistRule(HosBlacklistRule hosBlacklistRule) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into");
		sqlStr.append(" hos_blacklist_rule( ");
		sqlStr.append(" rosterType, ");
		sqlStr.append(" sums, ");
		sqlStr.append(" rosterModel, ");
		sqlStr.append(" rosterTime, ");
		sqlStr.append(" description ");
		sqlStr.append(" values( ");
		sqlStr.append(" :rosterType, ");
		sqlStr.append(" :sums, ");
		sqlStr.append(" :rosterModel, ");
		sqlStr.append(" :rosterTime, ");
		sqlStr.append(" :description ");
		sqlStr.append(" ) ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklistRule);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosBlacklistRuleM1(HosBlacklistRule hosBlacklistRule) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_blacklist_rule ");
		sqlStr.append(" set ");
		sqlStr.append(" sums =:sums ");
		sqlStr.append(" where ");
		sqlStr.append(" id =:id ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklistRule);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosBlacklistRuleM2(HosBlacklistRule hosBlacklistRule) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_blacklist_rule ");
		sqlStr.append(" set ");
		sqlStr.append(" sums =:sums, ");
		sqlStr.append(" rosterTime =:rosterTime ");
		sqlStr.append(" where ");
		sqlStr.append(" id =:id ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklistRule);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosBlacklistRule selectHosBlacklistRuleById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" w.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_blacklist_rule w ");
		sqlStr.append(" where ");
		sqlStr.append(" w.id =:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		HosBlacklistRule blick = this.getSession().getSQLExecutor().setSQL(sql)
				.executeQueryBean(HosBlacklistRule.class);
		return blick;
	}

	@Override
	public List<HosBlacklistRule> selectEnabledHosBlacklistRule() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                     ");
		sqlStr.append(" 	hbr.*                  ");
		sqlStr.append(" FROM                       ");
		sqlStr.append(" 	hos_blacklist_rule hbr ");
		sqlStr.append(" WHERE                      ");
		sqlStr.append(" 	hbr.`enable` = :enable ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("enable", "1");
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosBlacklistRule.class);
	}

	@Override
	public int updateBlacklistRuleModeOn(HosBlacklistRule blacklistRule) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_blacklist_rule hbr ");
		sqlStr.append(" SET hbr.`enable` = :onEnable  ");
		sqlStr.append(" WHERE                         ");
		sqlStr.append(" 	hbr.rosterModel = :mode   ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("onEnable", "1");
		sql.setVarChar("mode", blacklistRule.getRosterModel());
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateBlacklistRuleModeOff(HosBlacklistRule blacklistRule) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_blacklist_rule hbr       ");
		sqlStr.append(" SET hbr.`enable` = :offEnable       ");
		sqlStr.append(" WHERE                               ");
		sqlStr.append(" 	hbr.rosterModel NOT IN (:mode)  ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("offEnable", "0");
		sql.setVarChar("mode", blacklistRule.getRosterModel());
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosBlacklistRuleLatelyNumM1(HosBlacklistRule hosBlacklistRule) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_blacklist_rule ");
		sqlStr.append(" set ");
		sqlStr.append(" latelyNum =:latelyNum ");
		sqlStr.append(" where ");
		sqlStr.append(" rosterModel =:rosterModel ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklistRule);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosBlacklistRuleRosterTimeM2(HosBlacklistRule hosBlacklistRule) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_blacklist_rule ");
		sqlStr.append(" set ");
		sqlStr.append(" rosterTime =:rosterTime ");
		sqlStr.append(" where ");
		sqlStr.append(" rosterModel =:rosterModel ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosBlacklistRule);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosBlacklistRule> selectHosBlacklistRuleByRosterMode(String rosterMode) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                             ");
		sqlStr.append(" 	hbr.*                          ");
		sqlStr.append(" FROM                               ");
		sqlStr.append(" 	hos_blacklist_rule hbr         ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	hbr.rosterModel = :rosterModel ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("rosterMode", rosterMode);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosBlacklistRule.class);
	}

	@Override
	public List<Map<String, Object>> queryBlackListResult() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                  ");
		sqlStr.append(" 	a.userName,                         ");
		sqlStr.append(" 	a.creatorTime,                      ");
		sqlStr.append(" 	a.department,                       ");
		sqlStr.append(" 	(cast(GROUP_CONCAT(a.type) as char)) reason        ");
		sqlStr.append(" FROM                                    ");
		sqlStr.append(" 	(                                   ");
		sqlStr.append(" 		SELECT                          ");
		sqlStr.append(" 			DATE_FORMAT(                ");
		sqlStr.append(" 				a.creatorTime,          ");
		sqlStr.append(" 				'%Y年%m月%d日'          ");
		sqlStr.append(" 			) creatorTime,              ");
		sqlStr.append(" 			b.department,               ");
		sqlStr.append(" 			b.userName,                 ");
		sqlStr.append(" 			(                           ");
		sqlStr.append(" 				CASE c.type             ");
		sqlStr.append(" 				WHEN '0' THEN           ");
		sqlStr.append(" 					'鞋子'              ");
		sqlStr.append(" 				WHEN '1' THEN           ");
		sqlStr.append(" 					'衣服'              ");
		sqlStr.append(" 				ELSE                    ");
		sqlStr.append(" 					'收纳盒'            ");
		sqlStr.append(" 				END                     ");
		sqlStr.append(" 			) type                      ");
		sqlStr.append(" 		FROM                            ");
		sqlStr.append(" 			hos_blacklist a,            ");
		sqlStr.append(" 			auth_user_detail b,         ");
		sqlStr.append(" 			hos_recovery_goods c        ");
		sqlStr.append(" 		WHERE                           ");
		sqlStr.append(" 			a.enabled = '1'             ");
		sqlStr.append(" 		AND a.userId = b.userId         ");
		sqlStr.append(" 		AND a.userId = c.userId         ");
		sqlStr.append(" 		AND c.state = '0'               ");
		sqlStr.append(" 		AND a.display = '1'               ");
		sqlStr.append(" 		GROUP BY                        ");
		sqlStr.append(" 			creatorTime,                ");
		sqlStr.append(" 			b.department,               ");
		sqlStr.append(" 			b.userName,                 ");
		sqlStr.append(" 			type                        ");
		sqlStr.append(" 	) a                                 ");
		sqlStr.append(" GROUP BY                                ");
		sqlStr.append(" 	a.userName,                         ");
		sqlStr.append(" 	a.creatorTime,                      ");
		sqlStr.append(" 	a.department                        ");

		sql = SQL.getSQL(sqlStr);
		List<Map<String, Object>> aa = null;
		try {
			aa = getSession().getSQLExecutor().setSQL(sql).executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aa;
	}
}
