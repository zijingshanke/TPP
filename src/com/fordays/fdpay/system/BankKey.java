package com.fordays.fdpay.system;

import org.apache.struts.action.ActionForm;


public class BankKey extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String bankName;
	private String keyType;
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
			this.bankName=bankName;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

}
