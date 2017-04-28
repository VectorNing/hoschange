package com.sesxh.hoschange.biz.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.sys.dao.AuthUserDao;
import com.sesxh.hoschange.biz.sys.entity.AuthUser;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst.ENABLED;

@Repository
public class AuthUserDaoImpl extends SesframeBaseDao implements AuthUserDao {

	public List<Map<String, Object>> selectSaltByLoginName(String loginName) throws BaseException {
		String sqlStr = " select salt from auth_user a where 1=1 and a.loginName = :loginName ";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("loginName", loginName);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public AuthUser selectByLoginNameAndPas(String loginName, String password) throws BaseException {
		String sqlStr = " select a.* from auth_user a where 1=1 and a.loginName = :loginName and password = :password";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("loginName", loginName);
		sql.setVarChar("password", password);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	public AuthUser findByLoginName(String loginName) throws BaseException {
		String sqlStr = " select a.* from auth_user a where 1=1 and a.loginName = :loginName";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("loginName", loginName);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	public AuthUser findByLoginName2(String loginName2) throws BaseException {
		String sqlStr = " select a.* from auth_user a where 1=1 and a.loginName2 = :loginName2";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("loginName2", loginName2);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	public AuthUser findBySfzh(String sfzh) throws BaseException {
		String sqlStr = " select a.* from auth_user a where 1=1 and a.sfzh = :sfzh";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("sfzh", sfzh);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	public AuthUser selectByPrimaryKey(Integer id) throws BaseException {
		String sqlStr = " select a.* from auth_user a where 1=1 and a.id = :id ";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	public Long selectCountByLoginName(String loginName) throws BaseException {
		SQL sql = SQL.getSQL("select * from auth_user where 1=1 and loginName = :loginName");
		sql.setVarChar("loginName", loginName);
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public int insertAuthUser(AuthUser authUser) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" auth_user( ");
		sqlStr.append(" loginName, ");
		sqlStr.append(" loginName2, ");
		sqlStr.append(" password, ");
		sqlStr.append(" salt, ");
		sqlStr.append(" description, ");
		if (authUser.getJobnumber() != null) {
			sqlStr.append(" jobnumber, ");
		}
		sqlStr.append(" defaultRole) ");
		sqlStr.append(" values( ");
		sqlStr.append(" :loginName, ");
		sqlStr.append(" :loginName2, ");
		sqlStr.append(" :password, ");
		sqlStr.append(" :salt, ");
		sqlStr.append(" :description, ");
		if (authUser.getJobnumber() != null) {
			sqlStr.append(" :jobnumber, ");
		}
		sqlStr.append(" :defaultRole) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(authUser);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateAuthUser(AuthUser authUser) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" auth_user ");
		sqlStr.append(" set ");
		sqlStr.append(" loginName = :loginName ,");
		sqlStr.append(" loginName2 = :loginName2 ");
		sqlStr.append(" where ");
		sqlStr.append(" id = :id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(authUser);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int setAuthAuthUserStatus(Integer id, String enabled) throws BaseException {
		SQL sql;
		sql = SQL.getSQL("update auth_user set enabled =:enabled where id = :id ");
		sql.setVarChar("enabled", enabled);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectAuthUserList(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		int start = paramMap.getStart();
		int size = paramMap.getSize();
		Integer type = paramMap.getIntegerParam("type");

		sqlStr.append("SELECT                                          ");
		sqlStr.append("	u.id,                                          ");
		sqlStr.append("	u.loginName2,                                  ");
		sqlStr.append("	u.jobnumber,                                   ");
		sqlStr.append("	u.enabled,                                     ");
		sqlStr.append("	u.accountExpired,                              ");
		sqlStr.append("	u.accountLocked,                               ");
		sqlStr.append("	u.credentialsExpired,                          ");
		sqlStr.append("	u.description,                                 ");
		sqlStr.append("	d.userName,                                    ");
		sqlStr.append("	d.telephone,                                   ");
		sqlStr.append("	d.num,                                         ");
		sqlStr.append("	d.department,                                  ");
		sqlStr.append("	d.position,                                    ");
		sqlStr.append("	(                                              ");
		sqlStr.append("		SELECT                                     ");
		sqlStr.append("			NAME                                   ");
		sqlStr.append("		FROM                                       ");
		sqlStr.append("			dictionary                             ");
		sqlStr.append("		WHERE                                      ");
		sqlStr.append("			type = 'shoes'                         ");
		sqlStr.append("		AND CODE = d.shoesSize                     ");
		sqlStr.append("	) AS shoesSize,                                ");
		sqlStr.append("	(                                              ");
		sqlStr.append("		SELECT                                     ");
		sqlStr.append("			NAME                                   ");
		sqlStr.append("		FROM                                       ");
		sqlStr.append("			dictionary                             ");
		sqlStr.append("		WHERE                                      ");
		sqlStr.append("			type = 'xb'                            ");
		sqlStr.append("		AND CODE = d.sex                           ");
		sqlStr.append("	) AS sex,                                      ");
		sqlStr.append("	(                                              ");
		sqlStr.append("		SELECT                                     ");
		sqlStr.append("			NAME                                   ");
		sqlStr.append("		FROM                                       ");
		sqlStr.append("			dictionary                             ");
		sqlStr.append("		WHERE                                      ");
		sqlStr.append("			type = 'cloth'                         ");
		sqlStr.append("		AND CODE = d.clothesSize                   ");
		sqlStr.append("	) AS clothesSize,                              ");
		sqlStr.append("	d.sfzh,                                        ");
		sqlStr.append("	d.note                                         ");
		sqlStr.append("FROM                                            ");
		sqlStr.append("	auth_user u                                    ");
		sqlStr.append("LEFT JOIN auth_user_detail d ON u.id = d.userId ");
		sqlStr.append("WHERE                                           ");
		sqlStr.append("	1 = 1                                          ");
		sqlStr.append("AND u.sfcjgly != 1                              ");

		if (paramMap.getStrParam("userName") != null) {
			sqlStr.append(" and d.userName like :userName");
		}
		if (type != null) {
			if (1 == paramMap.getIntegerParam("type")) {
				sqlStr.append(" AND (d.shoesSize IS NOT NULL AND d.clothesSize IS NOT NULL)");
			}
			if (0 == paramMap.getIntegerParam("type")) {
				sqlStr.append(" AND(d.shoesSize IS  NULL OR d.clothesSize IS  NULL)");
			}
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

		if (paramMap.getStrParam("userName") != null) {
			sql.setVarChar("userName", paramMap.getStrParam("userName"));
		}
		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return lists;

	}

	@Override
	public Long selectAuthUserCount(ParamMap paramMap) throws BaseException {

		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select 1 from auth_user u, auth_user_detail d where 1=1 AND u.id=d.userId AND u.sfcjgly !=1 ");
		Integer type = paramMap.getIntegerParam("type");
		if (type != null) {
			if (1 == paramMap.getIntegerParam("type")) {
				sqlStr.append(" AND (d.shoesSize IS NOT NULL AND d.clothesSize IS NOT NULL)");
			}
			if (0 == paramMap.getIntegerParam("type")) {
				sqlStr.append(" AND(d.shoesSize IS  NULL OR d.clothesSize IS  NULL)");
			}
		}
		if (paramMap.getStrParam("loginName") != null) {
			sqlStr.append(" and u.loginName like:loginName");
		}
		String enabled = paramMap.getStrParam("enabled");
		if (enabled != null) {
			sqlStr.append(" and u.enabled = :enabled");
		}

		sql = SQL.getSQL(sqlStr);
		if (paramMap.getStrParam("loginName") != null) {
			sql.setVarChar("loginName", paramMap.getStrParam("loginName"));
		}
		if (enabled != null) {
			sql.setVarChar("enabled", paramMap.getStrParam("enabled"));
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public int assignRoleToUser(Integer id, Integer roleId) throws BaseException {
		SQL sql = SQL.getSQL("insert into auth_role_user(userId,roleId) values(:userId,:roleId)");
		sql.setInteger("userId", id);
		sql.setInteger("roleId", roleId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int removeRoleFromUser(Integer id, Integer roleId) throws BaseException {
		SQL sql = SQL.getSQL("delete from auth_role_user where userId=:userId and roleId=:roleId ");
		sql.setInteger("userId", id);
		sql.setInteger("roleId", roleId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public AuthUserDetail selectAuthUserDetailByUserId(Integer userId) throws BaseException {
		String sqlStr = "select * from  auth_user_detail where userId=:userId";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUserDetail.class);
	}

	public int insertAuthUserDetail(AuthUserDetail authUserDetail) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" auth_user_detail( ");
		sqlStr.append(" userName, ");
		sqlStr.append(" userId, ");
		sqlStr.append(" sfzh, ");
		sqlStr.append(" sex, ");
		sqlStr.append(" telephone, ");
		sqlStr.append(" num, ");
		sqlStr.append(" shoesSize, ");
		sqlStr.append(" clothesSize, ");
		sqlStr.append(" personnelType, ");
		sqlStr.append(" department, ");
		sqlStr.append(" position, ");
		sqlStr.append(" note) ");
		sqlStr.append(" values( ");
		sqlStr.append(" :userName, ");
		sqlStr.append(" :userId, ");
		sqlStr.append(" :sfzh, ");
		sqlStr.append(" :sex, ");
		sqlStr.append(" :telephone, ");
		sqlStr.append(" :num, ");
		sqlStr.append(" :shoesSize, ");
		sqlStr.append(" :clothesSize, ");
		sqlStr.append(" :personnelType, ");
		sqlStr.append(" :department, ");
		sqlStr.append(" :position, ");
		sqlStr.append(" :note) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(authUserDetail);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public Long selectUserByLoginName(String loginName, Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" auth_user ");
		sqlStr.append(" where ");
		sqlStr.append(" loginName=:loginName ");

		if (id != null) {
			sqlStr.append(" and ");
			sqlStr.append(" id!=:id ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("loginName", loginName);
		if (id != null) {
			sql.setInteger("id", id);
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public Long selectUserByLoginName2(String loginName2, Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" auth_user ");
		sqlStr.append(" where ");
		sqlStr.append(" loginName2=:loginName2 ");

		if (id != null) {
			sqlStr.append(" and ");
			sqlStr.append(" id!=:id ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("loginName2", loginName2);
		if (id != null) {
			sql.setInteger("id", id);
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public AuthUserDetail selectUserDetailByAuthUserId(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" u.loginName, ");
		sqlStr.append(" u.password, ");
		sqlStr.append(" u.loginName2, ");
		sqlStr.append(" d.* ");
		sqlStr.append(" from ");
		sqlStr.append(" auth_user u ");
		sqlStr.append(" left join ");
		sqlStr.append(" auth_user_detail d ");
		sqlStr.append(" on u.id = d.userId ");
		sqlStr.append(" where ");
		sqlStr.append(" u.id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUserDetail.class);
	}

	public int updateAuthUserDetailByUserId(AuthUserDetail authUserDetail) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" auth_user_detail ");
		sqlStr.append(" set ");
		sqlStr.append(" userName=:userName, ");
		sqlStr.append(" sfzh=:sfzh, ");
		sqlStr.append(" sex=:sex, ");
		sqlStr.append(" telephone=:telephone, ");
		sqlStr.append(" num=:num, ");
		sqlStr.append(" shoesSize=:shoesSize, ");
		sqlStr.append(" clothesSize=:clothesSize, ");
		sqlStr.append(" note=:note, ");
		sqlStr.append(" personnelType=:personnelType, ");
		sqlStr.append(" department=:department, ");
		sqlStr.append(" position=:position ");
		sqlStr.append(" where ");
		sqlStr.append(" userId=:userId ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(authUserDetail);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int changePasswordSalt(AuthUser user) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" auth_user ");
		sqlStr.append(" set ");
		sqlStr.append(" salt=:salt, ");
		sqlStr.append(" password=:password ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(user);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public AuthUser selectAuthUserByUserId(Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT         ");
		sqlStr.append(" 	*          ");
		sqlStr.append(" FROM           ");
		sqlStr.append(" 	auth_user  ");
		sqlStr.append(" WHERE          ");
		sqlStr.append(" 	id = :id   ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	@Override
	public List<Map<String, Object>> selectAuthUserJobNumber() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	jobnumber            ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	auth_user            ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	1 = 1                ");
		sqlStr.append("AND enabled = :enabled");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("enabled", ENABLED.ENABLE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int updateAuthUserDisableByJobNumber(String jobnumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" auth_user ");
		sqlStr.append(" set ");
		sqlStr.append(" enabled=:enabled ");
		sqlStr.append(" where ");
		sqlStr.append(" jobnumber = :jobnumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("enabled", ENABLED.DISABLE);
		sql.setVarChar("jobnumber", jobnumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public AuthUser selectIdByJobNumber(String jobnumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                ");
		sqlStr.append("	id           ");
		sqlStr.append("FROM                  ");
		sqlStr.append("	auth_user            ");
		sqlStr.append("WHERE                 ");
		sqlStr.append("	1 = 1                ");
		sqlStr.append("AND jobnumber = :jobnumber");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("jobnumber", jobnumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUser.class);
	}

	@Override
	public List<AuthUserDetail> selectUserDetailByAuthUserName(String userName) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                                ");
		sqlStr.append("	CONCAT(                              ");
		sqlStr.append("		aud.userName,                    ");
		sqlStr.append("		'(工号：',                       ");
		sqlStr.append("		IFNULL(au.jobnumber,'无工号'),   ");
		sqlStr.append("')'                                   ");
		sqlStr.append("	                                     ");
		sqlStr.append("	) AS userName,                       ");
		sqlStr.append("	au.id                                ");
		sqlStr.append("FROM                                  ");
		sqlStr.append("	auth_user au,                        ");
		sqlStr.append("	auth_user_detail aud                 ");
		sqlStr.append("WHERE                                 ");
		sqlStr.append("	1 = 1                                ");
		sqlStr.append("and au.id = aud.userId                ");
		sql = SQL.getSQL(sqlStr);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(AuthUserDetail.class);
	}

	@Override
	public List<AuthUserDetail> selectJobnumber(String jobnumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT a.jobnumber from auth_user a where a.jobnumber = :jobnumber");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("jobnumber", jobnumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(AuthUserDetail.class);

	}

	@Override
	public List<Map<String, Object>> selectclothesSize(String cType) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select a.code from dictionary a where a.type ='cloth' and a.name=:cType");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("cType", cType);
		List<Map<String, Object>> list = getSession().getSQLExecutor().setSQL(sql).executeQuery();
		return list;
	}

	@Override
	public List<Map<String, Object>> selectShoesSize(String sType) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select a.code from dictionary a where a.type ='shoes' and a.name = :sType");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("sType", sType);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

	}

	@Override
	public int updateUserInfo(String jobnumber, String department, String userName, String sex, String clothesType,
			String shoesType) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE auth_user_detail b                               ");
		sqlStr.append(" SET b.department = :department,                        ");
		sqlStr.append("  b.userName =:userName,                                ");
		sqlStr.append("  b.sex = :sex,                                         ");
		sqlStr.append("  b.clothesSize = :clothesType,                         ");
		sqlStr.append("  b.shoesSize =:shoesType where b.userId in(SELECT      ");
		sqlStr.append(" 	a.id                                                ");
		sqlStr.append(" FROM                                                    ");
		sqlStr.append(" 	auth_user a                                         ");
		sqlStr.append(" WHERE                                                   ");
		sqlStr.append(" 	a.jobnumber = :jobnumber)                          ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("department", department);
		sql.setVarChar("userName", userName);
		sql.setVarChar("sex", sex);
		sql.setVarChar("clothesType", clothesType);
		sql.setVarChar("shoesType", shoesType);
		sql.setVarChar("jobnumber", jobnumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();

	}

	@Override
	public int insertUserInfo(String jobnumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("  INSERT INTO hoschange.auth_user ( jobnumber )  VALUES  (:jobnumber)       ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("jobnumber", jobnumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectIdByJobnumber(String jobnumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select a.id from auth_user a where a.jobnumber = :jobnumber");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("jobnumber", jobnumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

	}

	@Override
	public int insertAuthUserDetailInfo(String userId, String department, String userName, String sex,
			String clothesType, String shoesType) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" INSERT INTO hoschange.auth_user_detail (                          ");
		sqlStr.append(" 	userName,userId,sex,shoesSize,             ");
		sqlStr.append(" 	clothesSize,department    ");
		sqlStr.append(" )                                                                 ");
		sqlStr.append(" VALUES                                                            ");
		sqlStr.append(" 	(                                                             ");
		sqlStr.append(" 		:userName,:userId,:sex,                     ");
		sqlStr.append(" 		:shoesSize,:clothesSize,            ");
		sqlStr.append(" 	  :department                                          ");
		sqlStr.append(" 	)                                                            ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("userName", userName);
		sql.setVarChar("userId", userId);
		sql.setVarChar("sex", sex);
		sql.setVarChar("shoesSize", shoesType);
		sql.setVarChar("clothesSize", clothesType);
		sql.setVarChar("department", department);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
}
