package com.sesxh.hoschange.biz.sys.entity;

/**
 * 字典
 * @author root
 *
 */
public class Dictionary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String type;// 类别码
	private String code;// 编码
	private String name;// 名称
	private String description;// 描述
	private Integer parentId;// 上级id
	private String enabled;// 是否有效(1 有效，0 无效)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
