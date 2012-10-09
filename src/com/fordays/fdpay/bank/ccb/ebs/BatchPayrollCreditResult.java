package com.fordays.fdpay.bank.ccb.ebs;

/**
 * @批量代发代扣应答报文
 */
public class BatchPayrollCreditResult extends ResultFromEbs {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Transaction_Body
	private String CREDIT_NO = "";// 凭证号 Char(12) F
	private String INDIVIDUAL_NAME1 = "";// 自定义输出名称1 varChar(99) T 分行自定义输出名称1
	private String INDIVIDUAL1 = "";// 自定义输出内容1 varChar(99) T
	private String INDIVIDUAL_NAME2 = "";// 自定义输出名称2 varChar(99) T 分行自定义输出名称2
	private String INDIVIDUAL2 = "";// 自定义输出内容2 varChar(99) T
	private String REM1 = "";// 备注1 varChar(32) T
	private String REM2 = "";// 备注2 varChar(32) T

	public String getCREDIT_NO() {
		return CREDIT_NO;
	}

	public void setCREDIT_NO(String credit_no) {
		CREDIT_NO = credit_no;
	}

	public String getINDIVIDUAL_NAME1() {
		return INDIVIDUAL_NAME1;
	}

	public void setINDIVIDUAL_NAME1(String individual_name1) {
		INDIVIDUAL_NAME1 = individual_name1;
	}

	public String getINDIVIDUAL1() {
		return INDIVIDUAL1;
	}

	public void setINDIVIDUAL1(String individual1) {
		INDIVIDUAL1 = individual1;
	}

	public String getINDIVIDUAL_NAME2() {
		return INDIVIDUAL_NAME2;
	}

	public void setINDIVIDUAL_NAME2(String individual_name2) {
		INDIVIDUAL_NAME2 = individual_name2;
	}

	public String getINDIVIDUAL2() {
		return INDIVIDUAL2;
	}

	public void setINDIVIDUAL2(String individual2) {
		INDIVIDUAL2 = individual2;
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
