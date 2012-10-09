package com.fordays.fdpay.bank.ccb.ebs;

/*******************************************************************************
 * @外联平台批量代发代扣 请求交易码 6W1403 内部交易码 6W1403
 * @后端系统 ECTIP 交易类型 代发代扣类 交易说明 外联平台批量代发代扣，一次往返。
 ******************************************************************************/
public class BatchPayrollCredit extends EbsConnection {
	// Transaction_Body
	private String ACC_NO1 = "";// 转出帐户 varChar(32) F
	private String BILL_CODE = "";// 代发代扣编号 varChar(20) F 同网银代发代扣编号
	private String AMOUNT = "";// 总金额 Decimal(16,2) F >0
	private String COUNT = "";// 总笔数 Decimal(7,0) F >0
	private String USEOF_CODE = "";// 用途 varChar(12) F
	private String FILE_CTX = "";// 代发代扣文件内容 varChar F 说明见下
	// "1.可以有多个FILE_CTX，对下面五个字符需要进行编码，空格、>、<、&、“、'，
	// 空格编码成&nbsp;，
	// <编码成&lt;，>编码成&gt;，&编码成&amp;，""编码成&quot;，'编码成&apos;
	// 2.如果文件大于9999byte，必须将内容分割成几个<FILE_CTX></FILE_CTX>，
	// 每一个<FILE_CTX></FILE_CTX>的长度不能超过9999byte，同时要注意的是，分隔的位置必须在一行的末尾，如果从中间分割，则会出错"
	// 网上银行的批量代发格式为
	// |序号|帐号|户名|金额|摘要
	// 例如
	// |01|30900003200000000088|张三|150|工资
	private String SIGN_INFO = "";// 签名信息
	private String SIGNCERT = "";// 签名CA信息 客户端自动添加

	
	public BatchPayrollCredit() {
		init();
		TX_CODE = "6W1403";
		ACC_NO1 = "44001646343053000762";// 转出帐号
		BILL_CODE = "6W1403";
		USEOF_CODE = "";
	}
	
	public String getBatchPayrollCreditCmd() {		
		StringBuffer str = getTransactionHeader();
		str.append("<TX_INFO>");
		str.append("<ACC_NO1>" + this.getACC_NO1() + "</ACC_NO1>");
		str.append("<BILL_CODE>" + this.getBILL_CODE() + "</BILL_CODE>");
		str.append("<AMOUNT>" + this.getAMOUNT() + "</AMOUNT>");
		str.append("<COUNT>" + this.getCOUNT() + "</COUNT>");
		str.append("<USEOF>" + this.getUSEOF_CODE() + "</USEOF>");
		str.append("<FILE_CTX>" + this.getFILE_CTX() + "</FILE_CTX>");
		str.append("<REM1>" + this.getREM1() + "</REM1>");
		str.append("<REM2>" + this.getREM2() + "</REM2>");
		str.append("</TX_INFO>");
		str.append("<SIGN_INFO>" + this.getSIGN_INFO() + "</SIGN_INFO>");
		str.append("<SIGNCERT></SIGNCERT>");
		str.append("</TX>");
		String requestStr = str.toString().trim();
		return requestStr;
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

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String amount) {
		AMOUNT = amount;
	}

	public String getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(String count) {
		COUNT = count;
	}

	public String getUSEOF_CODE() {
		return USEOF_CODE;
	}

	public void setUSEOF_CODE(String useof_code) {
		USEOF_CODE = useof_code;
	}

	public String getFILE_CTX() {
		return FILE_CTX;
	}

	public void setFILE_CTX(String file_ctx) {
		FILE_CTX = file_ctx;
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
