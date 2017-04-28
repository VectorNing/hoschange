package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.HisDataFromOracleDao;

@Repository
public class HisDataFromOracleDaoImpl extends SesframeBaseDao implements HisDataFromOracleDao {

	@Override
	public List<Map<String, Object>> getAllPersonData() throws BaseException {
		SQL sql;
		sql = SQL.getSQL("select * from sesxh.emp");
		return this.getSession("oracledatabase").getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public List<Map<String, Object>> getOperationSchedule() throws BaseException {
		SQL sql;
		sql = SQL.getSQL(
				"select s.THNUMBER,s.OPERATIONNAME,s.OPERATIONNUMBER,s.PATIENTID,to_char(s.OPERATIONTIME,'yyyymmddhh24miss') OPERATIONTIME from sesxh.schedule s");
		return this.getSession("oracledatabase").getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public List<Map<String, Object>> getOperationSchedulePerson(String operationNumber) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("select employess from sesxh.person where operationnumber=:operationnumber");
		sql.setVarChar("operationnumber", operationNumber);
		return this.getSession("oracledatabase").getSQLExecutor().setSQL(sql).executeQuery();
	}

}
