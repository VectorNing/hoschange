package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosTheaterDao;
import com.sesxh.hoschange.biz.hos.entity.HosTheater;
import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosTheaterDaoImpl extends SesframeBaseDao implements HosTheaterDao {

	@Override
	public int insertHosTheater(HosTheater hosTheater) throws BaseException {
		SQL sql;
		sql = SQL.getSQL(
				"insert into hos_theater(name,number,state,description,address) values(:name,:number,:state,:description,:address)");
		sql.setParas(hosTheater);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosTheaterById(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_theater where id = :id");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosTheater(HosTheater hosTheater) throws BaseException {
		SQL sql;
		sql = SQL.getSQL(
				"update hos_theater set name = :name,number = :number,state = :state,description = :description,address = :address where id = :id ");
		sql.setParas(hosTheater);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public Long selectTheByNumber(String number, Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_theater ");
		sqlStr.append(" where ");
		sqlStr.append(" number=:number ");

		if (id != null) {
			sqlStr.append(" and ");
			sqlStr.append(" id!=:id ");
		}
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		if (id != null) {
			sql.setInteger("id", id);
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectAllHosTheater(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" select ");
		sqlStr.append(" t.id, ");
		sqlStr.append(" t.number, ");
		sqlStr.append(" t.name, ");
		sqlStr.append(" t.state, ");
		sqlStr.append(" t.description, ");

		sqlStr.append(" (select ");
		sqlStr.append(" sum(1) ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer s ");
		sqlStr.append(" where ");
		sqlStr.append(" s.theaterNumber=t.number ");
		sqlStr.append(" ) as steCount, ");
		sqlStr.append(" (select ");
		sqlStr.append(" sum(1) ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe w ");
		sqlStr.append(" where ");
		sqlStr.append(" w.theaterNumber=t.number ");
		sqlStr.append(" ) as warCount, ");
		sqlStr.append(" (select ");
		sqlStr.append(" sum(1) ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_clothespress c ");
		sqlStr.append(" where ");
		sqlStr.append(" c.theaterNumber=t.number ");
		sqlStr.append(" ) as clothCount, ");

		sqlStr.append(" t.address ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_theater t");
		sqlStr.append(" where 1=1 ");
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name like :name");
		}
		if (paramMap.getStrParam("address") != null) {
			sqlStr.append(" and address like :address");
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
		if (paramMap.getStrParam("address") != null) {
			sql.setVarChar("address", paramMap.getStrParam("address"));
		}
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long HosTheaterCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" select id,name,state,description,address from hos_theater  where 1=1 ");
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and name = :name");
		}
		if (paramMap.getStrParam("address") != null) {
			sqlStr.append(" and address = :address");
		}
		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		if (paramMap.getStrParam("address") != null) {
			sql.setVarChar("address", paramMap.getStrParam("address"));
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	public HosTheater selectTheaterById(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("select t.* from  hos_theater t where 1=1 and id = :id");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosTheater.class);
	}

	public HosTheater selectTheaterByNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("select t.* from  hos_theater t where 1=1 and number = :number");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosTheater.class);
	}

	public AuthUserDetail loadUserDetailById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                  ");
		sqlStr.append(" 	ud.userName,        ");
		sqlStr.append(" 	ud.sfzh,            ");
		sqlStr.append(" 	ud.sex,             ");
		sqlStr.append(" 	ud.userId,          ");
		sqlStr.append(" 	ud.telephone,       ");
		sqlStr.append(" 	ud.shoesSize,       ");
		sqlStr.append(" 	ud.clothesSize      ");
		sqlStr.append(" FROM                    ");
		sqlStr.append(" 	auth_user u,        ");
		sqlStr.append(" 	auth_user_detail ud ");
		sqlStr.append(" WHERE                   ");
		sqlStr.append(" 	1 = 1               ");
		sqlStr.append(" AND u.id = ud.userId    ");
		sqlStr.append(" AND u.id = :id          ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		AuthUserDetail user = this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(AuthUserDetail.class);
		return user;
	}

	public List<Map<String, Object>> selectOperationFromContainerBySize(Integer size, String number)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" c.trayNumber, ");
		sqlStr.append(" c.id, ");
		sqlStr.append(" c.opeSize, ");
		sqlStr.append(" w.theaterNumber, ");
		sqlStr.append(" c.warNumber ");

		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe_container c ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_wardrobe w ");
		sqlStr.append(" on c.warNumber=w.number ");
		sqlStr.append(" where 1=1 ");
		if (size != null) {
			sqlStr.append(" and ");
			sqlStr.append(" c.opeSize=:size ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" c.warNumber=:number ");
		sqlStr.append(" and ");
		sqlStr.append(" c.alloutCount>0 ");
		// sqlStr.append(" and ");
		// sqlStr.append(" w.state=:state ");

		sql = SQL.getSQL(sqlStr);
		if (size != null) {
			sql.setInteger("size", size);
		}
		// sql.setVarChar("state", BizConst.STATE.USE);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public List<Map<String, Object>> selectShoesFromContainerBySize(Integer size, String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" lockerNumber ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container s ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_sterilizer w ");
		sqlStr.append(" on s.steNumber=w.number ");
		sqlStr.append(" where ");
		sqlStr.append(" s.shoesSize=:size ");
		sqlStr.append(" and ");
		sqlStr.append(" s.steNumber=:number ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("size", size);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public List<Map<String, Object>> selectShoesFromConBySizeAndTheNumber(Integer size, String theNumber)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" sc.lockerNumber, ");
		sqlStr.append(" sc.id, ");
		sqlStr.append(" sc.shoesSize, ");
		sqlStr.append(" sc.steNumber ");

		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container sc ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_sterilizer s ");
		sqlStr.append(" on sc.steNumber=s.number ");
		sqlStr.append(" where 1=1 ");
		if (size != null) {
			sqlStr.append(" and ");
			sqlStr.append(" sc.shoesSize=:size ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" s.theaterNumber=:theNumber ");
		sqlStr.append(" and ");
		sqlStr.append(" sc.state=:state ");
		sqlStr.append(" and sc.shoesSize!='' ");

		sql = SQL.getSQL(sqlStr);
		if (size != null) {
			sql.setInteger("size", size);
		}
		sql.setVarChar("theNumber", theNumber);
		sql.setVarChar("state", BizConst.STATE.USE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public List<Map<String, Object>> selectOpeFromConBySizeAndTheNumber(Integer size, String theNumber)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" c.trayNumber, ");
		sqlStr.append(" c.id, ");
		sqlStr.append(" c.opeSize, ");
		sqlStr.append(" c.warNumber ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe_container c ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_wardrobe w ");
		sqlStr.append(" on c.warNumber=w.number ");
		sqlStr.append(" where 1=1 ");
		if (size != null) {
			sqlStr.append(" and ");
			sqlStr.append(" c.opeSize=:size ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" w.theaterNumber=:theaterNumber ");
		sqlStr.append(" and ");
		sqlStr.append(" c.alloutCount>0 ");
		sqlStr.append(" and ");
		sqlStr.append(" w.state=:state ");
		// sqlStr.append(" and c.opeSize!='' ");

		sql = SQL.getSQL(sqlStr);
		if (size != null) {
			sql.setInteger("size", size);
		}
		sql.setVarChar("theaterNumber", theNumber);
		sql.setVarChar("state", BizConst.STATE.USE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public List<ClassConvertDict> loadTheNumberConvertDict() throws BaseException {
		SQL sql = SQL.getSQL("select t.number AS code, t.name AS name from  hos_theater t");
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(ClassConvertDict.class);
	}

	public int assignUserToTheater(Integer theaterId, Integer userId) throws BaseException {
		SQL sql = SQL.getSQL("insert into hos_user_theater(userId,theaterId) values(:userId,:theaterId)");
		sql.setInteger("userId", userId);
		sql.setInteger("theaterId", theaterId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int removeTheaterFromUser(Integer theaterId, Integer userId) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_user_theater where userId=:userId and theaterId=:theaterId ");
		sql.setInteger("userId", userId);
		sql.setInteger("theaterId", theaterId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectTheaterUserByTheaterIdList(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		int start = paramMap.getStart();
		int size = paramMap.getSize();
		sqlStr.append(" select u.*  from auth_user u where 1=1 and enabled=:enabled");

		String loginName = paramMap.getStrParam("loginName");
		if (loginName != null) {
			sqlStr.append(" and u.loginName like :loginName");
		}
		sqlStr.append(" and u.id in ( select ud.userId from hos_user_theater ud where ud.theaterId =:theaterId)");

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

		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setVarChar("theaterId", paramMap.getStrParam("theaterId"));

		if (loginName != null) {
			sql.setVarChar("loginName", loginName);
		}
		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

		return lists;
	}

	@Override
	public Long selectTheaterUserByTheaterIdCount(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1  from auth_user u where 1=1 and enabled=:enabled");

		String loginName = paramMap.getStrParam("loginName");
		if (loginName != null) {
			sqlStr.append(" and u.loginName like :loginName");
		}
		sqlStr.append(" and u.id in ( select ud.userId from hos_user_theater ud where ud.theaterId =:theaterId)");
		sql.putSql(sqlStr);

		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setVarChar("theaterId", paramMap.getStrParam("theaterId"));
		if (loginName != null) {
			sql.setVarChar("loginName", loginName);
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public List<Map<String, Object>> selectNotTheaterUserByTheaterIdList(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		int start = paramMap.getStart();
		int size = paramMap.getSize();

		sqlStr.append(" SELECT                       ");
		sqlStr.append(" 	u.*                      ");
		sqlStr.append(" FROM                         ");
		sqlStr.append(" 	auth_user u,             ");
		sqlStr.append(" 	auth_role_user ru,       ");
		sqlStr.append(" 	auth_role_permission rp, ");
		sqlStr.append(" 	auth_permission p        ");
		sqlStr.append(" WHERE                        ");
		sqlStr.append(" 	1 = 1                    ");
		sqlStr.append(" AND u.id = ru.userId         ");
		sqlStr.append(" AND ru.roleId = rp.roleId    ");
		sqlStr.append(" AND rp.permissionId = p.id   ");
		sqlStr.append(" AND u.sfcjgly !=1            ");
		sqlStr.append(" AND p.permCode = :code       ");
		sqlStr.append(" AND u.enabled=:enabled       ");

		String loginName = paramMap.getStrParam("loginName");
		if (loginName != null) {
			sqlStr.append(" and u.loginName like :loginName");
		}
		sqlStr.append(" and u.id not in ( select ud.userId from hos_user_theater ud where ud.theaterId =:theaterId)");

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

		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setVarChar("theaterId", paramMap.getStrParam("theaterId"));

		if (loginName != null) {
			sql.setVarChar("loginName", loginName);
		}
		sql.setVarChar("code", BizConst.PERMISSION_CODE.CLOTHINGADMIN);
		sql.setInteger("start", start);
		sql.setInteger("size", size);
		List<Map<String, Object>> lists = this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

		return lists;
	}

	@Override
	public Long selectNotTheaterUserByTheaterIdCount(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" SELECT                       ");
		sqlStr.append(" 	1                        ");
		sqlStr.append(" FROM                         ");
		sqlStr.append(" 	auth_user u,             ");
		sqlStr.append(" 	auth_role_user ru,       ");
		sqlStr.append(" 	auth_role_permission rp, ");
		sqlStr.append(" 	auth_permission p        ");
		sqlStr.append(" WHERE                        ");
		sqlStr.append(" 	1 = 1                    ");
		sqlStr.append(" AND u.id = ru.userId         ");
		sqlStr.append(" AND ru.roleId = rp.roleId    ");
		sqlStr.append(" AND rp.permissionId = p.id   ");
		sqlStr.append(" AND u.sfcjgly !=1            ");
		sqlStr.append(" AND p.permCode = :code       ");
		sqlStr.append(" AND u.enabled= :enabled      ");

		String loginName = paramMap.getStrParam("loginName");
		if (loginName != null) {
			sqlStr.append(" and u.loginName like :loginName");
		}
		sqlStr.append(" and u.id not in ( select ud.userId from hos_user_theater ud where ud.theaterId =:theaterId)");
		sql.putSql(sqlStr);

		sql.setVarChar("enabled", BizConst.ENABLED.ENABLE);
		sql.setVarChar("theaterId", paramMap.getStrParam("theaterId"));
		sql.setVarChar("code", BizConst.PERMISSION_CODE.CLOTHINGADMIN);
		if (loginName != null) {
			sql.setVarChar("loginName", loginName);
		}
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public Long selectTheaterUserByAllId(Integer theaterId, Integer userId) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1 from ");
		sqlStr.append(" hos_user_theater ut ");
		sqlStr.append(" where  ");
		sqlStr.append(" ut.userId=:userId ");
		sqlStr.append(" and ");
		sqlStr.append(" ut.theaterId=:theaterId ");

		sql.putSql(sqlStr);

		sql.setInteger("userId", userId);
		sql.setInteger("theaterId", theaterId);
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public Long selectTheaterUserByNumberAndLoginName2(String loginName2, String theNumber) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" select 1 from ");
		sqlStr.append(" hos_user_theater ut ");
		sqlStr.append(" where  ");
		sqlStr.append(" ut.userId=( ");
		sqlStr.append(" select u.id from auth_user u where u.loginName2=:loginName2) ");
		sqlStr.append(" and ");
		sqlStr.append(" ut.theaterId=( ");
		sqlStr.append(" select w.id from hos_theater w where w.number=:theNumber) ");

		sql.putSql(sqlStr);

		sql.setVarChar("loginName2", loginName2);
		sql.setVarChar("theNumber", theNumber);
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public List<HosTheater> selectTheaterByUserId(Integer UserId) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append(" SELECT                                            ");
		sqlStr.append(" 	ht.*                                          ");
		sqlStr.append(" FROM                                              ");
		sqlStr.append(" 	hos_user_theater hut                          ");
		sqlStr.append(" LEFT JOIN hos_theater ht ON hut.theaterId = ht.id ");
		sqlStr.append(" WHERE                                             ");
		sqlStr.append(" 	hut.userId = :UserId                          ");
		sql.putSql(sqlStr);
		sql.setInteger("UserId", UserId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosTheater.class);
	}

	public List<HosTheater> selectTheaterAll() throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		SQL sql = SQL.getSQL();
		sqlStr.append("select * from hos_theater where 1=1");
		sql.putSql(sqlStr);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosTheater.class);
	}
}
