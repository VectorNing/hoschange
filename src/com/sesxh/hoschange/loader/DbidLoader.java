package com.sesxh.hoschange.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sesxh.common.db.Session;
import com.sesxh.common.exception.FrameException;
import com.sesxh.frame.common.db.IdLoader;

@Component("DbidLoader")
public class DbidLoader extends IdLoader {

	@Override
	public String loadDbid(Session session) throws FrameException {
		List<DbInfo> list;
		String sql;

		sql = "select dbid from db_info where useflag='1'";
		list = session.getSQLExecutor().setSQL(sql).executeQueryBeanList(DbInfo.class);
		if (list.size() > 2) {
			throw new FrameException("数据库ID获取出错，存在多个可用值");
		}
		if (list.size() < 1) {
			throw new FrameException("数据库ID获取出错，不存在可用值");
		}
		session.close();
		return list.get(0).getDbid();
	}
}
