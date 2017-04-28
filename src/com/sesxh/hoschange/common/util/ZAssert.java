package com.sesxh.hoschange.common.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.NumberUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.sesxh.common.exception.BusinessException;

@SuppressWarnings("deprecation")
public class ZAssert {
	/**
	 * 如内容不是true 则抛出ServiceLogicalException
	 * 
	 * @param expression
	 *            ：待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void isTrue(boolean expression, String message) throws BusinessException {
		if (!expression)
			ZAlert.Error(message);
	}

	public static void equal(Object obj1, Object obj2, String message) throws BusinessException {
		if (obj1 == null && obj2 == null) {
			return;
		}
		if (obj1 == null && obj2 != null) {
			ZAlert.Error(message);
		}
		if (!obj1.equals(obj2)) {
			ZAlert.Error(message);
		}
	}

	/**
	 * 如内容不是False 则抛出ServiceLogicalException
	 * 
	 * @param expression
	 *            ：待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void isFalse(boolean expression, String message) throws BusinessException {
		if (expression)
			ZAlert.Error(message);
	}

	/**
	 * 判断内容是否 null ，如果不是null 则抛出ServiceLogicalException
	 * 
	 * @param object
	 *            ：待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void isNull(Object object, String message) throws BusinessException {
		if (object != null)
			ZAlert.Error(message);
	}

	/**
	 * 判断内容是否 不是 null ，如果是null 则抛出ServiceLogicalException
	 * 
	 * @param object
	 *            ：待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void notNull(Object object, String message) throws BusinessException {
		if (object == null)
			ZAlert.Error(message);
	}

	public static void notEmpty(Object[] array, String message) throws BusinessException {
		if (ObjectUtils.isEmpty(array))
			ZAlert.Error(message);
	}

	/**
	 * 
	 * 判断字符串是否 不是 null 而且不为空， 如果是null或空字符 ，则抛出ServiceLogicalException<br/>
	 * <br/>
	 * 
	 * (str != null) && (str.length() > 0);
	 * 
	 * @param text
	 *            ：待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void hasLength(String text, String message) throws BusinessException {
		if (!StringUtils.hasLength(text))
			ZAlert.Error(message);
	}

	/**
	 * 
	 * 判断字符串是否 不是 null 、不为空、且不是Whitespace ，<br/>
	 * <br/>
	 * 如果是null 或 空字符 或者全是 Whitespace (blank) ，则抛出ServiceLogicalException (blank)
	 * <br/>
	 * <br/>
	 * 
	 * @param text
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void hasText(String text, String message) throws BusinessException {
		if (!StringUtils.hasText(text))
			ZAlert.Error(message);
	}

	public static void lenLessTan(String text, int len, String message) throws BusinessException {
		if (!StringUtils.hasLength(text)) {
			ZAlert.Error(message);
		}
		if (text.length() >= len)
			ZAlert.Error(message);
	}

	public static void lenbigTan(String text, int len, String message) throws BusinessException {
		if (!StringUtils.hasLength(text)) {
			ZAlert.Error(message);
		}
		if (text.length() < len)
			ZAlert.Error(message);
	}

	public static void hasText(Object text, String message) throws BusinessException {
		if (text == null) {
			ZAlert.Error(message);
		}
		if (!StringUtils.hasText(String.valueOf(text))) {
			ZAlert.Error(message);
		}

	}

	public static void bigThanZero(Object text, String message, String formatErrorMessage) throws BusinessException {
		if (text == null) {
			ZAlert.Error(formatErrorMessage);
		}
		try {
			if (Long.parseLong(text.toString().trim()) <= 0) {
				ZAlert.Error(message);
			}
		} catch (NumberFormatException e) {
			ZAlert.Error(formatErrorMessage);
		}
	}

	/**
	 * textToSearch 不能包含substring， 如果包含则抛出ServiceLogicalException
	 * 
	 * @param textToSearch
	 *            : 待判断的内容
	 * @param substring
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void notContain(String textToSearch, String substring, String message) throws BusinessException {
		if ((StringUtils.hasLength(textToSearch)) && (StringUtils.hasLength(substring))
				&& (textToSearch.contains(substring))) {
			ZAlert.Error(message);
		}
	}

	/**
	 * 数组array 必须不能为空，必须至少包含一个元素；否则抛出 ServiceLogicalException
	 * 
	 * @param array
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void notEmptyArray(Object[] array, String message) throws BusinessException {
		if (StringUtils.isEmpty(array))
			ZAlert.Error(message);
	}

	/**
	 * 数组array 必须为null 或者 空，否则抛出 ServiceLogicalException
	 * 
	 * @param array
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void emptyArray(Object[] array, String message) throws BusinessException {
		if (!StringUtils.isEmpty(array))
			ZAlert.Error(message);
	}

	/**
	 * 数组array 必须不能为空，且不能包含null 元素 ；否则抛出 ServiceLogicalException
	 * 
	 * @param array
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void noNullElements(Object[] array, String message) throws BusinessException {
		if (array != null)
			for (Object element : array)
				if (element == null)
					ZAlert.Error(message);
	}

	/**
	 * 集合collection必须不能为空，必须至少包含一个元素；否则抛出 ServiceLogicalException
	 * 
	 * @param collection
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void notEmpty(Collection<?> collection, String message) throws BusinessException {
		if (CollectionUtils.isEmpty(collection))
			ZAlert.Error(message);
	}

	/**
	 * 集合collection必须null或者空；否则抛出 ServiceLogicalException
	 * 
	 * @param collection
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void empty(Collection<?> collection, String message) throws BusinessException {
		if (!CollectionUtils.isEmpty(collection))
			ZAlert.Error(message);
	}

	/**
	 * map必须不能为空，必须至少包含一个元素；否则抛出 ServiceLogicalException
	 * 
	 * @param collection
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void notEmpty(Map<?, ?> map, String message) throws BusinessException {
		if (CollectionUtils.isEmpty(map))
			ZAlert.Error(message);
	}

	/**
	 * Map必须为null 或者 空；否则抛出 ServiceLogicalException
	 * 
	 * @param collection
	 *            : 待判断的内容
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void empty(Map<?, ?> map, String message) throws BusinessException {
		if (!CollectionUtils.isEmpty(map))
			ZAlert.Error(message);
	}

	/**
	 * 判断个对象实例是否是一个类或接口的或其子类子接口的实例，否则抛出 ServiceLogicalException
	 * 
	 * @param type
	 *            : 待判断的类型
	 * @param obj
	 *            : 待判断的对象
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void isInstanceOf(Class<?> type, Object obj, String message) throws BusinessException {
		notNull(type, "待判断的类型参数不能为空");
		if (!type.isInstance(obj)) {
			ZAlert.Error(new StringBuilder()
					.append(StringUtils.hasLength(message) ? new StringBuilder().append(message).append(" ").toString()
							: "")
					.append("Object of class [").append(obj != null ? obj.getClass().getName() : "null")
					.append("] must be an instance of ").append(type).toString());
		}
	}

	/**
	 * 判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口，否则抛出 ServiceLogicalException
	 * 
	 * @param superType
	 *            : 待判断的上级类型
	 * @param subType
	 *            : 待判断的子类型
	 * @param message
	 *            : 抛出异常信息
	 * @throws BusinessException
	 */
	public static void isAssignable(Class<?> superType, Class<?> subType, String message) throws BusinessException {

		notNull(superType, "待判断的类型参数不能为空");

		if ((subType == null) || (!superType.isAssignableFrom(subType)))
			ZAlert.Error(new StringBuilder().append(message).append(subType).append(" is not assignable to ")
					.append(superType).toString());
	}

	public static void hasValidLength(String str, int min, int max, String message) throws BusinessException {
		if (str == null) {
			ZAlert.Error(message);
		}
		if (str.length() < min || str.length() > max) {
			ZAlert.Error(message);
		}
	}

	public static void isNumber(String str, String message) throws BusinessException {
		if (str == null) {
			ZAlert.Error(message);
		}
		if (!NumberUtils.isDigits(str)) {
			ZAlert.Error(message);
		}
	}

}
