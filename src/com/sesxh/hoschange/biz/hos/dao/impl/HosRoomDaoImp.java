package com.sesxh.hoschange.biz.hos.dao.impl;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosRoomDao;
import com.sesxh.hoschange.biz.hos.entity.HosRoom;

@Repository
public class HosRoomDaoImp extends SesframeBaseDao implements HosRoomDao {

	@Override
	public int addHosRoom(HosRoom hosRoom) throws BaseException {

		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" INSERT INTO hos_room ( ");
		sqlStr.append(" 	name,              ");
		sqlStr.append(" 	description,       ");
		sqlStr.append(" 	theaterNumber      ");
		sqlStr.append(" )                      ");
		sqlStr.append(" VALUES                 ");
		sqlStr.append(" 	(                  ");
		sqlStr.append(" 		:name,         ");
		sqlStr.append(" 		:description,  ");
		sqlStr.append(" 		:theaterNumber ");
		sqlStr.append(" 	)                  ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRoom);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosRoomByTheaterNumber(String theaterNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" DELETE                              ");
		sqlStr.append(" FROM                                ");
		sqlStr.append(" 	hos_room                        ");
		sqlStr.append(" WHERE                               ");
		sqlStr.append(" 	1 = 1                           ");
		sqlStr.append(" AND theaterNumber = :theaterNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("theaterNumber", theaterNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosRoomByTheaterNumber(String oldNumber, String newNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" UPDATE hos_room                ");
		sqlStr.append(" SET theaterNumber = :newNumber ");
		sqlStr.append(" WHERE                          ");
		sqlStr.append(" 	1 = 1                      ");
		sqlStr.append(" AND theaterNumber = :oldNumber ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("newNumber", newNumber);
		sql.setVarChar("oldNumber", oldNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosRoom selectHosRoomByTheaterAndRoomId(String theaterNumber, Integer roomId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                               ");
		sqlStr.append(" 	*                                ");
		sqlStr.append(" FROM                                 ");
		sqlStr.append(" 	hos_room hr                      ");
		sqlStr.append(" WHERE                                ");
		sqlStr.append(" 	1 = 1                            ");
		sqlStr.append(" AND hr.theaterNumber =:theaterNumber ");
		sqlStr.append(" AND hr.name =:name                   ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("theaterNumber", theaterNumber);
		sql.setInteger("name", roomId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRoom.class);
	}

	@Override
	public HosRoom selectHosRoomByRoomId(Integer roomId) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                               ");
		sqlStr.append(" 	*                                ");
		sqlStr.append(" FROM                                 ");
		sqlStr.append(" 	hos_room hr                      ");
		sqlStr.append(" WHERE                                ");
		sqlStr.append(" 	1 = 1                            ");
		sqlStr.append(" AND hr.id =:roomId                   ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("roomId", roomId);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRoom.class);
	}

}
