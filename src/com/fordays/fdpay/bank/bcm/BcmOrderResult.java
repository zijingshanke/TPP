package com.fordays.fdpay.bank.bcm;

import com.fordays.fdpay.bank.ResultFromBank;

/**
 * @description:交通银行订单查询结果
 */
public class BcmOrderResult extends ResultFromBank {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ------B2C批量订单查询结果
	private String order = "";
	private String orderDate = "";
	private String orderTime = "";
	private String curType = "";
	private String amount = "";
	private String tranDate = "";
	private String tranTime = "";
	private String tranState = "";
	private String orderState = "";
	private String fee = "";
	private String bankSerialNo = "";
	private String bankBatNo = "";
	private String cardType = "";
	private String merchantBatNo = "";
	private String merchantComment = "";
	// private String bankComment = "";
	// ----B2C end
	// ------------------
	private String url = "";

	public String getUrl() {
		url = "order=" + order + "&orderDate=" + orderDate + "&orderTime="
				+ orderTime + "&curType=" + curType + "&amount=" + amount
				+ "&tranDate=" + tranDate + "&tranTime=" + tranTime
				+ "&tranState=" + tranState + "&orderState=" + orderState
				+ "&fee=" + fee + "&bankSerialNo=" + bankSerialNo
				+ "&bankBatNo=" + bankBatNo + "&cardType=" + cardType
				+ "&merchantBatNo=" + merchantBatNo + "&merchantComment="
				+ merchantComment;
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCurType() {
		return curType;
	}

	public void setCurType(String curType) {
		this.curType = curType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getTranState() {
		return tranState;
	}

	public void setTranState(String tranState) {
		this.tranState = tranState;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getBankSerialNo() {
		return bankSerialNo;
	}

	public void setBankSerialNo(String bankSerialNo) {
		this.bankSerialNo = bankSerialNo;
	}

	public String getBankBatNo() {
		return bankBatNo;
	}

	public void setBankBatNo(String bankBatNo) {
		this.bankBatNo = bankBatNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMerchantBatNo() {
		return merchantBatNo;
	}

	public void setMerchantBatNo(String merchantBatNo) {
		this.merchantBatNo = merchantBatNo;
	}

	public String getMerchantComment() {
		return merchantComment;
	}

	public void setMerchantComment(String merchantComment) {
		this.merchantComment = merchantComment;
	}

	// public String getBankComment() {
	// return bankComment;
	// }
	//
	// public void setBankComment(String bankComment) {
	// this.bankComment = bankComment;
	// }
}
