package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecordDao;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.sys.entity.AuthRole;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;
import com.sesxh.hoschange.global.BizConst.SIGN;

@Repository
public class HosRecordDaoImpl extends SesframeBaseDao implements HosRecordDao {

	public int insertHosRecordService(HosRecord hosRecord) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_record( ");
		sqlStr.append(" userId, ");
		sqlStr.append(" sign, ");
		sqlStr.append(" signInTime, ");
		sqlStr.append(" deviceType, ");
		sqlStr.append(" deviceNumber, ");
		sqlStr.append(" theaterNumber, ");
		// sqlStr.append(" isCallback, ");
		sqlStr.append(" callbackTime ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :userId, ");
		sqlStr.append(" :sign, ");
		sqlStr.append(" :signInTime, ");
		sqlStr.append(" :deviceType, ");
		sqlStr.append(" :deviceNumber, ");
		sqlStr.append(" :theaterNumber, ");
		// sqlStr.append(" :isCallback, ");
		sqlStr.append(" :callbackTime ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRecord);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateHosRecordService(HosRecord hosRecord) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_record ");
		sqlStr.append(" set ");
		sqlStr.append(" sign =:sign, ");
		sqlStr.append(" isCallback =:isCallback, ");
		sqlStr.append(" callbackTime =:callbackTime ");
		sqlStr.append(" where ");
		sqlStr.append(" id =:id ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRecord);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public Long selectHosRecordCountByUserIdM1(Integer userId, String isCallback, Integer latelyNum)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(
				" (select r.* from hos_record r where sign=:sign and r.userId=:userId order by id desc limit :latelyNum) tmp ");
		sqlStr.append(" where tmp.userId=:userId   ");
		sqlStr.append(" and  ");
		sqlStr.append(" tmp.isCallback=:isCallback ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		sql.setVarChar("isCallback", isCallback);
		sql.setVarChar("sign", BizConst.SIGN.OUT);
		sql.setInteger("latelyNum", latelyNum);

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	public Long selectHosRecordCountByUserIdM2(Integer userId, String isCallback, String rosterTime)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" (select r.* from hos_record r where r.userId=:userId");
		sqlStr.append("  and DATEDIFF(NOW(),r.signInTime)<=:rosterTime )tmp ");
		sqlStr.append(" where tmp.userId=:userId   ");
		sqlStr.append(" and  ");
		sqlStr.append(" tmp.isCallback=:isCallback ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		sql.setVarChar("isCallback", isCallback);
		sql.setVarChar("rosterTime", rosterTime);

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public Long selectHosRecordForBrushCardTimes(String brushCardTime, Integer userId, String deviceType,
			String deviceNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_record ");
		sqlStr.append(" WHERE ");
		sqlStr.append(" TIMESTAMPDIFF(SECOND,signInTime,NOW()) < :brushCardTime ");
		sqlStr.append(" AND ");
		sqlStr.append(" userId = :userId ");
		sqlStr.append(" AND ");
		sqlStr.append(" deviceType = :deviceType ");
		sqlStr.append(" AND ");
		sqlStr.append(" deviceNumber =:deviceNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("brushCardTime", brushCardTime);
		sql.setInteger("userId", userId);
		sql.setVarChar("deviceType", deviceType);
		sql.setVarChar("deviceNumber", deviceNumber);

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	public HosRecord selectHosRecordLastByUserId(Integer userId, String theaterNumber, String deviceType)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" r.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_record r ");
		sqlStr.append(" where ");
		sqlStr.append(" r.userId=:userId ");
		if (deviceType != null && deviceType != "") {
			sqlStr.append(" and ");
			sqlStr.append(" r.deviceType=:deviceType ");
		}
		sqlStr.append(" and ");
		sqlStr.append(" r.theaterNumber=:theaterNumber ");
		// if(deviceType.equals(BizConst.DEVICE_TYPE.WARDRODE)){
		// sqlStr.append(" and ");
		// sqlStr.append(" r.deviceNumber=:deviceNumber ");
		// }else{//鞋柜 查询手术室编号 12-14
		// }
		sqlStr.append(" order by id desc limit 1 ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		if (deviceType != null && deviceType != "") {
			sql.setVarChar("deviceType", deviceType);
		}

		sql.setVarChar("theaterNumber", theaterNumber);
		HosRecord hosRecord = this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecord.class);
		return hosRecord;

	}

	public List<Map<String, Object>> selectHosRecordSet(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT ");
		sqlStr.append(" hr.*, ");
		sqlStr.append(" au.userName, ");
		sqlStr.append(" a.loginName, ");
		sqlStr.append(" ht.name ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_record hr ");
		sqlStr.append(" left join ");
		sqlStr.append(" auth_user a on hr.userId = a.id ");
		sqlStr.append(" left join ");
		sqlStr.append(" auth_user_detail au on a.id = au.userId ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_theater ht on hr.theaterNumber = ht.number ");
		sqlStr.append(" where 1=1 ");

		String userName = pm.getStrParam("userName");
		if (userName != null) {
			sqlStr.append(" and au.userName like :userName ");
		}
		String theaterNumber = pm.getStrParam("theaterNumber");
		if (theaterNumber != null) {
			sqlStr.append(" and hr.theaterNumber like :theaterNumber ");
		}
		String sign = pm.getStrParam("sign");
		if (sign != null) {
			sqlStr.append(" and hr.sign=:sign ");
		}

		String orderDir = pm.getOrder();
		String sort = pm.getSort();
		if (!ZStrUtil.isEmptyAfterTrimE(sort)) {
			sqlStr.append(" order by " + sort + " ");
			sqlStr.append(orderDir == null ? "asc" : orderDir);
		} else {
			sqlStr.append(" order by id asc ");
		}
		sqlStr.append(" limit :start ,:size");

		sql = SQL.getSQL(sqlStr);

		if (userName != null) {
			sql.setVarChar("userName", userName);
		}
		if (theaterNumber != null) {
			sql.setVarChar("theaterNumber", theaterNumber);
		}
		if (sign != null) {
			sql.setVarChar("sign", sign);
		}
		sql.setInteger("start", pm.getStart());
		sql.setInteger("size", pm.getSize());

		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public Long selectHosRecordCount(ParamMap pm) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_record hr ");
		sqlStr.append(" left join ");
		sqlStr.append(" auth_user a on hr.userId = a.id ");
		sqlStr.append(" left join ");
		sqlStr.append(" auth_user_detail au on a.id = au.userId ");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_theater ht on hr.theaterNumber = ht.number ");
		sqlStr.append(" where 1=1 ");

		String userName = pm.getStrParam("userName");
		if (userName != null) {
			sqlStr.append(" and au.userName like :userName ");
		}
		String theaterNumber = pm.getStrParam("theaterNumber");
		if (theaterNumber != null) {
			sqlStr.append(" and hr.theaterNumber like :theaterNumber ");
		}
		String sign = pm.getStrParam("sign");
		if (sign != null) {
			sqlStr.append(" and hr.sign=:sign ");
		}
		sql = SQL.getSQL(sqlStr);

		if (userName != null) {
			sql.setVarChar("userName", userName);
		}
		if (theaterNumber != null) {
			sql.setVarChar("theaterNumber", theaterNumber);
		}
		if (sign != null) {
			sql.setVarChar("sign", sign);
		}

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);
		Integer count = SQLExecutor.executeQueryRowCount();
		return count.longValue();
	}

