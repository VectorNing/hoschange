package com.sesxh.hoschange.common.exception;

/**
 * 未认证异常，（未登录异常）
 * 
 * @author root
 *
 */
public class UnauthenticatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthenticatedException() {
		super();
	}

	public UnauthenticatedException(String message) {
		super(message);
	}

	public UnauthenticatedException(Throwable cause) {
		super(cause);
	}

	public UnauthenticatedException(String message, Throwable cause) {
		super(message, cause);
	}
}
