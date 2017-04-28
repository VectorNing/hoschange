package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.hos.dao.HosPowerDao;
import com.sesxh.hoschange.biz.hos.entity.HosPower;
import com.sesxh.hoschange.common.data.ParamMap;

@Repository
public class HosPowerDaoImpl extends SesframeBaseBpo implements HosPowerDao {
	/**
	 * 删除超过时效的权限
	 * 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public int deleteInvilidPower(String time) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append("DELETE                             ");
		sqlStr.append("FROM                               ");
		sqlStr.append("	hos_power                         ");
		sqlStr.append("WHERE                              ");
		sqlStr.append("	validtime < :time ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("time", time);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteInvilidPower(String userid, String operschedulid) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append("DELETE                             ");
		sqlStr.append("FROM                               ");
		sqlStr.append("	hos_power                         ");
		sqlStr.append("WHERE  1=1 AND                     ");
		if (userid != null && userid != "") {
			sqlStr.append("	userid = :userid  and ");
		}
		sqlStr.append("	operschedulid = :operschedulid ");
		sql = SQL.getSQL(sqlStr);
		if (userid != null && userid != "") {
			sql.setVarChar("userid", userid);
		}
		sql.setVarChar("operschedulid", operschedulid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int insertPower(HosPower hosPower) throws BaseException {

		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_power ");
		sqlStr.append(" ( ");
		sqlStr.append(" userid,");
		sqlStr.append(" begintime,");
		sqlStr.append(" endtime, ");
		sqlStr.append(" synway, ");
		sqlStr.append(" operschedulid ");
		sqlStr.append(" ) ");
		sqlStr.append(" values ");
		sqlStr.append(" ( ");
		sqlStr.append(" :userid, ");
		sqlStr.append(" :begintime, ");
		sqlStr.append(" :endtime, ");
		sqlStr.append(" :synway, ");
		sqlStr.append(" :operschedulid ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosPower);
		return this.sessionManager.getSession().getSQLExecutor().setSQL(sql).executeUpdate();

	}

	@Override
	public int updatePower(HosPower hosPower) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update hos_power   ");
		sqlStr.append(" set ");
		sqlStr.append(" begintime = :begintime,   ");
		sqlStr.append(" endtime = :endtime,   ");
		sqlStr.append(" synway = :synway   ");
		sqlStr.append(" where ");
		sqlStr.append(" userid = :userid  and ");
		sqlStr.append(" operschedulid = :operschedulid ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosPower);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int selectPowerCount(String userid, String operschedulid) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                               ");
		sqlStr.append("	*                                   ");
		sqlStr.append("FROM                                 ");
		sqlStr.append("	hos_power                           ");
		sqlStr.append("WHERE                                ");
		sqlStr.append("	userid = :userid              ");
		sqlStr.append("AND operschedulid = :operschedulid   ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("userid", userid);
		sql.setVarChar("operschedulid", operschedulid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();

	}

	@Override
	public List<Map<String, Object>> selectPowerUserid(String operschedulid) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	userid      ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	hos_power       ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	operschedulid = :operschedulid");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operschedulid", operschedulid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long HosPowerCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                    ");
		sqlStr.append(" 	1                                                     ");
		sqlStr.append(" FROM                                                      ");
		sqlStr.append(" 	hos_power hp                                          ");
		sqlStr.append(" LEFT JOIN hos_scheduling hs ON hp.operschedulid = hs.id,  ");
		sqlStr.append("  auth_user au,                                            ");
		sqlStr.append("  auth_user_detail aud                                     ");
		sqlStr.append(" WHERE                                                     ");
		sqlStr.append(" 	1 = 1                                                 ");
		sqlStr.append(" AND hp.userid = au.id                                     ");
		sqlStr.append(" AND au.id = aud.userId                                    ");
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") == null) {
			sqlStr.append(" AND hp.begintime > :nowtime");
		}
		if (paramMap.getStrParam("username") != null) {
			sqlStr.append(" AND aud.userName LIKE :userName  ");
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") == null) {
			sqlStr.append(" AND hp.begintime > :begintime  ");
		}
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") != null) {
			sqlStr.append(" AND hp.endtime < :endtime  ");
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") != null) {
			sqlStr.append(" AND hp.begintime > :begintime AND hp.endtime < :endtime ");
		}
		if (paramMap.getStrParam("roomname") != null) {
			sqlStr.append(" AND hs.roomname LIKE :roomname  ");
		}

		sql = SQL.getSQL(sqlStr);
		if (paramMap.getStrParam("username") != null) {
			sql.setVarChar("username", paramMap.getStrParam("username"));
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") == null) {
			sql.setVarChar("begintime", paramMap.getStrParam("begintime"));
		}
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") != null) {
			sql.setVarChar("endtime", paramMap.getStrParam("endtime"));
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") != null) {
			sql.setVarChar("begintime", paramMap.getStrParam("begintime"));
			sql.setVarChar("endtime", paramMap.getStrParam("endtime"));
		}
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") == null) {
			sql.setVarChar("nowtime", paramMap.getStrParam("nowtime"));
		}
		if (paramMap.getStrParam("roomname") != null) {
			sql.setVarChar("roomname", paramMap.getStrParam("roomname"));
		}

		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> queryHosPowerAll(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                                    ");
		sqlStr.append(" 	aud.userName AS username,                             ");
		sqlStr.append(" 	au.jobnumber AS jobnumber,                            ");
		sqlStr.append(" 	hp.begintime AS begintime,                            ");
		sqlStr.append(" 	hp.endtime AS endtime,                                ");
		sqlStr.append(" 	hs.`name` AS roomname                                 ");
		sqlStr.append(" FROM                                                      ");
		sqlStr.append(" 	hos_power hp                                          ");
		sqlStr.append(" LEFT JOIN hos_scheduling hs ON hp.operschedulid = hs.id,  ");
		sqlStr.append("  auth_user au,                                            ");
		sqlStr.append("  auth_user_detail aud                                     ");
		sqlStr.append(" WHERE                                                     ");
		sqlStr.append(" 	1 = 1                                                 ");
		sqlStr.append(" AND hp.userid = au.id                                     ");
		sqlStr.append(" AND au.id = aud.userId                                    ");

		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") == null) {
			sqlStr.append(" AND hp.begintime > :nowtime");
		}
		if (paramMap.getStrParam("username") != null) {
			sqlStr.append(" AND aud.userName LIKE :userName  ");
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") == null) {
			sqlStr.append(" AND hp.begintime > :begintime  ");
		}
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") != null) {
			sqlStr.append(" AND hp.begintime < :endtime  ");
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") != null) {
			sqlStr.append(" AND hp.begintime > :begintime AND hp.begintime < :endtime ");
		}
		if (paramMap.getStrParam("roomname") != null) {
			sqlStr.append(" AND hs.roomname LIKE :roomname  ");
		}
		sqlStr.append(" order by hp.begintime           ");
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("username") != null) {
			sql.setVarChar("username", paramMap.getStrParam("username"));
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") == null) {
			sql.setVarChar("begintime", paramMap.getStrParam("begintime"));
		}
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") != null) {
			sql.setVarChar("endtime", paramMap.getStrParam("endtime"));
		}
		if (paramMap.getStrParam("begintime") != null && paramMap.getStrParam("endtime") != null) {
			sql.setVarChar("begintime", paramMap.getStrParam("begintime"));
			sql.setVarChar("endtime", paramMap.getStrParam("endtime"));
		}
		if (paramMap.getStrParam("begintime") == null && paramMap.getStrParam("endtime") == null) {
			sql.setVarChar("nowtime", paramMap.getStrParam("nowtime"));
		}
		if (paramMap.getStrParam("roomname") != null) {
			sql.setVarChar("roomname", paramMap.getStrParam("roomname"));
		}
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());

		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int selectIfHavePower(String userid) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	*      ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	hos_power       ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	userid = :userid");
		sqlStr.append("	AND NOW() < endtime");
		sqlStr.append("	AND NOW() > begintime");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("userid", userid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
	}
}
