package com.fordays.fdpay.agent;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;

public class AgentBalancePointListForm   extends ListActionForm{
	
	private String agentName = "";
	private String agentEmail = "";
	private java.sql.Timestamp balanceDate;
	private int sort=0;
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public java.sql.Timestamp getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(java.sql.Timestamp balanceDate) {
		this.balanceDate = balanceDate;
	}
	public void reset(ActionMapping actionMapping,
		    HttpServletRequest httpServletRequest)
		{
		  agentName = "";
		  agentEmail = "";

		}

}
