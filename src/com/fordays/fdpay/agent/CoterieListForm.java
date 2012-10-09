package com.fordays.fdpay.agent;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;

public class CoterieListForm extends ListActionForm {
	private long coterieId;
	private String coterieName = "";
	private String agentEmail = "";
	private Agent agent;
	private String tempAgentEmail="";
	
	public String getCoterieName() {
		return coterieName;
	}

	public void setCoterieName(String coterieName) {
		this.coterieName = coterieName;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		thisAction = "";
		coterieName="";
		agentEmail="";
		tempAgentEmail="";
	}

	public long getCoterieId() {
		return coterieId;
	}

	public void setCoterieId(long coterieId) {
		this.coterieId = coterieId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getTempAgentEmail() {
		return tempAgentEmail;
	}

	public void setTempAgentEmail(String tempAgentEmail) {
		this.tempAgentEmail = tempAgentEmail;
	}

}
