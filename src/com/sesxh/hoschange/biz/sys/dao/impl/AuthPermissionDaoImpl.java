package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.AuthPermissionDao;
import com.sesxh.hoschange.biz.sys.entity.AuthPermission;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class AuthPermissionDaoImpl extends SesframeBaseDao implements AuthPermissionDao {

	public List<Map<String, Object>> selectPermCodeListByUserId(Integer userId) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select p.permCode as code  from auth_permission p where 1=1 and p.enabled= "
				+ BizConst.ENABLED.ENABLE)
				.append(" and p.id in ( select rp.permissionId from auth_role_permission rp where 1=1 and rp.roleId in (")
				.append(" select ru.roleId from auth_role_user ru where 1=1 and ru.userId=:userId))");

		sql.putSql(sqlStr);
		sql.setInteger("userId", userId);

		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists == null ? Collections.emptyList() : lists;
	}

	public Long selectPermCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select * from auth_permission where 1=1 ");
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like :name");
		}
		if (paramMap.getStrParam("permCode") != null) {
			sqlStr.append(" and permCode like :permCode");
		}
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		if (paramMap.getStrParam("permCode") != null) {
			sql.setVarChar("permCode", paramMap.getStrParam("permCode"));
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count == null ? 0L : count.longValue();

	}

	public List<Map<String, Object>> selectPermList(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		int start = paramMap.getStart();
		int size = paramMap.getSize();

		sqlStr.append(" select * from auth_permission where 1=1 ");
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like :name");
		}
		if (paramMap.getStrParam("permCode") != null) {
			sqlStr.append(" and permCode like :permCode");
		}
		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		if (paramMap.getStrParam("permCode") != null) {
			sql.setVarChar("permCode", paramMap.getStrParam("permCode"));
		}
		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists == null ? Collections.emptyList() : lists;
	}

	public AuthPermission selectAuthPermById(Integer id) throws BaseException {
		String sqlStr = "select * from auth_permission where id=:id ";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthPermission.class);
	}

	public int insertAuthPerm(AuthPermission authPerm) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("insert into auth_permission(permCode,name,enabled,type,description) "
				+ " values(:permCode,:name,:enabled,:type,:description)");
		sql.setParas(authPerm);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateAuthPerm(AuthPermission authPerm) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("update auth_permission set permCode = :permCode,name = :name,enabled = :enabled,"
				+ " type=:type,description=:description where id = :id ");
		sql.setParas(authPerm);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int setAuthPermStatus(Integer id, String enabled) throws BaseException {
		SQL sql;
		sql = SQL.getSQL(" update auth_permission set enabled=:enabled where id = :id ");
		sql.setInteger("id", id);
		sql.setVarChar("enabled", enabled);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public AuthPermission selectAuthPermByCode(String permCode) throws BaseException {
		String sqlStr = "select * from auth_permission where permCode=:permCode and enabled=:enabled ";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("permCode", permCode);
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthPermission.class);
	}

	public List<Map<String, Object>> selectAuthPermListByRoleId(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		int start = paramMap.getStart();
		int size = paramMap.getSize();
		sqlStr.append(" select p.*  from auth_permission p where 1=1 and p.enabled=:enabled ");

		String name = paramMap.getStrParam("name");
		if (name != null) {
			sqlStr.append(" and p.name like :name");
		}
		sqlStr.append(" and p.id in ( select rp.permissionId from auth_role_permission rp where rp.roleId = :roleId)");

		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		sql.putSql(sqlStr);
		sql.setVarChar("roleId", paramMap.getStrParam("roleId"));

		if (name != null) {
			sql.setVarChar("name", name);
		}
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

		return lists;
	}

	public int updateStateByPermCode(String permCode, String enabled) throws BaseException {
		SQL sql;
		sql = SQL.getSQL(" update auth_permission set enabled=:enabled where permCode = :permCode ");
		sql.setVarChar("permCode", permCode);
		sql.setVarChar("enabled", enabled);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public Long selectAuthPermCountByRoleId(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1  from auth_permission p where 1=1 and p.enabled=:enabled ");

		String name = paramMap.getStrParam("name");
		if (name != null) {
			sqlStr.append(" and p.name like :name");
		}
		sqlStr.append(" and p.id in ( select rp.permissionId from auth_role_permission rp where rp.roleId = :roleId)");
		sql.putSql(sqlStr);
		sql.setVarChar("roleId", paramMap.getStrParam("roleId"));

		if (name != null) {
			sql.setVarChar("name", name);
		}
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<Map<String, Object>> selectPermListNotInPermRole(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		int start = paramMap.getStart();
		int size = paramMap.getSize();

		sqlStr.append(" select p.*  from auth_permission p where 1=1 and p.enabled=:enabled ");

		String name = paramMap.getStrParam("name");
		if (name != null) {
			sqlStr.append(" and p.name like :name");
		}
		sqlStr.append(
				" and p.id not in ( select rp.permissionId from auth_role_permission rp where rp.roleId = :roleId)");

		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");
		sql = SQL.getSQL(sqlStr);

		sql.putSql(sqlStr);
		sql.setVarChar("roleId", paramMap.getStrParam("roleId"));

		if (name != null) {
			sql.setVarChar("name", name);
		}
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);

		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

		return lists;
	}

	public Long selectPermCountNotInPermRole(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1  from auth_permission p where 1=1 and p.enabled=:enabled ");

		String name = paramMap.getStrParam("name");
		if (name != null) {
			sqlStr.append(" and p.name like :name");
		}
		sqlStr.append(
				" and p.id not in ( select rp.permissionId from auth_role_permission rp where rp.roleId = :roleId)");
		sql.putSql(sqlStr);
		sql.setVarChar("roleId", paramMap.getStrParam("roleId"));

		if (name != null) {
			sql.setVarChar("name", name);
		}
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectPermListByUser(ParamMap paramMap) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthPermission> selectPermListByUserId(Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                         ");
		sqlStr.append(" 	ap.*                       ");
		sqlStr.append(" FROM                           ");
		sqlStr.append(" 	auth_role_user aru,        ");
		sqlStr.append(" 	auth_role ar,              ");
		sqlStr.append(" 	auth_role_permission arp,  ");
		sqlStr.append(" 	auth_permission ap         ");
		sqlStr.append(" WHERE                          ");
		sqlStr.append(" 	1 = 1                      ");
		sqlStr.append(" AND aru.roleId = ar.id         ");
		sqlStr.append(" AND ar.id = arp.roleId         ");
		sqlStr.append(" AND arp.permissionId = ap.id   ");
		sqlStr.append(" AND ap.enabled = :enabled      ");
		sqlStr.append(" AND aru.userId = :userId       ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(AuthPermission.class);
	}
}
