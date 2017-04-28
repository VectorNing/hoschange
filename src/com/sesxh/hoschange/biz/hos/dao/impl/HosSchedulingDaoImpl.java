package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingDao;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.common.data.ParamMap;

@Repository
public class HosSchedulingDaoImpl extends SesframeBaseDao implements HosSchedulingDao {

	@Override
	public int insertHosScheduling(HosScheduling hosScheduling) throws BaseException {
		SQL sql;
		StringBuffer strSql = new StringBuffer();
		strSql.append(" insert ");
		strSql.append(" into ");
		strSql.append(" hos_scheduling ");
		strSql.append(" ( ");
		strSql.append(" thNumber, ");
		strSql.append(" name, ");
		strSql.append(" operationtime, ");
		strSql.append(" patientId, ");
		strSql.append(" operationNumber, ");
		strSql.append(" enabled ");
		strSql.append(" ) ");
		strSql.append(" values( ");
		strSql.append(" :thNumber, ");
		strSql.append(" :name, ");
		strSql.append(" :operationtime, ");
		strSql.append(" :patientId, ");
		strSql.append(" :operationNumber, ");
		strSql.append(" :enabled )");
		sql = SQL.getSQL(strSql);
		sql.setParas(hosScheduling);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosSchedulingByOperationNumber(String operationNumber) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_scheduling where operationNumber = :operationNumber");
		sql.setVarChar("operationNumber", operationNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosSchedulingById(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_scheduling where id = :id");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosScheduling(HosScheduling hosScheduling) throws BaseException {
		SQL sql;
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update ");
		strSql.append(" hos_scheduling ");
		strSql.append(" set ");
		strSql.append(" thNumber = :thNumber, ");
		strSql.append(" name = :name, ");
		strSql.append(" operationtime = :operationtime, ");
		strSql.append(" patientid = :patientid, ");
		strSql.append(" enabled = :enabled ");
		strSql.append(" where ");
		strSql.append(" operationNumber = :operationNumber ");
		sql = SQL.getSQL(strSql);
		// sql.setParas(hosScheduling);
		sql.setVarChar("thNumber", hosScheduling.getThNumber());
		sql.setVarChar("name", hosScheduling.getName());
		sql.setVarChar("operationtime", hosScheduling.getOperationtime());
		sql.setVarChar("patientid", hosScheduling.getPatientId());
		sql.setVarChar("enabled", hosScheduling.getEnabled());
		sql.setVarChar("operationNumber", hosScheduling.getOperationNumber());
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectAllHosScheduling(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String type = paramMap.getStrParam("type");
		sqlStr.append(" select ");
		sqlStr.append(" * ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_scheduling ");
		sqlStr.append(" where 1=1 ");
		if ("0".equals(type)) {// 当前排班
			sqlStr.append(" and operationtime > :nowtime");
		}
		if ("1".equals(type)) {// 历史排班
			sqlStr.append(" and operationtime < :nowtime");
		}

		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like :name");
		}
		if (paramMap.getStrParam("operationNumber") != null) {
			sqlStr.append(" and operationNumber like :operationNumber");
		}

		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		if (paramMap.getStrParam("operationNumber") != null) {
			sql.setVarChar("operationNumber", paramMap.getStrParam("operationNumber"));
		}

		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		sql.setVarChar("nowtime", paramMap.getStrParam("nowtime"));
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long HosHosSchedulingCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String type = paramMap.getStrParam("type");
		sqlStr.append(" select 1 from hos_scheduling  where 1=1 ");
		if ("0".equals(type)) {// 当前排班
			sqlStr.append(" and operationNumber > :nowtime");
		}
		if ("1".equals(type)) {// 历史排班
			sqlStr.append(" and operationNumber < :nowtime");
		}
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like :name");
		}
		if (paramMap.getStrParam("operationNumber") != null) {
			sqlStr.append(" and operationNumber like :operationNumber");
		}

		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("operationNumber") != null) {
			sql.setVarChar("operationNumber", paramMap.getStrParam("operationNumber"));
		}
		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		sql.setVarChar("nowtime", paramMap.getStrParam("nowtime"));
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	public int updateSchThNumberByNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_scheduling ");
		sqlStr.append(" set ");
		sqlStr.append(" thNumber ");
		sqlStr.append(" = ");
		sqlStr.append(" :newNumber ");
		sqlStr.append(" where ");
		sqlStr.append(" thNumber ");
		sqlStr.append(" = ");
		sqlStr.append(" :oldNumber ");
		sql = SQL.getSQL(sqlStr);

		sql.setVarChar("oldNumber", oldNumber);
		sql.setVarChar("newNumber", newNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectSchedulByOperationTime() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	operationNumber      ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	hos_scheduling       ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	operationtime > NOW()");
		sql = SQL.getSQL(sqlStr);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public List<Map<String, Object>> selectIdByOperationNumber(String operationNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	id      ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	hos_scheduling       ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	operationNumber=:operationNumber");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operationNumber", operationNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public HosScheduling selectHosSchedulingById(String id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	date_format(hs.operationtime,'%Y-%m-%d %H:%i:%s' ) AS operationtime  ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	hos_scheduling   hs    ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	id=:id");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosScheduling.class);
	}

	@Override
	public List<HosScheduling> queryHosSchedulingByTime(String begintime, String endtime) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                    ");
		sqlStr.append(" 	hs.*                                  ");
		sqlStr.append(" FROM                                      ");
		sqlStr.append(" 	hos_scheduling hs                     ");
		sqlStr.append(" WHERE                                     ");
		sqlStr.append(" 	1 = 1                                 ");
		sqlStr.append(" AND hs.operationtime BETWEEN :begintime  ");
		sqlStr.append(" AND :endtime                              ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("begintime", begintime);
		sql.setVarChar("endtime", endtime);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosScheduling.class);
	}

	@Override
	public List<Map<String, Object>> selectUserNameByUserId(String userId, String scheduleId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                   ");
		sqlStr.append(" 	aud.userName                         ");
		sqlStr.append(" FROM                                     ");
		sqlStr.append(" 	auth_user au,                        ");
		sqlStr.append(" 	auth_user_detail aud,                ");
		sqlStr.append(" 	hos_scheduling_persons hsp           ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	1 = 1                                ");
		sqlStr.append(" AND au.id = aud.userId                   ");
		sqlStr.append(" AND au.id = hsp.userid                   ");
		sqlStr.append(" AND hsp.operschedulid = :operschedulid   ");
		sqlStr.append(" AND hsp.userid = :userid                 ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operschedulid", scheduleId);
		sql.setVarChar("userid", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public List<Map<String, Object>> showUserFromSch(String scheduleId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                 ");
		sqlStr.append(" 	au.jobnumber,                      ");
		sqlStr.append(" 	aud.userName                       ");
		sqlStr.append(" FROM                                   ");
		sqlStr.append(" 	auth_user au,                      ");
		sqlStr.append(" 	auth_user_detail aud,              ");
		sqlStr.append(" 	hos_scheduling_persons hsp         ");
		sqlStr.append(" WHERE                                  ");
		sqlStr.append(" 	1 = 1                              ");
		sqlStr.append(" AND au.id = aud.userId                 ");
		sqlStr.append(" AND au.id = hsp.userid                 ");
		sqlStr.append(" AND hsp.operschedulid = :operschedulid ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operschedulid", scheduleId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long showUserFromSchCount(String scheduleId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                 ");
		sqlStr.append(" 	1                                  ");
		sqlStr.append(" FROM                                   ");
		sqlStr.append(" 	auth_user au,                      ");
		sqlStr.append(" 	auth_user_detail aud,              ");
		sqlStr.append(" 	hos_scheduling_persons hsp         ");
		sqlStr.append(" WHERE                                  ");
		sqlStr.append(" 	1 = 1                              ");
		sqlStr.append(" AND au.id = aud.userId                 ");
		sqlStr.append(" AND au.id = hsp.userid                 ");
		sqlStr.append(" AND hsp.operschedulid = :operschedulid ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operschedulid", scheduleId);
		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();

		return count.longValue();
	}
}
