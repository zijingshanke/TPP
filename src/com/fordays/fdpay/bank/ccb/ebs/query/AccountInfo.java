package com.fordays.fdpay.bank.ccb.ebs.query;

import com.fordays.fdpay.bank.ccb.ebs.EbsConnection;

/**
 * @交易名称:外联平台帐户信息查询 请求交易码 6W0101 内部交易码 6W0101
 * @description:外联平台帐户信息查询，一次往返
 */
public class AccountInfo extends EbsConnection{
	//request body
	private String ACC_BRANCH_CODE = "";// 转入一级行号 varChar(18) F
	private String ACC_NO = "";// 转入帐号 varChar(32) F 查询帐号
	private String ACC_NO1 = "";// //转出帐号 varChar(32) F
	//response body
	// private String ACC_NO="";//帐号 varChar(32) F 同请求报文
	private String ACC_TYPE = "";// 帐户类型 CHAR(2) F 代码，请参考帐户类型信息表
	private String ACC_NAME = "";// 帐户名称 VARChar(60) F
	private String OPENACC_DEPT = "";// 开户机构名称 VARChar(41) F
	private String ACC_STATUS = "";// 账户状态 VARChar(20) F 正常、冻结、部分冻结
	private String UBANK_NO = "";// 联行号 Char(10) T
	private String COUNTER_NO = "";// 会计柜台机构号 Char(10) T
	private String EXCHANGE_NO = "";// 交换号 Char(5) T	
	// ---------the end------

	public AccountInfo() {
		init();
		TX_CODE="6W0101";	
		ACC_BRANCH_CODE="441000000";
		ACC_NO="44001646343053000762";
		ACC_NO1="44001646343053000762";
	}
	
	public String getAccountInfoCmd(){		
		StringBuffer str = getTransactionHeader();

		str.append("<TX_INFO>");
		str.append("<ACC_BRANCH_CODE>"+ACC_BRANCH_CODE+"</ACC_BRANCH_CODE>");
		str.append("<ACC_NO>" + ACC_NO + "</ACC_NO>");
		str.append("<ACC_NO1>" + ACC_NO1 + "</ACC_NO1>");
		str.append("</TX_INFO>");
		str.append("</TX>");
		String cmd = str.toString();
		return cmd;
	}

	public String getACC_BRANCH_CODE() {
		return ACC_BRANCH_CODE;
	}

	public void setACC_BRANCH_CODE(String acc_branch_code) {
		ACC_BRANCH_CODE = acc_branch_code;
	}

	public String getACC_NO() {
		return ACC_NO;
	}

	public void setACC_NO(String acc_no) {
		ACC_NO = acc_no;
	}

	public String getACC_NO1() {
		return ACC_NO1;
	}

	public void setACC_NO1(String acc_no1) {
		ACC_NO1 = acc_no1;
	}


	public String getACC_TYPE() {
		return ACC_TYPE;
	}

	public void setACC_TYPE(String acc_type) {
		ACC_TYPE = acc_type;
	}

	public String getACC_NAME() {
		return ACC_NAME;
	}

	public void setACC_NAME(String acc_name) {
		ACC_NAME = acc_name;
	}

	public String getOPENACC_DEPT() {
		return OPENACC_DEPT;
	}

	public void setOPENACC_DEPT(String openacc_dept) {
		OPENACC_DEPT = openacc_dept;
	}

	public String getACC_STATUS() {
		return ACC_STATUS;
	}

	public void setACC_STATUS(String acc_status) {
		ACC_STATUS = acc_status;
	}

	public String getUBANK_NO() {
		return UBANK_NO;
	}

	public void setUBANK_NO(String ubank_no) {
		UBANK_NO = ubank_no;
	}

	public String getCOUNTER_NO() {
		return COUNTER_NO;
	}

	public void setCOUNTER_NO(String counter_no) {
		COUNTER_NO = counter_no;
	}

	public String getEXCHANGE_NO() {
		return EXCHANGE_NO;
	}

	public void setEXCHANGE_NO(String exchange_no) {
		EXCHANGE_NO = exchange_no;
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
