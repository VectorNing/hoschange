package com.sesxh.hoschange.biz.tmg.dao.impl;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerLogDao;

@Repository("com.sesxh.frame.tmg.dao.impl.TaskSchedularLogDaoImpl")
public class TaskSchedularLogDaoImpl extends SesframeBaseDao implements TaskSchedulerLogDao {

	/**
	 * 新增日志
	 */
	@Override
	public String addTaskLog(String taskId) throws BaseException {
		StringBuffer sb = new StringBuffer();
		SQLExecutor sqlExecutor = this.getSession().getSQLExecutor();
		// String rzid = dBUtil.generateSysId("SEQ_SYS");
		sb.append(" insert into task_scheduler_log (tsid,qssj) values(:tsid,now())");
		SQL sql = SQL.getSQL(sb);
		sql.setVarChar("tsid", taskId);
		sqlExecutor.setSQL(sql);
		sqlExecutor.executeUpdate();
		return taskId;
	}

	/**
	 * 任务调度结束 更新日志
	 */
	@Override
	public void updateTaskLog(String rzid, String cgbz, String sm) throws BaseException {
		StringBuffer sb = new StringBuffer();
		SQLExecutor sqlExecutor = this.getSession().getSQLExecutor();
		sb.append(" update task_scheduler_log set zzsj = now(),cgbz = :cgbz,sm = :sm where rzid=:rzid ");
		SQL sql = SQL.getSQL(sb);
		sql.setVarChar("rzid", rzid);
		sql.setVarChar("cgbz", cgbz);
		sql.setVarChar("sm", sm);
		sqlExecutor.setSQL(sql);
		sqlExecutor.executeUpdate();
	}
}
