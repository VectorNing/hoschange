/**
 * Copyright (c) 2016-2020 https://github.com/zhaohuatai
 *
 * contact z_huatai@qq.com
 *  
 */
package com.sesxh.hoschange.biz.sys.view;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

//{"title":"主页","url":"","icon":"home","items":[{"title":"主页示例一","url":"hplus/index_v1.html","icon":""},
//  	                                        {"title":"主页示例二","url":"hplus/index_v2.html","icon":""},
//  	                                        {"title":"主页示例三","url":"hplus/index_v3.html","icon":""},
//  	                                        {"title":"主页示例四","url":"hplus/index_v4.html","icon":""},
//  	                                        {"title":"主页示例五","url":"hplus/index_v5.html","icon":"","target":""}
public class MenuView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String url;
	private String icon;
	private String target;
	private Integer id;
	private Integer parentId;
	private List<MenuView> childMenus;

	public void addChild(MenuView childMenu) {
		if (childMenus == null) {
			childMenus = Lists.newArrayList();
		}
		childMenus.add(childMenu);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<MenuView> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<MenuView> childMenus) {
		this.childMenus = childMenus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
