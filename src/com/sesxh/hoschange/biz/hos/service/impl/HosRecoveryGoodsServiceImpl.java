package com.sesxh.hoschange.biz.hos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.SesframeBaseService;
import com.sesxh.hoschange.biz.hos.bpo.HosRecoveryGoodsBpo;
import com.sesxh.hoschange.biz.hos.entity.HosRecoveryGoods;
import com.sesxh.hoschange.biz.hos.service.HosRecoveryGoodsService;
import com.sesxh.hoschange.common.data.DataSet;

/** 
  * @title : HosRecoveryGoodsServiceImpl.java
  * @author  作者 E-mail: 	wwb										
  * @date 创建时间：2016年10月21日 下午4:47:03 
  * @version 1.0 
  * @parameter  
  * @since  
  * @return 
  */
@Service
public class HosRecoveryGoodsServiceImpl  extends SesframeBaseService implements  HosRecoveryGoodsService{

	@Autowired
	private HosRecoveryGoodsBpo hosRecoveryGoodsBpo;
	
	@Override
	@Transactional
	public DataSet queryHosRecoveryGoodsSet(Map<String, Object> params) throws BaseException {
		return hosRecoveryGoodsBpo.queryHosRecoveryGoodsSet(params);
	}



	@Override
	@Transactional
	public int updateHosRecoveryGoodsStateByUserId(Integer userId) throws BaseException{
		return hosRecoveryGoodsBpo.updateHosRecoveryGoodsStateByUserId(userId);
	}
	
	@Override
	@Transactional
	public int updateRecoveryGoodsByCardNumAndState(String cardNum,String recycleNum,String state) throws BaseException{
		return hosRecoveryGoodsBpo.updateRecoveryGoodsByCardNumAndState(cardNum,recycleNum, state);
	}

	@Override
	@Transactional
	public HosRecoveryGoods selectHosRecoveryGoodsByIdAndType(Integer id, String type) throws BaseException {
		return hosRecoveryGoodsBpo.selectHosRecoveryGoodsByIdAndType(id, type);
	}



	@Override
	@Transactional
	public List<String> selectImageBase64(Integer id) throws BaseException {
		return hosRecoveryGoodsBpo.selectImageBase64(id);
	}
}
