package com.sesxh.hoschange.biz.sys.entity;
import java.io.Serializable;
import java.util.Date;
public class LogError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	public LogError() {}
	public LogError(Long id) {
		this.setId(id);
	}
	
	  public LogError(String sessionId, Date operateTime, String accept, String useragent, String requestUrl,
			String methed, String referer, String remoteIp, String params) {
		super();
		this.sessionId = sessionId;
		this.operateTime = operateTime;
		this.accept = accept;
		this.useragent = useragent;
		this.requestUrl = requestUrl;
		this.methed = methed;
		this.referer = referer;
		this.remoteIp = remoteIp;
		this.params = params;
	}
	  private Long id;
	  private java.lang.String appId;
	  private java.lang.Long userId;
	  private java.lang.String userCharId;
	  private java.lang.String sessionId;
	  private java.lang.String userName;
	  private java.util.Date operateTime;
	  private java.lang.String accept;
	  private java.lang.String type;
	  private java.lang.String useragent;
	  private java.lang.String requestUrl;
	  private java.lang.String methed;
	  private java.lang.String referer;
	  private java.lang.String remoteIp;
	  private java.lang.String params;
	  private java.lang.String exception;
	
	//<-------------------------------------------->
	
	public void setUserId(java.lang.Long userId){
	 this.userId=userId;
	}
	public java.lang.Long getUserId(){
		return this.userId;
	}
	public void setUserCharId(java.lang.String userCharId){
		this.userCharId=userCharId==null?null:userCharId.trim();
	}
	public java.lang.String getUserCharId(){
		return this.userCharId;
	}
	public void setSessionId(java.lang.String sessionId){
		this.sessionId=sessionId==null?null:sessionId.trim();
	}
	public java.lang.String getSessionId(){
		return this.sessionId;
	}
	public void setUserName(java.lang.String userName){
		this.userName=userName==null?null:userName.trim();
	}
	public java.lang.String getUserName(){
		return this.userName;
	}
	public void setOperateTime(java.util.Date operateTime){
	 this.operateTime=operateTime;
	}
	public java.util.Date getOperateTime(){
		return this.operateTime;
	}
	public void setAccept(java.lang.String accept){
		this.accept=accept==null?null:accept.trim();
	}
	public java.lang.String getAccept(){
		return this.accept;
	}
	public void setType(java.lang.String type){
		this.type=type==null?null:type.trim();
	}
	public java.lang.String getType(){
		return this.type;
	}
	public void setUseragent(java.lang.String useragent){
		this.useragent=useragent==null?null:useragent.trim();
	}
	public java.lang.String getUseragent(){
		return this.useragent;
	}
	public void setRequestUrl(java.lang.String requestUrl){
		this.requestUrl=requestUrl==null?null:requestUrl.trim();
	}
	public java.lang.String getRequestUrl(){
		return this.requestUrl;
	}
	public void setMethed(java.lang.String methed){
		this.methed=methed==null?null:methed.trim();
	}
	public java.lang.String getMethed(){
		return this.methed;
	}
	public void setReferer(java.lang.String referer){
		this.referer=referer==null?null:referer.trim();
	}
	public java.lang.String getReferer(){
		return this.referer;
	}
	public void setRemoteIp(java.lang.String remoteIp){
		this.remoteIp=remoteIp==null?null:remoteIp.trim();
	}
	public java.lang.String getRemoteIp(){
		return this.remoteIp;
	}
	public void setParams(java.lang.String params){
		this.params=params==null?null:params.trim();
	}
	public java.lang.String getParams(){
		return this.params;
	}
	public void setException(java.lang.String exception){
		this.exception=exception==null?null:exception.trim();
	}
	public java.lang.String getException(){
		return this.exception;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public java.lang.String getAppId() {
		return appId;
	}
	public void setAppId(java.lang.String appId) {
		this.appId = appId;
	}

}
