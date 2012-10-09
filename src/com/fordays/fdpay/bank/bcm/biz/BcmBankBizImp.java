package com.fordays.fdpay.bank.bcm.biz;

import com.fordays.fdpay.bank.LogUtil;
import com.fordays.fdpay.bank.ResultFromBank;
import com.fordays.fdpay.bank.bcm.BcmOrder;
import com.fordays.fdpay.bank.bcm.BcmOrderResult;
import com.fordays.fdpay.transaction.Charge;
import com.neza.encrypt.BASE64;
import com.neza.exception.AppException;

/**
 * 交通银行业务处理实现类
 */
public class BcmBankBizImp implements BcmBankBiz {
	private LogUtil myLog;

	// ----------------------------订单查询与监听--------------------------
	/**
	 * @implement interface:BankBiz
	 * @description:检查订单状态,BankOrderListener调用的方法
	 * @return {@link ResultFromBank}
	 */
	public ResultFromBank initiativeQueryOrder(String orderNo, String version)
			throws AppException {
		myLog = new LogUtil(false, true, BcmBankBizImp.class);
		myLog.info("initiativeQueryOrder(" + orderNo + "," + version + ")");

		BcmOrder order = new BcmOrder(version);
		order.setOrderNo(orderNo);
		BcmOrderResult orderResult = order.getBcmOrderResult();
		orderResult.setRUrl(BASE64.dencrypt(orderResult.getUrl()));///----------------
		return orderResult;
	}

	public void sendNoticeMail(Charge charge) throws AppException {
		myLog = new LogUtil(false, true, BcmBankBizImp.class);
		myLog.info("sendNoticeMail(Charge charge)。。。。");
	}
}
