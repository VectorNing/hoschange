package com.sesxh.hoschange.biz.tmg.core;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.biz.tmg.core.plan.ExecutePlanUtil;
import com.sesxh.hoschange.biz.tmg.core.plan.SesTaskTrigger;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;
import com.sesxh.hoschange.global.ComponentQualifier;
import com.sesxh.hoschange.global.CodeNames.YXBZ;

/**
 * 任务调度程序
 */
@Component(ComponentQualifier.Tmg.sesTaskScheduler)
public class SesTaskScheduler {
	@Autowired
	private TmgConfig tmgCfg;

	Scheduler scheduler;
	ApplicationContext applicationContext;

	public SesTaskScheduler() throws FrameException {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			throw new FrameException(e);
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Scheduler getInstanceScheduler() {
		return scheduler;
	}

	/**
	 * 启动一个调度对象
	 * 
	 * @throws SchedulerException
	 */
	public void start() throws SchedulerException {
		scheduler.start();
	}

	/**
	 * 检查调度是否启动
	 * 
	 * @return boolean
	 * @throws SchedulerException
	 */
	public boolean isStarted() throws SchedulerException {
		return scheduler.isStarted();
	}

	/**
	 * 关闭调度信息
	 * 
	 * @throws SchedulerException
	 */
	public void shutdown() throws SchedulerException {
		scheduler.shutdown();
	}

	/**
	 * 停止调度Job任务
	 * 
	 * @param triggerkey
	 * @return boolean
	 * @throws SchedulerException
	 */
	public boolean unscheduleJob(TriggerKey triggerkey) throws SchedulerException {
		return scheduler.unscheduleJob(triggerkey);
	}

	/**
	 * 启用一个job
	 * 
	 * @param taskId
	 *            String
	 * @throws SchedulerException
	 */
	public void resumeJob(String taskId) throws SchedulerException {
		JobKey jobkey = JobKey.jobKey(taskId, tmgCfg.defaultTaskGroup);
		scheduler.resumeJob(jobkey);
	}

	/**
	 * 暂停一个job
	 * 
	 * @param taskId
	 * @throws SchedulerException
	 */
	public void pauseJob(String taskId) throws SchedulerException {
		JobKey jobkey = JobKey.jobKey(taskId, tmgCfg.defaultTaskGroup);
		scheduler.pauseJob(jobkey);
	}

	/**
	 * 添加相关的job任务
	 * 
	 * @param taskScheduler
	 * @param flag
	 * @throws SchedulerException
	 */

	public void addJob(TaskScheduler taskScheduler, boolean flag) throws SchedulerException {
		String taskId = taskScheduler.getTsid();
		JobDetail jobdetail = JobBuilder.newJob(SesTmgJob.class).withIdentity(taskId, tmgCfg.defaultTaskGroup).build();
		scheduler.addJob(jobdetail, flag);
	}

	/**
	 * 删除相关的job任务
	 * 
	 * @param taskId
	 * @return boolean
	 * @throws FrameException
	 * @throws SchedulerException
	 */
	public boolean deleteJob(String taskId) throws FrameException {
		try {
			JobKey jobkey = JobKey.jobKey(taskId, tmgCfg.defaultTaskGroup);
			return scheduler.deleteJob(jobkey);
		} catch (SchedulerException e) {
			throw new FrameException(e);
		}
	}

	/**
	 * 调度指定的任务
	 * 
	 * @param taskScheduler
	 *            TaskScheduler
	 * @throws FrameException
	 */
	public void scheduleTask(TaskScheduler taskScheduler) throws FrameException {
		String taskId, yxbz;
		JobDetail job = null;
		List<SesTaskTrigger> triggerList;
		CronTrigger trigger;
		SesTaskTrigger sesTaskTrigger;
		try {
			taskId = taskScheduler.getTsid();
			yxbz = taskScheduler.getYxbz();
			if (yxbz.equals(YXBZ.YX)) {// 有效标志
				job = JobBuilder.newJob(SesTmgJob.class).withIdentity(taskId, tmgCfg.defaultTaskGroup).build();
				job.getJobDataMap().put("taskscheduler", taskScheduler);
				job.getJobDataMap().put("applicationContext", this.applicationContext);
				triggerList = ExecutePlanUtil.getTriggerList(taskScheduler.getZxjh());
				for (int i = 0; i < triggerList.size(); i++) {
					sesTaskTrigger = triggerList.get(i);

					trigger = TriggerBuilder.newTrigger()
							.withIdentity(tmgCfg.triggerNamePrefix + taskId + i, tmgCfg.defaultTriggerGroup)
							.withSchedule(CronScheduleBuilder.cronSchedule(sesTaskTrigger.getCron())).build();
					job.getJobDataMap().put("trigger", sesTaskTrigger);
					this.scheduler.scheduleJob(job, trigger);
					this.scheduler.start();
				}
			}
		} catch (Exception e) {
			throw new FrameException(e);
		}
	}
}
