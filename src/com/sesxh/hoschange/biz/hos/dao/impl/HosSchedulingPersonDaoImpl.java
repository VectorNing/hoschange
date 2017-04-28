package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosSchedulingPersonDao;
import com.sesxh.hoschange.biz.hos.entity.HosSchedulingPerson;

@Repository
public class HosSchedulingPersonDaoImpl extends SesframeBaseDao implements HosSchedulingPersonDao {

	@Override
	public int insertHosSchedulingPerson(HosSchedulingPerson hosSchedulingPerson) throws BaseException {
		SQL sql;
		StringBuffer strSql = new StringBuffer();
		strSql.append(" insert ");
		strSql.append(" into ");
		strSql.append(" hos_scheduling_persons ");
		strSql.append(" ( ");
		strSql.append(" userid, ");
		strSql.append(" operschedulid, ");
		strSql.append(" synway ");
		strSql.append(" ) ");
		strSql.append(" values( ");
		strSql.append(" :userid, ");
		strSql.append(" :operschedulid, ");
		strSql.append(" :synway) ");
		sql = SQL.getSQL(strSql);
		sql.setParas(hosSchedulingPerson);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosSchedulingPersonByOperschedulid(String operschedulid) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_scheduling_persons where operschedulid=:operschedulid");
		sql.setVarChar("operschedulid", operschedulid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosSchedulingPersonByOperschedulid(String operschedulid, Integer userid) throws BaseException {
		SQL sql = SQL
				.getSQL("delete from hos_scheduling_persons where operschedulid=:operschedulid and userid=:userid");
		sql.setVarChar("operschedulid", operschedulid);
		sql.setInteger("userid", userid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosSchedulingPerson> selectHosSchedulingPersonByOperschedulid(String operschedulid)
			throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                 ");
		sqlStr.append("	userid                ");
		sqlStr.append("FROM                   ");
		sqlStr.append("	hos_scheduling_persons ");
		sqlStr.append("WHERE                  ");
		sqlStr.append("	1 = 1                 ");
		sqlStr.append("AND operschedulid =:operschedulid ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operschedulid", operschedulid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosSchedulingPerson.class);
	}

	@Override
	public int selectHosSchedulingPersonCount(String userid, String operschedulid) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                 ");
		sqlStr.append("	*                ");
		sqlStr.append("FROM                   ");
		sqlStr.append("	hos_scheduling_persons ");
		sqlStr.append("WHERE                  ");
		sqlStr.append("	1 = 1                 ");
		sqlStr.append("AND operschedulid =:operschedulid ");
		sqlStr.append("AND userid =:userid ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("operschedulid", operschedulid);
		sql.setVarChar("userid", userid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
	}

}
