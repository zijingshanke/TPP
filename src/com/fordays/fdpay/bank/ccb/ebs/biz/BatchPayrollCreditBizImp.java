package com.fordays.fdpay.bank.ccb.ebs.biz;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.bank.BankUtil;
import com.fordays.fdpay.bank.ccb.ebs.BatchPayrollCredit;
import com.fordays.fdpay.bank.ccb.ebs.BatchPayrollCreditResult;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawList;
import com.neza.encrypt.XmlUtil;
import com.neza.exception.AppException;

public class BatchPayrollCreditBizImp implements BatchPayrollCreditBiz {

	// 组装批量代发代扣指令
	public String getBatchPayrollCreditCmd(List tempdrawlist)
			throws AppException {
		BatchPayrollCredit batch = new BatchPayrollCredit();
		int totalNumber = 0;
		BigDecimal totalAmount = new BigDecimal(0);
		
		StringBuffer detailCon = new StringBuffer();		

		// |序号|帐号|户名|金额|摘要
		// |01|30900003200000000088|张三|150|工资
		for (int i = 0; i < tempdrawlist.size(); i++) {
			DrawList drawlist = (DrawList) tempdrawlist.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent=draw.getAgent();			
			
			detailCon.append("|" + i + "|" + draw.getCardNo() + "|");
			String accountName=agent.getName();// 账户姓名
			BigDecimal detaiAmount = draw.getAmount();// 明细金额
			detailCon.append(accountName + "|" + detaiAmount);
			detailCon.append("|" + batch.getUSEOF_CODE() + "\r\n");
			totalNumber++;// 笔数统计
			totalAmount = totalAmount.add(detaiAmount);// 金额统计
		}
		batch.setCOUNT(Integer.toString(totalNumber));
		batch.setAMOUNT(totalAmount.toString());
		batch.setFILE_CTX(BankUtil.setHtmlTag(detailCon.toString()));

		// FILE_CTX 代发代扣文件内容 varChar F 说明见下

		// "1.可以有多个FILE_CTX，对下面五个字符需要进行编码，空格、>、<、&、“、'，空格编码成&nbsp;，<编码成&lt;，>编码成&gt;，&编码成&amp;，""编码成&quot;，'编码成&apos;
		// 2.如果文件大于9999byte，必须将内容分割成几个<FILE_CTX></FILE_CTX>，每一个<FILE_CTX></FILE_CTX>的长度不能超过9999byte，同时要注意的是，分隔的位置必须在一行的末尾，如果从中间分割，则会出错"
		// 网上银行的批量代发格式为
		// |序号|帐号|户名|金额|摘要
		// 例如
		// |01|30900003200000000088|张三|150|工资

		String cmd = batch.getBatchPayrollCreditCmd();
		return cmd;
	}

	// 解析批量代发代扣结果
	public BatchPayrollCreditResult parseResultData(HttpServletRequest request)
			throws AppException {
		String queryString = request.getQueryString();
		StringBuffer str = new StringBuffer(queryString);
		XmlUtil xml = new XmlUtil();
		Document document = xml.readResult(str);
		String REQUEST_SN = xml.getTextByNode(document, "//TX/REQUEST_SN");
		String CUST_ID = xml.getTextByNode(document, "//TX/CUST_ID");
		String TX_CODE = xml.getTextByNode(document, "//TX/TX_CODE");
		String RETURN_CODE = xml.getTextByNode(document, "//TX/RETURN_CODE");
		String RETURN_MSG = xml.getTextByNode(document, "//TX/RETURN_MSG");
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

		BatchPayrollCreditResult result = new BatchPayrollCreditResult();
		result.setREQUEST_SN(REQUEST_SN);
		result.setCUST_ID(CUST_ID);
		result.setTX_CODE(TX_CODE);
		result.setRETURN_CODE(RETURN_CODE);
		result.setRETURN_MSG(RETURN_MSG);
		result.setLANGUAGE(LANGUAGE);
		result.setCREDIT_NO(CREDIT_NO);
		result.setINDIVIDUAL_NAME1(INDIVIDUAL_NAME1);
		result.setINDIVIDUAL1(INDIVIDUAL1);
		result.setINDIVIDUAL_NAME2(INDIVIDUAL_NAME2);
		result.setINDIVIDUAL2(INDIVIDUAL2);
		result.setREM1(REM1);
		result.setREM2(REM2);
		return result;
	}
}
