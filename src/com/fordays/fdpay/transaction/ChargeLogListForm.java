package com.fordays.fdpay.transaction;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.neza.base.ListActionForm;


public class ChargeLogListForm extends ListActionForm {
	private String agentName = "";
	private Agent agent =new Agent();
	private String amount="";
	private String beginDate="";
	private String endDate="";
	private BigDecimal minAmount =new BigDecimal(0);
	private BigDecimal maxAmount =new BigDecimal(1000000);
	private String orderNo="";
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

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

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) 
	{
		agentName="";
		 thisAction = "";
		 beginDate="";
			endDate="";
		 minAmount =new BigDecimal(0);
		 maxAmount =new BigDecimal(1000000);
		 this.setIntPage(1);
	}
}
