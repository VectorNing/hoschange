/**
 * @author:zhangkai
 * @date:2015年10月16日
 */
package com.sesxh.hoschange.common.proxy;

import com.sesxh.common.exception.FrameException;
import com.sesxh.frame.base.BaseAutoProxyHandler;

/**
 * @author:zhangkai
 * @date:2015年10月16日
 */
public class HoschangeAutoProxyHandler extends BaseAutoProxyHandler {

	// @Autowired
	// HttpServletRequest request;

	// @Override
	// protected Object getCustomService(Class<?> beanInterface)throws
	// FrameException {
	// String serviceId, serviceName, localId = null;
	// LogonUser logonUser = (LogonUser)
	// request.getSession(true).getAttribute(SesframeDemoGlobalNames.logonUser);
	// if (beanInterface == null) {
	// throw new FrameException("传入的服务组件为空！");
	// }
	// serviceName = beanInterface.getSimpleName() + "Impl";
	// String prefix = serviceName.substring(0, 2);
	// if (hasTwoUppser(prefix)) {
	// serviceId = serviceName;
	// } else {
	// serviceId = serviceName.substring(0, 1).toLowerCase()+
	// serviceName.substring(1);
	// }
	// try {
	// localId = this.componentFactory.getLocalComponentIdDefault(serviceId,
	// logonUser.getOrgnId());
	// } catch (BaseException e) {
	// }
	// if (localId != null) {
	// return
	// beanInterface.cast(this.applicationContextGetter.getApplicationContext().getBean(localId));
	// }
	//
	// return super.getCustomService(beanInterface);
	// }

	/**
	 * 判断参数传入的组件名称的前两位是不是都为大写<br>
	 * 如果是则不做处理，如果不是，需要将首字母变为小写（Spring3应用）
	 * 
	 * @param prefix
	 *            组件名称前缀
	 * @return Boolean类型：true Or False
	 */
	/*
	 * private boolean hasTwoUppser(String prefix) { return
	 * prefix.toUpperCase().equals(prefix); }
	 */
	@Override
	protected Object getCustomBpo(Class<?> beanInterface) throws FrameException {

		return super.getCustomBpo(beanInterface);
	}

	@Override
	protected Object getCustomDao(Class<?> beanInterface) throws FrameException {

		return super.getCustomDao(beanInterface);
	}

}
