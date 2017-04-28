package com.sesxh.hoschange.biz.tmg.bpo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.frame.global.SesframeGlobalNames;
import com.sesxh.hoschange.base.SesframeBaseBpo;
import com.sesxh.hoschange.biz.tmg.core.SesTaskScheduler;
import com.sesxh.hoschange.biz.tmg.core.plan.ExecutePlanUtil;
import com.sesxh.hoschange.biz.tmg.core.plan.SesTaskTrigger;
import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerDao;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;
import com.sesxh.hoschange.global.ComponentQualifier;
import com.sesxh.hoschange.global.CodeNames.YXBZ;

@Component(ComponentQualifier.Tmg.tmgBPO)
public class TmgBPO extends SesframeBaseBpo {
	@Autowired
	private TaskSchedulerDao taskschedao;

	@Autowired
	private SesTaskScheduler sesTaskScheduler;
	@Autowired
	private SesframeGlobalNames globalNames;

	/**
	 * 新增一个任务调度
	 * 
	 * @param taskinfo
	 *            TaskScheduler
	 * @throws BaseException
	 */
	public void addTask(TaskScheduler taskinfo) throws BaseException {

		List<SesTaskTrigger> triggerList = new ArrayList<SesTaskTrigger>();
		SesTaskTrigger sesTaskTrigger = new SesTaskTrigger();

		String vzxjh = taskinfo.getZxjh();
		sesTaskTrigger.setCron(vzxjh);
		sesTaskTrigger.setManualTerminate(true);
		sesTaskTrigger.setTimeout(0);
		triggerList.add(0, sesTaskTrigger);

		String zxjh = ExecutePlanUtil.getPlan(triggerList);
		taskinfo.setRwid("");
		taskinfo.setTsid("");
		taskinfo.setYxbz(YXBZ.YX);
		taskinfo.setZxjh(zxjh);
		taskinfo.setAppid(globalNames.appId);
		taskschedao.addTask(taskinfo);
		sesTaskScheduler.scheduleTask(taskinfo);
	}

	/**
	 * 更新一个任务
	 * 
	 * @param taskinfo
	 *            TaskScheduler
	 * @throws BaseException
	 */
	public void updateTask(TaskScheduler taskinfo) throws BaseException {
		List<SesTaskTrigger> triggerList = new ArrayList<SesTaskTrigger>();
		SesTaskTrigger sesTaskTrigger = new SesTaskTrigger();
		String taskId = taskinfo.getTsid();
		String vzxjh = taskinfo.getZxjh();
		sesTaskTrigger.setCron(vzxjh);
		sesTaskTrigger.setManualTerminate(true);
		sesTaskTrigger.setTimeout(0);
		triggerList.add(0, sesTaskTrigger);

		String zxjh = ExecutePlanUtil.getPlan(triggerList);
		taskinfo.setYxbz(YXBZ.YX);// 有效标志
		taskinfo.setZxjh(zxjh);
		taskschedao.addTask(taskinfo);
		sesTaskScheduler.deleteJob(taskId);
		sesTaskScheduler.scheduleTask(taskinfo);
	}

	/**
	 * 注销 或 启动一个任务
	 * 
	 * @since 2012-10-10 下午03:48:26
	 * 
	 */
	public void modTaskYxbz(String taskId, String yxbz) throws BaseException {

		taskschedao.modTaskYxbz(taskId, yxbz);
		if (yxbz.equals(YXBZ.YX)) {// 有效标志
			sesTaskScheduler.scheduleTask(taskschedao.getTaskSchedulerById(taskId).get(0));
		} else {
			sesTaskScheduler.deleteJob(taskId);
		}
	}
}
