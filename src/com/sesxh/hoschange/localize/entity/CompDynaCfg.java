package com.sesxh.hoschange.localize.entity;

/**
 * 本地化组件实体类
 * 
 * @author Administrator
 * 
 */

public class CompDynaCfg implements java.io.Serializable {

	private static final long serialVersionUID = 3129091902915449204L;

	private String cdcid;
	private String userobjid;
	private String compid;
	private String compname;
	private String localid;
	private String localname;
	private String note;

	public String getCdcid() {
		return this.cdcid;
	}

	public void setCdcid(String cdcid) {
		this.cdcid = cdcid;
	}

	public String getUserobjid() {
		return userobjid;
	}

	public void setUserobjid(String userobjid) {
		this.userobjid = userobjid;
	}

	public String getCompid() {
		return this.compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

	public String getCompname() {
		return this.compname;
	}

	public void setCompname(String compname) {
		this.compname = compname;
	}

	public String getLocalid() {
		return this.localid;
	}

	public void setLocalid(String localid) {
		this.localid = localid;
	}

	public String getLocalname() {
		return this.localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
