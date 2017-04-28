package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.ILogErrorDao;
import com.sesxh.hoschange.biz.sys.entity.LogError;
import com.sesxh.hoschange.common.data.ParamMap;

@Repository
public class LogErrorDaoImpl extends SesframeBaseDao implements ILogErrorDao {

	public LogError selectLogError(Integer id) throws BaseException {
		SQL sql;
		String sqlStr = " select * from log_error where 1=1 and id=:id";
		sql = SQL.getSQL(sqlStr);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(LogError.class);
	}

	public void insertLogError(LogError logError) throws BaseException {
		
	}

	public void deletLogError(Long id) throws BaseException {
		
	}

	public Long selectLogErrCount(ParamMap paramMap) throws BaseException{
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select * from log_error where 1=1 ");
		if (paramMap.getStrParam("") != null) {
			
		}
		sqlStr.append(" limit :start ,:size ");

		SQL sql = SQL.getSQL(sqlStr);
		if (paramMap.getStrParam("") != null) {
			
		}
		SQLExecutor SQLExecutor=this.getSession().getSQLExecutor().setSQL(sql);
		Integer count =SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<Map<String, Object>> selectLogErrLists(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		int start = paramMap.getStart();
		int size = paramMap.getSize();

		sqlStr.append("select * from log_error where 1=1 ");
		if (paramMap.getStrParam("") != null) {

		}
		sqlStr.append(" limit :start ,:size ");

		sql = SQL.getSQL(sqlStr);
		if (paramMap.getStrParam("") != null) {

		}
		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}
}
