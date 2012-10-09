package com.fordays.fdpay.transaction;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.neza.base.ListActionForm;


public class ChargeListForm extends ListActionForm {
	private String agentName = "";
	private Charge charge=new Charge();
	private Agent agent =new Agent();
	private String amount="";
	private String beginDate="";
	private String endDate="";
	private BigDecimal minAmount =new BigDecimal(0);
	private BigDecimal maxAmount =new BigDecimal(1000000);
	private long status=99;
	private String bank="0";
	private long id;
	private String  orderNo="";
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
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

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
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

		 thisAction = "";
		 beginDate="";
		 endDate="";
		 status=99;
		 agentName="";
		 minAmount =new BigDecimal(0);
		 maxAmount =new BigDecimal(1000000);
		 orderNo="";
		 bank="0";
		 this.setIntPage(1);
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
