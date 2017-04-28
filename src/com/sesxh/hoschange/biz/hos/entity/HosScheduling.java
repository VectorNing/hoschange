package com.sesxh.hoschange.biz.hos.entity;

import java.util.List;

/**
 * 手术室排班信息
 * 
 * @author wwb
 *
 */
public class HosScheduling {
	private Integer id;
	private String thNumber;// 手术室编号
	private String name;// 手术名称
	private String operationtime;// 手术时间
	private String enabled;// 是否使用
	private String patientId;// 患者Id
	private String operationNumber;// 手术申请编号
	private List<String> employees;// 手术员工id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThNumber() {
		return thNumber;
	}

	public void setThNumber(String thNumber) {
		this.thNumber = thNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getOperationtime() {
		return operationtime;
	}

	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getOperationNumber() {
		return operationNumber;
	}

	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}

	public List<String> getEmployees() {
		return employees;
	}

	public void setEmployees(List<String> employees) {
		this.employees = employees;
	}

}
