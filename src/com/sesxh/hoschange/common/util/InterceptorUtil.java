package com.sesxh.hoschange.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author zhaohutai
 *
 */
public class InterceptorUtil {

	public static boolean isInignorUrls(HttpServletRequest request, String[] ignorUrls) {
		if (ignorUrls != null && ignorUrls.length > 0) {
			for (String url : ignorUrls) {
				if (ZWebUtil.pathsMatch(url, request)) {
					return true;
				}
			}
		}
		return false;
	}
}
