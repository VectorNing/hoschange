package com.sesxh.hoschange.common.util;

import com.sesxh.common.exception.BusinessException;

/**
 * 
 * @author zhaohutai
 *
 */
public class ZAlert {

	public static void Error(String message) throws BusinessException {
		throw new BusinessException(message);
	}

	public static void isNull(String message) throws BusinessException {
		throw new BusinessException(message);
	}

	public static void notExist(String message) throws BusinessException {
		throw new BusinessException(message);
	}

	public static void FormatError(String message) throws BusinessException {
		throw new BusinessException(message);
	}

	public static void SqlError(String message) throws BusinessException {
		throw new BusinessException(message);
	}

	public static void IOError(String message) throws BusinessException {
		throw new BusinessException(message);
	}

	public static void FileError(String message) throws BusinessException {
		throw new BusinessException(message);
	}

}
