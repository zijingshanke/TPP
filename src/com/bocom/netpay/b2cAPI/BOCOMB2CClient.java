// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BOCOMB2CClient.java

package com.bocom.netpay.b2cAPI;

import java.io.*;
import java.net.URLEncoder;
import java.text.*;
import java.util.Date;
import java.util.StringTokenizer;

import com.fordays.fdpay.bank.LogUtil;

// Referenced classes of package com.bocom.netpay.b2cAPI:
//            BOCOMSetting, NetSignServer, B2CConnection, BOCOMB2COPRequest, 
//            BOCOMB2COPReply, OpResult, NamedValue

public class BOCOMB2CClient {
	private LogUtil myLog;

	private String lastErr;
	private B2CConnection connection;

	public BOCOMB2CClient() {
	}

	public String getLastErr() {
		return lastErr;
	}

	public synchronized int initialize(String fileName) {
		if (BOCOMSetting.isAPIInitialize)
			return 0;
		BOCOMSetting setting = new BOCOMSetting();
		int ret = setting.parseXML(fileName);
		if (ret != 0) {
			lastErr = setting.GetLastErr();
			return ret;
		}
		if (BOCOMSetting.EnableLog) {
			String logFileName = BOCOMSetting.LogPath + "//BOCOMB2CAPILog.log";
			try {
				BOCOMSetting.logWriter = new FileWriter(logFileName, true);
			} catch (Exception e) {
				BOCOMSetting.logWriter = null;
				return -1;
			}
		}
		log("API Initialize", "解析配置文件", "初始化成功");
		NetSignServer ns;
		try {
			ns = new NetSignServer();
		} catch (Exception exception) {
		}
		if (!NetSignServer.isAPIPropertySetted) {
			lastErr = "签名控件初始化失败";
			return -1;
		} else {
			BOCOMSetting.isAPIInitialize = true;
			return 0;
		}
	}

	public synchronized int force_initialize(String fileName) {
		BOCOMSetting setting = new BOCOMSetting();
		int ret = setting.parseXML(fileName);
		if (ret != 0) {
			lastErr = setting.GetLastErr();
			return ret;
		}
		if (BOCOMSetting.EnableLog) {
			String logFileName = BOCOMSetting.LogPath + "//BOCOMB2CAPILog.log";
			try {
				BOCOMSetting.logWriter = new FileWriter(logFileName, true);
			} catch (Exception e) {
				BOCOMSetting.logWriter = null;
				return -1;
			}
		}
		log("API Initialize", "解析配置文件", "初始化成功");
		try {
			NetSignServer.isAPIPropertySetted = false;
			NetSignServer ns = new NetSignServer();
		} catch (Exception exception) {
		}
		if (!NetSignServer.isAPIPropertySetted) {
			lastErr = "签名控件初始化失败";
			return -1;
		} else {
			BOCOMSetting.isAPIInitialize = true;
			return 0;
		}
	}

	public synchronized int initialize(InputStream in) {
		if (BOCOMSetting.isAPIInitialize)
			return 0;
		BOCOMSetting setting = new BOCOMSetting();
		int ret = setting.parseXML(in);
		if (ret != 0) {
			lastErr = setting.GetLastErr();
			return ret;
		}
		if (BOCOMSetting.EnableLog) {
			String logFileName = BOCOMSetting.LogPath + "//BOCOMB2CAPILog.log";
			try {
				BOCOMSetting.logWriter = new FileWriter(logFileName, true);
			} catch (Exception e) {
				BOCOMSetting.logWriter = null;
				return -1;
			}
		}
		log("API Initialize", "解析配置文件", "初始化成功");
		NetSignServer ns;
		try {
			ns = new NetSignServer();
		} catch (Exception exception) {
		}
		if (!NetSignServer.isAPIPropertySetted) {
			lastErr = "签名控件初始化失败";
			return -1;
		} else {
			BOCOMSetting.isAPIInitialize = true;
			return 0;
		}
	}

