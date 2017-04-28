package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosShoesDao;
import com.sesxh.hoschange.biz.hos.entity.HosShoes;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosShoesDaoImpl extends SesframeBaseDao implements HosShoesDao {

	@Override
	public int insertHosShoes(HosShoes hosShoes) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_shoes( ");
		sqlStr.append(" shoeSize, ");
		sqlStr.append(" count, ");
		sqlStr.append(" theaterNumber ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :shoeSize, ");
		sqlStr.append(" :count, ");
		sqlStr.append(" :theaterNumber ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosShoes);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosShoesById(Integer id) throws BaseException {
		SQL sql = SQL.getSQL("delete from hos_shoes where id = :id");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosShoes(HosShoes hosShoes) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_shoes ");
		sqlStr.append(" set ");
		sqlStr.append(" count=:count, ");
		sqlStr.append(" number=:number ");
		sqlStr.append(" where ");
		sqlStr.append(" id = :id ");

		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosShoes);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}


	@Override
	public HosShoes selectHosShoesById(Integer id) throws BaseException {
		String sqlStr = " SELECT s.* FROM hos_shoes s where 1=1 and id = :id ";
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosShoes.class);
	}

	@Override
	public int updateStateByShoesId(Integer id, String state) throws BaseException {
		SQL sql = SQL.getSQL("update hos_shoes set state = :state where id  = :id ");
		sql.setInteger("id", id);
		sql.setVarChar("state", state);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateStateByShoesNumber(String number, String state) throws BaseException {
		SQL sql = SQL.getSQL("update hos_shoes set state = :state where number  = :number ");
		sql.setVarChar("number", number);
		sql.setVarChar("state", state);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	public int updateStateBySteNumber(String steNumber, String state) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update hos_shoes sh  ");
		sqlStr.append(" set ");
		sqlStr.append(" sh.state = :state   ");
		sqlStr.append(" where 1=1 ");

		sqlStr.append(" and sh.number in ");
		sqlStr.append(" ( ");
		sqlStr.append(" select ");
		sqlStr.append(" s.shoNumber ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_shoes_sterilizer s ");
		sqlStr.append(" where ");
		sqlStr.append(" steNumber = :number ");
		sqlStr.append(" ) ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("number", steNumber);
		sql.setVarChar("state", state);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosShoes selectHosShoesByShoesSize(String shoesSize) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" id, ");
		sqlStr.append(" number, ");
		sqlStr.append(" count, ");
		sqlStr.append(" state, ");
		sqlStr.append(" shoeSize ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_shoes ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" shoeSize = ");
		sqlStr.append(" :shoeSize ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("shoeSize", shoesSize);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosShoes.class);

	}

	public HosShoes selectHosShoesByShoeSizeAndTheaterNumber(Integer shoeSize, String theaterNumber)
			throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" id, ");
		sqlStr.append(" number, ");
		sqlStr.append(" count, ");
		sqlStr.append(" state, ");
		sqlStr.append(" shoeSize ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_shoes ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" shoeSize = ");
		sqlStr.append(" :shoeSize ");
		sqlStr.append(" and ");
		sqlStr.append(" theaterNumber = ");
		sqlStr.append(" :theaterNumber ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("shoeSize", shoeSize);
		sql.setVarChar("theaterNumber", theaterNumber);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosShoes.class);
	}

	@Override
	public List<HosShoes> selectHosShoesByTheaterNumber(String number) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" hs.*, ");
		sqlStr.append(" d.name AS shoeSizeName ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_shoes hs ");
		sqlStr.append(" LEFT JOIN ");
		sqlStr.append(" dictionary d ");
		sqlStr.append(" ON hs.shoeSize = d.code ");
		sqlStr.append(" WHERE ");
		sqlStr.append(" d.type = 'shoes' AND ");
		sqlStr.append(" hs.theaterNumber = :theaterNumber ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("theaterNumber", number);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosShoes.class);
	}

	@Override
	public List<Map<String, Object>> selectHosShoesListByTheaterNumber(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT                                              ");
		sqlStr.append(" 	s.*, (                                          ");
		sqlStr.append(" 		SELECT                                      ");
		sqlStr.append(" 			sum(1)                                  ");
		sqlStr.append(" 		FROM                                        ");
		sqlStr.append(" 			hos_sterilizer_container sc             ");
		sqlStr.append(" 		WHERE                                       ");
		sqlStr.append(" 			sc.shoesSize = s.shoeSize               ");
		sqlStr.append(" 		AND sc.state = :fp                          ");
		sqlStr.append(" 		AND sc.steNumber IN (                       ");
		sqlStr.append(" 			SELECT                                  ");
		sqlStr.append(" 				hs.number                           ");
		sqlStr.append(" 			FROM                                    ");
		sqlStr.append(" 				hos_sterilizer hs                   ");
		sqlStr.append(" 			WHERE                                   ");
		sqlStr.append(" 				1 = 1                               ");
		sqlStr.append(" 			AND hs.theaterNumber = :theaterNumber   ");
		sqlStr.append(" 		)                                           ");
		sqlStr.append(" 	) AS fp,                                        ");
		sqlStr.append(" 	(                                               ");
		sqlStr.append(" 		SELECT                                      ");
		sqlStr.append(" 			sum(1)                                  ");
		sqlStr.append(" 		FROM                                        ");
		sqlStr.append(" 			hos_sterilizer_container sc             ");
		sqlStr.append(" 		WHERE                                       ");
		sqlStr.append(" 			sc.shoesSize = s.shoeSize               ");
		sqlStr.append(" 		AND sc.state = :syz                         ");
		sqlStr.append(" 		AND sc.steNumber IN (                       ");
		sqlStr.append(" 			SELECT                                  ");
		sqlStr.append(" 				hs.number                           ");
		sqlStr.append(" 			FROM                                    ");
		sqlStr.append(" 				hos_sterilizer hs                   ");
		sqlStr.append(" 			WHERE                                   ");
		sqlStr.append(" 				1 = 1                               ");
		sqlStr.append(" 			AND hs.theaterNumber = :theaterNumber   ");
		sqlStr.append(" 		)                                           ");
		sqlStr.append(" 	) AS syz                                        ");
		sqlStr.append(" FROM                                                ");
		sqlStr.append(" 	hos_shoes s                                     ");
		sqlStr.append(" WHERE                                               ");
		sqlStr.append(" 	1 = 1                                           ");
		sqlStr.append(" AND s.theaterNumber = :theaterNumber                ");
		sqlStr.append(" ORDER BY s.shoeSize                                 ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("fp", BizConst.STATE.USE);
		sql.setVarChar("syz", BizConst.STATE.USER_USE);
		sql.setVarChar("theaterNumber", paramMap.getStrParam("number"));
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}
}