	public HosRecord selectHosRecordById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" r.* ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_record r ");
		sqlStr.append(" where ");
		sqlStr.append(" r.id=:id ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		HosRecord hosRecord = this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecord.class);
		return hosRecord;
	}

	@Override
	public List<HosRecord> selectHosRecordByUserIdAndIsCallBack(Integer userId, String isCallback)
			throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT               ");
		sqlStr.append(" 	*                ");
		sqlStr.append(" FROM                 ");
		sqlStr.append(" 	hos_record       ");
		sqlStr.append(" WHERE                ");
		sqlStr.append(" 	userId = :userId       ");
		sqlStr.append(" AND isCallback = :isCallback  ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		sql.setVarChar("isCallback", isCallback);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosRecord.class);
	}

	@Override
	public int updateHosRecordIsCallbackById(Integer id, String isCallback) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_record    ");
		sqlStr.append(" SET isCallback = :isCallback   ");
		sqlStr.append(" WHERE                ");
		sqlStr.append(" 	1 = 1            ");
		sqlStr.append(" AND id = :id;         ");

		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("isCallback", isCallback);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<HosRecord> selectOvertimeNoSignOut(String time) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                                           ");
		sqlStr.append("	*                                              ");
		sqlStr.append("FROM                                             ");
		sqlStr.append("	hos_record                                      ");
		sqlStr.append("WHERE                                            ");
		sqlStr.append("	sign = :sign                                      ");
		sqlStr.append("AND  signInTime<:time");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("sign", SIGN.IN);
		sql.setVarChar("time", time);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosRecord.class);
	}

	@Override
	public HosRecord selectHosRecordLqcsById(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateHosRecordLqcs(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AuthRole selectAuthRoleLqcsById(Integer id) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
}
