package com.sesxh.hoschange.biz.tmg.dao;

import java.util.List;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;

public interface TaskSchedulerDao {
	public boolean lockTask(String taskId) throws FrameException;

	public List<TaskScheduler> getTaskSchedulerById(String taskId) throws BaseException;

	public List<TaskScheduler> getTaskSchedulerByAppId(String appId) throws BaseException;

	public String getTaskCount(TaskScheduler taskinfo) throws BaseException;

	public List<TaskScheduler> queryTaskList(TaskScheduler taskinfo, int pagesize, int startRow) throws BaseException;

	public void addTask(TaskScheduler taskinfo) throws BaseException;

	public void modTaskYxbz(String tsid, String yxbz) throws BaseException;

}
