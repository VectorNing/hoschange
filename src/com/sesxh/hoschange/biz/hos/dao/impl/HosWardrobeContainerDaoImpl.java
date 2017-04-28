package com.sesxh.hoschange.biz.hos.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosWardrobeContainerDao;
import com.sesxh.hoschange.biz.hos.entity.HosWardrobeContainer;
import com.sesxh.hoschange.common.data.ParamMap;

@Repository
public class HosWardrobeContainerDaoImpl extends SesframeBaseDao implements HosWardrobeContainerDao{

	@Override
	public int insertHosWardrobeContainer(HosWardrobeContainer hosWardrobeContainer) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_wardrobe_container ");
		sqlStr.append(" ( ");
		sqlStr.append(" warNumber, ");
		sqlStr.append(" trayNumber, ");
		sqlStr.append(" trayTotal ");
		sqlStr.append(" ) ");
		sqlStr.append(" values ");
		sqlStr.append(" ( ");
		sqlStr.append(" :warNumber,");
		sqlStr.append(" :trayNumber, ");
		sqlStr.append(" :trayTotal ");
		sqlStr.append(" ) ");
		sql =  SQL.getSQL(sqlStr);
		sql.setParas(hosWardrobeContainer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}


	@Override
	public List<HosWardrobeContainer> selectHosWardrobeContainerByWarNumber(String warNumber) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT ");
			sqlStr.append(" wc.id, ");
			sqlStr.append(" wc.warNumber, ");
			sqlStr.append(" wc.trayNumber, ");
			sqlStr.append(" wc.opeSize, ");
			sqlStr.append(" wc.alloutCount, ");
			sqlStr.append(" wc.trayTotal ");
			sqlStr.append(" from ");
			sqlStr.append(" hos_wardrobe_container wc ");
			sqlStr.append(" where 1=1 ");
		
