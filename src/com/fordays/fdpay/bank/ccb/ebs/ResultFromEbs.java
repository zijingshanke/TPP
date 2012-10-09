package com.fordays.fdpay.bank.ccb.ebs;

import org.apache.struts.action.ActionForm;

public class ResultFromEbs extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Transaction_Header
	private String REQUEST_SN;// 请求序列号 varChar(16) F
	private String CUST_ID;// 客户号 varChar(21) F 同请求报文中的客户号
	private String TX_CODE;// 交易码 Char(6) F 同请求报文中的交易码
	private String RETURN_CODE;// 响应码 varChar(12) F 交易响应码
	private String RETURN_MSG;// 响应信息 varChar(99) T 交易响应信息
	private String LANGUAGE;// 语言 Char(2) F CN，同请求报文

	public String getREQUEST_SN() {
		return REQUEST_SN;
	}

	public void setREQUEST_SN(String request_sn) {
		REQUEST_SN = request_sn;
	}

	public String getCUST_ID() {
		return CUST_ID;
	}

	public void setCUST_ID(String cust_id) {
		CUST_ID = cust_id;
	}

	public String getTX_CODE() {
		return TX_CODE;
	}

	public void setTX_CODE(String tx_code) {
		TX_CODE = tx_code;
	}

	public String getRETURN_CODE() {
		return RETURN_CODE;
	}

	public void setRETURN_CODE(String return_code) {
		RETURN_CODE = return_code;
	}

	public String getRETURN_MSG() {
		return RETURN_MSG;
	}

	public void setRETURN_MSG(String return_msg) {
		RETURN_MSG = return_msg;
	}

	public String getLANGUAGE() {
		return LANGUAGE;
	}

	public void setLANGUAGE(String language) {
		LANGUAGE = language;
	}
}
