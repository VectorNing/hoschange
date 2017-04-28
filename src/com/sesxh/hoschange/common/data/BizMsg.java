package com.sesxh.hoschange.common.data;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author zhaohuatai
 *
 */
public class BizMsg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode ;
	private String message;
	private String forwardUrl;
	private Map<String,Object> data;
	private String StrData;
	
	private String isOrNoShoe;//在设备签到，退 1：衣柜 2 鞋柜
	private String sign;//签到签退
	private Integer recordId;//刷卡记录id
	
	public BizMsg() {
		super();
	}
	public BizMsg(int statusCode) {
		super();
		this.statusCode = statusCode;
	}
	public static BizMsg newMSG(int statusCode) {
		return new BizMsg( statusCode,  null);
	}
	public static BizMsg newMSG(int statusCode, String message) {
		return new BizMsg( statusCode,  message);
	}
	
	public BizMsg(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	public BizMsg(int statusCode, String message, String forwardUrl) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.forwardUrl = forwardUrl;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getStrData() {
		return StrData;
	}
	public void setStrData(String strData) {
		StrData = strData;
	}
	
	public String getIsOrNoShoe() {
		return isOrNoShoe;
	}
	public void setIsOrNoShoe(String isOrNoShoe) {
		this.isOrNoShoe = isOrNoShoe;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	@Override
	public String toString() {
		return "BizMsg [statusCode=" + statusCode + ", message=" + message + ", forwardUrl=" + forwardUrl + ", data="
				+ data + ", StrData=" + StrData + "]";
	}
	
	//----------------------------------------------------
	
	
	
}
