package com.fordays.fdpay.bank.ccb.ebs;

import java.math.BigDecimal;

/*******************************************************************************
 * @建设银行外联平台--单笔代发代扣 请求交易码 6W1303 内部交易码 6W1303
 * @后端系统 ECTIP 交易类型 代发代扣类
 * @交易说明 外联平台单笔代发代扣，一次往返
 ******************************************************************************/
public class SinglePayrollCredit extends EbsConnection {
	private String ACC_NO1 = ""; // 转出帐户 varChar(32) F
	private String BILL_CODE = ""; // 代发代扣编号 varChar(20) F 同网银代发代扣编号
	private String ACC_NO2 = ""; // 转入帐户 varChar(32) F
	private String OTHER_NAME = ""; // 对方姓名 varChar(60) F
	private BigDecimal AMOUNT = new BigDecimal(0); // 金额 Decimal(16,2) F
	private String USEOF_CODE = ""; // 用途 varChar(12) F
	private String SIGN_INFO = ""; // 签名信息 varChar(254) T
	private String SIGNCERT = ""; // 签名CA信息 采用socket连接时，建行客户端自动添加

	public SinglePayrollCredit() {
		init();

		TX_CODE = "6W1303";
		ACC_NO1 = "44001646343053000762";// 转出帐号
		BILL_CODE = "6W1303";
	}

	// 组装单笔代发代扣报文
	public String getSinglePayrollCreditCmd() {
		StringBuffer str = getTransactionHeader();

		str.append("<TX_INFO>");
		str.append("<ACC_NO1>" + ACC_NO1 + "</ACC_NO1>");
		str.append("<BILL_CODE>" + BILL_CODE + "</BILL_CODE>");
		str.append("<ACC_NO2>" + ACC_NO2 + "</ACC_NO2>");
		str.append("<OTHER_NAME>" + OTHER_NAME + "</OTHER_NAME>");
		str.append("<AMOUNT>" + AMOUNT + "</AMOUNT>");
		str.append("<USEOF>" + USEOF_CODE + "</USEOF>");
		str.append("<REM1>" + REM1 + "</REM1>");
		str.append("<REM2>" + REM2 + "</REM2>");
		str.append("</TX_INFO>");
		str.append("<SIGN_INFO>" + SIGN_INFO + "</SIGN_INFO>");
		str.append("<SIGNCERT></SIGNCERT>");
		str.append("</TX>");
		
		String cmd = str.toString().trim();
		return cmd;
	}

	public String getACC_NO1() {
		return ACC_NO1;
	}

	public void setACC_NO1(String acc_no1) {
		ACC_NO1 = acc_no1;
	}

	public String getBILL_CODE() {
		return BILL_CODE;
	}

	public void setBILL_CODE(String bill_code) {
		BILL_CODE = bill_code;
	}

	public String getACC_NO2() {
		return ACC_NO2;
	}

	public void setACC_NO2(String acc_no2) {
		ACC_NO2 = acc_no2;
	}

	public String getOTHER_NAME() {
		return OTHER_NAME;
	}

	public void setOTHER_NAME(String other_name) {
		OTHER_NAME = other_name;
	}

	public BigDecimal getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(BigDecimal amount) {
		AMOUNT = amount;
	}

	public String getUSEOF_CODE() {
		return USEOF_CODE;
	}

	public void setUSEOF_CODE(String useof_code) {
		USEOF_CODE = useof_code;
	}

	public String getSIGN_INFO() {
		return SIGN_INFO;
	}

	public void setSIGN_INFO(String sign_info) {
		SIGN_INFO = sign_info;
	}

	public String getSIGNCERT() {
		return SIGNCERT;
	}

	public void setSIGNCERT(String signcert) {
		SIGNCERT = signcert;
	}
}
