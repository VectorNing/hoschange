package com.sesxh.hoschange.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.sys.entity.Dictionary;
import com.sesxh.hoschange.biz.sys.service.DictionaryService;
import com.sesxh.hoschange.biz.sys.service.impl.DictionaryServiceImpl;

public class DicTag extends SimpleTagSupport {

	private String type;
	private String value;

	ApplicationContext ctx = null;
	DictionaryService dicService = null;

	private void init() {
		if (ctx == null) {
			PageContext pc = (PageContext) this.getJspContext();
			ctx = WebApplicationContextUtils.getWebApplicationContext(pc.getServletContext());
			if (dicService == null) {
				dicService = ctx.getBean(DictionaryServiceImpl.class);
			}
		}
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		init();
		Dictionary dic = null;
		try {
			dic = dicService.loadDictByTypeAndCode(type, value);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		if (dic == null) {
			getJspContext().getOut().write(" ");
			return;
		}
		getJspContext().getOut().write(dic.getName());

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
