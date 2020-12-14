package kr.ac.sunmoon.urs.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message implements Serializable {
	private String code;
	@JsonIgnore
	private String cardNo;
	private String status;
	@JsonIgnore
	private int lockDeviceNo;
	
	public Message() {
	}

	public Message(String code, String cardNo, String status, int lockDeviceNo) {
		super();
		this.code = code;
		this.cardNo = cardNo;
		this.status = status;
		this.lockDeviceNo = lockDeviceNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLockDeviceNo() {
		return lockDeviceNo;
	}

	public void setLockDeviceNo(int lockDeviceNo) {
		this.lockDeviceNo = lockDeviceNo;
	}
}
