/**
 * Copyright (c) 2016-2020 https://github.com/zhaohuatai
 *
 * contact z_huatai@qq.com
 *  
 */
package com.sesxh.hoschange.common.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.sesxh.hoschange.common.util.ZStrUtil;
import com.sesxh.hoschange.global.BizConst;

/**
 * 
 * @author zhaohuatai
 *
 */
public class ParamMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private ParamMap(QueryType type) {
		if (type != null) {
			this.put(BizConst.QUERY_TYPE, type);
		}
	}

	public static ParamMap newJustMap() {
		return new ParamMap(null);
	}

	public static ParamMap newpm() {
		return new ParamMap(QueryType.S_NO_NC);
	}

	public static ParamMap newpm(QueryType type) {
		return new ParamMap(type);
	}

	public static ParamMap newpm(String key, Object value) {
		ParamMap pramMap = ParamMap.newpm();
		if (value != null && ZStrUtil.trimToNullIfStr(value) != null) {
			pramMap.put(key, value);
		}
		return pramMap;
	}

	public static List<ParamMap> newpmList() {
		return Lists.newArrayList();
	}

	public static ParamMap[] newpms(int size) {
		ParamMap[] paramMaps = new ParamMap[size];
		return paramMaps;
	}

	public ParamMap addParam(String key, Object value) {
		if (value != null && ZStrUtil.trimToNullIfStr(value) != null) {
			this.put(key, value);
		}
		return this;
	}

	public ParamMap updateParam(String key, Object value) {
		return addParam(key, value);
	}

	public ParamMap addParams(Map<String, Object> params) {
		Iterator<Map.Entry<String, Object>> entries = params.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			if (!org.springframework.util.StringUtils.hasText(entry.getKey())) {
				this.remove(entry.getKey());
				continue;
			}
			String key = StringUtils.trimToNull(entry.getKey());
			Object param = ZStrUtil.trimToNullIfStr(entry.getValue());
			if (key != null && param != null) {
				this.put(entry.getKey().trim(), param);
			}
		}
		return this;
	}

	public Object getObjParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object param = ZStrUtil.trimToNullIfStr(this.get(key));
		return param;
	}

	public Integer getIntegerParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object param = ZStrUtil.trimToNullIfStr(this.get(key));
		try {
			return Integer.valueOf("" + param);
		} catch (Exception e) {
			return null;
		}
	}

	public Integer getIntParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object param = ZStrUtil.trimToNullIfStr(this.get(key));
		try {
			return Integer.valueOf("" + param);
		} catch (Exception e) {
			return null;
		}
	}

	public Short getShortParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object param = ZStrUtil.trimToNullIfStr(this.get(key));
		try {
			return Short.valueOf("" + param);
		} catch (Exception e) {
			return null;
		}
	}

	public Byte getByteParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object param = ZStrUtil.trimToNullIfStr(this.get(key));
		try {
			return Byte.valueOf("" + param);
		} catch (Exception e) {
			return null;
		}
	}

	public String getStrParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object obj = ZStrUtil.trimToNullIfStr(this.get(key));
		if (obj == null) {
			return null;
		}
		String res = String.valueOf(obj);
		return res.trim();
	}

	public Boolean getBooleanParam(String key) {
		if (this.isEmpty() || key == null || key.trim().isEmpty()) {
			return null;
		}
		Object obj = this.get(key);
		if (obj == null) {
			return null;
		}
		if (obj instanceof java.lang.Boolean) {
			return (Boolean) obj;
		}
		String str = StringUtils.trimToNull(obj.toString());
		if (BizConst.BOOLEAN.FALSE.equals(str.trim())) {
			return false;
		}
		if (BizConst.BOOLEAN.TRUE.equals(str.trim())) {
			return true;
		}
		return null;
	}

	/// -----------------------------------------

	public static ParamMap[] toParamArray(List<ParamMap> list) {
		if (list == null || list.size() == 0) {
			return new ParamMap[0];
		}
		ParamMap[] paramMaps = new ParamMap[list.size()];
		int index = 0;
		for (ParamMap param : list) {
			paramMaps[index] = param;
			index++;
		}
		return paramMaps;
	}

	public static ParamMap filterParam(Map<String, Object> params) {
		return filterParam(params, QueryType.S_O_C);
	}

	public static ParamMap filterParam(Map<String, Object> params, QueryType qt) {
		ParamMap pm = ParamMap.newpm(QueryType.S_O_C);
		if (params != null) {
			Iterator<Map.Entry<String, Object>> entries = params.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Object> entry = entries.next();
				if (!org.springframework.util.StringUtils.hasText(entry.getKey())) {
					continue;
				}
				String key = StringUtils.trimToNull(entry.getKey());
				Object param = ZStrUtil.trimToEmptyIfStr(entry.getValue());

				if (key != null && param != null && !"".equals(param)) {
					pm.put(key, param);
				}
			}
		}

		String page = (String) params.get(BizConst.PAGEQUERY.PAGE);
		String rows = (String) params.get(BizConst.PAGEQUERY.ROWS);

		rows = (rows == null || rows.isEmpty()) ? ("" + BizConst.PAGEQUERY.DEFAULT_ROWS) : rows;
		page = (page == null || page.isEmpty()) ? ("" + BizConst.PAGEQUERY.DEFAULT_PAGE) : page;

		int current_page = Integer.valueOf(page);
		int current_row = Integer.valueOf(rows);

		if (current_page < 1) {
			current_page = 1;
		}
		if (current_row < 0) {
			current_row = BizConst.PAGEQUERY.DEFAULT_ROWS;
		}
		if (current_row > BizConst.PAGEQUERY.MAX_ROWS) {
			current_row = BizConst.PAGEQUERY.MAX_ROWS;
		}

		// 针对不同的数据库分页模式
		pm.put(BizConst.PAGEQUERY.START, ((current_page - 1) * current_row));
		pm.put(BizConst.PAGEQUERY.END, (current_page * current_row));
		pm.put(BizConst.PAGEQUERY.SIZE, current_row);
		pm.put(BizConst.PAGEQUERY.PAGE, current_page);
		pm.put(BizConst.PAGEQUERY.ROWS, current_row);

		String sort = (String) params.get(BizConst.PAGEQUERY.SORT);
		String order = (String) params.get(BizConst.PAGEQUERY.ORDER);
		if (sort != null && !sort.isEmpty()) {
			if (order == null || order.isEmpty()) {
				order = BizConst.PAGEQUERY.ASC;
			}
			if (!order.equalsIgnoreCase(BizConst.PAGEQUERY.ASC.trim())
					&& !order.equalsIgnoreCase(BizConst.PAGEQUERY.DESC.trim())) {
				order = BizConst.PAGEQUERY.ASC;
			}
			pm.put(BizConst.PAGEQUERY.SORT, sort);
			pm.put(BizConst.PAGEQUERY.ORDER, order);
		} else {
			pm.remove(BizConst.PAGEQUERY.SORT);
			pm.remove(BizConst.PAGEQUERY.ORDER);
		}
		if (qt != null) {
			pm.put(BizConst.QUERY_TYPE, qt);
		}
		return pm;
	}

	// ----------------------------------------------------------------

	public Integer getPage() {
		return (Integer) this.get(BizConst.PAGEQUERY.PAGE);
	}

	public Integer getRows() {
		return (Integer) this.get(BizConst.PAGEQUERY.ROWS);
	}

	public Integer getSize() {
		return (Integer) this.get(BizConst.PAGEQUERY.SIZE);
	}

	public Integer getStart() {
		return (Integer) this.get(BizConst.PAGEQUERY.START);
	}

	public Integer getEnd() {
		return (Integer) this.get(BizConst.PAGEQUERY.END);
	}

	public String getSort() {
		return (String) this.get(BizConst.PAGEQUERY.SORT);
	}

	public String getOrder() {
		return (String) this.get(BizConst.PAGEQUERY.ORDER);
	}

	public QueryType getQueryType() {
		return (QueryType) this.get(BizConst.QUERY_TYPE);
	}

	public ParamMap setPage(Integer page) {
		this.put(BizConst.PAGEQUERY.PAGE, page);
		return this;
	}

	public ParamMap setRows(Integer rows) {
		this.put(BizConst.PAGEQUERY.ROWS, rows);
		return this;
	}

	public ParamMap setSize(Integer size) {
		this.put(BizConst.PAGEQUERY.SIZE, size);
		return this;
	}

	public ParamMap setStart(Integer start) {
		this.put(BizConst.PAGEQUERY.START, start);
		return this;
	}

	public ParamMap setEnd(Integer end) {
		this.put(BizConst.PAGEQUERY.END, end);
		return this;
	}

	public ParamMap setOrder(String order) {
		if (order == null || order.isEmpty()) {
			order = BizConst.PAGEQUERY.ASC;
		}
		if (!order.equalsIgnoreCase(BizConst.PAGEQUERY.ASC) && !order.equalsIgnoreCase(BizConst.PAGEQUERY.DESC)) {
			order = BizConst.PAGEQUERY.ASC;
		}
		this.put(BizConst.PAGEQUERY.ORDER, order);
		return this;
	}

	public ParamMap setSort(String sort) {
		this.put(BizConst.PAGEQUERY.SORT, sort);
		return this;
	}

	public ParamMap setQueryType(QueryType qt) {
		this.put(BizConst.QUERY_TYPE, qt);
		return this;
	}

}
