package com.sesxh.hoschange.biz.tmg.core;

/**
 * 任务调度配置类
 */
public class TmgConfig {
	public boolean enableTmg = false;// tmg是否启用标志
	public String tmgAppId = null;
	public final String defaultTaskGroup = "SesTaskGroup";
	public final String defaultTriggerGroup = "SesTriggerGroup";
	public final String triggerNamePrefix = "SesTrigger";

	/**
	 * 判断任务调度是否启用
	 * 
	 * @return boolean
	 */
	public boolean isEnableTmg() {
		return enableTmg;
	}

	/**
	 * 设置是否启动任务调度
	 * 
	 * @param enableTmg
	 */
	public void setEnableTmg(boolean enableTmg) {
		this.enableTmg = enableTmg;
	}

	public String getTmgAppId() {
		return tmgAppId;
	}

	public void setTmgAppId(String tmgAppId) {
		this.tmgAppId = tmgAppId;
	}

}
