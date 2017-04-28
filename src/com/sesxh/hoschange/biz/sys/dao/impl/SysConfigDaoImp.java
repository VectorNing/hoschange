package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.SysConfigDao;
import com.sesxh.hoschange.biz.sys.entity.SysConfig;
import com.sesxh.hoschange.common.util.PageModel;

@Repository
public class SysConfigDaoImp extends SesframeBaseDao implements SysConfigDao {

	@Override
	public List<Map<String, Object>> querySysConfig(PageModel page) throws BaseException {

		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                     ");
		sqlStr.append(" 	sc.*, CASE                             ");
		sqlStr.append(" WHEN config = '1'                          ");
		sqlStr.append(" AND configName = 'isOrNoRoster' THEN       ");
		sqlStr.append(" 	'开启'                                 ");
		sqlStr.append(" WHEN config = '0'                          ");
		sqlStr.append(" AND configName = 'isOrNoRoster' THEN       ");
		sqlStr.append(" 	'关闭'                                 ");
		sqlStr.append(" WHEN config = '1'                          ");
		sqlStr.append(" AND configName = 'yesOrNoHaveShoeBox' THEN ");
		sqlStr.append(" 	'有'                                   ");
		sqlStr.append(" WHEN config = '0'                          ");
		sqlStr.append(" AND configName = 'yesOrNoHaveShoeBox' THEN ");
		sqlStr.append(" 	'无'                                   ");
		sqlStr.append(" WHEN config = '1'                          ");
		sqlStr.append(" AND configName = 'isOrNotRecycled' THEN    ");
		sqlStr.append(" 	'可回收'                               ");
		sqlStr.append(" WHEN config = '0'                          ");
		sqlStr.append(" AND configName = 'isOrNotRecycled' THEN    ");
		sqlStr.append(" 	'不可回收'                             ");
		sqlStr.append(" ELSE                                       ");
		sqlStr.append(" 	CONCAT(config, '秒')                   ");
		sqlStr.append(" END AS nowConfig                           ");
		sqlStr.append(" FROM                                       ");
		sqlStr.append(" 	sys_config sc                          ");
		sqlStr.append(" WHERE                                      ");
		sqlStr.append(" 	1 = 1                                  ");
		sqlStr.append(" LIMIT :start,                              ");
		sqlStr.append("  :rows                                     ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("start", page.getStartSize());
		sql.setInteger("rows", page.getRows());

		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int querySysConfigCount() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT             ");
		sqlStr.append(" 	1              ");
		sqlStr.append(" FROM               ");
		sqlStr.append(" 	sys_config sc  ");
		sqlStr.append(" WHERE              ");
		sqlStr.append(" 	1 = 1          ");

		sql = SQL.getSQL(sqlStr);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
	}

	@Override
	public SysConfig querySysConfigById(Integer id) throws BaseException {

		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                     ");
		sqlStr.append(" 	sc.*, CASE                             ");
		sqlStr.append(" WHEN config = '1'                          ");
		sqlStr.append(" AND configName = 'isOrNoRoster' THEN       ");
		sqlStr.append(" 	'开启'                                 ");
		sqlStr.append(" WHEN config = '0'                          ");
		sqlStr.append(" AND configName = 'isOrNoRoster' THEN       ");
		sqlStr.append(" 	'关闭'                                 ");
		sqlStr.append(" WHEN config = '1'                          ");
		sqlStr.append(" AND configName = 'yesOrNoHaveShoeBox' THEN ");
		sqlStr.append(" 	'有'                                   ");
		sqlStr.append(" WHEN config = '0'                          ");
		sqlStr.append(" AND configName = 'yesOrNoHaveShoeBox' THEN ");
		sqlStr.append(" 	'无'                                   ");
		sqlStr.append(" WHEN config = '1'                          ");
		sqlStr.append(" AND configName = 'isOrNotRecycled' THEN    ");
		sqlStr.append(" 	'可回收'                               ");
		sqlStr.append(" WHEN config = '0'                          ");
		sqlStr.append(" AND configName = 'isOrNotRecycled' THEN    ");
		sqlStr.append(" 	'不可回收'                             ");
		sqlStr.append(" ELSE                                       ");
		sqlStr.append(" 	CONCAT(config, '秒')                   ");
		sqlStr.append(" END AS nowConfig                           ");
		sqlStr.append(" FROM                                       ");
		sqlStr.append(" 	sys_config sc                          ");
		sqlStr.append(" WHERE                                      ");
		sqlStr.append(" 	1 = 1                                  ");
		sqlStr.append(" AND id = :id                                ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(SysConfig.class);
	}

	@Override
	public SysConfig querySysConfigByConfigName(String configName) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                         ");
		sqlStr.append(" 	sc.*                       ");
		sqlStr.append(" FROM                           ");
		sqlStr.append(" 	sys_config sc              ");
		sqlStr.append(" WHERE                          ");
		sqlStr.append(" 	1 = 1                      ");
		sqlStr.append(" AND sc.configName =:configName ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("configName", configName);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(SysConfig.class);
	}

	@Override
	public Integer updateSysConfig(SysConfig config) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE sys_config sc    ");
		sqlStr.append(" SET sc.config = :config ");
		sqlStr.append(" WHERE                   ");
		sqlStr.append(" 	sc.id = :id         ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("config", config.getConfig());
		sql.setInteger("id", config.getId());
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

}
