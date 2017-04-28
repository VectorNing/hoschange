package com.sesxh.hoschange.biz.hos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.base.ZBaseController;
import com.sesxh.hoschange.biz.hos.service.HosRecoveryGoodsService;
import com.sesxh.hoschange.common.data.DataSet;

/**
 * @title : HosRecoveryGoodsController.java
 * @author 作者 E-mail: wwb
 * @date 创建时间：2016年10月21日 下午4:50:20
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
@RequestMapping("/hosRecoveryGoods")
public class HosRecoveryGoodsController extends ZBaseController {

	@Autowired
	private HosRecoveryGoodsService hosRecoveryGoodsService;

	private static final String prefix = "hos/";

	@RequestMapping(value = "/queryhosRecoveryGoodsSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object queryHosRecoveryGoodsSet(@RequestParam Map<String, Object> params) throws BaseException {
		DataSet lists = hosRecoveryGoodsService.queryHosRecoveryGoodsSet(params);
		return lists;
	}

	@RequestMapping(value = {
			"/enterHosRecoveryGoods" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String enterHosRecoveryGoods(Model model) {
		return prefix + "pageRecoveryGoods";
	}

	@RequestMapping(value = { "/selectImageBase64" }, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String selectImageBase64(Integer id, Model model) throws BaseException {
		List<String> base64Strs = hosRecoveryGoodsService.selectImageBase64(id);
		if (base64Strs.size() == 0 || base64Strs == null) {
			return prefix + "resHosrecoveryImageError";
		} else {
			model.addAttribute("base64", base64Strs);
			return prefix + "resHosrecoveryImage";
		}
	}
}
