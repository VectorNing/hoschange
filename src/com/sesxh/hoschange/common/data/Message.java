package com.sesxh.hoschange.common.data;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 双向通信传递数据
 * <p>
 * Title:Message
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ning
 * @date 2017年2月7日
 */
public class Message {
	private String cardNumber;
	private String deviceID;
	private String deviceType;
	private String errorMessage;
	private String message;
	private String recoveryID;
	private List<String> operation;
	private String ip;
	private String sfzh;
	private String messageType;

	@JSONField(name = "CardNumber")
	public String getCardNumber() {
		return cardNumber;
	}

	@JSONField(name = "CardNumber")
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@JSONField(name = "DeviceID")
	public String getDeviceID() {
		return deviceID;
	}

	@JSONField(name = "DeviceID")
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@JSONField(name = "DeviceType")
	public String getDeviceType() {
		return deviceType;
	}

	@JSONField(name = "DeviceType")
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@JSONField(name = "ErrorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}

	@JSONField(name = "ErrorMessage")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JSONField(name = "Message")
	public String getMessage() {
		return message;
	}

	@JSONField(name = "Message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JSONField(name = "Operation")
	public List<String> getOperation() {
		return operation;
	}

	@JSONField(name = "Operation")
	public void setOperation(List<String> operation) {
		this.operation = operation;
	}

	@JSONField(name = "RecoveryID")
	public String getRecoveryID() {
		return recoveryID;
	}

	@JSONField(name = "RecoveryID")
	public void setRecoveryID(String recoveryID) {
		this.recoveryID = recoveryID;
	}

	@JSONField(name = "Ip")
	public String getIp() {
		return ip;
	}

	@JSONField(name = "Ip")
	public void setIp(String ip) {
		this.ip = ip;
	}

	@JSONField(name = "Sfzh")
	public String getSfzh() {
		return sfzh;
	}

	@JSONField(name = "Sfzh")
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	@JSONField(name = "MessageType")
	public String getMessageType() {
		return messageType;
	}
	@JSONField(name = "MessageType")
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@Override
	public String toString() {
		return "Message [cardNumber=" + cardNumber + ", deviceID=" + deviceID + ", deviceType=" + deviceType
				+ ", errorMessage=" + errorMessage + ", message=" + message + ", recoveryID=" + recoveryID
				+ ", operation=" + operation + ", ip=" + ip + ", sfzh=" + sfzh + ", messageType=" + messageType + "]";
	}

	

}
