package com.fordays.fdpay.transaction.biz;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.bank.BankUtil;
import com.fordays.fdpay.bank.XmlUtil;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawList;
import com.neza.base.Constant;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

/**
 * 提现文件业务实现类
 */
public class DrawFileBizImp {
	/**
	 * 获得建行外联平台批量代发指令
	 */
	public String getBatchPayrollCreditCmd(List list) throws AppException {
		return "";
	}

	/**
	 * 下载农业银行提现文件
	 */
	public boolean exportDrawFile_ABC_Person(List list,
			HttpServletResponse response) throws AppException {
		// String exportInfo = getExportInfo_ABC_Wages(list);//工资单文件
		String exportInfo = getExportInfo_Standard(list);

		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		String fileName = "ABC" + dateStr + ".txt";

		return downloadFile(fileName, exportInfo, response);
	}

	/**
	 * 下载交通银行提现文件
	 */
	public boolean exportDrawFile_BCM_Person(List list,
			HttpServletResponse response) throws AppException {
		String exportInfo = getExportInfo_Standard(list);

		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		String fileName = "BCM" + dateStr + ".txt";

		return downloadFile(fileName, exportInfo, response);
	}

	/**
	 * 下载中信银行提现文件
	 */
	public boolean exportDrawFile_CITIC_Person(List list,
			HttpServletResponse response) throws AppException {
		// String exportInfo = getExportInfo_CITIC_Person(list);
		String exportInfo = getExportInfo_Standard(list);

		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		String fileName = "CITIC" + dateStr + ".txt";

		return downloadFile(fileName, exportInfo, response);
	}

	/**
	 * 下载民生银行提现文件
	 */
	public boolean exportDrawFile_CMBC(List list, HttpServletResponse response)
			throws AppException {
		// String exportInfo = getExportInfo_CMBC_Person(list);
		String exportInfo = getExportInfo_Standard(list);

		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		String fileName = "CMBC" + dateStr + ".txt";

		return downloadFile(fileName, exportInfo, response);
	}

	/**
	 * 下载建设银行提现文件
	 */
	public boolean exportDrawFile_CCB_Person(List list,
			HttpServletResponse response) throws AppException {
		// String exportInfo = getExportInfo_CCB_Transfer_Person(list);
		String exportInfo = getExportInfo_Standard(list);

		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		String fileName = "CCB" + dateStr + ".txt";

		return downloadFile(fileName, exportInfo, response);
	}

	/**
	 * 下载工商银行企业财务室批量支付文件
	 */
	public boolean exportDrawFile_ICBC(List list, HttpServletResponse response)
			throws AppException {
		String exportInfo = getExportInfo_ICBC(list);

		String dateStr = DateUtil.getDateString("yyyyMMddhhmmss");
		String fileName = dateStr + "ICBC.bxt";

		return downloadFile(fileName, exportInfo, response);
	}

	/**
	 * 获取银行提现文件(标准版)
	 */
	public String getExportInfo_Standard(List list) throws AppException {
		String exportInfo = "";

		int totalNumber = 0;
		BigDecimal totalAmount = new BigDecimal(0);

		StringBuffer headerbuf = new StringBuffer(
				"序号	收款人帐户						 收款人姓名			交易金额			省市						提现银行");
		StringBuffer cutline = new StringBuffer(
				"--------------------------------------------------------------------------------------------------------");

		StringBuffer details = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();

			// 收款方
			String cardNo = draw.getCardNo();
			String accountName = agent.getDrawName();
			BigDecimal amount = draw.getAmount();

			String amountStr = BankUtil.getDoubleStringByDecimal(amount);
			String cityName = draw.getCity().getCname();
			String proviceName = draw.getCity().getProvince().getCname();
			String accountAddress = draw.getAccountBank();

			details.append((i + 1) + "			" + cardNo + "			" + accountName
					+ "					" + amountStr + "					" + proviceName + cityName
					+ "			" + accountAddress + "\r\n");

			totalNumber++;
			totalAmount = totalAmount.add(amount);
		}

		StringBuffer rootbuf = new StringBuffer("总计:	笔数=" + totalNumber
				+ "			总额=" + BankUtil.getDoubleStringByDecimal(totalAmount));

