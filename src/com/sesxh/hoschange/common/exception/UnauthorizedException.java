package com.sesxh.hoschange.common.exception;

/**
 * 未授权异常，没有权限
 * 
 * @author zhaohuatai
 *
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
}
