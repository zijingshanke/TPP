package com.fordays.fdpay.bank.ccb.ebs.biz;

import java.math.BigDecimal;
import java.util.List;

import org.dom4j.Document;

import com.fordays.fdpay.agent.Account;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.bank.ccb.ebs.SinglePayrollCredit;
import com.fordays.fdpay.bank.ccb.ebs.SinglePayrollCreditResult;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawList;
import com.neza.encrypt.XmlUtil;
import com.neza.exception.AppException;

/*******************************************************************************
 * @建设银行外联平台-单笔代发代扣业务实现类
 ******************************************************************************/
public class SinglePayrollCreditBizImp implements SinglePayrollCreditBiz {

	// 组装单笔代发代扣订单
	public String getSinglePayrollCmd(Draw draw) throws AppException {
		SinglePayrollCredit single = new SinglePayrollCredit();
		single.setACC_NO2("");// 转入帐户 varChar(32) F
		single.setOTHER_NAME("");// 对方姓名 varChar(60) F
		single.setAMOUNT(new BigDecimal(0));// 金额 Decimal(16,2) F
		single.setUSEOF_CODE("");// 用途 varChar(12) F

		String cmd = single.getSinglePayrollCreditCmd();
		return cmd;
	}

	// 组装单笔代发代扣指令
	public String getSinglePayrollCmd(List tempdrawlist) throws AppException {
		DrawList drawlist = (DrawList) tempdrawlist.get(0);

		Draw draw = drawlist.getDraw();
		Agent agent=draw.getAgent();
		
		String accountName=agent.getName();
		String cardNo = draw.getCardNo();
		BigDecimal amount = draw.getAmount();

		SinglePayrollCredit single = new SinglePayrollCredit();
		single.setACC_NO2(cardNo);// 转入帐户 varChar(32) F
		single.setOTHER_NAME(accountName);// 对方姓名 varChar(60) F
		single.setAMOUNT(amount);// 金额 Decimal(16,2) F
		single.setUSEOF_CODE("");// 用途 varChar(12) F

		String cmd = single.getSinglePayrollCreditCmd();
		return cmd;
	}

	/**
	 * @description: 解析单笔代发代扣结果
	 * @param String
	 *            queryString
	 * @return SinglePayrollCreditResult
	 */
	public SinglePayrollCreditResult getSinglePayrollResult(String queryString)
			throws AppException {
		StringBuffer str = new StringBuffer(queryString);
		XmlUtil xml = new XmlUtil();
		Document document = xml.readResult(str);
		String REQUEST_SN = xml.getTextByNode(document, "//TX/REQUEST_SN");
		String CUST_ID = xml.getTextByNode(document, "//TX/CUST_ID");
		String TX_CODE = xml.getTextByNode(document, "//TX/TX_CODE");
		String RETURN_CODE = xml.getTextByNode(document, "//TX/RETURN_CODE");
		String LANGUAGE = xml.getTextByNode(document, "//TX/LANGUAGE");
		String CREDIT_NO = xml
				.getTextByNode(document, "//TX/TX_INFO/CREDIT_NO");
		String INDIVIDUAL_NAME1 = xml.getTextByNode(document,
				"//TX/TX_INFO/INDIVIDUAL_NAME1");
		String INDIVIDUAL1 = xml.getTextByNode(document,
				"//TX/TX_INFO/INDIVIDUAL1");
		String INDIVIDUAL_NAME2 = xml.getTextByNode(document,
				"//TX/TX_INFO/INDIVIDUAL_NAME2");
		String INDIVIDUAL2 = xml.getTextByNode(document,
				"//TX/TX_INFO/INDIVIDUAL2");

		String REM1 = xml.getTextByNode(document, "//TX/TX_INFO/REM1");
		String REM2 = xml.getTextByNode(document, "//TX/TX_INFO/REM2");

		SinglePayrollCreditResult result = new SinglePayrollCreditResult();
		result.setREQUEST_SN(REQUEST_SN);
		result.setCUST_ID(CUST_ID);
		result.setTX_CODE(TX_CODE);
		result.setRETURN_CODE(RETURN_CODE);
		result.setLANGUAGE(LANGUAGE);
		result.setCREDIT_NO(CREDIT_NO);
		result.setINDIVIDUAL_NAME1(INDIVIDUAL_NAME1);
		result.setINDIVIDUAL1(INDIVIDUAL1);
		result.setINDIVIDUAL_NAME2(INDIVIDUAL_NAME2);
		result.setINDIVIDUAL2(INDIVIDUAL2);
		result.setREM1(REM1);
		result.setREM2(REM2);
		return result;
		// <?xml version="1.0" encoding="GB2312" standalone="yes" ?>
		// <TX>
		// <REQUEST_SN>请求序列码</REQUEST_SN>
		// <CUST_ID>客户号</CUST_ID>
		// <TX_CODE>6W1303</TX_CODE>
		// <RETURN_CODE>返回码</RETURN_CODE>
		// <RETURN_MSG>返回码说明</RETURN_MSG>
		// <LANGUAGE>CN</LANGUAGE>
		// <TX_INFO>
		// <CREDIT_NO>凭证号</CREDIT_NO>
		// <INDIVIDUAL_NAME1>自定义输出名称1</INDIVIDUAL_NAME1>
		// <INDIVIDUAL1>自定义输出内容1</INDIVIDUAL1>
		// <INDIVIDUAL_NAME2>自定义输出名称2</INDIVIDUAL_NAME2>
		// <INDIVIDUAL2>自定义输出内容2</INDIVIDUAL2>
		// <REM1>备注1</REM1>
		// <REM2>备注2</REM2>
		// </TX_INFO>
		// </TX>
	}
}
