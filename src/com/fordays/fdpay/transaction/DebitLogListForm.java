package com.fordays.fdpay.transaction;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.ListActionForm;


public class DebitLogListForm extends ListActionForm {
	private String agentName="";
	private Agent agent=new Agent();
	private String beginDate="";
	private String endDate="";
	private BigDecimal minAmount =new BigDecimal(0);
	private BigDecimal maxAmount =new BigDecimal(1000000);
	private String orderNo="";
	private long userId;
	private SysUser user;
	
	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
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
		 minAmount =new BigDecimal(0);
		 maxAmount =new BigDecimal(1000000);
		 beginDate="";
		 endDate="";
		 this.setIntPage(1);
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

}