		if (warNumber != null) {
			sqlStr.append(" and warNumber = :warNumber ");
		}
		sql =  SQL.getSQL(sqlStr);
		if (warNumber != null) {
			sql.setVarChar("warNumber", warNumber);
		}
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBeanList(HosWardrobeContainer.class);
	}
	
	public int updateContainerByWarNumber(String oldNumber,String newNumber) throws BaseException {
		SQL sql =  SQL.getSQL("update hos_wardrobe_container set warNumber=:newNumber where warNumber=:oldNumber ");
		sql.setVarChar("oldNumber", oldNumber);
		sql.setVarChar("newNumber", newNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	public int deleteContainerByWarNumber(String number) throws BaseException {
		SQL sql =  SQL.getSQL("delete from hos_wardrobe_container where  warNumber=:number ");
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	public int insertHosOpeWardbe(ParamMap paramMap) throws FrameException{
		StringBuffer sqlStr = new StringBuffer();

		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_ope_wardrobe ");
		sqlStr.append(" ( ");
		sqlStr.append(" warNumber, ");
		sqlStr.append(" trayNumber, ");
		sqlStr.append(" opeSize, ");
		sqlStr.append(" operationId ");
		sqlStr.append(" ) ");
		sqlStr.append(" values ");
		sqlStr.append(" ( ");
		sqlStr.append(" warNumber = :warNumber, ");
		sqlStr.append(" trayNumber = :trayNumber, ");
		sqlStr.append(" opeSize = :opeSize, ");
		sqlStr.append(" operationId = :operationId ");
		sqlStr.append(" ) ");
		SQL sql =  SQL.getSQL(sqlStr);
		if (paramMap.getStrParam("clothSize") != null) {
			sqlStr.append(" and clothSize = :clothSize ");
		}
		sql.setVarChar("warNumber", paramMap.getStrParam("warNumber"));
		sql.setVarChar("id", paramMap.getStrParam("trayNumber"));
		sql.setVarChar("id", paramMap.getStrParam("opeSize"));
		sql.setVarChar("id", paramMap.getStrParam("opeSizeName"));
		sql.setVarChar("id", paramMap.getStrParam("operationId"));
		sql.setVarChar("id", paramMap.getStrParam("clothSize"));
		
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	@Override
	public int updateContainerByClothSizeAndtheNumber(String clothSize,String theNumber) throws BaseException {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" UPDATE hos_wardrobe_container      ");
		sqlStr.append(" SET opeSize = 0,                   ");
		sqlStr.append("  alloutCount = 0                   ");
		sqlStr.append(" WHERE                              ");
		sqlStr.append(" 	opeSize = :opeSize             ");
		sqlStr.append(" AND warNumber IN (                 ");
		sqlStr.append(" 	SELECT                         ");
		sqlStr.append(" 		number                     ");
		sqlStr.append(" 	FROM                           ");
		sqlStr.append(" 		hos_wardrobe               ");
		sqlStr.append(" 	WHERE                          ");
		sqlStr.append(" 		theaterNumber = :theNumber)");

		SQL sql = SQL.getSQL(sqlStr);
		sql.setVarChar("opeSize", clothSize);
		sql.setVarChar("theNumber", theNumber);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	@Override
	public int emptyContainerById(Integer id) throws BaseException {
		SQL sql =  SQL.getSQL("update hos_Wardrobe_container set alloutCount = 0,opeSize = null where id = :id ");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	@Override
	public int updateContainerById(String id,Integer alloutCount,String opeSize) throws BaseException {
		SQL sql =  SQL.getSQL("update hos_Wardrobe_container set alloutCount = :alloutCount,opeSize = :opeSize where id = :id ");
		sql.setVarChar("id", id);
		sql.setInteger("alloutCount", alloutCount);
		sql.setVarChar("opeSize", opeSize);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public HosWardrobeContainer selectContainerById(Integer id) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();

			sqlStr.append(" SELECT ");
			sqlStr.append(" id, ");
			sqlStr.append(" warNumber, ");
			sqlStr.append(" trayNumber, ");
			sqlStr.append(" trayTotal, ");
			sqlStr.append(" alloutCount, ");
			sqlStr.append(" opeSize ");
			sqlStr.append(" from ");
			sqlStr.append(" hos_wardrobe_container ");
			sqlStr.append(" where 1=1 ");
			sqlStr.append(" and  ");
			sqlStr.append(" id = :id ");

		
		sql =  SQL.getSQL(sqlStr);

		sql.setInteger("id", id);
		
		return this.getSession().getSQLExecutor().setSQL(sql).executeQueryBean(HosWardrobeContainer.class);
		
	}
	
	public int updateContainerById(HosWardrobeContainer HosWardrobeContainer) throws BaseException{
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_Wardrobe_container ");
		sqlStr.append(" set ");
		sqlStr.append(" trayNumber=:trayNumber ");
		sqlStr.append(" where ");
		sqlStr.append(" id = :id ");
		sql =  SQL.getSQL(sqlStr);
		sql.setParas(HosWardrobeContainer);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	public List<Map<String,Object>> selectContainerByNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" id ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe_container ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" warNumber=:number ");
		sql =  SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}
	
	public List<Map<String,Object>> selectContainerByThNumber(String number) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select ");
		sqlStr.append(" ww.id ");
		sqlStr.append(" from ");
		sqlStr.append(" hos_wardrobe_container ww");
		sqlStr.append(" left join ");
		sqlStr.append(" hos_wardrobe w ");
		sqlStr.append(" on ww.warNumber=w.number ");
		sqlStr.append(" where 1=1 ");
		sqlStr.append(" and ");
		sqlStr.append(" w.theaterNumber=:number ");
		sql =  SQL.getSQL(sqlStr);
		sql.setVarChar("number", number);
		return this.getSession().getSQLExecutor().setSQL(sql).executeQuery();
	}
	
	public int updateContainerByWarTarNumber(Integer id) throws BaseException{
		SQL sql =  SQL.getSQL("update hos_Wardrobe_container set alloutCount = (alloutCount-1) where id=:id ");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int settingOperationSizeForContainer(Integer id, String opeSize) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_Wardrobe_container ");
		sqlStr.append(" set ");
		sqlStr.append(" opeSize=:opeSize ");
		sqlStr.append(" where ");
		sqlStr.append(" id = :id ");
		sql =  SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setVarChar("opeSize", opeSize);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

	@Override
	public int updateAlloutCount(Integer id, Integer count) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" update ");
		sqlStr.append(" hos_Wardrobe_container ");
		sqlStr.append(" set ");
		sqlStr.append(" alloutCount = (alloutCount + :count) ");
		sqlStr.append(" where ");
		sqlStr.append(" id = :id ");
		sql =  SQL.getSQL(sqlStr);
		sql.setInteger("id", id);
		sql.setInteger("count", count);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}
	
	public int emptyContainerCountById(Integer id) throws BaseException {
		SQL sql =  SQL.getSQL("update hos_Wardrobe_container set alloutCount = 0 where id = :id ");
		sql.setInteger("id", id);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}


	@Override
	public int updateHosWardrobeContainer(HosWardrobeContainer hoswardrobeContainer) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}
}
