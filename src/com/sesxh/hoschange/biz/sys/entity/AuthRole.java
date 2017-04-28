package com.sesxh.hoschange.biz.sys.entity;

public class AuthRole implements java.io.Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private String enabled;
	private String lqcs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "AuthRole [id=" + id + ", name=" + name + ", description=" + description + ", enabled=" + enabled
				+ ", lqcs=" + lqcs + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getLqcs() {
		return lqcs;
	}

	public void setLqcs(String lqcs) {
		this.lqcs = lqcs;
	}

}
