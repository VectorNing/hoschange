package com.sesxh.hoschange.biz.tmg.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.common.db.SQL;
import com.sesxh.common.db.SQLExecutor;
import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.base.SesframeBaseDao;
import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerDao;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;

@Repository
public class TaskSchedularDaoImpl extends SesframeBaseDao implements TaskSchedulerDao {

	/**
	 * 锁住任务调度计划
	 * 
	 * @throws FrameException
	 */
	@Override
	public boolean lockTask(String taskId) throws FrameException {
		String sqlstr = " update task_scheduler set tsid=tsid where tsid= '" + taskId + "'";
		SQL sql = SQL.getSQL(sqlstr);
		try {
			SQLExecutor sqlExecutor = this.getSession().getSQLExecutor();
			sqlExecutor.setSQL(sql);
			sqlExecutor.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取任务调度
	 * 
	 * @throws FrameException
	 */
	@Override
	public List<TaskScheduler> getTaskSchedulerById(String taskId) throws BaseException {
		List<TaskScheduler> list = new ArrayList<TaskScheduler>();
		String sqlstr = "select * from task_scheduler where tsid='" + taskId + "'";
		SQL sql = SQL.getSQL(sqlstr);
		SQLExecutor sqlExecutor;
		sqlExecutor = this.getSession().getSQLExecutor();
		sqlExecutor.setSQL(sql);
		list = sqlExecutor.executeQueryBeanList(TaskScheduler.class);
		return list;
	}

	/**
	 * 获取appid对应的所有任务调度
	 * 
	 * @throws FrameException
	 */
	@Override
	public List<TaskScheduler> getTaskSchedulerByAppId(String appId) throws BaseException {
		List<TaskScheduler> list = new ArrayList<TaskScheduler>();
		String sqlstr = "select * from task_scheduler where appid='" + appId + "'";
		SQL sql = SQL.getSQL(sqlstr);
		SQLExecutor sqlExecutor = this.getSession().getSQLExecutor();
		sqlExecutor.setSQL(sql);
		list = sqlExecutor.executeQueryBeanList(TaskScheduler.class);
		return list;
	}

	@Override
	public String getTaskCount(TaskScheduler taskinfo) throws FrameException {
		String para = null;
		StringBuffer sqlSb = new StringBuffer();
		List<Map<String, Object>> count = new ArrayList<Map<String, Object>>();
		SQL sql;
		SQLExecutor sqlExecutor;

		para = taskinfo.getAppid();
		sqlSb.append("select COUNT(1) FROM task_scheduler s WHERE 1=1 ");
		if (para != null && !"".equals(para)) {
			sqlSb.append(" and s.appid like '%" + para + "%'");
		}
		sql = SQL.getSQL(sqlSb);
		sqlExecutor = this.getSession().getSQLExecutor();
		sqlExecutor.setSQL(sql);
		count = sqlExecutor.executeQuery();
		return count.get(0).get("COUNT(1)").toString();
	}

	@Override
	public List<TaskScheduler> queryTaskList(TaskScheduler taskinfo, int pageSize, int startRow) throws FrameException {
		String para = null;
		StringBuffer sqlSb = new StringBuffer();
		List<TaskScheduler> list = new ArrayList<TaskScheduler>();
		SQL sql;
		SQLExecutor sqlExecutor;

		para = taskinfo.getRwmc();
		sqlSb.append("select * FROM task_scheduler as s WHERE 1=1 ");
		if (para != null && !"".equals(para)) {
			sqlSb.append(" and s.rwmc like '%" + para + "%'");
		}
		sql = SQL.getSQL(sqlSb);
		sqlExecutor = this.getSession().getSQLExecutor();
		sqlExecutor.setSQL(sql);
		list = sqlExecutor.executeQueryBeanList(TaskScheduler.class, startRow, startRow + pageSize);
		return list;
	}

	/**
	 * 新增一个任务
	 */
	@Override
	public void addTask(TaskScheduler taskinfo) throws BaseException {
		SQLExecutor sqlExecutor;
		SQL sql;
		String sqlstr = "insert into task_scheduler(tsid,appid,rwid,rwmc,rwnrlx,rwnr,zxjh,rwcs,yxbz ) "
				+ "values (:tsid,:appid,:rwid,:rwmc,:rwnrlx,:rwnr,:zxjh,:rwcs,:yxbz)";
		if ((taskinfo.getTsid() == null) || "".equals(taskinfo.getTsid())) {
			taskinfo.setRwid("");
			taskinfo.setTsid("");
		}
		sql = SQL.getSQL(sqlstr);
		sql.setParas(taskinfo);
		sqlExecutor = this.getSession().getSQLExecutor();
		sqlExecutor.setSQL(sql);
		sqlExecutor.executeUpdate();
	}

	/**
	 * 注销/启动任务
	 */
	@Override
	public void modTaskYxbz(String tsid, String yxbz) throws BaseException {
		SQLExecutor sqlExecutor;
		String sqlstr = "update task_scheduler set yxbz='" + yxbz + "' where tsid='" + tsid + "'";
		SQL sql = SQL.getSQL(sqlstr);
		sqlExecutor = this.getSession().getSQLExecutor();
		sqlExecutor.setSQL(sql);
		sqlExecutor.executeUpdate();
	}
}
