package com.sesxh.hoschange.biz.tmg.core.plan;

/**
 * 任务调度触发器
 */
public class SesTaskTrigger {
	private String cron;
	private boolean manualTerminate;
	private int timeout;

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	/**
	 * 是否手动终止
	 * 
	 * @return boolean
	 */
	public boolean isManualTerminate() {
		return manualTerminate;
	}

	public void setManualTerminate(boolean manualTerminate) {
		this.manualTerminate = manualTerminate;
	}

	/**
	 * 获得超时时间
	 * 
	 * @return int
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * 设置超时时间
	 * 
	 * @param timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
