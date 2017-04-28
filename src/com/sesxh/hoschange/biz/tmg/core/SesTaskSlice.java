package com.sesxh.hoschange.biz.tmg.core;

import com.sesxh.base.BaseException;

/**
 * 任务片段
 */
public interface SesTaskSlice {
	public boolean execute(String paras) throws BaseException;
}
