package com.sesxh.hoschange.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.AppException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.exception.UnauthenticatedException;
import com.sesxh.hoschange.common.exception.UnauthorizedException;
import com.sesxh.hoschange.common.util.ZWebUtil;
import com.sesxh.hoschange.global.BizConst;

public class ZBaseController {
	protected BizMsg ajaxDone(int statusCode, String message, String forwardUrl) {
		BizMsg msg = new BizMsg();
		msg.setStatusCode(statusCode);
		msg.setMessage(message);
		msg.setForwardUrl(forwardUrl);
		return msg;
	}

	protected BizMsg ajaxDone(int statusCode, String message) {
		return ajaxDone(statusCode, message, "");
	}

	protected BizMsg ajaxSuccess() {
		return ajaxDone(BizConst.SC.SC_200, "");
	}

	protected BizMsg ajaxDoneSuccess(String message) {
		return ajaxDone(BizConst.SC.SC_200, message);
	}

	// BusinessException
	protected BizMsg ajaxBizError(String message) {
		return ajaxDone(BizConst.SC.SC_300, message);
	}

	// -----------------------------------------------------------
	// Exception
	protected BizMsg ajaxFatalError(String message) {
		return ajaxDone(BizConst.SC.SC_500, message);
	}

	// BaseException
	protected BizMsg ajaxBaseError(String message) {
		return ajaxDone(BizConst.SC.SC_501, message);
	}

	// FrameException
	protected BizMsg ajaxFrameError(String message) {
		return ajaxDone(BizConst.SC.SC_502, message);
	}

	// AppException
	protected BizMsg ajaxAppError(String message) {
		return ajaxDone(BizConst.SC.SC_503, message);
	}

	// -----------------------------------------------------------
	protected BizMsg ajaxAuthError(String message) {
		return ajaxDone(BizConst.SC.SC_401, message);
	}

	@ExceptionHandler
	@ResponseBody
	public Object processGlobalExceptions(HttpServletRequest request, HttpServletResponse httpResponse,
			HandlerMethod handlerMethod, Exception e) throws Exception {
		if (e instanceof BusinessException) {
			if (ZWebUtil.isAjax(request)) {
				ZWebUtil.respJSON(httpResponse, ajaxBizError(e.getMessage()));
			} else {
				httpResponse.setHeader("header_msg", java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_300);
			}
			return null;
		} else if (e instanceof UnauthorizedException || e instanceof UnauthenticatedException) {// 未授权
			if (ZWebUtil.isAjax(request)) {
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_401);
				// ZWebUtil.respJSON(httpResponse,
				// ajaxAuthError(e.getMessage()));
			} else {
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_401);
			}
			return null;
		} else if (e instanceof AppException || e instanceof FrameException || e instanceof BaseException) {
			if (ZWebUtil.isAjax(request)) {
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_50X);
			} else {
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_50X);
			}
			return null;
		} else if (e instanceof Exception) {

			if (ZWebUtil.isAjax(request)) {
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_50X);
				// ZWebUtil.respJSON(httpResponse,
				// ajaxFatalError(e.getMessage()));
			} else {
				ZWebUtil.redirect(request, httpResponse, BizConst.INFOPAGE.ERROR_PAGE_50X);
			}
			return null;
		}
		return null;
	}
}
