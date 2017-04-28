package com.sesxh.hoschange.localize.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.localize.entity.CompDynaCfg;

/**
 * 动态组件多版本实现Dao层接口类
 */
@Repository("com.sdses.frame.common.localize.dao.CompDynaCfgDAO")
public interface CompDynaCfgDAO {
	/**
	 * 查询所有的本地化组件信息
	 * 
	 * @return List<CompDynaCfg>类型，本地化组件的list列表
	 * @throws BaseException
	 */
	List<CompDynaCfg> getAllCompDynaCfg() throws BaseException;
}