	public synchronized int force_initialize(InputStream in) {
		BOCOMSetting setting = new BOCOMSetting();
		int ret = setting.parseXML(in);
		if (ret != 0) {
			lastErr = setting.GetLastErr();
			return ret;
		}
		if (BOCOMSetting.EnableLog) {
			String logFileName = BOCOMSetting.LogPath + "//BOCOMB2CAPILog.log";
			try {
				BOCOMSetting.logWriter = new FileWriter(logFileName, true);
			} catch (Exception e) {
				BOCOMSetting.logWriter = null;
				return -1;
			}
		}
		log("API Initialize", "解析配置文件", "初始化成功");
		try {
			NetSignServer.isAPIPropertySetted = false;
			NetSignServer ns = new NetSignServer();
		} catch (Exception exception) {
		}
		if (!NetSignServer.isAPIPropertySetted) {
			lastErr = "签名控件初始化失败";
			return -1;
		} else {
			BOCOMSetting.isAPIInitialize = true;
			return 0;
		}
	}

	private void log(String dealName, String action, String msg) {
		try {
			if (BOCOMSetting.logWriter == null)
				return;
			long timeStamp = System.currentTimeMillis();
			Date curDate = new Date(timeStamp);
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStampStr = fmt.format(curDate);
			String logMsg = timeStampStr + "\t" + dealName + "\t[" + action
					+ "]\t";
			BOCOMSetting.logWriter.write(logMsg);
			if (msg != null && msg.trim().length() != 0)
				BOCOMSetting.logWriter.write(replace(replace(msg, "&lt;", "<"),
						"&gt;", ">"));
			BOCOMSetting.logWriter.write("\r\n");
			BOCOMSetting.logWriter.flush();
		} catch (IOException e) {
			lastErr = "记录日志文件错误:" + e.toString();
			return;
		}
	}

	public String executeOperation(String opName, String reqData) {
		String repContent = null;
		try {
			if (!BOCOMSetting.isAPIInitialize) {
				lastErr = "Bocomm API组件未初始化或初始化失败，请重新初始化";
				return null;
			}
			String fullURL = BOCOMSetting.ApiURL + "?dse_operationName="
					+ opName;
			fullURL = fullURL + "&reqData=" + URLEncoder.encode(reqData);
			
//			System.out.println("BOCOMSetting.UseSSL="+BOCOMSetting.UseSSL);
			
			if (connection == null)
				connection = new B2CConnection(true);//
			NetSignServer signServer = new NetSignServer();
			signServer.NSSetPlainText(reqData.getBytes("GBK"));
			String DN = BOCOMSetting.MerchantCertDN;
			byte signData[] = signServer.NSDetachedSign(DN);
			if (signServer.getLastErrnum() < 0) {
				lastErr = "商户端签名失败";
				return null;
			}
			fullURL = fullURL + "&signData="
					+ URLEncoder.encode(new String(signData, "GBK"));
			signServer = null;

			repContent = connection.sendAndReceive(fullURL);
			if (repContent == null) {
				lastErr = "通讯异常!";
				return null;
			}
			repContent.replace('\r', ' ');
			repContent.replace('\n', ' ');
			repContent = repContent.trim();
			int begIndex = repContent.indexOf("<opRep>");
			int endIndex = repContent.indexOf("</opRep>");
			String origData = repContent.substring(begIndex, endIndex + 8);
			begIndex = repContent.indexOf("<signData>");
			endIndex = repContent.indexOf("</signData>");
			String signMsg = repContent.substring(begIndex + 10, endIndex);
			signServer = new NetSignServer();
			signServer.NSDetachedVerify(signMsg.getBytes("GBK"), origData
					.getBytes("GBK"));
			if (signServer.getLastErrnum() < 0) {
				lastErr = "验证银行签名失败；response xml:"
						+ replace(replace(repContent, "<", "&lt;"), ">", "&gt;");
				return null;
			} else {
				return repContent;
			}
		} catch (Exception e) {
			if (repContent != null)
				System.out.println("repXML:" + repContent);
			lastErr = "method executeOperation exception" + e.toString();
			return null;
		}
	}
	
