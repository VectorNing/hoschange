package com.sesxh.hoschange.common.auth.interceptors;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Maps;
import com.sesxh.common.exception.AppException;
import com.sesxh.hoschange.common.exception.UnauthenticatedException;
import com.sesxh.hoschange.common.util.InterceptorUtil;
import com.sesxh.hoschange.common.util.SecurityUtils;
import com.sesxh.hoschange.common.util.ZWebUtil;

/**
 * 
 * @author zhaohutai <br>
 *         {/static/**=anon} <br>
 *         {/user/**=authc,role[admin]} <br>
 *         {/user/add/*=role[admin]} <br>
 * 
 *         anon authc role[admin,leader] perm[user:add,user:delete] <br>
 * 
 */
public class PathAuthInterceptor extends HandlerInterceptorAdapter {

	private static final String anon = "anon";
	private static final String authc = "authc";
	private static final String role = "role[";
	private static final String perm = "perm[";

	/*
	 * private static final Set<String> permStrSet = new HashSet<>(
	 * Arrays.asList(new String[] { anon, authc, role, perm }));
	 */

	private static final String splitChar_B = "=";
	private static final String splitChar_C = ",";
	private static final String lineSplit = "\n";

	private String[] ignorUrls;//

	private String urlAuthMapping;

	private static boolean isInited = false;

	// {/app/**=anon}
	// {/app/**=authc,role[]}
	private Map<String, Set<String>> pathAuthConfig = null;

	private static PathMatcher pathMatcher = new AntPathMatcher();

	private void initAndFillPathAuthConfig() throws AppException {
		pathAuthConfig = Maps.newHashMap();
		if (!StringUtils.hasText(urlAuthMapping)) {
			return;
		}
		String[] allLinesPermConfigArray = urlAuthMapping.split(lineSplit);
		if (allLinesPermConfigArray == null || allLinesPermConfigArray.length < 1) {
			return;
		}
		for (String str : allLinesPermConfigArray) {
			if (!StringUtils.hasText(str)) {
				continue;
			}
			String[] permItemArray = str.split(splitChar_B);
			String path = permItemArray[0].trim();
			String permConfig = permItemArray[1].trim();
			if (permConfig != null && !permConfig.startsWith(anon) && !permConfig.startsWith(authc)
					&& !permConfig.startsWith(role) && !permConfig.startsWith(perm)) {
				throw new AppException("权限配置错误");
			}
			String[] permSet = permConfig.split(splitChar_C);
			Set<String> pathSet = new LinkedHashSet<>(Arrays.asList(permSet));
			pathAuthConfig.put(path, pathSet);
		}
	}

	private Set<String> parsePermSet(HttpServletRequest request, String url) {
		Set<String> pathSet = pathAuthConfig.keySet();
		if (pathSet != null && !pathSet.isEmpty()) {
			for (String path : pathSet) {
				if (pathMatcher.match(path, url)) {
					return pathAuthConfig.get(path);
				}
			}
		}
		return Collections.emptySet();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (InterceptorUtil.isInignorUrls(request, ignorUrls)) {
			return true;
		}
		if (!isInited) {
			initAndFillPathAuthConfig();
			isInited = true;
		}
		if (pathAuthConfig == null || pathAuthConfig.isEmpty()) {
			return true;
		}
		String requestURL = ZWebUtil.getPathWithinApplication(request);
		Set<String> permSet = parsePermSet(request, requestURL);
		if (permSet != null && permSet.size() > 0) {
			if (isAnonymous(permSet)) {
				return true;
			}
			if (isAuthc(permSet)) {
				handleAuthc();
			}
		}
		return super.preHandle(request, response, handler);
	}
	// =================================================================================

	private boolean isAnonymous(Set<String> permSet) {
		return permSet == null || permSet.contains(PathAuthInterceptor.anon);
	}

	// -----------------------------------------------------------------------------
	private boolean isAuthc(Set<String> permSet) {
		return permSet == null || permSet.contains(PathAuthInterceptor.authc);
	}

	private void handleAuthc() {
		boolean idAuthced = SecurityUtils.getUserFromSession() == null ? true : false;
		if (!idAuthced) {
			throw new UnauthenticatedException("当前用户没有登陆");
		}
	}

	// -------------------------------------------------------------------------------------
	// ===========================================================

	public String[] getIgnorUrls() {
		return ignorUrls;
	}

	public void setIgnorUrls(String[] ignorUrls) {
		this.ignorUrls = ignorUrls;
	}

	public String getUrlAuthMapping() {
		return urlAuthMapping;
	}

	public void setUrlAuthMapping(String urlAuthMapping) {
		this.urlAuthMapping = urlAuthMapping;
	}

}
