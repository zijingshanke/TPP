package com.fordays.fdpay.bank.ccb.ebs.query;

import com.fordays.fdpay.bank.ccb.ebs.EbsConnection;

/**
 * @交易名称：外联平台历史明细查询 请求交易码 6W0300 内部交易码 6W0300
 * @description:外联平台历史明细查询，多次往返
 */
public class HistoryDetail extends EbsConnection {
	// request body
	private String ACC_NO = "";// 帐号 varChar(32) F 查询帐号
	private String START_DATE = "";// YYYYMMDD F 查询起始日期（Char）
	private String END_DATE = "";// YYYYMMDD F 查询截至日期（Char）
	private int START_PAGE = 1;// 起始页次 Int F 整数>0
	private String POSTSTR = "";// 查询定位串(252定位串) varChar(40) T 分行可选，对应于必输分行则必须输入

	// response body
	private String BALANCE = "";// 余额 Decimal(16,2) T
	private String BALANCE1 = "";// 可用余额 Decimal(16,2) T
	private String INTEREST = "";// 帐户利息 Decimal(16,2) T
	private String INTEREST_RATE = "";// 帐户利率 Decimal(6,4) T
	private String ACC_STATUS = "";// 帐户状态 Char(20) F
	private String RESV_NAME1 = "";// 自定义名称1 varChar(99) T 分行自定义输出名称1
	private String RESV1 = "";// 自定义内容1 varChar(99) T 分行自定义输出内容1
	private String RESV_NAME2 = "";// 自定义名称2 varChar(99) T 分行自定义输出名称2
	private String RESV2 = "";// 自定义内容2 varChar(99) T 分行自定义输出内容2

	public HistoryDetail() {
		init();
		TX_CODE="6W0300";
		ACC_NO = "44001646343053000762";
		START_DATE = "20090301";
		END_DATE = "20090922";
		START_PAGE = 1;
	}

	public String getHistoryDetailCmd() {
		StringBuffer str = getTransactionHeader();

		str.append("<TX_INFO>");
		str.append("<ACC_NO>" + ACC_NO + "</ACC_NO>");
		str.append("<START_DATE>" + START_DATE + "</START_DATE>");
		str.append("<END_DATE>" + END_DATE + "</END_DATE>");
		str.append("<START_PAGE>" + START_PAGE + "</START_PAGE>");
		str.append("<POSTSTR>" + POSTSTR + "</POSTSTR>");
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

	public String getSTART_DATE() {
		return START_DATE;
	}

	public void setSTART_DATE(String start_date) {
		START_DATE = start_date;
	}

	public String getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(String end_date) {
		END_DATE = end_date;
	}

	public int getSTART_PAGE() {
		return START_PAGE;
	}

	public void setSTART_PAGE(int start_page) {
		START_PAGE = start_page;
	}

	public String getPOSTSTR() {
		return POSTSTR;
	}

	public void setPOSTSTR(String poststr) {
		POSTSTR = poststr;
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