		exportInfo = headerbuf.append("\r\n").append(cutline).append("\r\n")
				.append(details).append(cutline).append("\r\n").append(rootbuf)
				.toString();
		return exportInfo;
	}

	/**
	 * 获取农业银行代发工资单内容
	 */
	public String getExportInfo_ABC_Wages(List list) throws AppException {
		String exportInfo = "";
		// 6228480112051122417|0.01|严睿|0.01|0||
		StringBuffer details = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();

			String cardNo = draw.getCardNo();
			BigDecimal amount = draw.getAmount();
			String accountName = agent.getDrawName();
			details.append(cardNo + "|" + amount + "|" + accountName + "|"
					+ amount + "|0||" + "\n");
		}
		exportInfo = details.toString();
		return exportInfo;
	}

	/**
	 * 获取工商银行批量支付文件内容
	 */
	public String getExportInfo_ICBC(List list) throws AppException {
		int totalNumber = 0;
		BigDecimal totalAmount = new BigDecimal(0);
		String dateStr = DateUtil.getDateString("yyyyMMdd");

		StringBuffer preCon = new StringBuffer("#ICBC").append("\n");
		preCon.append("#注意:本文件中的金额均以分为单位！").append("\n");
		preCon.append("#币种|日期|总计标志|总金额|总笔数|").append("\n");

		StringBuffer detailCon = new StringBuffer("#明细指令信息").append("\n");
		detailCon.append("#币种|日期|明细标志|顺序号|报销号|单据张数|付款帐号开户行|付款帐号|付款帐号名称|");
		detailCon.append("收款帐号开户行|收款帐号省份|收款帐号地市|收款帐号地区码|收款帐号|收款帐号名称|金额|汇款用途|");
		detailCon.append("备注信息|" + "\n");
		// 循环获得明细
		StringBuffer details = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();

			details.append("RMB|" + dateStr + "|2|" + (i + 1) + "|||");
			// 付款方信息暂时写死
			details.append("工行北岭支行|2002022509100027149|珠海市钱门网络科技有限公司|");

			// 收款方
			String cityName = draw.getCity().getCname();
			String proviceName = draw.getCity().getProvince().getCname();
			String accountAddress = draw.getAccountBank();

			String areaCode = getAreaCodeByCity(cityName);// 区域码
			details.append(accountAddress + "|" + proviceName + "|" + cityName
					+ "|" + areaCode + "|");
			BigDecimal amount = draw.getAmount();// 明细金额
			totalAmount = totalAmount.add(amount);// 统计总额
			String centAmount = BankUtil.getCentAmount(amount);// 金额进制转换
			String accountName = draw.getAgent().getDrawName();// 帐号名称
			details.append(draw.getCardNo() + "|" + accountName + "|");
			details.append(centAmount + "|劳务费||");
			details.append("\n");
			
			totalNumber++;
		}
		String totalCentAmount = BankUtil.getCentAmount(totalAmount);
		StringBuffer totalCon = new StringBuffer("RMB|" + dateStr + "|1|");
		totalCon.append(totalCentAmount + "|" + totalNumber + "|" + "\n");// 统计信息

		String exportInfo = preCon.append(totalCon).append(detailCon).append(
				details).append("*").toString();
		return exportInfo;
	}

	/**
	 * 获取建设银行个人网银批量转账文件内容
	 */
	public String getExportInfo_CCB_Transfer_Person(List list)
			throws AppException {
		String exportInfo = "";
		StringBuffer headbuf = new StringBuffer("#序号|付款账户|收款人账户|收款人名称|交易金额|")
				.append("\r\n");
		StringBuffer details = new StringBuffer();
		// 格式:序号|付款账户|收款账户|收款账户名称|交易金额
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();

			String accountName = agent.getDrawName();
			String cardNo = draw.getCardNo();

			BigDecimal amount = draw.getAmount();
			details.append((i + 1) + "|" + "6227003090700158059" + "|" + cardNo
					+ "|" + accountName + "|" + amount + "|");
			details.append("\r\n");
		}
		exportInfo = headbuf.append(details).toString();
		return exportInfo;
	}

	/**
	 * 获取建行批量转账文件(对公)内容
	 */
	public String getExportInfo_CCB_Transfer(List list) throws AppException {
		StringBuffer headbuf = new StringBuffer("#建设银行企业网银批量转账文件").append("\n");

		StringBuffer detals = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();

			String accountName = agent.getDrawName();
			String cardNo = draw.getCardNo();
			detals.append(i + "|" + "珠海市钱门网络科技有限公司" + "|"
					+ "44001646343053000000" + "|" + "机构号默认" + "|");
			detals.append(accountName + "|" + cardNo + "|" + " ");
			detals.append("收款开户行" + "|" + " " + "|" + " " + "|" + "0" + "|");
			BigDecimal amount = draw.getAmount();
			detals.append(amount + "|" + "01" + "|" + "交易款");
		}

		String exportInfo = headbuf.append(detals).toString();

		// 建设银行企业网银批量转账文件格式
		// --------------------------------------------
		// 本行文件
		// 001|珠海电力发展科技股份有限公司|44001646835059111122|441000000|68589004076000000403|江门市蓬江区富岗五金电器有限公司|
		// |市区农村信用合作联社广东江门塘边分社| | |0|20000.00|01|货款
		// 002|珠海电力发展科技股份有限公司|44001646835059111122|441000000|3602028509000390704|广州正道科技有限公司|
		// |工行广东广州花城支行| | |0|5200.00|01|货款

		// 格式说明:
		// 序号|付款企业名称|付款帐号|机构号默认|收款企业帐号|收款企业名称|空格|收款企业开户行|空格|空格|0|金额|01|用途
		return exportInfo;
	}

	/**
	 * 获取中信银行个人网银批量转账文件内容
	 */
	public String getExportInfo_CITIC_Person(List list) throws AppException {
		String exportInfo = "";
		StringBuffer headbuf = new StringBuffer("#序号|付款账户|收款人账户|收款人名称|交易金额|")
				.append("\r\n");
		StringBuffer details = new StringBuffer();
		// 格式:序号|付款账户|收款账户|收款账户名称|交易金额
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();

			String accountName = agent.getDrawName();
			String cardNo = draw.getCardNo();

			BigDecimal amount = draw.getAmount();
			details.append((i + 1) + "|" + "6226900303663153" + "|" + cardNo
					+ "|" + accountName + "|" + amount + "|");
			details.append("\r\n");
		}
		exportInfo = headbuf.append(details).toString();
		return exportInfo;
	}

	/**
	 * 获取民生银行批量支付文件(个人帐号)
	 */
	// 收款帐号,帐户类型,帐户名称,金额,用途
	// 4155990100008888,民生卡,测试张一,23,房租
	// 4155990100009999,对公帐号,测试公司,43,报销
	// 4155990100009999,活期一本通,测试帐户2,43,测试
	public String getExportInfo_CMBC_Person(List list) throws AppException {
		StringBuffer details = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();
			String accountName = agent.getDrawName();

			BigDecimal amount = draw.getAmount();
			String cardNo = draw.getCardNo();
			long regType = drawlist.getAgent().getRegisterType();
			String accType = "";
			if (regType == 0) {
				accType = "民生卡";
			} else if (regType == 1) {
				accType = "对公帐号";
			}
			details.append(cardNo + "," + accType + "," + accountName + ","
					+ amount + "," + "劳务" + "\r\n");
		}
		String exportInfo = details.toString();
		// System.out.println("----民生提现文件-----");
		// System.out.println(exportInfo);
		return exportInfo;
	}

	/**
	 * 获取民生银行批量支付文件
	 */
	public String getExportInfo_CMBC(List list) throws AppException {
		int totalNumber = 0;
		BigDecimal totalAmount = new BigDecimal(0);
		StringBuffer headbuf = new StringBuffer("ATNU:0019999").append("\n");
		headbuf.append("MICN:0").append("\n");
		headbuf.append("CUNM:珠海市钱门网络科技有限公司").append("\n");
		headbuf.append("MIAC:代发帐号").append("\n");
		headbuf.append("EYMD:1").append("\n");

		StringBuffer details = new StringBuffer();
		// 040000000016|1000|张三| | 个人帐号、金额、姓名，输入。
		for (int i = 0; i < list.size(); i++) {
			DrawList drawlist = (DrawList) list.get(i);
			Draw draw = drawlist.getDraw();
			Agent agent = draw.getAgent();

			String accountName = agent.getDrawName();
			BigDecimal amount = draw.getAmount();
			details.append(draw.getCardNo() + "|" + amount + "|");
			details.append(accountName + "|").append("\n");
			totalNumber++;
			totalAmount = totalAmount.add(amount);
		}
		StringBuffer totalbuf = new StringBuffer();
		totalbuf.append("TOAM:" + totalNumber);
		totalbuf.append("COUT:" + totalNumber);

		String exportInfo = headbuf.append(totalbuf).append(details).toString();
		return exportInfo;
	}

	/**
	 * 
	 * 下载文件
	 * 
	 * @param String
	 *            fileName
	 * @param String
	 *            exportInfo
	 * @return boolean
	 * 
	 * @remark use DownLoadFile
	 */
	public boolean downloadFile(String fileName, String exportInfo,
			HttpServletResponse response) throws AppException {
		try {
			exportInfo = new String(exportInfo.getBytes("UTF-8"));
		} catch (Exception ex) {
			return false;
		}
		DownLoadFile df = new DownLoadFile();
		df.performTask(response, exportInfo, fileName, "GBK");
		return true;
	}

	/**
	 * 导出文本文件到指定路径
	 */
	public boolean exportTxtFile(String content, String fullFileName)
			throws AppException {
		boolean isSuccess = false;
		try {
			char buffer[] = new char[(content.length())];
			content.getChars(0, content.length(), buffer, 0);
			FileWriter writer = new FileWriter(fullFileName, true);
			for (int i = 0; i < content.length(); i++) {
				writer.write(buffer[i]);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 查询城市地区码
	 * 
	 * @remark:工商银行
	 */
	public static String getAreaCodeByCity(String city) throws AppException {
		String areaCode = "";
		String fileUrl = Constant.WEB_INFO_PATH
				+ "bank/icbc/AreaCodesConfig.xml";
		// fileUrl = "E:\\AreaCodesConfig.xml";
		// System.out.println("资源路径=" + fileUrl);
		Document doc = XmlUtil.load(fileUrl);

		// System.out.println(city);
		// 根据其中一个属性值，读取文件某个节点，
		Element element = XmlUtil.getElementByAttributeValue(doc,
				"/AreaCodeConf/state/City", city);
		if (element != null) {
			areaCode = element.attribute("AreaCode").getValue();
			return areaCode;
		} else
			return "";
	}
}
