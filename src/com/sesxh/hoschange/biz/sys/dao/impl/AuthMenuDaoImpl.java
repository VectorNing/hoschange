package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.AuthMenuDao;
import com.sesxh.hoschange.biz.sys.entity.AuthMenu;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class AuthMenuDaoImpl extends SesframeBaseDao implements AuthMenuDao {


	@Override
	public List<AuthMenu> selectAllAuthMenuList() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		
		sqlStr.append(" SELECT             ");
		sqlStr.append(" 	a.id,          ");
		sqlStr.append(" 	a.parentId,    ");
		sqlStr.append(" 	a.`name`,      ");
		sqlStr.append(" 	a.`code`,      ");
		sqlStr.append(" 	a.url          ");
		sqlStr.append(" FROM               ");
		sqlStr.append(" 	auth_menu a    ");
		sqlStr.append(" WHERE              ");
		sqlStr.append(" 	1 = 1          ");
		sqlStr.append(" ORDER BY           ");
		sqlStr.append(" 	a.ordercol ASC ");
		
		sql = SQL.getSQL(sqlStr);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(AuthMenu.class);
	}

	@Override
	public List<AuthMenu> selectAuthMenuListByUserId(Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		
		sqlStr.append(" SELECT                             ");
		sqlStr.append(" 	am.id,                         ");
		sqlStr.append(" 	am.parentId,                   ");
		sqlStr.append(" 	am.`name`,                     ");
		sqlStr.append(" 	am.`code`,                     ");
		sqlStr.append(" 	am.url                         ");
		sqlStr.append(" FROM                               ");
		sqlStr.append(" 	auth_menu am                   ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	1 = 1                          ");
		sqlStr.append(" AND am.permId IN (                 ");
		sqlStr.append(" 	SELECT                         ");
		sqlStr.append(" 		ap.id                      ");
		sqlStr.append(" 	FROM                           ");
		sqlStr.append(" 		auth_role_user aru,        ");
		sqlStr.append(" 		auth_role ar,              ");
		sqlStr.append(" 		auth_role_permission arp,  ");
		sqlStr.append(" 		auth_permission ap         ");
		sqlStr.append(" 	WHERE                          ");
		sqlStr.append(" 		1 = 1                      ");
		sqlStr.append(" 	AND aru.roleId = ar.id         ");
		sqlStr.append(" 	AND ar.id = arp.roleId         ");
		sqlStr.append(" 	AND arp.permissionId = ap.id   ");
		sqlStr.append(" 	AND ap.enabled = :enabled      ");
		sqlStr.append(" 	AND aru.userId = :userId       ");
		sqlStr.append(" )                                  ");
		sqlStr.append(" ORDER BY                           ");
		sqlStr.append(" 	am.ordercol                    ");
		
		
		sql = SQL.getSQL(sqlStr);
		
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(AuthMenu.class);
	}

	@Override
	public AuthMenu selectAuthMenuListByParentId(Integer ParentId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		
		sqlStr.append(" SELECT                ");
		sqlStr.append(" 	am.id,            ");
		sqlStr.append(" 	am.parentId,      ");
		sqlStr.append(" 	am.`name`,        ");
		sqlStr.append(" 	am.`code`,        ");
		sqlStr.append(" 	am.url            ");
		sqlStr.append(" FROM                  ");
		sqlStr.append(" 	auth_menu am      ");
		sqlStr.append(" WHERE                 ");
		sqlStr.append(" 	1 = 1             ");
		sqlStr.append(" AND am.id = :ParentId ");
		
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("ParentId", ParentId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthMenu.class);
	}

	/*@Override
	public List<AuthMenu> selectAuthMenuListByUserId(Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		
		sqlStr.append("SELECT                        ");
		sqlStr.append("am.id,                        ");
		sqlStr.append("am.parentId,                  ");
		sqlStr.append("am.`name`,                    ");
		sqlStr.append("am.`code`,                    ");
		sqlStr.append("am.url                        ");
		sqlStr.append("FROM                          ");
		sqlStr.append("	auth_role_permission arp,    ");
		sqlStr.append("	auth_permission ap,          ");
		sqlStr.append("	auth_menu am                 ");
		sqlStr.append("WHERE                         ");
		sqlStr.append("	1 = 1                        ");
		sqlStr.append("AND arp.permissionId = ap.id  ");
		sqlStr.append("AND am.permId = ap.id         ");
		sqlStr.append("AND arp.roleId IN (           ");
		sqlStr.append("	SELECT                       ");
		sqlStr.append("		aru.roleId               ");
		sqlStr.append("	FROM                         ");
		sqlStr.append("		auth_role_user aru       ");
		sqlStr.append("	WHERE                        ");
		sqlStr.append("		1 = 1                    ");
		sqlStr.append("	AND aru.userId = :userId     ");
		sqlStr.append(")                             ");
		sqlStr.append("ORDER BY am.ordercol ASC      "); 
		
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(AuthMenu.class);
	}
*/


	
}
