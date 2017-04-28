package com.sesxh.hoschange.biz.tmg.dao;

import com.sesxh.base.BaseException;

public interface TaskSchedulerLogDao {
	// 新增一个日志记录
	public String addTaskLog(String taskId) throws BaseException;

	// 更新日志记录为成功/失败
	public void updateTaskLog(String taskId, String cgbz, String sm) throws BaseException;
}
