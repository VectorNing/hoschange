package com.sesxh.hoschange.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.sesxh.frame.base.BaseBpo;
import com.sesxh.frame.common.db.Database;

/**
 * 
 * @author:zhangkai
 * @date:2015年10月23日
 */
public class SesframeBaseBpo extends BaseBpo {

	@Autowired
	protected Database.Descriptor dataBaseDescriptor;

}
