package com.sesxh.hoschange.biz.hos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.entity.HosRecord;
import com.sesxh.hoschange.biz.hos.service.HosRecordService;
import com.sesxh.hoschange.common.data.BizMsg;
import com.sesxh.hoschange.common.data.DataSet;

@Controller
@RequestMapping("/hosRecord")
public class HosRecordController extends ZBaseController{

	@Autowired
	private HosRecordService hosRecordService;
	
	private static final String prefix = "hos/";
	
	@RequestMapping(value = {"/enterHosRecord" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosRecord() {
		return prefix + "pageHosRecord";
	}
	
	/**
	 * 加载刷卡记录数据列表
	 * @param params
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/selectHosRecordSet",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object selectHosRecordSet(@RequestParam Map<String,Object> params ) throws BaseException {
		 DataSet lists =  hosRecordService.selectHosRecordSet(params);
		 return lists;
	}
	
	
	/**
	 * 衣物管理员帮助签退
	 * @param HosRecord
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/updateHosRecordService",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BizMsg updateHosRecordService(HosRecord hosRecord) throws BaseException {
		hosRecordService.updateHosRecordService(hosRecord);
		return ajaxDoneSuccess("签退成功！");
	}
}
