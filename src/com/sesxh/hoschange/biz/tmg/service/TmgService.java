package com.sesxh.hoschange.biz.tmg.service;

import java.util.List;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;

public interface TmgService {

	public String getTaskCount(TaskScheduler taskinfo) throws BaseException;

	public List<TaskScheduler> queryTaskList(TaskScheduler taskinfo, int pagesize, int startRow) throws BaseException;

	public void addTask(TaskScheduler taskinfo) throws BaseException;

	public TaskScheduler getTaskinfoByTsid(String tsid) throws BaseException;

	public void updateTask(TaskScheduler taskinfo) throws BaseException;

	public void modTaskYxbz(String tsid, String yxbz) throws BaseException;

}
