package com.fordays.fdpay.agent;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;

public class AgentListForm extends ListActionForm
{

	private int agentId;
	private int agentType=0;
	protected String bankName="";
	private String agentName = "";
	private String agentEmail = "";
	private String agentEmails = "";
	private String key = "";
	private Long bankId;
	private long status=0;
	private String companyName="";
	private String downloadDate="";
	private int sort=0;
	
	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public long getStatus() {
		return status;
	}


	public void setStatus(long status) {
		this.status = status;
	}


	public Long getBankId() {
		return bankId;
	}


	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}


	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest)
	{
		thisAction = "";
		agentName="";
		this.agentEmail="";
		bankName="";
		status=0;
		downloadDate="";
		this.setIntPage(1);
	}


	public int getAgentId() {
		return agentId;
	}


	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}


	public int getAgentType() {
		return agentType;
	}


	public void setAgentType(int agentType) {
		this.agentType = agentType;
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


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getAgentEmails() {
		return agentEmails;
	}


	public void setAgentEmails(String agentEmails) {
		this.agentEmails = agentEmails;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getDownloadDate() {
		return downloadDate;
	}


	public void setDownloadDate(String downloadDate) {
		this.downloadDate = downloadDate;
	}


	public int getSort() {
		return sort;
	}


	public void setSort(int sort) {
		this.sort = sort;
	}


}
