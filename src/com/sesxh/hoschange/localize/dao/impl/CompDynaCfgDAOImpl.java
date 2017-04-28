package com.sesxh.hoschange.localize.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.localize.dao.CompDynaCfgDAO;
import com.sesxh.hoschange.localize.entity.CompDynaCfg;

/**
 * 动态组件多版本实现Dao层实现类
 */
@Repository
public class CompDynaCfgDAOImpl extends SesframeBaseDao implements
		CompDynaCfgDAO {

	@Override
	public List<CompDynaCfg> getAllCompDynaCfg() throws BaseException {
		SQL sql = SQL.getSQL("select * from comp_dyna_cfg ");
		SQLExecutor sqlExecutor = this.getSession().getSQLExecutor()
				.setSQL(sql);
		sqlExecutor.setSQL(sql);
		List<CompDynaCfg> list = sqlExecutor
				.executeQueryBeanList(CompDynaCfg.class);
		return list;
	}
}