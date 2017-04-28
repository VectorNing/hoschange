package com.sesxh.hoschange.biz.tmg.core;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sesxh.hoschange.biz.tmg.dao.TaskSchedulerDao;
import com.sesxh.hoschange.biz.tmg.model.TaskScheduler;
import com.sesxh.hoschange.global.ComponentQualifier;

/**
 * 任务调度加载类，实现ApplicationContextAware接口
 */
@Component(ComponentQualifier.Tmg.tmgLoader)
public class TmgLoader implements ApplicationContextAware {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected SesTaskScheduler sesTaskScheduler;

	@Autowired
	protected TaskSchedulerDao taskSchedulerDao;

	@Autowired
	protected TmgConfig tmgCfg;

	protected ApplicationContext applicationContext;

	/**
	 * 执行加载
	 */
	@PostConstruct
	public void load() {
		List<TaskScheduler> taskSchedulerList;
		if (!(this.tmgCfg.enableTmg)) {
			return;
		}
		try {
			taskSchedulerList = this.taskSchedulerDao.getTaskSchedulerByAppId(this.tmgCfg.tmgAppId);
			if (taskSchedulerList == null) {
				return;
			}
			this.sesTaskScheduler.setApplicationContext(applicationContext);
			for (TaskScheduler taskScheduler : taskSchedulerList) {

				sesTaskScheduler.scheduleTask(taskScheduler);

			}
		} catch (Exception e) {
			this.logger.error("加载系统任务失败：" + e.getMessage(), e);
		}
	}

	/**
	 * 设置应用上下文
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

}
