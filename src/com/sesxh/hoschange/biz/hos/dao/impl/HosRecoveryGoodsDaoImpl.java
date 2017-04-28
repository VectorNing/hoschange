package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosRecoveryGoodsDao;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.common.data.Image;
import com.sesxh.hoschange.common.data.ParamMap;
import com.sesxh.hoschange.global.BizConst;

/**
 * @title : HosRecoveryGoodsDaoImpl.java
 * @author 作者 E-mail: wwb
 * @date 创建时间：2016年10月21日 下午3:05:01
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Repository
public class HosRecoveryGoodsDaoImpl extends SesframeBaseDao implements HosRecoveryGoodsDao {

	@Override
	public int insertHosRecoveryGoods(HosRecoveryGoods hosRecoveryGoods) throws BaseException {
		// TODO Auto-generated method stub
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_recovery_goods ");
		sqlStr.append(" ( ");
		sqlStr.append(" userId, ");
		sqlStr.append(" theNumber, ");
		sqlStr.append(" type, ");
		sqlStr.append(" size, ");
		sqlStr.append(" recoveryTime, ");
		sqlStr.append(" state, ");
		sqlStr.append(" recordId, ");
		sqlStr.append(" goodsNumber ");
		sqlStr.append(" ) ");
		sqlStr.append(" values ");
		sqlStr.append(" ( ");
		sqlStr.append(" :userId, ");
		sqlStr.append(" :theNumber, ");
		sqlStr.append(" :type, ");
		sqlStr.append(" :size, ");
		sqlStr.append(" :recoveryTime, ");
		sqlStr.append(" :state, ");
		sqlStr.append(" :recordId, ");
		sqlStr.append(" :goodsNumber ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(hosRecoveryGoods);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosRecoveryGoodsStateByUserId(String state, Integer recordId, Integer userId, String type)
			throws BaseException {
		// TODO Auto-generated method stub
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_recovery_goods ");
		sqlStr.append(" set ");
		sqlStr.append(" state =  ");
		sqlStr.append(" :state, ");
		sqlStr.append(" recoveryTime =  ");
		sqlStr.append(" :recoveryTime ");
		sqlStr.append(" where ");
		sqlStr.append(" 1 = 1 ");
		sqlStr.append(" and ");
		sqlStr.append(" state = :state0 ");
		sqlStr.append(" and ");
		sqlStr.append(" recordId = :recordId ");
		sqlStr.append(" and ");
		sqlStr.append(" type = :type ");
		if (userId != null) {
			sqlStr.append(" and ");
			sqlStr.append(" userId =:userId ");
		}
		sqlStr.append(" order by id desc limit 1 ");
		sql = SQL.getSQL(sqlStr);
		if (userId != null) {
			sql.setInteger("userId", userId);
		}
		sql.setVarChar("state", state);
		sql.setVarChar("state0", BizConst.STATE.NOTUSE);
		sql.setDate("recoveryTime", new Date());
		sql.setInteger("recordId", recordId);
		sql.setVarChar("type", type);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateHosRecoveryGoodsStateById(String state, Integer id, String deviceNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_recovery_goods ");
		sqlStr.append(" set ");
		sqlStr.append(" state =  ");
		sqlStr.append(" :state ");
		if (deviceNumber != null) {
			sqlStr.append(" ,deviceNumber =  ");
			sqlStr.append(" :deviceNumber ");
		}
		sqlStr.append(" where ");
		sqlStr.append(" 1 = 1 ");
		sqlStr.append(" and id =  ");
		sqlStr.append(" :id ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("state", state);
		if (deviceNumber != null) {
			sql.setVarChar("deviceNumber", deviceNumber);
		}

		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosRecoveryGoodsByTypeAndSize(String type, Integer size) throws BaseException {
		// TODO Auto-generated method stub
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" delete ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_recovery_goods ");
		sqlStr.append(" where ");
		sqlStr.append(" type = :type ");
		sqlStr.append(" and size = :size ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("type", type);
		sql.setInteger("size", size);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int deleteHosRecoveryGoodsByIdAndType(String type, Integer id) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" DELETE          ");
		sqlStr.append(" FROM            ");
		sqlStr.append(" 	hos_recovery_goods  ");
		sqlStr.append(" WHERE           ");
		sqlStr.append("     1 = 1 ");
		sqlStr.append(" 	and type = :type    ");
		sqlStr.append(" 	and recordId = :id    ");
		SQL sql;
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("type", type);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosRecoveryGoods selectHosRecoveryGoodsByUserId(Integer userId) throws BaseException {
		// TODO Auto-generated method stub
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" id, ");
		sqlStr.append(" userId, ");
		sqlStr.append(" theNumber, ");
		sqlStr.append(" type, ");
		sqlStr.append(" size, ");
		sqlStr.append(" state, ");
		sqlStr.append(" recoveryTime, ");
		sqlStr.append(" goodsNumber ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_recovery_goods ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");
		if (userId != null) {
			sqlStr.append(" and userId = :userId ");
		}

		SQL sql = SQL.getSQL(sqlStr);
		if (userId != null) {
			sql.setInteger("userId", userId);
		}

		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecoveryGoods.class);
	}

	@Override
	public List<HosRecoveryGoods> selectHosRecoveryGoodsByState(String thNumber, String state) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" id, ");
		sqlStr.append(" userId, ");
		sqlStr.append(" theNumber, ");
		sqlStr.append(" type, ");
		sqlStr.append(" size, ");
		sqlStr.append(" state, ");
		sqlStr.append(" recoveryTime, ");
		sqlStr.append(" goodsNumber ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_recovery_goods ");
		sqlStr.append(" where ");
		sqlStr.append(" 1 = 1 ");
		if (!"".equals(state) && state != null) {
			sqlStr.append(" and state = :state ");
		}
		if (!"".equals(thNumber) && thNumber != null) {
			sqlStr.append(" and thNumber = :thNumber ");
		}
		SQL sql = SQL.getSQL(sqlStr);
		if (!"".equals(state) && state != null) {
			sql.setVarChar("state", state);
		}
		if (!"".equals(thNumber) && thNumber != null) {
			sql.setVarChar("thNumber", thNumber);
		}
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosRecoveryGoods.class);
	}

	@Override
	public List<Map<String, Object>> selectHosRecoveryGoods(ParamMap paramMap) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" hrg.id, ");
		sqlStr.append(" hrg.userId, ");
		sqlStr.append(" hrg.theNumber, ");
		sqlStr.append(" aud.userName, ");
		sqlStr.append(" ht.name, ");
		sqlStr.append(" hrg.type, ");
		sqlStr.append(" hrg.size, ");
		sqlStr.append(" hrg.state, ");
		sqlStr.append(" hrg.recoveryTime, ");
		sqlStr.append(" hrg.goodsNumber ");
		sqlStr.append(" FROM ");
		sqlStr.append(" hos_recovery_goods hrg ");
		sqlStr.append(" left join auth_user_detail aud");
		sqlStr.append(" on  ");
		sqlStr.append(" hrg.userId =  aud.userId ");
		sqlStr.append(" left join hos_theater ht");
		sqlStr.append(" on  ");
		sqlStr.append(" hrg.theNumber = ht.number ");
		sqlStr.append(" where ");
		sqlStr.append(" 1=1 ");

		if (paramMap.getStrParam("type") != null) {
			sqlStr.append(" and hrg.type = :type ");
		}
		if (paramMap.getStrParam("state") != null) {
			sqlStr.append(" and hrg.state = :state ");
		}
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and ht.name like :name ");
		}
		if (paramMap.getStrParam("userName") != null) {
			sqlStr.append(" and aud.userName like :userName ");
		}
		sqlStr.append(" limit :start ,:size");
		SQL sql = SQL.getSQL(sqlStr);
		if (paramMap.getStrParam("type") != null) {
			sql.setVarChar("type", paramMap.getStrParam("type"));
		}
		if (paramMap.getStrParam("state") != null) {
			sql.setVarChar("state", paramMap.getStrParam("state"));
		}
		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		if (paramMap.getStrParam("userName") != null) {
			sql.setVarChar("userName", paramMap.getStrParam("userName"));
		}
		sql.setInteger("start", paramMap.getStart());
		sql.setInteger("size", paramMap.getSize());
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();

	}

	@Override
	public HosRecoveryGoods selectHosRecoveryGoodsByIdAndType(Integer id, String deviceType) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                 ");
		sqlStr.append(" 	*                  ");
		sqlStr.append(" FROM                   ");
		sqlStr.append(" 	hos_recovery_goods ");
		sqlStr.append(" WHERE                  ");
		sqlStr.append(" 	1 = 1              ");
		sqlStr.append(" AND recordId = :id     ");
		sqlStr.append(" AND type = :deviceType       ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("deviceType", deviceType);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecoveryGoods.class);

	}

	@Override
	public Long HosRecoveryGoodsCount(ParamMap paramMap) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT ");
		sqlStr.append(" 1 ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_recovery_goods hrg ");
		sqlStr.append(" left join auth_user_detail aud");
		sqlStr.append(" on  ");
		sqlStr.append(" hrg.userId =  aud.userId ");
		sqlStr.append(" left join hos_theater ht");
		sqlStr.append(" on  ");
		sqlStr.append(" hrg.theNumber = ht.number ");
		sqlStr.append(" where 1=1 ");

		if (paramMap.getStrParam("type") != null) {
			sqlStr.append(" and hrg.type = :type ");
		}
		if (paramMap.getStrParam("state") != null) {
			sqlStr.append(" and hrg.state = :state ");
		}
		if (paramMap.getStrParam("name") != null) {
			sqlStr.append(" and ht.name like :name ");
		}
		if (paramMap.getStrParam("userName") != null) {
			sqlStr.append(" and aud.userName like :userName ");
		}

		sql = SQL.getSQL(sqlStr);

		if (paramMap.getStrParam("type") != null) {
			sql.setVarChar("type", paramMap.getStrParam("type"));
		}
		if (paramMap.getStrParam("state") != null) {
			sql.setVarChar("state", paramMap.getStrParam("state"));
		}
		if (paramMap.getStrParam("name") != null) {
			sql.setVarChar("name", paramMap.getStrParam("name"));
		}
		if (paramMap.getStrParam("userName") != null) {
			sql.setVarChar("userName", paramMap.getStrParam("userName"));
		}

		SQLExecutor SQLExecutor = this.getSession().getSQLExecutor().setSQL(sql);

		Integer count = SQLExecutor.executeQueryRowCount();

		return count.longValue();
	}

	@Override
	public List<HosRecoveryGoods> selectHosRecoveryGoodsById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                 ");
		sqlStr.append(" 	*                  ");
		sqlStr.append(" FROM                   ");
		sqlStr.append(" 	hos_recovery_goods ");
		sqlStr.append(" WHERE                  ");
		sqlStr.append(" 	1 = 1              ");
		sqlStr.append(" AND recordId = :id     ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosRecoveryGoods.class);
	}

	@Override
	public HosRecoveryGoods selectHosRecoveryGoodsByDeviceNumberAndReadyCallBack(String deviceNumber,
			String readyCallBack) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                 ");
		sqlStr.append(" 	*                  ");
		sqlStr.append(" FROM                   ");
		sqlStr.append(" 	hos_recovery_goods ");
		sqlStr.append(" WHERE                  ");
		sqlStr.append(" 	1 = 1              ");
		sqlStr.append(" AND deviceNumber = :deviceNumber     ");
		sqlStr.append(" AND state = :state       ");
		sql = SQL.getSQL(sqlStr);
		sql.setVarChar("deviceNumber", deviceNumber);
		sql.setVarChar("state", readyCallBack);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosRecoveryGoods.class);
	}

	@Override
	public List<Image> selectImageByRecoveryGoodsID(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" SELECT                 ");
		sqlStr.append(" 	*                  ");
		sqlStr.append(" FROM                   ");
		sqlStr.append(" 	hos_recovery_goods_pic ");
		sqlStr.append(" WHERE                  ");
		sqlStr.append(" 	1 = 1              ");
		sqlStr.append(" AND hrgid = :id        ");
		sqlStr.append(" ORDER BY               ");
		sqlStr.append(" date ASC               ");
		sql = SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(Image.class);
	}
}
