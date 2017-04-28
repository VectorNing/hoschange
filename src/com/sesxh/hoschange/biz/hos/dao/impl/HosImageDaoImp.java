package com.sesxh.hoschange.biz.hos.dao.impl;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.hos.dao.HosImageDao;
import com.sesxh.hoschange.common.data.Image;
@Repository
public class HosImageDaoImp extends SesframeBaseDao implements HosImageDao {

	@Override
	public int insertImage(Image image) throws BaseException {
		SQL sql;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" insert ");
		sqlStr.append(" into ");
		sqlStr.append(" hos_recovery_goods_pic( ");
		sqlStr.append(" date, ");
		sqlStr.append(" filePath, ");
		sqlStr.append(" hrgid ");
		sqlStr.append(" ) values( ");
		sqlStr.append(" :date, ");
		sqlStr.append(" :filePath, ");
		sqlStr.append(" :hrgid ");
		sqlStr.append(" ) ");
		sql = SQL.getSQL(sqlStr);
		sql.setParas(image);
		return this.getSession().getSQLExecutor().setSQL(sql).executeUpdate();
	}

}
