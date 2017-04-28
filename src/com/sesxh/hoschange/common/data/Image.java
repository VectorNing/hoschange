package com.sesxh.hoschange.common.data;


import com.alibaba.fastjson.annotation.JSONField;

public class Image {
	private String date;
	private String base64;
	private String hrgid;
	private String filePath;
	
	public String getHrgid() {
		return hrgid;
	}
	public void setHrgid(String hrgid) {
		this.hrgid = hrgid;
	}
	@JSONField(name = "Date")
	public String getDate() {
		return date;
	}
	@JSONField(name = "Date")
	public void setDate(String date) {
		this.date = date;
	}
	@JSONField(name = "Base64")
	public String getBase64() {
		return base64;
	}
	@JSONField(name = "Base64")
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	@JSONField(name = "FilePath")
	public String getFilePath() {
		return filePath;
	}
	@JSONField(name = "FilePath")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "Image [date=" + date + ", base64=" + base64 + ", hrgid=" + hrgid + ", filePath=" + filePath + "]";
	}
}
