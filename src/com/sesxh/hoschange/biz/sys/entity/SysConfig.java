package com.sesxh.hoschange.biz.sys.entity;
/**
 * 系统配置
* <p>Title:SysConfig </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author Ning
* @date 2017年1月12日
 */
public class SysConfig {
	private Integer id;
	private String configName;
	private String name;
	private String config;
	private String description;
	
	
	private String nowConfig;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getConfigName() {
		return configName;
	}


	public void setConfigName(String configName) {
		this.configName = configName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getConfig() {
		return config;
	}


	public void setConfig(String config) {
		this.config = config;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getNowConfig() {
		return nowConfig;
	}


	public void setNowConfig(String nowConfig) {
		this.nowConfig = nowConfig;
	}


	@Override
	public String toString() {
		return "SysConfig [id=" + id + ", configName=" + configName + ", name=" + name + ", config=" + config
				+ ", description=" + description + ", nowConfig=" + nowConfig + "]";
	}
	
	
	
	
}
