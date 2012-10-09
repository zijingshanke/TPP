package com.fordays.fdpay.bank.ccb.ebs.biz;

import org.dom4j.Document;
import com.fordays.fdpay.bank.XmlUtil;
import com.fordays.fdpay.bank.ccb.ebs.EbsConnection;
import com.fordays.fdpay.bank.ccb.ebs.query.AccountInfo;
import com.fordays.fdpay.bank.ccb.ebs.query.Balance;
import com.fordays.fdpay.bank.ccb.ebs.query.HistoryDetail;
import com.fordays.fdpay.bank.ccb.ebs.query.TodayDetail;
import com.neza.exception.AppException;

public class TestCCB_EBS {
	public static void main(String[] args) {
		String ebscmd = "";
		// EbsConnection conn = new EbsConnection();
		// ebscmd = conn.getEbsConnectionCmd();

		// Balance info = new Balance();
		// ebscmd = info.getBalanceCmd();

		// AccountInfoQuery account = new AccountInfoQuery();
		// ebscmd = account.getAccountInfoCmd();

		// HistoryDetail detail = new HistoryDetail();
		// ebscmd = detail.getHistoryDetailCmd();

		TodayDetail todayDetail = new TodayDetail();
		ebscmd = todayDetail.getTodayDetailCmd();

		CebsBaseBizImp biz = new CebsBaseBizImp();
		try {
			String result = biz.getResultAsSocket("localhost", 6666, ebscmd);// 192.168.1.11
			// exportXmlFromString(result, "E:\\历史明细.xml");
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

	public static void exportXmlFromString(String result, String fileName) {
		Document doc = XmlUtil.string2Document(result);
		boolean flag = XmlUtil.doc2XmlFile(doc, fileName);
	}
}

//
// 9101 连接信息
// 0102 付款方信息
// 0103 一级分行信息下载
// 0104 二级分行信息下载
// 0105 会计柜台机构号
// 0106 收款方信息下载
