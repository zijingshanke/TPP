package com.fordays.fdpay.bank.ccb.ebs.query;

import com.fordays.fdpay.bank.ccb.ebs.EbsConnection;

/**
 * @交易名称 外联平台余额查询 请求交易码 6W0100 内部交易码 610100
 * @后端系统 ECTIP 交易类型 查询类
 * @外联平台余额查询，一次往返
 */
public class Balance extends EbsConnection {
	//request body
	private String ACC_NO = "";// 帐号
	//response body
	private String BALANCE = "";// 余额
	private String BALANCE1 = "";// 可用余额
	private String INTEREST = "";// 帐户利息
	private String INTEREST_RATE = "";// 帐户利率
	private String ACC_STATUS = "";// 帐户状态
	private String RESV_NAME1 = "";// 自定义名称1
	private String RESV1 = "";// 自定义内容1
	private String RESV_NAME2 = "";// 自定义 名称2
	private String RESV2 = "";// 自定义内容2

	public Balance() {
		init();		
		TX_CODE="6W0100";
		ACC_NO="44001646343053000762";
	}

	public String getBalanceCmd() {	
		StringBuffer str = getTransactionHeader();
		str.append("<TX_INFO>");
		str.append("<ACC_NO>" + ACC_NO+ "</ACC_NO>");
		str.append("</TX_INFO>");
		str.append("</TX>");
		String cmd = str.toString();
		return cmd;
	}

	public String getACC_NO() {
		return ACC_NO;
	}

	public void setACC_NO(String acc_no) {
		ACC_NO = acc_no;
	}

	public String getBALANCE() {
		return BALANCE;
	}

	public void setBALANCE(String balance) {
		BALANCE = balance;
	}

	public String getBALANCE1() {
		return BALANCE1;
	}

	public void setBALANCE1(String balance1) {
		BALANCE1 = balance1;
	}

	public String getINTEREST() {
		return INTEREST;
	}

	public void setINTEREST(String interest) {
		INTEREST = interest;
	}

	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}

	public void setINTEREST_RATE(String interest_rate) {
		INTEREST_RATE = interest_rate;
	}

	public String getACC_STATUS() {
		return ACC_STATUS;
	}

	public void setACC_STATUS(String acc_status) {
		ACC_STATUS = acc_status;
	}

	public String getRESV_NAME1() {
		return RESV_NAME1;
	}

	public void setRESV_NAME1(String resv_name1) {
		RESV_NAME1 = resv_name1;
	}

	public String getRESV1() {
		return RESV1;
	}

	public void setRESV1(String resv1) {
		RESV1 = resv1;
	}

	public String getRESV_NAME2() {
		return RESV_NAME2;
	}

	public void setRESV_NAME2(String resv_name2) {
		RESV_NAME2 = resv_name2;
	}

	public String getRESV2() {
		return RESV2;
	}

	public void setRESV2(String resv2) {
		RESV2 = resv2;
	}
}
