package com.sesxh.hoschange.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.sesxh.common.db.Database.Descriptor;
import com.sesxh.frame.base.BaseService;

/**
 * 
 * @author:zhangkai
 * @date:2015年10月23日
 */
public class SesframeBaseService extends BaseService {

	@Autowired
	protected Descriptor dataBaseDescriptor;

}
