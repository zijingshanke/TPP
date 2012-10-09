package com.fordays.fdpay.bank.ccb.ebs;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.neza.base.Constant;
import com.neza.tool.DateUtil;

/**
 * @建设银行外联平台--客户端连接 请求交易码 6W9101 内部交易码 6W9101
 * @后端系统 IBS 交易类型 连接类
 * @交易说明 用于客户端建立连接
 */
public class EbsConnection {
	// request header
	protected String REQUEST_SN = ""; // 请求序列号 varChar(16) F 只可以使用数字
	protected String CUST_ID = ""; // 客户号
	protected String USER_ID = ""; // 操作员号
	protected String PASSWORD = ""; // 密码
	protected String TX_CODE = ""; // 交易码
	protected String LANGUAGE = ""; // 语言
	//request connection body
	protected String REM1 = ""; // 备注1
	protected String REM2 = ""; // 备注2	
	//response header
	private String RETURN_CODE = "";// 响应码 varChar(12) F
	private String RETURN_MSG = "";// 响应信息 varChar(99) T
	//response body
	private String CUR_TYPE = "";// 币种 Char(2) F

	public static String configXmlUrl = Constant.WEB_INFO_PATH + "bank"
			+ File.separator + "ccb" + File.separator + "ebs" + File.separator
			+ "init" + File.separator + "bankInterfaceConfig-CCB-EBS.xml";

	public EbsConnection() {
		init();
	}

	public void init() {
		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		REQUEST_SN = dateStr;
		CUST_ID = "GD440400000152716#02";
		// 操作员号与密码必须匹配
		USER_ID = "WLPT01";
		PASSWORD = "888888";
		LANGUAGE = "CN";
	}

	/**
	 * @description:组装请求报文头
	 */
	public StringBuffer getTransactionHeader() {
		StringBuffer str = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\" ?>");
		str.append("<TX>");
		str.append("<REQUEST_SN>" + this.getREQUEST_SN() + "</REQUEST_SN>");
		str.append("<CUST_ID>" + this.getCUST_ID() + "</CUST_ID>");
		str.append("<USER_ID>" + this.getUSER_ID() + "</USER_ID>");
		str.append("<PASSWORD>" + this.getPASSWORD() + "</PASSWORD>");
		str.append("<TX_CODE>" + this.getTX_CODE() + "</TX_CODE>");
		str.append("<LANGUAGE>" + this.getLANGUAGE() + "</LANGUAGE>");
		return str;
	}

	public String getEbsConnectionCmd() {
		this.setTX_CODE("6W9101");//
		StringBuffer str = getTransactionHeader();
		str.append("<TX_INFO>");
		str.append("<REM1>" + this.getREM1() + "</REM1>");
		str.append("<REM2>" + this.getREM2() + "</REM2>");
		str.append("</TX_INFO>");
		str.append("</TX>");
		String requestStr = str.toString().trim();
		return requestStr;
	}

	public String getEbsCmd_Test(String txcode) {
		this.setTX_CODE(txcode);//
		StringBuffer str = getTransactionHeader();
		str.append("<TX_INFO>");
		str.append("<REM1>" + this.getREM1() + "</REM1>");
		str.append("<REM2>" + this.getREM2() + "</REM2>");
		str.append("</TX_INFO>");
		str.append("</TX>");
		String requestStr = str.toString().trim();
		return requestStr;
	}

	public void closeSocketConn(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
	}

	public String getTX_CODE() {
		return TX_CODE;
	}

	public void setTX_CODE(String tx_code) {
		TX_CODE = tx_code;
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

	public String getCUR_TYPE() {
		return CUR_TYPE;
	}

	public void setCUR_TYPE(String cur_type) {
		CUR_TYPE = cur_type;
	}		
}
