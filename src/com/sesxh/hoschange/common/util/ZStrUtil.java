package com.sesxh.hoschange.common.util;

import com.sesxh.common.util.StringUtils;

/**
 * 
 * @author zhaohuatai
 *
 */
public class ZStrUtil extends StringUtils {

	public static Object trimToNullIfStr(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof java.lang.String) {
			String str = String.valueOf(obj);
			if (str == null || "".equals(str.trim())) {
				return null;
			} else {
				return str.trim();
			}
		}
		return obj;
	}

	public static Object trimToEmptyIfStr(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof java.lang.String) {
			String str = String.valueOf(obj);
			if (str == null || "".equals(str.trim())) {
				return "";
			} else {
				return str.trim();
			}
		}
		return obj;
	}

	public static boolean isEmptyAfterTrimE(String str) {
		return isEmpty(org.apache.commons.lang.StringUtils.trimToEmpty(str));
	}

	public static boolean isNullAfterTrimN(String str) {
		return null == org.apache.commons.lang.StringUtils.trimToNull(str);
	}

}
