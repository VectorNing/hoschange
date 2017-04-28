package com.sesxh.hoschange.biz.tmg.core;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.ApplicationContext;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.FrameException;
import com.sesxh.common.util.DateUtils;
import com.sesxh.frame.common.db.SessionManager;
import com.sesxh.hoschange.biz.tmg.core.plan.SesTaskTrigger;
import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerDao;
import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerLogDao;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;

/**
 * 任务调度的具体执行类，实现Job接口
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SesTmgJob implements Job {
	/**
	 * 执行某个待调度任务
	 */
	@Override
	public void execute(JobExecutionContext context) {
		ApplicationContext applicationContext;
		JobDetail jobDetail; // 当前任务对象
		JobDataMap jobDataMap; // 当前任务参数
		TaskScheduler taskScheduler;
		SesTaskTrigger sesTaskTrigger;
		boolean manualTerminate, result;
		int timeout;
		Date endTime = null, currentTime;
		String rwnrlx, rwnr, rwcs, rzid = null;
		SessionManager sessionManager = new SessionManager();
		TaskSliceExecutor taskSliceExecutor;
		TaskSchedulerDao taskSchedulerDao;
		TaskSchedulerLogDao taskSchedulerLogDao = null;
		try {
			jobDetail = context.getJobDetail(); // 当前任务对象
			jobDataMap = jobDetail.getJobDataMap();

			if (jobDataMap == null) {
				throw new FrameException("任务参数为空！");
			}
			applicationContext = (ApplicationContext) jobDataMap.get("applicationContext");
			taskScheduler = (TaskScheduler) jobDataMap.get("taskscheduler");
			sesTaskTrigger = (SesTaskTrigger) jobDataMap.get("trigger");

			taskSliceExecutor = applicationContext.getBean(TaskSliceExecutor.class);
			taskSchedulerDao = applicationContext.getBean(TaskSchedulerDao.class);
			taskSchedulerLogDao = applicationContext.getBean(TaskSchedulerLogDao.class);
			sessionManager = (SessionManager) applicationContext.getBean(SessionManager.class.getName());

			rwnrlx = taskScheduler.getRwnrlx();
			rwnr = taskScheduler.getRwnr();
			rwcs = taskScheduler.getRwcs();

			manualTerminate = sesTaskTrigger.isManualTerminate();
			timeout = sesTaskTrigger.getTimeout();

			if (timeout > 0) {
				endTime = DateUtils.addSecond(DateUtils.getLocalDate(), timeout);
			}
			if ((!manualTerminate) && (timeout < 1)) {
				throw new FrameException("未指定任务终止方式！");
			}
			// 锁定任务行
			if (!taskSchedulerDao.lockTask(taskScheduler.getTsid())) {
				sessionManager.rollbackSessions();
				return;
			}

			// 添加日志
			rzid = taskSchedulerLogDao.addTaskLog(taskScheduler.getTsid());
			if (rzid == null || "".equals(rzid)) {
				sessionManager.rollbackSessions();
				return;
			}

			while (true) {
				result = taskSliceExecutor.execute(rwnrlx, rwnr, rwcs);
				if (manualTerminate && result) {
					break;
				}
				if (timeout > 0) {
					currentTime = DateUtils.getLocalDate();
					if (currentTime.after(endTime) || currentTime.equals(endTime)) {
						break;
					}
				}
			}

			// 任务调度成功
			taskSchedulerLogDao.updateTaskLog(rzid, "1", "调度成功");
			sessionManager.commitSessions();
		} catch (Exception e) { // 任务调度失败
			try {
				String sm = "任务失败:" + e.getMessage();
				sm = sm.length() <= 1000 ? sm : sm.substring(0, 1000);
				taskSchedulerLogDao.updateTaskLog(rzid, "0", sm);
				sessionManager.commitSessions();
			} catch (BaseException e1) {
				try {
					sessionManager.rollbackSessions();
				} catch (FrameException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
