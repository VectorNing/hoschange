package com.sesxh.hoschange.biz.hos.dao.impl;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosBusinessDao;
import com.sesxh.hoschange.biz.hos.entity.HosBusiness;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosBusinessDaoImpl extends SesframeBaseDao implements HosBusinessDao{

	public int insertDeviceLog(HosBusiness bus) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_business( ");
		sqlStr.append(" userId, ");
		sqlStr.append(" deviceType, ");
		sqlStr.append(" operateTime, ");
		sqlStr.append(" deviceNumber, ");
		sqlStr.append(" conNumber, ");
		sqlStr.append(" size, ");
		sqlStr.append(" operationType, ");
		sqlStr.append(" description ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :userId, ");
		sqlStr.append(" :deviceType, ");
		sqlStr.append(" :operateTime, ");
		sqlStr.append(" :deviceNumber, ");
		sqlStr.append(" :conNumber, ");
		sqlStr.append(" :size, ");
		sqlStr.append(" :operationType, ");
		sqlStr.append(" :description ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(bus);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	@Override
	public Long selectHosRecordForBrushCardTimes(String brushCardTime, Integer userId, String deviceType,
			String deviceNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_business ");
		sqlStr.append(" WHERE ");
		sqlStr.append(" TIMESTAMPDIFF(SECOND,operateTime,NOW()) < :brushCardTime ");
		sqlStr.append(" AND ");
		sqlStr.append(" userId = :userId ");
		sqlStr.append(" AND ");
		sqlStr.append(" deviceType = :deviceType ");
		sqlStr.append(" AND ");
		sqlStr.append(" deviceNumber =:deviceNumber ");
		sqlStr.append(" AND ");
		sqlStr.append(" operationType =:operationType ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("brushCardTime", brushCardTime);
		sql.setInteger("userId", userId);
		sql.setVarChar("deviceType", deviceType);
		sql.setVarChar("deviceNumber", deviceNumber);
		sql.setVarChar("operationType", BizConst.OPERATION_TYPE.RECEIVE);//领取
		
		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public HosBusiness selectDeviceLogByUseridAndType(Integer userId,String deviceType) throws BaseException{
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                       ");
		sqlStr.append(" 	*                        ");
		sqlStr.append(" FROM                         ");
		sqlStr.append(" 	hos_business             ");
		sqlStr.append(" WHERE                        ");
		sqlStr.append(" 	1 = 1                    ");
		sqlStr.append(" AND userId = :userId         ");
		sqlStr.append(" AND deviceType = :deviceType ");
		sqlStr.append(" ORDER BY                     ");
		sqlStr.append(" 	id DESC                  ");
		sqlStr.append(" LIMIT 1                      ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("userId", userId);
		sql.setVarChar("deviceType", deviceType);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosBusiness.class);
	}
	
	@Override
	public int updateDeviceLogById(HosBusiness bus) throws BaseException{
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_business                 ");
		sqlStr.append(" SET operationType = :operationType, ");
		sqlStr.append("  description = :description         ");
		sqlStr.append(" WHERE                               ");
		sqlStr.append(" 1=1                                 ");
		sqlStr.append(" and id = :id                        ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(bus);
		
		this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
		return 0;
	}
}
