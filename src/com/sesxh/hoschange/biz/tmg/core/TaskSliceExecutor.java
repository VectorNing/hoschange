package com.sesxh.hoschange.biz.tmg.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.FrameException;
import com.sesxh.frame.common.db.SessionManager;
import com.sesxh.hoschange.global.CodeNames;

/**
 * 任务片断执行器，实现ApplicationContextAware接口
 */
@Component
public class TaskSliceExecutor implements ApplicationContextAware {
	@Autowired
	SessionManager sessionManager;

	private ApplicationContext applicationContext;

	/**
	 * 执行任务片断
	 * 
	 * @param rwnrlx
	 * @param rwnr
	 * @param rwcs
	 * @return boolean
	 * @throws BaseException
	 */
	public boolean execute(String rwnrlx, String rwnr, String rwcs) throws BaseException {
		try {
			if (CodeNames.RWNRLX.dbProcedure.equals(rwnrlx)) {
				//
				// CallableSQLExecutor sql =
				// this.sessionManager.getSession().getCallableSQLExecutor();
				// sql.setSql("{ call " + rwnr + "(:rwcs,:rwjg) }");
				// sql.setInString("rwcs", rwcs);
				// sql.setOutInt("rwjg");
				// HashMap<String, Object> resList;
				// resList = sql.prepareCall();
				// int rs = (Integer) resList.get("rwjg");
				// if (rs == 0) {
				// return false;
				// }
				return true;

			} else if (CodeNames.RWNRLX.javaCode.equals(rwnrlx)) {
				SesTaskSlice sesTaskSlice;

				sesTaskSlice = this.applicationContext.getBean(rwnr, SesTaskSlice.class);
				return sesTaskSlice.execute(rwcs);

			}
			throw new FrameException("不支持的任务类型:" + rwnrlx + "！");

		} catch (SecurityException e) {
			throw new FrameException(e);
		}
	}

	/**
	 * 设置应用上下文
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}
}
