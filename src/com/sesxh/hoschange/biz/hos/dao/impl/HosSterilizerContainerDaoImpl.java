package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.util.StringUtils;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosSterilizerContainerDao;
import com.sesxh.hoschange.biz.hos.entity.HosSterilizerContainer;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosSterilizerContainerDaoImpl extends SesframeBaseDao implements HosSterilizerContainerDao {

	public int updateContainerBySteNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql = SQL.getSQL("update hos_sterilizer_container set steNumber=:newNumber where steNumber=:oldNumber ");
		sql.setVarChar("oldNumber", oldNumber);
		sql.setVarChar("newNumber", newNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int deleteContainerBySteNumber(String number) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_sterilizer_container where  steNumber=:number ");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<Map<String, Object>> selectCountShoesSize(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		String state = BizConst.STATE.USE;
		sqlStr.append(" select ");
		sqlStr.append(" shoesSize, ");
		sqlStr.append(" sum(1) as count ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" steNumber=:number ");
		sqlStr.append(" and ");
		sqlStr.append(" state=:state ");
		sqlStr.append(" group by ");
		sqlStr.append(" shoesSize ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("state", state);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public int updateSterilizerContainer(HosSterilizerContainer hos) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_sterilizer_container ");
		sqlStr.append(" SET state =:state,              ");
		sqlStr.append("  shoesSize =:shoesSize          ");
		sqlStr.append(" WHERE                           ");
		sqlStr.append(" 	1 = 1                       ");
		sqlStr.append(" AND shoesSize =:oldShoesSize    ");
		sqlStr.append(" AND steNumber =:steNumber       ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hos);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int insertSterilizerContainer(HosSterilizerContainer hos) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_sterilizer_container( ");
		sqlStr.append(" steNumber, ");
		sqlStr.append(" lockerNumber, ");
		sqlStr.append(" doorNumber ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :steNumber, ");
		sqlStr.append(" :lockerNumber, ");
		sqlStr.append(" :doorNumber ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hos);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosSterilizerContainer> selectSterilizerContainerOneBySteNumber(String steNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                              ");
		sqlStr.append(" 	hsc.*, (                        ");
		sqlStr.append(" 		SELECT                      ");
		sqlStr.append(" 			aud.userName            ");
		sqlStr.append(" 		FROM                        ");
		sqlStr.append(" 			auth_user_detail aud    ");
		sqlStr.append(" 		WHERE                       ");
		sqlStr.append(" 			aud.userId = hsc.userId ");
		sqlStr.append(" 	) AS userName                   ");
		sqlStr.append(" FROM                                ");
		sqlStr.append(" 	hos_sterilizer_container hsc    ");
		sqlStr.append(" WHERE                               ");
		sqlStr.append(" 	1 = 1                           ");

		if (steNumber != null) {
			sqlStr.append(" and hsc.steNumber = :steNumber ");
		}
		sql = SQL.getSQL(sqlStr);
		if (steNumber != null) {
			sql.setVarChar("steNumber", steNumber);
		}
		List<HosSterilizerContainer> hos = this.getSession().getSQLExecutor().setSQL(sql)
				.executeQueryBeanList(HosSterilizerContainer.class);
		return hos;

	}

	public List<HosSterilizerContainer> selectSterilizerContainerTwoBySteNumber(String steNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" sc.id, ");
		sqlStr.append(" sc.steNumber, ");
		sqlStr.append(" sc.lockerNumber, ");
		sqlStr.append(" sc.shoesSize, ");
		sqlStr.append(" sc.state, ");
		sqlStr.append(" ss.shoNumber ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container sc ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_shoes_ste ss ");
		sqlStr.append(" on ");
		sqlStr.append(" sc.steNumber=ss.steNumber ");
		sqlStr.append(" and ");
		sqlStr.append(" sc.lockerNumber=ss.lockerNumber ");
		sqlStr.append(" where 1=1 ");

		if (steNumber != null) {
			sqlStr.append(" and sc.steNumber = :steNumber ");
		}
		sql = SQL.getSQL(sqlStr);
		if (steNumber != null) {
			sql.setVarChar("steNumber", steNumber);
		}
		List<HosSterilizerContainer> hos = this.getSession().getSQLExecutor().setSQL(sql)
				.executeQueryBeanList(HosSterilizerContainer.class);
		return hos;
	}

	@Override
	public int updateContainerById(Integer id, String state, String shoesSize) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer_container ");
		sqlStr.append(" set ");
		sqlStr.append(" state = :state, ");
		sqlStr.append(" shoesSize=:shoesSize ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("state", state);
		sql.setVarChar("shoesSize", shoesSize);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();

	}

	@Override
	public HosSterilizerContainer selectHosSterilizerContainerById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" sc.id, ");
		sqlStr.append(" sc.steNumber, ");
		sqlStr.append(" sc.lockerNumber, ");
		sqlStr.append(" sc.shoesSize, ");
		sqlStr.append(" sc.state ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container sc ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and id = :id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizerContainer.class);

	}

	public List<Map<String, Object>> selectContainerBySteNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" id ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" steNumber=:number ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public List<Map<String, Object>> selectContainerByThNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" sc.id ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container sc ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_sterilizer s ");
		sqlStr.append(" on sc.steNumber=s.number ");

		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" s.theaterNumber=:number ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public HosSterilizerContainer selectSteContainerByNumber(String number, String lockerNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" sc.id, ");
		sqlStr.append(" sc.steNumber, ");
		sqlStr.append(" sc.lockerNumber, ");
		sqlStr.append(" sc.shoesSize, ");
		sqlStr.append(" sc.state ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer_container sc ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and steNumber = :number ");
		sqlStr.append(" and lockerNumber = :lockerNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		sql.setVarChar("lockerNumber", lockerNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizerContainer.class);
	}

	public int updateContainer(HosSterilizerContainer HosSterilizerContainer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer_container ");
		sqlStr.append(" set ");
		sqlStr.append(" userId = :userId, ");
		// sqlStr.append(" shoesSize = :shoesSize, ");
		sqlStr.append(" state = :state ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(HosSterilizerContainer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public List<HosSterilizerContainer> findSteConNumberByUserIdAndThNumber(Integer userId, String number)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" sc.lockerNumber,  ");
		sqlStr.append(" sc.id,  ");
		sqlStr.append(" sc.steNumber,  ");
		sqlStr.append(" sc.yesOrNoBinding ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_sterilizer s ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_sterilizer_container sc ");
		sqlStr.append(" on s.number=sc.steNumber ");
		sqlStr.append(" where ");
		sqlStr.append(" s.theaterNumber=:number ");
		sqlStr.append(" and sc.userId = :userId ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosSterilizerContainer.class);
	}

	@Override
	public int updateContainerState(Integer id, String state) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer_container ");
		sqlStr.append(" set ");
		sqlStr.append(" state = :state ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sqlStr.append(" and ");
		sqlStr.append(" shoesSize!='' ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("state", state);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	// ####################################### 11-30
	// ##########################################//

	public int updateSizeShoesToSterilizer(Integer id, String shoesSize) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_sterilizer_container ");
		sqlStr.append(" set ");
		sqlStr.append(" shoesSize=:shoesSize ");
		sqlStr.append(" where ");
		sqlStr.append(" id=:id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("shoesSize", shoesSize);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosSterilizerContainer selectSterilizerContainerById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                        ");
		sqlStr.append(" 	*                         ");
		sqlStr.append(" FROM                          ");
		sqlStr.append(" 	hos_sterilizer_container  ");
		sqlStr.append(" WHERE                         ");
		sqlStr.append(" 	1 = 1                     ");
		sqlStr.append(" AND id =:id                   ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizerContainer.class);
	}

	@Override
	public int updateLockSterilizerContainer(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_sterilizer_container hsc");
		sqlStr.append(" SET hsc.yesOrNoLock = :yesOrNoLock ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	hsc.id = :id                   ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.TRUE);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateStopLockSterilizer(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_sterilizer_container hsc");
		sqlStr.append(" SET hsc.yesOrNoLock = :yesOrNoLock ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	hsc.id = :id                   ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setInteger("id", id);

		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateBindingSterilizerAndUser(Integer id, Integer userId, Integer shoesSize) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_sterilizer_container hsc      ");
		sqlStr.append(" SET hsc.yesOrNoBinding = :yesOrNoBinding,");
		sqlStr.append("  hsc.userId = :userId ,                  ");
		sqlStr.append("  hsc.shoesSize = :shoesSize              ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	hsc.id = :id                         ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.TRUE);
		sql.setInteger("userId", userId);
		sql.setInteger("shoesSize", shoesSize);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateStopBindingSterilizerAndUser(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_sterilizer_container hsc      ");
		sqlStr.append(" SET hsc.yesOrNoBinding = :yesOrNoBinding,");
		sqlStr.append("  hsc.userId = NULL,                      ");
		sqlStr.append("  hsc.shoesSize = NULL                    ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	hsc.id = :id                         ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.FALSE);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectAddShoeRule(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT * FROM hos_addshoes_rule WHERE 1=1 ");
		String orderDir = paramMap.getOrder();
		String sort = paramMap.getSort();

		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sql = SQL.getSQL(sqlStr);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long selectAddShoeRuleCount(ParamMap paramMap) throws BaseException {
		SQL sql = SQL.getSQL("SELECT 1 FROM hos_addshoes_rule WHERE 1=1 ");
		Integer count = this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();
		return count.longValue();
	}

	@Override
	public int updateSetUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException {
		SQL sql = SQL
				.getSQL("UPDATE hos_user_addshoes SET addShoesCode=:addShoesCode,size=:size WHERE userId = :userId");
		sql.setInteger("userId", userId);
		sql.setVarChar("addShoesCode", code);
		sql.setInteger("size", size);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int insertSetUserAddShoeRule(String code, Integer userId, Integer size) throws BaseException {
		SQL sql = SQL.getSQL(
				"INSERT INTO hos_user_addshoes (userId, addShoesCode, size) VALUES ( :userId, :addShoesCode, :size)");
		sql.setInteger("userId", userId);
		sql.setVarChar("addShoesCode", code);
		sql.setInteger("size", size);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosSterilizerContainer selectBindingSterilizerAndUser(Integer userId, String theaterNumber)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                                   ");
		sqlStr.append(" 	hsc.*                                ");
		sqlStr.append(" FROM                                     ");
		sqlStr.append(" 	hos_sterilizer_container hsc         ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	1 = 1                                ");
		sqlStr.append(" AND hsc.userId = :userId                 ");
		sqlStr.append(" AND hsc.yesOrNoBinding = :yesOrNoBinding ");
		sqlStr.append(" AND hsc.steNumber IN (                   ");
		sqlStr.append(" 	SELECT                               ");
		sqlStr.append(" 		hs.number                        ");
		sqlStr.append(" 	FROM                                 ");
		sqlStr.append(" 		hos_sterilizer hs                ");
		sqlStr.append(" 	WHERE                                ");
		sqlStr.append(" 		hs.theaterNumber = :theaterNumber");
		sqlStr.append(" )                                        ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.TRUE);
		sql.setVarChar("theaterNumber", theaterNumber);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizerContainer.class);
	}

	@Override
	public HosSterilizerContainer selectBindingContainer(String deviceNumber, Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                   ");
		sqlStr.append(" 	hsc.*                                ");
		sqlStr.append(" FROM                                     ");
		sqlStr.append(" 	hos_sterilizer_container hsc         ");
		sqlStr.append(" WHERE                                    ");
		sqlStr.append(" 	1 = 1                                ");
		sqlStr.append(" AND hsc.yesOrNoBinding = :yesOrNoBinding ");
		sqlStr.append(" AND hsc.steNumber = :steNumber           ");
		sqlStr.append(" AND hsc.userId = :userId                 ");
		sqlStr.append(" AND hsc.yesOrNoLock = :yesOrNoLock       ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.TRUE);
		sql.setVarChar("steNumber", deviceNumber);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizerContainer.class);
	}

	@Override
	public int updataBindingContainerState(HosSterilizerContainer container) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_sterilizer_container ");
		sqlStr.append(" SET state = :state              ");
		sqlStr.append(" WHERE                           ");
		sqlStr.append(" 	1 = 1                       ");
		sqlStr.append(" AND id = :id                    ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("state", BizConst.STATE.USER_USE);
		sql.setInteger("id", container.getId());
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosSterilizerContainer> selectRandomContainer(String deviceNumber, Integer shoeSize)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                    ");
		sqlStr.append(" 	hsc.*                                 ");
		sqlStr.append(" FROM                                      ");
		sqlStr.append(" 	hos_sterilizer_container hsc          ");
		sqlStr.append(" WHERE                                     ");
		sqlStr.append(" 	1 = 1                                 ");
		if (StringUtils.isNotEmpty(deviceNumber)) {
			sqlStr.append(" AND hsc.steNumber = :steNumber        ");
		}
		sqlStr.append(" AND hsc.shoesSize = :shoesSize            ");
		sqlStr.append(" AND hsc.yesOrNoLock = :yesOrNoLock        ");
		sqlStr.append(" AND hsc.yesOrNoBinding = :yesOrNoBinding  ");
		sqlStr.append(" AND hsc.state = :state  ");
		sql = SQL.getSQL(sqlStr);
		if (StringUtils.isNotEmpty(deviceNumber)) {
			sql.setVarChar("steNumber", deviceNumber);
		}
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("yesOrNoBinding", BizConst.BOOLEAN.FALSE);
		sql.setInteger("shoesSize", shoeSize);
		sql.setVarChar("state", BizConst.STATE.USE);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosSterilizerContainer.class);
	}

	@Override
	public HosSterilizerContainer selectSterilizerContainerByUserId(Integer userId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                        ");
		sqlStr.append(" 	*                         ");
		sqlStr.append(" FROM                          ");
		sqlStr.append(" 	hos_sterilizer_container  ");
		sqlStr.append(" WHERE                         ");
		sqlStr.append(" 	1 = 1                     ");
		sqlStr.append(" AND userId = :userId          ");
		sqlStr.append(" AND state = :state            ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("state", BizConst.STATE.USER_USE);
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosSterilizerContainer.class);
	}

	@Override
	public List<Map<String, Object>> selectAddShowRuleByUserId(Integer userId) throws BaseException {
		SQL sql = SQL.getSQL("SELECT * FROM hos_user_addshoes WHERE userId=:userId");
		sql.setInteger("userId", userId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public int updateContainerByUserId(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("UPDATE hos_sterilizer_container SET userId=NULL ,state=0 WHERE id=:id");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosSterilizerContainer> selectContainerByAdminCodeAndShoSizeAndState(String deviceNumber,
			Integer shoeSize, String state) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                            ");
		sqlStr.append(" 	*                             ");
		sqlStr.append(" FROM                              ");
		sqlStr.append(" 	hos_sterilizer_container hsc  ");
		sqlStr.append(" WHERE                             ");
		sqlStr.append(" 	hsc.steNumber = :steNumber    ");
		sqlStr.append(" AND hsc.yesOrNoLock = :yesOrNoLock");
		if (shoeSize != null) {
			sqlStr.append(" AND hsc.shoesSize = :shoesSize    ");
		}
		sqlStr.append(" AND hsc.state=:state              ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("steNumber", deviceNumber);
		sql.setVarChar("yesOrNoLock", BizConst.BOOLEAN.FALSE);
		sql.setVarChar("state", state);
		if (shoeSize != null) {
			sql.setInteger("shoesSize", shoeSize);
		}
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosSterilizerContainer.class);
	}

	@Override
	public int selectRecordByUseridAndSteNumber(String userid, String steNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                    ");
		sqlStr.append("	*                        ");
		sqlStr.append("FROM                      ");
		sqlStr.append("	hos_sterilizer_container ");
		sqlStr.append("WHERE                     ");
		sqlStr.append("	steNumber = :steNumber   ");
		sqlStr.append("AND userid = :userid      ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("steNumber", steNumber);
		sql.setVarChar("userid", userid);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryRowCount();

	}
}
