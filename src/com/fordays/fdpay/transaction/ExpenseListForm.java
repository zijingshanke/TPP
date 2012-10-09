package com.fordays.fdpay.transaction;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.neza.base.ListActionForm;


public class ExpenseListForm extends ListActionForm {
	private String agentName = "";
	private String amount="";
	private String beginDate="";
	private String endDate="";
	private BigDecimal minAmount =new BigDecimal(0);
	private BigDecimal maxAmount =new BigDecimal(1000000);
	private String  orderNo="";
	private long status=99;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}


	
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) 
	{

		 thisAction = "";
		 beginDate="";
		 endDate="";
		 agentName="";
		 minAmount =new BigDecimal(0);
		 maxAmount =new BigDecimal(1000000);
		 orderNo="";
		 status=99;
		 this.setIntPage(1);
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
}
