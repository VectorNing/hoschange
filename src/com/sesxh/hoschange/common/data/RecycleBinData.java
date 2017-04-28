package com.sesxh.hoschange.common.data;


import com.alibaba.fastjson.annotation.JSONField;

public class RecycleBinData {
	private String deviceType;
	private String deviceID;
	private String recoveryState;
	private Image image;
	private String recoveryID;

	@JSONField(name = "DeviceType")
	public String getDeviceType() {
		return deviceType;
	}

	@JSONField(name = "DeviceType")
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@JSONField(name = "DeviceID")
	public String getDeviceID() {
		return deviceID;
	}

	@JSONField(name = "DeviceID")
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@JSONField(name = "RecoveryState")
	public String getRecoveryState() {
		return recoveryState;
	}

	@JSONField(name = "RecoveryState")
	public void setRecoveryState(String recoveryState) {
		this.recoveryState = recoveryState;
	}

	@JSONField(name = "Image")
	public Image getImage() {
		return image;
	}

	@JSONField(name = "Image")
	public void setImage(Image image) {
		this.image = image;
	}
	@JSONField(name = "RecoveryID")
	public String getRecoveryID() {
		return recoveryID;
	}
	@JSONField(name = "RecoveryID")
	public void setRecoveryID(String recoveryID) {
		this.recoveryID = recoveryID;
	}
	@Override
	public String toString() {
		return "RecycleBinData [deviceType=" + deviceType + ", deviceID=" + deviceID + ", recoveryState="
				+ recoveryState + ", image=" + image + ", recoveryID=" + recoveryID + "]";
	}
}
