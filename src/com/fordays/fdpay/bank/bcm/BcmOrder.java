package com.fordays.fdpay.bank.bcm;

import java.io.File;

import com.bocom.netpay.b2cAPI.BOCOMB2COPReply;
import com.bocom.netpay.b2cAPI.OpResultSet;
import com.fordays.fdpay.bank.BankUtil;
import com.fordays.fdpay.bank.LogUtil;
import com.fordays.fdpay.bank.ResultFromBank;
import com.fordays.fdpay.transaction.Charge;
import com.neza.base.Constant;

/**
 * 交通银行订单查询
 */
public class BcmOrder {
	private String orderNo = "";
	// ---
	private String version = "";//
	// ----
	private LogUtil myLog;

	public BcmOrder(String rVersion) {
		version = rVersion;
		init(version);
	}

	public void init(String version) {

	}

	public BcmOrderResult getBcmOrderResult() {
		myLog = new LogUtil(false, false, BcmOrder.class);
		BcmOrderResult result = null;
		if ("B2C".equals(version)) {
			result = getQueryDataResult_B2C();
		} else {
			myLog.error(version + "订单查询建设中--2");
			result = null;
		}
		return result;
	}

	/**
	 * B2C订单查询结果
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return {@link ResultFromBank}
	 */
	public BcmOrderResult getQueryDataResult_B2C() {
		myLog = new LogUtil(false, false, BcmOrder.class);
		BcmOrderResult result = null;
		com.bocom.netpay.b2cAPI.BOCOMB2CClient client = getBOCOMB2CClient_Locale();

		// 查询指定多笔订单号的支付结果，返回数据参看 5.2（交易接口返回 XML 包） 。
		// orders 是订单信息，多笔订单不超过 20 笔，之间用 ‘|’ 分隔；如“10001|10002|10003|10004”

		BOCOMB2COPReply rep = client.queryOrder(orderNo);
		String code, err, msg;
		if (rep == null) {
			err = client.getLastErr();
			msg = rep.getErrorMessage();
			err = rep.getLastErr();
			myLog.error("交易错误信息：" + err + "\n" + msg + "\n" + err);
			return result;
		} else {
			code = rep.getRetCode(); // 得到交易返回码
			if ("0".equals(code)) {// 表示交易成功
				int index;
				OpResultSet oprSet = rep.getOpResultSet();
				int iNum = oprSet.getOpresultNum();
				myLog.info("总交易记录数：" + iNum);

				result = new BcmOrderResult();
				for (index = 0; index <= iNum - 1; index++) {
					String order = oprSet.getResultValueByName(index, "order"); // 订单号
					String orderDate = oprSet.getResultValueByName(index,
							"orderDate"); // 订单日期
					String orderTime = oprSet.getResultValueByName(index,
							"orderTime"); // 订单时间
					String curType = oprSet.getResultValueByName(index,
							"curType"); // 币种
					String amount = oprSet
							.getResultValueByName(index, "amount"); // 金额
					String tranDate = oprSet.getResultValueByName(index,
							"tranDate"); // 交易日期
					String tranTime = oprSet.getResultValueByName(index,
							"tranTime"); // 交易时间
					String tranState = oprSet.getResultValueByName(index,
							"tranState"); // 支付交易状态
					String orderState = oprSet.getResultValueByName(index,
							"orderState"); // 订单状态
					String fee = oprSet.getResultValueByName(index, "fee");
					String bankSerialNo = oprSet.getResultValueByName(index,
							"bankSerialNo"); // 银行流水号
					String bankBatNo = oprSet.getResultValueByName(index,
							"bankBatNo");
					String cardType = oprSet.getResultValueByName(index,
							"cardType");
					String merchantBatNo = oprSet.getResultValueByName(index,
							"merchantBatNo"); // 商户批次号
					String merchantComment = oprSet.getResultValueByName(index,
							"merchantComment");// 商户备注

					result.setOrder(order);
					result.setOrderDate(orderDate);
					result.setOrderTime(orderTime);
					result.setCurType(curType);
					result.setAmount(amount);
					result.setTranDate(tranDate);
					result.setTranTime(tranTime);
					result.setTranState(tranState);
					result.setOrderState(orderState);
					result.setFee(fee);
					result.setBankSerialNo(bankSerialNo);
					result.setBankBatNo(bankBatNo);
					result.setCardType(cardType);
					result.setMerchantBatNo(merchantBatNo);
					result.setMerchantComment(merchantComment);

					myLog.info("订单号：" + order);
					myLog.info("金额：" + amount);
					myLog.info("支付交易状态[1:成功]：" + tranState);
					myLog.info("交易卡类型[0:借记卡 1：准贷记卡 2:贷记卡]：" + cardType);
					// -----------------
					result.setROrderNo(order);
					result.setRAmount(BankUtil.getBigDecimalByString(amount));

					if ("1".equals(tranState)) {// 1:已支付
						result.setRChargeStatus(Charge.CHARGE_STATUS_1);
					}else {// 失败、放弃支付
						result.setRChargeStatus(Charge.CHARGE_STATUS_2);
					}
					
					result.setRRequestHost(merchantComment);
					result.setRUrl(result.getUrl());
				}
			}
		}
		return result;
	}

	public com.bocom.netpay.b2cAPI.BOCOMB2CClient getBOCOMB2CClient_Locale() {
		myLog = new LogUtil(false, false, BcmOrder.class);
		myLog.info("订单查询接口,初始化BOCOMB2CClient---------------");

		com.bocom.netpay.b2cAPI.BOCOMB2CClient client = new com.bocom.netpay.b2cAPI.BOCOMB2CClient();
		
		// final String fileName = "E:\\bocommjava\\B2CMerchant.xml";

		final String fileName = Constant.WEB_INFO_PATH + File.separator
				+ "bankkey" + File.separator + "init" + File.separator
				+ "bankInterfaceConfig-BCM.xml";

		// System.out.println("交通银行接口配置文件,path:" + fileName);

		int ret = client.initialize(fileName);
		if (ret != 0) {
			myLog.error("初始化失败,错误信息:" + client.getLastErr());
		}
		myLog.info("初始化BOCOMB2CClient成功");
		return client;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
