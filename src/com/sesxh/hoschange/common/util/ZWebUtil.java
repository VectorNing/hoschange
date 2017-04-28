/**
 * Copyright (c) 2016-2020 https://github.com/zhaohuatai
 *
 * contact z_huatai@qq.com
 *  
 */
package com.sesxh.hoschange.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sesxh.hoschange.common.data.BizMsg;

/**
 * 
 * @author zhaohuatai
 *
 */
public abstract class ZWebUtil extends WebUtils {

	public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";

	private static PathMatcher pathMatcher = new AntPathMatcher();

	public static boolean pathsMatch(String pattern, String path) {
		return pathMatcher.match(pattern, path);
	}

	public static boolean pathsMatch(String path, ServletRequest request) {
		String requestURL = getPathWithinApplication(request);
		return pathsMatch(path, requestURL);
	}

	public static HttpServletRequest toHttp(ServletRequest request) {
		return (HttpServletRequest) request;
	}

	public static HttpServletResponse toHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}

	public static String getPathWithinApplication(ServletRequest request) {
		return getPathWithinApplication(toHttp(request));
	}

	public static String getPathWithinApplication(HttpServletRequest request) {
		String contextPath = getContextPath(request);
		String requestUri = getRequestUri(request);
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			String path = requestUri.substring(contextPath.length());
			return (StringUtils.hasText(path) ? path : "/");
		} else {
			return requestUri;
		}
	}

	public static String getContextPath(HttpServletRequest request) {
		String contextPath = (String) request.getAttribute(INCLUDE_CONTEXT_PATH_ATTRIBUTE);
		if (contextPath == null) {
			contextPath = request.getContextPath();
		}
		if ("/".equals(contextPath)) {
			contextPath = "";
		}
		return decodeRequestString(request, contextPath);
	}

	public static String getRequestUri(HttpServletRequest request) {
		String uri = (String) request.getAttribute(INCLUDE_REQUEST_URI_ATTRIBUTE);
		if (uri == null) {
			uri = request.getRequestURI();
		}
		return decodeAndCleanUriString(request, uri);
	}

	private static String decodeAndCleanUriString(HttpServletRequest request, String uri) {
		uri = decodeRequestString(request, uri);
		int semicolonIndex = uri.indexOf(';');
		return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
	}

	@SuppressWarnings("deprecation")
	public static String decodeRequestString(HttpServletRequest request, String source) {
		String enc = determineEncoding(request);
		try {
			return URLDecoder.decode(source, enc);
		} catch (UnsupportedEncodingException ex) {
			return URLDecoder.decode(source);
		}
	}

	protected static String determineEncoding(HttpServletRequest request) {
		String enc = request.getCharacterEncoding();
		if (enc == null) {
			enc = DEFAULT_CHARACTER_ENCODING;
		}
		return enc;
	}
	// ---------------------------------------------------------------\

	public static boolean isAjax(HttpServletRequest request) {
		// String isajax=request.getHeader("X-Requested-With_x");
		// System.out.println("from isAjax: ||"+request.getRequestURL());
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With_x"));
	}

	@Deprecated
	public static boolean isAjaxJson(HttpServletRequest request) {
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			String accept = ("" + request.getHeader("accept")).toLowerCase();
			if (accept.contains("application/json")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInUrls(HttpServletRequest request, String[] urls) {
		if (urls != null && urls.length > 0) {
			for (String url : urls) {
				if (pathsMatch(url, request)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isInUrls(HttpServletRequest request, List<String> urls) {
		if (urls != null && urls.size() > 0) {
			for (String url : urls) {
				if (pathsMatch(url, request)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void respJSON(HttpServletResponse httpResponse, BizMsg jsonmsg) throws IOException {
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json;charset=utf-8");
		httpResponse.getWriter().write(JSON.toJSONString(jsonmsg));
	}

	public static void redirect(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String url)
			throws IOException, ServletException {
		if (url == null) {
			return;
		}
		url = url.startsWith("/") ? url : "/" + url;
		if (url != null && !url.contains(httpRequest.getContextPath())) {
			url = httpRequest.getContextPath() + url;
		}
		httpResponse.sendRedirect(url);
	}

	public static void forward(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String url)
			throws ServletException, IOException {
		if (url == null) {
			return;
		}
		url = url.startsWith("/") ? url : "/" + url;
		// if (!url.contains(httpRequest.getContextPath())) {
		// // url=httpRequest.getContextPath()+url;
		// }
		httpRequest.getRequestDispatcher(url).forward(httpRequest, httpResponse);
	}

	public static String getRequestParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		if (params == null) {
			return "";
		}
		return JSON.toJSONString(params);
	}

	public static String getRequestHeaders(HttpServletRequest request) {
		Map<String, List<String>> headers = Maps.newHashMap();
		Enumeration<String> namesEnumeration = request.getHeaderNames();
		while (namesEnumeration != null && namesEnumeration.hasMoreElements()) {
			String name = namesEnumeration.nextElement();
			Enumeration<String> valueEnumeration = request.getHeaders(name);
			List<String> values = Lists.newArrayList();
			while (valueEnumeration.hasMoreElements()) {
				values.add(valueEnumeration.nextElement());
			}
			headers.put(name, values);
		}
		return JSON.toJSONString(headers);
	}
}
