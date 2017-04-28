package com.sesxh.hoschange.biz.sys.service.impl;

import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.common.exception.BusinessException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.sys.entity.LogError;
import com.sesxh.hoschange.biz.sys.service.ILogErrorService;
import com.sesxh.hoschange.common.data.DataSet;
import com.sesxh.hoschange.common.data.ParamMap;

@Service
public class LogErrorServiceImpl extends SesframeBaseService implements ILogErrorService {

	@Async
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createLogError(LogError logError) throws BusinessException {
		// logErrorMapper.insertSelective(logError);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public LogError loadLogError(Integer id) {
		if (id != null) {
			return null;// return logErrorMapper.selectByPrimaryKey(id);
		}
		return null;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deletLogError(Integer id) throws BaseException {
		if (id != null) {
			// logErrorMapper.deleteByPrimaryKey(id);
		} else {
			throw new BusinessException("请选择要删除的数据");
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public DataSet loadLogErrorList(Map<String, Object> params) throws BaseException {
		ParamMap pm = ParamMap.filterParam(params);
		String username = pm.getStrParam("username");
		if (pm != null) {
			pm.updateParam("username", "%" + username + "%");
		}

		// DataSet ds=DataSet.newDS(logErrorMapper.selectLogErrorListCount(pm),
		// logErrorMapper.selectLogErrorList(pm));
		return null;
	}
}