	public BOCOMB2COPReply queryOrder(String orders) {
		if (orders == null || orders.trim().length() == 0) {
			lastErr = "查询定单信息不能为空，请确认定单信息";
			log("批量订单查证", "交易请求信息", "查询定单信息不能为空，请确认定单信息");
			return null;
		}
		StringTokenizer st = new StringTokenizer(orders, "|");
		int size = st.countTokens();
		if (size > 20) {
			lastErr = "一次查询定单信息数请不要超过20笔";
			log("批量订单查证", "交易请求信息", "一次查询定单信息数请不要超过20笔");
			return null;
		}
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("number", Integer.toString(size));
		req.addParam("detail", "1");
		req.addParam("orders", orders);
		req.setOpName("cb2202_queryOrderOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("批量订单查证", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("批量订单查证", "交易请求报文", xml);
		String repXML = executeOperation("cb2202_queryOrderOp", xml);
		if (repXML == null) {
			log("批量订单查证", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("批量订单查证", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("批量订单查证", "交易返回报文", repXML);
			return reply;
		}
	}

	public BOCOMB2COPReply queryAccountBalance() {
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("settAccount", null);
		req.setOpName("cb2201_AccountBalanceOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("帐户余额查询", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("帐户余额查询", "交易请求报文", xml);
		String repXML = executeOperation("cb2201_AccountBalanceOp", xml);
		if (repXML == null) {
			log("帐户余额查询", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("帐户余额查询", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("帐户余额查询", "交易返回报文", repXML);
			return reply;
		}
	}

//	public BOCOMB2COPReply queryOrder(String orders) {
//		if (orders == null || orders.trim().length() == 0) {
//			lastErr = "查询定单信息不能为空，请确认定单信息";
//			log("批量订单查证", "交易请求信息", "查询定单信息不能为空，请确认定单信息");
//			return null;
//		}
//		StringTokenizer st = new StringTokenizer(orders, "|");
//		int size = st.countTokens();
//		if (size > 20) {
//			lastErr = "一次查询定单信息数请不要超过20笔";
//			log("批量订单查证", "交易请求信息", "一次查询定单信息数请不要超过20笔");
//			return null;
//		}
//		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
//		req.addParam("merchantID", BOCOMSetting.MerchantID);
//		req.addParam("number", Integer.toString(size));
//		req.addParam("detail", "1");
//		req.addParam("orders", orders);
//		req.setOpName("cb2202_queryOrderOp");
//		String xml = req.getXML();
//		if (xml == null) {
//			lastErr = "请求数据组包错误";
//			log("批量订单查证", "交易请求信息", "请求数据组包错误");
//			return null;
//		}
//		log("批量订单查证", "交易请求报文", xml);
//		String repXML = executeOperation("cb2202_queryOrderOp", xml);
//		if (repXML == null) {
//			log("批量订单查证", "交易返回信息", "ERROR:" + lastErr);
//			return null;
//		}
//		BOCOMB2COPReply reply = new BOCOMB2COPReply();
//		int ret = reply.parseXML(repXML);
//		if (ret != 0) {
//			lastErr = reply.getLastErr();
//			log("批量订单查证", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
//					+ reply.getLastErr());
//			return null;
//		} else {
//			log("批量订单查证", "交易返回报文", repXML);
//			return reply;
//		}
//	}

	public BOCOMB2COPReply queryCurOrder(String beg, String end, int flag,
			int begindex, String begorder, String endorder, int sortField,
			int sortOrder) {
		if (!isValidDate(beg, "yyyyMMddHHmmss")) {
			lastErr = "开始时间参数输入错误";
			log("当批订单查询", "交易请求信息", "开始时间参数输入错误");
			return null;
		}
		if (!isValidDate(end, "yyyyMMddHHmmss")) {
			lastErr = "结束时间参数输入错误";
			log("当批订单查询", "交易请求信息", "结束时间参数输入错误");
			return null;
		}
		if (Double.parseDouble(end) < Double.parseDouble(beg)) {
			lastErr = "结束时间输入参数不能小于起始时间";
			log("当批订单查询", "交易请求信息", "结束时间输入参数不能小于起始时间");
			return null;
		}
		if (flag != 0 && flag != 1 && flag != 2) {
			lastErr = "定单状态输入参数错误(0/1/2)";
			log("当批订单查询", "交易请求信息", "定单状态输入参数错误(0/1/2)");
			return null;
		}
		if (sortField != 1 && sortField != 2 && sortField != 3)
			sortField = 1;
		if (sortOrder != 1 && sortOrder != 2)
			sortOrder = 1;
		if (begorder != null && begorder.trim().length() != 0) {
			begorder = begorder.trim();
			if (endorder == null || endorder.trim().length() == 0) {
				lastErr = "起始订单号需和截止定单号关联使用，请输入截止定单号";
				log("当批订单查询", "交易请求信息", "起始订单号需和截止定单号关联使用，请输入截止定单号");
				return null;
			}
		}
		if (endorder != null && endorder.trim().length() != 0) {
			endorder = endorder.trim();
			if (begorder == null || begorder.trim().length() == 0) {
				lastErr = "截止定单号需和起始订单号关联使用，请输入起始订单号";
				log("当批订单查询", "交易请求信息", "截止定单号需和起始订单号关联使用，请输入起始订单号");
				return null;
			}
		}
		if (begindex < 0) {
			lastErr = "起始索引号输入错误";
			log("当批订单查询", "交易请求信息", "起始索引号输入错误");
			return null;
		}
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("beginTime", beg);
		req.addParam("endTime", end);
		req.addParam("flag", Integer.toString(flag));
		req.addParam("beginIndex", Integer.toString(begindex));
		req.addParam("beginOrder", begorder);
		req.addParam("endOrder", endorder);
		req.addParam("sortField", Integer.toString(sortField));
		req.addParam("sortOrder", Integer.toString(sortOrder));
		req.setOpName("cb2203_queryCurOrderOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("当批订单查询", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("当批订单查询", "交易请求报文", xml);
		String repXML = executeOperation("cb2203_queryCurOrderOp", xml);
		if (repXML == null) {
			log("当批订单查询", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("当批订单查询", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("当批订单查询", "交易返回报文", repXML);
			return reply;
		}
	}

	public BOCOMB2COPReply queryCurOrderFull(String beg, String end, int flag,
			String begorder, String endorder, int sortField, int sortOrder) {
		if (!isValidDate(beg, "yyyyMMddHHmmss")) {
			lastErr = "开始时间输入参数错误";
			log("当批订单查询", "交易请求信息", "开始时间输入参数错误");
			return null;
		}
		if (!isValidDate(end, "yyyyMMddHHmmss")) {
			lastErr = "结束时间输入参数错误";
			log("当批订单查询", "交易请求信息", "结束时间输入参数错误");
			return null;
		}
		if (Double.parseDouble(end) < Double.parseDouble(beg)) {
			lastErr = "结束时间输入参数不能小于起始时间";
			log("当批订单查询", "交易请求信息", "结束时间输入参数不能小于起始时间");
			return null;
		}
		if (flag != 0 && flag != 1 && flag != 2) {
			lastErr = "定单状态输入参数错误(0/1/2)";
			log("当批订单查询", "交易请求信息", "定单状态输入参数错误(0/1/2)");
			return null;
		}
		if (sortField != 1 && sortField != 2 && sortField != 3)
			sortField = 1;
		if (sortOrder != 1 && sortOrder != 2)
			sortOrder = 1;
		if (begorder != null && begorder.trim().length() != 0) {
			begorder = begorder.trim();
			if (endorder == null || endorder.trim().length() == 0) {
				lastErr = "起始订单号需和截止定单号关联使用，请输入截止定单号";
				log("当批订单查询", "交易请求信息", "起始订单号需和截止定单号关联使用，请输入截止定单号");
				return null;
			}
		}
		if (endorder != null && endorder.trim().length() != 0) {
			endorder = endorder.trim();
			if (begorder == null || begorder.trim().length() == 0) {
				lastErr = "截止定单号需和起始订单号关联使用，请输入起始订单号";
				log("当批订单查询", "交易请求信息", "截止定单号需和起始订单号关联使用，请输入起始订单号");
				return null;
			}
		}
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("beginTime", beg);
		req.addParam("endTime", end);
		req.addParam("flag", Integer.toString(flag));
		req.addParam("beginOrder", begorder);
		req.addParam("endOrder", endorder);
		req.addParam("sortField", Integer.toString(sortField));
		req.addParam("sortOrder", Integer.toString(sortOrder));
		req.setOpName("cb2209_queryCurOrderOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("当批订单完整查询", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("当批订单完整查询", "交易请求报文", xml);
		String repXML = executeOperation("cb2209_queryCurOrderOp", xml);
		if (repXML == null) {
			log("当批订单完整查询", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("当批订单完整查询", "交易返回信息", "RETURN:" + reply.getRetCode()
					+ " |ERROR:" + reply.getLastErr());
			return null;
		} else {
			log("当批订单完整查询", "交易返回报文", repXML);
			return reply;
		}
	}

	public BOCOMB2COPReply queryHistoryOrder(String begDate, String endDate,
			int flag, int begindex, String begorder, String endorder,
			int sortField, int sortOrder) {
		if (!isValidDate(begDate, "yyyyMMddHHmmss")) {
			lastErr = "开始时间输入参数错误";
			log("历史订单查询", "交易请求信息", "开始时间输入参数错误");
			return null;
		}
		if (!isValidDate(endDate, "yyyyMMddHHmmss")) {
			lastErr = "结束时间输入参数错误";
			log("历史订单查询", "交易请求信息", "结束时间输入参数错误");
			return null;
		}
		if (Double.parseDouble(begDate) > Double.parseDouble(endDate)) {
			lastErr = "起始时间输入参数不能大于结束时间";
			log("历史订单查询", "交易请求信息", "起始时间输入参数不能大于结束时间");
			return null;
		}
		if (flag != 0 && flag != 1 && flag != 2 && flag != 3 && flag != 4
				&& flag != 5) {
			lastErr = "定单状态输入参数错误(0/1/2/3/4/5)";
			log("历史订单查询", "交易请求信息", "定单状态输入参数错误(0/1/2/3/4/5)");
			return null;
		}
		if (sortField != 1 && sortField != 2 && sortField != 3)
			sortField = 1;
		if (sortOrder != 1 && sortOrder != 2)
			sortOrder = 1;
		if (begorder != null && begorder.trim().length() != 0) {
			begorder = begorder.trim();
			if (endorder == null || endorder.trim().length() == 0) {
				lastErr = "起始订单号需和截止定单号关联使用，请输入截止定单号";
				log("历史订单查询", "交易请求信息", "起始订单号需和截止定单号关联使用，请输入截止定单号");
				return null;
			}
		}
		if (endorder != null && endorder.trim().length() != 0) {
			endorder = endorder.trim();
			if (begorder == null || begorder.trim().length() == 0) {
				lastErr = "截止定单号需和起始订单号关联使用，请输入起始订单号";
				log("历史订单查询", "交易请求信息", "截止定单号需和起始订单号关联使用，请输入起始订单号");
				return null;
			}
		}
		if (begindex < 0) {
			lastErr = "起始索引号输入错误";
			log("历史订单查询", "交易请求信息", "起始索引号输入错误");
			return null;
		}
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("beginDate", begDate);
		req.addParam("endDate", endDate);
		req.addParam("flag", Integer.toString(flag));
		req.addParam("beginIndex", Integer.toString(begindex));
		req.addParam("beginOrder", begorder);
		req.addParam("endOrder", endorder);
		req.addParam("sortField", Integer.toString(sortField));
		req.addParam("sortOrder", Integer.toString(sortOrder));
		req.setOpName("cb2204_queryHistoryOrderOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("历史订单查询", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("历史订单查询", "交易请求报文", xml);
		String repXML = executeOperation("cb2204_queryHistoryOrderOp", xml);
		if (repXML == null) {
			log("历史订单查询", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("历史订单查询", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("历史订单查询", "交易返回报文", repXML);
			return reply;
		}
	}

	public BOCOMB2COPReply queryRefund(String begDate, String endDate,
			int refundtype, String order, int flag, int begindex) {
		if (!isValidDate(begDate, "yyyyMMdd")) {
			lastErr = "起始日期输入参数错误";
			log("退款明细查询", "交易请求信息", "起始日期输入参数错误");
			return null;
		}
		if (!isValidDate(endDate, "yyyyMMdd")) {
			lastErr = "结束日期输入参数错误";
			log("退款明细查询", "交易请求信息", "结束日期输入参数错误");
			return null;
		}
		if (Integer.parseInt(begDate) > Integer.parseInt(endDate)) {
			lastErr = "结束日期输入参数不能大于起始日期";
			log("退款明细查询", "交易请求信息", "结束日期输入参数不能大于起始日期");
			return null;
		}
		if (flag != 0 && flag != 1 && flag != 2) {
			lastErr = "交易状态输入参数错误(0/1/2)";
			log("退款明细查询", "交易请求信息", "交易状态输入参数错误(0/1/2)");
			return null;
		}
		if (refundtype != 0 && refundtype != 1 && refundtype != 2) {
			lastErr = "退款类型输入参数错误(0/1/2)";
			log("退款明细查询", "交易请求信息", "退款类型输入参数错误(0/1/2)");
			return null;
		}
		if (begindex < 0) {
			lastErr = "起始索引号输入错误";
			log("退款明细查询", "交易请求信息", "起始索引号输入错误");
			return null;
		}
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("beginDate", begDate);
		req.addParam("endDate", endDate);
		req.addParam("refundType", Integer.toString(refundtype));
		req.addParam("order", order);
		req.addParam("state", Integer.toString(flag));
		req.addParam("beginIndex", Integer.toString(begindex));
		req.setOpName("cb2205_queryRefundOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("退款明细查询", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("退款明细查询", "交易请求报文", xml);
		String repXML = executeOperation("cb2205_queryRefundOp", xml);
		if (repXML == null) {
			log("退款明细查询", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("退款明细查询", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("退款明细查询", "交易返回报文", repXML);
			return reply;
		}
	}

	public BOCOMB2COPReply Refund(String operator, String order, String date,
			String amount, String comment) {
		if (operator == null || operator.trim().length() == 0) {
			lastErr = "操作员号不能为空";
			log("商户退款录入", "交易请求信息", "操作员号不能为空");
			return null;
		}
		if (order == null || order.trim().length() == 0) {
			lastErr = "退款订单号输入不能为空";
			log("商户退款录入", "交易请求信息", "退款订单号输入不能为空");
			return null;
		}
		if (!isValidDate(date, "yyyyMMdd")) {
			lastErr = "定单时间输入参数错误";
			log("商户退款录入", "交易请求信息", "定单时间输入参数错误");
			return null;
		}
		if (amount == null) {
			lastErr = "退款金额不能为空";
			log("商户退款录入", "交易请求信息", "退款金额不能为空");
			return null;
		}
		if (!moneyCheck(amount)) {
			lastErr = "退款金额输入不合法";
			log("商户退款录入", "交易请求信息", "退款金额输入不合法");
			return null;
		}
		if (Double.parseDouble(amount) <= 0.0D) {
			lastErr = "退款金额输入不合法";
			log("商户退款录入", "交易请求信息", "退款金额输入不合法");
			return null;
		}
		if (comment != null && comment.trim().length() > 50) {
			lastErr = "退款备注输入超长，请不要超过50个字符";
			log("商户退款录入", "交易请求信息", "退款备注输入超长，请不要超过50个字符");
			return null;
		}
		comment = comment.trim();
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("operator", operator);
		req.addParam("order", order);
		req.addParam("date", date);
		req.addParam("amount", amount);
		req.addParam("comment", comment);
		req.setOpName("cb2206_RefundOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("商户退款录入", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("商户退款录入", "交易请求报文", xml);
		String repXML = executeOperation("cb2206_RefundOp", xml);
		if (repXML == null) {
			log("商户退款录入", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("商户退款录入", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("商户退款录入", "交易返回报文", repXML);
			return reply;
		}
	}

	public BOCOMB2COPReply downLoadSettlement(String date) {
		if (!isValidDate(date, "yyyyMMdd")) {
			lastErr = "日期输入参数错误";
			log("结算明细下载", "交易请求信息", "日期输入参数错误");
			return null;
		}
		if (BOCOMSetting.SettlementFilePath == null
				|| BOCOMSetting.SettlementFilePath.trim().length() == 0) {
			lastErr = "请在配置文件中设置对帐明细文件下载路径，并且重新初始化API组件";
			log("结算明细下载", "交易请求信息", "请在配置文件中设置对帐明细文件下载路径，并且重新初始化API组件");
			return null;
		}
		OutputStreamWriter settlementWriter = null;
		String fileName = null;
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("date", date);
		req.setOpName("cb2207_downLoadSettlementOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("结算明细下载", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("结算明细下载", "交易请求报文", xml);
		String repXML = executeOperation("cb2207_downLoadSettlementOp", xml);
		if (repXML == null) {
			log("结算明细下载", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("结算明细下载", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		}
		if ("0".equals(reply.getRetCode())) {
			OpResult result = reply.getOpResult();
			NamedValue nValue = null;
			for (int i = 0; i < result.getNamedValueNum(); i++) {
				NamedValue nv = result.getNamedValue(i);
				if (!"settlementFile".equals(nv.name))
					continue;
				nValue = nv;
				break;
			}

			if (nValue == null) {
				lastErr = "没有找到结算文件内容!";
				return null;
			}
			fileName = BOCOMSetting.SettlementFilePath;
			fileName = fileName + "//BOCOM_B2C_Settlement_";
			fileName = fileName + date;
			try {
				settlementWriter = new FileWriter(fileName, false);
			} catch (Exception e) {
				lastErr = "结算文件路径错误";
				log("结算明细下载", "交易返回信息", "结算文件路径错误");
				return null;
			}
			try {
				settlementWriter.write(nValue.value);
				settlementWriter.close();
			} catch (Exception e) {
				try {
					settlementWriter.close();
				} catch (IOException e1) {
					settlementWriter = null;
				}
				log("结算明细下载", "交易返回信息", "保存文件错误");
				return null;
			}
			log("结算明细下载", "交易返回信息", "下载成功，文件名称:" + fileName);
		}
		return reply;
	}

	public BOCOMB2COPReply verifyCustID(String cardNo, String custName,
			String certType, String certNo) {
		if (cardNo == null || cardNo.trim().length() == 0) {
			lastErr = "校验卡号不能为空";
			log("客户身份校验", "交易请求信息", "校验卡号不能为空");
			return null;
		}
		if ((custName == null || custName.trim().length() == 0)
				&& (certType == null || certType.trim().length() == 0
						|| certNo == null || certNo.trim().length() == 0)) {
			lastErr = "校验信息输入不全，请重新输入";
			log("客户身份校验", "交易请求信息", "校验信息输入不全，请重新输入");
			return null;
		}
		if (certType != null && certType.trim().length() != 0
				&& (certNo == null || certNo.trim().length() == 0)) {
			lastErr = "证件类型和证件号码必须匹配输入";
			log("客户身份校验", "交易请求信息", "证件类型和证件号码必须匹配输入");
			return null;
		}
		if (certNo != null && certNo.trim().length() != 0
				&& (certType == null || certType.trim().length() == 0)) {
			lastErr = "证件类型和证件号码必须匹配输入";
			log("客户身份校验", "交易请求信息", "证件类型和证件号码必须匹配输入");
			return null;
		}
		BOCOMB2COPRequest req = new BOCOMB2COPRequest();
		req.addParam("merchantID", BOCOMSetting.MerchantID);
		req.addParam("cardNo", cardNo);
		req.addParam("custName", custName);
		req.addParam("certType", certType);
		req.addParam("certNo", certNo);
		req.setOpName("cb2208_verifyCustIDOp");
		String xml = req.getXML();
		if (xml == null) {
			lastErr = "请求数据组包错误";
			log("客户身份校验", "交易请求信息", "请求数据组包错误");
			return null;
		}
		log("客户身份校验", "交易请求报文", xml);
		String repXML = executeOperation("cb2208_verifyCustIDOp", xml);
		if (repXML == null) {
			log("客户身份校验", "交易返回信息", "ERROR:" + lastErr);
			return null;
		}
		BOCOMB2COPReply reply = new BOCOMB2COPReply();
		int ret = reply.parseXML(repXML);
		if (ret != 0) {
			lastErr = reply.getLastErr();
			log("客户身份校验", "交易返回信息", "RETURN:" + reply.getRetCode() + " |ERROR:"
					+ reply.getLastErr());
			return null;
		} else {
			log("客户身份校验", "交易返回报文", repXML);
			return reply;
		}
	}

	public static boolean isValidDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String STR = "0123456789";
		if (dateStr.length() != pattern.length())
			return false;
		for (int i = 0; i < dateStr.length(); i++)
			if ("0123456789".indexOf(dateStr.substring(i, i + 1)) == -1)
				return false;

		try {
			sdf.setLenient(false);
			sdf.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean moneyCheck(String moneyString) {
		if (moneyString == null)
			return false;
		String stringTemp = moneyString.trim();
		try {
			Float.parseFloat(stringTemp);
		} catch (NumberFormatException e) {
			return false;
		}
		int stringlength = stringTemp.length();
		if (stringTemp.indexOf(".") != -1) {
			int dot = stringTemp.indexOf(".");
			if (stringlength - dot > 3)
				return false;
		}
		return !stringTemp.substring(0, 1).equals("0") || stringlength == 1
				|| stringTemp.substring(1, 2).equals(".");
	}

	public static String replace(String strSource, String strFrom, String strTo) {
		String strDest = "";
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;
		return strDest;
	}
}
