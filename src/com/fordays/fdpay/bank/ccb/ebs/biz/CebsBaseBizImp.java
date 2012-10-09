package com.fordays.fdpay.bank.ccb.ebs.biz;

import java.net.Socket;
import org.dom4j.Document;
import com.fordays.fdpay.bank.BankUtil;
import com.fordays.fdpay.bank.ccb.ebs.EbsConnection;
import com.neza.encrypt.XmlUtil;
import com.neza.exception.AppException;

public class CebsBaseBizImp implements CebsBaseBiz {

	/**
	 * @description:
	 * @param String
	 *            ebsCmd
	 * @return String ebsResult
	 */
	@SuppressWarnings("unused")
	public String getEbsResult(String ebsCmd) throws AppException {
		String ebsResult = "";
		XmlUtil xml = new XmlUtil();
		Document configDoc = BankUtil.loadXml(EbsConnection.configXmlUrl);

		String host = xml.getTextByNode(configDoc,
				"//BANK/CCB/EBS/ClientConn/clientHost");
		int port = Integer.parseInt(xml.getTextByNode(configDoc,
				"//BANK/CCB/EBS/ClientConn/clientHost"));

		ebsResult = getResultAsSocket(host, port, ebsCmd);
		
		return ebsResult;
	}

	public String getResultAsSocket(String host, int port, String ebsCmd)
			throws AppException {
		Socket socket = BankUtil.initSocket(host, port);
		String result = BankUtil.getResultAsSocket(socket, ebsCmd);
		return result;
	}

	public String getTxCode(String ebsresult) throws AppException {
		StringBuffer str = new StringBuffer(ebsresult);
		XmlUtil xml = new XmlUtil();
		Document document = xml.readResult(str);
		String TX_CODE = xml.getTextByNode(document, "//TX/TX_CODE");
		System.out.println("请求的交易码是：》》" + TX_CODE);
		return TX_CODE;
	}	
}
