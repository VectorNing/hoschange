package com.sesxh.hoschange.biz.hos.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosOperationDao;
import com.sesxh.hoschange.biz.hos.entity.HosOperation;
import com.sesxh.hoschange.biz.util.ClassConvertDict;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

@Repository
public class HosOperationDaoImpl extends SesframeBaseDao implements HosOperationDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int[] bacthInsertHosOperation(List<HosOperation> hosOperation) throws BaseException {
		long start = System.currentTimeMillis();

		int[] ii = null;
		String sql = "insert into hos_operation (clothSize,number)values(?,?) ";
		try {
			ii = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int index) throws SQLException {
					ps.setString(1, hosOperation.get(index).getClothSize());
					ps.setString(2, hosOperation.get(index).getNumber());
				}

				@Override
				public int getBatchSize() {
					return hosOperation.size();
				}
			});
			long end = System.currentTimeMillis();
			System.out.println((end - start));
			return ii;
		} catch (org.springframework.dao.DataAccessException e) {
			e.printStackTrace();
			// throw new DataAccessException(e.getMessage());
		}

		return ii;

	}

	@Override
	public int insertHosOperation(HosOperation hosOperation) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" ( ");
		sqlStr.append(" number,");
		sqlStr.append(" count,");
		sqlStr.append(" clothSize, ");
		sqlStr.append(" theNumber ");
		sqlStr.append(" ) ");
		sqlStr.append(" values ");
		sqlStr.append(" ( ");
		sqlStr.append(" :number, ");
		sqlStr.append(" :count, ");
		sqlStr.append(" :clothSize, ");
		sqlStr.append(" :theNumber ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosOperation);
		return this.sessionManager.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosOperationById(Integer id) throws BaseException {

		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" delete ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" where ");
		sqlStr.append(" id ");
		sqlStr.append(" = ");
		sqlStr.append(" :id ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);

		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosOperation(HosOperation hosOperation) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" set ");
		sqlStr.append(" number = ");
		sqlStr.append(" :number, ");
		sqlStr.append(" count = ");
		sqlStr.append(" :count, ");
		sqlStr.append(" state = ");
		sqlStr.append(" :state, ");
		sqlStr.append(" clothSize = ");
		sqlStr.append(" :clothSize ");
		sqlStr.append(" where ");
		sqlStr.append(" id = ");
		sqlStr.append(" :id ");

		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosOperation);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectAllHosOperation(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT ");
		sqlStr.append(" id, ");
		sqlStr.append(" number, ");
		sqlStr.append(" count, ");
		sqlStr.append(" state, ");
		sqlStr.append(" clothSize ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" where 1=1 ");

		if (paramMap.getStrParam("clothSize") != null) {
			sqlStr.append(" and clothSize = :clothSize ");
		}
		if (paramMap.getStrParam("number") != null) {
			sqlStr.append(" and number like :number ");
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

		if (paramMap.getStrParam("clothSize") != null) {
			sql.setVarChar("clothSize", paramMap.getStrParam("clothSize"));
		}
		if (paramMap.getStrParam("number") != null) {
			sql.setVarChar("number", paramMap.getStrParam("number"));
		}

		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	@Override
	public Long hosOperationCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" where 1=1 ");
		if (paramMap.getStrParam("clothSize") != null) {
			sqlStr.append(" and clothSize = :clothSize ");
		}

		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("clothSize") != null) {
			sql.setVarChar("clothSize", paramMap.getStrParam("clothSize"));
		}

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public HosOperation selectHosOperationById(Integer id) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" * ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" id = :id ");

		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosOperation.class);
	}

	@Override
	public HosOperation selectHosOperationByClothSize(String clothSize) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" id, ");
		sqlStr.append(" number, ");
		sqlStr.append(" count, ");
		sqlStr.append(" state, ");
		sqlStr.append(" clothSize ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" clothSize = ");
		sqlStr.append(" :clothSize ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("clothSize", clothSize);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosOperation.class);
	}

	@Override
	public HosOperation selectHosOperationByClothSizeAndtheNumber(String clothSize, String theNumber)
			throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                     ");
		sqlStr.append(" 	*                      ");
		sqlStr.append(" FROM                       ");
		sqlStr.append(" 	hos_operation          ");
		sqlStr.append(" WHERE                      ");
		sqlStr.append(" 	1 = 1                  ");
		sqlStr.append(" AND clothSize = :clothSize ");
		sqlStr.append(" AND theNumber = :theNumber ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("clothSize", clothSize);
		sql.setVarChar("theNumber", theNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosOperation.class);

	}

	@Override
	public int deleteHosOperationByClothSize(Integer clothSize, Integer state) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" delete ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_operation ");
		sqlStr.append(" where ");
		sqlStr.append(" clothSize = ");
		sqlStr.append(" :clothSize ");
		if (state != null) {
			sqlStr.append(" and ");
			sqlStr.append(" state = ");
			sqlStr.append(" :state ");
		}
		SQL sql = SQL.getSQL(sqlStr);
		sql.setInteger("clothSize", clothSize);

		if (state != null) {
			sql.setInteger("state", state);
		}

		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateStateByWarNumber(String warNumber, String state) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update hos_operation o  ");
		sqlStr.append(" set ");
		sqlStr.append(" o.state = :state   ");
		sqlStr.append(" where 1=1 ");

		sqlStr.append(" and o.id in ");
		sqlStr.append(" ( ");
		sqlStr.append(" select ");
		sqlStr.append(" s.operationId ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_ope_wardrobe s ");
		sqlStr.append(" where ");
		sqlStr.append(" warNumber = :warNumber ");
		sqlStr.append(" ) ");

		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("warNumber", warNumber);
		sql.setVarChar("state", state);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> selectHosOperationList(String theNumber) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" ho.id, ");
		sqlStr.append(" ho.number, ");
		sqlStr.append(" ho.count, ");
		sqlStr.append(" ho.state, ");
		sqlStr.append(" ho.clothSize, ");
		sqlStr.append(" d.name ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_operation ho ");
		sqlStr.append(" LEFT JOIN ");
		sqlStr.append(" dictionary d ");
		sqlStr.append(" ON ");
		sqlStr.append(" ho.clothSize = d.code ");
		sqlStr.append(" where ");
		sqlStr.append(" d.type = 'cloth' ");
		sqlStr.append(" and ");
		sqlStr.append(" ho.theNumber=:theNumber ");
		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("theNumber", theNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}

	public List<ClassConvertDict> selectHosOperation() throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" SELECT ");
		sqlStr.append(" ho.clothSize as code, ");
		sqlStr.append(" d.name as name ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_operation ho ");
		sqlStr.append(" LEFT JOIN ");
		sqlStr.append(" dictionary d ");
		sqlStr.append(" ON ");
		sqlStr.append(" ho.clothSize = d.code ");
		sqlStr.append(" where ");
		sqlStr.append(" d.type = 'cloth' ");

		sql = SQL.getSQL(sqlStr);

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(ClassConvertDict.class);
	}

	public List<Map<String, Object>> selectHosOperationLists(Map<String, Object> params) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT                               ");
		sqlStr.append("	o.*, (                              ");
		sqlStr.append("		SELECT                          ");
		sqlStr.append("			sum(w.alloutCount)          ");
		sqlStr.append("		FROM                            ");
		sqlStr.append("			hos_wardrobe_container w    ");
		sqlStr.append("		WHERE                           ");
		sqlStr.append("			w.opeSize = o.clothSize     ");
		sqlStr.append("	) AS fp,                            ");
		sqlStr.append("	(                                   ");
		sqlStr.append("		SELECT                          ");
		sqlStr.append("			sum(1)                      ");
		sqlStr.append("		FROM                            ");
		sqlStr.append("			hos_recovery_goods rg       ");
		sqlStr.append("		WHERE                           ");
		sqlStr.append("			rg.size = o.clothSize       ");
		sqlStr.append("		AND rg.state = :state0           ");
		sqlStr.append("		AND rg.type = :type              ");
		sqlStr.append("	) AS syz,                           ");
		sqlStr.append("	(                                   ");
		sqlStr.append("		SELECT                          ");
		sqlStr.append("			SUM(1)                      ");
		sqlStr.append("		FROM                            ");
		sqlStr.append("			hos_recycle_container hrc   ");
		sqlStr.append("		WHERE                           ");
		sqlStr.append("			hrc.size = o.clothSize      ");
		sqlStr.append("		AND hrc.recycleId IN (          ");
		sqlStr.append("			SELECT                      ");
		sqlStr.append("				hr.id                   ");
		sqlStr.append("			FROM                        ");
		sqlStr.append("				hos_recycle hr          ");
		sqlStr.append("			WHERE                       ");
		sqlStr.append("			1=1                         ");
		sqlStr.append("			AND	hr.theNumber = :number  ");
		sqlStr.append("		)                               ");
		sqlStr.append("	) AS hsz                            ");
		sqlStr.append("FROM                                 ");
		sqlStr.append("	hos_operation o                     ");
		sqlStr.append("WHERE                                ");
		sqlStr.append("	1=1                                 ");
		sqlStr.append("	and o.theNumber = :number           ");

		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("state0", BizConst.STATE.NOTUSE);
		sql.setVarChar("type", BizConst.DEVICE_TYPE.WARDRODE);
		sql.setVarChar("number", (String) params.get("listRoomNumber"));
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

	}
}
