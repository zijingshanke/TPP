package com.fordays.fdpay.bank.ccb.ebs.query;

import com.fordays.fdpay.bank.ccb.ebs.EbsConnection;

/**
 * @交易名称： 外联平台当日明细查询 请求交易码 6W0400 内部交易码 6W0400
 * @description:外联平台当日明细查询，多次往返
 */
public class TodayDetail extends EbsConnection {
	// request body
	private String ACC_NO = "";// 帐号 varChar(32) F
	private int STARTPAGE = 1;// 起始页次 Int F 整数>0
	private String POSTSTR = "";// 查询定位串(252定位串) varChar(40) T 分行可选，对应于必输分行则必须输入
	// response body
	private String ACC_NAME = "";// 本方帐户名称 varChar(60) F 30个汉字
	private String ACC_DEPT = "";// 本方帐户开户机构 varChar(40) F 20个汉字
	private String ACC_STATUS = "";// 本方账户状态 varChar(20) F 10个汉字：正常、冻结等
	private String INTEREST_RATE = "";// 本方帐户利率 Decimal(6,4) T
	private int CUR_PAGE = 1;// 当前页次 Int T 整数>0
	private int PAGE_COUNT = 1;// 总页次 Int F 整数>0
	private String INDIVIDUAL_NAME1 = "";// varChar(99) 分行自定义输出名称1
	private String INDIVIDUAL_NAME2 = "";// varChar(99) 分行自定义输出名称2
	// response body detail (多条)
	private String CREDIT_TYPE = "";// 凭证种类 varChar(10) F 5个汉字
	private String CREDIT_NO = "";// 凭证号码 Char(12) F
	private String ABSTRACT = "";// 摘要 varChar(12) F 6个汉字
	private String AMOUNT = "";// 发生金额 Decimal(16,2) F
	private String BALANCE = "";// 余额 Decimal(16,2) F
	private String dORc = "";// 借贷标志 Char(1) F 0:借 1:贷
	private String ACC_NO1 = "";// 对方账号 varChar(32) T
	private String ACC_NAME1 = "";// 对方账户名称 varChar(60) T 30个汉字
	private String INDIVIDUAL1 = "";// 自定义输出内容1 varChar(99) T
	private String INDIVIDUAL2 = "";// 自定义输出内容2 varChar(99) T
	private String REM_CTX1 = "";// 备注内容1 varChar(99) T
	private String REM_CTX2 = "";// 备注内容 varChar(99) T

	public TodayDetail() {
		init();
		TX_CODE = "6W0400";
		ACC_NO = "44001646343053000762";
		STARTPAGE = 1;
	}

	public String getTodayDetailCmd() {
		StringBuffer str = getTransactionHeader();
		str.append("<TX_INFO>");
		str.append("<ACC_NO>" + ACC_NO + "</ACC_NO>");
		str.append("<STARTPAGE>" + STARTPAGE + "</STARTPAGE>");
		str.append("<POSTSTR>" + POSTSTR + "</POSTSTR>");
		str.append("<REM1>" + REM1 + "</REM1>");
		str.append("<REM2>" + REM2 + "</REM2>");
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

	public int getSTARTPAGE() {
		return STARTPAGE;
	}

	public void setSTARTPAGE(int startpage) {
		STARTPAGE = startpage;
	}

	public String getPOSTSTR() {
		return POSTSTR;
	}

	public void setPOSTSTR(String poststr) {
		POSTSTR = poststr;
	}

	public String getACC_NAME() {
		return ACC_NAME;
	}

	public void setACC_NAME(String acc_name) {
		ACC_NAME = acc_name;
	}

	public String getACC_DEPT() {
		return ACC_DEPT;
	}

	public void setACC_DEPT(String acc_dept) {
		ACC_DEPT = acc_dept;
	}

	public String getACC_STATUS() {
		return ACC_STATUS;
	}

	public void setACC_STATUS(String acc_status) {
		ACC_STATUS = acc_status;
	}

	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}

	public void setINTEREST_RATE(String interest_rate) {
		INTEREST_RATE = interest_rate;
	}

	public int getCUR_PAGE() {
		return CUR_PAGE;
	}

	public void setCUR_PAGE(int cur_page) {
		CUR_PAGE = cur_page;
	}

	public int getPAGE_COUNT() {
		return PAGE_COUNT;
	}

	public void setPAGE_COUNT(int page_count) {
		PAGE_COUNT = page_count;
	}

	public String getINDIVIDUAL_NAME1() {
		return INDIVIDUAL_NAME1;
	}

	public void setINDIVIDUAL_NAME1(String individual_name1) {
		INDIVIDUAL_NAME1 = individual_name1;
	}

	public String getINDIVIDUAL_NAME2() {
		return INDIVIDUAL_NAME2;
	}

	public void setINDIVIDUAL_NAME2(String individual_name2) {
		INDIVIDUAL_NAME2 = individual_name2;
	}

	public String getCREDIT_TYPE() {
		return CREDIT_TYPE;
	}

	public void setCREDIT_TYPE(String credit_type) {
		CREDIT_TYPE = credit_type;
	}

	public String getCREDIT_NO() {
		return CREDIT_NO;
	}

	public void setCREDIT_NO(String credit_no) {
		CREDIT_NO = credit_no;
	}

	public String getABSTRACT() {
		return ABSTRACT;
	}

	public void setABSTRACT(String abstract1) {
		ABSTRACT = abstract1;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String amount) {
		AMOUNT = amount;
	}

	public String getBALANCE() {
		return BALANCE;
	}

	public void setBALANCE(String balance) {
		BALANCE = balance;
	}

	public String getDORc() {
		return dORc;
	}

	public void setDORc(String rc) {
		dORc = rc;
	}

	public String getACC_NO1() {
		return ACC_NO1;
	}

	public void setACC_NO1(String acc_no1) {
		ACC_NO1 = acc_no1;
	}

	public String getACC_NAME1() {
		return ACC_NAME1;
	}

	public void setACC_NAME1(String acc_name1) {
		ACC_NAME1 = acc_name1;
	}

	public String getINDIVIDUAL1() {
		return INDIVIDUAL1;
	}

	public void setINDIVIDUAL1(String individual1) {
		INDIVIDUAL1 = individual1;
	}

	public String getINDIVIDUAL2() {
		return INDIVIDUAL2;
	}

	public void setINDIVIDUAL2(String individual2) {
		INDIVIDUAL2 = individual2;
	}

	public String getREM_CTX1() {
		return REM_CTX1;
	}

	public void setREM_CTX1(String rem_ctx1) {
		REM_CTX1 = rem_ctx1;
	}

	public String getREM_CTX2() {
		return REM_CTX2;
	}

	public void setREM_CTX2(String rem_ctx2) {
		REM_CTX2 = rem_ctx2;
	}
}
