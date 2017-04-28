package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.AuthRoleDao;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class AuthRoleDaoImpl extends SesframeBaseDao implements AuthRoleDao {

	@Override
	public AuthRole selectAuthRoleById(Integer roleId) throws BaseException {
		SQL sql = SQL.getSQL("select * from auth_role where id =:roleId ");// and
																			// enabled
																			// =
																			// "+BizConst.ENABLED.ENABLE
		sql.setInteger("roleId", roleId.intValue());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthRole.class);
	}

	@Override
	public List<Map<String, Object>> selectAuthRoleList(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		paramMap.getOrder();
		paramMap.getSort();
		int start = paramMap.getStart();
		int size = paramMap.getSize();
		sqlStr.append(" select       ");
		sqlStr.append(" id, ");
		sqlStr.append(" name, ");
		sqlStr.append(" description, ");
		sqlStr.append(" enabled, ");
		sqlStr.append(" lqcs ");
		sqlStr.append(" from ");
		sqlStr.append(" auth_role ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like :name");
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
		sql.setInteger("start", start);
		sql.setInteger("size", size);

		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;
	}

	@Override
	public Long selectAuthRoleCount(ParamMap paramMap) throws BaseException {
		SQL sql = SQL.getSQL();
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" select 1 from auth_role  where 1=1 ");
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like:name");
		}
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public int updateAuthRole(AuthRole sysRole) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("update auth_role set name = :name,description = :description,"
				+ "enabled=:enabled,lqcs=:lqcs where id = :id ");
		sql.setParas(sysRole);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int insertAuthRole(AuthRole sysRole) throws BaseException {
		SQL sql;
		sql = SQL.getSQL(
				"insert into auth_role(name,description,enabled,lqcs) " + "values(:name,:description,:enabled,:lqcs)");
		sql.setParas(sysRole);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();

	}

	@Override
	public int setAuthRoleStatus(Integer roleId, String enabled) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("update auth_role set enabled =:enabled where id = :roleId ");
		sql.setVarChar("enabled", enabled);
		sql.setInteger("roleId", roleId.intValue());
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int assignPermToRole(Integer id, Integer PermId) throws BaseException {
		SQL sql = SQL.getSQL("insert into auth_role_permission(permissionId,roleId) values(:permissionId,:roleId)");
		sql.setInteger("roleId", id);
		sql.setInteger("permissionId", PermId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int removePermFromRole(Integer id, Integer PermId) throws BaseException {
		SQL sql = SQL.getSQL("delete from auth_role_permission where permissionId=:permissionId and roleId=:roleId ");
		sql.setInteger("roleId", id);
		sql.setInteger("permissionId", PermId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteAuthRole(Integer roleId) throws BaseException {
		SQL sql = SQL.getSQL("delete from auth_role where id=:roleId ");
		sql.setInteger("roleId", roleId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<Map<String, Object>> selectAuthRoleListByUserId(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		int start = paramMap.getStart();
		int size = paramMap.getSize();
		sqlStr.append(" select r.*  from auth_role r where 1=1 and r.enabled=" + BizConst.ENABLED.ENABLE);

		sqlStr.append(" and r.id in ( select ru.roleId from auth_role_user ru where ru.userId = :userId)");

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

		sql.setVarChar("userId", paramMap.getStrParam("userId"));

		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

		return lists;
	}

	public Long selectAuthRoleCountByUserId(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1  from auth_role r where 1=1 and r.enabled=" + BizConst.ENABLED.ENABLE);

		sqlStr.append(" and r.id in ( select ru.roleId from auth_role_user ru where ru.userId = :userId)");
		sql.putSql(sqlStr);
		sql.setVarChar("userId", paramMap.getStrParam("userId"));

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<Map<String, Object>> selectNotAuthRoleListByUserId(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		int start = paramMap.getStart();
		int size = paramMap.getSize();
		sqlStr.append(" select r.*  from auth_role r where 1=1 and r.enabled=" + BizConst.ENABLED.ENABLE);

		sqlStr.append(" and r.id not in ( select ru.roleId from auth_role_user ru where ru.userId = :userId)");

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

		sql.setVarChar("userId", paramMap.getStrParam("userId"));

		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

		return lists;
	}

	public Long selectNotAuthRoleCountByUserId(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1  from auth_role r where 1=1 and r.enabled=" + BizConst.ENABLED.ENABLE);

		sqlStr.append(" and r.id not in ( select ru.roleId from auth_role_user ru where ru.userId = :userId)");
		sql.putSql(sqlStr);
		sql.setVarChar("userId", paramMap.getStrParam("userId"));

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<ClassConvertDict> selectAuthRoleList() throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql;
		sqlStr.append(" select t.id AS code, t.name AS name from  auth_role t  ");
		sqlStr.append(" where t.enabled=:enabled");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(ClassConvertDict.class);
	}
}
