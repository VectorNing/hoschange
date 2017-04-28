package com.sesxh.hoschange.biz.tmg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesxh.base.BaseException;
import com.sesxh.common.util.BeanUtils;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.tmg.bpo.TmgBPO;
import com.sesxh.hoschange.biz.tmg.core.plan.ExecutePlanUtil;
import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerDao;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;
import com.sesxh.hoschange.biz.tmg.service.TmgService;

@Service("com.sesxh.sesdep.tmg.service.impl.TmgServiceImpl")
public class TmgServiceImpl extends SesframeBaseService implements TmgService {
	@Autowired
	private TaskSchedulerDao taskschedao;
	@Autowired
	private TmgBPO tmgbpo;

	@Override
	public String getTaskCount(TaskScheduler taskinfo) throws BaseException {
		return taskschedao.getTaskCount(taskinfo);
	}

	@Override
	public List<TaskScheduler> queryTaskList(TaskScheduler taskinfo, int pagesize, int startRow) throws BaseException {
		List<TaskScheduler> copylist = null;
		List<TaskScheduler> list = taskschedao.queryTaskList(taskinfo, pagesize, startRow);
		copylist = BeanUtils.cloneList(list);
		return copylist;
	}

	@Override
	public void addTask(TaskScheduler taskinfo) throws BaseException {
		tmgbpo.addTask(taskinfo);
	}

	@Override
	public TaskScheduler getTaskinfoByTsid(String tsid) throws BaseException {
		List<TaskScheduler> list = taskschedao.getTaskSchedulerById(tsid);
		if (list != null && list.size() > 0) {
			TaskScheduler taskinfo = list.get(0);
			TaskScheduler copy = null;
			copy = BeanUtils.cloneBean(taskinfo);
			String cron = ExecutePlanUtil.getTriggerList(copy.getZxjh()).get(0).getCron();
			copy.setZxjh(cron);
			return copy;
		}
		return null;
	}

	@Override
	public void updateTask(TaskScheduler taskinfo) throws BaseException {
		tmgbpo.updateTask(taskinfo);
	}

	@Override
	public void modTaskYxbz(String tsid, String yxbz) throws BaseException {
		tmgbpo.modTaskYxbz(tsid, yxbz);
	}
}
