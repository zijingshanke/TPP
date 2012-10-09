package com.fordays.fdpay.bank;

import java.math.BigDecimal;

/**
 * 银行反馈信息通用实体
 */
public class ResultFromBank extends org.apache.struts.action.ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rOrderNo = "";
	private String rType = "";
	private BigDecimal rAmount = new BigDecimal(0);
	private long rChargeStatus = new Long(0);
	private String rRemark;
	private String rUrl;
	
	private String rRequestHost="";

	public String getROrderNo() {
		return rOrderNo;
	}

	public void setROrderNo(String orderNo) {
		rOrderNo = orderNo;
	}

	public String getRType() {
		return rType;
	}

	public void setRType(String type) {
		rType = type;
	}

	public BigDecimal getRAmount() {
		return rAmount;
	}

	public void setRAmount(BigDecimal amount) {
		rAmount = amount;
	}

	public long getRChargeStatus() {
		return rChargeStatus;
	}

	public void setRChargeStatus(long chargeStatus) {
		rChargeStatus = chargeStatus;
	}

	public String getRRemark() {
		return rRemark;
	}

	public void setRRemark(String remark) {
		rRemark = remark;
	}

	public String getRUrl() {
		return rUrl;
	}

	public void setRUrl(String url) {
		rUrl = url;
	}

	public String getRRequestHost() {
		return rRequestHost;
	}

	public void setRRequestHost(String requestHost) {
		rRequestHost = requestHost;
	}	
}
