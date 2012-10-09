package com.fordays.fdpay.bank.ccb.ebs;

/**
 * @建设银行外联平台--客户端连接 应答报文
 */
public class EbsConnectionResult {
	// Transaction_Header
	private String REQUEST_SN = ""; // 请求序列号 varChar(16) F 同请求报文中的序列号
	private String CUST_ID = ""; // 客户号 varChar(21) F 同请求报文中的客户号
	private String TX_CODE = ""; // 交易码 varChar(6) F 同请求报文中的交易码
	private String RETURN_CODE = ""; // 响应码 varChar(12) F 交易响应码
	private String RETURN_MSG = ""; // 响应信息 varChar(99) T 交易响应信息
	private String LANGUAGE = ""; // 语言 varChar(2) F CN，同请求报文
	// Transaction_Body
	private String REM1 = ""; // 备注1 varChar(32) T
	private String REM2 = ""; // 备注2 varChar(32) T

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

	public String getREM1() {
		return REM1;
	}

	public void setREM1(String rem1) {
		REM1 = rem1;
	}

	public String getREM2() {
		return REM2;
	}

	public void setREM2(String rem2) {
		REM2 = rem2;
	}
}
