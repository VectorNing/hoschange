package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.DictionaryDao;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class DictionaryDaoImpl extends SesframeBaseDao implements DictionaryDao {

	public List<Map<String,Object>> selectDictByCode(String type) throws BaseException {
		String enabled = BizConst.ENABLED.ENABLE;
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" d2.name, ");
		sqlStr.append(" d2.code ");
		sqlStr.append(" from ");
		sqlStr.append(" dictionary d1, ");
		sqlStr.append(" dictionary d2 ");
		sqlStr.append(" where ");
		sqlStr.append(" d1.id=d2.parentId ");
		sqlStr.append(" and ");
		sqlStr.append(" d1.type=:type ");
		sqlStr.append(" and ");
		sqlStr.append(" d2.enabled=:enabled ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("type", type);
		sql.setVarChar("enabled", enabled);
		List<Map<String,Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}
	
	public Dictionary selectDictByTypeAndCode(String type,String code) throws BaseException{
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" d2.* ");
		sqlStr.append(" from ");
		sqlStr.append(" dictionary d1, ");
		sqlStr.append(" dictionary d2 ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" d1.id=d2.parentId  ");
		sqlStr.append(" and ");
		sqlStr.append(" d1.type=:type ");
		sqlStr.append(" and ");
		sqlStr.append(" d2.code=:code ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("type", type);
		sql.setVarChar("code", code);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(Dictionary.class);
	}
}
