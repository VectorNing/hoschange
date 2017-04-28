package com.sesxh.hoschange.biz.sys.entity;

public class AuthPermission implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String permCode;
	private String name;
	private String enabled;
	private String type;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermCode() {
		return permCode;
	}

	public void setPermCode(String permCode) {
		this.permCode = permCode;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
